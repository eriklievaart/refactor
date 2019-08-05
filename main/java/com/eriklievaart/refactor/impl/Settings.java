package com.eriklievaart.refactor.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Settings {

	public static List<String> load() {
		File file = getSettingsFile();
		if (file.isFile()) {
			try {
				List<String> lines = FileTool.readLines(file);
				return lines.stream().map(s -> s.replaceAll("\\s++", " ")).collect(Collectors.toList());

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		String home = System.getProperty("user.home");
		String extensions = "java js json html css xml xsd dtd properties txt kt";
		return Arrays.asList(home, extensions, "");
	}

	public static void store(String root, String extensions, String excludes) {
		File file = getSettingsFile();
		file.getParentFile().mkdirs();
		try {
			FileTool.writeStringToFile(file, String.join("\n", root.trim(), extensions.trim(), excludes.trim()));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static File getSettingsFile() {
		return new File(getJarDirOrClassDir(), "data/settings.properties");
	}

	public static String getJarDirOrClassDir() {
		File file = new File(Settings.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		if (file.isFile()) {
			return file.getParentFile().getAbsolutePath();
		}
		return file.getAbsolutePath();
	}
}
