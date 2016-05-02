package com.tpgsi.demo.model;

/**
 * 
 * @author Akshay
 *
 */
public class AssignmentModel {
	private String category;
	private String subCategory;

	public AssignmentModel(String category, String subCategory) {
		this.category = category;
		this.subCategory = subCategory;
	}

	public String getCategory() {
		return category;
	}

	public String getSubCategory() {
		return subCategory;
	}
}
