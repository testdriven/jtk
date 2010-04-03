package org.testdriven.jtk;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JTK {

	private static final String TEST_JAVA_SUFFIX = "Test.java";

	private static final FileFilter TEST_CASES_FILTER = new FileFilter() {

		public boolean accept(File pathname) {
			return pathname.isFile()
					&& pathname.getAbsolutePath().endsWith(TEST_JAVA_SUFFIX);
		}
	};

	private static final FileFilter DIRECTORY_FILTER = new FileFilter() {

		public boolean accept(File pathname) {
			return pathname.isDirectory();
		}
	};

	private File baseDir;

	public JTK(String dir) throws IOException {
		this.baseDir = new File(dir);
		
		if(!baseDir.exists()||!baseDir.isDirectory()){
			throw new IOException("Directory " + baseDir.getAbsolutePath()+"doesn't exists");
		}
	}

	public File[] findTestCases() {
		return findTestCases(baseDir);

	}

	private File[] findTestCases(File directory) {
		// simple implementation that checks filename pattern
		// TODO in future it should be delegated to engine that will recognize
		// JUnit3, JUnit4 and TestNG tests
		List<File> testCases = new ArrayList<File>();

		File[] files = directory.listFiles(TEST_CASES_FILTER);
		testCases.addAll(Arrays.asList(files));

		// dive deep into directory structure

		File[] directories = directory.listFiles(DIRECTORY_FILTER);

		for (File dir : directories) {
			files = findTestCases(dir);
			testCases.addAll(Arrays.asList(files));
		}

		return testCases.toArray(new File[testCases.size()]);
	}

	public TestCaseAnalyzer createTestCaseAnalyzer() {
		return createTestCaseAnalyzer(findTestCases());
	}

	public TestCaseAnalyzer createTestCaseAnalyzer(File[] testCases) {
		return new TestCaseAnalyzer(findTestCases());
	}
}
