package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JButton btnBcher;
	private JButton btnKunden;
	private JTable table;
	private JMenu mnDatei;
	private JMenu mnKunden;
	private JMenu mnBcher;
	private JMenu mnLeihen;
	private JMenuItem mntmDatenbankBenutzen;
	private JMenuItem mntmXmlBenutzen;
	private JSeparator separator;
	private JMenuItem mntmBeenden;
	private JMenuItem mntmKundeErstellen;
	private JSeparator separator_1;
	private JMenuItem mntmKundeBearbeiten;
	private JMenuItem mntmKundeSuchen;
	private JMenuItem mntmKundeLschen;
	private JSeparator separator_2;
	private JMenuItem mntmAusleihvorgngeAnzeigen;
	private JMenuItem mntmBuchErstellen;
	private JMenuItem mntmBuchSuchen;
	private JMenuItem mntmBuchBearbeiten;
	private JSeparator separator_3;
	private JMenuItem mntmBuchLschen;
	private JMenuItem menuItem_4;
	private JSeparator separator_4;
	private JMenuItem menuItem_5;
	private JMenuItem menuItem_6;
	private JMenuItem menuItem_7;
	private JSeparator separator_5;
	private JMenuItem menuItem_8;
	private JMenuItem menuItem_9;
	private JSeparator separator_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		mntmDatenbankBenutzen = new JMenuItem("Datenbank Benutzen");
		mnDatei.add(mntmDatenbankBenutzen);
		
		mntmXmlBenutzen = new JMenuItem("XML Benutzen");
		mnDatei.add(mntmXmlBenutzen);
		
		separator = new JSeparator();
		mnDatei.add(separator);
		
		mntmBeenden = new JMenuItem("Beenden");
		mnDatei.add(mntmBeenden);
		
		mnKunden = new JMenu("Kunden");
		menuBar.add(mnKunden);
		
		mntmKundeErstellen = new JMenuItem("Kunde Erstellen");
		mnKunden.add(mntmKundeErstellen);
		
		mntmKundeSuchen = new JMenuItem("Kunde Suchen");
		mnKunden.add(mntmKundeSuchen);
		
		separator_1 = new JSeparator();
		mnKunden.add(separator_1);
		
		mntmKundeBearbeiten = new JMenuItem("Kunde Bearbeiten");
		mnKunden.add(mntmKundeBearbeiten);
		
		mntmKundeLschen = new JMenuItem("Kunde L\u00F6schen");
		mnKunden.add(mntmKundeLschen);
		
		separator_2 = new JSeparator();
		mnKunden.add(separator_2);
		
		mntmAusleihvorgngeAnzeigen = new JMenuItem("Ausleihvorg\u00E4nge anzeigen");
		mnKunden.add(mntmAusleihvorgngeAnzeigen);
		
		mnBcher = new JMenu("B\u00FCcher");
		menuBar.add(mnBcher);
		
		mntmBuchErstellen = new JMenuItem("Buch Erstellen");
		mnBcher.add(mntmBuchErstellen);
		
		mntmBuchSuchen = new JMenuItem("Buch Suchen");
		mnBcher.add(mntmBuchSuchen);
		
		separator_3 = new JSeparator();
		mnBcher.add(separator_3);
		
		mntmBuchBearbeiten = new JMenuItem("Buch Bearbeiten");
		mnBcher.add(mntmBuchBearbeiten);
		
		mntmBuchLschen = new JMenuItem("Buch L\u00F6schen");
		mnBcher.add(mntmBuchLschen);
		
		separator_4 = new JSeparator();
		mnBcher.add(separator_4);
		
		menuItem_4 = new JMenuItem("Ausleihvorg\u00E4nge anzeigen");
		mnBcher.add(menuItem_4);
		
		mnLeihen = new JMenu("Leihen");
		menuBar.add(mnLeihen);
		
		menuItem_5 = new JMenuItem("Kunde Erstellen");
		mnLeihen.add(menuItem_5);
		
		menuItem_6 = new JMenuItem("Kunde Suchen");
		mnLeihen.add(menuItem_6);
		
		separator_5 = new JSeparator();
		mnLeihen.add(separator_5);
		
		menuItem_7 = new JMenuItem("Kunde Bearbeiten");
		mnLeihen.add(menuItem_7);
		
		menuItem_8 = new JMenuItem("Kunde L\u00F6schen");
		mnLeihen.add(menuItem_8);
		
		separator_6 = new JSeparator();
		mnLeihen.add(separator_6);
		
		menuItem_9 = new JMenuItem("Ausleihvorg\u00E4nge anzeigen");
		mnLeihen.add(menuItem_9);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridwidth = 3;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 8;
		gbc_textField.gridy = 0;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnKunden = new JButton("Kunden");
		GridBagConstraints gbc_btnKunden = new GridBagConstraints();
		gbc_btnKunden.insets = new Insets(0, 0, 5, 5);
		gbc_btnKunden.gridx = 9;
		gbc_btnKunden.gridy = 1;
		frame.getContentPane().add(btnKunden, gbc_btnKunden);
		
		btnBcher = new JButton("B\u00FCcher");
		GridBagConstraints gbc_btnBcher = new GridBagConstraints();
		gbc_btnBcher.insets = new Insets(0, 0, 5, 0);
		gbc_btnBcher.gridx = 10;
		gbc_btnBcher.gridy = 1;
		frame.getContentPane().add(btnBcher, gbc_btnBcher);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 11;
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 2;
		frame.getContentPane().add(table, gbc_table);
	}

}

