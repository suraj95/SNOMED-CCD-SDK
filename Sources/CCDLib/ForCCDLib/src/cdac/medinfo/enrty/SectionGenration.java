/*******************************************************************************
 *  Copyright 2019 Centre for Development of Advanced Computing(C-DAC), Pune
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *   http:\www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package cdac.medinfo.enrty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBElement;

import cdac.medinfo.base.CCDConstantTemplates;
import cdac.medinfo.base.CCDConstants;
import cdac.medinfo.base.StandardMethods;
import cdac.medinfo.ccd.datatype.CD;
import cdac.medinfo.ccd.datatype.CR;
import cdac.medinfo.ccd.datatype.ED;
import cdac.medinfo.ccd.datatype.INT;
import cdac.medinfo.ccd.datatype.IVLPQ;
import cdac.medinfo.ccd.datatype.IVLTS;
import cdac.medinfo.ccd.datatype.IVXBPQ;
import cdac.medinfo.ccd.datatype.IVXBTS;
import cdac.medinfo.ccd.datatype.ON;
import cdac.medinfo.ccd.enums.ActClassSupply;
import cdac.medinfo.ccd.enums.ParticipationPhysicalPerformer;
import cdac.medinfo.ccd.enums.XActClassDocumentEntryAct;
import cdac.medinfo.ccd.enums.XActClassDocumentEntryOrganizer;
import cdac.medinfo.ccd.enums.XActMoodDocumentObservation;
import cdac.medinfo.ccd.enums.XActRelationshipEntry;
import cdac.medinfo.ccd.enums.XActRelationshipEntryRelationship;
import cdac.medinfo.ccd.enums.XActRelationshipExternalReference;
import cdac.medinfo.ccd.enums.XDocumentActMood;
import cdac.medinfo.ccd.enums.XDocumentEncounterMood;
import cdac.medinfo.ccd.enums.XDocumentProcedureMood;
import cdac.medinfo.ccd.enums.XDocumentSubject;
import cdac.medinfo.ccd.enums.XDocumentSubstanceMood;
import cdac.medinfo.ccd.narrativeblock.ObjectFactory;
import cdac.medinfo.ccd.narrativeblock.StrucDocLinkHtml;
import cdac.medinfo.ccd.narrativeblock.StrucDocParagraph;
import cdac.medinfo.ccd.narrativeblock.StrucDocTable;
import cdac.medinfo.ccd.narrativeblock.StrucDocTbody;
import cdac.medinfo.ccd.narrativeblock.StrucDocTd;
import cdac.medinfo.ccd.narrativeblock.StrucDocTh;
import cdac.medinfo.ccd.narrativeblock.StrucDocThead;
import cdac.medinfo.ccd.narrativeblock.StrucDocTr;
import cdac.medinfo.ccd.templates.Act;
import cdac.medinfo.ccd.templates.AssignedEntity;
import cdac.medinfo.ccd.templates.Component3;
import cdac.medinfo.ccd.templates.Component4;
import cdac.medinfo.ccd.templates.Consumable;
import cdac.medinfo.ccd.templates.Criterion;
import cdac.medinfo.ccd.templates.Device;
import cdac.medinfo.ccd.templates.Encounter;
import cdac.medinfo.ccd.templates.Entry;
import cdac.medinfo.ccd.templates.EntryRelationship;
import cdac.medinfo.ccd.templates.ExternalDocument;
import cdac.medinfo.ccd.templates.Informant12;
import cdac.medinfo.ccd.templates.ManufacturedProduct;
import cdac.medinfo.ccd.templates.Material;
import cdac.medinfo.ccd.templates.Observation;
import cdac.medinfo.ccd.templates.ObservationRange;
import cdac.medinfo.ccd.templates.Organization;
import cdac.medinfo.ccd.templates.Organizer;
import cdac.medinfo.ccd.templates.Participant2;
import cdac.medinfo.ccd.templates.ParticipantRole;
import cdac.medinfo.ccd.templates.Performer2;
import cdac.medinfo.ccd.templates.PlayingEntity;
import cdac.medinfo.ccd.templates.Precondition;
import cdac.medinfo.ccd.templates.Procedure;
import cdac.medinfo.ccd.templates.Reference;
import cdac.medinfo.ccd.templates.ReferenceRange;
import cdac.medinfo.ccd.templates.RelatedSubject;
import cdac.medinfo.ccd.templates.Section;
import cdac.medinfo.ccd.templates.Subject;
import cdac.medinfo.ccd.templates.SubjectPerson;
import cdac.medinfo.ccd.templates.SubstanceAdministration;
import cdac.medinfo.ccd.templates.Supply;
import cdac.medinfo.codesytem.SNOMEDCode;
import cdac.medinfo.enums.EnumClassCode;
import cdac.medinfo.enums.EnumCodeSystem;
import cdac.medinfo.enums.EnumEntryDataSection;
import cdac.medinfo.enums.EnumLOINC;
import cdac.medinfo.enums.EnumMoodCode;
import cdac.medinfo.enums.EnumRouteCode;
import cdac.medinfo.enums.EnumSection;
import cdac.medinfo.enums.EnumStatusCode;
import cdac.medinfo.enums.EnumTableHeader;
import cdac.medinfo.enums.EnumTypeCode;
import cdac.medinfo.model.AdvanceDirectives;
import cdac.medinfo.model.Alerts;
import cdac.medinfo.model.EffectiveDateTime;
import cdac.medinfo.model.Encounters;
import cdac.medinfo.model.FamilyHistory;
import cdac.medinfo.model.FunctionalStatus;
import cdac.medinfo.model.Immunizations;
import cdac.medinfo.model.MedicalEquipment;
import cdac.medinfo.model.Medications;
import cdac.medinfo.model.Payer;
import cdac.medinfo.model.Plan;
import cdac.medinfo.model.Problems;
import cdac.medinfo.model.Procedures;
import cdac.medinfo.model.Results;
import cdac.medinfo.model.SocialHistory;
import cdac.medinfo.model.SummaryPurpose;
import cdac.medinfo.model.TestReport;
import cdac.medinfo.model.VitalSigns;
import cdac.medinfo.utils.CCDLogger;
import cdac.medinfo.utils.PropertyLoader;
import cdac.medinfo.utils.ResourceBundleWrapper;
/**
 *  This Class is used to populate Entry data for Sections 
 */
public class SectionGenration {
	private static ObjectFactory objFactory =new ObjectFactory();
	private static Set<String> episodeObsPresentPD = new HashSet<String>();
	private static Set<String> episodeObsPresentSH = new HashSet<String>();
	private static Set<String> date = new LinkedHashSet<>();

	/**
	 *  Method is used to Generate section and add into component
	 *  @param objModels - List of Models
	 *  @param currentComponent - Current Component fetched from list of component 
	 *  @return component3 - {@link Component3} object of Component3
	 */
	public static Component3 generateSection(List<?> objModels,Component3 currentComponent) {
		Component3 component3 = null;
		StrucDocTbody tbody = null;
		Section section = null;
		StrucDocTable table=null;

		if(objModels !=null && !objModels.isEmpty())
		{
			if (currentComponent == null) {
				component3 = objFactory.createComponent3();

				if (objModels.get(0).getClass().getSimpleName().equals("FamilyHistory")) {
					section = createTableFamilyHistory(objModels, currentComponent);
					component3.setSection(section);
					return component3;
				}

				table = objFactory.createStrucDocTable();
				table.setBorder(PropertyLoader.getProperty("Table_Border"));
				table.setWidth(PropertyLoader.getProperty("Table_Width"));

				StrucDocThead thead = objFactory.createStrucDocThead();
				thead.getTr().add(getStrucDocThList(objModels));
				table.setThead(thead);
				tbody = objFactory.createStrucDocTbody();
				table.getTbody().add(tbody);

			}
			else {
				component3 = currentComponent;

				if (objModels.get(0).getClass().getSimpleName().equals("FamilyHistory")) {
					section = createTableFamilyHistory(objModels, currentComponent);
					component3.setSection(section);
					return component3;
				}
				/* Fetches section from existing component */
				section = component3.getSection();
				table = (StrucDocTable) section.getText().getContent().get(0);
				tbody = table.getTbody().get(0);

			}
			createTable(objModels,tbody);

			switch(objModels.get(0).getClass().getSimpleName())
			{

			case "SummaryPurpose" :
			{
				/*
				 *********************************************************
				  Summary Purpose section
				 ********************************************************
				 */
				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PURPOSE_SECTION_TEMPLATES)); 
					section.setCode(PopulateDataType.populateCE(EnumLOINC.SUMMARY_PURPOSE.getValue(),EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.SUMMARY_PURPOSE.getValue()));
					section.setText(objFactory.createStrucDocText());
				}
				if(objModels.isEmpty())
					section.getText().getContent().add(CCDConstants.TRANSFER_OF_CARE);
				else
				{
					@SuppressWarnings("unchecked")
					List<SummaryPurpose> objListPurpose=(List<SummaryPurpose>)objModels;
					
					section.getText().getContent().add(objListPurpose.get(0).getPurpose());
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(), objListPurpose.get(0)));
				}
			}
			break ;
			case "Payer" : 
			{
				/*
				 *********************************************************
				  Payers section
				 ********************************************************
				 */
				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PAYERS_SECTION_TEMPLATES)); 
					section.setCode(PopulateDataType.populateCE(EnumLOINC.PAYMENT_SOURCES.getValue(),EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.PAYERS.getValue()));
					section.setText(objFactory.createStrucDocText());
					section.getText().getContent().add(table);
				}

				@SuppressWarnings("unchecked") 
				List<Payer> listPayers=(List<Payer>)objModels;
				for(Payer objPayers: listPayers){
					if(objPayers.getPayerName() == null || objPayers.getPayerName().isEmpty() ||
							objPayers.getCoveredPartyID()==null || objPayers.getCoveredPartyID().isEmpty()||
							objPayers.getPolicyType() == null || objPayers.getPolicyType().isEmpty() ||
							objPayers.getAuthorization() == null || objPayers.getAuthorization().isEmpty() )
					{		
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.PAYERS.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(), objPayers));
				}
			}break;
			case "AdvanceDirectives" : 
			{
				/*
				 *********************************************************
				  Advanced Directives section
				 ********************************************************
				 */
				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.ADVANCE_DIRECTIVES_TEMPLATE)); 
					section.setCode(PopulateDataType.populateCE(EnumLOINC.ADVANCE_DIRECTIVES.getValue(), EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.ADVANCE_DIRECTIVE.getValue()));
					section.setText(objFactory.createStrucDocText());
					section.getText().getContent().add(table);
				}

				@SuppressWarnings("unchecked") 
				List<AdvanceDirectives> listAdvanceDirective=(List<AdvanceDirectives>)objModels;
				for(AdvanceDirectives objAdvanceDirectives: listAdvanceDirective){
					if(objAdvanceDirectives.getDirective() == null||objAdvanceDirectives.getDirective().isEmpty() 
							||objAdvanceDirectives.getDescription() == null ||objAdvanceDirectives.getDescription().isEmpty()
							||objAdvanceDirectives.getVerification() == null ||objAdvanceDirectives.getVerification().isEmpty() 
							||objAdvanceDirectives.getSupportingDocument() == null ||objAdvanceDirectives.getSupportingDocument().isEmpty()
							||objAdvanceDirectives.getVerificationDate() == null ||objAdvanceDirectives.getVerificationDate().isEmpty()
							||objAdvanceDirectives.getEffectiveTimes()==null)
					{		
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.ADVANCE_DIRECTIVE.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(),objAdvanceDirectives));
				}
			}break;
			case "FunctionalStatus" : 
			{
				/*
				 *********************************************************
				  Functional Status section
				 ********************************************************
				 */

				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.FUNCTIONAL_STATUS_SECTION_TEMPLATE)); 
					section.setCode(PopulateDataType.populateCE(EnumLOINC.FUNCTIONAL_STATUS_ASSESSMENT_NOTE.getValue(),EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.FUNCTIONAL_STATUS.getValue()));
					section.setText(objFactory.createStrucDocText());

					section.getText().getContent().add(table);
				}
				@SuppressWarnings("unchecked") 
				List<FunctionalStatus> objFunctionalStatus=(List<FunctionalStatus>)objModels;
				for(FunctionalStatus objFSD: objFunctionalStatus){
					if(objFSD.getFunctionalCondition() == null ||objFSD.getFunctionalCondition().isEmpty()
							||objFSD.getConditionStatus() == null ||objFSD.getConditionStatus().isEmpty()
							||objFSD.getEffectiveDates() == null)
					{
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.FUNCTIONAL_STATUS.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(),objFSD));
				}
			} break ;
			case "Problems" : 
			{
				/*
				 *********************************************************
				  Problems  Section
				 ********************************************************
				 */
				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PROBLEM_SECTION_TEMPLATE)); 
					section.setCode(PopulateDataType.populateCE(EnumLOINC.PROBLEM_LIST.getValue(),EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.PROBLEMS.getValue()));
					section.setText(objFactory.createStrucDocText());

					section.getText().getContent().add(table);
				}
				@SuppressWarnings("unchecked")
				List<Problems> objProblems=(List<Problems>)objModels;
				for(Problems objPD: objProblems){
					if(objPD.getCondition() == null || objPD.getCondition().isEmpty()
							||objPD.getEffectiveDates() == null 
							||objPD.getConditionStatus() == null ||objPD.getConditionStatus().isEmpty())
					{
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.PROBLEMS.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(),objPD));
				}

			} break ;

			case "SocialHistory" : 
			{
				/*
				 *********************************************************
				  Social History Section 
				 ********************************************************
				 */
				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.SOCIAL_HISTORY_SECTION_TEMPLATE));
					section.setCode(PopulateDataType.populateCE(EnumLOINC.SOCIAL_HISTORY.getValue(),EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.SOCIAL_HISTORY.getValue()));
					section.setText(objFactory.createStrucDocText());

					section.getText().getContent().add(table);
				}

				@SuppressWarnings("unchecked")
				List<SocialHistory> objSocialHistory=(List<SocialHistory>)objModels;
				for(SocialHistory objSHD: objSocialHistory){
					if(objSHD.getSocialHistoryElement() == null || objSHD.getSocialHistoryElement().isEmpty()
							||objSHD.getDescription() == null || objSHD.getDescription().isEmpty()
							||objSHD.getEffectiveDates() == null )
					{
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.SOCIAL_HISTORY.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(),objSHD));
				} }break;

				/*
				 *********************************************************
			  Alerts section
				 ********************************************************
				 */
			case "Alerts" : 
			{
				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.ALERT_SECTION_TEMPLATE)); 
					section.setCode(PopulateDataType.populateCE(EnumLOINC.ALLERGIES_OR_ADVERSE_REACTIONS.getValue(),  EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.ALERTS.getValue()));
					section.setText(objFactory.createStrucDocText());

					section.getText().getContent().add(table);
				}
				@SuppressWarnings("unchecked") 
				List<Alerts> listAlerts=(List<Alerts>)objModels;
				for(Alerts objAlerts: listAlerts){
					if(objAlerts.getSubstance() == null || objAlerts.getSubstance().isEmpty()
							||objAlerts.getReaction() == null || objAlerts.getReaction().isEmpty()
							|| objAlerts.getStatus() == null || objAlerts.getStatus().isEmpty())
					{		
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.ALERTS.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(),objAlerts));
				}
			}break;
			case "Medications" : 
			{
				/*
				 *********************************************************
				  Medications section
				 ********************************************************
				 */

				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.MEDICATIONS_SECTION_TEMPLATE)); 
					section.setCode(PopulateDataType.populateCE(EnumLOINC.MEDICATION_USE.getValue(), EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.MEDICATIONS.getValue()));
					section.setText(objFactory.createStrucDocText());

					section.getText().getContent().add(table);

					Informant12 informant = objFactory.createInformant12();
					AssignedEntity assignedEntity =objFactory.createAssignedEntity();
					Organization representedOrganization = objFactory.createOrganization() ;
					ON onOrganizationName = objFactory.createON();

					informant.setAssignedEntity(assignedEntity);
					String extension = PropertyLoader.getProperty("MRN");
					assignedEntity.getId().add(PopulateDataType.populateII(extension,EnumCodeSystem.OID_HL7.getValue()));					
					assignedEntity.setRepresentedOrganization(representedOrganization);
					representedOrganization.getId().add(PopulateDataType.populateII(EnumCodeSystem.OID_HL7.getValue()));
					onOrganizationName.getContent().add(PropertyLoader.getProperty("Organization_Name"));
					representedOrganization.getName().add(onOrganizationName);

					section.getInformant().add(informant);

				}
				@SuppressWarnings("unchecked") 
				List<Medications> objList=(List<Medications>)objModels;
				for(Medications objMD: objList){
					if(objMD.getMedication() == null || objMD.getMedication().isEmpty()
							||objMD.getInstructions() == null || objMD.getInstructions().isEmpty()
							||objMD.getStatus() == null ||objMD.getStatus().isEmpty()
							||objMD.getDate() == null 
							||objMD.getDoseQuantity() == null ||objMD.getCriterion() == null ||objMD.getCriterion().isEmpty())
					{		
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.MEDICATIONS.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(),objMD));
				}
			}
			break;
			case "MedicalEquipment" :
				/*
				 *********************************************************
				 Medical Equipment section
				 ********************************************************
				 */
			{
				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.MEDICAL_EQUIPMENT_SECTION_TEMPLATE));
					section.setCode(PopulateDataType.populateCE(EnumLOINC.HISTORY_OF_MEDICAL_DEVICE_USE.getValue(),EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.MEDICAL_EQUIPMENT.getValue()));
					section.setText(objFactory.createStrucDocText());

					section.getText().getContent().add(table);
				}
				@SuppressWarnings("unchecked") 
				List<MedicalEquipment> listMedicalEquipment=(List<MedicalEquipment>)objModels;
				for(MedicalEquipment objMED: listMedicalEquipment){
					if(objMED.getSupplyDevice() == null||objMED.getSupplyDevice().isEmpty()
							||objMED.getDateSupplied() == null || objMED.getDateSupplied().isEmpty())
					{		
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.MEDICAL_EQUIPMENT.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(),objMED));
				}
			} break ;
			case "Immunizations" : 
			{
				/*
				 *********************************************************
				 Immunizations section
				 ********************************************************
				 */

				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.IMMUNIZATION_SECTION_TEMPLATES)); 
					section.setCode(PopulateDataType.populateCE(EnumLOINC.IMMUNIZATION.getValue(), EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.IMMUNIZATION.getValue()));
					section.setText(objFactory.createStrucDocText());
					section.getText().getContent().add(table);

				}
				@SuppressWarnings("unchecked") 
				List<Immunizations> objList=(List<Immunizations>)objModels;
				for(Immunizations objIM: objList){
					if(objIM.getVaccine() == null||objIM.getVaccine().isEmpty()
							||objIM.getStatus() == null||objIM.getStatus().isEmpty()
							||objIM.getDate() == null||objIM.getMoodOfVaccine() == null||objIM.getMoodOfVaccine().isEmpty())
					{		
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.ALERTS.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(),objIM));
				}
			}break ;
			case "VitalSigns" : 
			{
				/*
				 *********************************************************
				 Vital Signs section
				 ********************************************************
				 */

				Map<String,String> hm = new HashMap<>(); 
				createTableForVitalSign(objModels,table,date);
				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.VITAL_SIGNS_SECTION_TEMPLATE)); 
					section.setCode(PopulateDataType.populateCE(EnumLOINC.VITAL_SIGNS.getValue(),EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.VITAL_SIGNS.getValue()));
					section.setText(objFactory.createStrucDocText());
					section.getText().getContent().add(table);
				}
				Iterator<String> colCount = date.iterator(); 

				@SuppressWarnings("unchecked")
				List<VitalSigns> objList =(List<VitalSigns>)objModels;
				for(VitalSigns objVitals: objList)
				{	if(objVitals.getVitalSigns() == null ||objVitals.getVitalSigns().isEmpty()
				||objVitals.getDate()== null||objVitals.getDate().isEmpty()
				||objVitals.getValue() == null||objVitals.getValue().isEmpty())
				{		
					CCDLogger objCCDLogger = CCDLogger.getLogger();
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.VITAL_SIGNS.getValue()));
				}	}
				while(colCount.hasNext())
				{
					String colDate =colCount.next();
					@SuppressWarnings("unchecked")
					List<VitalSigns> objListVitals =(List<VitalSigns>)objModels;
					for(VitalSigns objVitalSign: objListVitals)
					{	if(!(objVitalSign.getVitalSigns() == null ||objVitalSign.getVitalSigns().isEmpty()
					||objVitalSign.getDate()== null||objVitalSign.getDate().isEmpty()
					||objVitalSign.getValue() == null||objVitalSign.getValue().isEmpty()))

						if(objVitalSign.getDate().equals(colDate))
						{
							hm.put(objVitalSign.getVitalSigns(),objVitalSign.getValue()+" "+objVitalSign.getUnit());

						}
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),hm,colDate));	
					hm.clear();
				}

			} break ;
			case "Results" : 
			{
				/*
				 *********************************************************
				  Result  section
				 ********************************************************
				 */
				//counter variable counts number of blank columns to add in order to set data in the respective date column 
				int counter=0;
				@SuppressWarnings("unchecked")
				List<Results> listResult=(List<Results>)objModels;
				for(Results result: listResult){
					if(!(result.getTestDetails() == null||result.getTestDetails().isEmpty())){
						getTbodyResult(table,result,counter);
					}
					counter++;
				}
				section  = objFactory.createSection();
				section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.RESULTS_SECTION_TEMPLATE));
				section.setCode(PopulateDataType.populateCE(EnumLOINC.RELEVANT_DIAGNOSTIC_TESTS_OR_LABORATORY_DATA.getValue(),EnumCodeSystem.OID_LOINC.getValue()));
				section.setTitle(PopulateDataType.populateST(EnumSection.RESULTS.getValue()));
				section.setText(objFactory.createStrucDocText());
				section.getText().getContent().add(table);


				for(Results objResult : listResult)
				{ 
					if(objResult.getTestDate() == null||objResult.getTestDate().isEmpty()
							||objResult.getTestDetails() == null||objResult.getTestDetails().isEmpty())
					{
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.RESULTS.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(),objResult ));
				}
			} break ;
			case "Procedures" : 
			{
				/*
				 *********************************************************
				  Procedures  section
				 ********************************************************
				 */
				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PROCEDURES_SECTION_TEMPLATE)); 
					section.setCode(PopulateDataType.populateCE(EnumLOINC.HISTORY_OF_PROCEDURE.getValue(),EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.PROCEDURES.getValue()));
					section.setText(objFactory.createStrucDocText());

					section.getText().getContent().add(table);
				}
				@SuppressWarnings("unchecked")
				List<Procedures> listProcedures=(List<Procedures>)objModels;
				for(Procedures objPRD : listProcedures)
				{
					if(objPRD.getProcedure() == null||objPRD.getProcedure().isEmpty()
							||objPRD.getDate() == null||objPRD.getDate().isEmpty()
							||objPRD.getLaterality() == null || objPRD.getLaterality().isEmpty()
							||objPRD.getObserve() == null || objPRD.getObserve().isEmpty())
					{
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.PROCEDURES.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(),objPRD));
				}
			} break ;
			case "Encounters" : 
			{
				/*
				 *********************************************************
				  Encounter  section
				 ********************************************************
				 */
				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.ENCOUNTER_SECTION_TEMPLATE)); 
					section.setCode(PopulateDataType.populateCE(EnumLOINC.HISTORY_OF_ENCOUNTERS.getValue(), EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.ENCOUNTERS.getValue()));
					section.setText(objFactory.createStrucDocText());

					section.getText().getContent().add(table);
				}
				@SuppressWarnings("unchecked")
				List<Encounters> listEncounters=(List<Encounters>)objModels;
				for(Encounters objED : listEncounters)
				{
					if(objED.getEncounter() == null||objED.getEncounter().isEmpty()
							||objED.getLocation() == null||objED.getLocation().isEmpty() 
							||objED.getDate() == null)
					{
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.ENCOUNTERS.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(),objED));
				}
			} break ;
			case "Plan" : 
			{
				/*
				 *********************************************************
				  Plan  section
				 ********************************************************
				 */
				if(currentComponent==null){
					section  = objFactory.createSection();
					section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PLAN_OF_CARE_SECTION_TEMPLATE)); 
					section.setCode(PopulateDataType.populateCE(EnumLOINC.PLAN_OF_CARE_NOTE.getValue(),EnumCodeSystem.OID_LOINC.getValue()));
					section.setTitle(PopulateDataType.populateST(EnumSection.PLAN.getValue()));
					section.setText(objFactory.createStrucDocText());

					section.getText().getContent().add(table);
				}
				@SuppressWarnings("unchecked")
				List<Plan> listPlan=(List<Plan>)objModels;
				for(Plan objPlan : listPlan)
				{
					if(objPlan.getPlannedActivity() == null||objPlan.getPlannedActivity().isEmpty()
							||objPlan.getPlannedDate() == null  
							||objPlan.getActivityMoodCode() == null  ||objPlan.getActivityMoodCode().isEmpty()
							||objPlan.getPlanOfCareActivityName() == null  ||objPlan.getPlanOfCareActivityName().isEmpty())
					{
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.PLAN.getValue()));
					}
					section.getEntry().add(getEntryData(StandardMethods.generateUUID(),StandardMethods.generateUUID(),objPlan));
				}
			} break ;
			}
			component3.setSection(section);
		}
		return component3;
	}

	/**
	 *  Method is used to create and add Entry data in Document Body
	 *  @param actID - ACt ID 
	 *  @param obsID - Observation ID
	 *   @param objModels - List of Models
	 *  @return entry - {@link Entry} object of Entry
	 */
	private static Entry getEntryData(String actID,String obsID,Object objModel) 
	{ 
		Entry entry = objFactory.createEntry();
		entry.setTypeCode(XActRelationshipEntry.DRIV);

		switch(objModel.getClass().getSimpleName())
		{
		case "SummaryPurpose" :
		{
			Act act = objFactory.createAct();
			act.setClassCode(XActClassDocumentEntryAct.ACT);
			act.setMoodCode(XDocumentActMood.EVN);
			act.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PURPOSE_ACTIVITY_TEMPLATES)); 
			act.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(CCDConstants.DOCUMENTATION_PROCEDURE),EnumCodeSystem.OID_SNOMED_CT.getValue(),CCDConstants.DOCUMENTATION_PROCEDURE));
			act.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			EntryRelationship entryrelationship = new EntryRelationship();
			entryrelationship.setTypeCode(XActRelationshipEntryRelationship.RSON);

			Act entryAct = objFactory.createAct();
			entryAct.setClassCode(XActClassDocumentEntryAct.ACT);
			entryAct.setMoodCode(XDocumentActMood.EVN);
			entryAct.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(((SummaryPurpose)objModel).getPurpose()),EnumCodeSystem.OID_SNOMED_CT.getValue() ,((SummaryPurpose)objModel).getPurpose()));
			entryAct.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));

			entryrelationship.setAct(entryAct);
			act.getEntryRelationship().add(entryrelationship);
			act.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			entry.setAct(act);
		} break;
		case "Payer" : {

			//Creating ACT object
			Act act = objFactory.createAct();
			act.setClassCode(XActClassDocumentEntryAct.ACT);
			act.setMoodCode(XDocumentActMood.DEF);
			act.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.COVERAGE_ACTIVITY_TEMPLATE));
			act.getId().add(PopulateDataType.populateII(actID));
			act.setCode(PopulateDataType.populateCD(EnumLOINC.PAYMENT_SOURCES.getValue(),EnumCodeSystem.OID_LOINC.getValue() ,CCDConstants.PAYMENT_SOURCES));
			act.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			//Creating and populating  Entry Relationship object
			EntryRelationship entryrelationship = objFactory.createEntryRelationship();
			entryrelationship.setTypeCode(XActRelationshipEntryRelationship.COMP);

			//Creating and populating  Entry ACT object
			Act entryAct = objFactory.createAct();
			entryAct.setClassCode(XActClassDocumentEntryAct.ACT);
			entryAct.setMoodCode(XDocumentActMood.EVN);
			entryAct.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.POLICY_ACTIVITY_TEMPLATE));
			entryAct.getId().add(PopulateDataType.populateII(obsID));
			entryAct.setCode(PopulateDataType.populateCD(null,EnumCodeSystem.OID_ACTCODE.getValue() ,((Payer)objModel).getPolicyType())); 
			entryAct.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));

			//Creating and populating  Entry ACT object
			Performer2 performer = objFactory.createPerformer2();
			performer.setTypeCode(ParticipationPhysicalPerformer.PRF);

			//Creating and populating  Assigned Entity object
			AssignedEntity assignedEntity = objFactory.createAssignedEntity();
			assignedEntity.getId().add(PopulateDataType.populateII(obsID));
			performer.setAssignedEntity(assignedEntity);

			//Adding Organization name
			ON onOrganizationName = objFactory.createON();
			onOrganizationName.getContent().add(((Payer)objModel).getPayerName()); 
			Organization representedOrganization= objFactory.createOrganization();
			representedOrganization.getName().add(onOrganizationName);
			assignedEntity.setRepresentedOrganization(representedOrganization);
			entryAct.getPerformer().add(performer);
			entryrelationship.setAct(entryAct);
			act.getEntryRelationship().add(entryrelationship);

			//Creating and populating  Participant Role object
			ParticipantRole participantRole = objFactory.createParticipantRole();
			participantRole.getId().add(PopulateDataType.populateII( ((Payer) objModel).getCoveredPartyID())); 
			if(((Payer) objModel).getCoverageType()!=null)
				participantRole.setCode(PopulateDataType.populateCE(((Payer) objModel).getCoverageType().toUpperCase(),EnumCodeSystem.OID_ROLE_CODE.getValue(),((Payer) objModel).getCoverageType())); // To be discuss
			else
				participantRole.setCode(PopulateDataType.populateCE(((Payer) objModel).getCoverageType(),EnumCodeSystem.OID_ROLE_CODE.getValue(),((Payer) objModel).getCoverageType())); // To be discuss

			//Creating and populating participant object
			Participant2 participant = objFactory.createParticipant2();
			participant.getTypeCode().add(EnumTypeCode.COV.getValue());
			participant.setParticipantRole(participantRole);
			entryAct.getParticipant().add(participant);

			//Creating and populating ACT Entry Relationship object
			EntryRelationship actentryrelationship = objFactory.createEntryRelationship();
			actentryrelationship.setTypeCode(XActRelationshipEntryRelationship.REFR);

			//Creating and populating Entry  ACT object
			Act entryActRel = objFactory.createAct();
			entryActRel.setClassCode(XActClassDocumentEntryAct.ACT);
			entryActRel.setMoodCode(XDocumentActMood.EVN);
			entryActRel.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.AUTHORIZATION_ACTIVITY_TEMPLATE));
			entryActRel.getId().add(PopulateDataType.populateII(obsID));
			entryActRel.setCode(PopulateDataType.populateCDNullFalvor(CCDConstants.NULLFLAVOR_NA));
			entryAct.getEntryRelationship().add(actentryrelationship);
			actentryrelationship.setAct(entryActRel);

			//Creating and populating Sub Entry Relationship object
			EntryRelationship subEntry = objFactory.createEntryRelationship();
			subEntry.setTypeCode(XActRelationshipEntryRelationship.SUBJ);

			//Creating and populating Procedure object
			Procedure procedure= objFactory.createProcedure();
			procedure.getClassCode().add(EnumClassCode.PROC.getValue());
			procedure.setMoodCode(XDocumentProcedureMood.PRMS);
			procedure.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(((Payer) objModel).getAuthorization()),EnumCodeSystem.OID_SNOMED_CT.getValue() ,((Payer) objModel).getAuthorization()));  //4th feild
			subEntry.setProcedure(procedure);
			entryActRel.getEntryRelationship().add(subEntry);
			entry.setAct(act);
		}
		break;
		case "AdvanceDirectives":
		{
			EntryRelationship entryrelationship = objFactory.createEntryRelationship();
			Observation observation = objFactory.createObservation();
			entry.setTypeCode(XActRelationshipEntry.DRIV);
			entry.setObservation(observation);
			ED e= objFactory.createED();
			Observation obs = objFactory.createObservation();
			Reference ref= objFactory.createReference();
			ExternalDocument exdoc = objFactory.createExternalDocument();

			observation.getClassCode().add(EnumClassCode.OBS.getValue());
			observation.setMoodCode(XActMoodDocumentObservation.EVN);
			observation.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.ADVANCE_DIRECTIVE_OBSERVATION_TEMPLATE));
			observation.getId().add(PopulateDataType.populateII(actID));
			observation.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(((AdvanceDirectives)objModel).getDirective() ), EnumCodeSystem.OID_SNOMED_CT.getValue(),((AdvanceDirectives)objModel).getDirective() )); //1st field

			observation.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			observation.setEffectiveTime(PopulateDataType.populateIVLTS(((AdvanceDirectives)objModel).getEffectiveTimes()));
			e.setReference(PopulateDataType.populateTEl("#AD1"));
			observation.getValue().add(PopulateDataType.populateCDAndED(SNOMEDCode.getCode(((AdvanceDirectives)objModel).getDescription()),  EnumCodeSystem.OID_SNOMED_CT.getValue(), ((AdvanceDirectives)objModel).getDescription(),e)); // 2nd feild
			observation.setText(e);
			observation.getParticipant().add(getParticipantData(objModel));
			observation.getEntryRelationship().add(entryrelationship);
			entryrelationship.setTypeCode(XActRelationshipEntryRelationship.REFR);
			entryrelationship.setObservation(obs);
			obs.getClassCode().add(EnumClassCode.OBS.getValue());
			obs.setMoodCode(XActMoodDocumentObservation.EVN);
			obs.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.ADVANCE_DIRECTIVES_STATUS_OBSERVATION_TEMPLATE));
			obs.setCode(PopulateDataType.populateCD(EnumLOINC.DIAGNOSIS_STATUS.getValue(), EnumCodeSystem.OID_LOINC.getValue(), CCDConstants.STATUS)); 
			obs.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			obs.getValue().add(PopulateDataType.populateCE(SNOMEDCode.getCode("Current and verified"), EnumCodeSystem.OID_SNOMED_CT.getValue(), "Current and verified"));
			ref.setTypeCode(XActRelationshipExternalReference.REFR);
			exdoc.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.ADVANCE_DIRECTIVES_REFERENCE_TEMPLATE));
			exdoc.getId().add(PopulateDataType.populateII(StandardMethods.generateUUID()));
			exdoc.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(((AdvanceDirectives)objModel).getSupportingDocument()), EnumCodeSystem.OID_SNOMED_CT.getValue(), ((AdvanceDirectives)objModel).getSupportingDocument())); //4th field
			ED ed= new ED();
			ed.setMediaType(((AdvanceDirectives)objModel).getMediaType());
			ed.setReference(PopulateDataType.populateTEl(((AdvanceDirectives)objModel).getSupportingDocumentLink()));
			exdoc.setText(ed);
			ref.setExternalDocument(exdoc);
			observation.getReference().add(ref);
		}
		break;
		case "FunctionalStatus" : 
		{
			//Creating ACT object
			Act act = objFactory.createAct();
			act.setClassCode(XActClassDocumentEntryAct.ACT);
			act.setMoodCode(XDocumentActMood.EVN);
			act.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PROBLEM_ACT_TEMPLATE)); //<!-- Problem act template -->
			act.getId().add(PopulateDataType.populateII(actID));
			act.setCode(PopulateDataType.populateCDNullFalvor(CCDConstants.NULLFLAVOR_NA));

			//Creating and populating ACT's Entry Relationship object
			EntryRelationship entryrelationship = objFactory.createEntryRelationship();
			entryrelationship.setTypeCode(XActRelationshipEntryRelationship.SUBJ);
			act.getEntryRelationship().add(entryrelationship);

			//Creating and populating Observation object
			Observation obs = objFactory.createObservation();
			obs.getClassCode().add(EnumClassCode.OBS.getValue());
			obs.setMoodCode(XActMoodDocumentObservation.EVN);
			obs.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PROBLEM_OBSERVATION_TEMPLATE)); //<!-- Problem observation template -->
			obs.getId().add(PopulateDataType.populateII(obsID));
			obs.setCode(PopulateDataType.populateCD(CCDConstants.ASSERTION,EnumCodeSystem.OID_HL7_ACTCODE.getValue())); 
			obs.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			obs.setEffectiveTime(PopulateDataType.populateIVLTS(((FunctionalStatus) objModel).getEffectiveDates()));

			////<effectiveTime><low value="1998"/></effectiveTime>
			obs.getValue().add(PopulateDataType.populateCD(SNOMEDCode.getCode(((FunctionalStatus) objModel).getFunctionalCondition()),EnumCodeSystem.OID_SNOMED_CT.getValue(),((FunctionalStatus) objModel).getFunctionalCondition()));
			//Creating and populating  Observation's Entry Relationship object
			EntryRelationship rel = objFactory.createEntryRelationship();
			rel.setTypeCode(XActRelationshipEntryRelationship.REFR);
			obs.getEntryRelationship().add(rel);
			Observation obs1 = objFactory.createObservation();
			entryrelationship.setObservation(obs1);
			obs1.getClassCode().add(EnumClassCode.OBS.getValue());
			obs1.setMoodCode(XActMoodDocumentObservation.EVN);
			obs1.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.STATUS_OF_FUNCTIONAL_STATUS_OBSERVATION_TEMPLATE));
			obs1.setCode(PopulateDataType.populateCD(EnumLOINC.STATUS.getValue(),EnumCodeSystem.OID_LOINC.getValue(),CCDConstants.STATUS )); 
			obs1.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			obs1.getValue().add(PopulateDataType.populateCE(SNOMEDCode.getCode(((FunctionalStatus) objModel).getConditionStatus()), EnumCodeSystem.OID_SNOMED_CT.getValue(), ((FunctionalStatus) objModel).getConditionStatus())); 
			rel.setObservation(obs1);
			entryrelationship.setObservation(obs);

			entry.setAct(act);
		}break;
		case "Problems" : 
		{
			Act act = objFactory.createAct();
			entry.setAct(act);
			act.setClassCode(XActClassDocumentEntryAct.ACT);
			act.setMoodCode(XDocumentActMood.EVN);
			act.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PROBLEM_ACT_TEMPLATE)); //<!-- Problem act template -->
			act.getId().add(PopulateDataType.populateII(actID));
			act.setCode(PopulateDataType.populateCDNullFalvor(CCDConstants.NULLFLAVOR_NA));

			EntryRelationship entryrelationship =objFactory.createEntryRelationship();
			entryrelationship.setTypeCode(XActRelationshipEntryRelationship.SUBJ);
			act.getEntryRelationship().add(entryrelationship);

			Observation obs = objFactory.createObservation();
			obs.getClassCode().add(EnumClassCode.OBS.getValue());
			obs.setMoodCode(XActMoodDocumentObservation.EVN);
			obs.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PROBLEM_OBSERVATION_TEMPLATE)); //<!-- Problem observation template -->
			obs.getId().add(PopulateDataType.populateII(obsID));
			obs.setCode(PopulateDataType.populateCD(CCDConstants.ASSERTION,EnumCodeSystem.OID_HL7_ACTCODE.getValue()));
			obs.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			obs.setEffectiveTime(PopulateDataType.populateIVLTS(((Problems) objModel).getEffectiveDates()));

			////<effectiveTime><low value="1998"/></effectiveTime>
			obs.getValue().add(PopulateDataType.populateCD(SNOMEDCode.getCode(((Problems) objModel).getCondition()),EnumCodeSystem.OID_SNOMED_CT.getValue(), ((Problems) objModel).getCondition()));

			EntryRelationship rel = new EntryRelationship();
			rel.setTypeCode(XActRelationshipEntryRelationship.REFR);
			obs.getEntryRelationship().add(rel);
			Observation obs1 = objFactory.createObservation();
			entryrelationship.setObservation(obs1);
			obs1.getClassCode().add(EnumClassCode.OBS.getValue());
			obs1.setMoodCode(XActMoodDocumentObservation.EVN);
			obs1.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PROBLEM_STATUS_OBSERVATION_TEMPLATE));
			obs1.setCode(PopulateDataType.populateCD(EnumLOINC.DIAGNOSIS_STATUS.getValue(),EnumCodeSystem.OID_LOINC.getValue(), CCDConstants.STATUS));
			obs1.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			obs1.getValue().add(PopulateDataType.populateCE(SNOMEDCode.getCode(((Problems) objModel).getCondition()),EnumCodeSystem.OID_SNOMED_CT.getValue(), ((Problems) objModel).getConditionStatus()));
			rel.setObservation(obs1);
			entryrelationship.setObservation(obs);
			if(!(episodeObsPresentPD.add(((Problems) objModel).getCondition())))
			{
				act.getEntryRelationship().add(getEpisodeObservation(actID, obsID, ((Problems) objModel).getEffectiveDates(), SNOMEDCode.getCode("Clinical finding"), EnumCodeSystem.OID_SNOMED_CT.getValue(),  "Clinical finding"));
			}
			entry.setAct(act);

		} break;

		case "SocialHistory" : {
			Observation obs = objFactory.createObservation();
			obs.getClassCode().add(EnumClassCode.OBS.getValue());
			obs.setMoodCode(XActMoodDocumentObservation.EVN);
			obs.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.SOCIAL_HISTORY_OBSERVATION_TEMPLATE)); //<!-- Social history observation template -->
			obs.getId().add(PopulateDataType.populateII(obsID));
			obs.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(((SocialHistory) objModel).getSocialHistoryElement()),EnumCodeSystem.OID_SNOMED_CT.getValue(), ((SocialHistory) objModel).getSocialHistoryElement()));
			obs.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			obs.setEffectiveTime(PopulateDataType.populateIVLTS(((SocialHistory) objModel).getEffectiveDates()));
			obs.getValue().add(PopulateDataType.populateST(((SocialHistory) objModel).getDescription()));

			if(!(episodeObsPresentSH.add(((SocialHistory) objModel).getSocialHistoryElement())))
			{
				obs.getEntryRelationship().add(getEpisodeObservation( obsID, SNOMEDCode.getCode(((SocialHistory) objModel).getSocialHistoryElement()), EnumCodeSystem.OID_SNOMED_CT.getValue(), ((SocialHistory) objModel).getSocialHistoryElement()));
			}
			entry.setObservation(obs);
		}
		break;
		case "Alerts" : {
			//Creating ACT object
			Act act = objFactory.createAct();
			act.setClassCode(XActClassDocumentEntryAct.ACT);
			act.setMoodCode(XDocumentActMood.EVN);
			act.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PROBLEM_ACT_TEMPLATE)); //<!-- Problem act template -->
			act.getId().add(PopulateDataType.populateII(actID));
			act.setCode(PopulateDataType.populateCDNullFalvor(CCDConstants.NULLFLAVOR_NA));

			EntryRelationship entryrelationship = objFactory.createEntryRelationship();
			entryrelationship.setTypeCode(XActRelationshipEntryRelationship.SUBJ);
			act.getEntryRelationship().add(entryrelationship);

			Observation obs = objFactory.createObservation();
			obs.getClassCode().add(EnumClassCode.OBS.getValue());
			obs.setMoodCode(XActMoodDocumentObservation.EVN);
			obs.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.ALERT_OBSERVATION_TEMPLATE)); //Alert observation template
			obs.getId().add(PopulateDataType.populateII(obsID));
			obs.setCode(PopulateDataType.populateCD(CCDConstants.ASSERTION,EnumCodeSystem.OID_HL7_ACTCODE.getValue()));
			obs.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			obs.getValue().add(PopulateDataType.populateCD(SNOMEDCode.getCode(CCDConstants.ADVERSE_REACTION_TO_SUBSTANCE),EnumCodeSystem.OID_SNOMED_CT.getValue(), CCDConstants.ADVERSE_REACTION_TO_SUBSTANCE));
			obs.getParticipant().add(getParticipantData(objModel));

			EntryRelationship rel = objFactory.createEntryRelationship();
			rel.setTypeCode(XActRelationshipEntryRelationship.MFST);
			rel.setInversionInd(true);
			obs.getEntryRelationship().add(rel);

			Observation obs1 = objFactory.createObservation();

			obs1.getClassCode().add(EnumClassCode.OBS.getValue());
			obs1.setMoodCode(XActMoodDocumentObservation.EVN);
			obs1.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.REACTION_OBSERVATION_TEMPLATE)); //Reaction observation template
			obs1.setCode(PopulateDataType.populateCD(CCDConstants.ASSERTION,EnumCodeSystem.OID_HL7_ACTCODE.getValue()));
			obs1.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			obs1.getValue().add(PopulateDataType.populateCD(SNOMEDCode.getCode(((Alerts) objModel).getReaction()), EnumCodeSystem.OID_SNOMED_CT.getValue(), ((Alerts) objModel).getReaction()));
			rel.setObservation(obs1);
			entryrelationship.setObservation(obs1);

			entryrelationship.setObservation(obs);
			EntryRelationship rel1 = objFactory.createEntryRelationship();
			rel1.setTypeCode(XActRelationshipEntryRelationship.REFR);

			Observation obs2 = objFactory.createObservation();
			obs2.getClassCode().add(EnumClassCode.OBS.getValue());
			obs2.setMoodCode(XActMoodDocumentObservation.EVN);
			obs2.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.ALERT_STATUS_OBSERVATION_TEMPLATE)); //Alert status observation template
			obs2.setCode(PopulateDataType.populateCD(EnumLOINC.STATUS.getValue(), EnumCodeSystem.OID_LOINC.getValue(), CCDConstants.STATUS)); 
			obs2.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			if(((Alerts) objModel).getStatus()!=null||((Alerts) objModel).getStatus().equals(""))
				obs2.getValue().add(PopulateDataType.populateCE(SNOMEDCode.getCode( ((Alerts) objModel).getStatus()), EnumCodeSystem.OID_SNOMED_CT.getValue(), ((Alerts) objModel).getStatus())); 
			else
				obs2.getValue().add(PopulateDataType.populateCE(SNOMEDCode.getCode(CCDConstants.NO_KNOWN_ALLERGIES), EnumCodeSystem.OID_SNOMED_CT.getValue(), CCDConstants.NO_KNOWN_ALLERGIES));

			rel1.setObservation(obs2);
			entryrelationship.setObservation(obs2);
			obs.getEntryRelationship().add(rel1);
			entry.setAct(act);
			entryrelationship.setObservation(obs);
		}break;
		case "Medications" : 
		{
			SubstanceAdministration objSubAdmin = objFactory.createSubstanceAdministration();
			objSubAdmin.setMoodCode(XDocumentSubstanceMood.EVN);
			objSubAdmin.getClassCode().add(EnumClassCode.SBADM.getValue());
			objSubAdmin.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.MEDICATION_ACTIVITY_TEMPLATE));
			objSubAdmin.getId().add(PopulateDataType.populateII(actID));
			
			Boolean flag=false;
			if(((Medications)objModel).getStatus()!=null){
				if(((Medications)objModel).getStatus().trim().equalsIgnoreCase(EnumStatusCode.ACTIVE.getValue())){
					objSubAdmin.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.ACTIVE.getValue()));
				}			
				else if(((Medications)objModel).getStatus().trim().equalsIgnoreCase(CCDConstants.NO_LONGER_ACTIVE)){
					flag=true;
					objSubAdmin.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
				}
				else{
					objSubAdmin.setStatusCode(PopulateDataType.populateCS(((Medications)objModel).getStatus()));
				}
			}
			if(((Medications)objModel).getDate()!=null){
				objSubAdmin.getEffectiveTime().add(PopulateDataType.populateIVLTS(((Medications)objModel).getDate()));
				
				if(flag && ((Medications)objModel).getDate().getLow()!=null && !((Medications)objModel).getDate().getLow().isEmpty()){
					if(((Medications)objModel).getDate().getHigh()==null || ((Medications)objModel).getDate().getHigh().isEmpty()){
						
						IVLTS objIVLTS=(IVLTS) objSubAdmin.getEffectiveTime().get(0);
						IVXBTS valueHigh = new IVXBTS();
						valueHigh.getNullFlavor().add("UNK");
						JAXBElement<IVXBTS> objIVXBTSHigh=objFactory.createIVLTSHigh(valueHigh);
						objIVLTS.getRest().add(objIVXBTSHigh);
					}
				}
				flag=true;
			}
			objSubAdmin.getEffectiveTime().add(PopulateDataType.populatePIVLTS(((Medications) objModel).getPeriodUnit(),((Medications) objModel).getPeriodValue(),flag)); 
			objSubAdmin.setRouteCode(PopulateDataType.populateCE(null,EnumCodeSystem.OID_HL7_ROUTE_OF_ADMINISTRATION.getValue(),((Medications) objModel).getMedication()));
			objSubAdmin.setDoseQuantity(PopulateDataType.populateIVLPQ(((Medications) objModel).getDoseQuantity()));
			objSubAdmin.setAdministrationUnitCode(PopulateDataType.populateCE(SNOMEDCode.getCode(((Medications) objModel).getInstructions()),EnumCodeSystem.OID_SNOMED_CT.getValue(),((Medications) objModel).getInstructions()));
			Consumable Consumable = objFactory.createConsumable();

			ManufacturedProduct manufacturedProduct  = objFactory.createManufacturedProduct();
			manufacturedProduct.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PRODUCT_TEMPLATE));
			Material material = objFactory.createMaterial();
			material.setCode(PopulateDataType.populateCEAndED(SNOMEDCode.getCode(((Medications) objModel).getMedication()),EnumCodeSystem.OID_RXNORM.getValue(),((Medications) objModel).getMedication()));
			manufacturedProduct.setManufacturedMaterial(material);
			Consumable.setManufacturedProduct(manufacturedProduct);
			objSubAdmin.setConsumable(Consumable);

			if(((Medications) objModel).getCriterion() != null)
			{
				Precondition precondition = objFactory.createPrecondition();
				precondition.getTypeCode().add(EnumTypeCode.PRCN.getValue());
				Criterion criterion = objFactory.createCriterion();
				criterion.setCode(PopulateDataType.populateCD(CCDConstants.ASSERTION,EnumCodeSystem.OID_HL7_ACTCODE.getValue()));
				criterion.setValue(PopulateDataType.populateCE(SNOMEDCode.getCode(((Medications) objModel).getCriterion()),EnumCodeSystem.OID_SNOMED_CT.getValue(),((Medications) objModel).getCriterion() ));
				precondition.setCriterion(criterion);
				objSubAdmin.getPrecondition().add(precondition);
			}
			entry.setSubstanceAdministration(objSubAdmin);
		}break ;
		case "MedicalEquipment" : 
		{
			Supply supply = objFactory.createSupply();
			entry.setSupply(supply);
			supply.setClassCode(ActClassSupply.SPLY);
			supply.setMoodCode(XDocumentSubstanceMood.EVN);
			supply.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.SUPPLY_ACTIVITY_TEMPLATE));
			supply.getId().add(PopulateDataType.populateII(actID));
			supply.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			supply.getEffectiveTime().add(PopulateDataType.populateSXCMTS(((MedicalEquipment) objModel).getDateSupplied()));
			supply.getParticipant().add(getParticipantData(objModel));
			entry.setSupply(supply);
		} break ;
		case "Immunizations" : 
		{
			SubstanceAdministration substanceAdministration = objFactory.createSubstanceAdministration();
			substanceAdministration.getClassCode().add(EnumClassCode.SBADM.getValue());
			substanceAdministration.setMoodCode(XDocumentSubstanceMood.EVN);
			substanceAdministration.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.MEDICATION_ACTIVITY_TEMPLATE));
			substanceAdministration.getId().add(PopulateDataType.populateII(actID));
			substanceAdministration.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			substanceAdministration.getEffectiveTime().add(PopulateDataType.populateIVLTS(((Immunizations) objModel).getDate()));
			substanceAdministration.setRouteCode(PopulateDataType.populateCE(EnumRouteCode.IMMUNIZATION_ROUTECODE.getValue(),EnumCodeSystem.OID_HL7_ROUTE_OF_ADMINISTRATION.getValue(),EnumCodeSystem.MEDICATIONS_ROUTE_CODE_SYSTEM.getValue(),((Immunizations) objModel).getMoodOfVaccine()));

			Consumable Consumable = objFactory.createConsumable();
			ManufacturedProduct manufacturedProduct  = objFactory.createManufacturedProduct();
			manufacturedProduct.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PRODUCT_TEMPLATE));
			Material material = objFactory.createMaterial();
			material.setCode(PopulateDataType.populateCEAndED(null,EnumCodeSystem.IMMUNIZATION_MATERIAL_CODE_SYSTEM.getValue(),((Immunizations) objModel).getVaccine()));

			manufacturedProduct.setManufacturedMaterial(material);
			Consumable.setManufacturedProduct(manufacturedProduct);
			substanceAdministration.setConsumable(Consumable);
			entry.setSubstanceAdministration(substanceAdministration);

		} break ;
		case "Results" : 
		{
			Organizer organizer = objFactory.createOrganizer();

			organizer.setClassCode(XActClassDocumentEntryOrganizer.BATTERY);
			organizer.getMoodCode().add(EnumMoodCode.EVN.getValue()); 
			organizer.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.RESULT_ORGANIZER_TEMPLATE));
			organizer.getId().add(PopulateDataType.populateII(actID));

			organizer.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			organizer.setEffectiveTime(PopulateDataType.populateIVLTS(((Results)objModel).getTestDate())); 

			List<TestReport> listTestReport=(List<TestReport>) ((Results) objModel).getTestDetails();

			if(listTestReport!=null && listTestReport.size()>0){
				for(TestReport report:listTestReport){
					if(report== null||report.getResultTypeCode()==null || report.getResultTypeCode().isEmpty()
							||report.getTestName()==null || report.getTestName().isEmpty())
					{
						continue;
					}
					organizer.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(report.getResultTypeCode()),EnumCodeSystem.OID_SNOMED_CT.getValue(),report.getResultTypeCode())); //testName
					Component4 comp = objFactory.createComponent4();
					Observation obs = objFactory.createObservation();
					obs.getClassCode().add(EnumClassCode.OBS.getValue());
					obs.setMoodCode(XActMoodDocumentObservation.EVN);
					obs.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.RESULT_OBSERVATION_TEMPLATE));
					obs.getId().add(PopulateDataType.populateII(actID));
					obs.setCode(PopulateDataType.populateCD(null,EnumCodeSystem.OID_LOINC.getValue() ,report.getTestName())); // loinc code added
					obs.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
					obs.setEffectiveTime(PopulateDataType.populateIVLTS(((Results)objModel).getTestDate()));
					
					String reportValue=report.getValue();
					String numericValue=null;
					if(reportValue!=null){
						/*Pattern used to retrieve the first numeric value(Integers and floats) till a non numeric character(except dot(.)) is encountered*/
						Pattern pattern = Pattern.compile("[0-9.]+");
					    Matcher matcher = pattern.matcher(reportValue);
					    if (matcher.find()) {
					    	numericValue=reportValue.substring(matcher.start(),matcher.end());
					    }
					}
					obs.getValue().add(PopulateDataType.populatePQ(numericValue,report.getUnit()));
					obs.getInterpretationCode().add(PopulateDataType.populateCE(CCDConstants.CONFIDENTIALITY_CODE,EnumCodeSystem.OID_OBSERVATION_INTERPRETATION_CODE_SYSTEM.getValue()));
					ReferenceRange ref = objFactory.createReferenceRange();
					ObservationRange obsRange = objFactory.createObservationRange();

					if(report.getRange()!=null){
						int firstOcc=report.getRange().indexOf('-');
						if(report.getRange().contains("-") && report.getRange().indexOf('-', firstOcc+1)==-1){
							String[] rangeValues=null;
							rangeValues=report.getRange().split("-");

							if(rangeValues.length>=2){
								IVLPQ objIVLPQ=new IVLPQ();
								IVXBPQ objLow=new IVXBPQ();

								objLow.setValue(rangeValues[0].trim());
								objLow.setUnit(report.getUnit());			
								JAXBElement<IVXBPQ> objPQLow=objFactory.createIVLPQLow(objLow);

								IVXBPQ objHigh=new IVXBPQ();
								objHigh.setValue(rangeValues[1].trim());
								objHigh.setUnit(report.getUnit());
								JAXBElement<IVXBPQ> objPQHigh=objFactory.createIVLPQHigh(objHigh);

								objIVLPQ.getRest().add(objPQLow);
								objIVLPQ.getRest().add(objPQHigh);
								obsRange.setValue(objIVLPQ);	
							}

						}
						else
						{
							obsRange.setText(PopulateDataType.populateED(report.getRange()+report.getUnit()));

						}
					}

					ref.setObservationRange(obsRange);
					obs.getReferenceRange().add(ref);
					comp.setObservation(obs);
					organizer.getComponent().add(comp);
					entry.setOrganizer(organizer);
					entry.setTypeCode(XActRelationshipEntry.DRIV);
				}
			}

		}			
		return entry;

		case "Procedures" : 
		{
			Procedures objProcedures =(Procedures) objModel;
			if(objProcedures.getProcedureActivityName()!=null && !objProcedures.getProcedureActivityName().isEmpty())
			{
				String strProcedureActivityName=objProcedures.getProcedureActivityName();
				if(strProcedureActivityName.equalsIgnoreCase(EnumEntryDataSection.OBSERVATION.getValue()))
				{
					Observation obs= objFactory.createObservation();
					obs.getClassCode().add(EnumClassCode.OBS.getValue());
					obs.setMoodCode(XActMoodDocumentObservation.EVN);
					obs.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PROCEDURE_ACTIVITY_TEMPLATE));
					obs.getId().add(PopulateDataType.populateII(actID));
					String proObs = ((Procedures) objModel).getProcedure()+","+ ((Procedures) objModel).getObserve();
					obs.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(proObs), EnumCodeSystem.OID_SNOMED_CT.getValue(), objModel,"#Proc1"));
					obs.setStatusCode(PopulateDataType.populateCS(((Procedures) objModel).getProcedureStatusCode()));
					obs.setEffectiveTime(PopulateDataType.populateIVLTS(((Procedures) objModel).getDate()));
					obs.getParticipant().add(getParticipantData(objModel));
					entry.setObservation(obs);
					
				}
				else if(strProcedureActivityName.equalsIgnoreCase(EnumEntryDataSection.ACT.getValue()))
				{
					Act act = objFactory.createAct();
					act.setClassCode(XActClassDocumentEntryAct.ACT);
					act.setMoodCode(XDocumentActMood.EVN);
					act.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PROCEDURE_ACTIVITY_TEMPLATE));
					act.getId().add(PopulateDataType.populateII(actID));
					String proObs = ((Procedures) objModel).getProcedure()+","+ ((Procedures) objModel).getObserve();
					act.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(proObs), EnumCodeSystem.OID_SNOMED_CT.getValue(), objModel,"#Proc1"));
					act.setStatusCode(PopulateDataType.populateCS(((Procedures) objModel).getProcedureStatusCode()));
					act.setEffectiveTime(PopulateDataType.populateIVLTS(((Procedures) objModel).getDate()));
					act.getParticipant().add(getParticipantData(objModel));
					entry.setAct(act);
				}
				else if(strProcedureActivityName.equalsIgnoreCase(EnumEntryDataSection.PROCEDURE.getValue()))
				{
					Procedure objProcedure = objFactory.createProcedure();
					objProcedure.getClassCode().add(EnumClassCode.PROC.getValue());
					objProcedure.setMoodCode(XDocumentProcedureMood.EVN);
					objProcedure.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PROCEDURE_ACTIVITY_TEMPLATE));
					objProcedure.getId().add(PopulateDataType.populateII(actID));
					String proObs = ((Procedures) objModel).getProcedure()+","+ ((Procedures) objModel).getObserve();
					objProcedure.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(proObs), EnumCodeSystem.OID_SNOMED_CT.getValue(), objModel,"#Proc1"));
					objProcedure.setStatusCode(PopulateDataType.populateCS(((Procedures) objModel).getProcedureStatusCode()));
					objProcedure.setEffectiveTime(PopulateDataType.populateIVLTS(((Procedures) objModel).getDate()));
					objProcedure.getParticipant().add(getParticipantData(objModel));
					entry.setProcedure(objProcedure);
				}
				else
				{
					CCDLogger objCCDLogger = CCDLogger.getLogger();
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","InvalidProcedureActivity"));
				}
			}
			else
			{
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","MissingProcedureActivity"));
			}
			

		} break ;
		case "Encounters" : 
		{
			Encounter enc = objFactory.createEncounter();
			enc.getClassCode().add(EnumClassCode.ENC.getValue());
			enc.setMoodCode(XDocumentEncounterMood.EVN);
			enc.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.ENCOUNTER_ACTIVITY_TEMPLATE));
			enc.getId().add(PopulateDataType.populateII(actID));
			enc.setCode(PopulateDataType.populateCEAndED(CCDConstants.GENRL,EnumCodeSystem.OID_ACTCODE.getValue(),CCDConstants.GENERAL,((Encounters) objModel).getEncounter()));
			enc.setEffectiveTime(PopulateDataType.populateIVLTS(((Encounters) objModel).getDate()));
			if(((Encounters) objModel).getLocation()!=null)
			{
				for(String objModelEn: ((Encounters) objModel).getLocation())
				{
					Participant2 objParticipant = objFactory.createParticipant2();

					ParticipantRole  participantRole  = objFactory.createParticipantRole ();
					PlayingEntity playingEntity = objFactory.createPlayingEntity();
					objParticipant.getTypeCode().add(EnumTypeCode.LOC.getValue());
					objParticipant.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.ENCOUNTER_COVERAGE_ID));
					participantRole.getClassCode().add(EnumClassCode.SDLOC.getValue());
					participantRole.getId().add(PopulateDataType.populateII( EnumCodeSystem.OID_HL7.getValue()));
					playingEntity.getClassCode().add(EnumClassCode.PLC.getValue());
					playingEntity.getName().add(PopulateDataType.populatePN(objModelEn));
					participantRole.setPlayingEntity(playingEntity);
					objParticipant.setParticipantRole(participantRole);

					enc.getParticipant().add(objParticipant);
				}
			}

			entry.setTypeCode(XActRelationshipEntry.DRIV);
			entry.setEncounter(enc);
		} break ;
		case "Plan" : 
		{
			Plan objPlan=(Plan)objModel;
			if(objPlan.getPlanOfCareActivityName()!=null && !objPlan.getPlanOfCareActivityName().isEmpty())
			{
				String pendingEvent=objPlan.getPlanOfCareActivityName();
				String activityValue=null;

				if(objPlan.getActivityMoodCode()!=null && !objPlan.getActivityMoodCode().isEmpty())
				{
					for(int i=0; i<EnumMoodCode.values().length;i++){
						if(objPlan.getActivityMoodCode().equalsIgnoreCase(EnumMoodCode.values()[i].getValue()))
							activityValue=EnumMoodCode.values()[i].getValue();	
					}
					if(pendingEvent.equalsIgnoreCase(EnumEntryDataSection.OBSERVATION.getValue()))
					{
						Observation obs= objFactory.createObservation();
						if(activityValue!=EnumMoodCode.ARQ.getValue())
						{
							obs.getClassCode().add(EnumClassCode.OBS.getValue());
							obs.setMoodCode(XActMoodDocumentObservation.valueOf(activityValue));
							obs.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PLAN_ACTIVITY_TEMPLATE));
							obs.getId().add(PopulateDataType.populateII(actID));
							obs.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(objPlan.getPlannedActivity()),EnumCodeSystem.OID_SNOMED_CT.getValue(),objPlan.getPlannedActivity()));
							obs.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.NEW.getValue()));
							obs.setEffectiveTime(PopulateDataType.populateIVLTS(objPlan.getPlannedDate()));
							entry.setObservation(obs);
						}
					}
					else if(pendingEvent.equalsIgnoreCase(EnumEntryDataSection.ACT.getValue()))
					{
						Act act = objFactory.createAct();
						if(activityValue!=EnumMoodCode.GOL.getValue())
						{
							act.setClassCode(XActClassDocumentEntryAct.ACT);
							act.setMoodCode(XDocumentActMood.valueOf(activityValue));
							act.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PLAN_ACTIVITY_TEMPLATE));
							act.getId().add(PopulateDataType.populateII(actID));
							act.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(objPlan.getPlannedActivity()),EnumCodeSystem.OID_SNOMED_CT.getValue(),objPlan.getPlannedActivity()));
							act.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.NEW.getValue()));
							act.setEffectiveTime(PopulateDataType.populateIVLTS(objPlan.getPlannedDate()));
							entry.setAct(act);
						}
					}
					else if(pendingEvent.equalsIgnoreCase(EnumEntryDataSection.ENCOUNTER.getValue()))
					{
						Encounter enc = objFactory.createEncounter();
						if(activityValue!=EnumMoodCode.GOL.getValue())
						{	
							enc.getClassCode().add(EnumClassCode.ENC.getValue());
							enc.setMoodCode(XDocumentEncounterMood.valueOf(activityValue));
							enc.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PLAN_ACTIVITY_TEMPLATE));
							enc.getId().add(PopulateDataType.populateII(actID));
							enc.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(objPlan.getPlannedActivity()),EnumCodeSystem.OID_SNOMED_CT.getValue(),objPlan.getPlannedActivity()));
							enc.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.NEW.getValue()));
							enc.setEffectiveTime(PopulateDataType.populateIVLTS(objPlan.getPlannedDate()));
							entry.setEncounter(enc);
						}
					}
					else if(pendingEvent.equalsIgnoreCase(EnumEntryDataSection.SUPPLY.getValue()))
					{
						Supply sup=objFactory.createSupply();
						if(activityValue!=EnumMoodCode.GOL.getValue()||activityValue!=EnumMoodCode.ARQ.getValue())
						{
							sup.setClassCode(ActClassSupply.SPLY);
							sup.setMoodCode(XDocumentSubstanceMood.valueOf(activityValue));
							sup.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PLAN_ACTIVITY_TEMPLATE));
							sup.getId().add(PopulateDataType.populateII(actID));
							sup.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(objPlan.getPlannedActivity()),EnumCodeSystem.OID_SNOMED_CT.getValue(),objPlan.getPlannedActivity()));
							sup.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.NEW.getValue()));
							sup.getEffectiveTime().add(PopulateDataType.populateIVLTS(objPlan.getPlannedDate()));
							entry.setSupply(sup);
						}
					}
					else if(pendingEvent.equalsIgnoreCase(EnumEntryDataSection.PROCEDURE.getValue()))
					{
						Procedure pro=objFactory.createProcedure();
						if(activityValue!=EnumMoodCode.GOL.getValue())
						{
							pro.getClassCode().add(EnumClassCode.PROC.getValue());
							pro.setMoodCode(XDocumentProcedureMood.valueOf(activityValue));
							pro.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PLAN_ACTIVITY_TEMPLATE));
							pro.getId().add(PopulateDataType.populateII(actID));
							pro.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(objPlan.getPlannedActivity()),EnumCodeSystem.OID_SNOMED_CT.getValue(),objPlan.getPlannedActivity()));
							pro.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.NEW.getValue()));
							pro.setEffectiveTime(PopulateDataType.populateIVLTS(objPlan.getPlannedDate()));
							entry.setProcedure(pro);	
						}
					}
					else if(pendingEvent.equalsIgnoreCase(EnumEntryDataSection.SUBSTANCEADMINISTRATION.getValue()))
					{
						SubstanceAdministration sub =objFactory.createSubstanceAdministration();
						if(activityValue!=EnumMoodCode.GOL.getValue()||activityValue!=EnumMoodCode.ARQ.getValue())
						{
							sub.getClassCode().add(EnumClassCode.SBADM.getValue());
							sub.setMoodCode(XDocumentSubstanceMood.valueOf(activityValue));
							sub.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PLAN_ACTIVITY_TEMPLATE));
							sub.getId().add(PopulateDataType.populateII(actID));
							sub.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(objPlan.getPlannedActivity()),EnumCodeSystem.OID_SNOMED_CT.getValue(),objPlan.getPlannedActivity()));
							sub.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.NEW.getValue()));
							sub.getEffectiveTime().add(PopulateDataType.populateIVLTS(objPlan.getPlannedDate()));
							entry.setSubstanceAdministration(sub);
						}
					}
					else
					{
						CCDLogger objCCDLogger = CCDLogger.getLogger();
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","InvalidPlanofCareActivity"));
					}
				}
				else
				{
					CCDLogger objCCDLogger = CCDLogger.getLogger();
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","InvalidChoiceMoodCode",objPlan.getPlanOfCareActivityName()));
				}


			}
			else
			{
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","MissingPlanofCareActivity"));
			}
		}break;
		default:
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","InvalidChoice"));
		}
		return entry;

	}
	/**
	 *  Method is used to create and add Entry data in Document Body for vital sign
	 *  @param actID - ACt ID 
	 *  @param hm - Map of vitals and it's value
	 *   @param date - Date for Vitals 
	 *  @return entry - {@link Entry} object of Entry
	 */
	private static Entry getEntryData(String actID,Map<String, String> hm, String date) {
		Entry entry = objFactory.createEntry();
		entry.setTypeCode(XActRelationshipEntry.DRIV);

		Organizer organizer = objFactory.createOrganizer();
		organizer.setClassCode(XActClassDocumentEntryOrganizer.CLUSTER);
		organizer.getMoodCode().add(EnumMoodCode.EVN.getValue()); 
		organizer.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.VITAL_SIGNS_ORGANIZER_TEMPLATE));
		organizer.getId().add(PopulateDataType.populateII(actID));
		organizer.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(CCDConstants.VITAL_SIGNS),EnumCodeSystem.OID_SNOMED_CT.getValue() ,CCDConstants.VITAL_SIGNS));
		organizer.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
		organizer.setEffectiveTime(PopulateDataType.populateIVLTS(date)); 
		for (Map.Entry<String,String> data : hm.entrySet())
		{
			if(data.getKey().equalsIgnoreCase("Blood Pressure"))
			{
				String range[] =data.getValue().split("/|\\s");
				organizer.getComponent().add(getComponent("Systolic BP",range[0],range[2],date));
				organizer.getComponent().add(getComponent("Diastolic BP",range[1],range[2],date));
			}
			else
			{
				String range[] =data.getValue().split("/|\\s");
				organizer.getComponent().add(getComponent(data.getKey(),range[0],range[1],date));

			}

		}
		entry.setOrganizer(organizer);
		return entry;

	}
	/**
	 *  Method is used to create and add Component in Entry Data
	 *  @param key 
	 *  @param val 
	 *  @param unit 
	 *  @param date 
	 *  @return objComponent4 - {@link Component4} object of Component4
	 */
	private static Component4 getComponent(String key,String val,String unit, String date) {

		Component4 objComponent4 = objFactory.createComponent4();
		Observation obs = objFactory.createObservation();

		obs.getClassCode().add(EnumClassCode.OBS.getValue());
		obs.setMoodCode(XActMoodDocumentObservation.EVN);
		obs.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.RESULT_OBSERVATION_TEMPLATE));
		obs.getId().add(PopulateDataType.populateII(StandardMethods.generateUUID()));
		obs.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(key),EnumCodeSystem.OID_SNOMED_CT.getValue() ,key));
		obs.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
		obs.setEffectiveTime(PopulateDataType.populateIVLTS(date));
		obs.getValue().add(PopulateDataType.populatePQ(val,unit));
		objComponent4.setObservation(obs);
		return objComponent4;
	}
	/**
	 *  Method is used to create and add Participant in Entry Data
	 *  @param objModel - Object of given Model
	 *  @return objParticipant - Return {@link Participant2} Participant2
	 */
	private static Participant2 getParticipantData(Object objModel) {
		Participant2 objParticipant = objFactory.createParticipant2();

		switch(objModel.getClass().getSimpleName())
		{
		case "Alerts" : {
			objParticipant.getTypeCode().add(EnumTypeCode.CSM.getValue());
			ParticipantRole participantRole = objFactory.createParticipantRole();
			participantRole.getClassCode().add(EnumClassCode.MANU.getValue());

			//Adding Playing Entity  in Participant
			PlayingEntity playingEntity = objFactory.createPlayingEntity();
			playingEntity.getClassCode().add(EnumClassCode.MMAT.getValue());
			playingEntity.setCode(PopulateDataType.populateCE(null,EnumCodeSystem.OID_RXNORM.getValue(),((Alerts) objModel).getSubstance()));
			participantRole.setPlayingEntity(playingEntity);
			objParticipant.setParticipantRole(participantRole);
			return objParticipant;
		}
		case "AdvanceDirectives" : {

			objParticipant.getTypeCode().add(EnumTypeCode.VRF.getValue()); 
			objParticipant.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.VERIFICATION_OF_AN_ADVANCE_DIRECTIVE_OBSERVATION_TEMPLATE.toString()));
			objParticipant.setTime(PopulateDataType.populateIVLTS(((AdvanceDirectives)objModel).getVerificationDate())); 

			//Adding ParticipantRole in Participant
			ParticipantRole	participantRole = new ParticipantRole();
			participantRole.getId().add(PopulateDataType.populateII(StandardMethods.generateUUID()));

			PlayingEntity objPlayingEntity= new PlayingEntity();
			objPlayingEntity.getName().add(PopulateDataType.populatePN(((AdvanceDirectives)objModel).getVerification()));
			participantRole.setPlayingEntity(objPlayingEntity);
			objParticipant.setParticipantRole(participantRole);
			return objParticipant;
		}
		case "Procedures" : {
			objParticipant.getTypeCode().add(EnumTypeCode.DEV.getValue());
			//Adding ParticipantRole in Participant
			ParticipantRole participantRole = objFactory.createParticipantRole();
			participantRole.getClassCode().add(EnumClassCode.MANU.getValue());
			participantRole.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PRODUCT_INSTANCE_TEMPLATE)); //id 3rd field
			participantRole.getId().add(PopulateDataType.populateII(StandardMethods.generateUUID()));
			objParticipant.setParticipantRole(participantRole);
			return objParticipant;
		}
		case "MedicalEquipment" : {
			objParticipant.getTypeCode().add(EnumTypeCode.DEV.getValue());
			/**
			 * <participantRole classCode="MANU">
						<templateId root="2.16.840.1.113883.10.20.1.52"/> <!-- Product instance template -->
						<playingDevice>
							<code code="72506001" codeSystem="2.16.840.1.113883.6.96" displayName="Automatic implantable cardioverter/defibrillator"/>
						</playingDevice>
					</participantRole>
			 */
			ParticipantRole participantRole = objFactory.createParticipantRole();
			participantRole.getClassCode().add(EnumClassCode.MANU.getValue());
			participantRole.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.PRODUCT_INSTANCE_TEMPLATE));
			Device playingDevice = objFactory.createDevice();
			participantRole.setPlayingDevice(playingDevice);
			playingDevice.setCode(PopulateDataType.populateCE(SNOMEDCode.getCode(((MedicalEquipment)objModel).getSupplyDevice()),EnumCodeSystem.OID_SNOMED_CT.getValue(),((MedicalEquipment)objModel).getSupplyDevice()));
			objParticipant.setParticipantRole(participantRole);

			return objParticipant;
		}
		}
		return objParticipant ;
	}
	/**
	 *  Method is used to create and add Header in Table Body
	 *  @param objModel - Object of given Model whose table need to be created
	 *  @return tr - populated table header row for given Model
	 */
	private static StrucDocTr getStrucDocThList(List<?> objModel)
	{
		List<String> header = new ArrayList<>();

		switch(objModel.get(0).getClass().getSimpleName())
		{
		case "Payer" : {
			header.add(EnumTableHeader.PAYER_NAME.getValue());
			header.add(EnumTableHeader.POLICY_TYPE.getValue());
			header.add(EnumTableHeader.COVERED_PARTY_ID.getValue());
			header.add(EnumTableHeader.AUTHORIZATION.getValue());
		}
		break;
		case "AdvanceDirectives" : {
			header.add(EnumTableHeader.DIRECTIVE.getValue());
			header.add(EnumTableHeader.DESCRIPTION.getValue());
			header.add(EnumTableHeader.VERIFICATION.getValue());
			header.add(EnumTableHeader.SUPPORTING_DOCUMENT.getValue());
		}
		break;
		case "FunctionalStatus" : 
		{
			header.add(EnumTableHeader.FUNCTIONAL_CONDITION.getValue());
			header.add(EnumTableHeader.EFFECTIVE_DATES.getValue());
			header.add(EnumTableHeader.CONDITION_STATUS.getValue());

		} break ;
		case "Problems" : 
		{
			header.add(EnumTableHeader.CONDITION.getValue());
			header.add(EnumTableHeader.EFFECTIVE_DATES.getValue());
			header.add(EnumTableHeader.CONDITION_STATUS.getValue());
		} break ;
		case "FamilyHistory" : 
		{
			header.add(EnumTableHeader.DIAGNOSIS.getValue());
			header.add(EnumTableHeader.AGE_OF_ONSET.getValue());
		} break ;
		case "SocialHistory" : 
		{
			header.add(EnumTableHeader.SOCIAL_HISTORY_ELEMENT.getValue());
			header.add(EnumTableHeader.DESCRIPTION.getValue());
			header.add(EnumTableHeader.EFFECTIVE_DATES.getValue());
		} break ;

		case "Alerts" : {
			header.add(EnumTableHeader.SUBSTANCE.getValue());
			header.add(EnumTableHeader.REACTION.getValue());
			header.add(EnumTableHeader.STATUS.getValue());
		}break;
		case "Medications" :
		{
			header.add(EnumTableHeader.MEDICATIONS.getValue());
			header.add(EnumTableHeader.INSTRUCTIONS.getValue());
			header.add(EnumTableHeader.START_DATE.getValue());
			header.add(EnumTableHeader.STATUS.getValue());
		} break ;
		case "MedicalEquipment" : 
		{
			header.add(EnumTableHeader.SUPPLY_DEVICE.getValue());
			header.add(EnumTableHeader.DATE_SUPPLIED.getValue());
		} break ;
		case "Immunizations" : 
		{
			header.add(EnumTableHeader.VACCINE.getValue());
			header.add(EnumTableHeader.DATE.getValue());
			header.add(EnumTableHeader.STATUS.getValue());
		} break ;
		case "Results" : 
		{
			@SuppressWarnings("unchecked")
			List<Results> listRes=(List<Results>)objModel;
			for(Results result: listRes){
				if(header.size()==0){
					header.add("");
				}
				header.add(StandardMethods.getCalendarDate(result.getTestDate()));
			}
		} break ;
		case "Procedures" : 
		{
			header.add(EnumTableHeader.PROCEDURE.getValue());
			header.add(EnumTableHeader.DATE.getValue());
		} break ;
		case "Encounters" : 
		{
			header.add(EnumTableHeader.ENCOUNTER.getValue());
			header.add(EnumTableHeader.LOCATION.getValue());
			header.add(EnumTableHeader.DATE.getValue());
		} break ;
		case "Plan" : 
		{
			header.add(EnumTableHeader.PLANNED_ACTIVITY.getValue());
			header.add(EnumTableHeader.PLANNED_DATE.getValue());
		} 
		}

		StrucDocTr tr = objFactory.createStrucDocTr();
		int strucDocThMemberSize=0;
		if(header != null)
			strucDocThMemberSize = header.size() ; 
		for(int i=0;i<strucDocThMemberSize;i++){
			StrucDocTh th=objFactory.createStrucDocTh();
			th.getContent().add(header.get(i));
			tr.getThOrTd().add(th);
		}
		return tr;

	}
	/**
	 *  Method is used to create and add Header in Table Body
	 *  @param objModel - Object of given Model whose table need to be created
	 *  @return tr - populated table data row for given Model
	 */
	private static StrucDocTr getStrucDocTbody(Object objModel)
	{
		StrucDocTr tr = objFactory.createStrucDocTr();
		switch(objModel.getClass().getSimpleName())
		{
		case "Payer" : {
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();
			StrucDocTd td2 = objFactory.createStrucDocTd();
			StrucDocTd td3 = objFactory.createStrucDocTd();

			if(((Payer) objModel).getPayerName()!=null)
				td.getContent().add(((Payer) objModel).getPayerName());
			else
				td.getContent().add("");
			if(((Payer) objModel).getPolicyType()!=null)
			{	td1.getContent().add(((Payer) objModel).getPolicyType());
			if(((Payer) objModel).getCoverageType()!=null)
				td1.getContent().add(" / "+((Payer) objModel).getCoverageType());
			}
			else
				td1.getContent().add("");
			if(((Payer) objModel).getCoveredPartyID()!=null)
				td2.getContent().add(((Payer) objModel).getCoveredPartyID());
			else
				td2.getContent().add("");
			if(((Payer) objModel).getAuthorization()!=null)
				td3.getContent().add(((Payer) objModel).getAuthorization());
			else
				td3.getContent().add("");

			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);
			tr.getThOrTd().add(td2);
			tr.getThOrTd().add(td3);

			return tr;
		}
		case "AdvanceDirectives" : {
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();
			StrucDocTd td2 = objFactory.createStrucDocTd();
			StrucDocTd td3 = objFactory.createStrucDocTd();
			StrucDocLinkHtml linkHtml=objFactory.createStrucDocLinkHtml();

			if(((AdvanceDirectives) objModel).getDirective()!=null)
				td.getContent().add(((AdvanceDirectives) objModel).getDirective());
			else
				td.getContent().add("");

			if(((AdvanceDirectives) objModel).getDescription()!=null)
				td1.getContent().add(((AdvanceDirectives) objModel).getDescription());
			else
				td1.getContent().add("");

			if(((AdvanceDirectives) objModel).getVerification()!=null)
			{	td2.getContent().add(((AdvanceDirectives) objModel).getVerification());
			if (((AdvanceDirectives) objModel).getVerificationDate()!= null)
				td2.getContent().add(" , "+StandardMethods.getCalendarDate(((AdvanceDirectives) objModel).getVerificationDate()));
			}
			else
				td2.getContent().add("");

			if(((AdvanceDirectives) objModel).getSupportingDocumentLink()!=null)
			{			
				linkHtml.setHref(((AdvanceDirectives) objModel).getSupportingDocumentLink());
				if(((AdvanceDirectives) objModel).getSupportingDocument()!=null)
					linkHtml.getContent().add(((AdvanceDirectives) objModel).getSupportingDocument());

				td3.getContent().add(linkHtml);			
			}
			else if(((AdvanceDirectives) objModel).getSupportingDocument()!=null)
				td3.getContent().add(((AdvanceDirectives) objModel).getSupportingDocument());
			else
				td3.getContent().add("");

			
			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);
			tr.getThOrTd().add(td2);
			tr.getThOrTd().add(td3);

			return tr;
		} 
		case "FunctionalStatus" : 
		{
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();
			StrucDocTd td2 = objFactory.createStrucDocTd();

			if(((FunctionalStatus) objModel).getFunctionalCondition()!=null)
				td.getContent().add(((FunctionalStatus)objModel).getFunctionalCondition());
			else
				td.getContent().add("");
			if(((FunctionalStatus) objModel).getEffectiveDates()!=null)
			{
				String strValue=((FunctionalStatus)objModel).getEffectiveDates().getValue();
				String strCenter=((FunctionalStatus)objModel).getEffectiveDates().getCenter();
				String strDuration=((FunctionalStatus)objModel).getEffectiveDates().getWidth();
				String strHigh=((FunctionalStatus)objModel).getEffectiveDates().getHigh();
				String strLow=((FunctionalStatus)objModel).getEffectiveDates().getLow();

				if(strValue!=null){
					td1.getContent().add(StandardMethods.getCalendarDate(strValue));
				}
				else{
					td1.getContent().add(strValue);
				}
				if(strValue!=null && (strCenter!=null || strDuration!=null || strHigh!=null||strLow!=null))
					td1.getContent().add("; ");

				if(strCenter!=null){
					td1.getContent().add(StandardMethods.getCalendarDate(strCenter));
				}
				else{
					td1.getContent().add(strCenter);
				}
				if(strCenter!=null && (strDuration!=null || strHigh!=null||strLow!=null))
					td1.getContent().add("; ");

				if(strDuration!=null){
					td1.getContent().add(StandardMethods.getCalendarDate(strDuration));
				}
				else{
					td1.getContent().add(strDuration);
				}
				if(strDuration!=null && (strHigh!=null||strLow!=null) )
					td1.getContent().add("; ");

				if(strLow!=null && strHigh!=null)
				{
					td1.getContent().add(StandardMethods.getCalendarDate(strLow)+" - "+StandardMethods.getCalendarDate(strHigh));
				}
				else if(strLow==null && strHigh!=null)
				{
					td1.getContent().add(StandardMethods.getCalendarDate(strHigh));
				}
				else if(strLow!=null && strHigh==null)
				{
					td1.getContent().add(StandardMethods.getCalendarDate(strLow));
				}		
			}
			else
				td1.getContent().add("");

			if(((FunctionalStatus) objModel).getConditionStatus()!=null)
				td2.getContent().add(((FunctionalStatus)objModel).getConditionStatus());
			else
				td2.getContent().add("");

			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);
			tr.getThOrTd().add(td2);
		} break ;
		case "Problems" : 
		{
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();
			StrucDocTd td2 = objFactory.createStrucDocTd();

			if(((Problems) objModel).getCondition()!=null)
				td.getContent().add(((Problems)objModel).getCondition());
			else
				td.getContent().add("");

			if(((Problems) objModel).getEffectiveDates()!=null)
			{
				String strValue=((Problems)objModel).getEffectiveDates().getValue();
				String strCenter=((Problems)objModel).getEffectiveDates().getCenter();
				String strDuration=((Problems)objModel).getEffectiveDates().getWidth();
				String strHigh=((Problems)objModel).getEffectiveDates().getHigh();
				String strLow=((Problems)objModel).getEffectiveDates().getLow();

				if(strValue!=null){
					td1.getContent().add(StandardMethods.getCalendarDate(strValue));
				}
				else{
					td1.getContent().add(strValue);
				}
					
				if(strValue!=null && (strCenter!=null || strDuration!=null || strHigh!=null||strLow!=null))					
					td1.getContent().add("; ");
				
				if(strCenter!=null){
					td1.getContent().add(StandardMethods.getCalendarDate(strCenter));
				}
				else{
					td1.getContent().add(strCenter);
				}			
				if(strCenter!=null && (strDuration!=null || strHigh!=null||strLow!=null))
					td1.getContent().add("; ");
				
				if(strDuration!=null){
					td1.getContent().add(StandardMethods.getCalendarDate(strDuration));
				}
				else{
					td1.getContent().add(strDuration);
				}	
				if(strDuration!=null && (strHigh!=null||strLow!=null) )
					td1.getContent().add("; ");

				if(strLow!=null && strHigh!=null)
				{
					td1.getContent().add(StandardMethods.getCalendarDate(strLow)+" - "+StandardMethods.getCalendarDate(strHigh));
				}
				else if(strLow==null && strHigh!=null)
				{
					td1.getContent().add(StandardMethods.getCalendarDate(strHigh));
				}
				else if(strLow!=null && strHigh==null)
				{
					td1.getContent().add(StandardMethods.getCalendarDate(strLow));
				}		
			}
			else
				td1.getContent().add("");

			if(((Problems) objModel).getConditionStatus()!=null)
				td2.getContent().add(((Problems)objModel).getConditionStatus());
			else
				td2.getContent().add("");

			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);
			tr.getThOrTd().add(td2);
			break;
		} 
		case "FamilyHistory" : 
		{
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();


			if(((FamilyHistory) objModel).getDiagnosis()!=null)
				td.getContent().add(((FamilyHistory)objModel).getDiagnosis());
			else
				td.getContent().add("");
			if(((FamilyHistory) objModel).getAgeAtOnset()!=null)
				td1.getContent().add(((FamilyHistory)objModel).getAgeAtOnset());
			else
				td1.getContent().add("");

			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);

			return tr;
		}
		case "SocialHistory" : 
		{
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();
			StrucDocTd td2 = objFactory.createStrucDocTd();

			if(((SocialHistory) objModel).getSocialHistoryElement()!=null)
				td.getContent().add(((SocialHistory)objModel).getSocialHistoryElement());
			else
				td.getContent().add("");
			if(((SocialHistory) objModel).getDescription()!=null)
				td1.getContent().add(((SocialHistory)objModel).getDescription());
			else
				td1.getContent().add("");
			if(((SocialHistory) objModel).getEffectiveDates()!=null)
			{
				String strValue=((SocialHistory) objModel).getEffectiveDates().getValue();
				String strCenter=((SocialHistory) objModel).getEffectiveDates().getCenter();
				String strDuration=((SocialHistory) objModel).getEffectiveDates().getWidth();
				String strHigh=((SocialHistory) objModel).getEffectiveDates().getHigh();
				String strLow=((SocialHistory) objModel).getEffectiveDates().getLow();

				if(strValue!=null){
					td2.getContent().add(StandardMethods.getCalendarDate(strValue));
				}
				else{
					td2.getContent().add(strValue);
				}
				if(strValue!=null && (strCenter!=null || strDuration!=null || strHigh!=null||strLow!=null))
					td2.getContent().add("; ");

				if(strCenter!=null){
					td2.getContent().add(StandardMethods.getCalendarDate(strCenter));
				}
				else{
					td2.getContent().add(strCenter);
				}
				if(strCenter!=null && (strDuration!=null || strHigh!=null||strLow!=null))
					td2.getContent().add("; ");


				if(strDuration!=null){
					td2.getContent().add(StandardMethods.getCalendarDate(strDuration));
				}
				else{
					td2.getContent().add(strDuration);
				}
				if(strDuration!=null && (strHigh!=null||strLow!=null) )
					td2.getContent().add("; ");

				if(strLow!=null && strHigh!=null)
				{
					td2.getContent().add(StandardMethods.getCalendarDate(strLow)+" - "+StandardMethods.getCalendarDate(strHigh));
				}
				else if(strLow==null && strHigh!=null)
				{
					td2.getContent().add(StandardMethods.getCalendarDate(strHigh));
				}
				else if(strLow!=null && strHigh==null)
				{
					td2.getContent().add(StandardMethods.getCalendarDate(strLow));
				}		
			}
			else
				td2.getContent().add("");

			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);
			tr.getThOrTd().add(td2);
		} break ;
		case "Alerts" : {
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();
			StrucDocTd td2 = objFactory.createStrucDocTd();

			if(((Alerts) objModel).getSubstance()!=null)
				td.getContent().add(((Alerts)objModel).getSubstance());
			else
				td.getContent().add("");
			if(((Alerts) objModel).getReaction()!=null)
				td1.getContent().add(((Alerts)objModel).getReaction());
			else
				td1.getContent().add("");
			if(((Alerts) objModel).getStatus()!=null)
				td2.getContent().add(((Alerts)objModel).getStatus());
			else
				td2.getContent().add("");

			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);
			tr.getThOrTd().add(td2);

			return tr;
		}
		case "Medications" :
		{
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();
			StrucDocTd td2 = objFactory.createStrucDocTd();
			StrucDocTd td3 = objFactory.createStrucDocTd();

			if(((Medications) objModel).getMedication()!=null)
				td.getContent().add(((Medications)objModel).getMedication());
			else
				td.getContent().add("");

			if(((Medications)objModel).getInstructions()!=null)
			{	td1.getContent().add(((Medications)objModel).getInstructions());
			if(((Medications)objModel).getCriterion()!= null)
				td1.getContent().add(" "+((Medications)objModel).getCriterion());
			}
			else
				td1.getContent().add("");

			if(((Medications) objModel).getDate()!=null)
			{
				
				String strLow=((Medications) objModel).getDate().getLow();

				if(strLow!=null){
					td2.getContent().add(StandardMethods.getCalendarDate(strLow));
				}
			}
			else
				td2.getContent().add("");

			if(((Medications) objModel).getStatus()!=null)
				td3.getContent().add(((Medications)objModel).getStatus());
			else
				td3.getContent().add("");

			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);
			tr.getThOrTd().add(td2);
			tr.getThOrTd().add(td3);

			return tr;
		}
		case "MedicalEquipment" : 
		{
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();

			if(((MedicalEquipment) objModel).getSupplyDevice()!=null)
				td.getContent().add(((MedicalEquipment) objModel).getSupplyDevice());
			else
				td.getContent().add("");
			if(((MedicalEquipment) objModel).getDateSupplied()!=null)
				td1.getContent().add(StandardMethods.getCalendarDate(((MedicalEquipment) objModel).getDateSupplied()));
			else
				td1.getContent().add("");

			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);

		} break ;
		case "Immunizations" : 
		{
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();
			StrucDocTd td2 = objFactory.createStrucDocTd();

			if(((Immunizations)objModel).getVaccine()!=null)
			{	td.getContent().add(((Immunizations)objModel).getVaccine());

			if (((Immunizations)objModel).getMoodOfVaccine()!= null)
				td.getContent().add(" "+((Immunizations)objModel).getMoodOfVaccine());
			}
			else
				td.getContent().add("");

			if(((Immunizations) objModel).getDate()!=null)
			{
				String strValue=((Immunizations) objModel).getDate().getValue();
				String strCenter=((Immunizations) objModel).getDate().getCenter();
				String strDuration=((Immunizations) objModel).getDate().getWidth();
				String strHigh=((Immunizations) objModel).getDate().getHigh();
				String strLow=((Immunizations) objModel).getDate().getLow();

				if(strValue!=null){
					td1.getContent().add(StandardMethods.getCalendarDate(strValue));
				}
				else{
					td1.getContent().add(strValue);
				}
				if(strValue!=null && (strCenter!=null || strDuration!=null || strHigh!=null||strLow!=null))
					td1.getContent().add("; ");

				if(strCenter!=null){
					td1.getContent().add(StandardMethods.getCalendarDate(strCenter));
				}
				else{
					td1.getContent().add(strCenter);
				}
				if(strCenter!=null && (strDuration!=null || strHigh!=null||strLow!=null))
					td1.getContent().add("; ");

				if(strDuration!=null){
					td1.getContent().add(StandardMethods.getCalendarDate(strDuration));
				}
				else{
					td1.getContent().add(strDuration);
				}
				if(strDuration!=null && (strHigh!=null||strLow!=null) )
					td1.getContent().add("; ");

				if(strLow!=null && strHigh!=null)
				{
					td1.getContent().add(StandardMethods.getCalendarDate(strLow)+" - "+StandardMethods.getCalendarDate(strHigh));
				}
				else if(strLow==null && strHigh!=null)
				{
					td1.getContent().add(StandardMethods.getCalendarDate(strHigh));
				}
				else if(strLow!=null && strHigh==null)
				{
					td1.getContent().add(StandardMethods.getCalendarDate(strLow));
				}

			}
			else
				td1.getContent().add("");


			if(((Immunizations) objModel).getStatus()!=null)
				td2.getContent().add(((Immunizations)objModel).getStatus());
			else
				td2.getContent().add("");

			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);
			tr.getThOrTd().add(td2);

			return tr;
		}

		case "Procedures" : 
		{
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();

			if(((Procedures)objModel).getProcedure()!=null)
				td.getContent().add(((Procedures)objModel).getProcedure());

			if (((Procedures) objModel).getObserve()!= null)
				td.getContent().add(", "+((Procedures) objModel).getObserve());

			else if (((Procedures) objModel).getLaterality()!= null)
				td.getContent().add(", "+((Procedures) objModel).getLaterality());

			else
				td.getContent().add("");

			if(((Procedures)objModel).getProcedure()!=null)
				td1.getContent().add(StandardMethods.getCalendarDate(((Procedures)objModel).getDate()));
			else
				td1.getContent().add("");

			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);
		} break ;
		case "Encounters" : 
		{
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();
			StrucDocTd td2 = objFactory.createStrucDocTd();

			if(((Encounters) objModel).getEncounter()!=null)
				td.getContent().add(((Encounters)objModel).getEncounter());
			else
				td.getContent().add("");
			if(((Encounters) objModel).getLocation()!=null)
				for(int i=0;i< ((Encounters)objModel).getLocation().size();i++)
				{
					td1.getContent().add(((Encounters)objModel).getLocation().get(i));
					if(i< ((Encounters)objModel).getLocation().size()-1)
						td1.getContent().add(", ");
				}		
			else
				td1.getContent().add("");

			if(((Encounters) objModel).getDate()!=null)
			{
				String strValue=((Encounters)objModel).getDate().getValue();
				String strCenter=((Encounters)objModel).getDate().getCenter();
				String strDuration=((Encounters)objModel).getDate().getWidth();
				String strHigh=((Encounters)objModel).getDate().getHigh();
				String strLow=((Encounters)objModel).getDate().getLow();

				if(strValue!=null){
					td2.getContent().add(StandardMethods.getCalendarDate(strValue));
				}
				else{
					td2.getContent().add(strValue);
				}
				if(strValue!=null && (strCenter!=null || strDuration!=null || strHigh!=null||strLow!=null))
					td2.getContent().add("; ");

				if(strCenter!=null){
					td2.getContent().add(StandardMethods.getCalendarDate(strCenter));
				}else{
					td2.getContent().add(strCenter);
				}
				if(strCenter!=null && (strDuration!=null || strHigh!=null||strLow!=null))
					td2.getContent().add("; ");

				if(strDuration!=null){
					td2.getContent().add(StandardMethods.getCalendarDate(strDuration));
				}else{
					td2.getContent().add(strDuration);
				}
				if(strDuration!=null && (strHigh!=null||strLow!=null) )
					td2.getContent().add("; ");

				if(strLow!=null && strHigh!=null)
				{
					td2.getContent().add(StandardMethods.getCalendarDate(strLow)+" - "+StandardMethods.getCalendarDate(strHigh));
				}
				else if(strLow==null && strHigh!=null)
				{
					td2.getContent().add(StandardMethods.getCalendarDate(strHigh));
				}
				else if(strLow!=null && strHigh==null)
				{
					td2.getContent().add(StandardMethods.getCalendarDate(strLow));
				}

			}
			else
				td2.getContent().add("");


			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);
			tr.getThOrTd().add(td2);
		} break ;
		case "Plan" : 
		{
			StrucDocTd td = objFactory.createStrucDocTd();
			StrucDocTd td1 = objFactory.createStrucDocTd();

			if(((Plan) objModel).getPlannedActivity()!=null)
				td.getContent().add(((Plan)objModel).getPlannedActivity());
			else
				td.getContent().add("");
			if(((Plan) objModel).getPlannedDate()!=null)
			{
				String strValue=((Plan) objModel).getPlannedDate().getValue();
				String strCenter=((Plan) objModel).getPlannedDate().getCenter();
				String strDuration=((Plan) objModel).getPlannedDate().getWidth();
				String strHigh=((Plan) objModel).getPlannedDate().getHigh();
				String strLow=((Plan) objModel).getPlannedDate().getLow();

				if(strValue!=null){
					td1.getContent().add(StandardMethods.getCalendarDate(strValue));
				}else{
					td1.getContent().add(strValue);
				}
				if(strValue!=null && (strCenter!=null || strDuration!=null || strHigh!=null||strLow!=null))
					td1.getContent().add("; ");

				if(strCenter!=null){
					td1.getContent().add(StandardMethods.getCalendarDate(strCenter));
				}else{
					td1.getContent().add(strCenter);
				}
				if(strCenter!=null && (strDuration!=null || strHigh!=null||strLow!=null))
					td1.getContent().add("; ");

				if(strDuration!=null){
					td1.getContent().add(StandardMethods.getCalendarDate(strDuration));
				}else{
					td1.getContent().add(strDuration);
				}
				if(strDuration!=null && (strHigh!=null||strLow!=null) )
					td1.getContent().add("; ");

				if(strLow!=null && strHigh!=null)
				{
					td1.getContent().add(StandardMethods.getCalendarDate(strLow)+" - "+StandardMethods.getCalendarDate(strHigh));
				}
				else if(strLow==null && strHigh!=null)
				{
					td1.getContent().add(StandardMethods.getCalendarDate(strHigh));
				}
				else if(strLow!=null && strHigh==null)
				{
					td1.getContent().add(StandardMethods.getCalendarDate(strLow));
				}
			}
			else
				td1.getContent().add("");

			tr.getThOrTd().add(td);
			tr.getThOrTd().add(td1);

			return tr;
		} 
		}
		return tr;

	}
	/**
	 *  Method is used to create and add data rows in Table Body
	 * @param tbody - table body 
	 * @param objModelList - List of Model
	 */
	private static void createTable(List<?> objModelList, StrucDocTbody tbody) {
		if(!(objModelList.get(0) instanceof VitalSigns || objModelList.get(0) instanceof Results))
			for(Object objModel : objModelList){
				tbody.getTr().add(getStrucDocTbody(objModel));
			}
	}

	/**
	 *  Method is used to create and add Episode-Observation in Problems Section
	 */ 
	private static EntryRelationship getEpisodeObservation(String actID,String obsID, EffectiveDateTime effectiveDateTime, String code,String codeSystem, String displayName) {

		EntryRelationship entryrelationship = objFactory.createEntryRelationship();
		entryrelationship.setTypeCode(XActRelationshipEntryRelationship.SUBJ);

		Observation obs = objFactory.createObservation();
		obs.getClassCode().add(EnumClassCode.OBS.getValue());
		obs.setMoodCode(XActMoodDocumentObservation.EVN);
		obs.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.EPISODE_OBSERVATION_TEMPLATE)); //<!-- Episode observation template -->
		obs.getId().add(PopulateDataType.populateII(obsID));
		obs.setCode(PopulateDataType.populateCD(CCDConstants.ASSERTION,EnumCodeSystem.OID_HL7_ACTCODE.getValue()));
		obs.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
		obs.setEffectiveTime(PopulateDataType.populateIVLTS(effectiveDateTime));
		obs.getValue().add(PopulateDataType.populateCD(code,codeSystem, displayName));
		CD cd = objFactory.createCD();
		CR cr = objFactory.createCR();

		cr.setName(PopulateDataType.populateCV(SNOMEDCode.getCode(CCDConstants.EPISODICITY), CCDConstants.EPISODICITY));  
		cr.setValue(PopulateDataType.populateCD(SNOMEDCode.getCode(CCDConstants.NEW_EPISODE),null, CCDConstants.NEW_EPISODE));
		cd.getQualifier().add(cr);
		obs.getValue().add(cd);

		EntryRelationship rel = objFactory.createEntryRelationship();
		rel.setTypeCode(XActRelationshipEntryRelationship.SAS);
		obs.getEntryRelationship().add(rel);
		Act act1 = objFactory.createAct();
		act1.setClassCode(XActClassDocumentEntryAct.ACT);
		act1.setMoodCode(XDocumentActMood.EVN);
		act1.getId().add(PopulateDataType.populateII(StandardMethods.generateUUID())); //check 
		act1.setCode(PopulateDataType.populateCDNullFalvor(CCDConstants.NULLFLAVOR_NA));

		rel.setAct(act1);
		entryrelationship.setObservation(obs);


		return entryrelationship;
	}
	private static EntryRelationship getEpisodeObservation(String obsID,  String code,String codeSystem, String socialHistoryElement) {

		EntryRelationship entryrelationship = objFactory.createEntryRelationship();
		entryrelationship.setTypeCode(XActRelationshipEntryRelationship.SUBJ);
		entryrelationship.setInversionInd(true);

		Observation obs = objFactory.createObservation();
		obs.getClassCode().add(EnumClassCode.OBS.getValue());
		obs.setMoodCode(XActMoodDocumentObservation.EVN);
		obs.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.EPISODE_OBSERVATION_TEMPLATE)); //<!-- Episode observation template -->
		obs.setCode(PopulateDataType.populateCD(CCDConstants.ASSERTION,EnumCodeSystem.OID_HL7_ACTCODE.getValue()));
		obs.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
		obs.getValue().add(PopulateDataType.populateCDAndCR(SNOMEDCode.getCode("Clinical finding"),codeSystem, "Clinical finding"));  
		
		EntryRelationship rel = objFactory.createEntryRelationship();
		rel.setTypeCode(XActRelationshipEntryRelationship.SAS);
		obs.getEntryRelationship().add(rel);
		Observation observation = objFactory.createObservation();
		observation.getClassCode().add(EnumClassCode.OBS.getValue());
		observation.setMoodCode(XActMoodDocumentObservation.EVN);
		observation.getId().add(PopulateDataType.populateII(StandardMethods.generateUUID()));
		observation.setCode(PopulateDataType.populateCD(code, codeSystem, socialHistoryElement));
		rel.setObservation(observation);
		entryrelationship.setObservation(obs);

		return entryrelationship;
	}
	/**
	 *  Method is used to Generate section for Family History
	 *  @param objModels contains list of Family History objects
	 *  @param currentComponent contains the Component of Family History if it already exists
	 *  @return section - returns the family history generated section
	 */
	@SuppressWarnings("unchecked")
	private static Section createTableFamilyHistory(List<?> objModels, Component3 currentComponent) {

		Section section = null;
		section = objFactory.createSection();
		HashMap<String, StrucDocTable> tableMap = new HashMap<>();
		LinkedHashMap<String, List<FamilyHistory>> entryMap = new LinkedHashMap<>();
		List<FamilyHistory> objListFamilyHistory = null;
		List<FamilyHistory> listFamily = null;
		if (currentComponent != null) {

			objListFamilyHistory = new ArrayList<FamilyHistory>();
			listFamily = (List<FamilyHistory>) objModels;
			if (currentComponent.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.FAMILY_HISTORY_TEMPLATE))
			{
				//extracting section form component
				Section objSection = currentComponent.getSection();

				if(objSection.getEntry()!= null)
				{
					FamilyHistory objFamilyHistory=null;

					for(int i=0; i<objSection.getEntry().size();i++)
					{
						String birthTime=null;
						String vitalStatus=null;
						String relationWithPatient=objSection.getEntry().get(i).getOrganizer().getSubject().getRelatedSubject().getCode().getDisplayName();
						String gender=objSection.getEntry().get(i).getOrganizer().getSubject().getRelatedSubject().getSubject().getAdministrativeGenderCode().getDisplayName();
						birthTime=objSection.getEntry().get(i).getOrganizer().getSubject().getRelatedSubject().getSubject().getBirthTime().getValue();

						for(int j=0; j<objSection.getEntry().get(i).getOrganizer().getComponent().size();j++){
							if (objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getEntryRelationship().size() > 0) {
								for (int k = 0; k < objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getEntryRelationship().size(); k++) {
									if (objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getEntryRelationship().get(k).getTypeCode() == XActRelationshipEntryRelationship.CAUS) {
										vitalStatus = "Deceased";
									}
								}
							}
						}
						if(vitalStatus==null){
							vitalStatus="Alive";
						}

						for(int j=0; j<objSection.getEntry().get(i).getOrganizer().getComponent().size();j++){

							objFamilyHistory= new FamilyHistory();
							objFamilyHistory.setRelationWithPatient(relationWithPatient);
							objFamilyHistory.setGender(gender);
							objFamilyHistory.setBirthTime(birthTime);

							String diagnosis=((CD)objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getValue().get(0)).getDisplayName();
							String ageAtOnset=null;
							if (objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getEntryRelationship().size() > 0) {

								for (int k = 0; k < objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getEntryRelationship().size(); k++) {
									if (objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getEntryRelationship().get(k).getTypeCode() == XActRelationshipEntryRelationship.SUBJ) {
										ageAtOnset = ((INT) objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getEntryRelationship().get(k).getObservation().getValue().get(0)).getValue().toString();
									}
								}
							} else if(birthTime!=null){
									String yearAtOnset=((IVXBTS)((IVLTS)objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getEffectiveTime()).getRest().get(0).getValue()).getValue();
									ageAtOnset=Integer.toString(Integer.parseInt(yearAtOnset)-Integer.parseInt(birthTime));
								}
							objFamilyHistory.setVitalStatus(vitalStatus);
							objFamilyHistory.setDiagnosis(diagnosis);
							objFamilyHistory.setAgeAtOnset(ageAtOnset);
							objListFamilyHistory.add(objFamilyHistory);
						}	
					}
				}
				listFamily.addAll(objListFamilyHistory);
			}

		}

		section.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.FAMILY_HISTORY_TEMPLATE)); 

		section.setCode(PopulateDataType.populateCE(EnumLOINC.HISTORY_OF_FAMILY_MEMBER_DISEASE.getValue(),EnumCodeSystem.OID_LOINC.getValue()));
		section.setTitle(PopulateDataType.populateST(EnumSection.FAMILY_HISTORY.getValue()));
		section.setText(objFactory.createStrucDocText());

		StrucDocTable table = null;
		StrucDocTbody tbody = null;

		List<FamilyHistory> list = null;
		String relation = null;
		String vitalStatus = null;
		String key = null;

		if(listFamily==null){
			listFamily = (List<FamilyHistory>) objModels;
		}

		for (FamilyHistory objfamilyHistory : listFamily) {
			relation = objfamilyHistory.getRelationWithPatient();
			vitalStatus = objfamilyHistory.getVitalStatus();
			if(relation == null)
				relation=CCDConstants.FAMILY_MEMBER_UNKNOWN;
			if(vitalStatus==null){
				key=relation;
				vitalStatus="";
			}
			else
			key = relation + " (" + vitalStatus + ")";
			if (tableMap.containsKey(key)) {
				table = tableMap.get(key);
				tbody = table.getTbody().get(0);

				entryMap.get(key).add(objfamilyHistory);

			} else {
				table = objFactory.createStrucDocTable();
				table.setBorder(PropertyLoader.getProperty("Table_Border"));
				table.setWidth(PropertyLoader.getProperty("Table_Width"));

				StrucDocThead thead = objFactory.createStrucDocThead();
				thead.getTr().add(getStrucDocThList(objModels));
				table.setThead(thead);

				tbody = objFactory.createStrucDocTbody();
				table.getTbody().add(tbody);

				tableMap.put(key, table);
				list = new ArrayList<>();
				list.add(objfamilyHistory);
				entryMap.put(key, list);
			}

			tbody.getTr().add(getStrucDocTbody(objfamilyHistory));
		}

		for (Map.Entry<String, StrucDocTable> entry : tableMap.entrySet()) {
			StrucDocParagraph para = objFactory.createStrucDocParagraph();
			if(entry.getKey()!=null)
			para.getContent().add(entry.getKey());
			else para.getContent().add("");
			section.getText().getContent().add(para);
			section.getText().getContent().add(entry.getValue());
		}

		for (Map.Entry<String, List<FamilyHistory>> entry : entryMap.entrySet()){
			section.getEntry().add(getEntryDataFamilyHistory(StandardMethods.generateUUID(), StandardMethods.generateUUID(), entry.getValue()));
		}
		return section;
	}

	/**
	 *  Method is used to Generate entry data for Family History section
	 *  @param actID
	 *  @param obsID
	 *  @param listfamily contains list of Family History objects of a particular family member/relative
	 *  @return entry returns the populated entry of family History
	 */
	private static Entry getEntryDataFamilyHistory(String actID,String obsID,List<FamilyHistory>listfamily){
		Entry entry = objFactory.createEntry();
		entry.setTypeCode(XActRelationshipEntry.DRIV);

		Organizer organizer = objFactory.createOrganizer();
		Subject subject = objFactory.createSubject();
		SubjectPerson subjectPerson = objFactory.createSubjectPerson();
		RelatedSubject relatedSubject = objFactory.createRelatedSubject();

		entry.setTypeCode(XActRelationshipEntry.DRIV);
		organizer.setClassCode(XActClassDocumentEntryOrganizer.CLUSTER);
		organizer.getMoodCode().add(XActMoodDocumentObservation.EVN.value());
		organizer.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.FAMILY_HISTORY_ORGANIZER_TEMPLATE));
		organizer.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));

		relatedSubject.setClassCode(XDocumentSubject.PRS);

		relatedSubject.setSubject(subjectPerson);
		subject.setRelatedSubject(relatedSubject);

		List<Component4> listComponent4=new ArrayList<>();
		String genderCode=null;
		for(FamilyHistory relative:listfamily){
			if(relative.getDiagnosis() == null || relative.getDiagnosis().isEmpty() 
					|| relative.getAgeAtOnset() == null || relative.getAgeAtOnset().isEmpty()
					|| relative.getRelationWithPatient()==null || relative.getRelationWithPatient().isEmpty() 
					|| relative.getVitalStatus()==null || relative.getVitalStatus().isEmpty()
					|| relative.getGender()==null || relative.getGender().isEmpty()
					|| relative.getBirthTime()==null || relative.getBirthTime().isEmpty())
			{
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning", "SectionAttributeMissing",
						EnumSection.FAMILY_HISTORY.getValue()));
			}
			relatedSubject.setCode(PopulateDataType.populateCE(SNOMEDCode.getCode(relative.getRelationWithPatient()), EnumCodeSystem.OID_SNOMED_CT.getValue(),relative.getRelationWithPatient()));

			if(relative.getGender()!=null){
				if(relative.getGender().equalsIgnoreCase("male")){
					genderCode="M";
				}
				else if(relative.getGender().equalsIgnoreCase("female")){
					genderCode="F";
				}
				else{
					genderCode="UN";
				}
			}
			subjectPerson.setAdministrativeGenderCode(PopulateDataType.populateCE(genderCode,EnumCodeSystem.OID_ADMINISTRATIVE_GENDER.getValue(),relative.getGender()));
			subjectPerson.setBirthTime(PopulateDataType.populateTS(relative.getBirthTime()));

			Component4 component = objFactory.createComponent4();
			Observation observation = objFactory.createObservation();

			observation.getClassCode().add(EnumClassCode.OBS.getValue());
			observation.setMoodCode(XActMoodDocumentObservation.EVN);
			observation.getId().add(PopulateDataType.populateII(actID));
			observation.setCode(PopulateDataType.populateCD(CCDConstants.ASSERTION,EnumCodeSystem.OID_ACTCODE.getValue()));
			observation.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			observation.getValue().add(PopulateDataType.populateCD(SNOMEDCode.getCode(relative.getDiagnosis()), EnumCodeSystem.OID_SNOMED_CT.getValue(),relative.getDiagnosis()));

			if(relative.getDiagnosis()!=null && relative.getDiagnosis().toLowerCase().contains("cause of death".toLowerCase())){
				observation.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.FAMILY_HISTORY_CAUSE_OF_DEATH_OBSERVATION_TEMPLATE));
				EntryRelationship entryRelationship =objFactory.createEntryRelationship();
				entryRelationship.setTypeCode(XActRelationshipEntryRelationship.CAUS);
				Observation observation1 = objFactory.createObservation();
				observation1.getClassCode().add(EnumClassCode.OBS.getValue());
				observation1.setMoodCode(XActMoodDocumentObservation.EVN);
				observation1.getId().add(PopulateDataType.populateII(obsID));
				observation1.setCode(PopulateDataType.populateCD(CCDConstants.ASSERTION,EnumCodeSystem.OID_ACTCODE.getValue()));
				observation1.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
				observation1.getValue().add(PopulateDataType.populateCD(SNOMEDCode.getCode(CCDConstants.DEAD), EnumCodeSystem.OID_SNOMED_CT.getValue(),CCDConstants.DEAD));
				entryRelationship.setObservation(observation1);
				observation.getEntryRelationship().add(entryRelationship);


			}else{
				observation.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.FAMILY_HISTORY_OBSERVATION_TEMPLATE));

			}
			EntryRelationship entryRelationship2 =objFactory.createEntryRelationship();
			entryRelationship2.setTypeCode(XActRelationshipEntryRelationship.SUBJ);
			Observation observation2 = objFactory.createObservation();
			observation2.getClassCode().add(EnumClassCode.OBS.getValue());
			observation2.setMoodCode(XActMoodDocumentObservation.EVN);
			observation2.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.AGE_OBSERVATION_TEMPLATE));
			observation2.setCode(PopulateDataType.populateCD(SNOMEDCode.getCode(CCDConstants.AGE),EnumCodeSystem.OID_SNOMED_CT.getValue(),CCDConstants.AGE));
			observation2.setStatusCode(PopulateDataType.populateCS(EnumStatusCode.COMPLETED.getValue()));
			observation2.getValue().add(PopulateDataType.populateIVLINT(relative.getAgeAtOnset()));
			entryRelationship2.setObservation(observation2);
			observation.getEntryRelationship().add(entryRelationship2);
			component.setObservation(observation);
			listComponent4.add(component);

		}
		for(Component4 com:listComponent4){
			organizer.getComponent().add(com);
		}
		organizer.setSubject(subject);
		entry.setOrganizer(organizer);

		return entry;
	}
	/**
	 *  Method is used to create and add data rows in Table Body
	 *  @param objList - List of Model
	 * @param table - Table body
	 * @param date - Recoded date for given vital sign
	 */
	@SuppressWarnings("unchecked")
	private static void createTableForVitalSign(List<?> objList, StrucDocTable table, Set<String> date) {
		Set<String> fieldSet = new LinkedHashSet<>();
		List<String> header = new ArrayList<>();
		if(table.getThead().getTr().size()>0)
		{
			table.getThead().getTr().removeAll(table.getThead().getTr());
		}
		header.add("Date/time");
		if(objList.get(0) instanceof  VitalSigns)
		{
			if(table.getThead().getTr().size()>1)
			{
				header = null;
				table.getThead().getTr().add(getStrucDocVital(objList,date,header));
			}
			else
			{
				table.getThead().getTr().add(getStrucDocVital(objList,date,header));
			}

			for(Object obj : objList){
				if(!(((VitalSigns)obj).getVitalSigns() ==null || ((VitalSigns)obj).getVitalSigns().isEmpty())){
					fieldSet.add(((VitalSigns) obj).getVitalSigns());
				}
			}
			table.getTbody().get(0).getTr().addAll(getTBodyVitalSign((List<VitalSigns>)objList,fieldSet,date));
		}
	}
	/**
	 *  Method is used to create and add Header in Table Body
	 *  @param objList - List of Model  
	 * @param date - Recoded date for given vital sign
	 * @param header - header for vital sign table
	 * @return tr - Represent populated table row
	 */
	@SuppressWarnings("unchecked")
	private static StrucDocTr getStrucDocVital(List<?> objList, Set<String> date, List<String> header)
	{

		StrucDocTr tr = objFactory.createStrucDocTr();
		if(header != null)
		{
			StrucDocTh th=objFactory.createStrucDocTh();
			th.getContent().add(header.get(0));
			tr.getThOrTd().add(th);
		}
		Iterator<VitalSigns> itr = (Iterator<VitalSigns>) objList.iterator();
		VitalSigns objVital;
		while(itr.hasNext())
		{
			objVital= itr.next();

			if(objVital.getDate()!=null && objVital.getVitalSigns()!=null)
				date.add(objVital.getDate());
		}
		Iterator<String> itrDate = date.iterator();
		while(itrDate.hasNext())
		{
			StrucDocTh th=objFactory.createStrucDocTh();
			th.getContent().add((Serializable)StandardMethods.getCalendarDate( itrDate.next()));
			tr.getThOrTd().add(th);
		}
		return tr;
	}
	/**
	 *  Method is used to create and add Table Body for Vital Sign table
	 *  @param objListVitalSigns - object of VitalSign Model
	 *  @param fieldSet - Set of fields 
	 *  @param date -Recoded date for given vital sign
	 *  @return tr - Represent populated table row

	 */
	private static Collection<? extends StrucDocTr> getTBodyVitalSign(List<VitalSigns> objListVitalSigns,
			Set<String> fieldSet, Set<String> date) {

		List<StrucDocTr> trList = new LinkedList<>();

		List<String> fieldVal = new LinkedList<>(fieldSet);
		List<String> dateCount = new LinkedList<>(date);

		for(int i=0;i<fieldSet.size();i++) //while(itr.hasNext())
		{	int fieldValue =0 ;
		StrucDocTr tr = objFactory.createStrucDocTr();
		for(VitalSigns objVitalSigns : objListVitalSigns)
		{
			if(!(objVitalSigns.getVitalSigns() ==null || objVitalSigns.getVitalSigns().isEmpty())){
				if(fieldVal.get(i).equals(objVitalSigns.getVitalSigns()))
				{
					if(fieldValue++==0){
						StrucDocTh th = objFactory.createStrucDocTh();
						th.getContent().add(objVitalSigns.getVitalSigns());
						tr.getThOrTd().add(th);
					}

				}
			}
		}

		for(int j=0;j<dateCount.size();j++)
		{
			StrucDocTd td1 = objFactory.createStrucDocTd();
			for(VitalSigns objVitalSigns : objListVitalSigns)
			{
				if(!(objVitalSigns.getVitalSigns() ==null || objVitalSigns.getVitalSigns().isEmpty()
						||objVitalSigns.getDate()==null || objVitalSigns.getDate().isEmpty()
						||objVitalSigns.getUnit()==null || objVitalSigns.getUnit().isEmpty()
						||objVitalSigns.getValue()==null || objVitalSigns.getValue().isEmpty())){

					if(objVitalSigns.getVitalSigns().equals(fieldVal.get(i)))
					{
						if(objVitalSigns.getDate().equals(dateCount.get(j)))
						{
							td1.getContent().add(objVitalSigns.getValue()+" "+objVitalSigns.getUnit());
						}
						else
							td1.getContent().add(null);
					}
				}
			}
			tr.getThOrTd().add(td1);
		}
		trList.add(tr);
		}

		return trList;
	}

	/**
	 *  Method is used to create and add Header and body in Table for the Result Section
	 *  @param table contains the table for Result Section
	 *  @param result contains one result object from the list of Result objects
	 *  @param counter counts number of blank columns to add in order to set data in the respective date column
	 */
	private static void getTbodyResult(StrucDocTable table,Results result,int counter){

		StrucDocTd td = objFactory.createStrucDocTd();
		//td.setColspan("3");
		//td.getStyleCode().add("BoldItalics");
		StrucDocThead thead=table.getThead();
		int colNum=thead.getTr().size();
		StrucDocTr tr = objFactory.createStrucDocTr();
		tr.getThOrTd().add(td);
		StrucDocTbody tbody=table.getTbody().get(0);
		tbody.getTr().add(tr);
		List<TestReport> listTest=result.getTestDetails();
		for(TestReport report: listTest){
			if(report==null||report.getTestName()==null || report.getTestName().isEmpty()
					||report.getResultTypeCode()==null || report.getResultTypeCode().isEmpty())
			{
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.RESULTS.getValue()));
				continue;
			}else if(report.getRange()==null ||report.getRange().isEmpty()
					||report.getUnit()==null || report.getUnit().isEmpty()
					||report.getValue()==null ||report.getValue().isEmpty()){
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionAttributeMissing",EnumSection.RESULTS.getValue()));
			}
			if(td.getContent().size()==0){
				td.getContent().add(report.getResultTypeCode());
				td.setColspan(colNum+"");
			}
			StrucDocTd td1 = objFactory.createStrucDocTd();
			if(!(report.getRange()==null || report.getRange().isEmpty()
					||report.getUnit()==null || report.getUnit().isEmpty())){
				td1.getContent().add(report.getTestName()+"("+report.getRange()+" "+report.getUnit()+")");
			}else{
				td1.getContent().add(report.getTestName());
			}
			StrucDocTd td2 = objFactory.createStrucDocTd();
			td2.getContent().add(report.getValue());
			StrucDocTr tr2 = objFactory.createStrucDocTr();
			tr2.getThOrTd().add(td1);
			for(int i=0;i<counter;i++){

				StrucDocTd tdx = objFactory.createStrucDocTd();
				tr2.getThOrTd().add(tdx);
				colNum--;
			}

			tr2.getThOrTd().add(td2);
			for(int i=0;i<colNum;i++){
				StrucDocTd tdx = objFactory.createStrucDocTd();
				tr2.getThOrTd().add(tdx);
			}
			tbody.getTr().add(tr2);
		}
	}}


