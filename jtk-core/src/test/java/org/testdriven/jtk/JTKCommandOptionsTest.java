package org.testdriven.jtk;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class JTKCommandOptionsTest {

	@Test
	public void should_return_source_dir() throws Exception {

		String[] args = new String[] { "-sources", "src/test/java", "-classes",
				"classes" };
		JTKCommandParser cmdParser = new JTKCommandParser(args);

		JTKCommandOptions cmdOpts = cmdParser.parse();

		assertThat(cmdOpts.getSources()).containsOnly("src/test/java");
	}

	@Test
	public void should_return_two_source_dirs() throws Exception {
		String[] args = new String[] { "-sources",
				"src/test/java;src/test/java.jar", "-classes", "classes" };
		JTKCommandParser cmdParser = new JTKCommandParser(args);

		JTKCommandOptions cmdOpts = cmdParser.parse();

		assertThat(cmdOpts.getSources()).containsOnly("src/test/java",
				"src/test/java.jar");

	}

	@Test
	public void should_return_class_dir() throws Exception {

		String[] args = new String[] { "-classes", "target/test/java",
				"-sources", "sources" };
		JTKCommandParser cmdParser = new JTKCommandParser(args);

		JTKCommandOptions cmdOpts = cmdParser.parse();

		assertThat(cmdOpts.getClasses()).containsOnly("target/test/java");
	}

}
