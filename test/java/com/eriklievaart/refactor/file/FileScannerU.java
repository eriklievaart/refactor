package com.eriklievaart.refactor.file;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.eriklievaart.refactor.file.FileScanner;

public class FileScannerU {

	@Test
	public void getExtension() {
		Assertions.assertThat(FileScanner.getExtension("Main.java")).isEqualTo("java");
	}
}
