package edu.manchester.cs.pmmlplugin.taverna.ui.serviceprovider;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import edu.manchester.cs.pmmlplugin.taverna.ui.serviceprovider.PmmlPluginServiceDesc;
import edu.manchester.cs.pmmlplugin.taverna.ui.serviceprovider.PmmlScoreLocalServiceDesc;

import net.sf.taverna.t2.servicedescriptions.ServiceDescription;
import net.sf.taverna.t2.servicedescriptions.ServiceDescriptionProvider;

public class PmmlPluginServiceProvider implements ServiceDescriptionProvider {
	
	private static final URI providerId = URI
		.create("http://example.com/2011/service-provider/pmmlplugin-taverna");
	
	/**
	 * Do the actual search for services. Return using the callBack parameter.
	 */
	@SuppressWarnings("unchecked")
	public void findServiceDescriptionsAsync(
			FindServiceDescriptionsCallBack callBack) {
		// Use callback.status() for long-running searches
		// callBack.status("Resolving example services");

		List<ServiceDescription> results = new ArrayList<ServiceDescription>();

		// FIXME: Implement the actual service search/lookup instead
		// of dummy for-loop
		/*for (int i = 1; i <= 5; i++) {
			PmmlPluginServiceDesc service = new PmmlPluginServiceDesc();
			// Populate the service description bean
			service.setExampleString("Example " + i);
			service.setExampleUri(URI.create("http://localhost:8192/service"));

			// Optional: set description
			service.setDescription("Service example number " + i);
			results.add(service);
		}*/

		PmmlPluginServiceDesc service1 = new PmmlPluginServiceDesc();
		
		// Populate the service description bean
		service1.setPmmlFile("Unassigned");
		
		service1.setDescription("Import a valid PMML document and extract the provenance information");
		results.add(service1);
		
		PmmlScoreLocalServiceDesc service2 = new PmmlScoreLocalServiceDesc();
		
		// Populate the service description bean
		// Default scoring engine is JPMML-Evaluator on user's local machine
		service2.setScoreEngine("JPMML-Evaluator");
		
		service2.setDescription("Score input dataset based on the PMML model using Java library (JPMML)");
		results.add(service2);
		
		PmmlScoreRemoteServiceDesc service3 = new PmmlScoreRemoteServiceDesc();
		service3.setBaseURI(URI.create("http://localhost:8080/openscoring/model"));
		
		service3.setDescription("Score input dataset based on the PMML model using REST API");
		results.add(service3);
		
		// partialResults() can also be called several times from inside
		// for-loop if the full search takes a long time
		callBack.partialResults(results);

		// No more results will be coming
		callBack.finished();
	}

	/**
	 * Icon for service provider
	 */
	public Icon getIcon() {
		return PmmlPluginServiceIcon.getIcon();
	}

	/**
	 * Name of service provider, appears in right click for 'Remove service
	 * provider'
	 */
	public String getName() {
		return "My example service";
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public String getId() {
		return providerId.toASCIIString();
	}

}
