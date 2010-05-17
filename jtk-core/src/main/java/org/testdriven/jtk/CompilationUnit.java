/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testdriven.jtk;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 * @author jpalka
 */
class CompilationUnit {

	private final File baseDir;
	private final File sourceFile;
	private File classFile;

	public CompilationUnit(File baseDir, File sourceFile) {
		this.baseDir = baseDir;
		this.sourceFile = sourceFile;
	}

	public File getClassFile(String classesDir) throws FileNotFoundException {

		String baseDirPath = baseDir.getAbsolutePath();
		String sourceFilePath = sourceFile.getAbsolutePath();

		if (sourceFilePath.startsWith(baseDirPath)) {
			String localSourcePath = sourceFilePath.substring(baseDirPath
					.length() + 1);
			String localClassPath = localSourcePath.replace('\\', '.');
			localClassPath = localSourcePath.replace(".java", ".class");
			return new File(classesDir, localClassPath);
		}

		throw new FileNotFoundException(
				"Source directory doesnt match source file location");
	}

	public File getClassFile() {
		return this.classFile;
	}

	public void setClassFile(File classFile) {
		this.classFile = classFile;
	}

	public File getSourceFile() {
		return sourceFile;
	}

}
