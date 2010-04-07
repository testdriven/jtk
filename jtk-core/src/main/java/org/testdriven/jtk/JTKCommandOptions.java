package org.testdriven.jtk;

public class JTKCommandOptions {

    private final String[] sources;

    public JTKCommandOptions(String[] sources) {
        this.sources = sources;
    }

    public String[] getSources() {
        return sources;
    }
}
