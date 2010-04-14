package org.testdriven.jtk.junit4;

import static org.fest.assertions.Assertions.assertThat;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.junit.Test;
import org.testdriven.jtk.TestCaseAssertion;
import org.testdriven.jtk.TestCaseMethod;
import org.testdriven.jtk.junit4.ByteCodeAnalyzer;

public class ByteCodeAnalyzerTest {

	@SuppressWarnings("unchecked")
	@Test
	public void should_find_assertion_in_testcase() throws Exception {
		// given
		String testCaseClass = "target/test-classes/org/testdriven/testcases/AssertionTestCase.class";
		FileInputStream inputStream = new FileInputStream(testCaseClass);

		List<String> assertionsFilter = Arrays.asList("org.junit.Assert");

		ByteCodeAnalyzer analyzer = new ByteCodeAnalyzer(inputStream,
				assertionsFilter);

		// when
		TestCaseMethod[] testCaseMethods = analyzer.getTestMethods();

		// than
		Collection<String> testCaseNames = CollectionUtils.collect(Arrays
				.asList(testCaseMethods), new Transformer() {

			@Override
			public Object transform(Object input) {
				return ((TestCaseMethod) input).getMethodName();
			}
		});

		assertThat(testCaseNames).containsOnly(
				"this_method_is_an_empty_test_case");
		TestCaseMethod testCaseMethod = testCaseMethods[0];
		TestCaseAssertion[] assertions = testCaseMethod.getAssertions();
		assertThat(assertions).hasSize(1);
		TestCaseAssertion assertion = assertions[0];
		assertThat(assertion.getLineNumber()).isEqualTo(11);
		assertThat(assertion.getAssertionClass()).isEqualTo("org/junit/Assert");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void should_not_find_assertions_in_testcase() throws Exception {
		// given
		String testCaseClass = "target/test-classes/org/testdriven/testcases/NoAssertionTestCase.class";
		FileInputStream inputStream = new FileInputStream(testCaseClass);

		List<String> assertionsFilter = Arrays.asList("org.junit.Assert");
		ByteCodeAnalyzer analyzer = new ByteCodeAnalyzer(inputStream,
				assertionsFilter);

		// when
		TestCaseMethod[] testCaseMethods = analyzer.getTestMethods();

		// than
		Collection<String> testCaseNames = CollectionUtils.collect(Arrays
				.asList(testCaseMethods), new Transformer() {

			@Override
			public Object transform(Object input) {
				return ((TestCaseMethod) input).getMethodName();
			}
		});
		assertThat(testCaseNames).containsOnly(
				"this_method_is_an_empty_test_case");
		TestCaseMethod testCaseMethod = testCaseMethods[0];
		TestCaseAssertion[] assertions = testCaseMethod.getAssertions();
		assertThat(assertions).isEmpty();

	}

	@Test
	public void should_not_find_testcase() throws Exception {
		// given
		String testCaseClass = "target/test-classes/org/testdriven/testcases/EmptyTestCase.class";
		FileInputStream inputStream = new FileInputStream(testCaseClass);

		List<String> assertionsFilter = Arrays.asList("org.junit.Assert");
		ByteCodeAnalyzer analyzer = new ByteCodeAnalyzer(inputStream,
				assertionsFilter);

		// when
		TestCaseMethod[] testCaseMethods = analyzer.getTestMethods();

		// than
		assertThat(testCaseMethods).isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void should_find_assertions_by_filter() throws Exception {
		String testCaseClass = "target/test-classes/org/testdriven/testcases/MixedAssertionsTestCase.class";
		FileInputStream inputStream = new FileInputStream(testCaseClass);

		List<String> assertionsFilter = Arrays.asList("org.junit.Assert",
				"org.fest.assertions.Assertions");
		ByteCodeAnalyzer analyzer = new ByteCodeAnalyzer(inputStream,
				assertionsFilter);

		TestCaseMethod[] testCaseMethods = analyzer.getTestMethods();
		Collection<String> testCaseNames = CollectionUtils.collect(Arrays
				.asList(testCaseMethods), new Transformer() {

			@Override
			public Object transform(Object input) {
				return ((TestCaseMethod) input).getMethodName();
			}
		});
		assertThat(testCaseNames).containsOnly(
				"this_method_is_an_empty_test_case");
		TestCaseMethod testCaseMethod = testCaseMethods[0];
		assertThat(testCaseMethod.getAssertions()).hasSize(2);
	}
}
