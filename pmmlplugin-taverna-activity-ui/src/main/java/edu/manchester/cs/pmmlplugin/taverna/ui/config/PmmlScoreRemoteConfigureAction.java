package edu.manchester.cs.pmmlplugin.taverna.ui.config;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import net.sf.taverna.t2.workbench.ui.actions.activity.ActivityConfigurationAction;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ActivityConfigurationDialog;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreRemoteActivity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreRemoteActivityConfigurationBean;

@SuppressWarnings("serial")
public class PmmlScoreRemoteConfigureAction
		extends
		ActivityConfigurationAction<PmmlScoreRemoteActivity,
        PmmlScoreRemoteActivityConfigurationBean> {

	public PmmlScoreRemoteConfigureAction(PmmlScoreRemoteActivity activity, Frame owner) {
		super(activity);
	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		ActivityConfigurationDialog<PmmlScoreRemoteActivity, PmmlScoreRemoteActivityConfigurationBean> currentDialog = ActivityConfigurationAction
				.getDialog(getActivity());
		if (currentDialog != null) {
			currentDialog.toFront();
			return;
		}
		PmmlScoreRemoteConfigurationPanel panel = new PmmlScoreRemoteConfigurationPanel(
				getActivity());
		ActivityConfigurationDialog<PmmlScoreRemoteActivity,
        PmmlScoreRemoteActivityConfigurationBean> dialog = new ActivityConfigurationDialog<PmmlScoreRemoteActivity, PmmlScoreRemoteActivityConfigurationBean>(
				getActivity(), panel);

		ActivityConfigurationAction.setDialog(getActivity(), dialog);

	}

}
