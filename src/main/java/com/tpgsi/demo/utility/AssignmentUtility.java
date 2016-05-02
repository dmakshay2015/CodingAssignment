package com.tpgsi.demo.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tpgsi.demo.model.AssignmentModel;

/**
 * 
 * @author Akshay
 *
 */
public class AssignmentUtility {
	/**
	 * default category map with count
	 */
	public static final Map<String, Integer> DEFAULT_CATEGORY_COUNT_MAP = new LinkedHashMap<>();

	static {
		for (final Category category : Category.values()) {
			DEFAULT_CATEGORY_COUNT_MAP.put(category.name(), category.getCount());
		}
	}

	/**
	 * Check if the file is valid
	 * 
	 * @param fileName file path
	 * @return boolean true if file exists
	 */
	public static boolean checkForFile(final String fileName) {
		final File file = new File(fileName);
		return file.exists();
	}

	/**
	 * Read the input file
	 * 
	 * @param fileName file path
	 * @return list of strings
	 */
	public static List<String> readFile(final String fileName) {
		final Optional<String> optional = Optional.ofNullable(fileName);
		List<String> list = null;
		if (optional.isPresent()) {
			try (final BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
				list = br.lines().map(s -> s.trim()).distinct().collect(Collectors.toList());
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * Check if the input category is a valid category
	 * 
	 * @param testValue value to be tested against an enum
	 * @return boolean true if the value exists inside an enum
	 */
	public static boolean contains(final String testValue) {
		for (final Category type : Category.values()) {
			if (type.name().equals(testValue)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get the string values for category and subcategory
	 * 
	 * @param modelList list of all assignment model objects
	 * @return list of category and subcategory
	 */
	public static List<String> stringValues(final List<AssignmentModel> modelList) {
		final List<String> catList = new LinkedList<>();
		final Optional<List<AssignmentModel>> optional = Optional.ofNullable(modelList);
		if (optional.isPresent()) {
			for (final AssignmentModel model : modelList) {
				catList.add(model.getCategory().concat(" ").concat(model.getSubCategory()));
			}
		}
		return catList;
	}

	/**
	 * Print the category with its count
	 * 
	 * @param countMap computed map of category with its count
	 */
	public static void printCategoryCount(final Map<String, Integer> countMap) {
		final Optional<Map<String, Integer>> optional = Optional.ofNullable(countMap);
		if (optional.isPresent()) {
			System.out.println("Category Count");
			for (final Map.Entry<String, Integer> countEntry : countMap.entrySet()) {
				System.out.println(countEntry.getKey() + " " + countEntry.getValue());
			}
			System.out.println();
		}
	}

	/**
	 * Print the category with its subcategory
	 * 
	 * @param catList computed list of category with its subcategory
	 */
	public static void printCategoryValues(final List<String> catList) {
		final Optional<List<String>> optional = Optional.ofNullable(catList);
		if (optional.isPresent()) {
			for (final String value : catList) {
				System.out.println(value);
			}
		}
	}

}
