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
package cdac.medinfo.enums;
/**
 * This class specify section name used in sections
 */
public enum EnumSection {

	/**
	 *  This constant specifies Section Name of Social History Section
	 */
	SOCIAL_HISTORY("Social History"),
	/**
	 *  This constant specifies Section Name of Custodian Section
	 */
	CUSTODIAN("Custodian"),
	/**
	 * This constant specifies Section Name of Author Section
	 */
	AUTHOR("Author"),
	/**
	 *  This constant specifies Section Name of Legal Authenticator Section
	 */
	LEGAL_AUTHENTICATOR("LegalAuthenticator"),
	/**
	 * This constant specifies Section Name of Results Section
	 */
	RESULTS("Results"),
	/**
	 *  This constant specifies Section Name of Header Section
	 */
	HEADER("Header"),
	/**
	 * This constant specifies Section Name of Document Generator Section
	 */
	DOCUMENT_GENERATOR("Document Generator"),
	/**
	 *  This constant specifies Section Name of Advance Directive Section
	 */
	ADVANCE_DIRECTIVE("Advance Directives"),
	/**
	 *  This constant specifies Section Name of Alert Section
	 */
	ALERTS("Allergies, Adverse Reactions, Alerts"),
	/**
	 *  This constant specifies Section Name of Encounters Section
	 */
	ENCOUNTERS("Encounters"),
	/** 
	 * This constant specifies Section Name of Functional Status Section
	 * 
	 */
	FUNCTIONAL_STATUS("Functional Status"),
	/** 
	 * This constant specifies Section Name of Immunizations Section
	 */
	IMMUNIZATION("Immunizations"),
	/**
	 *  This constant specifies Section Name of Medical Equipment Section
	 */
	MEDICAL_EQUIPMENT("Medical Equipment"),
	/** 
	 * This constant specifies Section Name of Medications Section
	 */
	MEDICATIONS("Medications"),
	/** 
	 * This constant specifies Section Name of Payers Section
	 */
	PAYERS("Payer"),
	/** 
	 * This constant specifies Section Name of Plan Section
	 */
	PLAN("Plan"),
	/**
	 *  This constant specifies Section Name of Problems Section
	 */
	PROBLEMS("Problems"),
	/** 
	 * This constant specifies Section Name of Procedures Section
	 */
	PROCEDURES("Procedures"),
	/** 
	 * This constant specifies Section Name of Summary Purpose Section
	 */
	SUMMARY_PURPOSE("Summary Purpose"),
	/** 
	 * This constant specifies Section Name of Vital Signs Section
	 */
	VITAL_SIGNS("Vital Signs"),
	/**
	 *  This constant specifies Section Name of Family History Section
	 */
	FAMILY_HISTORY("Family History");

	private String value;

	EnumSection(String strValue)
	{
		this.value = strValue;
	}

	/**
	 * This function returns the String value of this enum.
	 * @return - returns string value of Code Value.
	 */
	public String getValue()
	{
		return value;
	}
}
