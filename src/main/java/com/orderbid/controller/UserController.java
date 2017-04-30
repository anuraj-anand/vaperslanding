package com.orderbid.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orderbid.beans.User;
import com.orderbid.beans.vo.UserVO;
import com.orderbid.service.KycServices;
import com.orderbid.service.UserService;
import com.orderbid.util.AppUtil;
import com.orderbid.util.ApplicationConstants;
import com.orderbid.util.EmailUtil;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@Autowired
	private KycServices kycService;
	
	
	
	
	
	
	@Value("${smtp.host}")
	private String smtpHost;

	@Value("${smtp.username}")
	private String smtpUsername;

	@Value("${smtp.password}")
	private String smtpPassword;

	@Value("${smtp.port}")
	private String smtpPort;
	
	@Value("${app.URL}")
	private String appURL;
	
	@Autowired
	private transient VelocityEngine velocityEngine;
	
	/**
	 * This method to get all users and applicable for both logistics and Eseller
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value="allUsers")
	@ResponseBody
	public String getAllUsers(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			List<UserVO> users = userService.getAllUsers(user);
			return AppUtil.getJsonString(users);
		}
		return null;
	}
	
	@RequestMapping(value="resetPassword")
	@ResponseBody
	public String resetPassword(HttpServletRequest request, HttpServletResponse response, ModelMap map, @RequestBody User addUser){
		String result = "";
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			String newPass = AppUtil.generateRandomPassword();
			addUser.setPassword(DigestUtils.md5Hex(newPass));
			userService.updateUser(addUser);
			//send Email for new password to the user.
			List<String> toList = new ArrayList<String>();
			toList.add(addUser.getEmail());
			try {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("URL", appURL);
				model.put("username", addUser.getUserName());
				model.put("password", newPass);
				String htmlText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "resetPassword.vm", model);
			    EmailUtil.sendMailWithAuth(smtpHost, smtpUsername, smtpPassword, smtpPort, toList, htmlText,
						"OrderBid : Password reset");
				map.put("msg", "password reset successfully.");
				result="Success";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="Error";
			}
			return AppUtil.getJsonString(result);
		}
		return null;
	}
	
	@RequestMapping(value="addUser")
	@ResponseBody
	public String addUser(HttpServletRequest request, HttpServletResponse response, ModelMap map, @RequestBody User addUser){
		String result = "";
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			String password = AppUtil.generateRandomPassword();
			addUser.setPassword(DigestUtils.md5Hex(password));
			addUser.setCompany(user.getCompany());
			int userId = userService.addUser(addUser);
			if(userId > 0){
				//send Email for new password to the new user.
				List<String> toList = new ArrayList<String>();
				toList.add(addUser.getEmail());
				String htmlBody = "Your Login created . Please login with Username : " + user.getUserName();
				// sendEmail(email, "Account Registration", " Your account
				// registered. Please login.");
				try {
					Map<String, Object> model = new HashMap<String, Object>();
					model.put("URL", appURL);
					model.put("username", addUser.getUserName());
					model.put("password", password);
					String htmlText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "addUser.vm", model);
				    EmailUtil.sendMailWithAuth(smtpHost, smtpUsername, smtpPassword, smtpPort, toList, htmlText,
							"OrderBid : Login created");
					map.put("msg", "New user added successfully.");
					result="Success";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result="Error";
				}
			}
		}
		return AppUtil.getJsonString(result);
	}
	
}
