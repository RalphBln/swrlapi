package org.swrlapi.ui.model;

import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.swrlapi.core.SWRLAPIFactory;
import org.swrlapi.core.SWRLAPIOWLOntology;
import org.swrlapi.core.SWRLRuleEngine;
import org.swrlapi.core.impl.DefaultSWRLAPIRuleRenderer;
import org.swrlapi.parser.SWRLParser;
import org.swrlapi.sqwrl.SQWRLQueryEngine;

/**
 * Provides an application model that can be used to build a MVC-based GUI that uses the SWRLAPI. Used in conjunction
 * with a {@link org.swrlapi.ui.model.SWRLAPIApplicationModel} and
 * a {@link org.swrlapi.ui.controller.SWRLAPIApplicationController}.
 * 
 * @see org.swrlapi.ui.view.SWRLAPIApplicationView
 * @see org.swrlapi.ui.controller.SWRLAPIApplicationController
 */
public class SWRLAPIApplicationModel implements SWRLAPIModel
{
	private final SWRLAPIOWLOntology swrlapiowlOntology;
	private final SWRLRulesTableModel swrlRulesTableModel;
	private final SQWRLQueryEngine queryEngine;
	private final SWRLRuleEngine ruleEngine;
	private final SWRLParser swrlParser;
	private final DefaultPrefixManager prefixManager;
	private final DefaultSWRLAPIRuleRenderer swrlRuleRenderer;

	public SWRLAPIApplicationModel(SWRLAPIOWLOntology swrlapiOWLOntology, SWRLRuleEngine ruleEngine)
	{
		this.swrlapiowlOntology = swrlapiOWLOntology;
		this.ruleEngine = ruleEngine;
		this.queryEngine = ruleEngine;
		this.prefixManager = swrlapiOWLOntology.getPrefixManager();
		this.swrlRuleRenderer = SWRLAPIFactory.createSWRLRuleRenderer(swrlapiowlOntology);
		this.swrlRulesTableModel = SWRLAPIFactory.createSWRLRulesTableModel(ruleEngine, swrlRuleRenderer);
		this.swrlParser = SWRLAPIFactory.createSWRLParser(swrlapiOWLOntology);
	}

	public SWRLAPIOWLOntology getSWRLAPIOWLOntology() { return this.swrlapiowlOntology; }

	public SWRLRuleEngine getSWRLRuleEngine()
	{
		return this.ruleEngine;
	}

	public SQWRLQueryEngine getSQWRLQueryEngine()
	{
		return this.queryEngine;
	}

	public SWRLParser getSWRLParser()
	{
		return this.swrlParser;
	}

	public DefaultPrefixManager getPrefixManager()
	{
		return this.prefixManager;
	}

	public SWRLRulesTableModel getSWRLRulesTableModel()
	{
		return this.swrlRulesTableModel;
	}

	public void saveSWRLRules() {} // TODO

	public boolean areSWRLRulesModified() { return swrlRulesTableModel.hasBeenModified(); }

	public void clearSWRLRulesModified()
	{
		swrlRulesTableModel.clearModifiedStatus();
	}
}