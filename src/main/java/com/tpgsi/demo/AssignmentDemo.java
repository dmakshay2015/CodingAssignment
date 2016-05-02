package com.tpgsi.demo;

import com.tpgsi.demo.service.AssignmentService;
import com.tpgsi.demo.utility.AssignmentUtility;

/**
 * 
 * @author Akshay
 *
 */
public class AssignmentDemo {
	/**
	 * program entry
	 * 
	 * @param args
	 *            file name as first argument
	 */
	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("Number of argument(s) required : 1");
			System.err.println("Arg 1 : Please provide the file name and retry");
			System.exit(-1);
		}

		final String fileName = args[0];
		final boolean fileFlag = AssignmentUtility.checkForFile(fileName);
		if (fileFlag) {
			final AssignmentService service = new AssignmentService();
			service.process(fileName);
		} else {
			System.err.println("File not found. Please provide the valid file.");
		}
	}

}
