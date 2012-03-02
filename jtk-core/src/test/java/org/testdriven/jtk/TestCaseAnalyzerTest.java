package org.testdriven.jtk;

import java.io.FileNotFoundException;
import java.io.InputStream;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import static org.mockito.Mockito.*;

public class TestCaseAnalyzerTest {

    @Test
    public void should_analyze_test_cases() throws Exception {
        // when
        AnalisysEngine engine = mock(AnalisysEngine.class);
        when(engine.getTestMethods(any(InputStream.class))).thenReturn(new TestCaseMethod[]{new TestCaseMethod(null, 0)});
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
        for (TestCase testCase : testCases) {
            assertThat(testCase.getUnit()).isNotNull();
            assertThat(testCase.getUnit().getClassFile()).isNotNull();
            assertThat(testCase.getTestCaseMethods()).isNotEmpty();

        }
        verify(engine, times(8)).getTestMethods(any(InputStream.class));
    }

    @Test(expected = FileNotFoundException.class)
    public void should_throw_exception_when_classes_not_found() throws Exception {
        // when
        AnalisysEngine engine = mock(AnalisysEngine.class);
        when(engine.getTestMethods(any(InputStream.class))).thenReturn(new TestCaseMethod[]{new TestCaseMethod(null, 0)});
        String[] srcDirs = new String[]{"src/test/java"};
        String[] classesDirs = new String[]{"target/"};
        TestCaseAnalyzer analyer = new TestCaseAnalyzer(engine, srcDirs,
                classesDirs);
        // then
        analyer.analyzeTestCases();
    }
}
