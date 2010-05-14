package org.testdriven.jtk;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.mockito.Mockito;

public class TestCaseAnalyzerTest {

    @Test
    public void should_analyze_test_cases() throws Exception {
        // when
        AnalisysEngine engine = Mockito.mock(AnalisysEngine.class);
        String[] srcDirs = new String[]{"src/test/java"};
        String[] classesDirs = new String[]{"target/test-classes"};
        TestCaseAnalyzer analyer = new TestCaseAnalyzer(engine, srcDirs,
                classesDirs);
        // then
        AnalyzerResults results = analyer.analyzeTestCases();
        final TestCase[] testCases = results.getTestCases();

        // assert
        //should find 8 test cases, make this 
        assertThat(testCases).hasSize(8);
        //testCases[0].
    }
}
