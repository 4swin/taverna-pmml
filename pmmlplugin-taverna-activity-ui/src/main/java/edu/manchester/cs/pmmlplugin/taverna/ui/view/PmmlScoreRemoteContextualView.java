package edu.manchester.cs.pmmlplugin.taverna.ui.view;


import java.awt.Frame;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JEditorPane;

import net.sf.taverna.t2.workbench.ui.views.contextualviews.ContextualView;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreRemoteActivity;
import edu.manchester.cs.pmmlplugin.taverna.PmmlScoreRemoteActivityConfigurationBean;
import edu.manchester.cs.pmmlplugin.taverna.ui.config.PmmlScoreRemoteConfigureAction;
import static net.sf.taverna.t2.lang.ui.HtmlUtils.buildTableOpeningTag;
import static net.sf.taverna.t2.lang.ui.HtmlUtils.createEditorPane;
import static net.sf.taverna.t2.lang.ui.HtmlUtils.getHtmlHead;
import static net.sf.taverna.t2.lang.ui.HtmlUtils.panelForHtml;

@SuppressWarnings("serial")
public class PmmlScoreRemoteContextualView extends ContextualView {
	private final PmmlScoreRemoteActivity activity;
	//private JLabel description = new JLabel("ads");
	
	private JEditorPane editorPane;

	public PmmlScoreRemoteContextualView(PmmlScoreRemoteActivity activity) {
		this.activity = activity;
		initView();
	}

	@Override
	public JComponent getMainFrame() {
		editorPane = createEditorPane(buildHtml());
		return panelForHtml(editorPane);
	}
	
	/*@Override
	public JComponent getMainFrame() {
		JPanel jPanel = new JPanel();
		jPanel.add(description);
		refreshView();
		return jPanel;
	}*/

	@Override
	public String getViewTitle() {
		PmmlScoreRemoteActivityConfigurationBean configuration = activity
				.getConfiguration();
		return "Pmml Remote Scoring";// + configuration.getExampleString();
	}

	/**
	 * Typically called when the activity configuration has changed.
	 */
	/*@Override
	public void refreshView() {
		PmmlScoreRemoteActivityConfigurationBean configuration = activity
				.getConfiguration();
		description.setText("PmmlPlugin service " + configuration.getExampleUri()
				+ " - " + configuration.getExampleString());
		description.setText("Default scoring engine is JPMML-Evaluator");
		// TODO: Might also show extra service information looked
		// up dynamically from endpoint/registry
	}*/
	
	@Override
	public void refreshView() {
		editorPane.setText(buildHtml());
		repaint();
	}

	/**
	 * View position hint
	 */
	@Override
	public int getPreferredPosition() {
		// We want to be on top
		return 100;
	}
	
	@Override
	public Action getConfigureAction(final Frame owner) {
		return new PmmlScoreRemoteConfigureAction(activity, owner);
	}
	
	private String buildHtml() {
		PmmlScoreRemoteActivityConfigurationBean configuration = activity.getConfiguration();
		
		StringBuilder html = new StringBuilder(getHtmlHead("#FFFFFF"));
		html.append(buildTableOpeningTag());
		html.append("<tr><td colspan=2><b>Input Dataset</b></td></tr>");
		html.append("<tr><td width=25%>Observations</td><td>0</td></tr>");
		html.append("<tr><td width=25%>Variables</td><td>0</td></tr>");
		html.append("<tr><td colspan=2><b>Scoring Engine</b></td></tr>");
		html.append("<tr><td width=25%>Base URI</td><td>"+configuration.getBaseURI()+"</td></tr>");
		return html.append("</table></body></html>").toString();
	}

}
