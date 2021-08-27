package com.eriklievaart.refactor.text;

public class StringReplacer implements Replacer {

	private String find;
	private String replace;

	public StringReplacer(String find, String replace) {
		this.find = find;
		this.replace = replace;
	}

	@Override
	public boolean matches(String line) {
		return line.contains(find);
	}

	@Override
	public String in(String contents) {
		return contents.replace(find, replace);
	}

	@Override
	public String toString() {
		return find + " => " + replace;
	}
}
