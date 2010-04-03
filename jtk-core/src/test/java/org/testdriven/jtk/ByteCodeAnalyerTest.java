package org.testdriven.jtk;

import java.io.FileInputStream;

import org.junit.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.EmptyVisitor;

public class ByteCodeAnalyerTest {

	@Test
	public void should_visit_test_methods() throws Exception {
		ClassReader reader = new ClassReader(new FileInputStream(
				"target\\test-classes\\org\\testdriven\\jtk\\JTKTest.class "));

		reader.accept(new EmptyVisitor() {

			@Override
			public MethodVisitor visitMethod(int arg0, String arg1,
					String arg2, String arg3, String[] arg4) {
				System.out.println(arg1);
				return super.visitMethod(arg0, arg1, arg2, arg3, arg4);
			}

			@Override
			public AnnotationVisitor visitAnnotation(String arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
				return super.visitAnnotation(arg0, arg1);
			}

			@Override
			public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
				return super.visitAnnotation(arg0, arg1);
			}

		}, 0);
	}

}
