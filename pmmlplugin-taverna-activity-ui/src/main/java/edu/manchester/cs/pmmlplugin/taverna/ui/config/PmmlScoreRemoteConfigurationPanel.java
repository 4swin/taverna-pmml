package edu.manchester.cs.pmmlplugin.taverna.ui.config;

import java.awt.GridLayout;
import java.net.URI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ActivityConfigurationPanel;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreRemoteActivity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreRemoteActivityConfigurationBean;


@SuppressWarnings("serial")
public class PmmlScoreRemoteConfigurationPanel
		extends
		ActivityConfigurationPanel<PmmlScoreRemoteActivity, 
        PmmlScoreRemoteActivityConfigurationBean> {

	private PmmlScoreRemoteActivity activity;
	private PmmlScoreRemoteActivityConfigurationBean configBean;
	
	private JTextField fieldURI;
	
	public PmmlScoreRemoteConfigurationPanel(PmmlScoreRemoteActivity activity) {
		this.activity = activity;
		initGui();
	}

	protected void initGui() {
		removeAll();
		setLayout(new GridLayout(0, 2));

		// FIXME: Create GUI depending on activity configuration bean
		JLabel labelString = new JLabel("Base URI:");
		add(labelString);
		
		fieldURI = new JTextField();
		add(fieldURI);
		
		labelString.setLabelFor(fieldURI);
		
		// Populate fields from activity configuration bean
		refreshConfiguration();
	}

	/**
	 * Check that user values in UI are valid
	 */
	@Override
	public boolean checkValues() {
		try {
			URI.create(fieldURI.getText());
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getCause().getMessage(),
					"Invalid URI", JOptionPane.ERROR_MESSAGE);
			// Not valid, return false
			return false;
		}
		// All valid, return true
		return true;
	}

	/**
	 * Return configuration bean generated from user interface last time
	 * noteConfiguration() was called.
	 */
	@Override
	public PmmlScoreRemoteActivityConfigurationBean getConfiguration() {
		// Should already have been made by noteConfiguration()
		return configBean;
	}

	/**
	 * Check if the user has changed the configuration from the original
	 */
	@Override
	public boolean isConfigurationChanged() {
		String originalUri = configBean.getBaseURI().toASCIIString();
		
		// true (changed) unless all fields match the originals
		return ! (originalUri.equals(fieldURI.getText()));
	}

	/**
	 * Prepare a new configuration bean from the UI, to be returned with
	 * getConfiguration()
	 */
	@Override
	public void noteConfiguration() {
		configBean = new PmmlScoreRemoteActivityConfigurationBean();
		
		// FIXME: Update bean fields from your UI elements
		configBean.setBaseURI(URI.create(fieldURI.getText()));
	}

	/**
	 * Update GUI from a changed configuration bean (perhaps by undo/redo).
	 * 
	 */
	@Override
	public void refreshConfiguration() {
		configBean = activity.getConfiguration();
		
		// FIXME: Update UI elements from your bean fields
		fieldURI.setText(configBean.getBaseURI().toASCIIString());
	}
}
