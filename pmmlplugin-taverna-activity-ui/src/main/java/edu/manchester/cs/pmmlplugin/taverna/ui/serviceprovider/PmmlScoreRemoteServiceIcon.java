package edu.manchester.cs.pmmlplugin.taverna.ui.serviceprovider;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import net.sf.taverna.t2.workbench.activityicons.ActivityIconSPI;
import net.sf.taverna.t2.workflowmodel.processor.activity.Activity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreRemoteActivity;

public class PmmlScoreRemoteServiceIcon implements ActivityIconSPI {

	private static Icon icon;

	public int canProvideIconScore(Activity<?> activity) {
		if (activity instanceof PmmlScoreRemoteActivity) {
			return DEFAULT_ICON;
		}
		return NO_ICON;
	}

	public Icon getIcon(Activity<?> activity) {
		return getIcon();
	}
	
	public static Icon getIcon() {
		if (icon == null) {
			icon = new ImageIcon(PmmlScoreRemoteServiceIcon.class.getResource("/Data-Scatter-Plot-icon.png"));
		}
		return icon;
	}

}
