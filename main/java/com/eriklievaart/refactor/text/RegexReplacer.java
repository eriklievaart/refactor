package com.eriklievaart.refactor.text;

import java.util.regex.Pattern;

public class RegexReplacer implements Replacer {

	private String find;
	private String replace;
	private Pattern pattern;

	public RegexReplacer(String find, String replace) {
		this.find = find;
		this.replace = replace;
		this.pattern = pattern.compile(find);
	}

	@Override
	public boolean matches(String line) {
		return pattern.matcher(line).find();
	}

	@Override
	public String in(String contents) {
		return pattern.matcher(contents).replaceAll(replace);
	}

	@Override
	public String toString() {
		return "/" + find + "/" + replace + "/";
	}
}
