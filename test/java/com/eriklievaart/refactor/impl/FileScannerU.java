package com.eriklievaart.refactor.impl;

import junit.framework.Assert;

import org.junit.Test;

import com.eriklievaart.refactor.impl.FileScanner;

public class FileScannerU {

	@Test
	public void getExtension() {
		Assert.assertEquals("java", FileScanner.getExtension("Main.java"));
	}
}
