package org.testdriven.jtk.maven.plugin;

import java.io.File;
import org.apache.maven.plugin.MojoExecutionException;
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

	public void testThePluginWillPrintInfoWhenNoTestCasesAreFound() throws Exception {
		// arrange
		File pom = getTestFile("src/test/resources/no-test-cases/pom.xml");
		CheckTestCasesMojo checker = (CheckTestCasesMojo) lookupMojo("check", pom);

		// act
		checker.execute();

		// assert
		assertTrue("Should not trow exceptions here", true);
	}

	public void testWillIssueWarningWhenFolderWithTestCasesDoesNotExist() throws Exception {
		// arrange
		File pom = getTestFile("src/test/resources/wrong-test-sources-location/pom.xml");
		CheckTestCasesMojo checker = (CheckTestCasesMojo) lookupMojo("check", pom);

		// act
		try {
			checker.execute();
			fail("Expected exception not thrown");
		} catch (MojoExecutionException e) {
			// assert
			assertTrue(e.getMessage().contains("Error executing JTK"));
		}
	}
}
