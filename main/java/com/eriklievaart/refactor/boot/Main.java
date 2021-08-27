package com.eriklievaart.refactor.boot;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;

import com.eriklievaart.refactor.ui.LookAndFeel;
import com.eriklievaart.refactor.ui.SearchAndReplacePanel;

public class Main {

	public static void main(final String[] args) {
		LookAndFeel.init();

		JFrame frame = new JFrame("Search and replace text");
		SearchAndReplacePanel panel = new SearchAndReplacePanel();
		if (args.length > 0) {
			panel.setSearchRoot(new File(args[0]));
		}
		frame.add(panel, BorderLayout.CENTER);
		frame.getRootPane().setDefaultButton(panel.getSearchButton());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(400, 400, 600, 140);
		frame.setVisible(true);
	}
}
