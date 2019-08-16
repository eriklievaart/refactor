package com.eriklievaart.refactor.impl;

import org.junit.Test;

import com.eriklievaart.toolkit.lang.api.check.Check;

public class FileToolU {

	@Test
	public void replaceNewLines() {
		Check.isEqual(FileTool.replaceNewLines("a\nb", true), "a\nb");
		Check.isEqual(FileTool.replaceNewLines("a\nb", false), "a\r\nb");
		Check.isEqual(FileTool.replaceNewLines("a\r\nb", true), "a\nb");
		Check.isEqual(FileTool.replaceNewLines("a\r\nb", false), "a\r\nb");
	}
}
