package org.jtk.cli;

import org.testdriven.jtk.AnalyzerResults;
import org.testdriven.jtk.AnalyzerResultsPrinter;
import org.testdriven.jtk.JTK;
import org.testdriven.jtk.TestCaseAnalyzer;

public final class Main {

	/**
	 * Main application entry point
	 */
	public static void main(final String[] args) throws Exception {
		JTKCommandParser parser = new JTKCommandParser(args);
		JTKCommandOptions options = parser.parse();
		
		final String sources = options.getSources()[0];
		final String classes = options.getClasses()[0];

		JTK jtk = new JTK(sources, classes);
		TestCaseAnalyzer analyzer = jtk.createTestCaseAnalyzer();
		AnalyzerResults results = analyzer.analyzeTestCases();

		AnalyzerResultsPrinter printer = new AnalyzerResultsPrinter(System.out);
		printer.writeSummary(results);
	}
}
