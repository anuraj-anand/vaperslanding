package com.orderbid.beans.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class OrderVO  {
	private int srNo;
	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	private Integer id;
	private String uuid;
	private String additionalFields;
	private Date shipmentDate;
	private Date deliveryDate;
	private Integer destPin;
	private String destAddress;
	private Timestamp lastModifiedDate;
	private Timestamp ordeBidExpiryTime;
	private Timestamp orderDate;
	private String orderNo;
	private String priority;
	private Integer askQuantity;
	private String sourceAddress;
	private String status;
	private Integer sourcePin;
	private Integer updateBy;
	private Integer createBy;
	private Long weight;
	private BigDecimal askRate;
	private Integer deliveryDays;
	private String itemSymbol;
	private Integer rowCount;
	private Integer orderStatus;
	
	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	
	public String getItemSymbol() {
		return itemSymbol;
	}

	public void setItemSymbol(String itemSymbol) {
		this.itemSymbol = itemSymbol;
	}
	private BidVO maxBidVO;
	private BidVO maxUserBidVO;
	private BidVO winnerBidVO;
	private CompanyVO winnerCompanyVO;
	
	public CompanyVO getWinnerCompanyVO() {
		return winnerCompanyVO;
	}

	public void setWinnerCompanyVO(CompanyVO winnerCompanyVO) {
		this.winnerCompanyVO = winnerCompanyVO;
	}

	public BidVO getWinnerBidVO() {
		return winnerBidVO;
	}

	public void setWinnerBidVO(BidVO winnerBidVO) {
		this.winnerBidVO = winnerBidVO;
	}
	private CompanyVO comapnyVo;

	
	public OrderVO() {
	}
	
	public OrderVO(Integer id, String uuid, String additionalFields, Date shipmentDate, Date deliveryDate,
			Integer destPin, String destAddress, Timestamp lastModifiedDate, Timestamp ordeBidExpiryTime,
			Timestamp orderDate, String orderNo, String priority, Integer askQuantity, String sourceAddress,
			String status, Integer sourcePin, Integer updateBy, Integer createBy, Long weight, BigDecimal askRate) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.additionalFields = additionalFields;
		this.shipmentDate = shipmentDate;
		this.deliveryDate = deliveryDate;
		this.destPin = destPin;
		this.destAddress = destAddress;
		this.lastModifiedDate = lastModifiedDate;
		this.ordeBidExpiryTime = ordeBidExpiryTime;
		this.orderDate = orderDate;
		this.orderNo = orderNo;
		this.priority = priority;
		this.askQuantity = askQuantity;
		this.sourceAddress = sourceAddress;
		this.status = status;
		this.sourcePin = sourcePin;
		this.updateBy = updateBy;
		this.createBy = createBy;
		this.weight = weight;
		this.askRate = askRate;
	}
	
	public BidVO getMaxBidVO() {
		return maxBidVO;
	}

	public void setMaxBidVO(BidVO maxBidVO) {
		this.maxBidVO = maxBidVO;
	}

	public BidVO getMaxUserBidVO() {
		return maxUserBidVO;
	}

	public void setMaxUserBidVO(BidVO maxUserBidVO) {
		this.maxUserBidVO = maxUserBidVO;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getDeliveryDays() {
		return deliveryDays;
	}

	public void setDeliveryDays(Integer deliveryDays) {
		this.deliveryDays = deliveryDays;
	}
	
	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public Timestamp getOrdeBidExpiryTime() {
		return ordeBidExpiryTime;
	}

	public void setOrdeBidExpiryTime(Timestamp ordeBidExpiryTime) {
		this.ordeBidExpiryTime = ordeBidExpiryTime;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getAdditionalFields() {
		return this.additionalFields;
	}

	public void setAdditionalFields(String additionalFields) {
		this.additionalFields = additionalFields;
	}

	public Integer getDestPin() {
		return destPin;
	}

	public void setDestPin(Integer destPin) {
		this.destPin = destPin;
	}

	public String getDestAddress() {
		return destAddress;
	}

	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public Integer getSourcePin() {
		return sourcePin;
	}

	public void setSourcePin(Integer sourcePin) {
		this.sourcePin = sourcePin;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public CompanyVO getComapnyVo() {
		return comapnyVo;
	}

	public void setComapnyVo(CompanyVO comapnyVo) {
		this.comapnyVo = comapnyVo;
	}

	public Long getWeight() {
		return weight;
	}
	public void setWeight(Long weight) {
		this.weight = weight;
	}
	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public Integer getAskQuantity() {
		return askQuantity;
	}

	public void setAskQuantity(Integer askQuantity) {
		this.askQuantity = askQuantity;
	}

	public BigDecimal getAskRate() {
		return askRate;
	}

	public void setAskRate(BigDecimal askRate) {
		this.askRate = askRate;
	}
	@Override
	public String toString() {
		return "OrderVO [id=" + id + ", uuid=" + uuid + ", additionalFields=" + additionalFields + ", shipmentDate="
				+ shipmentDate + ", deliveryDate=" + deliveryDate + ", destPin=" + destPin + ", destAddress="
				+ destAddress + ", lastModifiedDate=" + lastModifiedDate + ", ordeBidExpiryTime=" + ordeBidExpiryTime
				+ ", orderDate=" + orderDate + ", orderNo=" + orderNo + ", priority=" + priority + ", askQuantity="
				+ askQuantity + ", sourceAddress=" + sourceAddress + ", status=" + status + ", sourcePin=" + sourcePin
				+ ", updateBy=" + updateBy + ", createBy=" + createBy + ", weight=" + weight + ", askRate=" + askRate
				+ ", maxBidVO=" + maxBidVO + ", maxUserBidVO=" + maxUserBidVO + ", comapnyVo=" + comapnyVo + "]";
	}


	
}
