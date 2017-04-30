package com.orderbid.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orderbid.beans.Tariff;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.TariffVO;
import com.orderbid.service.RateService;
import com.orderbid.util.AppUtil;
import com.orderbid.util.ApplicationConstants;

@Controller
public class LocationController {

	@Autowired
	private RateService rateService;
	
	@RequestMapping(value="getLocationDetails/{cardType}")
	@ResponseBody
	public String getLocationDetails(HttpServletRequest request, HttpServletResponse response, ModelMap map, @PathVariable int cardType){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			List<TariffVO> lstRateCards =  rateService.getRateDetails(user, cardType);
			return AppUtil.getJsonString(lstRateCards);
		}
		return null;
	}
	
	@RequestMapping(value="addRateCard")
	@ResponseBody
	public String addRateCard(HttpServletRequest request, HttpServletResponse response, ModelMap map, @RequestBody Tariff tariff){
		User user = (User)request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if(user != null && user.getId() > 0){
			tariff.setCompanyId(user.getCompany().getId());
			int rateId =  rateService.createTariff(tariff);
			if(rateId > 0){
				return AppUtil.getJsonString("Success");
			}
			return AppUtil.getJsonString("Error");
		}
		return null;
	}
}
