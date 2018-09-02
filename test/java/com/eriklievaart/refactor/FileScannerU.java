package com.eriklievaart.refactor;

import junit.framework.Assert;

import org.junit.Test;

public class FileScannerU {

	@Test
	public void getExtension() {
		Assert.assertEquals("java", FileScanner.getExtension("Main.java"));
	}
}
