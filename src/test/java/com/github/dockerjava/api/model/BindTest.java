package com.github.dockerjava.api.model;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class BindTest {
	
	@Test
	public void parseUsingDefaultAccessMode() {
		Bind bind = Bind.parse("/host:/container");
		assertEquals(bind.getPath(), "/host");
		assertEquals(bind.getVolume().getPath(), "/container");
		assertEquals(bind.isReadOnly(), false);
	}

	@Test
	public void parseReadWrite() {
		Bind bind = Bind.parse("/host:/container:rw");
		assertEquals(bind.getPath(), "/host");
		assertEquals(bind.getVolume().getPath(), "/container");
		assertEquals(bind.isReadOnly(), false);
	}
	
	@Test
	public void parseReadOnly() {
		Bind bind = Bind.parse("/host:/container:ro");
		assertEquals(bind.getPath(), "/host");
		assertEquals(bind.getVolume().getPath(), "/container");
		assertEquals(bind.isReadOnly(), true);
	}

	@Test(expectedExceptions = IllegalArgumentException.class,
			expectedExceptionsMessageRegExp = "Error parsing Bind.*")
	public void parseInvalidAccessMode() {
		Bind.parse("/host:/container:xx");
	}

	@Test(expectedExceptions = IllegalArgumentException.class, 
			expectedExceptionsMessageRegExp = "Error parsing Bind 'nonsense'")
	public void parseInvalidInput() {
		Bind.parse("nonsense");
	}

	@Test(expectedExceptions = IllegalArgumentException.class, 
			expectedExceptionsMessageRegExp = "Error parsing Bind 'null'")
	public void parseNull() {
		Bind.parse(null);
	}

}
