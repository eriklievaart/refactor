package com.eriklievaart.refactor.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileTool {

	public static final String UNIX_NEWLINE = "\n";
	public static final String WINDOWS_NEWLINE = "\r\n";

	public static List<File> listFiles(File file) {
		List<File> files = new ArrayList<File>();

		if (file.isDirectory()) {
			File[] children = file.listFiles();
			for (File child : children) {
				files.addAll(listFiles(child));
			}
		}
		if (file.isFile() && file.getName().endsWith(".java")) {
			files.add(file);
		}
		return files;
	}

	public static List<String> readLines(File file) throws IOException {
		List<String> lines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {
				lines.add(line);
				line = br.readLine();
			}
		}
		return lines;
	}

	public static String readFileToString(File file) throws IOException {
		List<String> lines = readLines(file);
		StringBuilder builder = new StringBuilder();
		for (String line : lines) {
			builder.append(line).append(WINDOWS_NEWLINE);
		}
		return builder.toString();
	}

	public static void writeLines(File file, Collection<String> lines) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		try {
			for (String line : lines) {
				bw.write(line);
				bw.write(WINDOWS_NEWLINE);
			}
		} finally {
			bw.close();
		}
	}

	public static void writeStringToFile(File file, String replaced) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		try {
			bw.write(replaced);
		} finally {
			bw.close();
		}
	}
}
