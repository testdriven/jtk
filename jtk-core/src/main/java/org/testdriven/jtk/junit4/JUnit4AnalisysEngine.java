package org.testdriven.jtk.junit4;

import java.io.InputStream;
import org.testdriven.jtk.AnalisysEngine;
import java.io.IOException;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.testdriven.jtk.TestCaseMethod;

public class JUnit4AnalisysEngine implements AnalisysEngine {

    public static final String JUNIT4_TEST_ANNOTATION = "org/junit/Test";
    private final List<String> assertionsFilter;

    public JUnit4AnalisysEngine(List<String> assertionsFilter) {
        this.assertionsFilter = assertionsFilter;
    }

    @Override
    public TestCaseMethod[] getTestMethods(InputStream inputStream) throws IOException {
        ClassReader reader = new ClassReader(inputStream);

        JUnit4AnalisysContext context = new JUnit4AnalisysContext(assertionsFilter);

        final MethodVisitor methodVisitor = new TestCaseMethodVisitor(context);
        final ClassVisitor classVisitor = new TestCaseClassVisitor(context,
                methodVisitor);

        reader.accept(classVisitor, 0);

        return context.getTestMethods();
    }
}
