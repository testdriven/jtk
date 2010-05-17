package org.testdriven.jtk;

import java.io.IOException;
import java.io.InputStream;

public interface AnalisysEngine {

	TestCaseMethod[] getTestMethods(InputStream inputStream) throws IOException;

}