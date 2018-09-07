package com.eriklievaart.refactor.impl;

import java.io.File;
import java.io.IOException;

public class Settings {

	public static String loadExtensions(){
		File file = getSettingsFile();
		if(file.isFile()) {
			try {
				String data = FileTool.readFileToString(file);
				if(data.length() > 0) {
					return data;
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return "java js json html css xml xsd dtd properties txt kt";
	}

	public static void storeExtensions(String value){
		File file = getSettingsFile();
		file.getParentFile().mkdirs();
		try {
			FileTool.writeStringToFile(file, value.trim());
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
