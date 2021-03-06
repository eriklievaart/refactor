package com.eriklievaart.refactor.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TextSearchAndReplace {

	private final File root;
	private final String extensions;
	private String excludes;

	public TextSearchAndReplace(File root, String extensions, String exclude) {
		Objects.requireNonNull(root);
		Objects.requireNonNull(extensions);
		Objects.requireNonNull(exclude);

		this.root = root;
		this.extensions = extensions;
		this.excludes = exclude;
	}

	public void search(final String find) throws IOException {
		System.out.println("\t");
		System.out.println("searching: " + find);

		for (File file : listFiles()) {
			List<String> lines = FileTool.readLines(file);
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				if (line.contains(find)) {
					System.out.println(getRelativePath(file) + "(" + (i + 1) + "): " + line);
				}
			}
		}
		System.out.println("complete!");
	}

	public void replace(final String find, final String replace, boolean unix) throws IOException {
		System.out.println("\t");
		System.out.println("replacing : " + find + " => " + replace);

		for (File file : listFiles()) {
			String contents = FileTool.readFileToString(file);
			String replaced = FileTool.replaceNewLines(contents.replace(find, replace), unix);

			if (!replaced.equals(contents)) {
				FileTool.writeStringToFile(file, replaced);
				System.out.println("File has been modified: " + getRelativePath(file));
			}
		}
		System.out.println("complete!");
	}

	private String getRelativePath(File file) {
		return file.getAbsolutePath().substring(root.getAbsolutePath().length() + 1);
	}

	private List<File> listFiles() {
		return new FileScanner(root, extensions).exclude(excludes).getFiles();
	}
}
