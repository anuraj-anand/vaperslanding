package com.orderbid.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.orderbid.beans.Tariff;
import com.orderbid.beans.User;
import com.orderbid.dao.RateDao;

@Repository("RateDao")
@Transactional
public class RateDaoImpl  extends BaseDaoImpl implements RateDao{
	
	public List<Tariff> getRateDetails(User user, int cardType){
		List<Tariff> rateCards = new ArrayList<Tariff>();
		String query = "from Tariff where companyId = ? and cardType=?";
		Object[] queryParam = { user.getCompany().getId(), cardType};
		rateCards = (List<Tariff>) find(query, queryParam);
		if (rateCards != null && rateCards.size() > 0) {
			return rateCards;
		}
		return null;
	}
	
	public int createTariff(Tariff tariff){
		return (Integer)createEntity(tariff);
	}

}
