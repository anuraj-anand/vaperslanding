package com.orderbid.service;

import java.util.List;

import com.orderbid.beans.Tariff;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.TariffVO;

public interface RateService {
	List<TariffVO> getRateDetails(User user, int cardType);
	int createTariff(Tariff tariff);
}
