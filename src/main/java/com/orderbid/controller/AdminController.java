package com.orderbid.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orderbid.beans.Broad_casting_Message;
import com.orderbid.beans.Company;
import com.orderbid.beans.KycInfo;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.BidVO;
import com.orderbid.beans.vo.CompanyVO;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.service.AdminService;
import com.orderbid.util.AppUtil;
import com.orderbid.util.ApplicationConstants;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@RequestMapping(value = "getEselLogisPendingApproval")
	@ResponseBody
	public Map<String, Object> getEselLogPendingApproval() {
		return adminService.getEselLogPendingApproval();
	}

	@RequestMapping(value = "getEselLogisUserList")
	@ResponseBody
	public List<CompanyVO> getEselLogisUserList() {
		return adminService.getEselLogisUserList();
	}

	@RequestMapping(value = "getActiveEsselerLogisticUser")
	@ResponseBody
	public Map<String, Object> getActiveEselLogis() {
		return adminService.getActiveEselLogis();
	}

	@RequestMapping(value = "getEsselLogiskycStatus")
	@ResponseBody
	public Map<String, Object> getEsselLogiskycStatus() {
		return adminService.getEsselLogiskycStatus();
	}

	@RequestMapping(value = "filterRegisEselLogisUsers")
	@ResponseBody
	public List<CompanyVO> filterRegisEselLogisUsers(@RequestParam(value = "userType") Integer userType,
			@RequestParam(value = "filterType") Integer filterType,
			@RequestParam(value = "startTimestamp",required=false) Integer startTimestamp,
			@RequestParam(value = "endTimestamp",required=false) Integer endTimestamp) {
		return adminService.filterRegisEselLogisUsers(userType, filterType, startTimestamp, endTimestamp);
	}

	@RequestMapping(value = "filterOrdersRegisteredByEsellerUser")
	@ResponseBody
	public List<OrderVO> filterOrdersRegisteredByEsellerUser(@RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "filterType") Integer filterType,
			@RequestParam(value = "startTimestamp",required=false) Long startTimestamp,
			@RequestParam(value = "endTimestamp",required=false) Long endTimestamp) {
		return adminService.filterOrdersRegisteredByEsellerUser(userId, filterType, startTimestamp, endTimestamp);
	}
	
	@RequestMapping(value = "filterOrdersByLogisticUser")
	@ResponseBody
	public List<BidVO> filterOrdersByLogisticUser(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "filterType") Integer filterType,
			@RequestParam(value = "startTimestamp",required=false) Long startTimestamp,
			@RequestParam(value = "endTimestamp",required=false) Long endTimestamp) {
		return adminService.filterOrdersByLogisticUser(userId, filterType, startTimestamp, endTimestamp);
	}
	
	@RequestMapping(value = "getTotalRegisteredOrders")
	@ResponseBody
	public List<OrderVO> getTotalRegisteredOrders(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getUserName().equalsIgnoreCase("admin")) {
			List<OrderVO> orders = adminService.getTotalRegisteredOrders();
			return orders;
		}
		return null;
	}
	
	@RequestMapping(value = "getTotalBidedOrders")
	@ResponseBody
	public List<BidVO> getTotalBidedOrders(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getUserName().equalsIgnoreCase("admin")) {
			List<BidVO> bidedOrders = adminService.getTotalBidedOrders();
			return bidedOrders;
		}
		return null;
	}
	
	@RequestMapping(value = "getEselLogisNameList")
	@ResponseBody
	public Map<String, Object> getEselLogisNameList(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getUserName().equalsIgnoreCase("admin")) {
			Map<String, Object> mapEselLogisNames = adminService.getEselLogisNameList();
			return mapEselLogisNames;
		}
		return null;
	}
	
	
	
	@RequestMapping(value = "getUsernamelist")
	@ResponseBody
	public Map<String, Object> getUsernamelist(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getUserName().equalsIgnoreCase("admin")) {
			
			
			System.err.println("=======usename list===");
			Map<String, Object> mapEselLogisNames = adminService.getUsernamelist();
			return mapEselLogisNames;
		}
		return null;
	}
	
	
	
	
	
	
	
	@RequestMapping(value="SaveNotification")
	@ResponseBody
	public String saveKYCInfo(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestParam(value = "ID") String ID,
			@RequestParam(value = "uname") String uname,
			@RequestParam(value = "bmsg") String bmsg){
		//
		
		//System.err.println("ID"+ID+"========uname"+uname+"bmsg====="+bmsg);
		//
		String errMsg="";
		//System.err.println("inside the SaveNotification caall---------");
		String[] nameparts = uname.split(",");
		
		
	//	System.err.println("nameparts[0];"+nameparts[0]+"nameparts[1];"+nameparts[1]);
		String id;
		String name;
		//System.err.println("======"+bmsg+"=======message===nameparts.length======"+nameparts.length);
		for(int i=0;i<nameparts.length;i++ )
		{
			//System.err.println("============"+uname+"nameeee");
			 id=nameparts[i].split("-")[0];
			 name=nameparts[i].split("-")[1];
			
		//	System.err.println("Integer.parseInt(idparts[i])   "+nameparts[i].split("-")[0]);
			//System.err.println("nameparts[i]"+nameparts[i]);
		
         
		//System.err.println("======"+bmsg+"=======message=========");
		 
		//System.err.println("============"+uname+"nameeee");
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			
			Broad_casting_Message bmessage=new Broad_casting_Message();
			Company company=new Company();
			company.setId(Integer.parseInt(id));
			bmessage.setCompany_Id(company);
			bmessage.setUsername(name);
			bmessage.setBmessage(bmsg);
			
			adminService.saveOrUpdateBroadcastingmessage(bmessage);
			//return AppUtil.getJsonString("Success");
			
			errMsg="Success";
		
		}else{
			  errMsg = "You dont have permission for this page.";
			
		}
		}
		return AppUtil.getJsonString(errMsg);
	
}
}
