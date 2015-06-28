package edu.manchester.cs.pmmlplugin.taverna.ui.config;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ActivityConfigurationPanel;
import edu.manchester.cs.pmmlplugin.taverna.PmmlPluginActivity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlPluginActivityConfigurationBean;


@SuppressWarnings("serial")
public class PmmlPluginConfigurationPanel
		extends
		ActivityConfigurationPanel<PmmlPluginActivity, 
        PmmlPluginActivityConfigurationBean> {

	private PmmlPluginActivity activity;
	private PmmlPluginActivityConfigurationBean configBean;
	
	//private JTextField fieldString;
	//private JTextField fieldURI;
	
	private JFileChooser fc;
	private JButton buttonOpen;
	private JTextField fieldPmml;

	public PmmlPluginConfigurationPanel(PmmlPluginActivity activity) {
		this.activity = activity;
		initGui();
	}

	protected void initGui() {
		removeAll();
		setLayout(new GridLayout(0, 2));

		// FIXME: Create GUI depending on activity configuration bean
		/*JLabel labelString = new JLabel("Example string:");
		add(labelString);
		fieldString = new JTextField(20);
		add(fieldString);
		labelString.setLabelFor(fieldString);

		JLabel labelURI = new JLabel("Example URI:");
		add(labelURI);
		fieldURI = new JTextField(25);
		add(fieldURI);
		labelURI.setLabelFor(fieldURI);*/
		
		// Allow users select a PMML file
		FileFilter filter = new FileNameExtensionFilter("PMML file","pmml", "xml");
		
		fc = new JFileChooser();
		fc.setFileFilter(filter);
		
		fieldPmml = new JTextField(25);
		
		buttonOpen = new JButton("Open a PMML file...");
		buttonOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == buttonOpen) {
					int returnVal = fc.showOpenDialog(PmmlPluginConfigurationPanel.this);
					
					if (returnVal == JFileChooser.APPROVE_OPTION) {
		                File Pmml = fc.getSelectedFile();
		                fieldPmml.setText(Pmml.getAbsolutePath());
		                //fieldPmml.setText(Pmml.getName());
		                setVisible(true);
		            }
				}
			}
		});
		
		add(buttonOpen);
		add(fieldPmml);
		
		// Populate fields from activity configuration bean
		refreshConfiguration();
	}

	/**
	 * Check that user values in UI are valid
	 */
	@Override
	public boolean checkValues() {
		/*try {
			URI.create(fieldURI.getText());
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getCause().getMessage(),
					"Invalid URI", JOptionPane.ERROR_MESSAGE);
			// Not valid, return false
			return false;
		}*/
		// All valid, return true
		return true;
	}

	/**
	 * Return configuration bean generated from user interface last time
	 * noteConfiguration() was called.
	 */
	@Override
	public PmmlPluginActivityConfigurationBean getConfiguration() {
		// Should already have been made by noteConfiguration()
		return configBean;
	}

	/**
	 * Check if the user has changed the configuration from the original
	 */
	@Override
	public boolean isConfigurationChanged() {
		//String originalString = configBean.getExampleString();
		//String originalUri = configBean.getExampleUri().toASCIIString();
		String originalPmmlFile = configBean.getPmmlFile();
		
		// true (changed) unless all fields match the originals
		/*return ! (originalString.equals(fieldString.getText())
				&& originalUri.equals(fieldURI.getText())
				&& originalPmmlFile.equals(fieldPmml.getText())
				);*/
		return ! (originalPmmlFile.equals(fieldPmml.getText())
				);
	}

	/**
	 * Prepare a new configuration bean from the UI, to be returned with
	 * getConfiguration()
	 */
	@Override
	public void noteConfiguration() {
		configBean = new PmmlPluginActivityConfigurationBean();
		
		// FIXME: Update bean fields from your UI elements
		//configBean.setExampleString(fieldString.getText());
		//configBean.setExampleUri(URI.create(fieldURI.getText()));
		configBean.setPmmlFile(fieldPmml.getText());
	}

	/**
	 * Update GUI from a changed configuration bean (perhaps by undo/redo).
	 * 
	 */
	@Override
	public void refreshConfiguration() {
		configBean = activity.getConfiguration();
		
		// FIXME: Update UI elements from your bean fields
		//fieldString.setText(configBean.getExampleString());
		//fieldURI.setText(configBean.getExampleUri().toASCIIString());
		fieldPmml.setText(configBean.getPmmlFile());
	}
}
