package com.tpgsi.demo.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.tpgsi.demo.model.AssignmentModel;
import com.tpgsi.demo.utility.AssignmentUtility;

/**
 * 
 * @author Akshay
 *
 */
public class AssignmentService {

	/**
	 * Read the file to get the category and subcategory with its appropriate
	 * count Print the category and subcatrgory Print their counts
	 * 
	 * @param fileName
	 *            file path
	 */
	public void process(final String fileName) {
		final List<String> inputList = AssignmentUtility.readFile(fileName);
		final List<String> categoryList = processCategoryValues(inputList);
		final Map<String, Integer> countMap = processCategoryCounts(categoryList);
		AssignmentUtility.printCategoryCount(countMap);
		AssignmentUtility.printCategoryValues(categoryList);
	}

	/**
	 * From the input list, process the category values
	 * 
	 * @param inputList
	 *            Data List
	 * @return list of computed category and subcategory strings
	 */
	public List<String> processCategoryValues(final List<String> inputList) {
		final List<AssignmentModel> assignmentList = new LinkedList<>();
		final Optional<List<String>> optional = Optional.ofNullable(inputList);
		if (optional.isPresent()) {
			for (final String line : inputList) {
				final String[] pairArray = line.split("\\s", 2);
				final String category = pairArray[0].trim();
				if (pairArray.length == 2) {
					if (AssignmentUtility.contains(category)) {
						final String subCategory = pairArray[1];
						final Optional<String> subCategoryOptional = Optional.ofNullable(subCategory);
						if (subCategoryOptional.isPresent()) {
							assignmentList.add(new AssignmentModel(category, subCategory.trim()));
						}
					}
				}
			}
		}
		return AssignmentUtility.stringValues(assignmentList);
	}

	/**
	 * From processed category list, get the count for each category
	 * 
	 * @param categoryList
	 *            list of computed category counts
	 * @return
	 */
	public Map<String, Integer> processCategoryCounts(final List<String> categoryList) {
		final Map<String, Integer> countMap = new LinkedHashMap<>(AssignmentUtility.DEFAULT_CATEGORY_COUNT_MAP);
		final Optional<List<String>> optional = Optional.ofNullable(categoryList);
		if (optional.isPresent()) {
			for (final String line : categoryList) {
				final String category = line.split("\\s", 2)[0];
				if (AssignmentUtility.contains(category) && countMap.containsKey(category)) {
					final int cnt = countMap.get(category) + 1;
					countMap.put(category, cnt);
				}
			}
		}
		return countMap;
	}
}
