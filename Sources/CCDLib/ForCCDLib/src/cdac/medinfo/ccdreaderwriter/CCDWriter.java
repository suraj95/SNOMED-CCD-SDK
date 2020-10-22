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
package cdac.medinfo.ccdreaderwriter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import cdac.medinfo.base.CCDConstantTemplates;
import cdac.medinfo.base.CCDConstants;
import cdac.medinfo.base.StandardMethods;
import cdac.medinfo.ccd.narrativeblock.ObjectFactory;
import cdac.medinfo.ccd.templates.ClinicalDocument;
import cdac.medinfo.ccd.templates.Component3;
import cdac.medinfo.ccd.templates.InfrastructureRootTypeId;
import cdac.medinfo.codesytem.SNOMEDCode;
import cdac.medinfo.enrty.HeaderEntry;
import cdac.medinfo.enrty.PopulateDataType;
import cdac.medinfo.enrty.SectionGenration;
import cdac.medinfo.enums.EnumCodeSystem;
import cdac.medinfo.enums.EnumLOINC;
import cdac.medinfo.enums.EnumSection;
import cdac.medinfo.exception.HeaderMissingException;
import cdac.medinfo.exception.SerializeException;
import cdac.medinfo.model.AdvanceDirectives;
import cdac.medinfo.model.Alerts;
import cdac.medinfo.model.AuthorInfo;
import cdac.medinfo.model.CCDHeader;
import cdac.medinfo.model.DocumentationOfInfo;
import cdac.medinfo.model.Encounters;
import cdac.medinfo.model.FamilyHistory;
import cdac.medinfo.model.FunctionalStatus;
import cdac.medinfo.model.Immunizations;
import cdac.medinfo.model.MedicalEquipment;
import cdac.medinfo.model.Medications;
import cdac.medinfo.model.OrganizationInfo;
import cdac.medinfo.model.Participant;
import cdac.medinfo.model.PatientDetails;
import cdac.medinfo.model.Payer;
import cdac.medinfo.model.Plan;
import cdac.medinfo.model.Problems;
import cdac.medinfo.model.Procedures;
import cdac.medinfo.model.Results;
import cdac.medinfo.model.SocialHistory;
import cdac.medinfo.model.SummaryPurpose;
import cdac.medinfo.model.VitalSigns;
import cdac.medinfo.utils.CCDLogger;
import cdac.medinfo.utils.PropertyLoader;
import cdac.medinfo.utils.ResourceBundleWrapper;
import cdac.medinfo.validator.CCDValidator;
/**
 * This class used for generating Clinical Document in XML format
 */
public class CCDWriter {
	private Marshaller marshaller = null;
	private ObjectFactory objFactory;
	private ClinicalDocument objClinicalDoc ;
	private CCDLogger objCCDLogger = CCDLogger.getLogger();
	
	public CCDWriter(CCDHeader objCCDHeader)
	{
		try{
			if(objCCDHeader==null||objCCDHeader.getPatientDetails() ==null || objCCDHeader.getPatientDetails().isEmpty())
			{
				throw new HeaderMissingException();
			}
			else
			{
				objFactory = new ObjectFactory();
				objClinicalDoc = objFactory.createClinicalDocument();
				PropertyLoader.getInstance();
				if(objCCDHeader.getOrganization()!=null)
				{
					setOrganizationName(objCCDHeader.getOrganization());
				}					
				else
				{
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","OrganizationMissing",EnumSection.HEADER.getValue()));
				}
				addCCDHeader(objClinicalDoc,objCCDHeader);
			}
		
		}catch(HeaderMissingException hme)
		{
			objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","HeaderMissing",EnumSection.HEADER.getValue()));
			throw new HeaderMissingException();
		}
	}

	//Setting Organization name property file
	private  void setOrganizationName(OrganizationInfo objOrgName) {
		if(objOrgName!=null && objOrgName.getOrganizationName()!=null)
			PropertyLoader.setProperty("Organization_Name", objOrgName.getOrganizationName());		
	}
	/**
	 *  This method is used to create Continuity Of Care Document Header
	 *  
	 */
	private void addCCDHeader(ClinicalDocument objClinicalDoc, CCDHeader objCCDHeader)
	{
		List<PatientDetails> listPatientDetails=new ArrayList<PatientDetails>();
		for(PatientDetails objPD:objCCDHeader.getPatientDetails()){
			if(objPD==null){
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.HEADER.getValue()));
			}else
				listPatientDetails.add(objPD);
		}
		if(!(listPatientDetails==null||listPatientDetails.isEmpty()))
		{//Header Entry class object for populating Entry
			HeaderEntry entry = new HeaderEntry();
			InfrastructureRootTypeId typeId = objFactory.createInfrastructureRootTypeId();

			typeId.setRoot(CCDConstantTemplates.CLINICALDOCUMENT_ROOT);
			typeId.setExtension(CCDConstantTemplates.CLINICALDOCUMENT_EXTENSION);
			objClinicalDoc.setTypeId(typeId);

			objClinicalDoc.getTemplateId().add(PopulateDataType.populateII(CCDConstantTemplates.CLINICALDOCUMENT_ROOT_TEMPLATES));//<!-- CCD v1.0 Templates Root -->
			objClinicalDoc.setId(PopulateDataType.populateII(StandardMethods.generateUUID())); 
			objClinicalDoc.setTitle(PopulateDataType.populateST(PropertyLoader.getProperty("Organization_Name")+" "+CCDConstants.CONTINUITY_OF_CARE_DOCUMENT)); 
			objClinicalDoc.setCode(PopulateDataType.populateCE(EnumLOINC.SUMMARIZATION_OF_EPISODE_NOTE_CODE.getValue(),EnumCodeSystem.OID_LOINC.getValue(),CCDConstants.SUMMARIZATION_OF_EPISODE_NOTE));
			objClinicalDoc.setEffectiveTime(PopulateDataType.populateTS());  
			objClinicalDoc.setConfidentialityCode(PopulateDataType.populateCE(CCDConstants.CONFIDENTIALITY_CODE,EnumCodeSystem.OID_CONFIDENTIALITY.getValue()));  // code system not found in IG
			objClinicalDoc.setLanguageCode(PopulateDataType.populateCS(CCDConstants.LANGUAGECODE));
			//Adding Author in Header
			if(objCCDHeader.getAuthor()!=null && !objCCDHeader.getAuthor().isEmpty())
			{
				for(AuthorInfo objAuthor:objCCDHeader.getAuthor())
				{
					objClinicalDoc.getAuthor().add(entry.populateAuthor(objAuthor));
				}
			}
			
			//Adding Informant in Header
			objClinicalDoc.getInformant().add(entry.populateInformant());
			//Adding Custodian in Header
			objClinicalDoc.setCustodian(entry.populateCustodian());
			//Adding Legal Authenticator in Header
			objClinicalDoc.setLegalAuthenticator(entry.populateLegalAuthenticator());
			for (PatientDetails objPatientDetail : listPatientDetails) {
				//header section creation
				//Adding Record Target in Header
				objClinicalDoc.getRecordTarget().add(entry.populateRecordTarget(objPatientDetail));
				//Adding Participant in Header 
				if(objPatientDetail.getParticipant()!=null)
				{
					Iterator<Participant> itr = objPatientDetail.getParticipant().iterator();
					while(itr.hasNext())
					{
						objClinicalDoc.getParticipant().add(entry.getParticipant(itr.next()));
					}
				}
			}
			
			for(DocumentationOfInfo objDocumentationof:objCCDHeader.getDocumentationOf())
			{
				objClinicalDoc.getDocumentationOf().add(entry.populateDocumentation(objDocumentationof));
			}
			if(objClinicalDoc.getComponent()== null)
			{	
				objClinicalDoc.setComponent(objFactory.createComponent2());	
				if(objClinicalDoc.getComponent().getStructuredBody()==null)
					objClinicalDoc.getComponent().setStructuredBody(objFactory.createStructuredBody());
				objClinicalDoc.getComponent().getStructuredBody().getComponent();
			}}
		else 
		{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.HEADER.getValue()));
			throw new HeaderMissingException();
		}
	}


	/**
	 * This method is used to add Summary Or Purpose in Clinical Document
	 * @param objPurpose - List of SummaryPurpose object
	 */
	public void addSummaryOrPurpose(List<SummaryPurpose> objPurpose){
		if(objClinicalDoc!=null){
			if(!(objPurpose==null || objPurpose.isEmpty()))
			{
				List<SummaryPurpose> listPurpose=new ArrayList<SummaryPurpose>();
				for(SummaryPurpose objSumPurpose:objPurpose){
					if(objSumPurpose==null){
						objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.SUMMARY_PURPOSE.getValue()));
					}else
						listPurpose.add(objSumPurpose);
				}
				if(!(listPurpose==null || listPurpose.isEmpty()))
				{
					Component3 currentComponent=null;
					List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
					for(Component3 component:componentList){
						if(component.getSection().getTitle().getContent()==EnumSection.SUMMARY_PURPOSE.getValue()){
							currentComponent=component;
							break;
						}
					}
					if(!componentList.contains(currentComponent))
						componentList.add(SectionGenration.generateSection(listPurpose,null));
					else if(componentList.contains(currentComponent)){
						componentList.remove(currentComponent);
						componentList.add(SectionGenration.generateSection(listPurpose,currentComponent));
					}
				}
				else{
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.SUMMARY_PURPOSE.getValue()));
				}}
			else{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.SUMMARY_PURPOSE.getValue()));
			}}
		else
		{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.SUMMARY_PURPOSE.getValue()));
		}
	}
	/**
	 *  This method is used to add Payers Section in Clinical Document
	 *  @param objPayerDetails - List of Payers 
	 */
	public  void addPayer(List<Payer> objPayerDetails)  {
		if(objClinicalDoc!=null)
		{ if(!(objPayerDetails==null || objPayerDetails.isEmpty())){
			List<Payer> listPayers=new ArrayList<Payer>();
			for(Payer objPayers:objPayerDetails){
				if(objPayers==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.PAYERS.getValue()));
				}else
					listPayers.add(objPayers);
			}
			if(!(listPayers==null || listPayers.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.PAYERS.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listPayers,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listPayers,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.PAYERS.getValue()));
			}			}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.PAYERS.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.PAYERS.getValue()));
		}		
	}
	/**
	 *  This method is used to add Advance Directives Section in Clinical Document
	 *  @param objAdvanceDirectives - List of Advance Directives objects
	 */
	public  void addAdvanceDirectives(List<AdvanceDirectives> objAdvanceDirectives){
		if(objClinicalDoc!=null)
		{ if(!(objAdvanceDirectives==null || objAdvanceDirectives.isEmpty())){
			List<AdvanceDirectives> listAdvanceDirectives=new ArrayList<AdvanceDirectives>();
			for(AdvanceDirectives objAdvDirectives:objAdvanceDirectives){
				if(objAdvDirectives==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.ADVANCE_DIRECTIVE.getValue()));
				}else
					listAdvanceDirectives.add(objAdvDirectives);
			}
			if(!(listAdvanceDirectives==null || listAdvanceDirectives.isEmpty()))
			{	Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.ADVANCE_DIRECTIVE.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listAdvanceDirectives,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listAdvanceDirectives,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.ADVANCE_DIRECTIVE.getValue()));
			}		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.ADVANCE_DIRECTIVE.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.ADVANCE_DIRECTIVE.getValue()));
		}
	}
	/**
	 *  This method is used to add Plan Section in Clinical Document
	 *  @param objPlanDetails - List of Plan objects
	 */
	public  void addPlan(List<Plan> objPlanDetails) {
		if(objClinicalDoc!=null)
		{ if(!(objPlanDetails==null || objPlanDetails.isEmpty())){
			List<Plan> listPlan=new ArrayList<Plan>();
			for(Plan objPlan:objPlanDetails){
				if(objPlan==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.PLAN.getValue()));
				}else
					listPlan.add(objPlan);
			}
			if(!(listPlan==null || listPlan.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.PLAN.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listPlan,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listPlan,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.PLAN.getValue()));
			}		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.PLAN.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.PLAN.getValue()));
		}
	}
	/**
	 *  This method is used to add Alerts Section in Clinical Document
	 *  @param objAlertsDetails - List of Alerts objects
	 */
	public  void addAlerts(List<Alerts> objAlertsDetails){
		if(objClinicalDoc!=null)
		{ if(!(objAlertsDetails==null || objAlertsDetails.isEmpty())){
			List<Alerts> listAlerts=new ArrayList<Alerts>();
			for(Alerts objAlerts:objAlertsDetails){
				if(objAlerts==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.ALERTS.getValue()));
				}else
					listAlerts.add(objAlerts);
			}
			if(!(listAlerts==null || listAlerts.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.ALERTS.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listAlerts,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listAlerts,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.ALERTS.getValue()));
			}		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.ALERTS.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.ALERTS.getValue()));
		}
	}
	/**
	 *  This method is used to add Functional Status Section in Clinical Document
	 *  @param objFunctionalStatus - List of FunctionalStatus objects
	 */
	public  void addFunctionalStatus(List<FunctionalStatus> objFunctionalStatus) {
		if(objClinicalDoc!=null)
		{ if(!(objFunctionalStatus==null || objFunctionalStatus.isEmpty())){
			List<FunctionalStatus> listFunctionalStatus=new ArrayList<FunctionalStatus>();
			for(FunctionalStatus objFuncStatus:objFunctionalStatus){
				if(objFuncStatus==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.FUNCTIONAL_STATUS.getValue()));
				}else
					listFunctionalStatus.add(objFuncStatus);
			}
			if(!(listFunctionalStatus==null || listFunctionalStatus.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.FUNCTIONAL_STATUS.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listFunctionalStatus,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listFunctionalStatus,currentComponent));
			}
			}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.FUNCTIONAL_STATUS.getValue()));
			}		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.FUNCTIONAL_STATUS.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.FUNCTIONAL_STATUS.getValue()));
		}
	}
	/**
	 *  This method is used to add Vital Signs Section in Clinical Document
	 *  @param objVitalSigns - List of VitalSigns objects
	 */
	public  void addVitalSigns(List<VitalSigns> objVitalSigns) {
		if(objClinicalDoc!=null)
		{ if(!(objVitalSigns==null || objVitalSigns.isEmpty())){
			List<VitalSigns> listVitalSigns=new ArrayList<VitalSigns>();
			for(VitalSigns objVital:objVitalSigns){
				if(objVital==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.VITAL_SIGNS.getValue()));
				}else
					listVitalSigns.add(objVital);
			}
			if(!(listVitalSigns==null || listVitalSigns.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.VITAL_SIGNS.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listVitalSigns,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listVitalSigns,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.VITAL_SIGNS.getValue()));
			}		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.VITAL_SIGNS.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.VITAL_SIGNS.getValue()));
		}
	}
	/**
	 *  This method is used to add Social History Section in Clinical Document
	 *  @param objSocialHistory - List of SocialHistory objects
	 */
	public  void addSocialHistory(List<SocialHistory> objSocialHistory) {
		if(objClinicalDoc!=null)
		{ if(!(objSocialHistory==null || objSocialHistory.isEmpty())){
			List<SocialHistory> listSocialHistory=new ArrayList<SocialHistory>();
			for(SocialHistory objSocial:objSocialHistory){
				if(objSocial==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.SOCIAL_HISTORY.getValue()));
				}else
					listSocialHistory.add(objSocial);
			}
			if(!(listSocialHistory==null || listSocialHistory.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.SOCIAL_HISTORY.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listSocialHistory,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listSocialHistory,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.SOCIAL_HISTORY.getValue()));
			}
		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.SOCIAL_HISTORY.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.SOCIAL_HISTORY.getValue()));
		}
	}
	/**
	 *  This method is used to add Problems Section in Clinical Document
	 *  @param objProblems - List of Problems objects
	 */
	public  void addProblems(List<Problems> objProblems) {
		if(objClinicalDoc!=null)
		{ if(!(objProblems==null || objProblems.isEmpty())){
			List<Problems> listProblems=new ArrayList<Problems>();
			for(Problems objProb:objProblems){
				if(objProb==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.PROBLEMS.getValue()));
				}else
					listProblems.add(objProb);
			}
			if(!(listProblems==null || listProblems.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.PROBLEMS.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listProblems,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listProblems,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.PROBLEMS.getValue()));
			}		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.PROBLEMS.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.PROBLEMS.getValue()));
		}
	}
	/**
	 *  This method is used to add Procedures Section in Clinical Document
	 *  @param objProcedures - List of Procedures objects
	 */
	public  void addProcedures(List<Procedures> objProcedures){
		if(objClinicalDoc!=null)
		{ if(!(objProcedures==null || objProcedures.isEmpty())){
			List<Procedures> listProcedure=new ArrayList<Procedures>();
			for(Procedures objProb:objProcedures){
				if(objProb==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.PROCEDURES.getValue()));
				}else
					listProcedure.add(objProb);
			}
			if(!(listProcedure==null || listProcedure.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.PROCEDURES.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listProcedure,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listProcedure,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.PROCEDURES.getValue()));
			}		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.PROCEDURES.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.PROCEDURES.getValue()));
		}
	}
	/**
	 *  This method is used to add Encounters Section in Clinical Document
	 *  @param objEncounters - List of Encounters objects
	 */
	public  void addEncounters(List<Encounters> objEncounters){
		if(objClinicalDoc!=null)
		{ if(!(objEncounters==null || objEncounters.isEmpty())){
			List<Encounters> listEncounters=new ArrayList<Encounters>();
			for(Encounters objEnc:objEncounters){
				if(objEnc==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.ENCOUNTERS.getValue()));
				}else
					listEncounters.add(objEnc);
			}
			if(!(listEncounters==null || listEncounters.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.ENCOUNTERS.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listEncounters,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listEncounters,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.ENCOUNTERS.getValue()));
			}
		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.ENCOUNTERS.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.ENCOUNTERS.getValue()));
		}
	}
	/**
	 *  This method is used to add Medical Equipment Section in Clinical Document
	 *  @param objMedicalEquipment - List of MedicalEquipment objects
	 */
	public  void addMedicalEquipment(List<MedicalEquipment> objMedicalEquipment) {
		if(objClinicalDoc!=null)
		{ if(!(objMedicalEquipment==null || objMedicalEquipment.isEmpty())){
			List<MedicalEquipment> listMedicalEquipment=new ArrayList<MedicalEquipment>();
			for(MedicalEquipment objMedEquipment:objMedicalEquipment){
				if(objMedEquipment==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.MEDICAL_EQUIPMENT.getValue()));
				}else
					listMedicalEquipment.add(objMedEquipment);
			}
			if(!(listMedicalEquipment==null || listMedicalEquipment.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.MEDICAL_EQUIPMENT.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listMedicalEquipment,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listMedicalEquipment,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.MEDICAL_EQUIPMENT.getValue()));
			}		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.MEDICAL_EQUIPMENT.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.MEDICAL_EQUIPMENT.getValue()));
		}
	}
	/**
	 *  This method is used to add Medications Section in Clinical Document
	 *  @param objMedications - List of Medications objects
	 */
	public  void addMedication(List<Medications> objMedications) {
		if(objClinicalDoc!=null)
		{ if(!(objMedications==null || objMedications.isEmpty())){
			List<Medications> listMedications=new ArrayList<Medications>();
			for(Medications objMedication:objMedications){
				if(objMedication==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.MEDICATIONS.getValue()));
				}else
					listMedications.add(objMedication);
			}
			if(!(listMedications==null || listMedications.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.MEDICATIONS.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listMedications,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listMedications,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.MEDICATIONS.getValue()));
			}		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.MEDICATIONS.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.MEDICATIONS.getValue()));
		}
	}
	/**
	 *  This method is used to add Immunizations Section in Clinical Document
	 *  @param objImmunizations - List of Immunizations objects
	 */
	public  void addImmunization(List<Immunizations> objImmunizations) {
		if(objClinicalDoc!=null)
		{ if(!(objImmunizations==null || objImmunizations.isEmpty())){
			List<Immunizations> listImmunization=new ArrayList<Immunizations>();
			for(Immunizations objImmunization:objImmunizations){
				if(objImmunization==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.IMMUNIZATION.getValue()));
				}else
					listImmunization.add(objImmunization);
			}
			if(!(listImmunization==null || listImmunization.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.IMMUNIZATION.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listImmunization,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listImmunization,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.IMMUNIZATION.getValue()));
			}		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.IMMUNIZATION.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.IMMUNIZATION.getValue()));
		}
	}
	/**
	 *  This method is used to add Family History Section in Clinical Document
	 *  @param objFamilyDetails - List of FamilyHistory objects
	 */
	public  void addFamilyHistory(List<FamilyHistory> objFamilyDetails) {
		if(objClinicalDoc!=null)
		{ if(!(objFamilyDetails==null || objFamilyDetails.isEmpty())){
			List<FamilyHistory> listFamilyHistory=new ArrayList<FamilyHistory>();
			for(FamilyHistory objFamilyHistory:objFamilyDetails){
				if(objFamilyHistory==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.FAMILY_HISTORY.getValue()));
				}else
					listFamilyHistory.add(objFamilyHistory);
			}
			if(!(listFamilyHistory==null || listFamilyHistory.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.FAMILY_HISTORY.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listFamilyHistory,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listFamilyHistory,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.FAMILY_HISTORY.getValue()));
			}		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.FAMILY_HISTORY.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.FAMILY_HISTORY.getValue()));
		}

	}
	/**
	 *  This method is used to add Results Section in Clinical Document
	 *  @param objResultDetails - List of Results objects
	 */
	public  void addResults(List<Results> objResultDetails) {
		if(objClinicalDoc!=null)
		{ if(!(objResultDetails==null || objResultDetails.isEmpty())){
			List<Results> listResult=new ArrayList<Results>();
			for(Results objResult:objResultDetails){
				if(objResult==null){
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.RESULTS.getValue()));
				}else
					listResult.add(objResult);
			}
			if(!(listResult==null || listResult.isEmpty()))
			{Component3 currentComponent=null;
			List<Component3> componentList=objClinicalDoc.getComponent().getStructuredBody().getComponent();
			for(Component3 component:componentList){
				if(component.getSection().getTitle().getContent()==EnumSection.RESULTS.getValue()){
					currentComponent=component;
					break;
				}
			}
			if(!componentList.contains(currentComponent))
				componentList.add(SectionGenration.generateSection(listResult,null));
			else if(componentList.contains(currentComponent)){
				componentList.remove(currentComponent);
				componentList.add(SectionGenration.generateSection(listResult,currentComponent));
			}}
			else
			{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.RESULTS.getValue()));
			}		}
		else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissing",EnumSection.RESULTS.getValue()));
		}
		}else{
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","ClinicalDocMissing",EnumSection.RESULTS.getValue()));
		}
	}
	/**
	 *  This method is used to generate Continuity Of Care Document in XML format
	 *  @param storagePath Path for storing CCD doc
	 *  @param CCDXslPath Path of Xslt  
	 */
	public void generateDoc(String storagePath, String CCDXslPath)
	{
		try {
			if(objClinicalDoc==null)
			{
				throw new SerializeException(); 
			}
			else
			{
				if(objClinicalDoc.getComponent()==null)
				{
					throw new HeaderMissingException();
				}
				//method call to generate XML
				getCCDWriter(objClinicalDoc,storagePath,CCDXslPath);
			}
		}catch(HeaderMissingException hme)
		{
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","HeaderMissing",EnumSection.HEADER.getValue()));
		}catch(SerializeException se)
		{
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","SerializeFailed"));
		}
	}

	/**
	 *  This method is used to include SNOMED-CT code system database
	 *  @param dbPath Path of SNOMED-CT code system database
	 */
	public void addSNOMEDCodeSystem(String dbPath) 
	{
		SNOMEDCode.checkForConnection(dbPath);
	}
	/**
	 *  This method is used for Marshaller instance of Continuity Of Care Document 
	 * @throws JAXBException 
	 * @throws IOException 
	 * @param Marshaller
	 */

	private Marshaller getCCDWriter(ClinicalDocument ccdDoc, String storagePath, String CCDXslPath){

		String storagePath1 = null;
		if(storagePath == null||storagePath == "" ||storagePath.isEmpty())
		{
			storagePath1 = StandardMethods.getTempDirectoryStorage()+"ClinicalDocument.xml" ;
		}
		else
		{
			File fileCCD = new File(storagePath);
		
			if(fileCCD.isDirectory()){
					storagePath1= storagePath +File.separator+"ClinicalDocument.xml";
			}
			else if(getFileExtension(fileCCD).equalsIgnoreCase("xml"))
			{	
				String dir = storagePath.substring(0, storagePath.lastIndexOf(File.separator));
				new File(dir).mkdir();
				storagePath1= storagePath ;
			}
			else
			{
				new File(storagePath).mkdir();
				storagePath1= storagePath +File.separator+"ClinicalDocument.xml";
			}
		}
		if(CCDXslPath==null||CCDXslPath==""||CCDXslPath.isEmpty())
		{
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","InvalidXSLFilePath"));
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","DefaultXslApplied"));
			String path = getXSLPath();
			File xmlFile = new File(path);
			if(xmlFile.exists()&&xmlFile.isFile()&& xmlFile.canRead())
			{			
				if(getFileExtension(xmlFile).equals("xsl")){
					
					objCCDLogger.log(ResourceBundleWrapper.getValue("status","DefaultPath",path));
					CCDXslPath = path;
				}
			}
			else{
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","XSLNotFound",path));
			}
		}
		else{
			File fileCCD = new File(CCDXslPath);
			if(fileCCD==null||fileCCD.isDirectory()||!fileCCD.exists())
			{
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","InvalidXSLFilePath"));
				CCDXslPath = "";
			}
		}

		if (marshaller == null && storagePath1!=null ) {
			try {

				JAXBContext jaxbContext = JAXBContext.newInstance(ClinicalDocument.class);
				marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:hl7-org:v3 CDA.xsd");
				marshaller.setProperty(Marshaller.JAXB_FRAGMENT, new Boolean(true));
				marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
						"<?xml version='1.0'?> \n <?xml-stylesheet type=\"text/xsl\" href=\""+CCDXslPath+"\" ?>");
				marshaller.marshal(ccdDoc, System.out);
						
				
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("status","StoragePath",storagePath1));
				marshaller.marshal(ccdDoc,new File(storagePath1));
				CCDValidator.validate( storagePath1);
			}
			catch (Exception se) {
				se.printStackTrace();
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","SerializeFailed"));
			}
		}
		else{
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","FileNotFound"));
		}
		return marshaller;
	}
	/**
	 * To retrieve XSLT path from system
	 * @return path of XSLT file
	 */
	private String getXSLPath()
	{
		try {
			File jarPath = new File(CCDWriter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			String localPath = jarPath.getParentFile().getParent().concat(File.separator+"Resources"+File.separator+"CCD.xsl");
			return localPath;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","XslPathNotFound"));
			return null;
		}
	}
	/*
	 * Used to retrieve Extension for a given File
	 */
	private String getFileExtension(File file) {
		String fileName = file.getName();
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".")+1);
		else return "";
	}
}	


