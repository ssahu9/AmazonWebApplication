package com.project.bean;

public class Category {
	public Category() {

	}

	private String CategoryName;

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public Category(String categoryName) {
		super();
		CategoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [CategoryName=" + CategoryName + "]";
	}

}
