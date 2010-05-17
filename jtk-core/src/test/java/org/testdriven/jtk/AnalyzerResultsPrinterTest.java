/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testdriven.jtk;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * @author jpalka
 */
public class AnalyzerResultsPrinterTest {

	@Test
	public void should_print_test_cases_not_found() throws Exception {
		// given
		AnalyzerResults results = new AnalyzerResults(new ArrayList<TestCase>());
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		AnalyzerResultsPrinter printer = new AnalyzerResultsPrinter(output);

		// when
		printer.writeSummary(results);

		// than
		String summary = new String(output.toByteArray());
		StringReader input = new StringReader(summary);
		BufferedReader reader = new BufferedReader(input);
		assertEquals("no test cases found", reader.readLine());
	}

	@Test
	public void should_print_test_cases_summary() throws Exception {
		// given
		File baseDir = null;
		File sourceFile = null;
		TestCase testCase = new TestCase(new CompilationUnit(baseDir,
				sourceFile));
		TestCaseMethod testMethod = new TestCaseMethod("", 0);
		testCase.setTestCaseMethods(new TestCaseMethod[] { testMethod });
		AnalyzerResults results = new AnalyzerResults(Arrays.asList(testCase));
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		AnalyzerResultsPrinter printer = new AnalyzerResultsPrinter(output);

		// when
		printer.writeSummary(results);

		// than
		String summary = new String(output.toByteArray());
		StringReader input = new StringReader(summary);
		BufferedReader reader = new BufferedReader(input);
		assertEquals("found 1 test case(s) with 1 method(s)", reader.readLine());
	}
}
