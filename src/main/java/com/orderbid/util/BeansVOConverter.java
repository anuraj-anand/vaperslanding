package com.orderbid.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.orderbid.beans.Bid;
import com.orderbid.beans.Broad_casting_Message;
import com.orderbid.beans.Company;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.OrderWatch;
import com.orderbid.beans.Tariff;
import com.orderbid.beans.User;
import com.orderbid.beans.WinnerBid;
import com.orderbid.beans.vo.BidVO;
import com.orderbid.beans.vo.CompanyVO;
import com.orderbid.beans.vo.OrderStatusVO;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.beans.vo.OrderWatchVO;
import com.orderbid.beans.vo.TariffVO;
import com.orderbid.beans.vo.UserVO;

/**
 * This is POJO to VO converter
 * @author KsMAC
 *
 */
public class BeansVOConverter {

	/**
	 * Order to OrderVO
	 * @param order
	 * @return
	 */
	public static OrderVO getOrderVO(Order order){
		OrderVO ref  = new OrderVO();
		if(order == null)
			return null;
		BeanUtils.copyProperties(order, ref);
		ref.setComapnyVo(getCompanyVO(order.getCompany()));
		return ref;
	}
	
	
	/**
	 * Bid to BidVO converter
	 * @param bid
	 * @return
	 */
	public static BidVO getBidVO(Bid bid){
		BidVO ref  = new BidVO();
		if(bid == null)
			return null;
		BeanUtils.copyProperties(bid, ref);
		return ref;
	}
	
	/**
	 * OrderVO to Order
	 * @param order
	 * @return
	 */
	public static Order getOrder(OrderVO orderVO){
		Order ref  = new Order();
		if(orderVO == null)
			return null;
		BeanUtils.copyProperties(orderVO, ref);
		ref.setCompany(getCompany(orderVO.getComapnyVo()));
		return ref;
	}
	
	/**
	 * Bid to BidVO converter
	 * @param bid
	 * @return
	 */
	public static UserVO getUserVO(User user){
		UserVO ref  = new UserVO();
		if(user == null)
			return null;
		BeanUtils.copyProperties(user, ref);
		return ref;
	}
	
	/**
	 * Bid to BidVO converter
	 * @param bid
	 * @return
	 */
	public static CompanyVO getCompanyVO(Company company){
		CompanyVO ref  = new CompanyVO();
		if(company == null)
			return null;
		BeanUtils.copyProperties(company, ref);
		if(company.getType() == ApplicationConstants.TYPE_USER_WAREHOUSE){
			ref.setTypeStr("E-seller");
		}else if(company.getType() == ApplicationConstants.TYPE_USER_LOGISTICS){
			ref.setTypeStr("Logistics");
		} else if(company.getType() == ApplicationConstants.TYPE_USER_ADMIN){
			ref.setTypeStr("Super Admin");
		}
		if(company.getActive() != null)
			ref.setActiveBool(company.getActive() == 1 ? true : false);
		return ref;
	}
	
	/**
	 * OrderVO to Order
	 * @param order
	 * @return
	 */
	public static Company getCompany(CompanyVO companyVO){
		Company ref  = new Company();
		if(companyVO == null)
			return null;
		BeanUtils.copyProperties(companyVO, ref);
		
		return ref;
	}
	
	/**
	 * BidVO to Bid converter
	 * @param order
	 * @return
	 */
	public static BidVO getgBidVO(Bid bid){
		BidVO ref  = new BidVO();
		if(bid == null)
			return null;
		BeanUtils.copyProperties(bid, ref);
		return ref;
	}
	
	/**
	 * BidVO to Bid converter
	 * @param order
	 * @return
	 */
	public static Bid getOngoingBid(BidVO bidVO){
		Bid ref  = new OngoingBid();
		if(bidVO == null)
			return null;
		BeanUtils.copyProperties(bidVO, ref);
		return ref;
	}

	/**
	 * OrderWatch to OrderWatchVO converter
	 * @param order
	 * @return
	 */
	public static OrderWatchVO getgOrderWatchVO(OrderWatch odrWatch){
		OrderWatchVO ref  = new OrderWatchVO();
		if(odrWatch == null)
			return null;
		BeanUtils.copyProperties(odrWatch, ref);
		return ref;
	}
	
	/**
	 * OrderWatchVO to OrderWatch converter
	 * @param order
	 * @return
	 */
	public static OrderWatch getOngoingBid(OrderWatchVO odrWatchVO){
		OrderWatch ref  = new OrderWatch();
		if(odrWatchVO == null)
			return null;
		BeanUtils.copyProperties(odrWatchVO, ref);
		return ref;
	}
	
	/**
	 * BidVO to Bid converter
	 * @param order
	 * @return
	 */
	public static Bid getWinnerBid(BidVO bidVO){
		Bid ref  = new WinnerBid();
		if(bidVO == null)
			return null;
		BeanUtils.copyProperties(bidVO, ref);
		return ref;
	}
	
	/**
	 * BidVO to Bid converter
	 * @param order
	 * @return
	 */
	public static OngoingBid getOngoingBid(Bid bid){
		if(bid == null)
			return null;
		if(bid instanceof OngoingBid)
			return (OngoingBid) bid;
		OngoingBid ref  = new OngoingBid();
		BeanUtils.copyProperties(bid, ref);
		return ref;
	}
	
	/**
	 * BidVO to Bid converter
	 * @param order
	 * @return
	 */
	public static WinnerBid getWinnerBid(Bid bid){
		if(bid == null)
			return null;
		if(bid instanceof WinnerBid)
			return (WinnerBid) bid;
		WinnerBid ref  = new WinnerBid();
		BeanUtils.copyProperties(bid, ref);
		return ref;
	}
	
	/**
	 * BidVO to Bid converter
	 * @param order
	 * @return
	 */
	public static List<OngoingBid> getOngoingBid(List<Bid> lstBid){
		if(lstBid == null)
			return null;
		List<OngoingBid> refOngoingBids = new ArrayList<OngoingBid>();
		for (Iterator iterator = lstBid.iterator(); iterator.hasNext();) {
			Bid bid = (Bid) iterator.next();
			if(bid instanceof OngoingBid){
				refOngoingBids.add((OngoingBid) bid);
			} else {
				refOngoingBids.add(getOngoingBid(bid));
			}
		}
		return refOngoingBids;
	}
	
	/**
	 * BidVO to Bid converter
	 * @param order
	 * @return
	 */
	public static List<WinnerBid> getWinnerBidList(List<Bid> lstBid){
		if(lstBid == null)
			return null;
		List<WinnerBid> refWinnerBids = new ArrayList<WinnerBid>();
		for (Iterator iterator = lstBid.iterator(); iterator.hasNext();) {
			Bid bid = (Bid) iterator.next();
			if(bid instanceof WinnerBid){
				refWinnerBids.add((WinnerBid) bid);
			} else {
				refWinnerBids.add(getWinnerBid(bid));
			}
		}
		return refWinnerBids;
	}
	
	/**
	 * BidVO to Bid converter
	 * @param order
	 * @return
	 */
	public static User getUser(UserVO userVO){
		User ref  = new User();
		if(userVO == null)
			return null;
		BeanUtils.copyProperties(userVO, ref);
		return ref;
	}

	/**
	 * Order to OrderVO list converter
	 * @param lstOrder
	 * @return
	 */
	public static List<OrderVO> getOrderVOList(List<Order> lstOrder){
		List<OrderVO> refList  = new  ArrayList<OrderVO>();
		if(lstOrder == null)
			return null;
		int count =0;
		for (Order order : lstOrder) {
			count++;
			OrderVO ref = getOrderVO(order);
			ref.setSrNo(count);
			refList.add(ref);
		}
		return refList;
	}
	
	/**
	 * OrderWatch to OrderWatchVO list converter
	 * @param lstOrder
	 * @return
	 */
	public static List<OrderWatchVO> getOrderWatchVOList(List<OrderWatch> lstOrder){
		List<OrderWatchVO> refList  = new  ArrayList<OrderWatchVO>();
		if(lstOrder == null)
			return null;
		for (OrderWatch order : lstOrder) {
			OrderWatchVO ref = getgOrderWatchVO(order);
			refList.add(ref);
		}
		return refList;
	}
	
	/**
	 * OrderVO to Order list converter
	 * @param lstOrder
	 * @return
	 */
	public static List<Order> getOrderList(List<OrderVO> lstOrderVO){
		List<Order> refList  = new  ArrayList<Order>();
		if(lstOrderVO == null)
			return null;
		for (OrderVO orderVO : lstOrderVO) {
			refList.add(getOrder(orderVO));
		}
		return refList;
	}
	
	/**
	 * Bid to BidVO list converter
	 * @param lstOrder
	 * @return
	 */
	public static List<BidVO> getBidVOList(List<Bid> lstBid){
		List<BidVO> refList  = new  ArrayList<BidVO>();
		if(lstBid == null)
			return null;
		for (Bid bid : lstBid) {
			BidVO ref = new BidVO();
			BeanUtils.copyProperties(bid, ref);
			refList.add(ref);
		}
		return refList;
	}
	
	/**
	 * BidVO to Bid list converter
	 * @param lstOrder
	 * @return
	 */
	public static List<Bid> getBidList(List<BidVO> lstBidVO){
		List<Bid> refList  = new  ArrayList<Bid>();
		if(lstBidVO == null)
			return null;
		for (BidVO order : lstBidVO) {
			Bid ref = new OngoingBid();
			BeanUtils.copyProperties(order, ref);
			refList.add(ref);
		}
		return refList;
	}
	

	/**
	 * User to UserVO list converter
	 * @param lstOrder
	 * @return
	 */
	public static List<UserVO> getUserVOList(List<User> lstUsers){
		List<UserVO> refList  = new  ArrayList<UserVO>();
		if(lstUsers == null)
			return null;
		for (User user : lstUsers) {
			user.getCompany().setUsers(null);
			UserVO ref = new UserVO();
			BeanUtils.copyProperties(user, ref);
			if(user.getType() == 1){
				ref.setType("Admin");
			} else if(user.getType() == 2){
				ref.setType("Bidder");
			} else{
				ref.setType("Delivery guy");
			}
			refList.add(ref);
		}
		return refList;
	}
	

	/**
	 * UserVO to User list converter
	 * @param lstOrder
	 * @return
	 */
	public static List<User> getUserList(List<UserVO> lstUsersVO){
		List<User> refList  = new  ArrayList<User>();
		if(lstUsersVO == null)
			return null;
		for (UserVO uservo : lstUsersVO) {
			User ref = new User();
			BeanUtils.copyProperties(uservo, ref);
			refList.add(ref);
		}
		return refList;
	}
	

	/**
	 * User to CompanyVO list converter
	 * @param lstOrder
	 * @return
	 */
	public static List<CompanyVO> getCompanyVOList(List<Company> lstCompanys){
		List<CompanyVO> refList  = new  ArrayList<CompanyVO>();
		if(lstCompanys == null)
			return null;
		for (Company company : lstCompanys) {
			refList.add(getCompanyVO(company));
		}
		return refList;
	}
	

	/**
	 * UserVO to User list converter
	 * @param lstOrder
	 * @return
	 */
	public static List<Company> getCompanyList(List<CompanyVO> lstCompanysVO){
		List<Company> refList  = new  ArrayList<Company>();
		if(lstCompanysVO == null)
			return null;
		for (CompanyVO companyVO : lstCompanysVO) {
			refList.add(getCompany(companyVO));
		}
		return refList;
	}
	
	/**
	 * User to CompanyVO list converter
	 * @param lstOrder
	 * @return
	 */
	public static List<TariffVO> getTarriffVOList(List<Tariff> lstTariffs){
		List<TariffVO> refList  = new  ArrayList<TariffVO>();
		if(lstTariffs == null)
			return null;
		for (Tariff tariff: lstTariffs) {
			refList.add(getTariffVO(tariff));
		}
		return refList;
	}
	

	/**
	 * UserVO to User list converter
	 * @param lstOrder
	 * @return
	 */
	public static List<Tariff> getTariffList(List<TariffVO> lstTariffsVO){
		List<Tariff> refList  = new  ArrayList<Tariff>();
		if(lstTariffsVO == null)
			return null;
		for (TariffVO tariffVO : lstTariffsVO) {
			refList.add(getTariff(tariffVO));
		}
		return refList;
	}
	
	/**
	 * RateCard to RateCardVO converter
	 * @param bid
	 * @return
	 */
	public static TariffVO getTariffVO(Tariff tariff){
		TariffVO ref  = new TariffVO();
		if(tariff == null)
			return null;
		BeanUtils.copyProperties(tariff, ref);
		ref.setCharges(tariff.getCharges());
		return ref;
	}
	
	/**
	 * OrderVO to Order
	 * @param order
	 * @return
	 */
	public static Tariff getTariff(TariffVO tariffVO){
		Tariff ref  = new Tariff();
		if(tariffVO == null)
			return null;
		BeanUtils.copyProperties(tariffVO, ref);
		ref.setCharges(tariffVO.getCharges());
		return ref;
	}
	
	public static Map<String,Object> getActiveEselLogis(Map<String,Object> mapActiveEselLogis){
		Map<String,Object> mapActiveEselLogisUser = new HashMap<>();
		List<CompanyVO> refEselList = new ArrayList<>();
		List<CompanyVO> refLogisList = new ArrayList<>();
		List<Company> eseluList = (List<Company>) mapActiveEselLogis.get("esselerUserList");
		List<Company> logisulList = (List<Company>) mapActiveEselLogis.get("logisticUserList");

		for (Company company : eseluList) {
			refEselList.add(getCompany(company));
		}
		
		for (Company company : logisulList) {
			refLogisList.add(getCompany(company));

		}
		mapActiveEselLogisUser.put("activeEsselCount", refEselList.size());
		mapActiveEselLogisUser.put("activeLogisCount", refLogisList.size());
		mapActiveEselLogisUser.put("activeEsselUserList", refEselList);
		mapActiveEselLogisUser.put("activeLogisUserList", refLogisList);
 
	return mapActiveEselLogisUser;	
	}
	
	public static Map<String,Object> getEsselLogiskycStatus(Map<String,Object> mapEsselLogisKycStatus){
		Map<String,Object> mapEsselLogisUserKycStatus = new HashMap<>();
		
		List<CompanyVO> refEsselKycCompletedList = new ArrayList<>();
		List<CompanyVO> refEsselKycIncompletedList = new ArrayList<>();

		List<CompanyVO> refLogisKycCompletedList = new ArrayList<>();
		List<CompanyVO> refLogisKycIncompletedList = new ArrayList<>();

		List<CompanyVO> refLogisList = new ArrayList<>();
		List<Company> masterKycCompleted = (List<Company>) mapEsselLogisKycStatus.get("masterKycCompleted");
		List<Company> masterKycIncompleted = (List<Company>) mapEsselLogisKycStatus.get("masterKycIncompleted");

		List<CompanyVO> refmasterKycCompleted = new ArrayList<>();
		List<CompanyVO> refmasterKycIncompleted = new ArrayList<>();

		for (Company company : masterKycCompleted) {
			refmasterKycCompleted.add(getCompany(company));
		}
		
		for (Company company : masterKycIncompleted) {
			refmasterKycIncompleted.add(getCompany(company));

		}
		
		for (CompanyVO companyVO : refmasterKycIncompleted) {
			if (companyVO.getType() == 1) {
				refEsselKycIncompletedList.add(companyVO);
			}
			else{
				refLogisKycIncompletedList.add(companyVO);
			}
		}
		
		for (CompanyVO companyVO : refmasterKycCompleted) {
			if (companyVO.getType() == 1) {
				refEsselKycCompletedList.add(companyVO);
			}
			else{
				refLogisKycCompletedList.add(companyVO);
			}
		}
		
		mapEsselLogisUserKycStatus.put("esselKycCompletedCount", refEsselKycCompletedList.size());
		mapEsselLogisUserKycStatus.put("esselKycIncompletedCount", refEsselKycIncompletedList.size());
		mapEsselLogisUserKycStatus.put("logisKycCompletedCount", refLogisKycCompletedList.size());
		mapEsselLogisUserKycStatus.put("logisKycIncompletedCount", refLogisKycIncompletedList.size());
 
	return mapEsselLogisUserKycStatus;	
	}
	
	public static List<CompanyVO> filterRegisEselLogisUsers(List<Company> filterUserList)
	{
		List<CompanyVO> filterRegisEselLogisUsers = new ArrayList<CompanyVO>();
		for (Company company : filterUserList) {
			filterRegisEselLogisUsers.add(getCompany(company));
		}
		return filterRegisEselLogisUsers;
	}
	
	public static List<CompanyVO> getEselLogisUserList(List<Company> esselLogisUsers)
	{
		List<CompanyVO> esselLogisUserList = new ArrayList<CompanyVO>();
		for (Company company : esselLogisUsers) {
			esselLogisUserList.add(getCompany(company));
		}
		return esselLogisUserList;
	}
	
	public static List<OrderVO> filterOrdersRegisteredByEsellerUser(List<Order> orderRegistered){
		List<OrderVO> orderRegisteredList = new ArrayList<OrderVO>();
		for (Order order : orderRegistered) {
			orderRegisteredList.add(getOrderVO(order));
		}
		return orderRegisteredList;
	}
	
	public static List<BidVO> filterOrdersByLogisticUser(List<OngoingBid> OrdersByLogisticUsers)
	{
		List<BidVO> OrdersByLogisticList = new ArrayList<BidVO>();
		for (OngoingBid ongoingBid : OrdersByLogisticUsers) {
			OrdersByLogisticList.add(getBidVO(ongoingBid));
		}
		return OrdersByLogisticList;
	}
	
	public static List<BidVO> getTotalBidedOrders(List<OngoingBid> totalBidedOrders)
	{
		List<BidVO> totalBidedOrderList = new ArrayList<BidVO>();
		for (OngoingBid ongoingBid : totalBidedOrders) {
			totalBidedOrderList.add(getBidVO(ongoingBid));
		}
		return totalBidedOrderList;
	}
	
	
	public static Map<String,Object> getEselLogisNameList(Map<String,Object> mapEselLogisNames){
		Map<String,Object> mapEselLogisName = new HashMap<String,Object>();
		
		List<CompanyVO> refEselNameList = new ArrayList<>();
		List<CompanyVO> refLogisNameList = new ArrayList<>();
				
		List<Object[]> eselName = (List<Object[]>) mapEselLogisNames.get("eselNames");
		List<Object[]> logisName = (List<Object[]>) mapEselLogisNames.get("logisNames");
		
		
		
		for (Object[] company : logisName) {
			CompanyVO companyVO = new CompanyVO();
			companyVO.setId((Integer) company[0]);
			companyVO.setName((String) company[1]);
			refLogisNameList.add(companyVO);
		}
		for (Object[] company : eselName) {
			CompanyVO companyVO = new CompanyVO();
			companyVO.setId((Integer) company[0]);
			companyVO.setName((String) company[1]);
			refEselNameList.add(companyVO);
		}
		
		
		
		mapEselLogisName.put("eselNames", refEselNameList);
		mapEselLogisName.put("logisNames", refLogisNameList);
		return mapEselLogisName;
	}
	
	public static Map<String,Object> getUserNameList(Map<String,Object> mapsuerNames){
		Map<String,Object> mapName = new HashMap<String,Object>();
		
		List<CompanyVO> refuseNameList = new ArrayList<>();
				
		List<Object[]> eselName = (List<Object[]>) mapsuerNames.get("usernames");
		
		
		
		
		for (Object[] company : eselName) {
			CompanyVO companyVO = new CompanyVO();
			companyVO.setId((Integer) company[0]);
			companyVO.setName((String) company[1]);
			refuseNameList.add(companyVO);
		}
		
		
		
		mapName.put("usernames", refuseNameList);
		return mapName;
	}
	
	
	public static Map<String,Object> getNotificationMessage(Map<String,Object> mapsuerNames){
		Map<String,Object> mapName = new HashMap<String,Object>();
		
		List<Broad_casting_Message> refuseNameList = new ArrayList<>();
				
		List<Object[]> eselName = (List<Object[]>) mapsuerNames.get("userMessage");
		
		
		
		
		for (Object[] company : eselName) {
			Broad_casting_Message companyVO = new Broad_casting_Message();
			companyVO.setUsername((String)company[0]);
			companyVO.setBmessage((String) company[1]);
			companyVO.setLast_Modified_Time((String) company[2]);
			
			refuseNameList.add(companyVO);
		}
		
		
		
		mapName.put("usernames", refuseNameList);
		return mapName;
	}
	
	public static OrderVO getOrderDetailsByNo(Order order)
	{
			return getOrderVO(order);
	}
	
	public static List<OrderStatusVO> getOrderStatusList(List<com.orderbid.beans.OrderStatus> orderStatus){
		List<OrderStatusVO> orderStatusList = new ArrayList<OrderStatusVO>();
		for(com.orderbid.beans.OrderStatus orderSta: orderStatus)
		{
			orderStatusList.add(getOrderStatus(orderSta));
		}
		return orderStatusList;
	}
	
	
	public static OrderStatusVO getOrderStatus(com.orderbid.beans.OrderStatus orderStaus){
		OrderStatusVO ref = new OrderStatusVO();
		if(orderStaus == null)
		{
			return null;
		}
		
		BeanUtils.copyProperties(orderStaus, ref);
		return ref;
		
	} 
	
	
	
	public static CompanyVO getCompany(Company company){
		CompanyVO ref  = new CompanyVO();
		if(company == null)
			return null;
		BeanUtils.copyProperties(company, ref);
		
		return ref;
	}
	
}
