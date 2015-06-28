package edu.manchester.cs.pmmlplugin.taverna.ui.config;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ActivityConfigurationPanel;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreLocalActivity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreLocalActivityConfigurationBean;


@SuppressWarnings("serial")
public class PmmlScoreLocalConfigurationPanel
		extends
		ActivityConfigurationPanel<PmmlScoreLocalActivity, 
        PmmlScoreLocalActivityConfigurationBean> {

	private PmmlScoreLocalActivity activity;
	private PmmlScoreLocalActivityConfigurationBean configBean;
	
	//private JTextField fieldString;
	//private JTextField fieldURI;
	
	String[] inputScoreEngine = {"JPMML-Evaluator"};
	private JComboBox comboScoreEngine;
	
	public PmmlScoreLocalConfigurationPanel(PmmlScoreLocalActivity activity) {
		this.activity = activity;
		initGui();
	}

	protected void initGui() {
		removeAll();
		setLayout(new GridLayout(0, 2));

		// FIXME: Create GUI depending on activity configuration bean
		JLabel labelString = new JLabel("Score Engine:");
		add(labelString);
		comboScoreEngine = new JComboBox(inputScoreEngine);
		add(comboScoreEngine);
		labelString.setLabelFor(comboScoreEngine);
		comboScoreEngine.setSelectedIndex(1);
		
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
	public PmmlScoreLocalActivityConfigurationBean getConfiguration() {
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
		String originalScoreEngine = configBean.getScoreEngine();
		
		// true (changed) unless all fields match the originals
		/*return ! (originalString.equals(fieldString.getText())
				&& originalUri.equals(fieldURI.getText())
				&& originalPmmlFile.equals(fieldPmml.getText())
				);*/
		return ! (originalScoreEngine.equals((String)comboScoreEngine.getSelectedItem())
				);
	}

	/**
	 * Prepare a new configuration bean from the UI, to be returned with
	 * getConfiguration()
	 */
	@Override
	public void noteConfiguration() {
		configBean = new PmmlScoreLocalActivityConfigurationBean();
		
		// FIXME: Update bean fields from your UI elements
		//configBean.setExampleString(fieldString.getText());
		//configBean.setExampleUri(URI.create(fieldURI.getText()));
		configBean.setScoreEngine((String)comboScoreEngine.getSelectedItem());
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
		comboScoreEngine.setSelectedItem(configBean.getScoreEngine());
	}
}
