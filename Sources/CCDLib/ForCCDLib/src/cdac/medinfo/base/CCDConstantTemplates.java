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
package cdac.medinfo.base;
/**
 * This class specify template identifier constants used in clinical document
 */
public class CCDConstantTemplates {

	/**
	 * This constant specifies value representation of clinical document header root
	 */
	public static final String CLINICALDOCUMENT_ROOT = "2.16.840.1.113883.1.3";
	/**
	 * This constant specifies value representation of clinical document header extension
	 */
	public static final String CLINICALDOCUMENT_EXTENSION = "POCD_HD000040";
	/**
	 * This constant specifies value representation of purpose activity templates
	 */
	public static final String PURPOSE_ACTIVITY_TEMPLATES = "2.16.840.1.113883.10.20.1.30";
	/**
	 * This constant specifies value representation of clinical document root templates
	 */
	public static final String CLINICALDOCUMENT_ROOT_TEMPLATES = "2.16.840.1.113883.10.20.1";
	/**
	 * This constant specifies value representation of medication observation templates
	 */
	public static final String MEDICATION_OBSERVAYION_TEMPLATES = "2.16.840.1.113883.10.20.1.46";
	/**
	 * This constant specifies value representation of  purpose section template
	 */
	public static final String PURPOSE_SECTION_TEMPLATES = "2.16.840.1.113883.10.20.1.13";
	/**
	 * This constant specifies value representation of immunizations section template
	 */
	public static final String IMMUNIZATION_SECTION_TEMPLATES = "2.16.840.1.113883.10.20.1.6";
	/**
	 * This constant specifies value representation of payer section template
	 */
	public static final String PAYERS_SECTION_TEMPLATES = "2.16.840.1.113883.10.20.1.9";
	/**
	 * This constant specifies value representation of coverage activity template
	 */
	public static final String  COVERAGE_ACTIVITY_TEMPLATE = "2.16.840.1.113883.10.20.1.20";
	/**
	 * This constant specifies value representation of  procedure activity template
	 */
	public static final String  PROCEDURE_ACTIVITY_TEMPLATE = "2.16.840.1.113883.10.20.1.29";
	/**
	 * This constant specifies value representation of  advance directives template
	 */
	public static final String  ADVANCE_DIRECTIVES_TEMPLATE = "2.16.840.1.113883.10.20.1.1";
	/**
	 * This constant specifies value representation of encounter section template
	 */
	public static final String  ENCOUNTER_SECTION_TEMPLATE = "2.16.840.1.113883.10.20.1.3";
	/**
	 * This constant specifies value representation of procedure section template
	 */
	public static final String  PROCEDURES_SECTION_TEMPLATE = "2.16.840.1.113883.10.20.1.12";
	/**
	 * This constant specifies value representation of  advance directive observation template
	 */
	public static final String  ADVANCE_DIRECTIVE_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.17";
	/**
	 * This constant specifies value representation of verification of an advance directive observation template
	 */
	public static final String  VERIFICATION_OF_AN_ADVANCE_DIRECTIVE_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.58";
	/**
	 * This constant specifies value representation of advance directive reference template
	 */
	public static final String ADVANCE_DIRECTIVES_REFERENCE_TEMPLATE = "2.16.840.1.113883.10.20.1.36";
	/**
	 * This constant specifies value representation of advance directive status observation template
	 */
	public static final String ADVANCE_DIRECTIVES_STATUS_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.37";
	/**
	 * This constant specifies value representation of policy activity template
	 */
	public static final String POLICY_ACTIVITY_TEMPLATE = "2.16.840.1.113883.10.20.1.26";
	/**
	 * This constant specifies value representation of medications criterion template
	 */
	public static final String MEDICATIONS_CRITERION_TEMPLATE = "2.16.840.1.113883.10.20.1.26";
	/**
	 * This constant specifies value representation of authorization activity template
	 */
	public static final String AUTHORIZATION_ACTIVITY_TEMPLATE = "2.16.840.1.113883.10.20.1.19";
	/**
	 * This constant specifies value representation of functional status section template
	 */
	public static final String FUNCTIONAL_STATUS_SECTION_TEMPLATE = "2.16.840.1.113883.10.20.1.5";
	/**
	 * This constant specifies value representation of status of functional status observation template
	 */
	public static final String STATUS_OF_FUNCTIONAL_STATUS_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.44";
	/**
	 * This constant specifies value representation of problem act template
	 */
	public static final String PROBLEM_ACT_TEMPLATE = "2.16.840.1.113883.10.20.1.27";
	/**
	 * This constant specifies value representation of family history template
	 */
	public static final String FAMILY_HISTORY_TEMPLATE = "2.16.840.1.113883.10.20.1.4";
	/**
	 * This constant specifies value representation of problem observation template
	 */
	public static final String PROBLEM_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.28";
	/**
	 * This constant specifies value representation of problem section template
	 */
	public static final String PROBLEM_SECTION_TEMPLATE = "2.16.840.1.113883.10.20.1.11";
	/**
	 * This constant specifies value representation of problem status observation template
	 */
	public static final String PROBLEM_STATUS_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.50";
	/**
	 * This constant specifies value representation of episode observation template
	 */
	public static final String EPISODE_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.41";
	/**
	 * This constant specifies value representation of social history section template 
	 */
	public static final String SOCIAL_HISTORY_SECTION_TEMPLATE = "2.16.840.1.113883.10.20.1.15";
	/**
	 * This constant specifies value representation of social history observation template
	 */
	public static final String SOCIAL_HISTORY_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.33";
	/**
	 * This constant specifies value representation of alert section template 
	 */
	public static final String ALERT_SECTION_TEMPLATE = "2.16.840.1.113883.10.20.1.2";
	/**
	 * This constant specifies value representation of alert observation template
	 */
	public static final String ALERT_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.18";
	/**
	 * This constant specifies value representation of reaction observation template
	 */
	public static final String REACTION_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.54";
	/**
	 * This constant specifies value representation of encounter coverage template
	 */
	public static final String ENCOUNTER_COVERAGE_ID = "2.16.840.1.113883.10.20.1.45";
	/**
	 * This constant specifies value representation of alert status observation template
	 */
	public static final String ALERT_STATUS_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.39";
	/**
	 * This constant specifies value representation of medications section template
	 */
	public static final String MEDICATIONS_SECTION_TEMPLATE = "2.16.840.1.113883.10.20.1.8";
	/**
	 * This constant specifies value representation of medication activity template
	 */
	public static final String MEDICATION_ACTIVITY_TEMPLATE = "2.16.840.1.113883.10.20.1.24";
	/**
	 * This constant specifies value representation of product template
	 */
	public static final String PRODUCT_TEMPLATE = "2.16.840.1.113883.10.20.1.53";
	/**
	 * This constant specifies value representation of medical equipment section template
	 */
	public static final String MEDICAL_EQUIPMENT_SECTION_TEMPLATE = "2.16.840.1.113883.10.20.1.7";
	/**
	 * This constant specifies value representation of vital Signs section template
	 */
	public static final String VITAL_SIGNS_SECTION_TEMPLATE = "2.16.840.1.113883.10.20.1.16";
	/**
	 * This constant specifies value representation of vital Signs organizer template
	 */
	public static final String VITAL_SIGNS_ORGANIZER_TEMPLATE = "2.16.840.1.113883.10.20.1.35";
	/**
	 * This constant specifies value representation of results section template
	 */
	public static final String RESULTS_SECTION_TEMPLATE = "2.16.840.1.113883.10.20.1.14";
	/**
	 * This constant specifies value representation of result observation template
	 */
	public static final String RESULT_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.31";
	/**
	 * This constant specifies value representation of result organizer template
	 */
	public static final String RESULT_ORGANIZER_TEMPLATE = "2.16.840.1.113883.10.20.1.32";
	/**
	 * This constant specifies value representation of supply activity template
	 */
	public static final String SUPPLY_ACTIVITY_TEMPLATE = "2.16.840.1.113883.10.20.1.34";
	/**
	 * This constant specifies value representation of product instance template
	 */
	public static final String PRODUCT_INSTANCE_TEMPLATE = "2.16.840.1.113883.10.20.1.52";
	/**
	 * This constant specifies value representation of plan activity template
	 */
	public static final String PLAN_ACTIVITY_TEMPLATE = "2.16.840.1.113883.10.20.1.25";
	/**
	 * This constant specifies value representation of plan of care section template
	 */
	public static final String PLAN_OF_CARE_SECTION_TEMPLATE = "2.16.840.1.113883.10.20.1.10";
	/**
	 * This constant specifies value representation of encounter activity template
	 */
	public static final String ENCOUNTER_ACTIVITY_TEMPLATE = "2.16.840.1.113883.10.20.1.21";
	/**
	 * This constant specifies value representation of family history observation template
	 */
	public static final String FAMILY_HISTORY_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.22";
	/**
	 * This constant specifies value representation of family history cause of death observation template
	 */
	public static final String FAMILY_HISTORY_CAUSE_OF_DEATH_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.42";
	/**
	 * This constant specifies value representation of  age observation template
	 */
	public static final String AGE_OBSERVATION_TEMPLATE = "2.16.840.1.113883.10.20.1.38";
	/**
	 * This constant specifies value representation of family history organizer template
	 */
	public static final String FAMILY_HISTORY_ORGANIZER_TEMPLATE = "2.16.840.1.113883.10.20.1.23";

}