/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testdriven.jtk;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 
 * @author jpalka
 */
class AnalyzerResultsPrinter {

	private final PrintWriter writer;

	AnalyzerResultsPrinter(OutputStream output) {
		this.writer = new PrintWriter(output);
	}

	void writeSummary(AnalyzerResults results) {
		if (results.getTestCases().length == 0) {
			writer.println("no test cases found");

		} else {

			int testCasesCount = results.getTestCases().length;
			int testMethodCount = 0;
			TestCase[] testCases = results.getTestCases();

			for (TestCase testCase : testCases) {
				testMethodCount += testCase.getTestCaseMethods().length;
			}

			writer.println("found " + testCasesCount + " test case(s) with "
					+ testMethodCount + " method(s)");

			for (TestCase testCase : testCases) {
				if (testCase.getUnit().getClassFile() != null) {
					writer.println("Test case class: "
							+ testCase.getUnit().getClassFile()
									.getAbsolutePath());
					for (TestCaseMethod testMethod : testCase
							.getTestCaseMethods()) {
						writer.println("\t method "
								+ testMethod.getMethodName() + " found "
								+ testMethod.getAssertions().length
								+ " assertion(s)");
					}
				}
			}
		}
		writer.flush();
	}
}
