package com.eriklievaart.refactor.text;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RegexReplacerU {

	@Test
	public void in() {
		RegexReplacer testable = new RegexReplacer("TODO", "FIXME");
		Assertions.assertThat(testable.in("// TODO go home")).isEqualTo("// FIXME go home");
		Assertions.assertThat(testable.in("// go home")).isEqualTo("// go home");
	}

	@Test
	public void matches() {
		RegexReplacer testable = new RegexReplacer("TODO", "FIXME");
		Assertions.assertThat(testable.matches("// TODO go home")).isEqualTo(true);
		Assertions.assertThat(testable.matches("// FIXME go home")).isEqualTo(false);
	}
}
