package org.testdriven.jtk;

import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;

public class JTKTest {

	@Test(expected = IOException.class)
	public void should_throw_exception_if_source_dir_not_exists()
			throws Exception {
		String sourcedir = "test/java";
		String classesdir = "target/test-classes";
		new JTK(sourcedir, classesdir);
	}

	@Test(expected = IOException.class)
	public void should_throw_exception_if_classes_dir_not_exists()
			throws Exception {
		String sourcedir = "src/test/java";
		String classesdir = "test-classes";
		new JTK(sourcedir, classesdir);
	}

	@Test
	public void should_create_test_case_analyzer() throws Exception {
		// given
		String sourcedir = "src/test/java";
		String classesdir = "target/test-classes";
		JTK jtk = new JTK(sourcedir, classesdir);
		TestCaseAnalyzer analyzer = jtk.createTestCaseAnalyzer();

		assertThat(analyzer).isNotNull();
	}
}
