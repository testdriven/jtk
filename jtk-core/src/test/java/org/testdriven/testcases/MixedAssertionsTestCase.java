package org.testdriven.testcases;

import static org.junit.Assert.assertTrue;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class MixedAssertionsTestCase {

	@Test
	public void this_method_is_an_empty_test_case() {
		assertTrue(true);
		assertThat(true).isTrue();
	}

}
