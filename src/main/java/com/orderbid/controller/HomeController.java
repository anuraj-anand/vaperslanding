package com.orderbid.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.orderbid.beans.KycInfo;
import com.orderbid.beans.OrderGrid;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.importdata.OrderBidImport;
import com.orderbid.importdata.OrderBidImportFactory;
import com.orderbid.service.BidService;
import com.orderbid.service.KycServices;
import com.orderbid.service.OrderService;
import com.orderbid.util.AppUtil;
import com.orderbid.util.ApplicationConstants;
import com.orderbid.util.GridRangeData;

@Controller
public class HomeController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BidService bidServie;
	
	@Autowired
	private KycServices kycService;
	
	@RequestMapping(value="home")
	public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		String viewName = "login";
		if(user != null && user.getId() > 0){
			if(user.getCompany().getName().equalsIgnoreCase("admin")){
				viewName = "admin_home";
			} else if(user.getCompany().getType() == 1){
				viewName = "warehouse_home";
			} else if(user.getCompany().getType() == 2){
				viewName = "logistics_home";
			}
		}else{
			viewName = "login";
		}
		return new ModelAndView(viewName);
	}
	
	
	@RequestMapping(value="dboyhome")
	public ModelAndView  deliveryDashboard(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		System.out.println("comming user details==>  "+user.toString()+user.getEmail()+"===user type=="+user.getCompany().getType());
		String viewName = "track_order";
		if(user != null && user.getId() > 0){
			 if(user.getCompany().getType() == 3){
				 
				viewName = "track_order";
			}
		}else{
			viewName = "track_order";
		}
		System.out.println("my view name=======<><><><><><   "+viewName);
		return new ModelAndView(viewName);
	}
	
	@RequestMapping(value="getLoggedIn")
	@ResponseBody
	public String getLoggedIn(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			user.getCompany().setUsers(null);
			KycInfo kyc = kycService.getKycInfo(user.getCompany().getId());
			if(kyc != null && kyc.getAddressProof() != null && kyc.getAddressProof().length() > 0
					&& kyc.getIdProof() != null && kyc.getIdProof().length() > 0 
					&& kyc.getCompany().getKycFlag().equalsIgnoreCase("1")){
				map.put("kyc", true);
			}
			map.put("user", user);
			return AppUtil.getJsonString(map);
		}
		return null;
	}
	
	@RequestMapping(value="matchingOrders")
	@ResponseBody
	public String getMatchingOrders(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			List<OrderVO> orders = orderService.getAutQulifyOrders(user);
			return AppUtil.getJsonString(orders);
		}
		return null;
	}
	
	@RequestMapping(value="recentlyAddedOrders/{itemCount}/{pageNO}")
	@ResponseBody
	public String getRecentlyAdded(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@PathVariable int itemCount, @PathVariable int pageNO){

		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			// Set range parameter as per request
			GridRangeData range = AppUtil.getGridRangeData(itemCount, pageNO);
			List<OrderVO> lstOrders = orderService.getRecentlyAddedOrders(range, user);
			bidServie.getOrderVOWithMaxBids(lstOrders, user);
			OrderGrid grid = new OrderGrid();
			grid.setOrders(lstOrders);
			return AppUtil.getJsonString(grid);
		}
		return "";
	}
	
	/*@RequestMapping(value="autoQulifyOrders")
	@ResponseBody
	public String getAutQulifyOrders(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			// Set range parameter as per request
			GridRangeData range = new GridRangeData(1, 10);
			List<OrderVO> lstOrders = orderService.getAutoQulifyOrders(user, range);
			bidServie.getOrderVOWithMaxBids(lstOrders, user);
			return AppUtil.getJsonString(lstOrders);
		}
		return "";
	}*/
	
	@RequestMapping(value="autoQulifyOrders/{itemCount}/{pageNO}")
	@ResponseBody
	public String getAutQulifyOrders(HttpServletRequest request, HttpServletResponse response, ModelMap map, 
			@PathVariable int itemCount, @PathVariable int pageNO){
		System.out.println("Itms::"+itemCount +" pageNo::"+pageNO);
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			// Set range parameter as per request
			GridRangeData range = AppUtil.getGridRangeData(itemCount, pageNO);
			List<OrderVO> lstOrders = orderService.getAutoQulifyOrders(user, range);
			bidServie.getOrderVOWithMaxBids(lstOrders, user);
			OrderGrid grid = new OrderGrid();
			grid.setOrders(lstOrders);
			return AppUtil.getJsonString(grid);
		}
		return "";
	}
	
	
	@RequestMapping(value="getAlldetails")
	@ResponseBody
	public String getAlldetails(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			List<OrderVO> lstTotalOdrs = orderService.getTotalUpalodedOrders(user);
			List<OrderVO> lstRecentOdrs = orderService.getRecentlyAddedOrders(user);
			List<OrderVO> lstExpiredOdrs = orderService.getExpriedOrders(user);
			if(user.getCompany().getType() == 2){
				List<OrderVO> lstQulifiedOdrs = orderService.getAutoQulifyOrders(user, null);
				map.put("autoQualified", lstQulifiedOdrs !=null ? lstQulifiedOdrs.size() : 0);
			}
			map.put("totalOrders", lstTotalOdrs !=null ? lstTotalOdrs.size() : 0);
			map.put("newOrders", lstRecentOdrs !=null ? lstRecentOdrs.size() : 0);
			map.put("expired", lstExpiredOdrs !=null ? lstExpiredOdrs.size() : 0);
			return AppUtil.getJsonString(map);
		}
		return "";
	}
	
	@RequestMapping(value="getPortletData")
	@ResponseBody
	public String getPortletData(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		List<OrderVO> lstClosedOdrs = orderService.getDeliveredOrders(user);
		List<OrderVO> lstOpenOdrs = orderService.getOpenOrders(user);
		map.put("closedOrders", lstClosedOdrs !=null ? lstClosedOdrs.size() : 0);
		map.put("openOrders", lstOpenOdrs !=null ? lstOpenOdrs.size() : 0);
		map.put("hotOrders", orderService.getHotOrders(user));
		if(user.getCompany().getType() == 2){
			List<OrderVO> lstQulifiedOdrs = orderService.getAutoQulifyOrders(user, null);
			map.put("autoQualified", lstQulifiedOdrs !=null ? lstQulifiedOdrs.size() : 0);
		}
		return AppUtil.getJsonString(map);
	}
	
	@RequestMapping(value="uploadOrder")
	@ResponseBody
	public String uploadOrder(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		try{
			User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
			String result = "";
			if(user != null && user.getId() > 0){
				Map<String, MultipartFile> fileMap =((MultipartRequest)request).getFileMap();
				MultipartFile file  = fileMap.get("file");
				String extension = AppUtil.getFileExtension(file.getOriginalFilename());
				if(extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")){
					OrderBidImport orderImport = OrderBidImportFactory.getOrderBidImporter(extension);
					List<OrderVO> lstOrders =  orderImport.importOrders(file.getInputStream(), user);
					orderService.importOrders(lstOrders, user);
					
				}else{
					result = "file not supported.";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
}