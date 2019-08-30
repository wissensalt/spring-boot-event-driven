package com.wissensalt.rnd.sbed.requests;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"item_name",
	"guid",
	"position",
	"tax",
	"category_name",
	"client_price",
	"discount_amount",
	"updated_at",
	"updated_by",
	"item_variant_sku",
	"order_status",
	"sales_type_id",
	"net_sales",
	"quantity",
	"gratuity",
	"redeem_amount",
	"sales_type_name",
	"item_variant_name",
	"item_image",
	"note",
	"is_program_item",
	"item_id",
	"item_variant_id",
	"track_stock",
	"gross_sales",
	"created_at",
	"item_background_color",
	"category_id"
})
public class Item {

	@JsonProperty("item_name")
	private String itemName;
	
	@JsonProperty("guid")
	private String guid;
	
	@JsonProperty("position")
	private Integer position;
	
	@JsonProperty("tax")
	private String tax;
	
	@JsonProperty("category_name")
	private String categoryName;
	
	@JsonProperty("client_price")
	private String clientPrice;
	
	@JsonProperty("discount_amount")
	private String discountAmount;
	
	@JsonProperty("updated_at")
	private String updatedAt;
	
	@JsonProperty("updated_by")
	private String updatedBy;
	
	@JsonProperty("item_variant_sku")
	private String itemVariantSku;
	
	@JsonProperty("order_status")
	private String orderStatus;
	
	@JsonProperty("sales_type_id")
	private Integer salesTypeId;
	
	@JsonProperty("net_sales")
	private String netSales;
	
	@JsonProperty("quantity")
	private String quantity;
	
	@JsonProperty("gratuity")
	private String gratuity;
	
	@JsonProperty("redeem_amount")
	private String redeemAmount;
	
	@JsonProperty("sales_type_name")
	private String salesTypeName;
	
	@JsonProperty("item_variant_name")
	private String itemVariantName;
	
	@JsonProperty("item_image")
	private String itemImage;
	
	@JsonProperty("note")
	private String note;
	
	@JsonProperty("is_program_item")
	private Boolean isProgramItem;
	
	@JsonProperty("item_id")
	private Integer itemId;
	
	@JsonProperty("item_variant_id")
	private Integer itemVariantId;
	
	@JsonProperty("track_stock")
	private Boolean trackStock;
	
	@JsonProperty("gross_sales")
	private String grossSales;
	
	@JsonProperty("created_at")
	private String createdAt;
	
	@JsonProperty("item_background_color")
	private String itemBackgroundColor;
	
	@JsonProperty("category_id")
	private Integer categoryId;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("item_name")
	public String getItemName() {
		return itemName;
	}

	@JsonProperty("item_name")
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@JsonProperty("guid")
	public String getGuid() {
		return guid;
	}

	@JsonProperty("guid")
	public void setGuid(String guid) {
		this.guid = guid;
	}

	@JsonProperty("position")
	public Integer getPosition() {
		return position;
	}

	@JsonProperty("position")
	public void setPosition(Integer position) {
		this.position = position;
	}

	@JsonProperty("tax")
	public String getTax() {
		return tax;
	}

	@JsonProperty("tax")
	public void setTax(String tax) {
		this.tax = tax;
	}

	@JsonProperty("category_name")
	public String getCategoryName() {
		return categoryName;
	}

	@JsonProperty("category_name")
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@JsonProperty("client_price")
	public String getClientPrice() {
		return clientPrice;
	}

	@JsonProperty("client_price")
	public void setClientPrice(String clientPrice) {
		this.clientPrice = clientPrice;
	}

	@JsonProperty("discount_amount")
	public String getDiscountAmount() {
		return discountAmount;
	}

	@JsonProperty("discount_amount")
	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	@JsonProperty("updated_at")
	public String getUpdatedAt() {
		return updatedAt;
	}

	@JsonProperty("updated_at")
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@JsonProperty("updated_by")
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	@JsonProperty("updated_by")
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@JsonProperty("item_variant_sku")
	public String getItemVariantSku() {
		return itemVariantSku;
	}
	
	@JsonProperty("item_variant_sku")
	public void setItemVariantSku(String itemVariantSku) {
		this.itemVariantSku = itemVariantSku;
	}
	
	@JsonProperty("order_status")
	public String getOrderStatus() {
		return orderStatus;
	}

	@JsonProperty("order_status")
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@JsonProperty("sales_type_id")
	public Integer getSalesTypeId() {
		return salesTypeId;
	}
	
	@JsonProperty("sales_type_id")
	public void setSalesTypeId(Integer salesTypeId) {
		this.salesTypeId = salesTypeId;
	}

	@JsonProperty("net_sales")
	public String getNetSales() {
		return netSales;
	}
	
	@JsonProperty("net_sales")
	public void setNetSales(String netSales) {
		this.netSales = netSales;
	}
	
	@JsonProperty("quantity")
	public String getQuantity() {
		return quantity;
	}
	
	@JsonProperty("quantity")
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@JsonProperty("gratuity")
	public String getGratuity() {
		return gratuity;
	}
	
	@JsonProperty("gratuity")
	public void setGratuity(String gratuity) {
		this.gratuity = gratuity;
	}
	
	@JsonProperty("redeem_amount")
	public String getRedeemAmount() {
		return redeemAmount;
	}

	@JsonProperty("redeem_amount")
	public void setRedeemAmount(String redeemAmount) {
		this.redeemAmount = redeemAmount;
	}
	
	@JsonProperty("sales_type_name")
	public String getSalesTypeName() {
		return salesTypeName;
	}
	
	@JsonProperty("sales_type_name")
	public void setSalesTypeName(String salesTypeName) {
		this.salesTypeName = salesTypeName;
	}

	@JsonProperty("item_variant_name")
	public String getItemVariantName() {
		return itemVariantName;
	}
	
	@JsonProperty("item_variant_name")
	public void setItemVariantName(String itemVariantName) {
		this.itemVariantName = itemVariantName;
	}
	
	@JsonProperty("item_image")
	public String getItemImage() {
		return itemImage;
	}

	@JsonProperty("item_image")
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	
	@JsonProperty("note")
	public String getNote() {
		return note;
	}
	
	@JsonProperty("note")
	public void setNote(String note) {
		this.note = note;
	}

	@JsonProperty("is_program_item")
	public Boolean getIsProgramItem() {
		return isProgramItem;
	}
	
	@JsonProperty("is_program_item")
	public void setIsProgramItem(Boolean isProgramItem) {
		this.isProgramItem = isProgramItem;
	}
	
	@JsonProperty("item_id")
	public Integer getItemId() {
		return itemId;
	}

	@JsonProperty("item_id")
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	@JsonProperty("item_variant_id")
	public Integer getItemVariantId() {
		return itemVariantId;
	}
	
	@JsonProperty("item_variant_id")
	public void setItemVariantId(Integer itemVariantId) {
		this.itemVariantId = itemVariantId;
	}

	@JsonProperty("track_stock")
	public Boolean getTrackStock() {
		return trackStock;
	}
	
	@JsonProperty("track_stock")
	public void setTrackStock(Boolean trackStock) {
		this.trackStock = trackStock;
	}
	
	@JsonProperty("gross_sales")
	public String getGrossSales() {
		return grossSales;
	}

	@JsonProperty("gross_sales")
	public void setGrossSales(String grossSales) {
		this.grossSales = grossSales;
	}
	
	@JsonProperty("created_at")
	public String getCreatedAt() {
		return createdAt;
	}
	
	@JsonProperty("created_at")
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty("item_background_color")
	public String getItemBackgroundColor() {
		return itemBackgroundColor;
	}
	
	@JsonProperty("item_background_color")
	public void setItemBackgroundColor(String itemBackgroundColor) {
		this.itemBackgroundColor = itemBackgroundColor;
	}
	
	@JsonProperty("category_id")
	public Integer getCategoryId() {
		return categoryId;
	}

	@JsonProperty("category_id")
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
