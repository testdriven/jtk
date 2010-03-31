package org.bitbucket.jtk;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;

import org.junit.Test;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaSource;

public class SourceParserTest {

	@Test
	public void should_parse_java_source() throws Exception {
		JavaDocBuilder docBuilder = new JavaDocBuilder();

		JavaSource javaSource = docBuilder.addSource(new File(
				"src/test/java/org/bitbucket/jtk/SourceParserTest.java"));

		assertThat(javaSource.getClasses()).hasSize(1);
	}

}
