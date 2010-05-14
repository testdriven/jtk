package org.testdriven.jtk;

public class TestCase {
    private final CompilationUnit unit;
    private TestCaseMethod[] testCaseMethods;

    public TestCase(CompilationUnit unit) {
        this.unit = unit;
    }

    public CompilationUnit getUnit() {
        return unit;
    }

    public TestCaseMethod[] getTestCaseMethods(){
        return testCaseMethods;
    }

    void setTestCaseMethods(TestCaseMethod[] methods) {
        this.testCaseMethods = methods;
    }


}
