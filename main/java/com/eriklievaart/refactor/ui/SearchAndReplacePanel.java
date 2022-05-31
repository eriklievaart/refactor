package com.eriklievaart.refactor.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.eriklievaart.refactor.file.FileSearchAndReplace;
import com.eriklievaart.refactor.file.Settings;
import com.eriklievaart.refactor.text.RegexReplacer;
import com.eriklievaart.refactor.text.Replacer;
import com.eriklievaart.refactor.text.StringReplacer;

public class SearchAndReplacePanel extends JPanel {

	private JTextField rootField = new JTextField();
	private JTextField extField = new JTextField();
	private JTextField excludeField = new JTextField();
	private JTextField findField = new JTextField();
	private JTextField replaceField = new JTextField();
	private JRadioButton unchangedRadio = new JRadioButton("unchanged");
	private JRadioButton unixRadio = new JRadioButton("unix");
	private JRadioButton windowsRadio = new JRadioButton("windows");
	private JCheckBox regexCheckBox = new JCheckBox("regex");

	private JButton searchButton = new JButton("Search");
	private JButton replaceButton = new JButton("Replace");

	public SearchAndReplacePanel() {
		super(new GridLayout(0, 2));

		loadSettings();
		addComponents();

		searchButton.addActionListener(createSearchAction());
		replaceButton.addActionListener(createReplaceAction());
	}

	private void loadSettings() {
		List<String> settings = Settings.load();
		rootField.setText(settings.get(0));
		extField.setText(settings.get(1));
		excludeField.setText(settings.size() < 3 ? "" : settings.get(2));
	}

	private void addComponents() {
		addComponentWithLabel("Root dir:", rootField);
		addComponentWithLabel("File Extensions:", extField);
		addComponentWithLabel("Exclude:", excludeField);
		addComponentWithLabel("Find:", findField);
		addComponentWithLabel("Replace with:", replaceField);
		addComponentWithLabel("Line endings:", createLineEndingsPanel());
		addComponentWithLabel("Regex:", regexCheckBox);
		add(searchButton);
		add(replaceButton);
	}

	private void addComponentWithLabel(String label, JComponent component) {
		add(new JLabel(label));
		add(component);
	}

	private JPanel createLineEndingsPanel() {
		JPanel lePanel = new JPanel(new GridLayout(1, 0));

		lePanel.add(unchangedRadio);
		lePanel.add(unixRadio);
		lePanel.add(windowsRadio);

		ButtonGroup group = new ButtonGroup();
		group.add(unchangedRadio);
		group.add(unixRadio);
		group.add(windowsRadio);

		unchangedRadio.setSelected(true);
		return lePanel;
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
						textSearch();
					}
				} catch (IOException ioe) {
					throw new Error(ioe);
				}
				storeSettings();
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
						textSearchAndReplace();
					}
				} catch (IOException ioe) {
					throw new Error(ioe);
				}
				storeSettings();
			}
		};
	}

	private void storeSettings() {
		Settings.store(rootField.getText(), extField.getText(), excludeField.getText());
	}

	private void textSearch() throws IOException {
		new FileSearchAndReplace(createRoot(), extField.getText(), excludeField.getText()).search(getReplacer());
	}

	private void textSearchAndReplace() throws IOException {
		FileSearchAndReplace tsar = new FileSearchAndReplace(createRoot(), extField.getText(), excludeField.getText());
		tsar.replace(getReplacer(), unchangedRadio.isSelected() ? null : unixRadio.isSelected());
	}

	private Replacer getReplacer() {
		if (regexCheckBox.isSelected()) {
			return new RegexReplacer(findField.getText(), replaceField.getText());
		} else {
			return new StringReplacer(findField.getText(), replaceField.getText());
		}
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

	public void setSearchRoot(File root) {
		rootField.setText(root.getAbsolutePath());
	}
}
