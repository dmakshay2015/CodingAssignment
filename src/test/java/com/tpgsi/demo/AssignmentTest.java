package com.tpgsi.demo;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.tpgsi.demo.service.AssignmentService;
import com.tpgsi.demo.utility.AssignmentUtility;

/**
 * 
 * @author Akshay
 *
 */
public class AssignmentTest {
	AssignmentService service = new AssignmentService();
	private final static File TEST_FILES = new File("src/test/resources");

	@Test
	public void testValidCategories() {
		Assert.assertTrue(AssignmentUtility.checkForFile(TEST_FILES.getAbsolutePath() + "/validcategories.txt"));
		final List<String> testList = AssignmentUtility.readFile(TEST_FILES.getAbsolutePath() + "/validcategories.txt");
		final List<String> finalList = service.processCategoryValues(testList);
		final Map<String, Integer> countMap = service.processCategoryCounts(finalList);

		final Map<String, Integer> expMap = new LinkedHashMap<>();
		expMap.put("PERSON", 2);
		expMap.put("PLACE", 2);
		expMap.put("ANIMAL", 2);
		expMap.put("COMPUTER", 1);
		expMap.put("OTHER", 1);

		Assert.assertEquals(expMap, countMap);

		final List<String> expList = new LinkedList<>();
		expList.add("PERSON Bob Jones");
		expList.add("PLACE Washington");
		expList.add("PERSON Mary");
		expList.add("COMPUTER Mac");
		expList.add("OTHER Tree");
		expList.add("ANIMAL Dog");
		expList.add("PLACE Texas");
		expList.add("ANIMAL Cat");

		Assert.assertEquals(expList, finalList);
	}

	@Test
	public void testInvalidFilePath() {
		Assert.assertFalse(AssignmentUtility.checkForFile(TEST_FILES.getAbsolutePath() + "/invalidfilepath"));
	}

	@Test
	public void testJumbledCategories() {
		final List<String> testList = AssignmentUtility
				.readFile(TEST_FILES.getAbsolutePath() + "/jumbledcategories.txt");
		final List<String> finalList = service.processCategoryValues(testList);
		final Map<String, Integer> countMap = service.processCategoryCounts(finalList);

		final Map<String, Integer> expMap = new LinkedHashMap<>();
		expMap.put("PERSON", 2);
		expMap.put("PLACE", 1);
		expMap.put("ANIMAL", 2);
		expMap.put("COMPUTER", 1);
		expMap.put("OTHER", 0);
		Assert.assertEquals(expMap, countMap);

		final List<String> expList = new LinkedList<>();
		expList.add("COMPUTER Mac");
		expList.add("ANIMAL Dog");
		expList.add("PLACE Texas");
		expList.add("PERSON Mary");
		expList.add("ANIMAL Cat");
		expList.add("PERSON Bob Jones");

		Assert.assertEquals(expList, finalList);
	}

	@Test
	public void testProcessMethod() {
		try {
			service.process(null);
		} catch (Exception e) {
			Assert.fail("Exception occured");
		}
	}

	@Test
	public void testDuplicateCategories() {
		final List<String> testList = AssignmentUtility
				.readFile(TEST_FILES.getAbsolutePath() + "/duplicatecategories.txt");
		final List<String> finalList = service.processCategoryValues(testList);
		final Map<String, Integer> countMap = service.processCategoryCounts(finalList);

		final Map<String, Integer> expMap = new LinkedHashMap<>();
		expMap.put("PERSON", 2);
		expMap.put("PLACE", 2);
		expMap.put("ANIMAL", 2);
		expMap.put("COMPUTER", 1);
		expMap.put("OTHER", 1);
		Assert.assertEquals(expMap, countMap);

		final List<String> expList = new LinkedList<>();
		expList.add("ANIMAL Dog");
		expList.add("PERSON Bob Jones");
		expList.add("PLACE Washington");
		expList.add("PERSON Mary");
		expList.add("OTHER Tree");
		expList.add("COMPUTER Mac");
		expList.add("ANIMAL Cat");
		expList.add("PLACE Texas");

		Assert.assertEquals(expList, finalList);
	}

	@Test
	public void testExtraLineAndSpacesInCategories() {
		final List<String> testList = AssignmentUtility
				.readFile(TEST_FILES.getAbsolutePath() + "/extralineandspacescategories.txt");
		final List<String> finalList = service.processCategoryValues(testList);
		final Map<String, Integer> countMap = service.processCategoryCounts(finalList);

		final Map<String, Integer> expMap = new LinkedHashMap<>();
		expMap.put("PERSON", 2);
		expMap.put("PLACE", 2);
		expMap.put("ANIMAL", 1);
		expMap.put("COMPUTER", 0);
		expMap.put("OTHER", 1);
		Assert.assertEquals(expMap, countMap);

		final List<String> expList = new LinkedList<>();
		expList.add("PERSON Bob Jones");
		expList.add("PLACE Washington");
		expList.add("PERSON Mary");
		expList.add("OTHER Tree");
		expList.add("PLACE Texas");
		expList.add("ANIMAL Cat");

		Assert.assertEquals(expList, finalList);
	}
}
