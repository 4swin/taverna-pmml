package edu.manchester.cs.pmmlplugin.taverna.ui.menu;

import javax.swing.Action;

import net.sf.taverna.t2.workbench.activitytools.AbstractConfigureActivityMenuAction;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreLocalActivity;
import edu.manchester.cs.pmmlplugin.taverna.ui.config.PmmlScoreLocalConfigureAction;

public class PmmlScoreLocalConfigureMenuAction extends
		AbstractConfigureActivityMenuAction<PmmlScoreLocalActivity> {

	public PmmlScoreLocalConfigureMenuAction() {
		super(PmmlScoreLocalActivity.class);
	}

	@Override
	protected Action createAction() {
		PmmlScoreLocalActivity a = findActivity();
		Action result = null;
		result = new PmmlScoreLocalConfigureAction(findActivity(),
				getParentFrame());
		result.putValue(Action.NAME, "Configure example service");
		addMenuDots(result);
		return result;
	}

}
