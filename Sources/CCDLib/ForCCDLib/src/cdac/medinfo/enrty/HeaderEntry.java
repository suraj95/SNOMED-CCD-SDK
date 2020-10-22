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

import java.util.List;

import cdac.medinfo.base.CCDConstants;
import cdac.medinfo.base.StandardMethods;
import cdac.medinfo.ccd.datatype.AD;
import cdac.medinfo.ccd.datatype.ON;
import cdac.medinfo.ccd.datatype.SXCMTS;
import cdac.medinfo.ccd.datatype.TEL;
import cdac.medinfo.ccd.enums.EntityClassDevice;
import cdac.medinfo.ccd.enums.XDeterminerInstanceKind;
import cdac.medinfo.ccd.enums.XServiceEventPerformer;
import cdac.medinfo.ccd.narrativeblock.ObjectFactory;
import cdac.medinfo.ccd.templates.AssignedAuthor;
import cdac.medinfo.ccd.templates.AssignedCustodian;
import cdac.medinfo.ccd.templates.AssignedEntity;
import cdac.medinfo.ccd.templates.AssociatedEntity;
import cdac.medinfo.ccd.templates.Author;
import cdac.medinfo.ccd.templates.AuthoringDevice;
import cdac.medinfo.ccd.templates.Birthplace;
import cdac.medinfo.ccd.templates.Custodian;
import cdac.medinfo.ccd.templates.CustodianOrganization;
import cdac.medinfo.ccd.templates.DocumentationOf;
import cdac.medinfo.ccd.templates.Informant12;
import cdac.medinfo.ccd.templates.LegalAuthenticator;
import cdac.medinfo.ccd.templates.Organization;
import cdac.medinfo.ccd.templates.OrganizationPartOf;
import cdac.medinfo.ccd.templates.Participant1;
import cdac.medinfo.ccd.templates.Patient;
import cdac.medinfo.ccd.templates.PatientRole;
import cdac.medinfo.ccd.templates.Performer1;
import cdac.medinfo.ccd.templates.Person;
import cdac.medinfo.ccd.templates.Place;
import cdac.medinfo.ccd.templates.RecordTarget;
import cdac.medinfo.ccd.templates.ServiceEvent;
import cdac.medinfo.codesytem.SNOMEDCode;
import cdac.medinfo.enums.EnumClassCode;
import cdac.medinfo.enums.EnumCodeSystem;
import cdac.medinfo.enums.EnumSection;
import cdac.medinfo.enums.EnumTypeCode;
import cdac.medinfo.model.Address;
import cdac.medinfo.model.AuthorInfo;
import cdac.medinfo.model.Contact;
import cdac.medinfo.model.DocumentationOfInfo;
import cdac.medinfo.model.Identifiers;
import cdac.medinfo.model.OrganizationInfo;
import cdac.medinfo.model.OrganizationPartOfInfo;
import cdac.medinfo.model.Participant;
import cdac.medinfo.model.PatientDetails;
import cdac.medinfo.model.Performer;
import cdac.medinfo.utils.CCDLogger;
import cdac.medinfo.utils.PropertyLoader;
import cdac.medinfo.utils.ResourceBundleWrapper;
/**
 *  This Class is used to populate data for CCD Header
 */
public class HeaderEntry {
	ObjectFactory objFactory =new ObjectFactory();
	/**
	 *  This method is used to create and add Record Target data in Header
	 *  @param objPatientDetails - Patient Details for whose Clinical Document to be generated
	 *  @return objRecordTarget - {@link RecordTarget} RecordTarget object
	 */
	public RecordTarget populateRecordTarget(PatientDetails objPatientDetails)
	{
		RecordTarget objRecordTarget= objFactory.createRecordTarget();
		ON onOrganizationName  =objFactory.createON();

		if(objPatientDetails!=null){
			if(objPatientDetails.getName()==null||objPatientDetails.getName().isEmpty())
			{
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("warning","PrimaryAAttributeMissing",EnumSection.HEADER.getValue()));
			}
					//Creating Patient Role Object
					PatientRole patientRole =  objFactory.createPatientRole();
					patientRole.getId().add(PopulateDataType.populateII(objPatientDetails.getMrn(),EnumCodeSystem.OID_HL7.getValue())); 
					if(objPatientDetails.getMrn()!=null){
						PropertyLoader.setProperty("MRN",objPatientDetails.getMrn());		
					}
					//Creating Patient Object
					Patient	patient = objFactory.createPatient();
					patient.getName().add(PopulateDataType.populateNameList(objPatientDetails));
					String genderCode = null;
					if(objPatientDetails.getGender()!=null)
					{
							if(objPatientDetails.getGender().equalsIgnoreCase("male")||objPatientDetails.getGender().equalsIgnoreCase("m")){
								genderCode="M";
							}
							else if(objPatientDetails.getGender().equalsIgnoreCase("female")||objPatientDetails.getGender().equalsIgnoreCase("f")){
								genderCode="F";
							}
							else{
								genderCode="UN";
							}
							patient.setAdministrativeGenderCode(PopulateDataType.populateCE(genderCode,EnumCodeSystem.OID_ADMINISTRATIVE_GENDER.getValue()));
					}
					if(objPatientDetails.getBirthTime()!=null)
					patient.setBirthTime(PopulateDataType.populateTS(objPatientDetails.getBirthTime()));
					if(objPatientDetails.getMaritalStatus()!=null)
						patient.setMaritalStatusCode(PopulateDataType.populateCE(objPatientDetails.getMaritalStatus()));
					if(objPatientDetails.getRaceCode()!=null)
						patient.setRaceCode(PopulateDataType.populateCE(objPatientDetails.getRaceCode()));
					if(objPatientDetails.getReligiousAffiliation()!=null)
						patient.setReligiousAffiliationCode(PopulateDataType.populateCE(objPatientDetails.getReligiousAffiliation()));
					if(objPatientDetails.getBirthPlace()!=null){
						AD objAD=new AD();
						objAD.getContent().add(objPatientDetails.getBirthPlace());
						Place objPlace=new Place();
						objPlace.setAddr(objAD);
						Birthplace objBirthplace=new Birthplace();
						objBirthplace.setPlace(objPlace);
						patient.setBirthplace(objBirthplace);
					}
					if(objPatientDetails.getEthnicGroup()!=null)
						patient.setEthnicGroupCode(PopulateDataType.populateCE(objPatientDetails.getEthnicGroup()));
					
					//Creating Provider Organization representing Organization Name
					Organization org =  objFactory.createOrganization();
					org.getId().add(PopulateDataType.populateII(EnumCodeSystem.OID_HL7.getValue()));
					onOrganizationName.getContent().add(PropertyLoader.getProperty("Organization_Name"));
					org.getName().add(onOrganizationName);
					//adding Patient
					patientRole.setPatient(patient);
					//adding Provider Organization
					patientRole.setProviderOrganization(org);
					//adding Patient Role
					objRecordTarget.setPatientRole(patientRole);
		}
		return objRecordTarget;
	}

	/**
	 *  This method is used to create and add Author data in CCD Header
	 *  @param objAuthor - Author object
	 *  @return objAuthor - {@link Author} Author object 
	 */
	public  Author populateAuthor(AuthorInfo objAuthor) {
		//Creating Author Object
		Author objAuthorElement = objFactory.createAuthor();
		
		if(objAuthor.getTime()!=null)
			objAuthorElement.setTime(PopulateDataType.populateTS(objAuthor.getTime()));
		else
		    objAuthorElement.setTime(PopulateDataType.populateTS()); 
		
		if(objAuthor.getFunctionCode()!=null)
			objAuthorElement.setFunctionCode(PopulateDataType.populateCE(objAuthor.getFunctionCode(), EnumCodeSystem.OID_PARTICIPATION_FUNCTION.getValue()));
		
		//Creating Assigned Author Object
		AssignedAuthor assignedAuthor= objFactory.createAssignedAuthor();
		if(objAuthor.getAuthoringDevice()!=null || (objAuthor.getPrefix()!=null || objAuthor.getName()!=null || objAuthor.getFamily()!=null || objAuthor.getSuffix()!=null))
		{
			if(!objAuthor.getId().isEmpty())
			{
				for(Identifiers objIdentifier:objAuthor.getId())
					assignedAuthor.getId().add(PopulateDataType.populateII(objIdentifier.getExtension(), objIdentifier.getRootIdentifier(), objIdentifier.getAssigningAuthorityName()));
			}			
			else
				assignedAuthor.getId().add(PopulateDataType.populateII(StandardMethods.generateUUID()));
		}
		else if(objAuthor.getOrganization()!=null)
		{
			assignedAuthor.getId().add(PopulateDataType.setIINullFalvour("NA"));
		}
			
		//Create and populate Authoring Device code
		if(objAuthor.getAuthoringDevice()!=null)
		{
			AuthoringDevice objAuthoringDevice=objFactory.createAuthoringDevice();
			objAuthoringDevice.setClassCode(EntityClassDevice.DEV);
			objAuthoringDevice.setDeterminerCode(XDeterminerInstanceKind.INSTANCE.value());
			if(objAuthor.getAuthoringDevice().getSoftwareName()!=null)
				objAuthoringDevice.setSoftwareName(PopulateDataType.populateSC(objAuthor.getAuthoringDevice().getSoftwareName()));
			if(objAuthor.getAuthoringDevice().getManufacturerModelName()!=null)
				objAuthoringDevice.setManufacturerModelName(PopulateDataType.populateSC(objAuthor.getAuthoringDevice().getManufacturerModelName()));
			//Add Authoring Device code
			assignedAuthor.setAssignedAuthoringDevice(objAuthoringDevice);
		}
		
		//Creating Assigned Person Object
		if(objAuthor.getPrefix()!=null || objAuthor.getName()!=null || objAuthor.getFamily()!=null || objAuthor.getSuffix()!=null)
		{
			//Creating Assigned Person Object
			Person assignedPerson =objFactory.createPerson();
			assignedPerson.getName().add(PopulateDataType.populateNameList(objAuthor.getPrefix(),objAuthor.getName(), objAuthor.getFamily(), objAuthor.getSuffix())); 
			
			//Adding Assigned Person 
			assignedAuthor.setAssignedPerson(assignedPerson);		
		}
		
		//Creating Represented Organization
		Organization representedOrganization = objFactory.createOrganization() ;
		if(objAuthor.getOrganization()!=null)
		{
			OrganizationInfo objOrganization=objAuthor.getOrganization();
			
			//Populate Organization	
			representedOrganization=populateOrganization(objOrganization, representedOrganization);
			
			//Set As Organization Part Of
			if(objOrganization.getAsOrganizationPartOfInfo()!=null)
			{
				OrganizationPartOfInfo objOrgnizationPartOfInfo=objOrganization.getAsOrganizationPartOfInfo();
				OrganizationPartOf objOrganizationpartOf=objFactory.createOrganizationPartOf();
				
				if(objOrgnizationPartOfInfo.getId()!=null)
				{
					for(Identifiers objIdentifier:objOrgnizationPartOfInfo.getId())
						objOrganizationpartOf.getId().add(PopulateDataType.populateII(objIdentifier.getExtension(), objIdentifier.getRootIdentifier(), objIdentifier.getAssigningAuthorityName()));
				}
							
				if(objOrgnizationPartOfInfo.getEffectiveTime()!=null)
					objOrganizationpartOf.setEffectiveTime(PopulateDataType.populateIVLTS(objOrgnizationPartOfInfo.getEffectiveTime()));
				
				if(objOrgnizationPartOfInfo.getWholeOrganization()!=null)
					objOrganizationpartOf.setWholeOrganization(populateOrganization(objOrgnizationPartOfInfo.getWholeOrganization(), representedOrganization));
				
				representedOrganization.setAsOrganizationPartOf(objOrganizationpartOf);
			}
		}
		else
		{
			String Organization_Name = PropertyLoader.getProperty("Organization_Name");
			ON onOrganizationName = objFactory.createON();
			onOrganizationName.getContent().add(Organization_Name);
			representedOrganization.getName().add(onOrganizationName);
		}
		
		// adding Represented Organization 
		assignedAuthor.setRepresentedOrganization(representedOrganization);
		//adding Assigned Author
		objAuthorElement.setAssignedAuthor(assignedAuthor);
		return objAuthorElement;
	}

	/**
	 *  This method is used to create and add Custodian data in CCD Header
	 *  @return objCustodian - {@link Custodian} Custodian object
	 */
	public  Custodian populateCustodian() {

		//Creating Custodian Object
		Custodian objCustodian = objFactory.createCustodian();
		
		//Creating  Represented Custodian Organization
		CustodianOrganization representedCustodianOrganization = objFactory.createCustodianOrganization();
		representedCustodianOrganization.getId().add(PopulateDataType.populateII(EnumCodeSystem.OID_HL7.getValue()));

		String Organization_Name = PropertyLoader.getProperty("Organization_Name");
		ON onOrganizationName = objFactory.createON();
		onOrganizationName.getContent().add(Organization_Name);
		representedCustodianOrganization.setName(onOrganizationName);

		//Creating Assigned Custodian
		AssignedCustodian assignedCustodian = objFactory.createAssignedCustodian();
		assignedCustodian.setRepresentedCustodianOrganization(representedCustodianOrganization);
				
		//adding Assigned Custodian
		objCustodian.setAssignedCustodian(assignedCustodian);
		return objCustodian;

	}
	/**
	 *  This method is used to create and add Documentation data in Header
	 *  @param objDocumentation - DocumentationOf object
	 *  @return objDocumentationOf - {@link DocumentationOf} DocumentationOf object
	 */
	public  DocumentationOf populateDocumentation(DocumentationOfInfo objDocumentation) {
		DocumentationOf objDocumentationOf = objFactory.createDocumentationOf();

		//Creating Service Element
		ServiceEvent serviceElement = objFactory.createServiceEvent();
		serviceElement.getClassCode().add(EnumClassCode.PCPR.getValue());
		serviceElement.setEffectiveTime(PopulateDataType.populateIVLTS(objDocumentation.getEffectiveDateofDocument()));  // need to add high low
		if(objDocumentation.getPerformer()!=null)
		{
			//Creating Performer1
			Performer1 performer = objFactory.createPerformer1();
	
			List<Performer> objPerformerL= objDocumentation.getPerformer();
			for(Performer objPerformer:objPerformerL)
			{
				performer.setTypeCode(XServiceEventPerformer.PRF);
				performer.setFunctionCode(PopulateDataType.populateCE(objPerformer.getFunctionCode(), EnumCodeSystem.OID_PARTICIPATION_FUNCTION.getValue()));
			
				performer.setTime(PopulateDataType.populateIVLTS(objPerformer.getTime()));		

				//Creating Assigned Person
				Person assignedPerson =objFactory.createPerson();
				
				assignedPerson.getName().add(PopulateDataType.populateNameList(objPerformer.getPrefix(),objPerformer.getName(), objPerformer.getFamily(), objPerformer.getSuffix()));

				//Creating Assigned Entity
				AssignedEntity assignedEntity =objFactory.createAssignedEntity();
				assignedEntity.getId().add(PopulateDataType.populateII(StandardMethods.generateUUID()));	
			
				//Creating  Represented Organization
				Organization representedOrganization = objFactory.createOrganization() ;
				
				
					if(objPerformer.getOrganization()!=null)
					{
						OrganizationInfo objOrganization=objPerformer.getOrganization();
						
						//Populate Organization	
						representedOrganization=populateOrganization(objOrganization, representedOrganization);
						
						//Set As Organization Part Of
						if(objOrganization.getAsOrganizationPartOfInfo()!=null)
						{
							OrganizationPartOfInfo objOrgnizationPartOfInfo=objOrganization.getAsOrganizationPartOfInfo();
							OrganizationPartOf objOrganizationpartOf=objFactory.createOrganizationPartOf();
							
							if(objOrgnizationPartOfInfo.getId()!=null)
							{
								for(Identifiers objIdentifier : objOrgnizationPartOfInfo.getId())
									objOrganizationpartOf.getId().add(PopulateDataType.populateII(objIdentifier.getExtension(), objIdentifier.getRootIdentifier(), objIdentifier.getAssigningAuthorityName()));
							}
								
							if(objOrgnizationPartOfInfo.getEffectiveTime()!=null)
								objOrganizationpartOf.setEffectiveTime(PopulateDataType.populateIVLTS(objOrgnizationPartOfInfo.getEffectiveTime()));
							
							if(objOrgnizationPartOfInfo.getWholeOrganization()!=null)
								objOrganizationpartOf.setWholeOrganization(populateOrganization(objOrgnizationPartOfInfo.getWholeOrganization(), representedOrganization));
							
							representedOrganization.setAsOrganizationPartOf(objOrganizationpartOf);
						}
						
						assignedEntity.setRepresentedOrganization(representedOrganization);
					}
					else
					{
						performer.setTime(PopulateDataType.populateIVLTS());
						
						String Organization_Name = PropertyLoader.getProperty("Organization_Name");;
						ON onOrganizationName = objFactory.createON();
						onOrganizationName.getContent().add(Organization_Name);
						representedOrganization.getName().add(onOrganizationName);
					}
					
					//adding Assigned Person
					assignedEntity.setAssignedPerson(assignedPerson);
							
					//adding Assigned Entity
					performer.setAssignedEntity(assignedEntity);
					//adding Performer
					serviceElement.getPerformer().add(performer);
			}//adding Service Element
		}
		objDocumentationOf.setServiceEvent(serviceElement);
		return objDocumentationOf;
	}
	
	private Organization populateOrganization(OrganizationInfo objOrganization,Organization representedOrganization)
	{
		//Set Organization ID
		if(objOrganization.getId()!=null)
		{
			for(Identifiers objIdentifier: objOrganization.getId())
				representedOrganization.getId().add(PopulateDataType.populateII(objIdentifier.getExtension(), objIdentifier.getRootIdentifier(), objIdentifier.getAssigningAuthorityName()));
		}
			
		//Set Organization Name
		if(objOrganization.getOrganizationName()!=null)
		{
			ON onOrganizationName = objFactory.createON();
			onOrganizationName.getContent().add(objOrganization.getOrganizationName());
			representedOrganization.getName().add(onOrganizationName);
		}
		//Set Address
		if(objOrganization.getAddr()!=null)
		{
			for(Address objAddress:objOrganization.getAddr())
			{
				representedOrganization.getAddr().add(PopulateDataType.populateAD(objAddress));
			}
		}
		//Set Telecom
		if(objOrganization.getTelecom()!=null && !objOrganization.getTelecom().isEmpty())
		{
			for(Contact Tel:objOrganization.getTelecom())
			{
				TEL objTelecom = objFactory.createTEL();
				if(Tel.getContactNumber()!=null)
					objTelecom.setValue("tel:"+Tel.getContactNumber());
				if(Tel.getEmailAddress()!=null)
					objTelecom.setValue("mailto:"+Tel.getEmailAddress());
				if(Tel.getUrl()!=null)
					objTelecom.setValue("http:"+Tel.getUrl());
				
				if(Tel.getUse()!=null || !Tel.getUse().isEmpty())
				{
					for(String use:Tel.getUse())
						objTelecom.getUse().add(use);
				}
				if(Tel.getUseablePeriod()!=null || !Tel.getUseablePeriod().isEmpty())
				{
					SXCMTS e =new SXCMTS();
					for(String UsePeriod:Tel.getUseablePeriod())
					{
						e.setValue(UsePeriod);
						objTelecom.getUseablePeriod().add(e );
					}
				}
				representedOrganization.getTelecom().add(objTelecom);
			}	
		}
		//Set Standard Industry Class Code
		if(objOrganization.getStandardIndustryClassCode()!=null)
			representedOrganization.setStandardIndustryClassCode(PopulateDataType.populateCE(objOrganization.getStandardIndustryClassCode()));
		
		return representedOrganization;
	}
	/**
	 *  This method is used to create and add Informant12 data in CCD Header
	 *  @return objInformant - {@link Informant12} Informant12 object
	 */
	public  Informant12 populateInformant() {
		//Creating Informant object
		Informant12 objInformant = objFactory.createInformant12();
		//Creating Assigned Entity
		AssignedEntity assignedEntity =objFactory.createAssignedEntity();
		assignedEntity.getId().add(PopulateDataType.setIINullFalvour(CCDConstants.NULLFLAVOR_NI));
		
		Organization representedOrganization = objFactory.createOrganization() ;
		ON onOrganizationName = objFactory.createON();
		representedOrganization.getId().add(PopulateDataType.populateII(EnumCodeSystem.OID_HL7.getValue()));

		String Organization_Name = PropertyLoader.getProperty("Organization_Name");
		onOrganizationName.getContent().add(Organization_Name);
		representedOrganization.getName().add(onOrganizationName);
		
		assignedEntity.setRepresentedOrganization(representedOrganization);
		objInformant.setAssignedEntity(assignedEntity);
		return objInformant;
	}
	/**
	 *  This method is used to create and add Legal Authenticator data in CCD Header
  	 *  @return objLegalAuthenticator - {@link LegalAuthenticator} LegalAuthenticator object
	 */
	public LegalAuthenticator populateLegalAuthenticator() {

		LegalAuthenticator objLegalAuthenticator = objFactory.createLegalAuthenticator();
		objLegalAuthenticator.setTime(PopulateDataType.populateTS());
		objLegalAuthenticator.setSignatureCode(PopulateDataType.populateCS(CCDConstants.SIGNATURECODE));
		//Creating Assigned Entity
		AssignedEntity assignedEntity = objFactory.createAssignedEntity();
		//	assignedEntity.getNullFlavor().add(CCDConstants.NULLFLAVOR_NI);
		assignedEntity.getId().add(PopulateDataType.setIINullFalvour(CCDConstants.NULLFLAVOR_NI));
	
		Organization representedOrganization = objFactory.createOrganization();
		ON onOrganizationName = objFactory.createON();
		representedOrganization.getId().add(PopulateDataType.populateII(EnumCodeSystem.OID_HL7.getValue()));  
		
		String Organization_Name = PropertyLoader.getProperty("Organization_Name");
		onOrganizationName.getContent().add(Organization_Name);
		representedOrganization.getName().add(onOrganizationName);
		assignedEntity.setRepresentedOrganization(representedOrganization);
		objLegalAuthenticator.setAssignedEntity(assignedEntity);
		return objLegalAuthenticator;
	}
	/**
	 *  This method is used to create and add Participant1 data in CCD Header
	 *  @param objParticipant - object specify Participant Details
  	 *  @return participant - {@link Participant1} Participant1 object
	 */
	public Participant1 getParticipant(Participant objParticipant)
	{
		Participant1 participant = objFactory.createParticipant1() ;
	
		participant.getTypeCode().add(EnumTypeCode.IND.getValue()); 
		//Creating Associated Entity
		AssociatedEntity associatedEntity = objFactory.createAssociatedEntity() ;
		if(objParticipant!= null)
		{
			if(objParticipant.isGaurdian())//depend upon user role
				associatedEntity.getClassCode().add(EnumClassCode.GUAR.getValue());  
			else if(objParticipant.isNOK())
				associatedEntity.getClassCode().add(EnumClassCode.NOK.getValue());  
			else if(objParticipant.isEmergencyContactPresent())
				associatedEntity.getClassCode().add(EnumClassCode.ECON.getValue());  
			else if(objParticipant.isCareGiverPresent())
				associatedEntity.getClassCode().add(EnumClassCode.CAREGIVER.getValue());  
			associatedEntity.getId().add(PopulateDataType.populateII(StandardMethods.generateUUID()));
			if(objParticipant.getParticipantRelation()!=null)
				associatedEntity.setCode(PopulateDataType.populateCE(SNOMEDCode.getCode(objParticipant.getParticipantRelation()),EnumCodeSystem.OID_SNOMED_CT.getValue(),objParticipant.getParticipantRelation()));

			if(objParticipant.getParticipantAddress()!=null)
			{
				for(int i=0;i<objParticipant.getParticipantAddress().size();i++){
					associatedEntity.getAddr().add(getAddress(objParticipant.getParticipantAddress().get(i)));
				}
			}
			if(objParticipant.getParticipantContact()!=null)
			{
				for(int i=0;i!=objParticipant.getParticipantContact().size();i++){
					List<Contact> objConList = objParticipant.getParticipantContact();
					for(Contact objCon :objConList){
						associatedEntity.getTelecom().add(getTelecom(objCon));
					}
				}
			}
			//Creating Associated Person
			Person associatedPerson = objFactory.createPerson();
			associatedPerson.getName().add(PopulateDataType.populateNameList(objParticipant.getParticipantPrefix(), objParticipant.getParticipantName(), objParticipant.getParticipantFamily(), objParticipant.getParticipantSuffix()));
			associatedEntity.setAssociatedPerson(associatedPerson);
		}
		participant.setAssociatedEntity(associatedEntity);
		return participant;
	}

	/**
	 *  This method is used to create and add Participant1 data in CCD Header
	 *  @param objParticipantAddr - object specify Participant Address
  	 *  @return Address object
	 */
	private AD getAddress(Address objParticipantAddr)
	{
		return PopulateDataType.populateAD(objParticipantAddr);
	}
	/**
	 *  This method is used to create and add Participant1 data in CCD Header
	 *  @param telecom - object specify Participant Telecom
  	 *  @return TEL object
	 */
	private TEL getTelecom(Contact telecom)
	{
		return PopulateDataType.populateTEL(telecom);
	}

}
