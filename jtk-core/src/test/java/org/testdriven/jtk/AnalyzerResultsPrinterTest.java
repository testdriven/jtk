/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.testdriven.jtk;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author jpalka
 */
public class AnalyzerResultsPrinterTest {

    @Test
    public void should_print_test_cases_not_found(){
        //given
        AnalyzerResults results = new AnalyzerResults(new ArrayList<TestCase>());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        AnalyzerResultsPrinter printer = new AnalyzerResultsPrinter(output);

        //when
        printer.writeSummary(results);

        //than
        String summary = output.toString();
        Assert.assertEquals("no test cases found", summary);
    }
}
