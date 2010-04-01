package org.testdriven.jtk;

import java.io.File;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class TestCaseAnalyzerTest {

	@Test
	public void should_return_invalid_test_cases() {
		File[] testCases = null;
		TestCaseAnalyzer analyzer = new TestCaseAnalyzer(testCases);
		AnalyzerResults results = analyzer.analyzeTestCases();
		Assertions.assertThat(results);
	}
}
