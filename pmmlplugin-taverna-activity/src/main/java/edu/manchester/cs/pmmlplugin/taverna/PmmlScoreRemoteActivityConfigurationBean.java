package edu.manchester.cs.pmmlplugin.taverna;

import java.io.Serializable;
import java.net.URI;

/**
 * PmmlScoreRemote activity configuration bean.
 * 
 */
public class PmmlScoreRemoteActivityConfigurationBean implements Serializable {

	private URI baseURI;
	
	public URI getBaseURI() {
		return baseURI;
	}
	
	public void setBaseURI(URI uri) {
		this.baseURI = uri;
	}

}
