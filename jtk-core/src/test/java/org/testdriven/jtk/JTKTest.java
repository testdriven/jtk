package org.testdriven.jtk;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class JTKTest {

	@Test
	public void should_find_test_cases() throws Exception {
		// given
		String dir = "src/test/java";
		JTK jtk = new JTK(dir);
		// when
		File[] testCases = jtk.findTestCases();
		// then
		Assertions.assertThat(testCases).hasSize(3);
	}

	@Test(expected = IOException.class)
	public void should_throw_exception_if_base_dir_not_exists()
			throws Exception {
		String dir = "test/java";
		JTK jtk = new JTK(dir);
	}

	@Test
	public void should_create_test_case_analyzer() throws Exception {
		// given
		String dir = "src/test/java";
		JTK jtk = new JTK(dir);
		TestCaseAnalyzer analyzer = jtk.createTestCaseAnalyzer();

		assertThat(analyzer).isNotNull();
		assertThat(analyzer.getTestCases()).hasSize(3);
	}

}
