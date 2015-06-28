package edu.manchester.cs.pmmlplugin.taverna.ui.menu;

import javax.swing.Action;

import net.sf.taverna.t2.workbench.activitytools.AbstractConfigureActivityMenuAction;
import edu.manchester.cs.pmmlplugin.taverna.PmmlPluginActivity;
import edu.manchester.cs.pmmlplugin.taverna.ui.config.PmmlPluginConfigureAction;

public class PmmlPluginConfigureMenuAction extends
		AbstractConfigureActivityMenuAction<PmmlPluginActivity> {

	public PmmlPluginConfigureMenuAction() {
		super(PmmlPluginActivity.class);
	}

	@Override
	protected Action createAction() {
		PmmlPluginActivity a = findActivity();
		Action result = null;
		result = new PmmlPluginConfigureAction(findActivity(),
				getParentFrame());
		result.putValue(Action.NAME, "Configure example service");
		addMenuDots(result);
		return result;
	}

}
