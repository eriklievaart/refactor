package com.eriklievaart.refactor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchAndReplacePanel extends JPanel {

	private JTextField rootField = new JTextField();
	private JTextField extField = new JTextField();
	private JTextField excludeField = new JTextField();
	private JTextField findField = new JTextField();
	private JTextField replaceField = new JTextField();

	private JButton searchButton = new JButton("Search");
	private JButton replaceButton = new JButton("Replace");

	public SearchAndReplacePanel(File root) {
		super(new GridLayout(0, 2));

		rootField.setText(root.getAbsolutePath());
		extField.setText(Settings.loadExtensions());
		addComponents();

		searchButton.addActionListener(createSearchAction());
		replaceButton.addActionListener(createReplaceAction());
	}

	private void addComponents() {
		add(new JLabel("Root dir:"));
		add(rootField);
		add(new JLabel("File Extensions:"));
		add(extField);
		add(new JLabel("Exclude:"));
		add(excludeField);
		add(new JLabel("Find:"));
		add(findField);
		add(new JLabel("Replace with:"));
		add(replaceField);
		add(searchButton);
		add(replaceButton);
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	private ActionListener createSearchAction() {
		return new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent ae) {
				try {
					String query = findField.getText();
					if (notBlank(query)) {
						textSearchAndReplace().search(query);
						Settings.storeExtensions(extField.getText());
					}
				} catch (IOException ioe) {
					throw new Error(ioe);
				}
			}

		};
	}

	private ActionListener createReplaceAction() {
		return new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent ae) {
				try {
					String query = findField.getText();
					if (notBlank(query)) {
						textSearchAndReplace().replace(findField.getText(), replaceField.getText());
					}
				} catch (IOException ioe) {
					throw new Error(ioe);
				}
			}
		};
	}

	private TextSearchAndReplace textSearchAndReplace() {
		return new TextSearchAndReplace(createRoot(), extField.getText(), excludeField.getText());
	}

	private File createRoot() {
		File file = new File(rootField.getText());
		if (!file.isDirectory()) {
			throw new RuntimeException("Not a directory: " + rootField.getText());
		}
		return file;
	}

	private boolean notBlank(String value) {
		return value != null && value.trim().length() > 0;
	}

}
