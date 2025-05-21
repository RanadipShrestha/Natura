   package models;

public class ProductModel {
	private int product_id;
	private String productName;
	private String description;
	private int price;
	private String image;
	private String quantity_unit;
	private String manufacture_date;
	private String expiry_date;
	private int category_id;
	private String category_name;
	private String key_feature;
	public ProductModel(int product_id, String productName, String description, int price, String image,
			String quantity_unit, String manufacture_date, String expiry_date, int category_id, String category_name,
			String key_feature) {
		super();
		this.product_id = product_id;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.image = image;
		this.quantity_unit = quantity_unit;
		this.manufacture_date = manufacture_date;
		this.expiry_date = expiry_date;
		this.category_id = category_id;
		this.category_name = category_name;
		this.key_feature = key_feature;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getQuantity_unit() {
		return quantity_unit;
	}
	public void setQuantity_unit(String quantity_unit) {
		this.quantity_unit = quantity_unit;
	}
	public String getManufacture_date() {
		return manufacture_date;
	}
	public void setManufacture_date(String manufacture_date) {
		this.manufacture_date = manufacture_date;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getKey_feature() {
		return key_feature;
	}
	public void setKey_feature(String key_feature) {
		this.key_feature = key_feature;
	}
	
	
}
