package com.orderbid.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.orderbid.beans.Company;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.OrderFilter;
import com.orderbid.beans.OrderGrid;
import com.orderbid.beans.OrderWrapper;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.OrderStatusVO;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.beans.vo.OrderWatchVO;
import com.orderbid.jobs.ScheduledExcuteWnningService;
import com.orderbid.service.BidService;
import com.orderbid.service.OrderService;
import com.orderbid.util.AppUtil;
import com.orderbid.util.ApplicationConstants;
import com.orderbid.util.BidStatus;
import com.orderbid.util.DateUtil;
import com.orderbid.util.GridRangeData;
import com.orderbid.util.OrderPriority;
import com.orderbid.util.OrderStatus;

@Controller
public class OrderController {

	Logger pnrCommonLogger = Logger.getLogger("pnrCommonLoger");

	@Autowired
	private OrderService orderService;

	@Autowired
	private BidService bidService;

	// Not in use it will work when we receiving object through @ModelAttribute.
	// Will not work for @RequestBody
	@InitBinder("order")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * This method applicable for Logistics and E-seller both Logistics :
	 * display all E-seller orders E-Seller : Display only own orders
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "totalOrders")
	@ResponseBody
	public String getTotalOrders(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0) {
			List<OrderVO> orders = orderService.getTotalUpalodedOrders(user);
			bidService.getOrderVOWithMaxBids(orders, user);
			return AppUtil.getJsonString(orders);
		}
		return null;
	}

	/**
	 * This method applicable for Logistics and E-seller both Logistics :
	 * display all E-seller orders E-Seller : Display only own orders
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "newOrders")
	@ResponseBody
	public String getNewOrders(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0) {
			List<OrderVO> orders = null;
			if (user.getCompany().getType() == 1) {
				orders = orderService.getNewlyAddedOrders(user);
			} else {
				orders = orderService.getRecentlyAddedOrders(user);
			}
			bidService.getOrderVOWithMaxBids(orders, user);
			return AppUtil.getJsonString(orders);
		}
		return null;
	}

	@RequestMapping(value = "getOrderDetailsByNo",method = RequestMethod.POST, produces =MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OrderVO getOrderDetailsByNo(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody Order order) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
	//	Company company = user.getCompany();

		//System.err.println("========getOrderDetailsByNo======="+order.getOrderNo());
		//System.err.println("========getOrderDetailsByNo======="+order.getOrderNo());
		/*System.err.println("========getOrderDetailsByNo======="+order.getOrderNo());
		System.err.println("========getOrderDetailsByNo======="+order.getOrderNo());
		*/
		if (user != null && user.getId() > 0) {
		/*	System.err.println("=====inside the user call==="+orderService.getOrderDetailsByNo(order.getOrderNo()));
			System.err.println("=====inside the user call==="+orderService.getOrderDetailsByNo(order.getOrderNo()));
			System.err.println("=====inside the user call==="+orderService.getOrderDetailsByNo(order.getOrderNo()));
			System.err.println("=====inside the user call==="+orderService.getOrderDetailsByNo(order.getOrderNo()));
			
			
		*/	
			
				return orderService.getOrderDetailsByNo(order.getOrderNo());
			
		}
		return null;
	}

	@RequestMapping(value = "getAuditOrderDetails")
	@ResponseBody
	public OrderVO getAuditOrderDetails(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestParam(value = "orderNo") String orderNo) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);

		if (user != null && user.getId() > 0) {
			
			return orderService.getOrderDetailsByNo(orderNo);
			
		}
		return null;
	}
	
	/**
	 * This method applicable for Logistics.
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "autoQualifiedOrders")
	@ResponseBody
	public String getAutoqualifiedOrders(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0) {
			List<OrderVO> orders = orderService.getTotalUpalodedOrders(user);
			bidService.getOrderVOWithMaxBids(orders, user);
			return AppUtil.getJsonString(orders);
		}
		return null;
	}

	/**
	 * This method applicable for ESeller.
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "expiredOrders")
	@ResponseBody
	public String getExpiredOrders(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0) {
			List<OrderVO> orders = orderService.getExpriedOrders(user);
			return AppUtil.getJsonString(orders);
		}
		return null;
	}

	/**
	 * This method applicable for ESeller.
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "closedOrders/{itemsPerPage}/{pageNO}")
	@ResponseBody
	public String getClosedOrders(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@PathVariable int itemsPerPage, @PathVariable int pageNO) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0) {
			GridRangeData range = AppUtil.getGridRangeData(itemsPerPage, pageNO);
			List<OrderVO> orders = orderService.getClosedOrders(user, range);
			orders = bidService.getOrderVOWithMaxBids(orders, user);
			orders = bidService.getOrderVOWithWinnerBids(orders, user);
			orders = bidService.getOrderWinningCompany(orders, user);
			return AppUtil.getJsonString(orders);
		}
		return null;
	}

	@RequestMapping(value = "addOrder", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String addOrder(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody OrderWrapper orderwrapper) {
		// System.out.println("shipmentDate::"+shipmentDate);
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0 && orderwrapper != null && orderwrapper.getOrder() != null) {
			Order order = orderwrapper.getOrder();
			order.setCompany(user.getCompany());
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
			order.setLastModifiedDate(currentTimestamp);
			order.setOrderDate(currentTimestamp);
			order.setCreateBy(user.getId());
			order.setUpdateBy(user.getId());
			order.setStatus(OrderStatus.DRAFT.toString());
			order.setPriority(OrderPriority.MEDIUM.toString());
			order.setUuid(UUID.randomUUID().toString());
			order.setShipmentDate(DateUtil.getStringToDate(orderwrapper.getShipmentDate()));
			order.setDeliveryDate(DateUtil.getStringToDate(orderwrapper.getDeliveryDate()));
			int orderID = orderService.createOrder(order, user);
			if (orderID > 0) {
				// send Email for confimation and notification to all logistics
			}
		}
		return null;
	}

	@RequestMapping(value = "addBid")
	@ResponseBody
	public String addBid(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody OngoingBid bid) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0) {
			Order order = orderService.getOrder(bid.getOrderNo());
			bid.setOrderNo(bid.getOrderNo());
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
			bid.setBidTime(currentTimestamp);
			bid.setBidderId(user.getId().toString());
			bid.setBidStatus(BidStatus.ONGOING.getStatus());
			bidService.placeOrderBid(order, bid);
			// Winning Bid Thread
			ScheduledExcuteWnningService.scheduleWinnigBidThread(order.getOrderNo());
			// send Email for confimation and notification to all logistics
		}
		return null;
	}

	@RequestMapping(value = "addWatch")
	@ResponseBody
	public String addWatch(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody int[] selectedOrders) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0) {
			orderService.addWatch(selectedOrders, user);
			return AppUtil.getJsonString(user);
		}
		return null;
	}

	@RequestMapping(value = "watches/{itemCount}/{pageNO}")
	@ResponseBody
	public String getWatches(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@PathVariable int itemCount, @PathVariable int pageNO) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0) {
			GridRangeData range = AppUtil.getGridRangeData(itemCount, pageNO);
			List<OrderWatchVO> orders = orderService.getWatches(user, range);
			List<OrderWatchVO> watchedOrders = new ArrayList<OrderWatchVO>();
			Iterator<OrderWatchVO> itr = orders.iterator();
			while (itr.hasNext()) {
				OrderWatchVO vo = itr.next();
				String orderNo = vo.getOrderNo();
				Order order = orderService.getOrderById(orderNo);
				order.getCompany().setUsers(null);
				vo.setOrder(order);
				watchedOrders.add(vo);
			}
			return AppUtil.getJsonString(watchedOrders);
		}
		return null;
	}

	@RequestMapping(value = "filter")
	@ResponseBody
	public String geTfilter(HttpServletRequest request, HttpServletResponse response, ModelMap map,
			@RequestBody OrderFilter orderFilter) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0) {
			List<OrderVO> orders = new ArrayList<OrderVO>();
			GridRangeData data = new GridRangeData(0, 10);
			data.setFilter(orderFilter);
			if (user.getCompany().getName().equalsIgnoreCase("admin")) {
				orders = orderService.getRecentlyAddedOrders(data, user);
			} else if (user.getCompany().getType() == 1) {
				orders = orderService.getRecentlyAddedOrders(data, user);
			} else if (user.getCompany().getType() == 2) {
				orders = orderService.getAutoQulifyOrders(user, data);
			}
			OrderGrid grid = new OrderGrid();
			grid.setOrders(orders);
			return AppUtil.getJsonString(grid);
		}
		return null;
	}

	@RequestMapping(value = "getorderstatuslist")
	@ResponseBody
	public List<OrderStatusVO> getOrderStatusList(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0) {
			return orderService.getOrderStatusList();
		}
		return null;
	}

	@RequestMapping(value = "updateOrderStatus")
	@ResponseBody
	public Boolean updateOrderStatus(@RequestParam(value = "orderNo") String orderNo,
			@RequestParam(value = "orderStatus") Integer orderStatus, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ApplicationConstants.SESSION_USER);
		if (user != null && user.getId() > 0) {
			return orderService.updateOrderStatus(orderNo, orderStatus);
		}
		return null;
	}

}
