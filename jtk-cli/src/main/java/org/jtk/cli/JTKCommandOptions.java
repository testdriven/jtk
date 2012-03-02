package org.jtk.cli;

public class JTKCommandOptions {

	private final String[] sources;
	private final String[] classes;

	public JTKCommandOptions(String[] sources, String classes[]) {
		this.sources = sources;
		this.classes = classes;
	}

	public String[] getSources() {
		return sources;
	}

	public String[] getClasses() {
		return classes;
	}
}
