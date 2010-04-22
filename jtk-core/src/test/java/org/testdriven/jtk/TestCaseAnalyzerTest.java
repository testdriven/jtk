package org.testdriven.jtk;

import static org.fest.assertions.Assertions.assertThat;


import org.junit.Test;
import org.mockito.Mockito;

public class TestCaseAnalyzerTest {

	@Test
	public void should_return_invalid_test_cases() {
            //when
            AnalisysEngine engine = Mockito.mock(AnalisysEngine.class);
            String[] srcDirs = new String[]{"src/test/jsva"};
            String[] classesDirs = new String[]{"target/test-classes"};
            TestCaseAnalyzer analyer = new TestCaseAnalyzer(engine,srcDirs,classesDirs);
            //then
            AnalyzerResults results = analyer.analyzeTestCases();
            
            //assert
            assertThat(results);
        }
}
