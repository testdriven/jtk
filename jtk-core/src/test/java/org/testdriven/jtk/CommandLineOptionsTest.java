package org.testdriven.jtk;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class CommandLineOptionsTest {

	@Test
	public void should_return_source_dir() throws Exception {

		String[] args = new String[]{"-srcdir","src/test/java"};
		JTKCommandParser cmdParser = new JTKCommandParser(args);

		JTKCommandOptions cmdOpts = cmdParser.parse();

		assertThat(cmdOpts.getSourceDirectory()).isEqualTo("src/test/java");
	}
}
