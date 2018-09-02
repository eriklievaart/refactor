package com.eriklievaart.refactor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileScanner {

	private File root;
	private Set<String> ext;
	private Set<String> excludes = new HashSet<>();

	public FileScanner(File root, String extensions) {
		this.root = root;
		this.ext = splitCommaSpace(extensions);
	}

	public List<File> getFiles() {
		return getFiles(root, ext);
	}

	private List<File> getFiles(File file, Set<String> ext) {
		if (excludes.contains(file.getName())) {
			return Collections.emptyList();
		}
		if (file.isFile()) {
			if (ext.isEmpty() || ext.contains(getExtension(file.getName()))) {
				return Arrays.asList(file);
			}
		}
		List<File> files = new ArrayList<File>();
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				if (!child.getName().equals(".hg")) {
					files.addAll(getFiles(child, ext));
				}
			}
		}
		return files;
	}

	private Set<String> splitCommaSpace(String raw) {
		Set<String> set = new HashSet<String>();
		for (String entry : raw.split("[, ]++")) {
			if (entry.trim().length() > 0) {
				set.add(entry.trim());
			}
		}
		return set;
	}

	static String getExtension(String name) {
		return name.replaceAll("[^.]++[.]", "");
	}

	public FileScanner exclude(String raw) {
		this.excludes.addAll(splitCommaSpace(raw));
		return this;
	}
}
