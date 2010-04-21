package org.testdriven.jtk;

public class TestCaseAnalyzer {

    private final String[] srcDirs;
    private final String[] classesDirs;

    TestCaseAnalyzer(String[] srcDirs, String[] classesDirs) {
        this.srcDirs = srcDirs;
        this.classesDirs = classesDirs;
    }

    public AnalyzerResults analyzeTestCases() {
        return new AnalyzerResults();
    }
}
