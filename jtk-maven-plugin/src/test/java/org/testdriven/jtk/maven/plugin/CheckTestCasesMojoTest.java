package org.testdriven.jtk.maven.plugin;

import java.io.File;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

public class CheckTestCasesMojoTest extends AbstractMojoTestCase {

	public void testThePluginWorks() throws Exception {
		// arrange
		File pom = getTestFile("src/test/resources/simple/pom.xml");
		CheckTestCasesMojo checker = (CheckTestCasesMojo) lookupMojo("check", pom);

		// act
		checker.execute();

		// assert
		assertTrue("Should not trow exceptions here", true);
	}
}
