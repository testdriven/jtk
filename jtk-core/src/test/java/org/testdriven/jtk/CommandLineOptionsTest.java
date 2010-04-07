package org.testdriven.jtk;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class CommandLineOptionsTest {

	@Test
	public void should_return_source_dir() throws Exception {

		String[] args = new String[]{"-src","src/test/java"};
		JTKCommandParser cmdParser = new JTKCommandParser(args);

		JTKCommandOptions cmdOpts = cmdParser.parse();

		assertThat(cmdOpts.getSources()).containsOnly("src/test/java");
	}

        @Test
        public void should_return_two_source_dirs() throws Exception{
		String[] args = new String[]{"-src","src/test/java;src/test/java.jar"};
		JTKCommandParser cmdParser = new JTKCommandParser(args);

		JTKCommandOptions cmdOpts = cmdParser.parse();

		assertThat(cmdOpts.getSources()).containsOnly("src/test/java","src/test/java.jar");

        }
}
