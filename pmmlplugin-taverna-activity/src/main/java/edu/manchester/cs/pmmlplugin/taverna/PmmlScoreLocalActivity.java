package edu.manchester.cs.pmmlplugin.taverna;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;

import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.EvaluationException;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.EvaluatorUtil;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.ModelEvaluatorFactory;
import org.jpmml.model.ImportFilter;
import org.jpmml.model.JAXBUtil;
import org.xml.sax.InputSource;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import edu.manchester.cs.pmmlplugin.taverna.CsvUtil.Table;
import net.sf.taverna.t2.invocation.InvocationContext;
import net.sf.taverna.t2.reference.ReferenceService;
import net.sf.taverna.t2.reference.T2Reference;
import net.sf.taverna.t2.workflowmodel.processor.activity.AbstractAsynchronousActivity;
import net.sf.taverna.t2.workflowmodel.processor.activity.ActivityConfigurationException;
import net.sf.taverna.t2.workflowmodel.processor.activity.AsynchronousActivity;
import net.sf.taverna.t2.workflowmodel.processor.activity.AsynchronousActivityCallback;

public class PmmlScoreLocalActivity extends
		AbstractAsynchronousActivity<PmmlScoreLocalActivityConfigurationBean>
		implements AsynchronousActivity<PmmlScoreLocalActivityConfigurationBean> {

	/*
	 * Best practice: Keep port names as constants to avoid misspelling. This
	 * would not apply if port names are looked up dynamically from the service
	 * operation, like done for WSDL services.
	 */
	private static final String IN_PMML_FILE = "pmmlFile";
	private static final String IN_DATASET = "inputDataset";
	private static final String OUT_SCORED_DATASET = "scoredDataset";
	private static final String OUT_PMML_FILE = "pmmlOut";
	
	private PmmlScoreLocalActivityConfigurationBean configBean;
	
	private File model = null;
	private File input = new File("/home/as850065/Downloads/Iris.csv");
	private File output = new File("/home/as850065/Downloads/IrisOut.csv");
	private String separator = ",";

	@Override
	public void configure(PmmlScoreLocalActivityConfigurationBean configBean)
			throws ActivityConfigurationException {

		// Any pre-config sanity checks
		/*if (configBean.getExampleString().equals("invalidExample")) {
			throw new ActivityConfigurationException(
					"Example string can't be 'invalidExample'");
		}*/
		if (configBean.getScoreEngine().equals("invalidExample")) {
			throw new ActivityConfigurationException(
					"PMML file can't be 'invalidExample'");
		}
		// Store for getConfiguration(), but you could also make
		// getConfiguration() return a new bean from other sources
		this.configBean = configBean;

		// OPTIONAL: 
		// Do any server-side lookups and configuration, like resolving WSDLs

		// myClient = new MyClient(configBean.getExampleUri());
		// this.service = myClient.getService(configBean.getExampleString());
		
		// REQUIRED: (Re)create input/output ports depending on configuration
		configurePorts();
	}

	protected void configurePorts() {
		// In case we are being reconfigured - remove existing ports first
		// to avoid duplicates
		removeInputs();
		removeOutputs();

		// FIXME: Replace with your input and output port definitions
		
		// Hard coded input port, expecting a single String
		//addInput(IN_FIRST_INPUT, 0, true, null, String.class);

		// Optional ports depending on configuration
		/*if (configBean.getExampleString().equals("specialCase")) {
			// depth 1, ie. list of binary byte[] arrays
			addInput(IN_EXTRA_DATA, 1, true, null, byte[].class);
			addOutput(OUT_REPORT, 0);
		}*/
		
		/*// Single value output port (depth 0)
		addOutput(OUT_SIMPLE_OUTPUT, 0);
		// Output port with list of values (depth 1)
		addOutput(OUT_MORE_OUTPUTS, 1);*/
		
		addInput(IN_PMML_FILE, 0, true, null, String.class);
		addInput(IN_DATASET, 1, true, null, byte[].class);
		
		addOutput(OUT_SCORED_DATASET, 1);
		//addOutput(OUT_PMML_FILE, 0);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void executeAsynch(final Map<String, T2Reference> inputs,
			final AsynchronousActivityCallback callback) {
		// Don't execute service directly now, request to be run ask to be run
		// from thread pool and return asynchronously
		callback.requestRun(new Runnable() {
			
			public void run() {
				InvocationContext context = callback
						.getContext();
				ReferenceService referenceService = context
						.getReferenceService();
				// Resolve inputs 				
				/*String firstInput = (String) referenceService.renderIdentifier(inputs.get(IN_FIRST_INPUT), 
						String.class, context);
				
				// Support our configuration-dependendent input
				boolean optionalPorts = configBean.getExampleString().equals("specialCase"); 
				
				List<byte[]> special = null;
				// We'll also allow IN_EXTRA_DATA to be optionally not provided
				if (optionalPorts && inputs.containsKey(IN_EXTRA_DATA)) {
					// Resolve as a list of byte[]
					special = (List<byte[]>) referenceService.renderIdentifier(
							inputs.get(IN_EXTRA_DATA), byte[].class, context);
				}*/
				

				// TODO: Do the actual service invocation
//				try {
//					results = this.service.invoke(firstInput, special)
//				} catch (ServiceException ex) {
//					callback.fail("Could not invoke PmmlPlugin service " + configBean.getExampleUri(),
//							ex);
//					// Make sure we don't call callback.receiveResult later 
//					return;
//				}

				// Register outputs
				/*Map<String, T2Reference> outputs = new HashMap<String, T2Reference>();
				String simpleValue = "simple";
				T2Reference simpleRef = referenceService.register(simpleValue, 0, true, context);
				outputs.put(OUT_SIMPLE_OUTPUT, simpleRef);

				// For list outputs, only need to register the top level list
				List<String> moreValues = new ArrayList<String>();
				moreValues.add("Value 1");
				moreValues.add("Value 2");
				T2Reference moreRef = referenceService.register(moreValues, 1, true, context);
				outputs.put(OUT_MORE_OUTPUTS, moreRef);

				if (optionalPorts) {
					// Populate our optional output port					
					// NOTE: Need to return output values for all defined output ports
					String report = "Everything OK";
					outputs.put(OUT_REPORT, referenceService.register(report,
							0, true, context));
				}*/
				
				// Resolve inputs 				
				String pmmlFile = (String) referenceService.renderIdentifier(inputs.get(IN_PMML_FILE), 
						String.class, context);
				model = new File(pmmlFile);
				
				//execute scoring
				try {
					executeScoring();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/*Map<String, T2Reference> outputs = new HashMap<String, T2Reference>();			
				T2Reference pmmlRef = referenceService.register(pmmlFile, 0, true, context);
				outputs.put(OUT_PMML_FILE, pmmlRef);*/
				
				//tutup:
				List<byte[]> dataset = null;
				dataset = (List<byte[]>) referenceService.renderIdentifier(
						inputs.get(IN_DATASET), byte[].class, context);
				
				Map<String, T2Reference> outputs = new HashMap<String, T2Reference>();
				/*List<String> scDataset = new ArrayList<String>();
				scDataset.add("Value 1");
				scDataset.add("Value 2");*/
				T2Reference scDatasetRef = referenceService.register(dataset, 1, true, context);
				outputs.put(OUT_SCORED_DATASET, scDatasetRef);
				
				// return map of output data, with empty index array as this is
				// the only and final result (this index parameter is used if
				// pipelining output)
				callback.receiveResult(outputs, new int[0]);
			}
		});
	}

	@Override
	public PmmlScoreLocalActivityConfigurationBean getConfiguration() {
		return this.configBean;
	}
	
	public void executeScoring() throws Exception {
		CsvUtil.Table inputTable = CsvUtil.readTable(this.input, this.separator);
		
		InputStream is = new FileInputStream(this.model);
		
		PMML pmml;
		try {
			Source source = ImportFilter.apply(new InputSource(is));
			
			pmml = JAXBUtil.unmarshalPMML(source);
		} finally {
			is.close();
		}
		
		ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
		
		Evaluator evaluator = (Evaluator)modelEvaluatorFactory.newModelManager(pmml);
		
		List<Map<FieldName, FieldValue>> argumentsList;
		
		List<Map<FieldName, ?>> resultList;
		
		argumentsList = prepareAll(evaluator, inputTable);
				
		resultList = evaluateAll(evaluator, argumentsList);
		
		// Check if the input table and the output table have equal number of rows
		boolean copyCells = (argumentsList.size() == (inputTable.size() - 1));
		
		CsvUtil.Table outputTable = new CsvUtil.Table();
		outputTable.setSeparator(inputTable.getSeparator());
		
		List<FieldName> fields = new ArrayList<>();
		fields.addAll(evaluator.getTargetFields());
		fields.addAll(evaluator.getOutputFields());
		
		List<String> headerRow = new ArrayList<>();
		
		if(copyCells){
			headerRow.addAll(inputTable.get(0));
		}
		
		for(FieldName field : fields){
			headerRow.add(field.getValue());
		}
		
		outputTable.add(headerRow);
		
		for(int i = 0; i < resultList.size(); i++){
			List<String> bodyRow = new ArrayList<>();

			if(copyCells){
				bodyRow.addAll(inputTable.get(i + 1));
			}

			Map<FieldName, ?> result = resultList.get(i);

			for(FieldName field : fields){
				Object value = EvaluatorUtil.decode(result.get(field));

				bodyRow.add(String.valueOf(value));
			}

			outputTable.add(bodyRow);
		}
		
		CsvUtil.writeTable(outputTable, this.output);
	}

	static private List<Map<FieldName, ?>> evaluateAll(Evaluator evaluator,
			List<Map<FieldName, FieldValue>> argumentsList) {
		List<Map<FieldName, ?>> resultList = new ArrayList<>();
		
		for(Map<FieldName, FieldValue> arguments : argumentsList){
			Map<FieldName, ?> result = evaluator.evaluate(arguments);

			resultList.add(result);
		}
		
		return resultList;
	}

	static private List<Map<FieldName, FieldValue>> prepareAll(Evaluator evaluator,
			Table table) {
		List<FieldName> names = new ArrayList<>();
		
		List<FieldName> activeFields = evaluator.getActiveFields();
		List<FieldName> groupFields = evaluator.getGroupFields();
		
		List<String> headerRow = table.get(0);
		for(int column = 0; column < headerRow.size(); column++){
			FieldName name = FieldName.create(headerRow.get(column));

			if(!(activeFields.contains(name) || groupFields.contains(name))){
				name = null;
			}

			names.add(name);
		}
		
		Sets.SetView<FieldName> missingActiveFields = difference(activeFields, names);
		if(missingActiveFields.size() > 0){
			throw new IllegalArgumentException("Missing active field(s): " + missingActiveFields.toString());
		}
		
		Sets.SetView<FieldName> missingGroupFields = difference(groupFields, names);
		if(missingGroupFields.size() > 0){
			throw new IllegalArgumentException("Missing group field(s): " + missingGroupFields.toString());
		}
		
		List<Map<FieldName, Object>> stringRows = new ArrayList<>();
		
		for(int i = 1; i < table.size(); i++){
			List<String> bodyRow = table.get(i);

			Map<FieldName, Object> stringRow = new LinkedHashMap<>();

			for(int column = 0; column < bodyRow.size(); column++){
				FieldName name = names.get(column);
				if(name == null){
					continue;
				}

				String value = bodyRow.get(column);
				if(("").equals(value) || ("NA").equals(value) || ("N/A").equals(value)){
					value = null;
				}

				stringRow.put(name, value);
			}

			stringRows.add(stringRow);
		}
		
		if(groupFields.size() == 1){
			FieldName groupField = groupFields.get(0);

			stringRows = EvaluatorUtil.groupRows(groupField, stringRows);
		} else

		if(groupFields.size() > 1){
			throw new EvaluationException();
		}
		
		List<Map<FieldName, FieldValue>> fieldValueRows = new ArrayList<>();
		
		for(Map<FieldName, Object> stringRow : stringRows){
			Map<FieldName, FieldValue> fieldValueRow = new LinkedHashMap<>();

			Collection<Map.Entry<FieldName, Object>> entries = stringRow.entrySet();
			for(Map.Entry<FieldName, Object> entry : entries){
				FieldName name = entry.getKey();
				FieldValue value = EvaluatorUtil.prepare(evaluator, name, entry.getValue());

				fieldValueRow.put(name, value);
			}

			fieldValueRows.add(fieldValueRow);
		}
		
		return fieldValueRows;
	}

	private static SetView<FieldName> difference(List<FieldName> requiredFields,
			List<FieldName> fields) {
		Predicate<FieldName> notNull = Predicates.notNull();
		return Sets.difference(Sets.newHashSet(Iterables.filter(requiredFields, notNull)), Sets.newHashSet(Iterables.filter(fields, notNull)));
	}

}
