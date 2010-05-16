/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testdriven.jtk;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.TestMethod;

/**
 *
 * @author jpalka
 */
public class AnalyzerResultsPrinterTest {

    @Test
    public void should_print_test_cases_not_found() {
        //given
        AnalyzerResults results = new AnalyzerResults(new ArrayList<TestCase>());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        AnalyzerResultsPrinter printer = new AnalyzerResultsPrinter(output);

        //when
        printer.writeSummary(results);

        //than
        String summary = new String(output.toByteArray());
        Assert.assertEquals("no test cases found\n", summary);
    }

    @Test
    public void should_print_test_cases_summary() {
        //given
        File baseDir = null;
        File sourceFile = null;
        TestCase testCase = new TestCase(new CompilationUnit(baseDir,sourceFile));
        TestCaseMethod testMethod = new TestCaseMethod("", 0);
        testCase.setTestCaseMethods(new TestCaseMethod[]{testMethod});
        AnalyzerResults results = new AnalyzerResults(Arrays.asList(testCase));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        AnalyzerResultsPrinter printer = new AnalyzerResultsPrinter(output);

        //when
        printer.writeSummary(results);

        //than
        String summary = new String(output.toByteArray());
        Assert.assertEquals("found 1 test case(s) with 1 method(s)\n", summary);
    }
}
