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
 * This class specify table's header name used in clinical document
 */
public enum EnumTableHeader {

	/**
	 * This constant specifies value representation of column header for payer section
	 */
	PAYER_NAME("Payer name"),
	/**
	 * This constant specifies value representation of column header for payer section
	 */
	POLICY_TYPE("Policy type / Coverage type"),
	/**
	 * This constant specifies value representation of column header for payer section
	 */
	COVERED_PARTY_ID("Covered party ID"),
	/**
	 * This constant specifies value representation of column header for payer section
	 */
	AUTHORIZATION("Authorization(s)"),
	/**
	 * This constant specifies value representation of column header for advance directives section
	 */
	DIRECTIVE("Directive"),
	/**
	 * This constant specifies value representation of column header for multiple section
	 */
	DESCRIPTION("Description"),
	/**
	 * This constant specifies value representation of column header for advance directives section
	 */
	VERIFICATION("Verification"),
	/**
	 * This constant specifies value representation of column header for advance directives section
	 */
	SUPPORTING_DOCUMENT("Supporting Document(s)"),
	/**
	 * This constant specifies value representation of column header for functional status section
	 */
	FUNCTIONAL_CONDITION("Functional Condition"),
	/**
	 * This constant specifies value representation of column header for multiple section
	 */
	EFFECTIVE_DATES("Effective Dates"),
	/**
	 * This constant specifies value representation of column header for  multiple section
	 */
	CONDITION_STATUS("Condition Status"),
	/**
	 * This constant specifies value representation of column header for family history section
	 */
	DIAGNOSIS("Diagnosis"),
	/**
	 * This constant specifies value representation of column header for family history section
	 */
	AGE_OF_ONSET("Age of Onset"),
	/**
	 * This constant specifies value representation of column header for problems section
	 */
	CONDITION("Condition"),
	/**
	 * This constant specifies value representation of column header for social history section
	 */
	SOCIAL_HISTORY_ELEMENT("Social History Element"),
	/**
	 * This constant specifies value representation of column header for alerts section
	 */
	SUBSTANCE("Substance"),
	/**
	 * This constant specifies value representation of column header for multiple section
	 */
	STATUS("Status"),
	/**
	 * This constant specifies value representation of column header for alerts section
	 */
	REACTION("Reaction"),
	/**
	 * This constant specifies value representation of column header for medications section
	 */
	MEDICATIONS("Medications"),
	/**
	 * This constant specifies value representation of column header for medications section
	 */
	INSTRUCTIONS("Instructions"),
	/**
	 * This constant specifies value representation of column header for medications section
	 */
	START_DATE("Start Date"),
	/**
	 * This constant specifies value representation of column header for medical equipment section
	 */
	SUPPLY_DEVICE("Supply/Device"),
	/**
	 * This constant specifies value representation of column header for medical equipment section
	 */
	DATE_SUPPLIED("Date Supplied"),
	/**
	 * This constant specifies value representation of column header for immunizations section
	 */
	VACCINE("Vaccine"),
	/**
	 * This constant specifies value representation of column header for multiple section
	 */
	DATE("Date"),
	/**
	 * This constant specifies value representation of column header for procedures section
	 */
	PROCEDURE("Procedure"),
	/**
	 * This constant specifies value representation of column header for encounters section
	 */
	ENCOUNTER("Encounter"),
	/**
	 * This constant specifies value representation of column header for encounters section
	 */
	LOCATION("Location"),
	/**
	 * This constant specifies value representation of column header for plan section
	 */
	PLANNED_ACTIVITY("Planned Activity"),
	/**
	 * This constant specifies value representation of column header for plan section
	 */
	PLANNED_DATE("Planned Date"),
	
	
	;
	private String value;

	EnumTableHeader(String strValue)
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
