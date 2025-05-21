package models;

public class IndexModel {
	private int category_id;
	private String categoryName;
	private String date;
	private String categoryImage;
	public IndexModel(int category_id, String categoryName, String date, String categoryImage) {
		super();
		this.category_id = category_id;
		this.categoryName = categoryName;
		this.date = date;
		this.categoryImage = categoryImage;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCategoryImage() {
		return categoryImage;
	}
	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}
}
