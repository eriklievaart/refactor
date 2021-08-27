package com.eriklievaart.refactor.text;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class StringReplacerU {

	@Test
	public void in() {
		StringReplacer testable = new StringReplacer("TODO", "FIXME");
		Assertions.assertThat(testable.in("// TODO go home")).isEqualTo("// FIXME go home");
		Assertions.assertThat(testable.in("// go home")).isEqualTo("// go home");
	}

	@Test
	public void matches() {
		StringReplacer testable = new StringReplacer("TODO", "FIXME");
		Assertions.assertThat(testable.matches("// TODO go home")).isEqualTo(true);
		Assertions.assertThat(testable.matches("// FIXME go home")).isEqualTo(false);
	}
}
