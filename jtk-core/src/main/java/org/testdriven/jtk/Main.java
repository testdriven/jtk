package org.testdriven.jtk;

public final class Main {

	/**
	 * Main application entry point
	 */
	public static void main(final String[] args) throws  Exception{
            JTKCommandParser parser = new JTKCommandParser(args);
            JTKCommandOptions options = parser.parse();

            JTK jtk = new JTK(options.getSources()[0],options.getClasses()[0]);
            TestCaseAnalyzer analyzer = jtk.createTestCaseAnalyzer();
            AnalyzerResults results = analyzer.analyzeTestCases();

            AnalyzerResultsPrinter printer = new AnalyzerResultsPrinter(System.out);
            printer.writeSummary(results);
	}
}
