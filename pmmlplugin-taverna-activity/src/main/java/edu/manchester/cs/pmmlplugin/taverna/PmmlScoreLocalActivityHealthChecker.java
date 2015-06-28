package edu.manchester.cs.pmmlplugin.taverna;

import java.util.ArrayList;
import java.util.List;

import net.sf.taverna.t2.visit.VisitReport;
import net.sf.taverna.t2.visit.VisitReport.Status;
import net.sf.taverna.t2.workflowmodel.health.HealthCheck;
import net.sf.taverna.t2.workflowmodel.health.HealthChecker;

/**
 * PmmlScoreLocal health checker
 * 
 */
public class PmmlScoreLocalActivityHealthChecker implements
		HealthChecker<PmmlScoreLocalActivity> {

	public boolean canVisit(Object o) {
		// Return True if we can visit the object. We could do
		// deeper (but not time consuming) checks here, for instance
		// if the health checker only deals with PmmlPluginActivity where
		// a certain configuration option is enabled.
		return o instanceof PmmlScoreLocalActivity;
	}

	public boolean isTimeConsuming() {
		// Return true if the health checker does a network lookup
		// or similar time consuming checks, in which case
		// it would only be performed when using File->Validate workflow
		// or File->Run.
		return false;
	}

	public VisitReport visit(PmmlScoreLocalActivity activity, List<Object> ancestry) {
		PmmlScoreLocalActivityConfigurationBean config = activity.getConfiguration();

		// We'll build a list of subreports
		List<VisitReport> subReports = new ArrayList<VisitReport>();

		// Check if configuration bean is properly filled
		if (config.getScoreEngine().equals("")) {
			// Warning: score engine is not set
			subReports.add(new VisitReport(HealthCheck.getInstance(), activity,
					"Score engine is empty", HealthCheck.NO_CONFIGURATION,
					Status.WARNING));
		}
		
		// The default explanation here will be used if the subreports list is
		// empty
		return new VisitReport(HealthCheck.getInstance(), activity,
				"Pmml Score Local service OK", HealthCheck.NO_PROBLEM, subReports);
	}

}
