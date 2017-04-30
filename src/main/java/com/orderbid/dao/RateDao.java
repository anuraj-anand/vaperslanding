package com.orderbid.dao;

import java.util.List;

import com.orderbid.beans.Tariff;
import com.orderbid.beans.User;

public interface RateDao {
	List<Tariff> getRateDetails(User user, int cardType);
	public int createTariff(Tariff tariff);
}
