package edu.manchester.cs.pmmlplugin.taverna.ui.view;

import java.util.Arrays;
import java.util.List;

import net.sf.taverna.t2.workbench.ui.views.contextualviews.ContextualView;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ContextualViewFactory;
import edu.manchester.cs.pmmlplugin.taverna.PmmlPluginActivity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreLocalActivity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreRemoteActivity;
import edu.manchester.cs.pmmlplugin.taverna.ui.view.PmmlPluginContextualView;
import edu.manchester.cs.pmmlplugin.taverna.ui.view.PmmlScoreLocalContextualView;

public class PmmlPluginActivityContextViewFactory implements
		//ContextualViewFactory<PmmlPluginActivity> {
		ContextualViewFactory {

	public boolean canHandle(Object selection) {
		//return selection instanceof PmmlPluginActivity;
		
		if(selection instanceof PmmlPluginActivity)
			return true;
		else if(selection instanceof PmmlScoreLocalActivity)
			return true;
		else if(selection instanceof PmmlScoreRemoteActivity)
			return true;
		else
			return false;
	}

	public List<ContextualView> getViews(PmmlPluginActivity selection) {
		return Arrays.<ContextualView>asList(new PmmlPluginContextualView(selection));
	}
	
	public List<ContextualView> getViews(PmmlScoreLocalActivity selection) {
		return Arrays.<ContextualView>asList(new PmmlScoreLocalContextualView(selection));
	}
	
	public List<ContextualView> getViews(PmmlScoreRemoteActivity selection) {
		return Arrays.<ContextualView>asList(new PmmlScoreRemoteContextualView(selection));
	}
	
	@Override
	public List<ContextualView> getViews(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 instanceof PmmlPluginActivity)
			return getViews((PmmlPluginActivity) arg0);
		else if(arg0 instanceof PmmlScoreLocalActivity)
			return getViews((PmmlScoreLocalActivity) arg0);
		else if(arg0 instanceof PmmlScoreRemoteActivity)
			return getViews((PmmlScoreRemoteActivity) arg0);
		else 
			return null;
	}
}
