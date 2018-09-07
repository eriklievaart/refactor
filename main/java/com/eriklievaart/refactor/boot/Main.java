package com.eriklievaart.refactor.boot;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;

import com.eriklievaart.refactor.impl.SearchAndReplacePanel;

public class Main {

	public static void main(final String[] args) {
		File root = getRootDir(args);
		JFrame frame = new JFrame("Search and replace text");

		SearchAndReplacePanel panel = new SearchAndReplacePanel(root);
		frame.add(panel, BorderLayout.CENTER);
		frame.getRootPane().setDefaultButton(panel.getSearchButton());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(400, 400, 600, 140);
		frame.setVisible(true);
	}

	private static File getRootDir(String[] args) {
		if (args.length > 0) {
			return new File(args[0]);
		}
		return new File("");
	}
}
