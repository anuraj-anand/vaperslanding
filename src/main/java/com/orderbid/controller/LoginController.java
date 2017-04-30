package com.orderbid.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.SysexMessage;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.velocity.app.VelocityEngine;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.orderbid.beans.Company;
import com.orderbid.beans.HibernateUtil;
import com.orderbid.beans.Registration;
import com.orderbid.beans.User;
import com.orderbid.beans.Vap_Registration;
import com.orderbid.security.RSAEncryptionUtil;
import com.orderbid.service.CompanyService;
import com.orderbid.service.LoginService;
import com.orderbid.service.OrderService;
import com.orderbid.service.UserService;
import com.orderbid.util.AppUtil;
import com.orderbid.util.ApplicationConstants;
import com.orderbid.util.EmailUtil;
import com.orderbid.util.SendEmailUsingGMailSMTP;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

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

	/*
	 * public String getRecentlyAdded(HttpServletRequest request,
	 * HttpServletResponse response, ModelMap map){ List<Order> orders = new
	 * ArrayList<Order>(); Order order = new Order(); order.setId(1); // Set
	 * range parameter as per request GridRangeData range = new GridRangeData(1,
	 * 10); List<Order> lstOrders = orderService.recentlyAddedOrders(range);
	 * return AppUtil.getJsonString(lstOrders);
	 * 
	 * }
	 */
	@RequestMapping(value = "login")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "dlogin")
	public ModelAndView dlogin(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		return new ModelAndView("dlogin");
	}

	@RequestMapping(value = "logout")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		User user = (User) request.getSession().getAttribute(
				ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0) {
			request.getSession().invalidate();
		}
		return new ModelAndView("login");
	}

	@RequestMapping(value = "loginsubmit")
	@ResponseBody
	public String loginSubmit(HttpServletRequest request,
			HttpServletResponse response, ModelMap map, @RequestBody User user) {
		String result = "";

		try {
			if (user.getUserName() != null && !user.getUserName().isEmpty()) {
				String password = RSAEncryptionUtil.decrypt(user.getPassword());
				// Set encrypted password to check in database.
				user.setPassword(DigestUtils.md5Hex(password));
				user = loginService.userLogin(user);
				if (user != null && user.getId() > 0) {
					if (user.getCompany() != null
							&& user.getCompany().getActive() == 1&&user.getCompany().getKycFlag().equals("1")) {
						HttpSession session = request.getSession(true);
						session.setAttribute(ApplicationConstants.SESSION_USER,
								user);
						result = "Success";
					} 
					else if (user.getCompany() != null
														&& user.getCompany().getActive() == 1&& user.getCompany().getKycFlag().equals("0") ) {
													HttpSession session = request.getSession(true);
													session.setAttribute(ApplicationConstants.SESSION_USER,
															user);
													System.err.println("inside the else if");
													
													result = "NOTFILLED";
												}
					
					
					
					else {
						String message = "Your account is not yet activated by system administrator Please contact system administrator.";
						map.put("errorMsg", message);
						result = "notActivated";
					}

				} else {
					String message = "Mobile number or password is wrong. Please try again.";
					map.put("errorMsg", message);
					result = "Error";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AppUtil.getJsonString(result);
	}

	
	
	@RequestMapping(value = "deliveryloginsubmit")
	@ResponseBody
	public String dloginSubmit(HttpServletRequest request,
			HttpServletResponse response, ModelMap map, @RequestBody User user) {
		String result = "";

		orderService.getCanelledOrder();
		
		
		
		try {
			if (user.getUserName() != null && !user.getUserName().isEmpty()) {
				String password = RSAEncryptionUtil.decrypt(user.getPassword());
				// Set encrypted password to check in database.
				user.setPassword(DigestUtils.md5Hex(password));
				user = loginService.userLogin(user);
				
				
				if (user != null && user.getId() > 0) {
					if (user.getCompany() != null
							&& user.getCompany().getActive() == 1&&user.getCompany().getKycFlag().equals("1")) {
						HttpSession session = request.getSession(true);
						session.setAttribute(ApplicationConstants.SESSION_USER,
								user);
						result = "Success";
					} 
					else if (user.getCompany() != null
														&& user.getCompany().getActive() == 1&& user.getCompany().getKycFlag().equals("0") ) {
													HttpSession session = request.getSession(true);
													session.setAttribute(ApplicationConstants.SESSION_USER,
															user);
													System.err.println("inside the else if");
													
													result = "NOTFILLED";
												}
					
					
					
					else {
						String message = "Your account is not yet activated by system administrator Please contact system administrator.";
						map.put("errorMsg", message);
						result = "notActivated";
					}

				} else {
					String message = "Mobile number or password is wrong. Please try again.";
					map.put("errorMsg", message);
					result = "Error";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AppUtil.getJsonString(result);
	}

	
	
	
	

	/**
	 * Forget Password
	 */

	@RequestMapping(value = "forgotPassword")
	@ResponseBody
	public String ForgetPassword(HttpServletRequest request,
			HttpServletResponse response, ModelMap map, @RequestBody User user) {
		String result = "";

		try {
		
			if (user.getEmail() != null && !user.getEmail().isEmpty()) {
		
				if (loginService.isEmailExist(user)) {

					String pwd = "ABnnmi@fh23";
					user.setPassword(DigestUtils.md5Hex(pwd));

					User success = loginService.updatepwd(user);

					List<String> toList = new ArrayList<String>();
					toList.add(user.getEmail());
					String htmlBody = "Your New Password. Please login with Username : "
							+ user.getUserName();
					// sendEmail(email, "Account Registration", " Your account
					// registered. Please login.");
					try {

						Map<String, Object> model = new HashMap<String, Object>();
						model.put("URL", appURL);
						model.put("username", user.getUserName());
						model.put("password", pwd);
						String htmlText = VelocityEngineUtils
								.mergeTemplateIntoString(velocityEngine,
										"addUser.vm", model);
						EmailUtil.sendMailWithAuth(smtpHost, smtpUsername,
								smtpPassword, smtpPort, toList, htmlText,
								"OrderBid : Account Registration");
						// EmailUtil.SendSimpleMessage("kunalshinde098@gmail.com",
						// htmlText, "OrderBid : Account Registration");
						map.put("msg", "New user added successfully.");

						result = "New Password sent to your email id";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						result = "Error";

					}

				} else {
					System.err.println("Eid Not exist");
					String message = "Email is wrong. Please try again.";
					map.put("errorMsg", message);
					result = "Email Id Not exist";

				}
			} else {
				String message = "Email is wrong. Please try again.";
				map.put("errorMsg", message);
				result = "Email is wrong. Please try again";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return AppUtil.getJsonString(result);
	}

	/**
	 * @param request
	 * @param response
	 * @return Returns RSA public Key used for Encryption.
	 */
	@RequestMapping(value = "getPublicKey", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String getPublicKey(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("In GetPublicKey...");
		String publicKey = RSAEncryptionUtil.getPublicKey();
		return AppUtil.getJsonString(publicKey);
	}

	
	@RequestMapping(value = "sendmail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String sendMail(HttpServletRequest request,@RequestBody Registration registration,
			HttpServletResponse response) {
		System.out.println("In MAil Sending..."+registration.toString());
		registration.getEmail();
		SendEmailUsingGMailSMTP send=new SendEmailUsingGMailSMTP();
		send.SendMailEsseler(registration.getEmail());
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
   Vap_Registration vap=new Vap_Registration();
   vap.setVap_Registrationid(1);
   vap.setEmail(registration.getEmail());
   
		

         session.save(vap);     
        System.out.println("Finally Insert");
        
        
        System.out.println("Inserted Detaisls Successfully");
        
        
        
        

		session.getTransaction().commit();
		System.out.println("Done");
		//send.SendMailLogistic();
		
		
		
		//String publicKey = RSAEncryptionUtil.getPublicKey();
		return AppUtil.getJsonString("Success");
	}

	
	
	
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "createUser")
	@ResponseBody
	public String createUser(HttpServletRequest request,
			HttpServletResponse response, ModelMap map,
			@RequestBody Registration registration) {
		String result = "";
		if (registration != null && registration.getUsername() != null
				&& !registration.getUsername().isEmpty()) {
			String type = registration.getIndustryType();
			User user = new User();
			user.setUserName(registration.getUsername());
			user.setPassword(DigestUtils.md5Hex(registration.getPassword()));
			user.setType(ApplicationConstants.TYPE_USER_COMPANY_ADMIN);
			user.setEmail(registration.getEmail());
			user.setMobile(registration.getMobile());
			if (!loginService.isUserExist(user)) {
				Company company = new Company();
				company.setName(registration.getCompany());
				company.setUsers(null);
				company.setKycFlag("0");
				
				if (type.equalsIgnoreCase("warehouse")) {
					/*
					 * Warehouse house = new Warehouse();
					 * house.setName(registration.getCompany()); success =
					 * warehouseService.createEntity(house);
					 */
					company.setType(ApplicationConstants.TYPE_USER_WAREHOUSE);
				} else if (type.equalsIgnoreCase("logistics")) {
					/*
					 * Logisitcs logistics = new Logisitcs();
					 * logistics.setName(registration.getCompany()); success =
					 * logisitcsService.createEntity(logistics);
					 */
					company.setType(ApplicationConstants.TYPE_USER_LOGISTICS);
				} else {
					System.out.println("No type found.");
				}
				company.setActive(0);
				int companyId = companyService.createEntity(company);
				company.setId(companyId);
				user.setCompany(company);
				int success = loginService.createUser(user);
				// int success = 0;

				// success = companyService.createEntity(company);
				if (success > 1) {
					List<String> toList = new ArrayList<String>();
					toList.add(user.getEmail());
					String htmlBody = "Your account registered. Please login with Username : "
							+ user.getUserName();
					// sendEmail(email, "Account Registration", " Your account
					// registered. Please login.");
					try {
						Map<String, Object> model = new HashMap<String, Object>();
						model.put("URL", appURL);
						model.put("username", user.getUserName());
						model.put("password", registration.getPassword());
						String htmlText = VelocityEngineUtils
								.mergeTemplateIntoString(velocityEngine,
										"addUser.vm", model);
						EmailUtil.sendMailWithAuth(smtpHost, smtpUsername,
								smtpPassword, smtpPort, toList, htmlText,
								"OrderBid : Account Registration");
						// EmailUtil.SendSimpleMessage("kunalshinde098@gmail.com",
						// htmlText, "OrderBid : Account Registration");
						map.put("msg", "New user added successfully.");

						result = "Success";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						result = "Error";
					}
				}
			} else {
				result = "userExistsError";
			}
		}
		return AppUtil.getJsonString(result);
	}

}
