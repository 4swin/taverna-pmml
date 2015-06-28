package edu.manchester.cs.pmmlplugin.taverna.ui.config;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import net.sf.taverna.t2.workbench.ui.actions.activity.ActivityConfigurationAction;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ActivityConfigurationDialog;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreLocalActivity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreLocalActivityConfigurationBean;

@SuppressWarnings("serial")
public class PmmlScoreLocalConfigureAction
		extends
		ActivityConfigurationAction<PmmlScoreLocalActivity,
        PmmlScoreLocalActivityConfigurationBean> {

	public PmmlScoreLocalConfigureAction(PmmlScoreLocalActivity activity, Frame owner) {
		super(activity);
	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		ActivityConfigurationDialog<PmmlScoreLocalActivity, PmmlScoreLocalActivityConfigurationBean> currentDialog = ActivityConfigurationAction
				.getDialog(getActivity());
		if (currentDialog != null) {
			currentDialog.toFront();
			return;
		}
		PmmlScoreLocalConfigurationPanel panel = new PmmlScoreLocalConfigurationPanel(
				getActivity());
		ActivityConfigurationDialog<PmmlScoreLocalActivity,
        PmmlScoreLocalActivityConfigurationBean> dialog = new ActivityConfigurationDialog<PmmlScoreLocalActivity, PmmlScoreLocalActivityConfigurationBean>(
				getActivity(), panel);

		ActivityConfigurationAction.setDialog(getActivity(), dialog);

	}

}
