package com.eriklievaart.refactor.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class LookAndFeel {
	private static Color dark = new Color(64, 64, 64);
	private static Color text = new Color(236, 236, 236);
	private static Color caret = new Color(255, 255, 0);
	private static Color input = new Color(48, 71, 94);

	public static void init() {
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		setupColors(defaults);
		setupFonts(defaults);
	}

	private static void setupFonts(UIDefaults defaults) {
		Font font = Font.decode("ubuntu-18");
		defaults.put("Button.font", font);
		defaults.put("TextField.font", font);
		defaults.put("Label.font", font);
		defaults.put("RadioButton.font", font);
	}

	private static void setupColors(UIDefaults defaults) {
		defaults.put("Label.background", dark);
		defaults.put("Label.foreground", text);
		defaults.put("Panel.background", dark);
		defaults.put("Panel.foreground", text);
		defaults.put("TextField.background", input);
		defaults.put("TextField.foreground", text);
		defaults.put("TextField.caretForeground", caret);
		defaults.put("RadioButton.background", input);
		defaults.put("RadioButton.foreground", text);
		defaults.put("CheckBox.background", input);
		defaults.put("CheckBox.foreground", text);
		defaults.put("Button.background", Color.gray);
	}
}
