package org.testdriven.jtk.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal check
 */
public class CheckTestCasesMojo extends AbstractMojo {

	public void execute() throws MojoExecutionException {
		getLog().info("Hello, world.");
	}
}
