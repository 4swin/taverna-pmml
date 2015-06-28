package edu.manchester.cs.pmmlplugin.taverna;

import java.util.ArrayList;
import java.util.List;

import net.sf.taverna.t2.visit.VisitReport;
import net.sf.taverna.t2.visit.VisitReport.Status;
import net.sf.taverna.t2.workflowmodel.health.HealthCheck;
import net.sf.taverna.t2.workflowmodel.health.HealthChecker;

/**
 * PmmlScoreRemote health checker
 * 
 */
public class PmmlScoreRemoteActivityHealthChecker implements
		HealthChecker<PmmlScoreRemoteActivity> {

	public boolean canVisit(Object o) {
		// Return True if we can visit the object. We could do
		// deeper (but not time consuming) checks here, for instance
		// if the health checker only deals with PmmlPluginActivity where
		// a certain configuration option is enabled.
		return o instanceof PmmlScoreRemoteActivity;
	}

	public boolean isTimeConsuming() {
		// Return true if the health checker does a network lookup
		// or similar time consuming checks, in which case
		// it would only be performed when using File->Validate workflow
		// or File->Run.
		return false;
	}

	public VisitReport visit(PmmlScoreRemoteActivity activity, List<Object> ancestry) {
		PmmlScoreRemoteActivityConfigurationBean config = activity.getConfiguration();

		// We'll build a list of subreports
		List<VisitReport> subReports = new ArrayList<VisitReport>();

		// Check if configuration bean is properly filled
		if (config.getBaseURI().equals("")) {
			// Error: REST server is not set
			subReports.add(new VisitReport(HealthCheck.getInstance(), activity,
					"REST server is empty", HealthCheck.NO_CONFIGURATION,
					Status.SEVERE));
		}
		
		// The default explanation here will be used if the subreports list is
		// empty
		return new VisitReport(HealthCheck.getInstance(), activity,
				"Pmml Score Remote service OK", HealthCheck.NO_PROBLEM, subReports);
	}

}
