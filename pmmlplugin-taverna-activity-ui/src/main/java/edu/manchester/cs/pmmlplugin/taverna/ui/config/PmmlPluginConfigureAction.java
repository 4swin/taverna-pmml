package edu.manchester.cs.pmmlplugin.taverna.ui.config;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import net.sf.taverna.t2.workbench.ui.actions.activity.ActivityConfigurationAction;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ActivityConfigurationDialog;

import edu.manchester.cs.pmmlplugin.taverna.PmmlPluginActivity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlPluginActivityConfigurationBean;

@SuppressWarnings("serial")
public class PmmlPluginConfigureAction
		extends
		ActivityConfigurationAction<PmmlPluginActivity,
        PmmlPluginActivityConfigurationBean> {

	public PmmlPluginConfigureAction(PmmlPluginActivity activity, Frame owner) {
		super(activity);
	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		ActivityConfigurationDialog<PmmlPluginActivity, PmmlPluginActivityConfigurationBean> currentDialog = ActivityConfigurationAction
				.getDialog(getActivity());
		if (currentDialog != null) {
			currentDialog.toFront();
			return;
		}
		PmmlPluginConfigurationPanel panel = new PmmlPluginConfigurationPanel(
				getActivity());
		ActivityConfigurationDialog<PmmlPluginActivity,
        PmmlPluginActivityConfigurationBean> dialog = new ActivityConfigurationDialog<PmmlPluginActivity, PmmlPluginActivityConfigurationBean>(
				getActivity(), panel);

		ActivityConfigurationAction.setDialog(getActivity(), dialog);

	}

}
