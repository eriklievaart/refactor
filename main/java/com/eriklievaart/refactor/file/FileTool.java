package com.eriklievaart.refactor.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileTool {

	public static final String UNIX_NEWLINE = "\n";
	public static final String WINDOWS_NEWLINE = "\r\n";

	public static List<File> listFiles(File file) {
		List<File> files = new ArrayList<>();

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
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {
				lines.add(line);
				line = br.readLine();
			}
		}
		return lines;
	}

	public static String replaceNewLines(String replace, boolean unix) {
		String[] lines = replace.split("\r?\n");
		return String.join(unix ? UNIX_NEWLINE : WINDOWS_NEWLINE, lines);
	}

	public static String readFileToString(File file) throws IOException {
		try (Scanner scanner = new Scanner(new FileInputStream(file))) {
			scanner.useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
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
