package com.orderbid.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderbid.beans.Tariff;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.TariffVO;
import com.orderbid.dao.RateDao;
import com.orderbid.dao.impl.RateDaoImpl;
import com.orderbid.service.RateService;
import com.orderbid.util.BeansVOConverter;

@Service
public class RateServiceImpl implements RateService{
	
	@Autowired
	private RateDao rateDao;
	
	public List<TariffVO> getRateDetails(User user, int cardType){
		return BeansVOConverter.getTarriffVOList(rateDao.getRateDetails(user, cardType));
	}
	
	@Override
	public int createTariff(Tariff tariff) {
		if(rateDao == null)
			rateDao = new RateDaoImpl();
		return  rateDao.createTariff(tariff);
	}
}
