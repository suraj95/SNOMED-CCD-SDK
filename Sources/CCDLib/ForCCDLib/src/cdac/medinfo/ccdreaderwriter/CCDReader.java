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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import cdac.medinfo.base.CCDConstantTemplates;
import cdac.medinfo.base.CCDConstants;
import cdac.medinfo.ccd.datatype.AD;
import cdac.medinfo.ccd.datatype.ANY;
import cdac.medinfo.ccd.datatype.AdxpAdditionalLocator;
import cdac.medinfo.ccd.datatype.AdxpBuildingNumberSuffix;
import cdac.medinfo.ccd.datatype.AdxpCareOf;
import cdac.medinfo.ccd.datatype.AdxpCensusTract;
import cdac.medinfo.ccd.datatype.AdxpCity;
import cdac.medinfo.ccd.datatype.AdxpCounty;
import cdac.medinfo.ccd.datatype.AdxpDelimiter;
import cdac.medinfo.ccd.datatype.AdxpDeliveryAddressLine;
import cdac.medinfo.ccd.datatype.AdxpDeliveryInstallationArea;
import cdac.medinfo.ccd.datatype.AdxpDeliveryInstallationQualifier;
import cdac.medinfo.ccd.datatype.AdxpDeliveryInstallationType;
import cdac.medinfo.ccd.datatype.AdxpDeliveryMode;
import cdac.medinfo.ccd.datatype.AdxpDeliveryModeIdentifier;
import cdac.medinfo.ccd.datatype.AdxpDirection;
import cdac.medinfo.ccd.datatype.AdxpHouseNumber;
import cdac.medinfo.ccd.datatype.AdxpHouseNumberNumeric;
import cdac.medinfo.ccd.datatype.AdxpPostBox;
import cdac.medinfo.ccd.datatype.AdxpPostalCode;
import cdac.medinfo.ccd.datatype.AdxpPrecinct;
import cdac.medinfo.ccd.datatype.AdxpState;
import cdac.medinfo.ccd.datatype.AdxpStreetAddressLine;
import cdac.medinfo.ccd.datatype.AdxpStreetName;
import cdac.medinfo.ccd.datatype.AdxpStreetNameBase;
import cdac.medinfo.ccd.datatype.AdxpStreetNameType;
import cdac.medinfo.ccd.datatype.AdxpUnitID;
import cdac.medinfo.ccd.datatype.AdxpUnitType;
import cdac.medinfo.ccd.datatype.CD;
import cdac.medinfo.ccd.datatype.CE;
import cdac.medinfo.ccd.datatype.CS;
import cdac.medinfo.ccd.datatype.ED;
import cdac.medinfo.ccd.datatype.EnFamily;
import cdac.medinfo.ccd.datatype.EnGiven;
import cdac.medinfo.ccd.datatype.EnPrefix;
import cdac.medinfo.ccd.datatype.EnSuffix;
import cdac.medinfo.ccd.datatype.II;
import cdac.medinfo.ccd.datatype.INT;
import cdac.medinfo.ccd.datatype.IVLPQ;
import cdac.medinfo.ccd.datatype.IVLTS;
import cdac.medinfo.ccd.datatype.IVXBTS;
import cdac.medinfo.ccd.datatype.PIVLTS;
import cdac.medinfo.ccd.datatype.PQ;
import cdac.medinfo.ccd.datatype.SXCMTS;
import cdac.medinfo.ccd.datatype.TEL;
import cdac.medinfo.ccd.datatype.TS;
import cdac.medinfo.ccd.enums.XActRelationshipEntryRelationship;
import cdac.medinfo.ccd.narrativeblock.StrucDocTable;
import cdac.medinfo.ccd.narrativeblock.StrucDocTbody;
import cdac.medinfo.ccd.narrativeblock.StrucDocTd;
import cdac.medinfo.ccd.narrativeblock.StrucDocText;
import cdac.medinfo.ccd.narrativeblock.StrucDocTh;
import cdac.medinfo.ccd.narrativeblock.StrucDocTr;
import cdac.medinfo.ccd.templates.Author;
import cdac.medinfo.ccd.templates.ClinicalDocument;
import cdac.medinfo.ccd.templates.Component3;
import cdac.medinfo.ccd.templates.Component4;
import cdac.medinfo.ccd.templates.DocumentationOf;
import cdac.medinfo.ccd.templates.Entry;
import cdac.medinfo.ccd.templates.EntryRelationship;
import cdac.medinfo.ccd.templates.Informant12;
import cdac.medinfo.ccd.templates.Observation;
import cdac.medinfo.ccd.templates.Organization;
import cdac.medinfo.ccd.templates.OrganizationPartOf;
import cdac.medinfo.ccd.templates.Participant1;
import cdac.medinfo.ccd.templates.Participant2;
import cdac.medinfo.ccd.templates.Patient;
import cdac.medinfo.ccd.templates.Performer1;
import cdac.medinfo.ccd.templates.Person;
import cdac.medinfo.ccd.templates.RecordTarget;
import cdac.medinfo.ccd.templates.ReferenceRange;
import cdac.medinfo.ccd.templates.Section;
import cdac.medinfo.ccd.templates.ServiceEvent;
import cdac.medinfo.ccd.templates.Supply;
import cdac.medinfo.enums.EnumEntryDataSection;
import cdac.medinfo.enums.EnumSection;
import cdac.medinfo.enums.EnumStatusCode;
import cdac.medinfo.enums.EnumTableHeader;
import cdac.medinfo.exception.InvalidFileFormat;
import cdac.medinfo.exception.ParseException;
import cdac.medinfo.model.Address;
import cdac.medinfo.model.AdvanceDirectives;
import cdac.medinfo.model.Alerts;
import cdac.medinfo.model.AuthorInfo;
import cdac.medinfo.model.AuthoringDeviceInfo;
import cdac.medinfo.model.CCDHeader;
import cdac.medinfo.model.Contact;
import cdac.medinfo.model.DocumentationOfInfo;
import cdac.medinfo.model.EffectiveDateTime;
import cdac.medinfo.model.Encounters;
import cdac.medinfo.model.FamilyHistory;
import cdac.medinfo.model.FunctionalStatus;
import cdac.medinfo.model.Identifiers;
import cdac.medinfo.model.Immunizations;
import cdac.medinfo.model.MedicalEquipment;
import cdac.medinfo.model.Medications;
import cdac.medinfo.model.OrganizationInfo;
import cdac.medinfo.model.OrganizationPartOfInfo;
import cdac.medinfo.model.Participant;
import cdac.medinfo.model.PatientDetails;
import cdac.medinfo.model.Payer;
import cdac.medinfo.model.Performer;
import cdac.medinfo.model.Plan;
import cdac.medinfo.model.Problems;
import cdac.medinfo.model.Procedures;
import cdac.medinfo.model.Results;
import cdac.medinfo.model.SocialHistory;
import cdac.medinfo.model.SummaryPurpose;
import cdac.medinfo.model.TestReport;
import cdac.medinfo.model.VitalSigns;
import cdac.medinfo.utils.CCDLogger;
import cdac.medinfo.utils.ResourceBundleWrapper;
import cdac.medinfo.validator.CCDValidator;
/**
 * This Class is used to Read Continuity of care Document XML and do Unmarshalling of XML document and provide 
 * CCDReader Object
 */
public class CCDReader {

	private  ClinicalDocument objClinicalDoc ;
	/**
	 * Constructs a CCDReader Object
	 * @param filePath - File path of Clinical Document(XML)
	 * @throws IOException 
	 */
	public CCDReader(String filePath) throws IOException
	{
		try {
			File xmlFile = new File(filePath);
			if(!(getFileExtension(xmlFile).equals("xml")))
				throw new InvalidFileFormat();
			if(!(xmlFile.exists()&&xmlFile.isFile()&& xmlFile.canRead()))
				throw new FileNotFoundException();
			Source source = new StreamSource(xmlFile);
			JAXBContext context = JAXBContext.newInstance(ClinicalDocument.class);
			if(context==null || source == null)
				throw new JAXBException("Parsing Failed");
			Unmarshaller un = context.createUnmarshaller();
			CCDValidator.validate(filePath);
			JAXBElement<ClinicalDocument> root = un.unmarshal(source, ClinicalDocument.class);
			if(root==null || root.getValue()==null)
				throw new ParseException();
			
			objClinicalDoc = root.getValue();
			if(objClinicalDoc.getComponent()==null || objClinicalDoc.getComponent().getStructuredBody()==null)
			{
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","InvalidFile"));
				System.exit(0);
			}
			
		}catch (FileNotFoundException e) {
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","InvalidXMLFilePath"));
			throw new ParseException();
		}catch (InvalidFileFormat e) {
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","InvalidFileFormat"));
			throw new ParseException();
		} catch (JAXBException e) {
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","ParsingFailed"));
			System.exit(0);
		}
		catch(ParseException pe){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","ParseException"));
			System.exit(0);
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

	/**
	 * This method return List of Patient Details 
	 * @return objSummary- List of PatientDetails
	 */
	public List<PatientDetails> getPatientDetails()
	{
		List<PatientDetails> objListPatient= new ArrayList<PatientDetails>();
		PatientDetails objPatientDetails  = new PatientDetails();
		//Extracting Record Target containing patient information
		List<RecordTarget> objListRT= objClinicalDoc.getRecordTarget();
		for(RecordTarget objRT : objListRT)
		{
			//Extracting Patient from Record Target
			Patient objPatient = 	objRT.getPatientRole().getPatient();
			//Setting MRN for Patient
			if(objRT.getPatientRole()!=null)
			{
				List<II> objID = objRT.getPatientRole().getId();
				if(objID.get(0)!=null)
					objPatientDetails.setMrn(objID.get(0).getExtension());
			}
			if(objPatient!=null){
				for(int i=0;i<objPatient.getName().get(0).getContent().size();i++) 
				{
					//Populating patient Name 
					if(objPatient.getName().get(0).getContent().get(i) instanceof JAXBElement)
					{					
						Object objJAXB= ((JAXBElement<?>)objPatient.getName().get(0).getContent().get(i)).getValue();
						if(objJAXB instanceof EnGiven && ((EnGiven) objJAXB).getContent()!=null )
							objPatientDetails.setName(((EnGiven) objJAXB).getContent().toString());
						if(objJAXB instanceof EnFamily && ((EnFamily) objJAXB).getContent()!=null )
							objPatientDetails.setFamily(((EnFamily) objJAXB).getContent().toString());
						if(objJAXB instanceof EnPrefix && ((EnPrefix) objJAXB).getContent()!=null )
							objPatientDetails.setPrefix(((EnPrefix) objJAXB).getContent().toString());
						if(objJAXB instanceof EnSuffix && ((EnSuffix) objJAXB).getContent()!= null )
							objPatientDetails.setSuffix(((EnSuffix) objJAXB).getContent().toString());
					}
				}
				if(objPatient.getBirthTime()!=null)
					objPatientDetails.setBirthTime(objPatient.getBirthTime().getValue().toString());
				if(objPatient.getBirthplace()!=null)
					objPatientDetails.setBirthPlace(objPatient.getBirthplace().toString());
				if(objPatient.getAdministrativeGenderCode()!=null)
					objPatientDetails.setGender(objPatient.getAdministrativeGenderCode().getCode());
				if(objPatient.getMaritalStatusCode()!=null)
					objPatientDetails.setMaritalStatus(objPatient.getMaritalStatusCode().getCode());
				if(objPatient.getReligiousAffiliationCode()!=null)
					objPatientDetails.setReligiousAffiliation(objPatient.getReligiousAffiliationCode().getCode());
				if(objPatient.getRaceCode()!=null)
					objPatientDetails.setRaceCode(objPatient.getRaceCode().getCode());
				if(objPatient.getBirthplace()!=null)
					objPatientDetails.setBirthPlace(objPatient.getBirthplace().getPlace().getAddr().getContent().get(0).toString());
				if(objPatient.getEthnicGroupCode()!=null)
					objPatientDetails.setEthnicGroup(objPatient.getEthnicGroupCode().getCode());

				//extracting patient address
				if(objRT.getPatientRole().getAddr()!=null)
				{
					List<AD> objListAddr =objRT.getPatientRole().getAddr();
					for(AD objAddr : objListAddr)
					{
						Address adr = new Address();
						adr=getAddress(objAddr);
						objPatientDetails.getPersonalAddress().add(adr);
					}
				}
				//Populating Participant
				List<Participant1> objListParticipant= objClinicalDoc.getParticipant();
				for (Participant1 objParticipant : objListParticipant) {
					Participant objPar = new Participant();
					//Populating Participant if he is a Guardian
					if(objParticipant.getAssociatedEntity().getClassCode().get(0).equals("GUAR"))
						objPar.setGaurdian(true);
					else if(objParticipant.getAssociatedEntity().getClassCode().get(0).equals("NOK"))//Populating Participant if he is a Next of Kin
						objPar.setNOK(true);
					else if(objParticipant.getAssociatedEntity().getClassCode().get(0).equals("ECON"))//Populating Participant if he is a Emergency Contact
						objPar.setEmergencyContactPresent(true);
					else if(objParticipant.getAssociatedEntity().getClassCode().get(0).equals("CAREGIVER"))//Populating Participant if he is a caregiver
						objPar.setCareGiverPresent(true);

					if(objParticipant.getAssociatedEntity().getCode()!=null)
					objPar.setParticipantRelation(objParticipant.getAssociatedEntity().getCode().getDisplayName());
					Person objPer = objParticipant.getAssociatedEntity().getAssociatedPerson();
					for(int i=0;i<objPer.getName().get(0).getContent().size();i++)
					{
						//Populating participant Name 
						if(objPer.getName().get(0).getContent().get(i) instanceof JAXBElement)
						{
							Object objJAXB= ((JAXBElement<?>)objPer.getName().get(0).getContent().get(i)).getValue();
							if(objJAXB instanceof EnGiven && ((EnGiven) objJAXB).getContent()!=null )
								objPar.setParticipantName(((EnGiven) objJAXB).getContent().toString());
							if(objJAXB instanceof EnFamily && ((EnFamily) objJAXB).getContent()!=null )
								objPar.setParticipantFamily(((EnFamily) objJAXB).getContent().toString());
							if(objJAXB instanceof EnPrefix && ((EnPrefix) objJAXB).getContent()!=null )
								objPar.setParticipantPrefix(((EnPrefix) objJAXB).getContent().toString());
							if(objJAXB instanceof EnSuffix && ((EnSuffix) objJAXB).getContent()!= null )
								objPar.setParticipantSuffix(((EnSuffix) objJAXB).getContent().toString());
						}
					}
					//Extracting participant Contact
					List<TEL> objContactList = objParticipant.getAssociatedEntity().getTelecom();
					for(TEL Telecom:objContactList)
					{
						Contact objContact=new Contact();
						if(Telecom.getValue()!=null){
							String[] strtelecomType=Telecom.getValue().split(":");
							if(strtelecomType[0].equals("tel"))
								objContact.setContactNumber(strtelecomType[1]);
							if(strtelecomType[0].equals("mailto"))
								objContact.setEmailAddress(strtelecomType[1]);
							if(strtelecomType[0].equals("http"))
								objContact.setUrl(strtelecomType[1]);
						}

						if(Telecom.getUse()!=null)
							objContact.getUse().addAll(Telecom.getUse());
						if(Telecom.getUseablePeriod()!=null)
						{
							for(SXCMTS objusablePeriod:Telecom.getUseablePeriod())
							{
								objContact.getUseablePeriod().add(objusablePeriod.getValue());
							}
						}
						objPar.getParticipantContact().add(objContact);
					}
					
					//Extracting participant address
					List<AD> objListAddr =objParticipant.getAssociatedEntity().getAddr();
					for(AD objAddr : objListAddr)
					{
						Address adr = new Address();
						adr=getAddress(objAddr);
						objPar.getParticipantAddress().add(adr);

					}
					if(objPar!=null);
					objPatientDetails.getParticipant().add(objPar);

				}
			}
			objListPatient.add(objPatientDetails);

		}
		if(objListPatient.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.HEADER.getValue()));
		}
		return objListPatient;
	}
	private Address getAddress(AD objAddr)
	{
		 Address adr =new Address();
		for(int i=0;i<objAddr.getContent().size();i++)
		{	if(objAddr.getContent().get(i) instanceof JAXBElement)
		{
			Object objJAXB= ((JAXBElement<?>)objAddr.getContent().get(i)).getValue();
			if(objJAXB instanceof AdxpCity && ((AdxpCity) objJAXB).getContent()!=null )
				adr.setCity(((AdxpCity) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpStreetName && ((AdxpStreetName) objJAXB).getContent()!=null )
				adr.setStreetName(((AdxpStreetName) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpCounty && ((AdxpCounty) objJAXB).getContent()!=null )
				adr.setCounty(((AdxpCounty) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpUnitID && ((AdxpUnitID) objJAXB).getContent()!=null )
				adr.setUnitID(((AdxpUnitID) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpDeliveryModeIdentifier && ((AdxpDeliveryModeIdentifier) objJAXB).getContent()!=null )
				adr.setDeliveryModeIdentifier(((AdxpDeliveryModeIdentifier) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpAdditionalLocator  && ((AdxpAdditionalLocator) objJAXB).getContent()!=null )
				adr.setAdditionalLocator(((AdxpAdditionalLocator) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpBuildingNumberSuffix && ((AdxpBuildingNumberSuffix) objJAXB).getContent()!=null )
				adr.setBuildingNumberSuffix(((AdxpBuildingNumberSuffix) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpUnitType && ((AdxpUnitType) objJAXB).getContent()!=null )
				adr.setUnitType(((AdxpUnitType) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpState && ((AdxpState) objJAXB).getContent()!=null )
				adr.setState(((AdxpState) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpStreetNameBase && ((AdxpStreetNameBase) objJAXB).getContent()!=null )
				adr.setStreetNameBase(((AdxpStreetNameBase) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpStreetNameType && ((AdxpStreetNameType) objJAXB).getContent()!=null )
				adr.setStreetNameType(((AdxpStreetNameType) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpHouseNumber && ((AdxpHouseNumber) objJAXB).getContent()!=null )
				adr.setHouseNumber(((AdxpHouseNumber) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpStreetAddressLine && ((AdxpStreetAddressLine) objJAXB).getContent()!=null )
				adr.setStreetAddressLine(((AdxpStreetAddressLine) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpCareOf && ((AdxpCareOf) objJAXB).getContent()!=null )
				adr.setCareOf(((AdxpCareOf) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpDelimiter && ((AdxpDelimiter) objJAXB).getContent()!=null )
				adr.setDelimiter(((AdxpDelimiter) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpPostalCode && ((AdxpPostalCode) objJAXB).getContent()!=null )
				adr.setPostalCode(((AdxpPostalCode) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpDirection && ((AdxpDirection) objJAXB).getContent()!=null )
				adr.setDirection(((AdxpDirection) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpDeliveryInstallationType && ((AdxpDeliveryInstallationType) objJAXB).getContent()!=null )
				adr.setDeliveryInstallationType(((AdxpDeliveryInstallationType) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpCensusTract && ((AdxpCensusTract) objJAXB).getContent()!=null )
				adr.setCensusTract(((AdxpCensusTract) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpDeliveryInstallationQualifier  && ((AdxpDeliveryInstallationQualifier) objJAXB).getContent()!=null )
				adr.setDeliveryInstallationQualifier(((AdxpDeliveryInstallationQualifier) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpPrecinct && ((AdxpPrecinct) objJAXB).getContent()!=null )
				adr.setPrecinct(((AdxpPrecinct) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpHouseNumberNumeric && ((AdxpHouseNumberNumeric) objJAXB).getContent()!=null )
				adr.setHouseNumberNumeric(((AdxpHouseNumberNumeric) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpDeliveryMode && ((AdxpDeliveryMode) objJAXB).getContent()!=null )
				adr.setDeliveryMode(((AdxpDeliveryMode) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpDeliveryAddressLine && ((AdxpDeliveryAddressLine) objJAXB).getContent()!=null )
				adr.setDeliveryAddressLine(((AdxpDeliveryAddressLine) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpDeliveryInstallationArea && ((AdxpDeliveryInstallationArea) objJAXB).getContent()!=null )
				adr.setDeliveryInstallationArea(((AdxpDeliveryInstallationArea) objJAXB).getContent().toString());
			if(objJAXB instanceof AdxpPostBox && ((AdxpPostBox) objJAXB).getContent()!=null )
				adr.setPostBox(((AdxpPostBox) objJAXB).getContent().toString());
		}
		}
		return adr;
	}
	/**
	 * This method return Summary Purpose of Document
	 * @return objSummary- List of SummaryPurpose
	 */
	public List<SummaryPurpose> getSummaryPurpose()
	{
		List<SummaryPurpose> objSummary = new ArrayList<SummaryPurpose>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();
		for (Component3 component3 : objListComp) {
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.PURPOSE_SECTION_TEMPLATES))
			{
				//extracting section form component
				Section objSection = component3.getSection();
				SummaryPurpose purpose = new SummaryPurpose();
				StrucDocText docText = objSection.getText();
				List<Serializable> purposeList = docText.getContent();
				for(int i=0;i<purposeList.size();i++)
					purpose.setPurpose(purposeList.get(i).toString());
				objSummary.add(purpose);
			}
			if(objSummary.isEmpty()){
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.SUMMARY_PURPOSE.getValue()));
			}
		}
		return objSummary;
	}
	/**
	 * This method return Organization Name
	 * @return object of Organization Name
	 */
	public OrganizationInfo getOrganizationName() {

		//Extracting Record Target containing patient information
		List<Informant12> objListInfor= objClinicalDoc.getInformant();
		List<Serializable> orgName=null;
		if(objListInfor.size()>0 && objListInfor.get(0).getAssignedEntity()!=null && objListInfor.get(0).getAssignedEntity().getRepresentedOrganization()!=null && objListInfor.get(0).getAssignedEntity().getRepresentedOrganization().getName().size()>0){
			orgName=objListInfor.get(0).getAssignedEntity().getRepresentedOrganization().getName().get(0).getContent();
		}
		OrganizationInfo objOrg = new OrganizationInfo();
		if(orgName!=null && orgName.size()>0){
			objOrg.setOrganizationName(orgName.get(0).toString());
		}else
			objOrg.setOrganizationName(null);
		return objOrg;
	}
	
	/**
	 * This method return list of authors of CCD.
	 * @return object of objListAuthor
	 */
	public List<AuthorInfo> getAuthorInfo()
	{
		//Retrieve and Set Author of CCD 
				List<AuthorInfo> objListAuthor=new ArrayList<AuthorInfo>();
				List<Author> objAuthorL=objClinicalDoc.getAuthor();
				if(objAuthorL!=null)
					if(!objAuthorL.isEmpty()){ 
					for(Author objAuthor : objAuthorL)
					{
						AuthorInfo objAuthorInfo=new AuthorInfo();
						
						if(objAuthor.getFunctionCode()!=null)
						{
							String functionCode= objAuthor.getFunctionCode().getCode();
							objAuthorInfo.setFunctionCode(functionCode);
						}
						if(objAuthor.getAssignedAuthor().getId()!=null)
						{
							for(II objId:objAuthor.getAssignedAuthor().getId())
							{
								Identifiers objIdentifier=new Identifiers();
								if(objId.getRoot()!=null)
									objIdentifier.setRootIdentifier(objId.getRoot());
								if(objId.getExtension()!=null)
									objIdentifier.setExtension(objId.getExtension());
								if(objId.getAssigningAuthorityName()!=null)
									objIdentifier.setAssigningAuthorityName(objId.getAssigningAuthorityName());
								
								objAuthorInfo.getId().add(objIdentifier);
							}
								
						}
						
						if(objAuthor.getAssignedAuthor().getAssignedPerson()!=null)
						{
							if(objAuthor.getAssignedAuthor().getAssignedPerson().getName()!=null && !objAuthor.getAssignedAuthor().getAssignedPerson().getName().isEmpty())
							{
								for(int i=0;i<objAuthor.getAssignedAuthor().getAssignedPerson().getName().get(0).getContent().size();i++) 
								{
									//Populating patient Name 
									if(objAuthor.getAssignedAuthor().getAssignedPerson().getName().get(0).getContent().get(i) instanceof JAXBElement)
									{
										Object objJAXB= ((JAXBElement<?>)objAuthor.getAssignedAuthor().getAssignedPerson().getName().get(0).getContent().get(i)).getValue();
										if(objJAXB instanceof EnGiven && ((EnGiven) objJAXB).getContent()!=null )
											objAuthorInfo.setName(((EnGiven) objJAXB).getContent().toString());
										if(objJAXB instanceof EnFamily && ((EnFamily) objJAXB).getContent()!=null )
											objAuthorInfo.setFamily(((EnFamily) objJAXB).getContent().toString());
										if(objJAXB instanceof EnPrefix && ((EnPrefix) objJAXB).getContent()!=null )
											objAuthorInfo.setPrefix(((EnPrefix) objJAXB).getContent().toString());
										if(objJAXB instanceof EnSuffix && ((EnSuffix) objJAXB).getContent()!= null )
											objAuthorInfo.setSuffix(((EnSuffix) objJAXB).getContent().toString());
									}
								}
							}
							
						}
						
						if(objAuthor.getTime()!=null)
						{
							String time=objAuthor.getTime().getValue();
							objAuthorInfo.setTime(time);
						}
						
						if(objAuthor.getAssignedAuthor().getRepresentedOrganization()!=null)
						{
							Organization objOrganization=objAuthor.getAssignedAuthor().getRepresentedOrganization();
							
							OrganizationInfo objOrganizationInfo=new OrganizationInfo();
							objOrganizationInfo=getOrgnization(objOrganization);
							objAuthorInfo.setObjOrganization(objOrganizationInfo);
							
						}
						
						if(objAuthor.getAssignedAuthor().getAssignedAuthoringDevice()!=null)
						{
							AuthoringDeviceInfo objAuthoringDevice=new AuthoringDeviceInfo();
							if(objAuthor.getAssignedAuthor().getAssignedAuthoringDevice().getSoftwareName()!=null && objAuthor.getAssignedAuthor().getAssignedAuthoringDevice().getSoftwareName().getDisplayName()!=null)
								objAuthoringDevice.setSoftwareName(objAuthor.getAssignedAuthor().getAssignedAuthoringDevice().getSoftwareName().getDisplayName());
							if(objAuthor.getAssignedAuthor().getAssignedAuthoringDevice().getManufacturerModelName()!=null && objAuthor.getAssignedAuthor().getAssignedAuthoringDevice().getManufacturerModelName().getDisplayName()!=null)
								objAuthoringDevice.setManufacturerModelName(objAuthor.getAssignedAuthor().getAssignedAuthoringDevice().getManufacturerModelName().getDisplayName());
							
							objAuthorInfo.setAuthoringDevice(objAuthoringDevice);
						}
						
						objListAuthor.add(objAuthorInfo);
					}
					
				}	
		return objListAuthor;
	}
	/**
	 * This method returns the list documentation of in CCD.
	 * @return object of objListDocumentationOfInfo
	 */
	public List<DocumentationOfInfo> getDocumentationOfInfo()
	{
		//Retrieve and Set Documentation of CCD
				List<DocumentationOfInfo> objListDocumentationOfInfo= new ArrayList<DocumentationOfInfo>();
				List<DocumentationOf> objDocumentationOfL=objClinicalDoc.getDocumentationOf();
				if(objDocumentationOfL!=null )
					if(!objDocumentationOfL.isEmpty()){
					for(DocumentationOf objDocumentationOf:objDocumentationOfL)
					{
						DocumentationOfInfo objDocumentationOfInfo=new DocumentationOfInfo();
						
						ServiceEvent objServiceEvent=objDocumentationOf.getServiceEvent();
						
						if(objServiceEvent!=null)
						{
							//Retrieve and Set Effective Time for Service Event
							if(objServiceEvent.getEffectiveTime()!=null)
							{
								EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
								objEffectiveDate=getEffectiveTime(objServiceEvent.getEffectiveTime());
								objDocumentationOfInfo.setEffectiveDateofDocument(objEffectiveDate);
																
							}else{
									EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
									objDocumentationOfInfo.setEffectiveDateofDocument(objEffectiveDate);
							}
							//Retrieve and Set Performer of Service Event
							if(objServiceEvent.getPerformer()!=null)
							{
								for(Performer1 objPerformerT: objServiceEvent.getPerformer())
								{
									Performer objPerformer=new Performer();
									
									if(objPerformerT.getFunctionCode()!=null)
									{
										objPerformer.setFunctionCode(objPerformerT.getFunctionCode().getCode());
									}
									if(objPerformerT.getTime()!=null)
									{
										EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
										objEffectiveDate=getEffectiveTime(objPerformerT.getTime());
										objPerformer.setTime(objEffectiveDate);
																		
									}else{
											EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
											objPerformer.setTime(objEffectiveDate);
									}
									
									if(objPerformerT.getAssignedEntity()!=null)
									{
										if(objPerformerT.getAssignedEntity().getId()!=null && !objPerformerT.getAssignedEntity().getId().isEmpty())
										{
											for(II objId: objPerformerT.getAssignedEntity().getId())
											{
												Identifiers objIdentifier=new Identifiers();
												if(objId.getRoot()!=null)
													objIdentifier.setRootIdentifier(objId.getRoot());
												if(objId.getExtension()!=null)
													objIdentifier.setExtension(objId.getExtension());
												if(objId.getAssigningAuthorityName()!=null)
													objIdentifier.setAssigningAuthorityName(objId.getAssigningAuthorityName());
												
												objPerformer.getId().add(objIdentifier);
											}
										}
										if(objPerformerT.getAssignedEntity().getAssignedPerson()!=null)
										{
											if(objPerformerT.getAssignedEntity().getAssignedPerson().getName()!=null && !objPerformerT.getAssignedEntity().getAssignedPerson().getName().isEmpty())
											{
												for(int i=0;i<objPerformerT.getAssignedEntity().getAssignedPerson().getName().get(0).getContent().size();i++) 
												{
													//Populating Performer Name 
													if(objPerformerT.getAssignedEntity().getAssignedPerson().getName().get(0).getContent().get(i) instanceof JAXBElement)
													{
														Object objJAXB= ((JAXBElement<?>)objPerformerT.getAssignedEntity().getAssignedPerson().getName().get(0).getContent().get(i)).getValue();
														if(objJAXB instanceof EnGiven && ((EnGiven) objJAXB).getContent()!=null )
															objPerformer.setName(((EnGiven) objJAXB).getContent().toString());
														if(objJAXB instanceof EnFamily && ((EnFamily) objJAXB).getContent()!=null )
															objPerformer.setFamily(((EnFamily) objJAXB).getContent().toString());
														if(objJAXB instanceof EnPrefix && ((EnPrefix) objJAXB).getContent()!=null )
															objPerformer.setPrefix(((EnPrefix) objJAXB).getContent().toString());
														if(objJAXB instanceof EnSuffix && ((EnSuffix) objJAXB).getContent()!= null )
															objPerformer.setSuffix(((EnSuffix) objJAXB).getContent().toString());
													}
												}
											}
											
										}
										
										if(objPerformerT.getAssignedEntity().getRepresentedOrganization()!=null)
										{
											Organization objOrganization=objPerformerT.getAssignedEntity().getRepresentedOrganization();
											
											OrganizationInfo objOrganizationInfo=new OrganizationInfo();
											objOrganizationInfo=getOrgnization(objOrganization);
											objPerformer.setObjOrganization(objOrganizationInfo);
										}
									}
									objDocumentationOfInfo.getPerformer().add(objPerformer);
								}
							}
						}
						objListDocumentationOfInfo.add(objDocumentationOfInfo);
					}
				}
		return objListDocumentationOfInfo;		
	}
	
	/**
	 * This method return Header of CCD.
	 * @return object of Organization Name
	 */
	public CCDHeader getCCDHeader()
	{
		CCDHeader objCCDHeader=new CCDHeader();
		
		//Get Patient Details
		List<PatientDetails> objListPatientDetails=new ArrayList<PatientDetails>();
		objListPatientDetails=getPatientDetails();
		if(objListPatientDetails!=null)
			objCCDHeader.getPatientDetails().addAll(objListPatientDetails);
		
		//Retrieve and Set Author of CCD 
		List<AuthorInfo> objListAuthor=new ArrayList<AuthorInfo>();
		objListAuthor=getAuthorInfo();
		if(objListAuthor!=null)
			objCCDHeader.getAuthor().addAll(objListAuthor);	
		
		
		//Retrieve and Set Documentation of CCD
		List<DocumentationOfInfo> objListDocumentationOfInfo= new ArrayList<DocumentationOfInfo>();
		objListDocumentationOfInfo=getDocumentationOfInfo();
		if(objListDocumentationOfInfo!=null)
			objCCDHeader.getDocumentationOf().addAll(objListDocumentationOfInfo);
			
		//Retrieve and set Organization name
		objCCDHeader.setOrganization(getOrganizationName());
		
		return objCCDHeader;
	}
	
	/*
	 * Used to retrieve effective date and time
	 */
	private EffectiveDateTime getEffectiveTime(IVLTS objIVLTSeffectiveDate)
	{
		EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
		if(objIVLTSeffectiveDate.getRest().size() !=0)
		{

			for(int i=0;i<objIVLTSeffectiveDate.getRest().size();i++)
				{
					if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("low"))
					{
						objEffectiveDate.setLow( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
					}
					if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("high"))
					{
						objEffectiveDate.setHigh( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
					}
					if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("center"))
					{
						objEffectiveDate.setCenter( ((TS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
					}
					if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("width"))
					{
						objEffectiveDate.setWidth( ((PQ) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
					}

				}
			}
			if(objIVLTSeffectiveDate.getValue()!=null)
			{
				objEffectiveDate.setValue(objIVLTSeffectiveDate.getValue());
			}
			
		return objEffectiveDate;
	}
	
	/*
	 * Used to retrieve organization part of
	 */
	private OrganizationPartOfInfo getOrganizationPartOf(OrganizationPartOf asOrganizationPartOf)
	{
		OrganizationPartOfInfo asOrganizationPartOfInfo = new OrganizationPartOfInfo();
		if(asOrganizationPartOf.getEffectiveTime()!=null)
		{
			EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
			IVLTS objIVLTSeffectiveDate=asOrganizationPartOf.getEffectiveTime();
			if(objIVLTSeffectiveDate.getRest().size() !=0)
			{

				for(int i=0;i<objIVLTSeffectiveDate.getRest().size();i++)
					{
						if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("low"))
						{
							objEffectiveDate.setLow( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
						}
						if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("high"))
						{
							objEffectiveDate.setHigh( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
						}
						if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("center"))
						{
							objEffectiveDate.setCenter( ((TS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
						}
						if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("width"))
						{
							objEffectiveDate.setWidth( ((PQ) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
						}

					}
				}
				if(asOrganizationPartOf.getEffectiveTime().getValue()!=null)
				{
					objEffectiveDate.setValue(asOrganizationPartOf.getEffectiveTime().getValue());
				}
				asOrganizationPartOfInfo.setEffectiveTime(objEffectiveDate);
											
			}else{
				EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
				asOrganizationPartOfInfo.setEffectiveTime(objEffectiveDate);
			}
		 
			if(asOrganizationPartOf.getRealmCode()!=null || !asOrganizationPartOf.getRealmCode().isEmpty())
			{
				for(CS objrealmCode:asOrganizationPartOf.getRealmCode())
				{
					String realmCode = objrealmCode.getCode();
					asOrganizationPartOfInfo.getRealmCode().add(realmCode);
				}
			}
			
			if(asOrganizationPartOf.getId()!=null || !asOrganizationPartOf.getId().isEmpty())
			{
				for(II objId:asOrganizationPartOf.getId())
				{
					Identifiers objIdentifier=new Identifiers();
					if(objId.getRoot()!=null)
						objIdentifier.setRootIdentifier(objId.getRoot());
					if(objId.getExtension()!=null)
						objIdentifier.setExtension(objId.getExtension());
					if(objId.getAssigningAuthorityName()!=null)
						objIdentifier.setAssigningAuthorityName(objId.getAssigningAuthorityName());
					
					asOrganizationPartOfInfo.getId().add(objIdentifier);
				}
			}
		
			if(asOrganizationPartOf.getWholeOrganization()!=null)
			{
				OrganizationInfo objorganization=getOrgnization(asOrganizationPartOf.getWholeOrganization());
				asOrganizationPartOfInfo.setWholeOrganization(objorganization);
			}
						
		return asOrganizationPartOfInfo;
	}
	
	/*
	 * Used to retrieve Organization
	 */
	private OrganizationInfo getOrgnization(Organization objOrganization)
	{
		OrganizationInfo objOrganizationInfo=new OrganizationInfo();
		if(objOrganization.getId()!=null)
		{
			for(II objId:objOrganization.getId())
			{
				Identifiers objIdentifier=new Identifiers();
				if(objId.getRoot()!=null)
					objIdentifier.setRootIdentifier(objId.getRoot());
				if(objId.getExtension()!=null)
					objIdentifier.setExtension(objId.getExtension());
				if(objId.getAssigningAuthorityName()!=null)
					objIdentifier.setAssigningAuthorityName(objId.getAssigningAuthorityName());
				
				objOrganizationInfo.getId().add(objIdentifier);
			}
		}
		if((objOrganization.getName()!=null || !objOrganization.getName().isEmpty())
				&& (objOrganization.getName().get(0).getContent()!=null && !objOrganization.getName().get(0).getContent().isEmpty())){
			objOrganizationInfo.setOrganizationName(objOrganization.getName().get(0).getContent().get(0).toString());
		}
		
		//Retrieve and Set Address of Organization
		if(objOrganization.getAddr()!=null)
		{
			for(AD objAddr:objOrganization.getAddr())
			{
				Address adr = new Address();
				adr=getAddress(objAddr);
				objOrganizationInfo.getAddr().add(adr);
			}
		}
		//Retrieve and Set Telecom of Organization
		if(objOrganization.getTelecom()!=null || !objOrganization.getTelecom().isEmpty())
		{
			for(TEL Telecom:objOrganization.getTelecom())
			{
				Contact objContact=new Contact();
				if(Telecom.getValue()!=null)
				{
					String[] strtelecomType=Telecom.getValue().split(":");
					if(strtelecomType[0].equals("tel"))
						objContact.setContactNumber(strtelecomType[1]);
					if(strtelecomType[0].equals("mailto"))
						objContact.setEmailAddress(strtelecomType[1]);
					if(strtelecomType[0].equals("http"))
						objContact.setUrl(strtelecomType[1]);
				}
				
				if(Telecom.getUse()!=null)
					objContact.getUse().addAll(Telecom.getUse());
				if(Telecom.getUseablePeriod()!=null)
				{
					for(SXCMTS objusablePeriod:Telecom.getUseablePeriod())
					{
						objContact.getUseablePeriod().add(objusablePeriod.getValue());
					}
				}
				objOrganizationInfo.getTelecom().add(objContact);
			}
		}
		
		if(objOrganization.getStandardIndustryClassCode()!=null)
		{
			String standardIndustryClassCode=objOrganization.getStandardIndustryClassCode().getCode();
			objOrganizationInfo.setStandardIndustryClassCode(standardIndustryClassCode);
		}
		
		if(objOrganization.getAsOrganizationPartOf()!=null)
		{
			OrganizationPartOf objOrganizationPartOf=objOrganization.getAsOrganizationPartOf();
			OrganizationPartOfInfo objOrganizationPartOfInfo=new OrganizationPartOfInfo();
			objOrganizationPartOfInfo=getOrganizationPartOf(objOrganizationPartOf);
			objOrganizationInfo.setAsOrganizationPartOfInfo(objOrganizationPartOfInfo);
		}
		
		
		return objOrganizationInfo;
	}
	/**
	 * This method return List of Payers object
	 * @return objListPayer - List of Payers
	 */
	public List<Payer> getPayers()
	{
		List<Payer> objListPayer = new ArrayList<Payer>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();
		for (Component3 component3 : objListComp) {
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.PAYERS_SECTION_TEMPLATES))
			{
				//extracting section from component
				Section objSection = component3.getSection();

				List<cdac.medinfo.ccd.templates.Entry> objEntryList = objSection.getEntry();
				for(cdac.medinfo.ccd.templates.Entry objEntry :objEntryList)
				{
					Payer objPayer = new Payer();
					if(objEntry.getAct()!=null)
					{
						if(objEntry.getAct().getEntryRelationship()!=null&&objEntry.getAct().getEntryRelationship().size()>0)
						{	//setting Payer Name
							if(objEntry.getAct().getEntryRelationship().get(0).getAct()!=null && objEntry.getAct().getEntryRelationship().get(0).getAct().getPerformer()!=null)
							{
								List<Serializable> orgName=objEntry.getAct().getEntryRelationship().get(0).getAct().getPerformer().get(0).getAssignedEntity().getRepresentedOrganization().getName().get(0).getContent();
								objPayer.setPayerName(orgName.get(0).toString());
								//setting Policy Type 
								if(objEntry.getAct().getEntryRelationship().get(0).getAct().getCode() != null)
									objPayer.setPolicyType(objEntry.getAct().getEntryRelationship().get(0).getAct().getCode().getDisplayName());
							}
							//setting  Authorization
							objPayer.setAuthorization(objEntry.getAct().getEntryRelationship().get(0).getAct().getEntryRelationship().get(0).getAct().
									getEntryRelationship().get(0).getProcedure().getCode().getDisplayName());
							List<II> ii =objEntry.getAct().getEntryRelationship().get(0).getAct().getParticipant().get(0).getParticipantRole().getId();
							objPayer.setCoveredPartyID(ii.get(0).getRoot());
							objPayer.setCoverageType(objEntry.getAct().getEntryRelationship().get(0).getAct().getParticipant().get(0).getParticipantRole().getCode().getDisplayName());
							objListPayer.add(objPayer);
						}
					}
				}
			}
		}
		if(objListPayer.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.PAYERS.getValue()));
		}
		return objListPayer;
	}
	/**
	 * This method return List of Vital Signs object
	 * @return objListVitals- List of Vital Signs
	 */
	public List<VitalSigns> getVitalSigns()
	{
		List<VitalSigns> objListVitals = new ArrayList<VitalSigns>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();
		for (Component3 component3 : objListComp) {
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.VITAL_SIGNS_SECTION_TEMPLATE))
			{
				//extracting section form component
				Section objSection = component3.getSection();
				//extracting entry list for section object 
				List<cdac.medinfo.ccd.templates.Entry> objEntryList = objSection.getEntry();
				for(cdac.medinfo.ccd.templates.Entry objEntry :objEntryList)
				{	
					VitalSigns objSys = new VitalSigns();
					VitalSigns objDias = new VitalSigns();

					if(objEntry.getOrganizer()!= null){
						List<Component4> objCompList = objEntry.getOrganizer().getComponent();
						for(Component4 objComp: objCompList)	{
							VitalSigns objVSModel = new VitalSigns();
							if(objComp.getObservation()!=null){
								if(objComp.getObservation().getCode()!=null){		
									if(objComp.getObservation().getCode().getDisplayName().equalsIgnoreCase("Systolic BP")){
										if(objComp.getObservation().getEffectiveTime()!=null)
											objSys.setDate(objComp.getObservation().getEffectiveTime().getValue());
										List<ANY> objAnyList =objComp.getObservation().getValue() ;
										for(ANY objAny : objAnyList ){
											objSys.setValue(((PQ)objAny).getValue());
											objSys.setUnit(((PQ)objAny).getUnit());
										}
									}
									else if(objComp.getObservation().getCode().getDisplayName().equalsIgnoreCase("Diastolic BP")){
										if(objComp.getObservation().getEffectiveTime()!=null)
											objDias.setDate(objComp.getObservation().getEffectiveTime().getValue());
										List<ANY> objAnyList =objComp.getObservation().getValue() ;
										for(ANY objAny : objAnyList ){
											objDias.setValue(((PQ)objAny).getValue());
											objDias.setUnit(((PQ)objAny).getUnit());
										}
									}
									else{
										objVSModel.setVitalSigns(objComp.getObservation().getCode().getDisplayName());
										if(objComp.getObservation().getEffectiveTime()!=null)
											objVSModel.setDate(objComp.getObservation().getEffectiveTime().getValue());
										List<ANY> objAnyList =objComp.getObservation().getValue() ;
										for(ANY objAny : objAnyList ){
											objVSModel.setValue(((PQ)objAny).getValue());
											objVSModel.setUnit(((PQ)objAny).getUnit());
										}
										objListVitals.add(objVSModel);
									}
								}
							}
						}
					}
					if(objSys!=null&&objDias!=null)
					{
						VitalSigns objVSModel = new VitalSigns();
						objVSModel.setVitalSigns("Blood Pressure");
						objVSModel.setDate(objSys.getDate());
						objVSModel.setValue(objSys.getValue()+"/"+objDias.getValue());
						objVSModel.setUnit(objSys.getUnit());
						objListVitals.add(objVSModel);
					}else{
						if(objSys!=null &&objDias==null )
							objListVitals.add(objSys);
						else
							objListVitals.add(objDias);
					}
				}
			}
		}
		if(objListVitals.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.VITAL_SIGNS.getValue()));
		}
		return objListVitals;
	}

	/**
	 * This method return List of Medical Equipment object
	 * @return objListMedicalEquipment- List of MedicalEquipment
	 */
	public List<MedicalEquipment> getMedicalEquipment()
	{
		List<MedicalEquipment> objListMedicalEquipment = new ArrayList<MedicalEquipment>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();

		for (Component3 component3 : objListComp) {
			//  System.out.println("Executing for "+ component3.getSection().getTitle().getContent()); 

			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.MEDICAL_EQUIPMENT_SECTION_TEMPLATE))
			{
				//extracting section form component
				Section objSection = component3.getSection();
				if(objSection.getEntry()!= null)
				{
					for(Entry objEntry:objSection.getEntry())
					{
						MedicalEquipment objMedicalEquipment= new MedicalEquipment();
						Supply objSupply=objEntry.getSupply();

						//Extract and Set Supply Device Name
						if(objSupply.getParticipant() !=null)
						{
							for(Participant2 objParticipant: objSupply.getParticipant())
							{
								objMedicalEquipment.setSupplyDevice(objParticipant.getParticipantRole().getPlayingDevice().getCode().getDisplayName());
							}
						}
						//Extract and Set Date Supplied
						if(objSupply.getEffectiveTime()!=null)
						{						
							for(int j=0;j<objSupply.getEffectiveTime().size();j++)
							{
								if(objSupply.getEffectiveTime().get(j).getValue() !=null)
								{
									objMedicalEquipment.setDateSupplied(objSupply.getEffectiveTime().get(j).getValue());	
								}
								else
								{
									objMedicalEquipment.setDateSupplied(((TS) ((IVLTS) objSupply.getEffectiveTime().get(j)).getRest().get(0).getValue()).getValue()); //Effective time cardinality 0..1
								}							
							}
						}
						objListMedicalEquipment.add(objMedicalEquipment);
					}
				}
			}
		}
		if(objListMedicalEquipment.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.MEDICAL_EQUIPMENT.getValue()));
		}
		return objListMedicalEquipment;
	}

	/**
	 * This method return List of Immunizations object
	 * @return objListImmunizations- List of Immunizations
	 */
	public List<Immunizations> getImmunizations()
	{
		List<Immunizations> objListImmunizations = new ArrayList<Immunizations>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();

		for (Component3 component3 : objListComp) 
		{
			//  System.out.println("Executing for "+ component3.getSection().getTitle().getContent());  
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.IMMUNIZATION_SECTION_TEMPLATES))
			{
				//extracting section form component
				Section objSection = component3.getSection();

				if(objSection.getEntry()!= null)
				{
					for(Entry objEntry:objSection.getEntry())
					{
						Immunizations objImmunizations= new Immunizations();

						//Extract and Set Vaccine
						if(objEntry.getSubstanceAdministration().getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getOriginalText()!=null){
							objImmunizations.setVaccine(objEntry.getSubstanceAdministration().getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getOriginalText().getContent());
						}

						//Extract and Set Effective Time

						if(objEntry.getSubstanceAdministration().getEffectiveTime()!=null)
						{
							for(int j=0; j<objEntry.getSubstanceAdministration().getEffectiveTime().size();j++)
							{
								EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
								IVLTS objIVLTSeffectiveDate=(IVLTS) objEntry.getSubstanceAdministration().getEffectiveTime().get(j);
								if(objIVLTSeffectiveDate.getRest().size() !=0)
								{

									for(int i=0;i<objIVLTSeffectiveDate.getRest().size();i++)
									{
										if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("low"))
										{
											objEffectiveDate.setLow( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
										}
										if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("high"))
										{
											objEffectiveDate.setHigh( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
										}
										if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("center"))
										{
											objEffectiveDate.setCenter( ((TS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
										}
										if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("width"))
										{
											objEffectiveDate.setWidth( ((PQ) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
										}

									}
								}
								if(objEntry.getSubstanceAdministration().getEffectiveTime().get(j).getValue()!=null)
								{
									objEffectiveDate.setValue(objEntry.getSubstanceAdministration().getEffectiveTime().get(j).getValue());
								}
								objImmunizations.setDate(objEffectiveDate);
							}							
						}else{
							EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
							objImmunizations.setDate(objEffectiveDate);
						}

						//Extract and Set Mood of Vaccine
						objImmunizations.setMoodOfVaccine(objEntry.getSubstanceAdministration().getRouteCode().getDisplayName());

						//Extract and Set Status
						objImmunizations.setStatus(objEntry.getSubstanceAdministration().getStatusCode().getCode());

						objListImmunizations.add(objImmunizations);
					}

				}
			}
		}
		if(objListImmunizations.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.IMMUNIZATION.getValue()));
		}
		return objListImmunizations;
	}

	/**
	 * This method return List of Advance Directives object
	 * @return objListAdvanceDirectives- List of AdvanceDirectives
	 */
	public List<AdvanceDirectives> getAdvanceDirectives()
	{
		List<AdvanceDirectives> objListAdvanceDirectives = new ArrayList<AdvanceDirectives>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();

		LinkedHashMap<String, String> dataMap = new LinkedHashMap<>();
		StrucDocTr tr = null;
		for (Component3 component3 : objListComp)
		{
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.ADVANCE_DIRECTIVES_TEMPLATE))
			{
				//extracting section form component
				Section objSection = component3.getSection();

				@SuppressWarnings({ "unchecked", "rawtypes" })
				JAXBElement<StrucDocTable> objJAX = (JAXBElement)objSection.getText().getContent().get(1);

				//fetching table header
				List<StrucDocTr> headerRow = ((StrucDocTable)objJAX.getValue()).getThead().getTr();
				StrucDocTr docHeader =   headerRow.get(0);
				List<Object> objHeader  = docHeader.getThOrTd();

				//Adding table header in data map
				for ( Object object : objHeader) {
					dataMap.put(((StrucDocTh)object).getContent().get(0).toString(),null);
				}

				if(objSection.getEntry() !=null)
				{
					for(int i=0; i<objSection.getEntry().size();i++)
					{
						AdvanceDirectives objAdvanceDirectives= new AdvanceDirectives();

						Observation objObservation=objSection.getEntry().get(i).getObservation();
						//Extract and Set Directive
						objAdvanceDirectives.setDirective(objObservation.getCode().getDisplayName());

						//Extract and Set Description
						if(objObservation.getValue() != null)
						{
							for(int j=0;j<objObservation.getValue().size();j++)
							{
								objAdvanceDirectives.setDescription(((CD) objObservation.getValue().get(j)).getDisplayName());
							}
						}

						//Extract and Set Verification, Verification Date
						if(objObservation.getParticipant() !=null)
						{
							for(int j=0;j<objObservation.getParticipant().size();j++)
							{
								objAdvanceDirectives.setVerificationDate(objObservation.getParticipant().get(j).getTime().getValue());
								if(objObservation.getParticipant().get(j).getParticipantRole().getPlayingEntity()!=null)
								{
									objAdvanceDirectives.setVerification(objObservation.getParticipant().get(j).getParticipantRole().getPlayingEntity().getName().get(0).getContent().get(0).toString());
								}
							}
						}

						//Extract and Set Reference
						if(objObservation.getReference()!=null)
						{
							for(int j=0;j<objObservation.getReference().size();j++)
							{
								//To get Supporting Document
								objAdvanceDirectives.setSupportingDocument(objObservation.getReference().get(j).getExternalDocument().getCode().getDisplayName());

								//To get Supporting Document Link
								objAdvanceDirectives.setSupportingDocumentLink(objObservation.getReference().get(j).getExternalDocument().getText().getReference().getValue());

								//To get Media Type
								objAdvanceDirectives.setMediaType(objObservation.getReference().get(j).getExternalDocument().getText().getMediaType());
							}
						}

						//Code to extract verification if participantRole does not having PlayingEntity populated.
						if(objAdvanceDirectives.getVerification()==null)
						{
							//Extract and Set Verification
							tr = (StrucDocTr)((StrucDocTbody)((StrucDocTable)objJAX.getValue()).getTbody().get(0)).getTr().get(i);

							if(dataMap.containsKey(EnumTableHeader.VERIFICATION.getValue()))
							{
								if(((StrucDocTd) tr.getThOrTd().get(2))!=null &&((StrucDocTd) tr.getThOrTd().get(2)).getContent().size()>0)
								{
									String[] strVerification=((StrucDocTd) tr.getThOrTd().get(2)).getContent().get(0).toString().split(", ");
									objAdvanceDirectives.setVerification(strVerification[0]);
								}

							}
						}	

						//Extract and Set Effective Date
						EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
						if(objObservation.getEffectiveTime()!=null && objObservation.getEffectiveTime().getRest().size() !=0)
						{

							for(int j=0;j<objObservation.getEffectiveTime().getRest().size();j++)
							{
								if(objObservation.getEffectiveTime().getRest().get(j).getName().getLocalPart().equals("low"))
								{
									objEffectiveDate.setLow( ((IVXBTS) objObservation.getEffectiveTime().getRest().get(j).getValue()).getValue());
								}
								if(objObservation.getEffectiveTime().getRest().get(j).getName().getLocalPart().equals("high"))
								{
									objEffectiveDate.setHigh( ((IVXBTS) objObservation.getEffectiveTime().getRest().get(j).getValue()).getValue());
								}
								if(objObservation.getEffectiveTime().getRest().get(j).getName().getLocalPart().equals("center"))
								{
									objEffectiveDate.setCenter( ((TS) objObservation.getEffectiveTime().getRest().get(j).getValue()).getValue());
								}
								if(objObservation.getEffectiveTime().getRest().get(j).getName().getLocalPart().equals("width"))
								{
									objEffectiveDate.setWidth( ((PQ) objObservation.getEffectiveTime().getRest().get(j).getValue()).getValue());
								}

							}
						}
						if(objObservation.getEffectiveTime()!=null && objObservation.getEffectiveTime().getValue()!=null)
						{
							objEffectiveDate.setValue(objObservation.getEffectiveTime().getValue());
						}
						objAdvanceDirectives.setEffectiveTimes(objEffectiveDate);
						objListAdvanceDirectives.add(objAdvanceDirectives);
					}
				}

			}
		}
		if(objListAdvanceDirectives.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.ADVANCE_DIRECTIVE.getValue()));
		}
		return objListAdvanceDirectives;
	}

	/**
	 * This method return List of Functional Status object
	 * @return objListFunctionalStatus- List of FunctionalStatus
	 */
	public List<FunctionalStatus> getFunctionalStatus()
	{
		List<FunctionalStatus> objListFunctionalStatus = new ArrayList<FunctionalStatus>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();

		for (Component3 component3 : objListComp) 
		{			
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.FUNCTIONAL_STATUS_SECTION_TEMPLATE))
			{
				//extracting section form component
				Section objSection = component3.getSection();

				if(objSection.getEntry()!= null)
				{
					for(Entry objEntry : objSection.getEntry() )
					{					
						if(objEntry.getAct().getEntryRelationship() != null)
						{
							FunctionalStatus objFunctionalStatus= new FunctionalStatus();
							for(EntryRelationship objEntryRelationship: objEntry.getAct().getEntryRelationship())
							{
								Observation objObservationSection=objEntryRelationship.getObservation();
								// Extract and Set Functional Condition
								objFunctionalStatus.setFunctionalCondition(((CD) objObservationSection.getValue().get(0)).getDisplayName());

								//Extract and Set Effective Date
								EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
								if(objObservationSection.getEffectiveTime().getRest().size() !=0)
								{

									for(int i=0;i<objObservationSection.getEffectiveTime().getRest().size();i++)
									{
										if(objObservationSection.getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("low"))
										{
											objEffectiveDate.setLow( ((IVXBTS) objObservationSection.getEffectiveTime().getRest().get(i).getValue()).getValue());
										}
										if(objObservationSection.getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("high"))
										{
											objEffectiveDate.setHigh( ((IVXBTS) objObservationSection.getEffectiveTime().getRest().get(i).getValue()).getValue());
										}
										if(objObservationSection.getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("center"))
										{
											objEffectiveDate.setCenter( ((TS) objObservationSection.getEffectiveTime().getRest().get(i).getValue()).getValue());
										}
										if(objObservationSection.getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("width"))
										{
											objEffectiveDate.setWidth( ((PQ) objObservationSection.getEffectiveTime().getRest().get(i).getValue()).getValue());
										}

									}
								}

								if(objObservationSection.getEffectiveTime().getValue()!=null)
								{
									objEffectiveDate.setValue(objObservationSection.getEffectiveTime().getValue());
								}
								objFunctionalStatus.setEffectiveDates(objEffectiveDate);

								//Extract and set Condition Status
								if(objObservationSection.getEntryRelationship() != null)
								{
									for(EntryRelationship objEntryRelation: objObservationSection.getEntryRelationship())
									{
										objFunctionalStatus.setConditionStatus(((CE) objEntryRelation.getObservation().getValue().get(0)).getDisplayName());
									}
								}

							}					
							objListFunctionalStatus.add(objFunctionalStatus);
						}

					}

				}

			}
		}
		if(objListFunctionalStatus.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.FUNCTIONAL_STATUS.getValue()));
		}
		return objListFunctionalStatus;
	}

	/**
	 * This method return List of Encounters object
	 * @return objListEncounter- List of Encounters
	 */
	public List<Encounters> getEncounters()
	{
		List<Encounters> objListEncounter = new ArrayList<Encounters>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();

		for (Component3 component3 : objListComp)
		{
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.ENCOUNTER_SECTION_TEMPLATE))
			{
				//extracting section form component
				Section objSection = component3.getSection();

				if(objSection.getEntry()!= null)
				{
					String strLocationname = null;
					for(Entry objEntry:objSection.getEntry())
					{
						Encounters objEncounters= new Encounters();

						//Extract and Set Encounter
						if(!(objEntry.getEncounter().getCode().getOriginalText().getContent()).isEmpty())
							objEncounters.setEncounter(objEntry.getEncounter().getCode().getOriginalText().getContent());

						//Extract and Set Date
						EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
						if(objEntry.getEncounter().getEffectiveTime().getRest().size() !=0)
						{

							for(int i=0;i<objEntry.getEncounter().getEffectiveTime().getRest().size();i++)
							{
								if(objEntry.getEncounter().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("low"))
								{
									objEffectiveDate.setLow( ((IVXBTS) objEntry.getEncounter().getEffectiveTime().getRest().get(i).getValue()).getValue());
								}
								if(objEntry.getEncounter().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("high"))
								{
									objEffectiveDate.setHigh( ((IVXBTS) objEntry.getEncounter().getEffectiveTime().getRest().get(i).getValue()).getValue());
								}
								if(objEntry.getEncounter().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("center"))
								{
									objEffectiveDate.setCenter( ((TS) objEntry.getEncounter().getEffectiveTime().getRest().get(i).getValue()).getValue());
								}
								if(objEntry.getEncounter().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("width"))
								{
									objEffectiveDate.setWidth( ((PQ) objEntry.getEncounter().getEffectiveTime().getRest().get(i).getValue()).getValue());
								}

							}
						}
						if(objEntry.getEncounter().getEffectiveTime().getValue()!=null)
						{
							objEffectiveDate.setValue(objEntry.getEncounter().getEffectiveTime().getValue());
						}
						objEncounters.setDate(objEffectiveDate);

						//Extract and Set Location
						if(objEntry.getEncounter().getParticipant() !=null)
						{
							for(Participant2 objParticipant: objEntry.getEncounter().getParticipant())
							{
								if(objParticipant.getParticipantRole().getPlayingEntity().getName().get(0).getContent().size()!=0)
								{
									strLocationname=objParticipant.getParticipantRole().getPlayingEntity().getName().get(0).getContent().get(0).toString();
								}

								objEncounters.getLocation().add(strLocationname);
							}

						}

						objListEncounter.add(objEncounters);
					}

				}

			}
		}
		if(objListEncounter.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.ENCOUNTERS.getValue()));
		}
		return objListEncounter;
	}

	/**
	 * This method return List of Plan object
	 * @return objListPlan- List of Plan
	 */
	public List<Plan> getPlan()
	{
		List<Plan> objListPlan = new ArrayList<Plan>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();
		for (Component3 component3 : objListComp) 
		{ 
			if (component3.getSection().getTitle().getContent().equals("Plan"))
			{
				//extracting section form component
				Section objSection = component3.getSection();

				if(objSection.getEntry()!= null)
				{
					for(Entry objEntry:objSection.getEntry())
					{
						Plan objPlan=new Plan();

						if(objEntry.getAct() !=null)
						{
							//Extract and Set Planned Activity
							objPlan.setPlannedActivity(objEntry.getAct().getCode().getDisplayName());

							//Extract and Set Planned Date
							EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
							if(objEntry.getAct().getEffectiveTime().getRest().size() !=0)
							{

								for(int i=0;i<objEntry.getAct().getEffectiveTime().getRest().size();i++)
								{
									if(objEntry.getAct().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("low"))
									{
										objEffectiveDate.setLow( ((IVXBTS) objEntry.getAct().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}
									if(objEntry.getAct().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("high"))
									{
										objEffectiveDate.setHigh( ((IVXBTS) objEntry.getAct().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}
									if(objEntry.getAct().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("center"))
									{
										objEffectiveDate.setCenter( ((TS) objEntry.getAct().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}
									if(objEntry.getAct().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("width"))
									{
										objEffectiveDate.setWidth( ((PQ) objEntry.getAct().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}

								}
							}
							if(objEntry.getAct().getEffectiveTime().getValue()!=null)
							{
								objEffectiveDate.setValue(objEntry.getAct().getEffectiveTime().getValue());
							}
							objPlan.setPlannedDate(objEffectiveDate);

							//Set Plan of Care Activity Name
							objPlan.setPlanOfCareActivityName(EnumEntryDataSection.ACT.getValue());

							//Extract and Set Activity Mood Code
							objPlan.setActivityMoodCode(objEntry.getAct().getMoodCode().toString());
						}
						if(objEntry.getEncounter() !=null)
						{
							//Extract and Set Planned Activity
							objPlan.setPlannedActivity(objEntry.getEncounter().getCode().getDisplayName());

							//Extract and Set Planned Date
							EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
							if(objEntry.getEncounter().getEffectiveTime().getRest().size() !=0)
							{

								for(int i=0;i<objEntry.getEncounter().getEffectiveTime().getRest().size();i++)
								{
									if(objEntry.getEncounter().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("low"))
									{
										objEffectiveDate.setLow( ((IVXBTS) objEntry.getEncounter().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}
									if(objEntry.getEncounter().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("high"))
									{
										objEffectiveDate.setHigh( ((IVXBTS) objEntry.getEncounter().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}
									if(objEntry.getEncounter().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("center"))
									{
										objEffectiveDate.setCenter( ((TS) objEntry.getEncounter().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}
									if(objEntry.getEncounter().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("width"))
									{
										objEffectiveDate.setWidth( ((PQ) objEntry.getEncounter().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}

								}
							}
							if(objEntry.getEncounter().getEffectiveTime().getValue()!=null)
							{
								objEffectiveDate.setValue(objEntry.getEncounter().getEffectiveTime().getValue());
							}
							objPlan.setPlannedDate(objEffectiveDate);

							//Set Plan of Care Activity Name
							objPlan.setPlanOfCareActivityName(EnumEntryDataSection.ENCOUNTER.getValue());

							//Extract and Set Activity Mood Code
							objPlan.setActivityMoodCode(objEntry.getEncounter().getMoodCode().toString());
						}
						if(objEntry.getObservation()!=null)
						{
							//Extract and Set Planned Activity
							objPlan.setPlannedActivity(objEntry.getObservation().getCode().getDisplayName());

							//Extract and Set Planned Date
							EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
							if(objEntry.getObservation().getEffectiveTime().getRest().size() !=0)
							{

								for(int i=0;i<objEntry.getObservation().getEffectiveTime().getRest().size();i++)
								{
									if(objEntry.getObservation().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("low"))
									{
										objEffectiveDate.setLow( ((IVXBTS) objEntry.getObservation().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}
									if(objEntry.getObservation().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("high"))
									{
										objEffectiveDate.setHigh( ((IVXBTS) objEntry.getObservation().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}
									if(objEntry.getObservation().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("center"))
									{
										objEffectiveDate.setCenter( ((TS) objEntry.getObservation().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}
									if(objEntry.getObservation().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("width"))
									{
										objEffectiveDate.setWidth( ((PQ) objEntry.getObservation().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}

								}
							}
							if(objEntry.getObservation().getEffectiveTime().getValue()!=null)
							{
								objEffectiveDate.setValue(objEntry.getObservation().getEffectiveTime().getValue());
							}
							objPlan.setPlannedDate(objEffectiveDate);

							//Set Plan of Care Activity Name
							objPlan.setPlanOfCareActivityName(EnumEntryDataSection.OBSERVATION.getValue());

							//Extract and Set Activity Mood Code
							if(objEntry.getObservation().getMoodCode()!=null){
								objPlan.setActivityMoodCode(objEntry.getObservation().getMoodCode().toString());
							}
						}
						if(objEntry.getSubstanceAdministration()!=null)
						{
							//Extract and Set Planned Activity
							objPlan.setPlannedActivity(objEntry.getObservation().getCode().getDisplayName());

							//Extract and Set Planned Date
							if(objEntry.getSubstanceAdministration().getEffectiveTime()!=null)
							{
								for(int j=0; j<objEntry.getSubstanceAdministration().getEffectiveTime().size();j++)
								{
									EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
									IVLTS objIVLTSeffectiveDate=(IVLTS) objEntry.getSubstanceAdministration().getEffectiveTime().get(j);
									if(objIVLTSeffectiveDate.getRest().size() !=0)
									{

										for(int i=0;i<objIVLTSeffectiveDate.getRest().size();i++)
										{
											if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("low"))
											{
												objEffectiveDate.setLow( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
											}
											if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("high"))
											{
												objEffectiveDate.setHigh( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
											}
											if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("center"))
											{
												objEffectiveDate.setCenter( ((TS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
											}
											if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("width"))
											{
												objEffectiveDate.setWidth( ((PQ) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
											}

										}
									}
									if(objEntry.getSubstanceAdministration().getEffectiveTime().get(j).getValue()!=null)
									{
										objEffectiveDate.setValue(objEntry.getSubstanceAdministration().getEffectiveTime().get(j).getValue());
									}
									objPlan.setPlannedDate(objEffectiveDate);
								}							
							}else{
								EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
								objPlan.setPlannedDate(objEffectiveDate);
							}

							//Set Plan of Care Activity Name
							objPlan.setPlanOfCareActivityName(EnumEntryDataSection.SUBSTANCEADMINISTRATION.getValue());

							//Extract and Set Activity Mood Code
							objPlan.setActivityMoodCode(objEntry.getSubstanceAdministration().getMoodCode().toString());
						}
						if(objEntry.getProcedure()!=null)
						{
							//Extract and Set Planned Activity
							objPlan.setPlannedActivity(objEntry.getObservation().getCode().getDisplayName());

							//Extract and Set Planned Date
							EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
							if(objEntry.getProcedure().getEffectiveTime().getRest().size() !=0)
							{

								for(int i=0;i<objEntry.getProcedure().getEffectiveTime().getRest().size();i++)
								{
									if(objEntry.getProcedure().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("low"))
									{
										objEffectiveDate.setLow( ((IVXBTS) objEntry.getProcedure().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}
									if(objEntry.getProcedure().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("high"))
									{
										objEffectiveDate.setHigh( ((IVXBTS) objEntry.getProcedure().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}
									if(objEntry.getProcedure().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("center"))
									{
										objEffectiveDate.setCenter( ((TS) objEntry.getProcedure().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}
									if(objEntry.getProcedure().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("width"))
									{
										objEffectiveDate.setWidth( ((PQ) objEntry.getProcedure().getEffectiveTime().getRest().get(i).getValue()).getValue());
									}

								}
							}
							if(objEntry.getProcedure().getEffectiveTime().getValue()!=null)
							{
								objEffectiveDate.setValue(objEntry.getProcedure().getEffectiveTime().getValue());
							}
							objPlan.setPlannedDate(objEffectiveDate);

							//Set Plan of Care Activity Name
							objPlan.setPlanOfCareActivityName(EnumEntryDataSection.PROCEDURE.getValue());

							//Extract and Set Activity Mood Code
							objPlan.setActivityMoodCode(objEntry.getProcedure().getMoodCode().toString());
						}
						if(objEntry.getSupply()!=null)
						{
							//Extract and Set Planned Activity
							objPlan.setPlannedActivity(objEntry.getSupply().getCode().getDisplayName());

							//Extract and Set Planned Date
							EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
							if(objEntry.getSupply().getEffectiveTime()!=null)
							{
								for(int j=0;j<objEntry.getSupply().getEffectiveTime().size();j++)
								{
									IVLTS objIVLTSeffectiveDate=(IVLTS) objEntry.getSupply().getEffectiveTime().get(j);
									if(objIVLTSeffectiveDate.getRest().size() !=0)
									{

										for(int i=0;i<objIVLTSeffectiveDate.getRest().size();i++)
										{
											if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("low"))
											{
												objEffectiveDate.setLow( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
											}
											if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("high"))
											{
												objEffectiveDate.setHigh( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
											}
											if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("center"))
											{
												objEffectiveDate.setCenter( ((TS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
											}
											if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("width"))
											{
												objEffectiveDate.setWidth( ((PQ) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
											}

										}
									}
									if(objEntry.getSupply().getEffectiveTime().get(j).getValue()!=null)
									{
										objEffectiveDate.setValue(objEntry.getSupply().getEffectiveTime().get(j).getValue());
									}
								}
							}
							objPlan.setPlannedDate(objEffectiveDate);

							//Set Plan of Care Activity Name
							objPlan.setPlanOfCareActivityName(EnumEntryDataSection.SUPPLY.getValue());		

							//Extract and Set Activity Mood Code
							objPlan.setActivityMoodCode(objEntry.getSupply().getMoodCode().toString());
						}						
						objListPlan.add(objPlan);
					}					
				}					
			}
		}
		if(objListPlan.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.PLAN.getValue()));
		}
		return objListPlan;
	}

	/**
	 *  This method is used to Parse Social History Section
	 *  @return objListSocialHistory- List of Social History
	 */
	public List<SocialHistory> getSocialHistory()
	{
		List<SocialHistory> objListSocialHistory = new ArrayList<SocialHistory>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();
		for (Component3 component3 : objListComp) {

			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.SOCIAL_HISTORY_SECTION_TEMPLATE))

			{
				//extracting section form component
				Section objSection = component3.getSection();

				if(objSection.getEntry()!= null)
				{
					for(Entry objEntry:objSection.getEntry())
					{
						SocialHistory objSocialHistory= new SocialHistory();
						//Extract and Set Social History Element
						objSocialHistory.setSocialHistoryElement(objEntry.getObservation().getCode().getDisplayName());

						//Extract Effective dates
						EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
						if(objEntry.getObservation().getEffectiveTime().getRest().size() !=0)
						{

							for(int i=0;i<objEntry.getObservation().getEffectiveTime().getRest().size();i++)
							{
								if(objEntry.getObservation().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("low"))
								{
									objEffectiveDate.setLow( ((IVXBTS) objEntry.getObservation().getEffectiveTime().getRest().get(i).getValue()).getValue());
								}
								if(objEntry.getObservation().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("high"))
								{
									objEffectiveDate.setHigh( ((IVXBTS) objEntry.getObservation().getEffectiveTime().getRest().get(i).getValue()).getValue());
								}
								if(objEntry.getObservation().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("center"))
								{
									objEffectiveDate.setCenter( ((TS) objEntry.getObservation().getEffectiveTime().getRest().get(i).getValue()).getValue());
								}
								if(objEntry.getObservation().getEffectiveTime().getRest().get(i).getName().getLocalPart().equals("width"))
								{
									objEffectiveDate.setWidth( ((PQ) objEntry.getObservation().getEffectiveTime().getRest().get(i).getValue()).getValue());
								}

							}
						}
						if(objEntry.getObservation().getEffectiveTime().getValue()!=null)
						{
							objEffectiveDate.setValue(objEntry.getObservation().getEffectiveTime().getValue());
						}
						objSocialHistory.setEffectiveDates(objEffectiveDate);

						//Extract and Set Description
						objSocialHistory.setDescription(objEntry.getObservation().getValue().get(0).getContent());

						objListSocialHistory.add(objSocialHistory);
					}
				}
			}
		}
		if(objListSocialHistory.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.SOCIAL_HISTORY.getValue()));
		}
		return objListSocialHistory;
	}

	/**
	 * This method return List of Results object
	 * @return objListResults- List of Results
	 */
	public List<Results> getResults() {
		List<Results> objListResults = new ArrayList<Results>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();

		for (Component3 component3 : objListComp) {
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.RESULTS_SECTION_TEMPLATE)) {
				// extracting section from component
				Section objSection = component3.getSection();

				Results objResult = null;
				TestReport report = null;
				List<TestReport> listReport=null;
				if (objSection.getEntry() != null) {
					String unit = null;
					String range = null;
					String value = null;
					for (int i = 0; i < objSection.getEntry().size(); i++) {

						// create new Result object for every Entry
						objResult = new Results();
						listReport = new ArrayList<>();
						if (objSection.getEntry().get(i).getOrganizer() != null) {
							// components within an entry will have same ResultTypeCode/DisplayName
							String resultTypeCode = objSection.getEntry().get(i).getOrganizer().getCode().getDisplayName();

							// loop for all components within an entry
							for (int j = 0; j < objSection.getEntry().get(i).getOrganizer().getComponent().size(); j++) {
								objResult.setTestDate(objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getEffectiveTime().getValue());

								// create new TestReport object for everycomponent
								report = new TestReport();
								report.setResultTypeCode(resultTypeCode);
								report.setTestName(objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getCode().getDisplayName());
								report.setValue(((PQ) objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getValue().get(0)).getValue());
								unit = ((PQ) objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getValue().get(0)).getUnit();
								report.setUnit(unit);

								List<ReferenceRange> refRange = objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getReferenceRange();
								for (ReferenceRange ref : refRange) {

									// If condition executes when range is set in text and else condition executes if range is set in low and high tags within rest
									if (ref.getObservationRange().getText() != null) {
										range = ((ED) ref.getObservationRange().getText()).getContent();
										report.setRange(range.replace(unit, ""));
									} else if(ref.getObservationRange().getValue()!=null){
										IVLPQ objpq = (IVLPQ) ref.getObservationRange().getValue();
										range = "";
										for (int k = 0; k < (((IVLPQ) ref.getObservationRange().getValue()).getRest()).size(); k++) {
											value = (objpq.getRest().get(k).getValue()).getValue();
											if (range.isEmpty()) {
												range += value;
											} else {
												range = range + "-" + value;
											}
										}
										report.setRange(range);
									}
								}
								listReport.add(report);
							}
							objResult.setTestDetails(listReport);
							objListResults.add(objResult);
						}

					}
				}

			}
		}
		if(objListResults.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.RESULTS.getValue()));
		}
		return objListResults;
	}

	/**
	 * This method return List of Family History object
	 * @return objListFamilyHistory- List of relatives with a medical history
	 */
	public List<FamilyHistory> getFamilyHistory() {
		List<FamilyHistory> objListFamilyHistory = new ArrayList<FamilyHistory>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();

		for (Component3 component3 : objListComp) {
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.FAMILY_HISTORY_TEMPLATE))
			{
				//extracting section form component
				Section objSection = component3.getSection();

				if(objSection.getEntry()!= null)
				{
					FamilyHistory objFamilyHistory=null;
					String vitalStatus;

					for(int i=0; i<objSection.getEntry().size();i++)
					{
						vitalStatus=null;
						String relationWithPatient=objSection.getEntry().get(i).getOrganizer().getSubject().getRelatedSubject().getCode().getDisplayName();
						String gender=objSection.getEntry().get(i).getOrganizer().getSubject().getRelatedSubject().getSubject().getAdministrativeGenderCode().getDisplayName();
						String birthTime=objSection.getEntry().get(i).getOrganizer().getSubject().getRelatedSubject().getSubject().getBirthTime().getValue();

						//if a component contains an EntryRelationship with a typeCode CAUS then the relative is deceased else alive
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
							} else {
								if(birthTime!=null){
									String yearAtOnset=((IVXBTS)((IVLTS)objSection.getEntry().get(i).getOrganizer().getComponent().get(j).getObservation().getEffectiveTime()).getRest().get(0).getValue()).getValue();
									ageAtOnset=Integer.toString(Integer.parseInt(yearAtOnset)-Integer.parseInt(birthTime));
								}
							}
							objFamilyHistory.setVitalStatus(vitalStatus);
							objFamilyHistory.setDiagnosis(diagnosis);
							objFamilyHistory.setAgeAtOnset(ageAtOnset);
							objListFamilyHistory.add(objFamilyHistory);
						}	
					}
				}
			}
		}
		if(objListFamilyHistory.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.FAMILY_HISTORY.getValue()));
		}
		return objListFamilyHistory;
	}
	/**
	 * This method return List of Problems object
	 * @return objListProblems- List of problem related to patient
	 */
	public List<Problems> getProblems() {
		List<Problems> objListProblems = new ArrayList<Problems>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();
		for (Component3 component3 : objListComp) {
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.PROBLEM_SECTION_TEMPLATE))
			{
				//extracting section form component
				Section objSection = component3.getSection();

				List<cdac.medinfo.ccd.templates.Entry> objEntryList = objSection.getEntry();
				for(cdac.medinfo.ccd.templates.Entry objEntry :objEntryList)
				{
					Problems objProblem = new Problems();
					if(objEntry.getAct()!=null && objEntry.getAct().getEntryRelationship()!=null&& !(objEntry.getAct().getEntryRelationship().isEmpty())){
						if(objEntry.getAct().getEntryRelationship().get(0).getObservation()!=null &&objEntry.getAct().getEntryRelationship().get(0).getObservation().getValue()!=null&&
								!(objEntry.getAct().getEntryRelationship().get(0).getObservation().getValue().isEmpty()))
							objProblem.setCondition(((CD)objEntry.getAct().getEntryRelationship().get(0).getObservation().getValue().get(0)).getDisplayName());

						objEntry.getAct().getEntryRelationship().get(0).getObservation().getEntryRelationship().get(0).getObservation().getValue();
						objProblem.setConditionStatus(((CD)objEntry.getAct().getEntryRelationship().get(0).
								getObservation().getEntryRelationship().get(0).getObservation().getValue().get(0)).getDisplayName());

						//Extract and Set Date
						EffectiveDateTime objEffectiveDate=new EffectiveDateTime();
						if(objEntry.getAct().getEntryRelationship().get(0).getObservation().getEffectiveTime()!=null){
							if(objEntry.getAct().getEntryRelationship().get(0).getObservation().getEffectiveTime().getRest()!=null){
							for(int j=0;j<objEntry.getAct().getEntryRelationship().get(0).getObservation().getEffectiveTime().getRest().size();j++){

								IVLTS objIVLTSeffectiveDate=(IVLTS) objEntry.getAct().getEntryRelationship().get(0).getObservation().getEffectiveTime();
								if(objIVLTSeffectiveDate!=null && objIVLTSeffectiveDate.getRest().size() !=0){

									for(int i=0;i<objIVLTSeffectiveDate.getRest().size();i++){

										if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("low")){
											objEffectiveDate.setLow( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
										}
										if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("high")){
											objEffectiveDate.setHigh( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
										}
										if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("center")){
											objEffectiveDate.setCenter( ((TS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
										}
										if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("width")){
											objEffectiveDate.setWidth( ((PQ) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
										}

									}
								}
							}
						}
								if(objEntry.getAct().getEntryRelationship().get(0).getObservation().getEffectiveTime().getValue()!=null)
								{
									objEffectiveDate.setValue(objEntry.getAct().getEntryRelationship().get(0).getObservation().getEffectiveTime().getValue());
								}
						}
						objProblem.setEffectiveDates(objEffectiveDate);
					}
					objListProblems.add(objProblem);
				}
			}
		}
		if(objListProblems.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.PROBLEMS.getValue()));
		}
		return objListProblems;
	}
	/**
	 * This method return List of Medications object
	 * @return objListMedications- List of Medications related to patient
	 */
	public List<Medications> getMedications() {
		List<Medications> objListMedications = new ArrayList<Medications>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();
		for (Component3 component3 : objListComp) {
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.MEDICATIONS_SECTION_TEMPLATE))
			{
				//extracting section form component
				Section objSection = component3.getSection();
				int in = 0;
				List<cdac.medinfo.ccd.templates.Entry> objEntryList = objSection.getEntry();
				for(cdac.medinfo.ccd.templates.Entry objEntry :objEntryList)
				{
					Medications objMedications = new Medications();
					if(objEntry.getSubstanceAdministration().getPrecondition()!=null&&!(objEntry.getSubstanceAdministration().getPrecondition().isEmpty()))
						objMedications.setCriterion(((CE)objEntry.getSubstanceAdministration().getPrecondition().get(0).getCriterion().getValue()).getDisplayName());
					if(objEntry.getSubstanceAdministration()!=null){
						objMedications.setMedication(((ED)objEntry.getSubstanceAdministration().getConsumable().getManufacturedProduct()
								.getManufacturedMaterial().getCode().getOriginalText()).getContent());
						if(objEntry.getSubstanceAdministration().getEffectiveTime().get(0) instanceof PIVLTS)
						{
							objMedications.setPeriodUnit(((PIVLTS)objEntry.getSubstanceAdministration().getEffectiveTime().get(0)).getPeriod().getUnit());
							objMedications.setPeriodValue(((PIVLTS)objEntry.getSubstanceAdministration().getEffectiveTime().get(0)).getPeriod().getValue());
						}
						EffectiveDateTime objEffectiveDate=new EffectiveDateTime();

						if(objEntry.getSubstanceAdministration().getEffectiveTime().get(0) instanceof IVLTS)
						{
							//Extract and Set Date
							if(objEntry.getSubstanceAdministration().getEffectiveTime()!=null){

								objMedications.setPeriodUnit(((PIVLTS)objEntry.getSubstanceAdministration().getEffectiveTime().get(1)).getPeriod().getUnit());
								objMedications.setPeriodValue(((PIVLTS)objEntry.getSubstanceAdministration().getEffectiveTime().get(1)).getPeriod().getValue());
								IVLTS objIVLTSeffectiveDate=(IVLTS) objEntry.getSubstanceAdministration().getEffectiveTime().get(0);
								if(objIVLTSeffectiveDate.getRest().size() !=0){
									for(int i=0;i<objIVLTSeffectiveDate.getRest().size();i++){
										if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("low")){
											objEffectiveDate.setLow( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
										}
										if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("high")){
											objEffectiveDate.setHigh( ((IVXBTS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
										}
										if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("center")){
											objEffectiveDate.setCenter( ((TS) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
										}
										if(objIVLTSeffectiveDate.getRest().get(i).getName().getLocalPart().equals("width")){
											objEffectiveDate.setWidth( ((PQ) objIVLTSeffectiveDate.getRest().get(i).getValue()).getValue());
										}
									}
								}
								if(objEntry.getSubstanceAdministration().getEffectiveTime().get(0).getValue()!=null)
								{
									objEffectiveDate.setValue(objEntry.getAct().getEntryRelationship().get(0).getObservation().getEffectiveTime().getValue());
								}
							}
						}
						objMedications.setDate(objEffectiveDate);
						if(objEntry.getSubstanceAdministration().getStatusCode().getCode().equals(EnumStatusCode.ACTIVE.getValue())){
							objMedications.setStatus(EnumStatusCode.ACTIVE.getValue());
						}
						else if(objEntry.getSubstanceAdministration().getStatusCode().getCode().equals(EnumStatusCode.COMPLETED.getValue())){
							objMedications.setStatus("No Longer Active");
						}
						else{
							objMedications.setStatus(objEntry.getSubstanceAdministration().getStatusCode().getCode());
						}
						objMedications.setDoseQuantity(objEntry.getSubstanceAdministration().getDoseQuantity().getValue());
						@SuppressWarnings("unchecked")
						JAXBElement<StrucDocTable> objJAX = (JAXBElement<StrucDocTable>) objSection.getText().getContent().get(1);
						StrucDocTr tr = (StrucDocTr)((StrucDocTbody)((StrucDocTable)objJAX.getValue()).getTbody().get(0)).getTr().get(in++);
						objMedications.setInstructions(((StrucDocTd) tr.getThOrTd().get(1)).getContent().get(0).toString());
					}
					
					objListMedications.add(objMedications);
				}
			}
		}
		if(objListMedications.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.MEDICATIONS.getValue()));
		}
		return objListMedications;
	}

	/**
	 * This method return List of Alert object
	 * @return objListAlerts- List of Alerts 
	 */
	public List<Alerts> getAlerts()
	{
		List<Alerts> objListAlert = new ArrayList<Alerts>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();

		for (Component3 component3 : objListComp)
		{
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.ALERT_SECTION_TEMPLATE))
			{
				//extracting section form component
				Section objSection = component3.getSection();

				if(objSection.getEntry()!= null)
				{
					for(Entry objEntry:objSection.getEntry())
					{
						if(objEntry != null)
						{
							Alerts objAlerts= new Alerts();

							//Set substance name
							if(objEntry.getAct().getEntryRelationship().get(0).getObservation().getParticipant().get(0).getParticipantRole().getPlayingEntity().getCode().getDisplayName() != null)
							{
								String subName = objEntry.getAct().getEntryRelationship().get(0).getObservation().getParticipant().get(0).getParticipantRole().getPlayingEntity().getCode().getDisplayName();
								objAlerts.setSubstance(subName);
							}


							List<EntryRelationship> objListER = objEntry.getAct().getEntryRelationship().get(0).getObservation().getEntryRelationship();
							for(EntryRelationship objER : objListER)
							{
								if(objER.getTypeCode().equals(XActRelationshipEntryRelationship.MFST))
								{
									//Set reaction
									String reaction = ((CD)objER.getObservation().getValue().get(0)).getDisplayName();
									objAlerts.setReaction(reaction);
								}
								if(objER.getTypeCode().equals(XActRelationshipEntryRelationship.REFR))
								{
									//Set Status
									String status = ((CE)objER.getObservation().getValue().get(0)).getDisplayName();
									objAlerts.setStatus(status);
								}
							}
							objListAlert.add(objAlerts);
						}
					}
				}
			}
		}
		if(objListAlert.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.ALERTS.getValue()));
		}
		return objListAlert;
	}


	/**
	 * This method return List of Procedure object
	 * @return objListProcedures- List of Procedures
	 */
	public List<Procedures> getProcedure()
	{
		List<Procedures> objListProcedure = new ArrayList<Procedures>();
		List<Component3> objListComp = objClinicalDoc.getComponent().getStructuredBody().getComponent();

		for (Component3 component3 : objListComp)
		{
			if (component3.getSection().getTemplateId().get(0).getRoot().equals(CCDConstantTemplates.PROCEDURES_SECTION_TEMPLATE))
			{
				//extracting section form component
				Section objSection = component3.getSection();

				if(objSection.getEntry()!= null)
				{
					for(Entry objEntry:objSection.getEntry())
					{
						if(objEntry != null)
						{

							Procedures objProcedure= new Procedures();
							String procedure = null;

							if(objEntry.getAct()!=null)
							{
								//set procedure
								procedure = objEntry.getAct().getCode().getDisplayName();
								if(procedure != null)
									objProcedure.setProcedure(procedure);
								//set laterality
								for(int i =0; i < objEntry.getAct().getCode().getQualifier().size(); i++)
								{
									if(objEntry.getAct().getCode().getQualifier().get(i) != null)
									{
										if(objEntry.getAct().getCode().getQualifier().get(i).getName().getDisplayName().equals(CCDConstants.LATERALITY))
											objProcedure.setLaterality(objEntry.getAct().getCode().getQualifier().get(0).getValue().getDisplayName());

										if(objEntry.getAct().getCode().getQualifier().get(i).getName().getDisplayName().equals(CCDConstants.OBSERVE))
											objProcedure.setObserve(objEntry.getAct().getCode().getQualifier().get(0).getValue().getDisplayName());

									}
								}
								//set Date
								objProcedure.setDate(objEntry.getAct().getEffectiveTime().getValue());

								objProcedure.setProcedureActivityName(EnumEntryDataSection.ACT.getValue());

								objProcedure.setProcedureStatusCode(objEntry.getAct().getStatusCode().getCode());

								objListProcedure.add(objProcedure);
							}

							if(objEntry.getObservation()!=null)
							{
								//set procedure
								procedure = objEntry.getObservation().getCode().getDisplayName();
								if(procedure != null)
									objProcedure.setProcedure(procedure);
								//set laterality
								for(int i =0; i < objEntry.getObservation().getCode().getQualifier().size(); i++)
								{
									if(objEntry.getObservation().getCode().getQualifier().get(i) != null)
									{
										if(objEntry.getObservation().getCode().getQualifier().get(i).getName().getDisplayName().equals(CCDConstants.LATERALITY))
											objProcedure.setLaterality(objEntry.getObservation().getCode().getQualifier().get(0).getValue().getDisplayName());

										if(objEntry.getObservation().getCode().getQualifier().get(i).getName().getDisplayName().equals(CCDConstants.OBSERVE))
											objProcedure.setObserve(objEntry.getObservation().getCode().getQualifier().get(0).getValue().getDisplayName());

									}
								}
								//set Date
								objProcedure.setDate(objEntry.getObservation().getEffectiveTime().getValue());

								objProcedure.setProcedureActivityName(EnumEntryDataSection.OBSERVATION.getValue());

								objProcedure.setProcedureStatusCode(objEntry.getObservation().getStatusCode().getCode());

								objListProcedure.add(objProcedure);
							}

							if(objEntry.getProcedure()!=null)
							{
								//set procedure
								procedure = objEntry.getProcedure().getCode().getDisplayName();
								if(procedure != null)
									objProcedure.setProcedure(procedure);
								//set laterality
								for(int i =0; i < objEntry.getProcedure().getCode().getQualifier().size(); i++)
								{
									if(objEntry.getProcedure().getCode().getQualifier().get(i) != null)
									{
										if(objEntry.getProcedure().getCode().getQualifier().get(i).getName().getDisplayName().equals(CCDConstants.LATERALITY))
											objProcedure.setLaterality(objEntry.getProcedure().getCode().getQualifier().get(0).getValue().getDisplayName());

										if(objEntry.getProcedure().getCode().getQualifier().get(i).getName().getDisplayName().equals(CCDConstants.OBSERVE))
											objProcedure.setObserve(objEntry.getProcedure().getCode().getQualifier().get(0).getValue().getDisplayName());

									}
								}
								//set Date
								objProcedure.setDate(objEntry.getProcedure().getEffectiveTime().getValue());

								objProcedure.setProcedureActivityName(EnumEntryDataSection.PROCEDURE.getValue());

								objProcedure.setProcedureStatusCode(objEntry.getProcedure().getStatusCode().getCode());

								objListProcedure.add(objProcedure);
							}


						}
					}

				}

			}
		}
		if(objListProcedure.isEmpty()){
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","SectionMissingWhileParsing",EnumSection.PROCEDURES.getValue()));
		}
		return objListProcedure;
	}
}
