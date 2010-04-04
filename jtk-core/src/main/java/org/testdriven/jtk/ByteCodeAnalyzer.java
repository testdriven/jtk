package org.testdriven.jtk;

import static java.lang.String.format;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.EmptyVisitor;

public class ByteCodeAnalyzer {

	private static final String JUNIT4_TEST_ANNOTATION = "org/junit/Test";
	private final FileInputStream inputStream;

	public ByteCodeAnalyzer(FileInputStream inputStream) {
		this.inputStream = inputStream;

	}

	public String[] getTestMethods() throws IOException {
		ClassReader reader = new ClassReader(inputStream);

		final Stack<String> methods = new Stack<String>();
		final List<String> testMethodsNames = new ArrayList<String>();

		final MethodVisitor methodVisitor = new EmptyVisitor() {

			@Override
			public AnnotationVisitor visitAnnotation(String name,
					boolean visible) {
				System.out.println(format("visitAnnotation(%s,%b)", name,
						visible));

				String methodName = methods.pop();
				if (name.contains(JUNIT4_TEST_ANNOTATION)) {
					testMethodsNames.add(methodName);
				}

				return super.visitAnnotation(name, visible);
			}

			@Override
			public AnnotationVisitor visitAnnotation(String arg0, String arg1) {
				return super.visitAnnotation(arg0, arg1);
			}

		};

		reader.accept(new EmptyVisitor() {

			@Override
			public MethodVisitor visitMethod(int access, String name,
					String desc, String signature, String[] exceptions) {

				System.out.println(format("visitMethod(%d,%s,%s,%s,%s)",
						access, name, desc, signature, exceptions));

				methods.push(name);

				return methodVisitor;
			}

		}, 0);

		return testMethodsNames.toArray(new String[methods.size()]);
	}
}