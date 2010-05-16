package org.testdriven.jtk;

import java.io.File;
import java.io.IOException;
import org.testdriven.jtk.junit4.JUnit4AnalisysEngine;

public class JTK {

    private final File sourcesDir;
    private final File classesDir;

    public JTK(String sourcedir, String classesdir) throws IOException {
        this.sourcesDir = new File(sourcedir);

        if (!sourcesDir.exists() || !sourcesDir.isDirectory()) {
            throw new IOException("Sources directory " + sourcesDir.getAbsolutePath() + "doesn't exists");
        }

        this.classesDir = new File(classesdir);
        if (!classesDir.exists() || !classesDir.isDirectory()) {
            throw new IOException("Classes directory " + classesDir.getAbsolutePath() + "doesn't exists");
        }
    }

    public TestCaseAnalyzer createTestCaseAnalyzer() {
        AnalisysEngine engine = new JUnit4AnalisysEngine(null);
        String[] sourcesDirs = new String[]{sourcesDir.getAbsolutePath()};
        String[] classesDirs = new String[]{classesDir.getAbsolutePath()};
        return new TestCaseAnalyzer(null, sourcesDirs, classesDirs);
    }
}
