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
    private String testClasses = "target/test-classes";

    public void setTestSources(String testSources) {
        this.testSources = testSources;
    }

    public void setTestClasses(String testClasses) {
        this.testClasses = testClasses;
    }

    @Override
    public void execute() throws MojoExecutionException {
        try {
            JTK jtk = new JTK(testSources, testClasses);
            TestCaseAnalyzer analyzer = jtk.createTestCaseAnalyzer();
            final AnalyzerResults results = analyzer.analyzeTestCases();
            getLog().info("JTK Result: " + results);
        } catch (IOException e) {
            throw new MojoExecutionException("Error executing JTK", e);
        }
    }
}
