package com.tpgsi.demo.utility;

/**
 * 
 * @author Akshay
 *
 */
public enum Category {
	PERSON(0), PLACE(0), ANIMAL(0), COMPUTER(0), OTHER(0);

	private int count;

	/**
	 * Constructs a Category with the specified count
	 * 
	 * @param count
	 *            category count
	 */
	private Category(int count) {
		this.count = count;
	}

	/**
	 * Get the count
	 * 
	 * @return category default value
	 */
	public int getCount() {
		return count;
	}
}
