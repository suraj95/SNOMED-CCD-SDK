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
 * This class specify LOINC constants used in clinical document
 */
public enum EnumLOINC {

	/**
	 *  This constant specifies value representation of LOINC code for summary purpose 
	 */
	SUMMARY_PURPOSE("48764-5"),
	/**
	 *  This constant specifies value representation of LOINC code for dose Number 
	 */
	DOSE_NUMBER("30973-2"),
	/**
	 * This constant specifies value representation of LOINC code for summarization of episode node 
	 */
	SUMMARIZATION_OF_EPISODE_NOTE_CODE("34133-9"),
	/**
	 * This constant specifies value representation of LOINC code for payment sources  
	 */
	PAYMENT_SOURCES("48768-6"),
	/**
	 *  This constant specifies value representation of LOINC code for history of family member diseases 
	 */
	HISTORY_OF_FAMILY_MEMBER_DISEASE("10157-6"),
	/**
	 * This constant specifies value representation of LOINC code for advance directives 
	 */
	ADVANCE_DIRECTIVES("42348-3"),
	/**
	 * This constant specifies value representation of LOINC code for medication use 
	 */
	MEDICATION_USE ("10160-0"),
	/**
	 *  This constant specifies value representation of LOINC code for immunization  
	 */
	IMMUNIZATION ("11369-6"),
	/**
	 * This constant specifies value representation of LOINC code for diagnosis status
	 */
	DIAGNOSIS_STATUS ("33999-4"),
	/**
	 *  This constant specifies value representation of LOINC code for vital Signs
	 */
	VITAL_SIGNS("8716-3"),
	/**
	 * This constant specifies value representation of LOINC code for plan of care note 
	 */
	PLAN_OF_CARE_NOTE("18776-5"),
	/**
	 * This constant specifies value representation of LOINC code for problem list
	 */
	PROBLEM_LIST("11450-4"),
	/**
	 *  This constant specifies value representation of LOINC code for  history of medical device use 
	 */
	HISTORY_OF_MEDICAL_DEVICE_USE("46264-8"),
	/**
	 * This constant specifies value representation of LOINC code for functional status
	 */
	FUNCTIONAL_STATUS("47420-5"),
	/**
	 * This constant specifies value representation of LOINC code for  history of procedures 
	 */
	HISTORY_OF_PROCEDURE("47519-4"),
	/**
	 * This constant specifies value representation of LOINC code for functional status assessment note
	 */
	FUNCTIONAL_STATUS_ASSESSMENT_NOTE("47420-5"),
	/**
	 *  This constant specifies value representation of LOINC code for plan of social history 	
	 */
	SOCIAL_HISTORY("29762-2"),
	/**
	 * This constant specifies value representation of LOINC code for allergies or adverse reactions 
	 */
	ALLERGIES_OR_ADVERSE_REACTIONS("48765-2"),
	/**
	 * This constant specifies value representation of LOINC code for relevant diagnostic tests or laboratory data  	
	 */
	RELEVANT_DIAGNOSTIC_TESTS_OR_LABORATORY_DATA("30954-2"),
	/**
	 * This constant specifies value representation of LOINC code for status  	
	 */
	STATUS("33999-4"),
	/**
	 * This constant specifies value representation of LOINC code for history of encounters  	
	 */
	HISTORY_OF_ENCOUNTERS("46240-8");


	private String value;

	EnumLOINC(String strValue)
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
