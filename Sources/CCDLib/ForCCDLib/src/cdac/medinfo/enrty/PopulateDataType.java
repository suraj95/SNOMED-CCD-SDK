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
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.bind.JAXBElement;

import cdac.medinfo.base.CCDConstants;
import cdac.medinfo.ccd.datatype.AD;
import cdac.medinfo.ccd.datatype.AdxpAdditionalLocator;
import cdac.medinfo.ccd.datatype.AdxpBuildingNumberSuffix;
import cdac.medinfo.ccd.datatype.AdxpCareOf;
import cdac.medinfo.ccd.datatype.AdxpCensusTract;
import cdac.medinfo.ccd.datatype.AdxpCity;
import cdac.medinfo.ccd.datatype.AdxpCountry;
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
import cdac.medinfo.ccd.datatype.CR;
import cdac.medinfo.ccd.datatype.CS;
import cdac.medinfo.ccd.datatype.CV;
import cdac.medinfo.ccd.datatype.ED;
import cdac.medinfo.ccd.datatype.ENXP;
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
import cdac.medinfo.ccd.datatype.PN;
import cdac.medinfo.ccd.datatype.PQ;
import cdac.medinfo.ccd.datatype.SC;
import cdac.medinfo.ccd.datatype.ST;
import cdac.medinfo.ccd.datatype.SXCMTS;
import cdac.medinfo.ccd.datatype.TEL;
import cdac.medinfo.ccd.datatype.TS;
import cdac.medinfo.ccd.enums.SetOperator;
import cdac.medinfo.ccd.narrativeblock.ObjectFactory;
import cdac.medinfo.codesytem.SNOMEDCode;
import cdac.medinfo.model.Address;
import cdac.medinfo.model.Contact;
import cdac.medinfo.model.EffectiveDateTime;
import cdac.medinfo.model.PatientDetails;
import cdac.medinfo.model.Procedures;

/**
 *  This Class is used to populate Data types used for Entry Data Generation 
 */
public class PopulateDataType {

	static ArrayList<ENXP> nameList = null ; 

	/**
	 *  This method is used to populate ST Data types which specify the character string data type stands for text data,primarily 
	 *  intended for machine processing (e.g.,sorting, querying, indexing, etc.) Used for names,symbols, and formal expressions
	 *  @param value - String value to be converted in ST data type
	 *  @return st- ST data type object
	 */
	public static  ST populateST(String value){
		ST st = new ST();
		st.setContent(value);
		return st;
	}
	/**
	 *  This method is used to populate II Data types an identifier that uniquely identifies a thing or object.
	 *  Examples are object identifier for HL7 RIM objects,medical record number, order id, service catalog item id,
	 *  Vehicle Identification Number (VIN), etc. Instance identifiers are defined based on ISO object identifiers. 
	 *  @param value - String value to be converted in II data type
	 *  @return ii- II data type object
	 */
	public static  II setIINullFalvour( String value){
		II ii = new II();
		ii.getNullFlavor().add(value);
		return ii;
	}
	/**
	 * 	This method is used to populate II Data types an identifier that uniquely identifies a thing or object.
	 *  Examples are object identifier for HL7 RIM objects,medical record number, order id, service catalog item id,
	 *  Vehicle Identification Number (VIN), etc. Instance identifiers are defined based on ISO object identifiers. 
	 *  @param extension - String value to be represent extension in II data type
	 *  @param root - String value to be represent root in II data type
	 *  @return ii- II data type object
	 */
	public static  II populateII(String extension, String root){
		II ii = new II();
		ii.setRoot(root);
		ii.setExtension(extension);
		return ii;
	}
	
	/**
	 * 	This method is used to populate II Data types an identifier that uniquely identifies a thing or object.
	 *  Examples are object identifier for HL7 RIM objects,medical record number, order id, service catalog item id,
	 *  Vehicle Identification Number (VIN), etc. Instance identifiers are defined based on ISO object identifiers. 
	 *  @param extension - String value to be represent extension in II data type
	 *  @param root - String value to be represent root in II data type
	 *  @param assigningAuthorityName - String value to represent assigningAuthorityNamein II data type
	 *  @return ii- II data type object
	 */
	public static  II populateII(String extension, String root, String assigningAuthorityName){
		II ii = new II();
		ii.setRoot(root);
		ii.setExtension(extension);
		ii.setAssigningAuthorityName(assigningAuthorityName);
		return ii;
	}
	/**
	 * 	This method is used to populate II Data types an identifier that uniquely identifies a thing or object.
	 *  Examples are object identifier for HL7 RIM objects,medical record number, order id, service catalog item id,
	 *  Vehicle Identification Number (VIN), etc. Instance identifiers are defined based on ISO object identifiers. 
	 *  @param root - String value to be represent root in II data type
	 *  @return ii- II data type object
	 */
	public static  II populateII( String root){
		II ii = new II();
		ii.setRoot(root);
		return ii;
	}
	/**
	 *  This method is used to populate CS Data types which specifies Coded data, consists of a code, display name, code system,and original text. 
	 *  Used when a single code value must be sent. 
	 *  @param value - String value to be converted in CS data type
	 *  @return cs- CS data type object
	 */
	public static  CS populateCS(String value){
		CS cs = new CS();
		cs.setCode(value);	
		return cs;
	}
	
	/**
	 *  This method is used to populate SC Data type.
	 *  A character string that optionally may have a code attached. The text must always be present if a code is present. The code is often a local code.  
	 *  @param value - String value to be converted in CS data type
	 *  @return sc- SC data type object
	 */
	public static SC populateSC(String value)
	{
		SC sc=new SC();
		sc.setDisplayName(value);
		return sc;
	}
	
	/**
	 *  This method is used to populate TS Data types which specify a quantity specifying a point on the axis of natural time.
	 * 	A point in time is most often represented as a calendar expression. 
	 *  @param value - String value to be converted in TS data type
	 *  @return ts- TS data type object
	 */
	public static  TS populateTS(String value){
		TS ts = new TS();
		ts.setValue(value);
		return ts;
	}
	/**
	 *  This method is used to populate TS Data types which specify a quantity specifying a point on the axis of natural time.
	 * 	A point in time is most often represented as a calendar expression. 
	 *  @return ts- TS data type object representing Current date
	 */
	public static  TS populateTS(){
		TS ts = new TS();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssZZZZ");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
		ts.setValue(dateFormat.format(new Date()));
		return ts;
	}
	/**
	 *  This method is used to populate INT Data types used which specify as Integer numbers (-1,0,1,2, 100, 3398129, etc.) are precise 
	 *  numbers that are results of counting and enumerating.Integer numbers are discrete, the set of integers is
	 *  infinite but countable. No arbitrary limit is imposed on the range of integer numbers. Two NULL flavors are
	 *  defined for the positive and negative infinity. 
	 *  @param value - String value to be converted in INT data type
	 *  @return intobj- INT data type object
	 */
	public static  INT populateIVLINT(String value){
		INT intobj=new INT();
		if(value!=null&&!value.isEmpty())
		intobj.setValue(new BigInteger(value));
		return intobj;
	}
	
	/**
	 *  This method is used to populate IVLTS Data types. 
	 *  @param value - String value to be converted in IVLTS data type
	 *  @return ivlts- IVLTS data type object
	 */
	public static  IVLTS populateIVLTS(String value){

		IVLTS ivlts = new IVLTS();
		if(value!=null)
		ivlts.setValue(value);
		else
			ivlts.setValue("");
		return ivlts;
	}
	/**
	 *  This method is used to populate IVLTS Data types. 
	 *  @param objEffectiveDateTime - EffectiveDateTime object value to be converted in IVLTS data type
	 *  @return ivlts- IVLTS data type object
	 */
	public static IVLTS populateIVLTS(EffectiveDateTime objEffectiveDateTime)
	{
		IVLTS objIVLTS = new IVLTS();
			
		ObjectFactory objFactory =new ObjectFactory();
		if(objEffectiveDateTime != null ){
			if(objEffectiveDateTime.getLow()!=null)
			{
				//Populate Low value of effective date/time
				IVXBTS valueLow = new IVXBTS();
				valueLow.setValue(objEffectiveDateTime.getLow());
				JAXBElement<IVXBTS> objIVXBTSLow=objFactory.createIVLTSLow(valueLow);
				objIVLTS.getRest().add(objIVXBTSLow);
			}
			
			if(objEffectiveDateTime.getHigh()!=null)
			{
				IVXBTS valueHigh= new IVXBTS();
				valueHigh.setValue(objEffectiveDateTime.getHigh());
				JAXBElement<IVXBTS> objIVXBTSHigh =objFactory.createIVLTSHigh(valueHigh);
				objIVLTS.getRest().add(objIVXBTSHigh);
			}
			
			if(objEffectiveDateTime.getCenter()!=null)
			{
				TS valueCenter = new TS();
				valueCenter.setValue(objEffectiveDateTime.getCenter());
				JAXBElement<TS> objTSCenter=objFactory.createIVLTSCenter(valueCenter);	
				objIVLTS.getRest().add(objTSCenter);
			}
			
			if(objEffectiveDateTime.getWidth()!=null)
			{
				PQ valueWidth =new PQ();
				valueWidth.setValue(objEffectiveDateTime.getWidth());
				JAXBElement<PQ> objPQWidth=objFactory.createIVLTSWidth(valueWidth );
				objIVLTS.getRest().add(objPQWidth);
			}
			
			if(objEffectiveDateTime.getValue()!=null)
			{
				objIVLTS.setValue(objEffectiveDateTime.getValue());
			}
		}
		return objIVLTS;
	}
	/**
	 *  This method is used to populate PIVLTS Data types. 
	 *  @param periodUnit - periodUnit string value to be converted in PIVLTS data type
	 *  @param periodValue -periodValue string value to be converted in PIVLTS data type
	 *  @param datePresent - if date object is populated in Medication then value is true else false
	 *  @return pivlts- PIVLTS data type object
	 */
	public static PIVLTS populatePIVLTS(String periodUnit, String periodValue, Boolean datePresent) {
		
		PIVLTS pivlts = new PIVLTS();
		PQ pq = new PQ();
		pq.setUnit(periodUnit);
		pq.setValue(periodValue);
		
		pivlts.setPeriod(pq);
		if(datePresent){
			pivlts.setOperator(SetOperator.A);
		}
		return pivlts;
	}
	/**
	 *  This method is used to populate PIVLTS Data types. 
	 *  @return ivlts- IVLTS data type object representing current date time with timezone
	 */
	public static  IVLTS populateIVLTS(){
		IVLTS ivlts = new IVLTS();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssZZZZ");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
		ivlts.setValue(dateFormat.format(new Date()));
		return ivlts;
	}
	/**
	 *  This method is used to populate SXCMTS Data types. 
	 *  @param val - Represents date in string format
	 *  @return sxcmts- SXCMTS data type object representing current date time with timezone
	 */
	public static  SXCMTS populateSXCMTS(String val){
		SXCMTS sxcmts = new SXCMTS();		
		if(val!=null)
		sxcmts.setValue(val);
		else
		sxcmts.setValue("");
		return sxcmts;
	}
	/**
	 *  This method is used to populate CV Data types which specifies Coded data, consists of a code, display name, code system,and original text. 
	 *  Used when a single code value must be sent. 
	 *  @param code - code in String format
	 *  @param codeSystem - code system in String format
	 *  @param displayName - Ddisplay Name
	 *  @return cv- CV data type object
	 */
	public static  CV populateCV( String code,String codeSystem,String displayName ){
		CV cv = new CV();
		cv.setCode(code);
		cv.setCodeSystem(codeSystem);
		cv.setDisplayName(displayName);
		return cv;
	}
	/**
	 *  This method is used to populate CV Data types which specifies Coded data, consists of a code, display name, code system,and original text. 
	 *  Used when a single code value must be sent. 
	 *  @param code - code in String format
	 *  @param displayName - Display Name
	 *  @return cv- CV data type object
	 */
	public static  CV populateCV( String code,String displayName ){
		CV cv = new CV();
		cv.setCode(code);
		cv.setDisplayName(displayName);
		return cv;
	}
	/**
	 *  This method is used to populate CE Data types which specifies Coded data, consists of a coded value (CV)and, optionally, coded value(s) from other coding systemsthat identify the same concept. 
	 *  Used when alternative codes may exist
	 *  @param code - code in String format
	 *  @param codeSystem - code system in String format
	 *  @param displayName - Display Name
	 *  @return ce- CE data type object
	 */
	public static  CE populateCE( String code,String codeSystem,String displayName ){
		CE ce = new CE();
		ce.setCode(code);
		ce.setCodeSystem(codeSystem);
		ce.setDisplayName(displayName);
		return ce;
	}
	/**
	 *  This method is used to populate CE Data types which specifies Coded data, consists of a coded value (CV)and, optionally, coded value(s) from other coding systemsthat identify the same concept. 
	 *  Used when alternative codes may exist
	 *  @param code - code in String format
	 *  @param codeSystem - code system in String format
	 *  @return ce- CE data type object
	 */
	public static  CE populateCE( String code,String codeSystem ){
		CE ce = new CE();
		ce.setCode(code);
		ce.setCodeSystem(codeSystem);
		return ce;
	}
	/**
	 *  This method is used to populate CE Data types which specifies Coded data, consists of a coded value (CV)and, optionally, coded value(s) from other coding systemsthat identify the same concept. 
	 *  Used when alternative codes may exist
	 *  @param code - code in String format
	 *  @return ce- CE data type object
	 */
	public static  CE populateCE( String code ){
		CE ce = new CE();
		ce.setCode(code);
		return ce;
	}
	/**
	 *  This method is used to populate TEL Data types an identifier a telephone number (voice or fax), e-mail address,
	 *  or other locator for a resource (information or service)mediated by telecommunication equipment. The address is 
	 *  specified as a Universal Resource Locator(URL) qualified by time specification and use codes that help in deciding 
	 *  which address to use for a given time and purpose.  
	 *  @param telcom - String value to be converted in TEL data type
	 *  @return tel- TEL data type object
	 */
	public static  TEL populateTEl( String telcom ){
		TEL tel = new TEL();
		tel.setValue(telcom);
		return tel;
	}
	/**
	 *  This method is used to populate TEL Data types an identifier a telephone number (voice or fax), e-mail address,
	 *  or other locator for a resource (information or service)mediated by telecommunication equipment. The address is 
	 *  specified as a Universal Resource Locator(URL) qualified by time specification and use codes that help in deciding 
	 *  which address to use for a given time and purpose.  
	 *  @param objContact - Contact Object to be converted in TEL data type
	 *  @return Telecom- TEL data type object
	 */
	public static TEL populateTEL(Contact objContact)
	{
		TEL Telecom = new TEL();
		if(objContact.getContactNumber()!=null)
			Telecom.setValue("tel:"+objContact.getContactNumber());
		if(objContact.getEmailAddress()!=null)
			Telecom.setValue("mailto:"+objContact.getEmailAddress());
		if(objContact.getUrl()!=null)
			Telecom.setValue("http:"+objContact.getUrl());
		
		if(objContact.getUse()!=null || !objContact.getUse().isEmpty())
		{
			for(String use:objContact.getUse())
				Telecom.getUse().add(use);
		}
		if(objContact.getUseablePeriod()!=null || !objContact.getUseablePeriod().isEmpty())
		{
			SXCMTS e =new SXCMTS();
			for(String UsePeriod:objContact.getUseablePeriod())
			{
				e.setValue(UsePeriod);
				Telecom.getUseablePeriod().add(e );
			}
		}
		return Telecom;
	}
	/**
	 *  This method is used to populate CD Data types which specifies a concept descriptor represents any kind of concept usually 
	 *  by giving a code defined in a code system. A concept descriptor can contain the original text or phrase that served as the
	 *  basis of the coding and one or more translations into different coding systems. A concept descriptor can also contain 
	 *  qualifiers to describe, e.g.,the concept of a "left foot" as a post coordinated term built from the primary code "FOOT"
	 *  and the qualifier "LEFT".In exceptional cases, the concept descriptor need not contain a code but only the original text describing that concept. 
	 *  @param code - code in String format
	 *  @param codeSystem - code system in String format
	 *  @param displayName - Display Name
	 *  @return cd- CD data type object
	 */
	public static  CD populateCD( String code,String codeSystem,String displayName ){
		CD cd = new CD();
		cd.setCode(code);
		cd.setCodeSystem(codeSystem);
		cd.setDisplayName(displayName);
				
		return cd;
	}
	/**
	 *  This method is used to populate CD Data types which specifies a concept descriptor represents any kind of concept usually 
	 *  by giving a code defined in a code system. A concept descriptor can contain the original text or phrase that served as the
	 *  basis of the coding and one or more translations into different coding systems. A concept descriptor can also contain 
	 *  qualifiers to describe, e.g.,the concept of a "left foot" as a post coordinated term built from the primary code "FOOT"
	 *  and the qualifier "LEFT".In exceptional cases, the concept descriptor need not contain a code but only the original text describing that concept. 
	 *  @param code - code in String format
	 *  @param codeSystem - code system in String format
	 *  @param displayName - Display Name
	 *  @param objED - ED data type object
	 *  @return cd- CD data type object
	 */
	public static  CD populateCDAndED( String code,String codeSystem,String displayName, ED objED ){
		CD cd = new CD();
		cd.setCode(code);
		cd.setCodeSystem(codeSystem);
		cd.setDisplayName(displayName);
		
		ED ed= new ED();
		ed.setContent(displayName);
		ed.setReference(objED.getReference());
		cd.setOriginalText(ed);
		
		return cd;
	}
	/**
	 *  This method is used to populate CD Data types which specifies a concept descriptor represents any kind of concept usually 
	 *  by giving a code defined in a code system. A concept descriptor can contain the original text or phrase that served as the
	 *  basis of the coding and one or more translations into different coding systems. A concept descriptor can also contain 
	 *  qualifiers to describe, e.g.,the concept of a "left foot" as a post coordinated term built from the primary code "FOOT"
	 *  and the qualifier "LEFT".In exceptional cases, the concept descriptor need not contain a code but only the original text describing that concept. 
	 *  @param code - code in String format
	 *  @param codeSystem - code system in String format
	 *  @return cd- CD data type object
	 */
	public static  CD populateCD( String code,String codeSystem  ){
		CD cd = new CD();
		cd.setCode(code);
		cd.setCodeSystem(codeSystem);
		return cd;
	}
	/**
	 *  This method is used to populate CD Data types which specifies a concept descriptor represents any kind of concept usually 
	 *  by giving a code defined in a code system. A concept descriptor can contain the original text or phrase that served as the
	 *  basis of the coding and one or more translations into different coding systems. A concept descriptor can also contain 
	 *  qualifiers to describe, e.g.,the concept of a "left foot" as a post coordinated term built from the primary code "FOOT"
	 *  and the qualifier "LEFT".In exceptional cases, the concept descriptor need not contain a code but only the original text describing that concept. 
	 *  @param nullFalvor - Null Falvor in String format
	 *  @return cd- CD data type object
	 */
	public static  CD populateCDNullFalvor( String nullFalvor  ){
		CD cd = new CD();
		cd.getNullFlavor().add(nullFalvor);
		return cd;
	}
	/**
	 *  This method is used to populate AD Data types which specifies Mailing and home or office addresses. A sequence of 
	 *  address parts, such as street or post office Box, city,postal code, country, etc. 
	 *  @param objParticipantAddr - {@link Address} object representing Participant Address
	 *  @return addr- AD data type object
	 */
	public static  AD populateAD(Address objParticipantAddr){
		AD addr = new AD();
		if(objParticipantAddr== null)
			return addr;
		if(objParticipantAddr.getStreetAddressLine()!=null)
		{
			AdxpStreetAddressLine AddressLine = new AdxpStreetAddressLine();
			AddressLine.setContent(objParticipantAddr.getStreetAddressLine());
			addr.getContent().add((Serializable) AddressLine);
		}
		
		if(objParticipantAddr.getPostalCode()!=null)
		{
		AdxpPostalCode adpxpostalCode = new AdxpPostalCode();
		adpxpostalCode.setContent(objParticipantAddr.getPostalCode());
		addr.getContent().add((Serializable) adpxpostalCode);
		}
		
		if(objParticipantAddr.getCity()!=null)
		{
		AdxpCity adpxcity = new AdxpCity();
		adpxcity.setContent(objParticipantAddr.getCity());
		addr.getContent().add((Serializable) adpxcity);
		}
		if(objParticipantAddr.getState()!=null)
		{
		AdxpState adpxstate = new AdxpState();
		adpxstate.setContent(objParticipantAddr.getState());
		addr.getContent().add((Serializable) adpxstate);
		}
		if(objParticipantAddr.getCounty()!=null)
		{
		AdxpCountry adxpcountry = new AdxpCountry();
		adxpcountry.setContent(objParticipantAddr.getCounty());
		addr.getContent().add((Serializable) adxpcountry);
		}
		if(objParticipantAddr.getAdditionalLocator()!=null)
		{
			AdxpAdditionalLocator additionalLocator = new AdxpAdditionalLocator();
			additionalLocator.setContent(objParticipantAddr.getAdditionalLocator());
			addr.getContent().add((Serializable) additionalLocator);
		}
		if(objParticipantAddr.getBuildingNumberSuffix()!=null)
		{
			AdxpBuildingNumberSuffix buildingNumberSuffix = new AdxpBuildingNumberSuffix();
			buildingNumberSuffix.setContent(objParticipantAddr.getBuildingNumberSuffix());
			addr.getContent().add((Serializable) buildingNumberSuffix);
		}
		if(objParticipantAddr.getCareOf()!=null)
		{
			AdxpCareOf careOf = new AdxpCareOf();
			careOf.setContent(objParticipantAddr.getCareOf());
			addr.getContent().add((Serializable) careOf);
		}
		if(objParticipantAddr.getCensusTract()!=null)
		{
			AdxpCensusTract censusTract = new AdxpCensusTract();
			censusTract.setContent(objParticipantAddr.getCensusTract());
			addr.getContent().add((Serializable) censusTract);
		}
		if(objParticipantAddr.getDelimiter()!=null)
		{
			AdxpDelimiter delimiter = new AdxpDelimiter();
			delimiter.setContent(objParticipantAddr.getDelimiter());
			addr.getContent().add((Serializable) delimiter);
		}
		if(objParticipantAddr.getDeliveryAddressLine()!=null)
		{
			AdxpDeliveryAddressLine deliveryAddressLine = new AdxpDeliveryAddressLine();
			deliveryAddressLine.setContent(objParticipantAddr.getDeliveryAddressLine());
			addr.getContent().add((Serializable) deliveryAddressLine);
		}
		if(objParticipantAddr.getDeliveryInstallationArea()!=null)
		{
			AdxpDeliveryInstallationArea deliveryInstallationArea = new AdxpDeliveryInstallationArea();
			deliveryInstallationArea.setContent(objParticipantAddr.getDeliveryInstallationArea());
			addr.getContent().add((Serializable) deliveryInstallationArea);
		}
		if(objParticipantAddr.getDeliveryInstallationQualifier()!=null)
		{
			AdxpDeliveryInstallationQualifier deliveryInstallationQualifier = new AdxpDeliveryInstallationQualifier();
			deliveryInstallationQualifier.setContent(objParticipantAddr.getDeliveryInstallationQualifier());
			addr.getContent().add((Serializable) deliveryInstallationQualifier);
		}
		if(objParticipantAddr.getDeliveryInstallationType()!=null)
		{
			AdxpDeliveryInstallationType deliveryInstallationType = new AdxpDeliveryInstallationType();
			deliveryInstallationType.setContent(objParticipantAddr.getDeliveryInstallationType());
			addr.getContent().add((Serializable) deliveryInstallationType);
		}
		if(objParticipantAddr.getDeliveryMode()!=null)
		{
			AdxpDeliveryMode deliveryMode = new AdxpDeliveryMode();
			deliveryMode.setContent(objParticipantAddr.getDeliveryMode());
			addr.getContent().add((Serializable) deliveryMode);
		}
		if(objParticipantAddr.getDeliveryModeIdentifier()!=null)
		{
			AdxpDeliveryModeIdentifier deliveryModeIdentifier = new AdxpDeliveryModeIdentifier();
			deliveryModeIdentifier.setContent(objParticipantAddr.getDeliveryModeIdentifier());
			addr.getContent().add((Serializable) deliveryModeIdentifier);
		}
		if(objParticipantAddr.getDirection()!=null)
		{
			AdxpDirection direction = new AdxpDirection();
			direction.setContent(objParticipantAddr.getDirection());
			addr.getContent().add((Serializable) direction);
		}
		if(objParticipantAddr.getHouseNumber()!=null)
		{
			AdxpHouseNumber houseNumber = new AdxpHouseNumber();
			houseNumber.setContent(objParticipantAddr.getHouseNumber());
			addr.getContent().add((Serializable) houseNumber);
		}
		if(objParticipantAddr.getHouseNumberNumeric()!=null)
		{
			AdxpHouseNumberNumeric houseNumberNumeric = new AdxpHouseNumberNumeric();
			houseNumberNumeric.setContent(objParticipantAddr.getHouseNumberNumeric());
			addr.getContent().add((Serializable) houseNumberNumeric);
		}
		if(objParticipantAddr.getPostBox()!=null)
		{
			AdxpPostBox postBox = new AdxpPostBox();
			postBox.setContent(objParticipantAddr.getPostBox());
			addr.getContent().add((Serializable) postBox);
		}
		if(objParticipantAddr.getPrecinct()!=null)
		{
			AdxpPrecinct precinct = new AdxpPrecinct();
			precinct.setContent(objParticipantAddr.getPrecinct());
			addr.getContent().add((Serializable) precinct);
		}
		if(objParticipantAddr.getStreetName()!=null)
		{
			AdxpStreetName streetName = new AdxpStreetName();
			streetName.setContent(objParticipantAddr.getStreetName());
			addr.getContent().add((Serializable) streetName);
		}
		if(objParticipantAddr.getStreetNameBase()!=null)
		{
			AdxpStreetNameBase streetNameBase = new AdxpStreetNameBase();
			streetNameBase.setContent(objParticipantAddr.getStreetNameBase());
			addr.getContent().add((Serializable) streetNameBase);
		}
		if(objParticipantAddr.getStreetNameType()!=null)
		{
			AdxpStreetNameType streetNameType = new AdxpStreetNameType();
			streetNameType.setContent(objParticipantAddr.getStreetNameType());
			addr.getContent().add((Serializable) streetNameType);
		}
		if(objParticipantAddr.getUnitID()!=null)
		{
			AdxpUnitID unitID = new AdxpUnitID();
			unitID.setContent(objParticipantAddr.getUnitID());
			addr.getContent().add((Serializable) unitID);
		}
		if(objParticipantAddr.getUnitType()!=null)
		{
			AdxpUnitType unitType = new AdxpUnitType();
			unitType.setContent(objParticipantAddr.getUnitType());
			addr.getContent().add((Serializable) unitType);
		}
		return addr;
	}
	/**
	 *  This method is used to populate PN Data types which specifies A name for a person. A sequence of name parts, such as 
	 *  given name or family name, prefix, suffix, etc.PN differs from EN because the qualifier type cannot include LS(Legal Status).  
	 *  @param prefix - represent Prefix used by person
	 *  @param given - represent Name used by person
	 *  @param family - represent Family name used by person
	 *  @param suffix- represent suffix used by person
	 *  @return pn- PN data type object
	 */
	public static  PN populateNameList(String prefix,String given,String family,String suffix)
	{
		PN pn = new PN();
		nameList  = new ArrayList<ENXP>();	
		if(prefix!=null){
			EnPrefix pfx = new EnPrefix();
			pfx.setContent(prefix);
			nameList.add(pfx);

		}

		if(given!=null)
		{
			EnGiven name = new EnGiven();
			name.setContent(given);
			nameList.add(name);
		}
		
		if(family!=null)
		{
			EnFamily fname = new EnFamily();
			fname.setContent(family);
			nameList.add(fname);
		}
		
		if(suffix!=null){
			EnSuffix sfx = new EnSuffix();
			sfx.setContent(suffix);
			nameList.add(sfx);
		}
		pn.getContent().addAll(nameList);
		return pn;

	}
	/**
	 *  This method is used to populate CD Data types which specifies a concept descriptor represents any kind of concept usually 
	 *  by giving a code defined in a code system. A concept descriptor can contain the original text or phrase that served as the
	 *  basis of the coding and one or more translations into different coding systems. A concept descriptor can also contain 
	 *  qualifiers to describe, e.g.,the concept of a "left foot" as a post coordinated term built from the primary code "FOOT"
	 *  and the qualifier "LEFT".In exceptional cases, the concept descriptor need not contain a code but only the original text describing that concept. 
	 *  @param originalText - originalText in String format
	 *  @return cd- CD data type object
	 */
	public static CD populateCDOriginalText(String originalText) {
		CD cd = new CD();
		ED ed = new ED();
		ed.setContent(originalText);
		cd.setOriginalText(ed);
		return null;
	}
	/**
	 *  This method is used to populate PN Data types which specifies A name for a person. A sequence of name parts, such as 
	 *  given name or family name, prefix, suffix, etc.PN differs from EN because the qualifier type cannot include LS(Legal Status).  
	 *  @param objPatientDetails - PatientDetails object
	 *  @return pn- PN data type object
	 */
	public static PN populateNameList(PatientDetails objPatientDetails) {

		PN pn = new PN();

		nameList  = new ArrayList<ENXP>();	

		if(objPatientDetails.getPrefix()!=null){
			EnPrefix pfx = new EnPrefix();
			pfx.setContent(objPatientDetails.getPrefix());
			nameList.add(pfx);

		}
		if(objPatientDetails.getName()!=null){
			EnGiven name = new EnGiven();
			name.setContent(objPatientDetails.getName());
			nameList.add(name);
		}

		if(objPatientDetails.getFamily()!=null){
			EnFamily fname = new EnFamily();
			fname.setContent(objPatientDetails.getFamily());
			nameList.add(fname);
		}

		if(objPatientDetails.getSuffix()!=null){
			EnSuffix sfx = new EnSuffix();
			sfx.setContent(objPatientDetails.getSuffix());
			nameList.add(sfx);
		}
		pn.getContent().addAll(nameList);
		return pn;
	}
	/**
	 *  This method is used to populate PQ Data types which specifies a dimensioned quantity expressing the result of a measurement act.   
	 *  @param value - Value string value to be converted in PQ data type 
	 *  @param unit -Unit string value to be converted in PQ data type
	 *  @return pq- PQ data type object
	 */
	public static PQ populatePQ(String value, String unit) {

		PQ pq = new PQ();
		pq.setUnit(unit);
		pq.setValue(value);
		return pq;
	}
	/**
	 *  This method is used to populate ED Data types which specifies Data that is primarily intended for human interpretation 
	 *  or for further machine processing is outside the scope ofHL7. This includes unformatted or formatted written language,
	 *  multi media data, or structured information as defined by a different standard (e.g., XML-signatures.) Instead of 
	 *  the data itself, an ED may contain only a reference (see TEL.) Note that the ST data type is a specialization of the 
	 *  ED data type when the ED media type is text/plain. 
	 *  @param key - key is string value to be converted in ED data type 
	 *  @return ed- ED data type object
	 */
	public static ED populateED(String key) {
		ED ed = new ED();
		ed.setContent(key);
		return ed;
	}
	/**
	 *  This method is used to populate IVLPQ Data types.
	 *  @param value - value is string value to be converted in IVLPQ data type 
	 *  @return ivlpq- IVLPQ data type object
	 */
	public static IVLPQ populateIVLPQ( String value) {
		IVLPQ ivlpq = new IVLPQ();
	
		if(value!=null)
		{	
		ivlpq.setValue(value);
		}
		
		return ivlpq;
		
	}
	 /**  This method is used to populate CE Data types which specifies Coded data, consists of a coded value (CV)and, optionally, coded value(s) from other coding systemsthat identify the same concept. 
	 *  Used when alternative codes may exist
	 *  @param code - code in String format
	 *  @param codeSystem - code system in String format
	 *  @param displayName - Display Name
	 *  @return ce- CE data type object
	 */
	public static CE populateCEAndED(String code, String codeSystem, String displayName) {
		CE ce = new CE();
		ce.setCode(code);
		ce.setCodeSystem(codeSystem);
		ce.setDisplayName(displayName);
		
		ED ed = new ED();
		ed.setContent(displayName);
		ce.setOriginalText(ed);
		
		return ce;
	}
	
	/**  This method is used to populate CE Data types which specifies Coded data, consists of a coded value (CV)and, optionally, coded value(s) from other coding systemsthat identify the same concept. 
	 *  Used when alternative codes may exist
	 *  @param code - code in String format
	 *  @param codeSystem - code system in String format
	 *  @param displayName - Display Name
	 *  @param testName - Test Name
	 *  @return ce- CE data type object
	 */
	public static CE populateCEAndED(String code, String codeSystem, String displayName, String testName) {
		CE ce = new CE();
		ce.setCode(code);
		ce.setCodeSystem(codeSystem);
		ce.setDisplayName(displayName);
		
		ED ed = new ED();
		ed.setContent(testName);
		ce.setOriginalText(ed);
		
		return ce;
	}

	/**
	 *  This method is used to populate CD Data types which specifies a concept descriptor represents any kind of concept usually 
	 *  by giving a code defined in a code system. A concept descriptor can contain the original text or phrase that served as the
	 *  basis of the coding and one or more translations into different coding systems. A concept descriptor can also contain 
	 *  qualifiers to describe, e.g.,the concept of a "left foot" as a post coordinated term built from the primary code "FOOT"
	 *  and the qualifier "LEFT".In exceptional cases, the concept descriptor need not contain a code but only the original text describing that concept. 
	 *  @param code - code in String format
	 *  @param codeSystem - code system in String format
	 *  @param objModel - represent Model object
  	 *  @param testName - Test Name
	 *  @return cd- CD data type object
	 */
	public static CD populateCD(String code, String codeSystem, Object objModel, String testName) {
		CD cd = new CD();
		cd.setCode(code);
		cd.setCodeSystem(codeSystem);
		cd.setDisplayName((((Procedures) objModel).getProcedure()));
		
		//check if value of laterality attribute present
		if(((Procedures) objModel).getLaterality() != null)
				{
					CR cr = new CR();
					cr.setName(PopulateDataType.populateCV(SNOMEDCode.getCode(CCDConstants.LATERALITY), CCDConstants.LATERALITY));
					cr.setValue(PopulateDataType.populateCD(SNOMEDCode.getCode(((Procedures) objModel).getLaterality()),null, ((Procedures) objModel).getLaterality()));//check 
					cd.getQualifier().add(cr);
				}
				
		if(((Procedures) objModel).getObserve() != null){
					CR cr = new CR();
					cr.setName(PopulateDataType.populateCV(SNOMEDCode.getCode(CCDConstants.OBSERVE), CCDConstants.OBSERVE));
					cr.setValue(PopulateDataType.populateCD(SNOMEDCode.getCode(((Procedures) objModel).getObserve()),null, ((Procedures) objModel).getObserve()));//check 
					cd.getQualifier().add(cr);
				}
		
		ED ed = new ED();
		TEL tel = new TEL();
		tel.setValue(testName);
		ed.setReference(tel);
		cd.setOriginalText(ed);
		
		return cd;
	}
	/**  This method is used to populate CE Data types which specifies Coded data, consists of a coded value (CV)and, optionally, coded value(s) from other coding systemsthat identify the same concept. 
	 *  Used when alternative codes may exist
	 *  @param code - code in String format
	 *  @param codeSystem - code system in String format
	 *  @param codeSystemName - code system name in String format
	 *  @param displayName - Display Name
	 *  @return ce- CE data type object
	 */
	public static CE populateCE(String code, String codeSystem, String codeSystemName, String displayName) {
		CE ce = new CE();
		ce.setCode(code);
		ce.setCodeSystem(codeSystem);
		ce.setCodeSystemName(codeSystemName);
		ce.setDisplayName(displayName);
		return ce;
	}
	/**
	 *  This method is used to populate PN Data types which specifies A name for a person. A sequence of name parts, such as 
	 *  given name or family name, prefix, suffix, etc.PN differs from EN because the qualifier type cannot include LS(Legal Status).  
	 *  @param property - String represent property 
	 *  @return pn- PN data type object
	 */
	public static PN populatePN(String property) {
		PN pn = new PN();
		pn.getContent().add(property);
		return pn;
	}
	/**
	 *  This method is used to populate CD Data types which specifies a concept descriptor represents any kind of concept usually 
	 *  by giving a code defined in a code system. A concept descriptor can contain the original text or phrase that served as the
	 *  basis of the coding and one or more translations into different coding systems. A concept descriptor can also contain 
	 *  qualifiers to describe, e.g.,the concept of a "left foot" as a post coordinated term built from the primary code "FOOT"
	 *  and the qualifier "LEFT".In exceptional cases, the concept descriptor need not contain a code but only the original text describing that concept. 
	 *  @param code - code in String format
	 *  @param codeSystem - code system in String format
	 *  @param displayName - Display Name
	 *  @return cd- CD data type object
	 */
	public static CD populateCDAndCR(String code, String codeSystem, String displayName) {
		CD cd = new CD();
		CR cr = new CR();

		cr.setName(PopulateDataType.populateCV(SNOMEDCode.getCode(CCDConstants.EPISODICITY), CCDConstants.EPISODICITY));  
		cr.setValue(PopulateDataType.populateCD(SNOMEDCode.getCode(CCDConstants.NEW_EPISODE),null, CCDConstants.NEW_EPISODE)); 
		cd.getQualifier().add(cr);
		
		cd.setCode(code);
		cd.setCodeSystem(codeSystem);
		cd.setDisplayName(displayName);
		
		return cd;
	}

	
}
