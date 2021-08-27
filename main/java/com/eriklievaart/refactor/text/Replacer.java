package com.eriklievaart.refactor.text;

public interface Replacer {

	boolean matches(String line);

	String in(String contents);
}
