package org.testdriven.jtk.maven.plugin;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.testdriven.jtk.AnalyzerResults;

import org.testdriven.jtk.JTK;
import org.testdriven.jtk.TestCaseAnalyzer;

/**
 * @goal check
 */
public class CheckTestCasesMojo extends AbstractMojo {

	private String testSources = "src/test/java";

	public void setTestSources(String testSources) {
		this.testSources = testSources;
	}

	@Override
	public void execute() throws MojoExecutionException {
		try {
			JTK jtk = new JTK(testSources);
			File[] testCases = readTestCases(jtk);
			if (testCases.length > 0) {
				TestCaseAnalyzer analyser = jtk.createTestCaseAnalyzer(testCases);
				AnalyzerResults results = analyser.analyzeTestCases();
				getLog().info("JTK Result: " + results);
			}
		} catch (IOException e) {
			throw new MojoExecutionException("Error executing JTK", e);
		}
	}

	private File[] readTestCases(JTK jtk) {
		File[] testCases = jtk.findTestCases();
		if (testCases.length == 0) {
			getLog().warn("No test cases fund under '" + testSources + "'");
		} else {
			getLog().info("Found " + testCases.length + " test case(s):");
			for (File testCase : testCases) {
				getLog().info(testCase.getPath());
			}
		}
		return testCases;
	}
}
