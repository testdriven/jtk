/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.testdriven.jtk;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import org.testdriven.jtk.AnalyzerResults;

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
        if(results.getTestCases().length == 0){
            writer.println("no test cases found");
        }

        writer.flush();
    }

}
