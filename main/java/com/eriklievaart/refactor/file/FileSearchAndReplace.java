package com.eriklievaart.refactor.file;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.eriklievaart.refactor.text.Replacer;

public class FileSearchAndReplace {

	private final File root;
	private final String extensions;
	private String excludes;

	public FileSearchAndReplace(File root, String extensions, String exclude) {
		Objects.requireNonNull(root);
		Objects.requireNonNull(extensions);
		Objects.requireNonNull(exclude);

		this.root = root;
		this.extensions = extensions;
		this.excludes = exclude;
	}

	public void search(final Replacer replacer) throws IOException {
		System.out.println("\t");
		System.out.println("searching: " + replacer);

		for (File file : listFiles()) {
			List<String> lines = FileTool.readLines(file);
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				if (replacer.matches(line)) {
					System.out.println(getRelativePath(file) + "(" + (i + 1) + "): " + line);
				}
			}
		}
		System.out.println("complete!");
	}

	public void replace(Replacer replace, Boolean unix) throws IOException {
		System.out.println("\t");
		System.out.println("replacing : " + replace.toString());

		for (File file : listFiles()) {
			String original = FileTool.readFileToString(file);
			String replaced = replace.in(original);
			if (unix != null) {
				replaced = FileTool.replaceNewLines(replaced, unix);
			}
			if (!replaced.equals(original)) {
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
