package org.testdriven.jtk;

import static org.fest.assertions.Assertions.assertThat;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.junit.Test;

public class ByteCodeAnalyzerTest {

	@SuppressWarnings("unchecked")
	@Test
	public void should_find_test_methods() throws Exception {
		FileInputStream inputStream = new FileInputStream(
				"target/test-classes/org/testdriven/jtk/JTKTest.class");
		ByteCodeAnalyzer analyzer = new ByteCodeAnalyzer(inputStream);

		TestCaseMethod[] testCaseMethods = analyzer.getTestMethods();

		Collection<String> testCaseNames = CollectionUtils.collect(Arrays
				.asList(testCaseMethods), new Transformer() {

			@Override
			public Object transform(Object input) {
				return ((TestCaseMethod) input).getMethodName();
			}
		});

		assertThat(testCaseNames).containsOnly(
				"should_throw_exception_if_base_dir_not_exists",
				"should_create_test_case_analyzer", "should_find_test_cases");
	}

	@Test
	public void should_find_assertions_in_test_methods() throws Exception {
		FileInputStream inputStream = new FileInputStream(
				"target/test-classes/org/testdriven/jtk/JTKTest.class");
		ByteCodeAnalyzer analyzer = new ByteCodeAnalyzer(inputStream);

		TestCaseMethod[] testCaseMethods = analyzer.getTestMethods();

		TestCaseMethod testCaseMethod = testCaseMethods[0];
		assertThat(testCaseMethod.getAssertions()).hasSize(1);
	}

}
