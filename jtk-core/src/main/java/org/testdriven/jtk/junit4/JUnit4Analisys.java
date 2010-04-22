package org.testdriven.jtk.junit4;

import java.util.*;
import org.testdriven.jtk.TestCaseAssertion;
import org.testdriven.jtk.TestCaseMethod;

public class JUnit4Analisys {

    // stack of all visited methods names
    private final Stack<String> methodNames = new Stack<String>();
    // list of all found test case methods
    private final List<TestCaseMethod> testCaseMethods = new ArrayList<TestCaseMethod>();
    private final List<String> assertionsFilter;

    public JUnit4Analisys(List<String> assertionsFilter) {
        this.assertionsFilter = assertionsFilter;
    }

    public TestCaseMethod[] getTestMethods() {
        return testCaseMethods.toArray(new TestCaseMethod[]{});
    }

    public List<String> getAssertionsFilter() {
        return assertionsFilter;
    }

    public String getMethodName() {
        return methodNames.pop();
    }

    public void addTestCaseMethod(TestCaseMethod testCaseMethod) {
        testCaseMethods.add(testCaseMethod);

    }

    public boolean hasTestCaseMehtods() {
        return !testCaseMethods.isEmpty();
    }

    public void addAssertion(TestCaseAssertion testCaseAssertion) {
        TestCaseMethod currentTestCaseMethod = testCaseMethods.get(testCaseMethods.size() - 1);
        currentTestCaseMethod.addAssertion(testCaseAssertion);
    }

    public void addMethodName(String name) {
        methodNames.push(name);
    }

    public boolean matchesAssetionsFilter(String owner) {

        return hasTestCaseMehtods() && assertionsFilter.contains(owner.replace("/", "."));
    }
}
