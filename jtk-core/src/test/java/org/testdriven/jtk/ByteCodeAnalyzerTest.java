package org.testdriven.jtk;

import static org.fest.assertions.Assertions.assertThat;

import java.io.FileInputStream;

import org.junit.Test;

public class ByteCodeAnalyzerTest {

	@Test
	public void should_visit_test_methods() throws Exception {
		FileInputStream inputStream = new FileInputStream(
				"target/test-classes/org/testdriven/jtk/JTKTest.class");
		ByteCodeAnalyzer analyzer = new ByteCodeAnalyzer(inputStream);

		String[] methods = analyzer.getTestMethods();

		assertThat(methods).containsOnly(
				"should_throw_exception_if_base_dir_not_exists",
				"should_create_test_case_analyzer", 
				"should_find_test_cases");
	}

}
