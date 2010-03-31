package org.bitbucket.jtk;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;

import org.junit.Test;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaSource;

public class SourceParserTest {

	@Test
	public void should_parse_java_source() throws Exception {
		// arrange
		JavaDocBuilder docBuilder = new JavaDocBuilder();

		// act
		JavaSource javaSource = docBuilder.addSource(new File(
				"src/test/resources/SimpleTestCase.java"));

		// assert
		assertThat(javaSource.getClasses()).hasSize(1);
		JavaClass testClass = javaSource.getClasses()[0];
		assertThat(testClass.getMethods()).hasAtLeastOneElementOfType(JavaMethod.class);
		JavaMethod testMethod = testClass.getMethods()[0];
		assertThat(testMethod.getAnnotations()).hasAllElementsOfType(Annotation.class);
		Annotation testAnnotation = testMethod.getAnnotations()[0];
		assertThat(testAnnotation.getType().getValue()).isEqualTo("org.junit.Test");
	}

}
