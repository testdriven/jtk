/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testdriven.jtk;

import java.io.File;

/**
 *
 * @author jpalka
 */
class CompilationUnit {

    private final File baseDir;
    private final File sourceFile;

    public CompilationUnit(File baseDir, File sourceFile) {
        this.baseDir = baseDir;
        this.sourceFile = sourceFile;
    }

    public File getClassFile(String baseDir) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    void setClassFile(File classFile) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
