package org.testdriven.jtk;

public class TestCaseAnalyzer {

    private final String[] srcDirs;
    private final String[] classesDirs;
    private final AnalisysEngine engine;


    public TestCaseAnalyzer(AnalisysEngine engine, String[] srcDirs, String[] classesDirs) {
        this.srcDirs = srcDirs;
        this.classesDirs = classesDirs;
        this.engine = engine;
    }

    public AnalyzerResults analyzeTestCases() {
        return new AnalyzerResults();
    }
}
