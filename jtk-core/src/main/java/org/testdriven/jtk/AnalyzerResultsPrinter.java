/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testdriven.jtk;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author jpalka
 */
class AnalyzerResultsPrinter {

    private final PrintWriter writer;

    AnalyzerResultsPrinter(OutputStream output) {
        this.writer = new PrintWriter(output);
    }

    void writeSummary(AnalyzerResults results) {
        if (results.getTestCases().length == 0) {
            writer.println("no test cases found");

        } else {

            int testCasesCount = results.getTestCases().length;
            int testMethodCount = 0;
            for (TestCase testCase : results.getTestCases()) {
                testMethodCount += testCase.getTestCaseMethods().length;
            }

            writer.println("found " + testCasesCount + " test case(s) with " + testMethodCount + " method(s)");
        }
        writer.flush();
    }
}
