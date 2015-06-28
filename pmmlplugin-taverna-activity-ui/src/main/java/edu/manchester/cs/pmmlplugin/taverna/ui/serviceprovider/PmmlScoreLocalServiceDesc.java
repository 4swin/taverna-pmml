package edu.manchester.cs.pmmlplugin.taverna.ui.serviceprovider;

import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;

import net.sf.taverna.t2.servicedescriptions.ServiceDescription;
import net.sf.taverna.t2.workflowmodel.processor.activity.Activity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreLocalActivity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreLocalActivityConfigurationBean;

public class PmmlScoreLocalServiceDesc extends ServiceDescription<PmmlScoreLocalActivityConfigurationBean> {

	private String scoreEngine;
	
	/**
	 * The subclass of Activity which should be instantiated when adding a service
	 * for this description 
	 */
	@Override
	public Class<? extends Activity<PmmlScoreLocalActivityConfigurationBean>> getActivityClass() {
		return PmmlScoreLocalActivity.class;
	}

	/**
	 * The configuration bean which is to be used for configuring the instantiated activity.
	 * Making this bean will typically require some of the fields set on this service
	 * description, like an endpoint URL or method name. 
	 * 
	 */
	@Override
	public PmmlScoreLocalActivityConfigurationBean getActivityConfiguration() {
		PmmlScoreLocalActivityConfigurationBean bean = new PmmlScoreLocalActivityConfigurationBean();
		/*bean.setExampleString(exampleString);
		bean.setExampleUri(exampleUri);*/
		bean.setScoreEngine(scoreEngine);
		return bean;
	}

	/**
	 * An icon to represent this service description in the service palette.
	 */
	@Override
	public Icon getIcon() {
		return PmmlScoreLocalServiceIcon.getIcon();
	}

	/**
	 * The display name that will be shown in service palette and will
	 * be used as a template for processor name when added to workflow.
	 */
	@Override
	public String getName() {
		return "PMML Local Scoring"; //exampleString;
	}

	public String getIdName() {
		return "pmmllocalscoring"; //exampleString;
	}
	
	/**
	 * The path to this service description in the service palette. Folders
	 * will be created for each element of the returned path.
	 */
	@Override
	public List<String> getPath() {
		// For deeper paths you may return several strings
		//return Arrays.asList("PmmlPlugins " + exampleUri);
		return Arrays.asList("PMML plugins");
	}

	/**
	 * Return a list of data values uniquely identifying this service
	 * description (to avoid duplicates). Include only primary key like fields,
	 * ie. ignore descriptions, icons, etc.
	 */
	@Override
	protected List<? extends Object> getIdentifyingData() {
		// FIXME: Use your fields instead of example fields
		//return Arrays.<Object>asList(exampleString, exampleUri);
		return Arrays.<Object>asList("pmmlplugin", "standard", this.getIdName());
	}

	
	// FIXME: Replace example fields and getters/setters with any required
	// and optional fields. (All fields are searchable in the Service palette,
	// for instance try a search for exampleString:3)
	public String getScoreEngine() {
		return scoreEngine;
	}
	
	public void setScoreEngine(String scoreEngine) {
		this.scoreEngine = scoreEngine;
	}
	
	/*private String exampleString;
	private URI exampleUri;
	public String getExampleString() {
		return exampleString;
	}
	public URI getExampleUri() {
		return exampleUri;
	}
	public void setExampleString(String exampleString) {
		this.exampleString = exampleString;
	}
	public void setExampleUri(URI exampleUri) {
		this.exampleUri = exampleUri;
	}*/

}
