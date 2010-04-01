package org.testdriven.jtk;

import java.io.File;

import org.junit.Test;

public class TestCaseAnalyzerTest {

	@Test
	public void should_return_invalid_test_cases() {
		File[] testCases = null;
		TestCaseAnalyzer analyzer = new TestCaseAnalyzer(testCases);
		analyzer.analyzeTestCases();
	}
}
