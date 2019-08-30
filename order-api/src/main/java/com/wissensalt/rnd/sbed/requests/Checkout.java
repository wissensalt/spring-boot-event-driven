package com.wissensalt.rnd.sbed.requests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"aid",
"latitude",
"table_name",
"customer_address",
"server_name",
"total_collected",
"subtotal",
"items",
"note",
"customer_state",
"pg_mid",
"transaction_number",
"transaction_reference",
"longitude",
"total_redeem_amount",
"customer_uniq_id",
"created_by",
"receipt_counter",
"mpos_device_id",
"cc_name",
"transaction_certificate",
"pg_setting",
"customer_sex",
"enable_gratuity",
"collector_id",
"order_info",
"is_offline",
"client_created_at",
"transaction_date",
"transaction_status_info",
"amount_pay",
"include_tax_and_gratuity",
"customer_guid",
"total_gross_sales",
"updated_at",
"customer_id",
"merchant_id",
"customer_city",
"total_net_sales",
"card_no",
"guid",
"uniq_id",
"card_type",
"total_rounding_amount",
"server_title",
"is_loyalty",
"total_gratuity",
"created_at",
"served_by",
"customer_name",
"customer_zip",
"customer_birthday",
"cvm_result",
"is_sales_type",
"payment_type",
"enable_tax",
"creator_name",
"total_discount",
"order_id",
"receipt_number",
"collector_name",
"tvr",
"auth_code",
"total_tax",
"is_refund_transaction",
"pii",
"customer_email",
"customer_phone"
})
public class Checkout {

	@JsonProperty("aid")
	private String aid;
	
	@JsonProperty("latitude")
	private String latitude;
	
	@JsonProperty("table_name")
	private String tableName;
	
	@JsonProperty("customer_address")
	private String customerAddress;
	
	@JsonProperty("server_name")
	private String serverName;
	
	@JsonProperty("total_collected")
	private String totalCollected;
	
	@JsonProperty("subtotal")
	private String subtotal;
	
	@JsonProperty("items")
	private List<Item> items = null;
	
	@JsonProperty("note")
	private String note;
	
	@JsonProperty("customer_state")
	private String customerState;
	
	@JsonProperty("pg_mid")
	private String pgMid;
	
	@JsonProperty("transaction_number")
	private String transactionNumber;
	
	@JsonProperty("transaction_reference")
	private String transactionReference;
	
	@JsonProperty("longitude")
	private String longitude;
	
	@JsonProperty("total_redeem_amount")
	private String totalRedeemAmount;
	
	@JsonProperty("customer_uniq_id")
	private Integer customerUniqId;
	
	@JsonProperty("created_by")
	private Integer createdBy;
	
	@JsonProperty("receipt_counter")
	private Integer receiptCounter;
	
	@JsonProperty("mpos_device_id")
	private String mposDeviceId;
	
	@JsonProperty("cc_name")
	private String ccName;
	
	@JsonProperty("transaction_certificate")
	private String transactionCertificate;
	
	@JsonProperty("pg_setting")
	private String pgSetting;
	
	@JsonProperty("customer_sex")
	private String customerSex;
	
	@JsonProperty("enable_gratuity")
	private Boolean enableGratuity;
	
	@JsonProperty("collector_id")
	private Integer collectorId;
	
	@JsonProperty("order_info")
	private String orderInfo;
	
	@JsonProperty("is_offline")
	private Boolean isOffline;
	
	@JsonProperty("client_created_at")
	private String clientCreatedAt;
	
	@JsonProperty("transaction_date")
	private String transactionDate;
	
	@JsonProperty("transaction_status_info")
	private String transactionStatusInfo;
	
	@JsonProperty("amount_pay")
	private String amountPay;
	
	@JsonProperty("include_tax_and_gratuity")
	private Boolean includeTaxAndGratuity;
	
	@JsonProperty("customer_guid")
	private String customerGuid;
	
	@JsonProperty("total_gross_sales")
	private String totalGrossSales;
	
	@JsonProperty("updated_at")
	private String updatedAt;
	
	@JsonProperty("customer_id")
	private Integer customerId;
	
	@JsonProperty("merchant_id")
	private String merchantId;
	
	@JsonProperty("customer_city")
	private String customerCity;
	
	@JsonProperty("total_net_sales")
	private String totalNetSales;
	
	@JsonProperty("card_no")
	private String cardNo;
	
	@JsonProperty("guid")
	private String guid;
	
	@JsonProperty("uniq_id")
	private Integer uniqId;
	
	@JsonProperty("card_type")
	private String cardType;
	
	@JsonProperty("total_rounding_amount")
	private String totalRoundingAmount;
	
	@JsonProperty("server_title")
	private String serverTitle;
	
	@JsonProperty("is_loyalty")
	private Boolean isLoyalty;
	
	@JsonProperty("total_gratuity")
	private String totalGratuity;
	
	@JsonProperty("created_at")
	private String createdAt;
	
	@JsonProperty("served_by")
	private Integer servedBy;
	
	@JsonProperty("customer_name")
	private String customerName;
	
	@JsonProperty("customer_zip")
	private String customerZip;
	
	@JsonProperty("customer_birthday")
	private String customerBirthday;
	
	@JsonProperty("cvm_result")
	private String cvmResult;
	
	@JsonProperty("is_sales_type")
	private Boolean isSalesType;
	
	@JsonProperty("payment_type")
	private String paymentType;
	
	@JsonProperty("enable_tax")
	private Boolean enableTax;
	
	@JsonProperty("creator_name")
	private String creatorName;
	
	@JsonProperty("total_discount")
	private String totalDiscount;
	
	@JsonProperty("order_id")
	private String orderId;
	
	@JsonProperty("receipt_number")
	private String receiptNumber;
	
	@JsonProperty("collector_name")
	private String collectorName;
	
	@JsonProperty("tvr")
	private String tvr;
	
	@JsonProperty("auth_code")
	private String authCode;
	
	@JsonProperty("total_tax")
	private String totalTax;
	
	@JsonProperty("is_refund_transaction")
	private Boolean isRefundTransaction;
	
	@JsonProperty("pii")
	private String pii;
	
	@JsonProperty("customer_email")
	private String customerEmail;
	
	@JsonProperty("customer_phone")
	private String customerPhone;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("aid")
	public String getAid() {
		return aid;
	}
	
	@JsonProperty("aid")
	public void setAid(String aid) {
		this.aid = aid;
	}
	
	@JsonProperty("latitude")
	public String getLatitude() {
		return latitude;
	}
	
	@JsonProperty("latitude")
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@JsonProperty("table_name")
	public String getTableName() {
		return tableName;
	}
	
	@JsonProperty("table_name")
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@JsonProperty("customer_address")
	public String getCustomerAddress() {
		return customerAddress;
	}
	
	@JsonProperty("customer_address")
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	@JsonProperty("server_name")
	public String getServerName() {
		return serverName;
	}
	
	@JsonProperty("server_name")
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	@JsonProperty("total_collected")
	public String getTotalCollected() {
		return totalCollected;
	}
	
	@JsonProperty("total_collected")
	public void setTotalCollected(String totalCollected) {
		this.totalCollected = totalCollected;
	}

	@JsonProperty("subtotal")
	public String getSubtotal() {
		return subtotal;
	}
	
	@JsonProperty("subtotal")
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	
	@JsonProperty("items")
	public List<Item> getItems() {
		return items;
	}
	
	@JsonProperty("items")
	public void setItems(List<Item> items) {
		this.items = items;
	}

	@JsonProperty("note")
	public String getNote() {
		return note;
	}
	
	@JsonProperty("note")
	public void setNote(String note) {
		this.note = note;
	}
	
	@JsonProperty("customer_state")
	public String getCustomerState() {
		return customerState;
	}
	
	@JsonProperty("customer_state")
	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	@JsonProperty("pg_mid")
	public String getPgMid() {
		return pgMid;
	}
	
	@JsonProperty("pg_mid")
	public void setPgMid(String pgMid) {
		this.pgMid = pgMid;
	}
	
	@JsonProperty("transaction_number")
	public String getTransactionNumber() {
		return transactionNumber;
	}

	@JsonProperty("transaction_number")
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	
	@JsonProperty("transaction_reference")
	public String getTransactionReference() {
		return transactionReference;
	}
	
	@JsonProperty("transaction_reference")
	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	@JsonProperty("longitude")
	public String getLongitude() {
		return longitude;
	}
	
	@JsonProperty("longitude")
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@JsonProperty("total_redeem_amount")
	public String getTotalRedeemAmount() {
		return totalRedeemAmount;
	}
	
	@JsonProperty("total_redeem_amount")
	public void setTotalRedeemAmount(String totalRedeemAmount) {
		this.totalRedeemAmount = totalRedeemAmount;
	}

	@JsonProperty("customer_uniq_id")
	public Integer getCustomerUniqId() {
		return customerUniqId;
	}
	
	@JsonProperty("customer_uniq_id")
	public void setCustomerUniqId(Integer customerUniqId) {
		this.customerUniqId = customerUniqId;
	}
	
	@JsonProperty("created_by")
	public Integer getCreatedBy() {
		return createdBy;
	}

	@JsonProperty("created_by")
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	
	@JsonProperty("receipt_counter")
	public Integer getReceiptCounter() {
		return receiptCounter;
	}
	
	@JsonProperty("receipt_counter")
	public void setReceiptCounter(Integer receiptCounter) {
		this.receiptCounter = receiptCounter;
	}
	
	@JsonProperty("mpos_device_id")
	public String getMposDeviceId() {
		return mposDeviceId;
	}

	@JsonProperty("mpos_device_id")
	public void setMposDeviceId(String mposDeviceId) {
		this.mposDeviceId = mposDeviceId;
	}
	
	@JsonProperty("cc_name")
	public String getCcName() {
		return ccName;
	}
	
	@JsonProperty("cc_name")
	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	@JsonProperty("transaction_certificate")
	public String getTransactionCertificate() {
		return transactionCertificate;
	}
	
	@JsonProperty("transaction_certificate")
	public void setTransactionCertificate(String transactionCertificate) {
		this.transactionCertificate = transactionCertificate;
	}
	
	@JsonProperty("pg_setting")
	public String getPgSetting() {
		return pgSetting;
	}

	@JsonProperty("pg_setting")
	public void setPgSetting(String pgSetting) {
		this.pgSetting = pgSetting;
	}
	
	@JsonProperty("customer_sex")
	public String getCustomerSex() {
		return customerSex;
	}
	
	@JsonProperty("customer_sex")
	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}
	
	@JsonProperty("enable_gratuity")
	public Boolean getEnableGratuity() {
		return enableGratuity;
	}

	@JsonProperty("enable_gratuity")
	public void setEnableGratuity(Boolean enableGratuity) {
		this.enableGratuity = enableGratuity;
	}
	
	@JsonProperty("collector_id")
	public Integer getCollectorId() {
		return collectorId;
	}
	
	@JsonProperty("collector_id")
	public void setCollectorId(Integer collectorId) {
		this.collectorId = collectorId;
	}

	@JsonProperty("order_info")
	public String getOrderInfo() {
		return orderInfo;
	}
	
	@JsonProperty("order_info")
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}
	
	@JsonProperty("is_offline")
	public Boolean getIsOffline() {
		return isOffline;
	}

	@JsonProperty("is_offline")
	public void setIsOffline(Boolean isOffline) {
		this.isOffline = isOffline;
	}
	
	@JsonProperty("client_created_at")
	public String getClientCreatedAt() {
		return clientCreatedAt;
	}
	
	@JsonProperty("client_created_at")
	public void setClientCreatedAt(String clientCreatedAt) {
		this.clientCreatedAt = clientCreatedAt;
	}

	@JsonProperty("transaction_date")
	public String getTransactionDate() {
		return transactionDate;
	}
	
	@JsonProperty("transaction_date")
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	@JsonProperty("transaction_status_info")
	public String getTransactionStatusInfo() {
		return transactionStatusInfo;
	}

	@JsonProperty("transaction_status_info")
	public void setTransactionStatusInfo(String transactionStatusInfo) {
		this.transactionStatusInfo = transactionStatusInfo;
	}
	
	@JsonProperty("amount_pay")
	public String getAmountPay() {
		return amountPay;
	}
	
	@JsonProperty("amount_pay")
	public void setAmountPay(String amountPay) {
		this.amountPay = amountPay;
	}

	@JsonProperty("include_tax_and_gratuity")
	public Boolean getIncludeTaxAndGratuity() {
		return includeTaxAndGratuity;
	}
	
	@JsonProperty("include_tax_and_gratuity")
	public void setIncludeTaxAndGratuity(Boolean includeTaxAndGratuity) {
		this.includeTaxAndGratuity = includeTaxAndGratuity;
	}
	
	@JsonProperty("customer_guid")
	public String getCustomerGuid() {
		return customerGuid;
	}

	@JsonProperty("customer_guid")
	public void setCustomerGuid(String customerGuid) {
		this.customerGuid = customerGuid;
	}
	
	@JsonProperty("total_gross_sales")
	public String getTotalGrossSales() {
		return totalGrossSales;
	}
	
	@JsonProperty("total_gross_sales")
	public void setTotalGrossSales(String totalGrossSales) {
	this.totalGrossSales = totalGrossSales;
	}

	@JsonProperty("updated_at")
	public String getUpdatedAt() {
		return updatedAt;
	}
	
	@JsonProperty("updated_at")
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@JsonProperty("customer_id")
	public Integer getCustomerId() {
		return customerId;
	}

	@JsonProperty("customer_id")
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	@JsonProperty("merchant_id")
	public String getMerchantId() {
		return merchantId;
	}
	
	@JsonProperty("merchant_id")
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	@JsonProperty("customer_city")
	public String getCustomerCity() {
		return customerCity;
	}

	@JsonProperty("customer_city")
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}
	
	@JsonProperty("total_net_sales")
	public String getTotalNetSales() {
		return totalNetSales;
	}
	
	@JsonProperty("total_net_sales")
	public void setTotalNetSales(String totalNetSales) {
		this.totalNetSales = totalNetSales;
	}
	
	@JsonProperty("card_no")
	public String getCardNo() {
		return cardNo;
	}

	@JsonProperty("card_no")
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	@JsonProperty("guid")
	public String getGuid() {
		return guid;
	}
	
	@JsonProperty("guid")
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	@JsonProperty("uniq_id")
	public Integer getUniqId() {
		return uniqId;
	}

	@JsonProperty("uniq_id")
	public void setUniqId(Integer uniqId) {
		this.uniqId = uniqId;
	}
	
	@JsonProperty("card_type")
	public String getCardType() {
		return cardType;
	}
	
	@JsonProperty("card_type")
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	@JsonProperty("total_rounding_amount")
	public String getTotalRoundingAmount() {
		return totalRoundingAmount;
	}

	@JsonProperty("total_rounding_amount")
	public void setTotalRoundingAmount(String totalRoundingAmount) {
		this.totalRoundingAmount = totalRoundingAmount;
	}
	
	@JsonProperty("server_title")
	public String getServerTitle() {
		return serverTitle;
	}
	
	@JsonProperty("server_title")
	public void setServerTitle(String serverTitle) {
		this.serverTitle = serverTitle;
	}
	
	@JsonProperty("is_loyalty")
	public Boolean getIsLoyalty() {
		return isLoyalty;
	}

	@JsonProperty("is_loyalty")
	public void setIsLoyalty(Boolean isLoyalty) {
		this.isLoyalty = isLoyalty;
	}
	
	@JsonProperty("total_gratuity")
	public String getTotalGratuity() {
		return totalGratuity;
	}
	
	@JsonProperty("total_gratuity")
	public void setTotalGratuity(String totalGratuity) {
		this.totalGratuity = totalGratuity;
	}
	
	@JsonProperty("created_at")
	public String getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("created_at")
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	@JsonProperty("served_by")
	public Integer getServedBy() {
		return servedBy;
	}
	
	@JsonProperty("served_by")
	public void setServedBy(Integer servedBy) {
		this.servedBy = servedBy;
	}
	
	@JsonProperty("customer_name")
	public String getCustomerName() {
		return customerName;
	}

	@JsonProperty("customer_name")
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@JsonProperty("customer_zip")
	public String getCustomerZip() {
		return customerZip;
	}
	
	@JsonProperty("customer_zip")
	public void setCustomerZip(String customerZip) {
		this.customerZip = customerZip;
	}
	
	@JsonProperty("customer_birthday")
	public String getCustomerBirthday() {
		return customerBirthday;
	}

	@JsonProperty("customer_birthday")
	public void setCustomerBirthday(String customerBirthday) {
		this.customerBirthday = customerBirthday;
	}
	
	@JsonProperty("cvm_result")
	public String getCvmResult() {
		return cvmResult;
	}
	
	@JsonProperty("cvm_result")
	public void setCvmResult(String cvmResult) {
		this.cvmResult = cvmResult;
	}
	
	@JsonProperty("is_sales_type")
	public Boolean getIsSalesType() {
		return isSalesType;
	}

	@JsonProperty("is_sales_type")
	public void setIsSalesType(Boolean isSalesType) {
		this.isSalesType = isSalesType;
	}
	
	@JsonProperty("payment_type")
	public String getPaymentType() {
		return paymentType;
	}
	
	@JsonProperty("payment_type")
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	@JsonProperty("enable_tax")
	public Boolean getEnableTax() {
		return enableTax;
	}

	@JsonProperty("enable_tax")
	public void setEnableTax(Boolean enableTax) {
		this.enableTax = enableTax;
	}
	
	@JsonProperty("creator_name")
	public String getCreatorName() {
		return creatorName;
	}
	
	@JsonProperty("creator_name")
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	@JsonProperty("total_discount")
	public String getTotalDiscount() {
		return totalDiscount;
	}

	@JsonProperty("total_discount")
	public void setTotalDiscount(String totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	
	@JsonProperty("order_id")
	public String getOrderId() {
		return orderId;
	}
	
	@JsonProperty("order_id")
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@JsonProperty("receipt_number")
	public String getReceiptNumber() {
		return receiptNumber;
	}
	
	@JsonProperty("receipt_number")
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	
	@JsonProperty("collector_name")
	public String getCollectorName() {
		return collectorName;
	}

	@JsonProperty("collector_name")
	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}
	
	@JsonProperty("tvr")
	public String getTvr() {
		return tvr;
	}
	
	@JsonProperty("tvr")
	public void setTvr(String tvr) {
		this.tvr = tvr;
	}

	@JsonProperty("auth_code")
	public String getAuthCode() {
		return authCode;
	}
	
	@JsonProperty("auth_code")
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	@JsonProperty("total_tax")
	public String getTotalTax() {
		return totalTax;
	}
	
	@JsonProperty("total_tax")
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

	@JsonProperty("is_refund_transaction")
	public Boolean getIsRefundTransaction() {
		return isRefundTransaction;
	}
	
	@JsonProperty("is_refund_transaction")
	public void setIsRefundTransaction(Boolean isRefundTransaction) {
		this.isRefundTransaction = isRefundTransaction;
	}
	
	@JsonProperty("pii")
	public String getPii() {
		return pii;
	}
	
	@JsonProperty("pii")
	public void setPii(String pii) {
		this.pii = pii;
	}
	
	@JsonProperty("customer_email")
	public String getCustomerEmail() {
		return customerEmail;
	}
	
	@JsonProperty("customer_email")
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	@JsonProperty("customer_phone")
	public String getCustomerPhone() {
		return customerPhone;
	}
	
	@JsonProperty("customer_phone")
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}









