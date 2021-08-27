package com.eriklievaart.refactor.file;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.eriklievaart.refactor.file.FileTool;

public class FileToolU {

	@Test
	public void replaceNewLines() {
		Assertions.assertThat(FileTool.replaceNewLines("a\nb", true)).isEqualTo("a\nb");
		Assertions.assertThat(FileTool.replaceNewLines("a\nb", false)).isEqualTo("a\r\nb");
		Assertions.assertThat(FileTool.replaceNewLines("a\r\nb", true)).isEqualTo("a\nb");
		Assertions.assertThat(FileTool.replaceNewLines("a\r\nb", false)).isEqualTo("a\r\nb");
	}
}
