package edu.manchester.cs.pmmlplugin.taverna.ui.serviceprovider;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;

import net.sf.taverna.t2.servicedescriptions.ServiceDescription;
import net.sf.taverna.t2.workflowmodel.processor.activity.Activity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreRemoteActivity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreRemoteActivityConfigurationBean;

public class PmmlScoreRemoteServiceDesc extends ServiceDescription<PmmlScoreRemoteActivityConfigurationBean> {

	private URI baseURI;
	
	/**
	 * The subclass of Activity which should be instantiated when adding a service
	 * for this description 
	 */
	@Override
	public Class<? extends Activity<PmmlScoreRemoteActivityConfigurationBean>> getActivityClass() {
		return PmmlScoreRemoteActivity.class;
	}

	/**
	 * The configuration bean which is to be used for configuring the instantiated activity.
	 * Making this bean will typically require some of the fields set on this service
	 * description, like an endpoint URL or method name. 
	 * 
	 */
	@Override
	public PmmlScoreRemoteActivityConfigurationBean getActivityConfiguration() {
		PmmlScoreRemoteActivityConfigurationBean bean = new PmmlScoreRemoteActivityConfigurationBean();
		bean.setBaseURI(baseURI);
		
		return bean;
	}

	/**
	 * An icon to represent this service description in the service palette.
	 */
	@Override
	public Icon getIcon() {
		return PmmlScoreRemoteServiceIcon.getIcon();
	}

	/**
	 * The display name that will be shown in service palette and will
	 * be used as a template for processor name when added to workflow.
	 */
	@Override
	public String getName() {
		return "PMML Remote Scoring"; //exampleString;
	}

	public String getIdName() {
		return "pmmlremotescoring"; //exampleString;
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
	public URI getBaseURI() {
		return baseURI;
	}
	
	public void setBaseURI(URI uri) {
		this.baseURI = uri;
	}

}
