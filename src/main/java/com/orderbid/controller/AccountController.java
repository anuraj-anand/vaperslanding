package com.orderbid.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.orderbid.beans.BankDetails;
import com.orderbid.beans.Bid;
import com.orderbid.beans.BiddingSession;
import com.orderbid.beans.Company;
import com.orderbid.beans.DisplayDetails;
import com.orderbid.beans.KycInfo;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.PickupInfo;
import com.orderbid.beans.PrimaryContactInfo;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.BidVO;
import com.orderbid.beans.vo.ChangePasswordVO;
import com.orderbid.beans.vo.CompanyVO;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.dao.AdminDao;
import com.orderbid.security.RSAEncryptionUtil;
import com.orderbid.service.AdminService;
import com.orderbid.service.BidService;
import com.orderbid.service.CompanyService;
import com.orderbid.service.KycServices;
import com.orderbid.service.LoginService;
import com.orderbid.service.OrderService;
import com.orderbid.service.UserService;
import com.orderbid.util.AppUtil;
import com.orderbid.util.ApplicationConstants;

@Controller
public class AccountController {

	@Autowired
	CompanyService companyService;
	
	@Autowired
	BidService bidService;
	
	@Autowired
	KycServices kycService;
	
	@Autowired
	UserService userservice;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private OrderService ordreService;
	
	
	
	
	
	
	

	
	@RequestMapping(value="getCompanies")
	@ResponseBody
	public String getCompanies(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		
		
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0 && user.getCompany().getName().equalsIgnoreCase("Admin")){
			List<CompanyVO> companies = companyService.getCompanies();
			return AppUtil.getJsonString(companies);
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	
	
	@RequestMapping(value="getKycDetails/{companyId}")
	@ResponseBody
	public String getKYCDetails(@PathVariable  int companyId, HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0 && user.getCompany().getName().equalsIgnoreCase("Admin")){
			KycInfo info = kycService.getKycInfo(companyId);
			info.getCompany().setUsers(null);
			return AppUtil.getJsonString(info);
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	
	@RequestMapping(value="approveKYC")
	@ResponseBody
	public String approveKYC(HttpServletRequest request, HttpServletResponse response, ModelMap map, @RequestBody Company company){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0 && user.getCompany().getName().equalsIgnoreCase("Admin")){
			company.setKycFlag("1");
			companyService.updateCompanyStatus(company);
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	
	
	
	@RequestMapping(value = "getNotificationMessage")
	@ResponseBody
	public Map<String, Object> getNotificationMessage(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		//System.err.println("bosssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		
		Map<String, Object> mapEselLogisNames=null;
		
		
			
			System.err.println("=========getNotificationMessage=========="+user.getCompany().getId());
			System.err.println("getNotificationMessage=========="+user.getCompany().getId());
			System.err.println("getNotificationMessage=========="+user.getCompany().getId());
			System.err.println("getNotificationMessage==========");
			System.err.println("getNotificationMessage==========");
			System.err.println("getNotificationMessage==========");
			System.err.println("getNotificationMessage==========");
			
			
			//System.err.println("=======usename list===");
			 mapEselLogisNames = adminService.getNotificationMessage(user.getCompany().getId());
			return mapEselLogisNames;
		
		
	}
	
	
	
	@RequestMapping(value="updateCompanyStatus")
	@ResponseBody
	public String updateCompanyStatus(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody Company company){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0 && user.getCompany().getName().equalsIgnoreCase("Admin")){
			companyService.updateCompanyStatus(company);
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	
	
	@RequestMapping(value="getBiddingSessions")
	@ResponseBody
	public String getBiddingSessions(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		//if(user != null && user.getId() > 0 && user.getCompany().getName().equalsIgnoreCase("Admin")){
			List<BiddingSession> sessions = bidService.getBiddingSessions(user);
			return AppUtil.getJsonString(sessions);
		//}else{
		//	String errMsg = "You dont have permission for this page.";
		//	return AppUtil.getJsonString(errMsg);
		//}
	}
	
	@RequestMapping(value="addBiddingSession")
	@ResponseBody
	public String addBiddingSession(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody BiddingSession biddingSession){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0 && user.getCompany().getName().equalsIgnoreCase("Admin")){
			List<BiddingSession> sessions = bidService.getBiddingSessions(user);
			if(sessions != null){
				for(BiddingSession bidSession : sessions){
					if(bidSession.getDay().equalsIgnoreCase(biddingSession.getDay()) 
							&& bidSession.getFromTime().equals(biddingSession.getFromTime()) &&
							bidSession.getToTime().equals(biddingSession.getToTime())){
						String errMsg = "duplicate";
						return AppUtil.getJsonString(errMsg);
					}
				}	
			}
			bidService.addBiddingSession(biddingSession, user);
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	
	@RequestMapping(value="deleteBiddingSession")
	@ResponseBody
	public String deleteBiddingSession(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody BiddingSession biddingSession){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0 && user.getCompany().getName().equalsIgnoreCase("Admin")){
			bidService.deleteBiddingSession(biddingSession, user);
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	
	
	@RequestMapping(value="getEsselerorder")
	@ResponseBody
	public String getEsselerOrder(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		List<OrderVO> orders=null;
		if(user != null && user.getId() > 0){
			
			System.err.println("===================");
			orders = ordreService.getEsselerOrders(user);
			System.err.println("=========sizeeeeeeeeee=========="+orders.size());
			System.err.println("==================="+orders.size());
			System.err.println("==================="+orders.size());
			
			
			
	}
		return AppUtil.getJsonString(orders);
	}
	
	
	@RequestMapping(value="getAdminOrder")
	@ResponseBody
	public String getAdminOrder(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		List<OrderVO> orders=null;
		if(user != null && user.getId() > 0){
			
			System.err.println("===================");
			orders = ordreService.getAdminOrder();
			System.err.println("=========sizeeeeeeeeee=========="+orders.size());
			System.err.println("==================="+orders.size());
			System.err.println("==================="+orders.size());
			
			
			
	}
		return AppUtil.getJsonString(orders);
	}
	
	
	
	@RequestMapping(value="getlogisticOrder",method = RequestMethod.POST, produces =MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getlogisticOrder(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		List<OngoingBid> bids=null;
		if(user != null && user.getId() > 0){
			
			System.err.println("===================");
			bids = bidService.getLogisticOrder(user);
			System.err.println("=========sizeeeeeeeeee=========="+bids.size());
			System.err.println("==================="+bids.size());
			System.err.println("==================="+bids.size());
			
			
			
	}
		return AppUtil.getJsonString(bids);
	}
	
	/*@RequestMapping(value="cancelBid",method = RequestMethod.POST,produces =MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String cancelBid(HttpServletRequest request, HttpServletResponse response, ModelMap map,@RequestBody Order order){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
	//	List<OngoingBid> bids=null;
		Boolean status=true;
		if(user != null && user.getId() > 0){
			
			System.err.println("===================");
			System.err.println("===="+order.toString());
			 status = bidService.updateCancelStatus(order.getOrderNo(),"Cancel");
			System.err.println("=========status=========="+status);
			System.err.println("============status======="+status);
			System.err.println("==================="+status);
			
			
			
	}
		return "";
	}*/
	
	@RequestMapping(value = "/cancelBid", method = RequestMethod.POST, produces = "application/String; charset=utf-8")
	public @ResponseBody String getSKUSearch(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String orderNo) {

		

		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			  bidService.updateCancelStatus(orderNo,"Cancel");
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
		

	}
	/*@RequestMapping(value="cancelBid")
	@ResponseBody
	public String cancelBid(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody OngoingBid display){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			  bidService.updateCancelStatus(display.getOrderNo(),"Cancel");
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}*/
	
	@RequestMapping(value="getAdminBidOrder",method = RequestMethod.POST, produces =MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAdminBidOrder(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		List<OngoingBid> bids=null;
		if(user != null && user.getId() > 0){
			
			//System.err.println("===================");
			bids = bidService.getAdminBidOrder();
			//System.err.println("=========sizeeeeeeeeee=========="+bids.size());
		//	System.err.println("==================="+bids.size());
		//	System.err.println("==================="+bids.size());
			
			
			
	}
		return AppUtil.getJsonString(bids);
	}
	
	
	@RequestMapping(value="getAccountInfo")
	@ResponseBody
	public String getAccountInfo(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			DisplayDetails details = kycService.getDisplayDetails(user.getCompany().getId());
			if(details != null){
				details.getCompany().setUsers(null);
				map.put("display", details);	
			}
			
			PrimaryContactInfo primary = kycService.getPrimaryInfo(user.getCompany().getId());
			if(primary != null){
				primary.getCompany().setUsers(null);
				map.put("primary", primary);	
			}
			PickupInfo pickup = kycService.getPickupInfo(user.getCompany().getId());
			if(pickup != null){
				pickup.getCompany().setUsers(null);
				map.put("pickup", pickup);	
			}
			BankDetails bank =  kycService.getBankInfo(user.getCompany().getId());
			if(bank != null){
				bank.getCompany().setUsers(null);
				map.put("bank", bank);	
			}
			KycInfo kyc = kycService.getKycInfo(user.getCompany().getId());
			if(kyc != null){
				kyc.getCompany().setUsers(null);
				map.put("kyc", kyc);	
			}
			
			User userdetails=kycService.getUser(user.getUserName());
			if(userdetails != null){
				userdetails.getCompany().setUsers(null);
				
				map.put("userdetails",userdetails);	
			}
			
			
			return AppUtil.getJsonString(map);
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	
	@RequestMapping(value="saveDisplayInfo")
	@ResponseBody
	public String saveDisplayInfo(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody DisplayDetails display){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			display.setCompany(user.getCompany());
			kycService.saveorupdatedisplayinfo(display);
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	
	@RequestMapping(value="savePickupInfo")
	@ResponseBody
	public String savePickupInfo(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody PickupInfo pickup){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			pickup.setCompany(user.getCompany());
			kycService.saveOrUpdatePickupInfo(pickup);
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	@RequestMapping(value="savePrimaryInfo")
	@ResponseBody
	public String savePrimaryInfo(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody PrimaryContactInfo primary){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			primary.setCompany(user.getCompany());
			kycService.saveOrUpdatePrimaryInfo(primary);
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	@RequestMapping(value="saveBankInfo")
	@ResponseBody
	public String saveBankInfo(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody BankDetails bank){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			bank.setCompany(user.getCompany());
			kycService.saveOrUpdateBankInfo(bank);
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	
	@RequestMapping(value="saveKYCInfo")
	@ResponseBody
	public String saveKYCInfo(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody KycInfo kyc ){
		
		
	//	System.err.println("inside the saveKYCInfo caall---------");
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			kyc.setCompany(user.getCompany());
			kycService.saveOrUpdateKycInfo(kyc);
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	
	
	//Save Profile pics controller
	@RequestMapping(value="saveProfilePics",method = RequestMethod.POST, produces =MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveProfilePics(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody User  userDetails){
		
		
		System.err.println("inside the saveprofile caall---------"+userDetails.toString());
		System.err.println("inside the saveprofile caall---------"+userDetails.toString());
		System.err.println("inside the saveprofile caall---------"+userDetails.toString());
		System.err.println("inside the saveprofile caall---------");
		System.err.println("inside the saveprofile caall---------");
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0 && user.getUserName().equalsIgnoreCase(userDetails.getUserName())){
			
			User userPicture=new User();
			userPicture.setProfilePicture(userDetails.getProfilePicture());
			
			System.err.println("Inside the if condition");
			userPicture.setCompany(user.getCompany());
			kycService.saveOrUpdateProfilePics(userPicture);
			System.err.println("After Sucess update");
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}
	
	
	
	

	
	//Save ChangePassword
	
	@RequestMapping(value="saveChangedPassword")
	@ResponseBody
	public String saveChangedPassword(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody ChangePasswordVO changepass ){
		
		
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			
			
			System.err.println("Before Changing Password"+user.getPassword());
			
			System.err.println("");
			
			
			
			
			String password = RSAEncryptionUtil.decrypt(changepass.getCurrentpassword());
			// Set encrypted password to check in database.


			
		   System.err.println("user.getPassword()=========="+user.getPassword());
			System.err.println("password======================== "+DigestUtils.md5Hex(password));
			//String password = RSAEncryptionUtil.decrypt(user.getPassword());
			// Set encrypted password to check in database.
			//user.setPassword(DigestUtils.md5Hex(password));
			//user = loginService.userLogin(user);
			if(user.getPassword().equalsIgnoreCase(DigestUtils.md5Hex(password)))
			{
				String passwordnew = RSAEncryptionUtil.decrypt(changepass.getNewpassword());

				user.setPassword(DigestUtils.md5Hex(passwordnew));
				userservice.saveOrUpdateChangePassword(user);
				System.err.println("After Password Successfully Updating");
				
			}
			else
			{
				System.err.println("Password Is Not Matched");
			}
			
			
			
			
			
			
			System.err.println("FROM ui paSSWORD IS COMING"+changepass.getCurrentpassword()+"===="+changepass.getNewpassword()+"=="+changepass.getConfirmpassword());
			//userservice.saveOrUpdateChangePassword(userpasssword);
			
			System.err.println("After Changing Password");
			return AppUtil.getJsonString("Success");
		}else{
			String errMsg = "You dont have permission for this page.";
			return AppUtil.getJsonString(errMsg);
		}
	}

	
	
	
}
