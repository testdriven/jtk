package org.testdriven.jtk;

import java.io.IOException;

public interface AnalisysEngine{

    TestCaseMethod[] getTestMethods() throws IOException;

    boolean hasTestCaseMehtods();
    
}