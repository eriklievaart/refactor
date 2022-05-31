package com.eriklievaart.refactor.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Settings {
	private static final String EXTENSIONS = "java js json html css xml xsd dtd properties txt kt";

	public static List<String> load() {
		File file = getSettingsFile();
		if (file.isFile()) {
			try {
				List<String> lines = FileTool.readLines(file);
				if (lines.size() == 3 && lines.get(1).isBlank()) {
					lines.add(1, EXTENSIONS);
					lines.remove(2);
				}
				return lines.stream().map(s -> s.replaceAll("\\s++", " ")).collect(Collectors.toList());

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		String home = System.getProperty("user.home");
		return Arrays.asList(home, EXTENSIONS, "");
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
