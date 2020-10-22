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
 * This class specify code system used in clinical document
 */
public enum EnumCodeSystem {

	/**
	 * This constant specifies value representation of LOINC code system
	 */
	OID_LOINC ( "2.16.840.1.113883.6.1"),
	/**
	 * This constant specifies value representation of CPT-4 system
	 */
	OID_CPT_4( "2.16.840.1.113883.6.12"),
	/**
	 * This constant specifies value representation of NDC system
	 */
	OID_NDC( "2.16.840.1.113883.6.69"),
	/**
	 * This constant specifies value representation of HL7 code system
	 */
	OID_HL7 ("2.16.840.1.113883.19.5") ,
	/**
	 * This constant specifies value representation of ACT code system
	 */
	OID_ACTCODE ("2.16.840.1.113883.5.4") ,
	/**
	 * This constant specifies value representation of HL7 code system
	 */
	OID_HL7_ACTCODE  ("2.16.840.1.113883.19.5.4"),
	/**
	 * This constant specifies value representation of SNOMED-CT code system
	 */
	OID_SNOMED_CT ("2.16.840.1.113883.6.96"),
	/**
	 * This constant specifies value representation of oid for administrative gender code system
	 */
	OID_ADMINISTRATIVE_GENDER ("2.16.840.1.113883.5.1"),
	/**
	 * This constant specifies value representation of medications route code system
	 */
	MEDICATIONS_ROUTE_CODE_SYSTEM ("RouteOfAdministration"),
	/**
	 * This constant specifies value representation of immunization material code system
	 */
	IMMUNIZATION_MATERIAL_CODE_SYSTEM ("2.16.840.1.113883.6.59"),
	/**
	 * This constant specifies value representation of RXNORM code system
	 */
	OID_RXNORM("2.16.840.1.113883.6.88"),
	/**
	 * This constant specifies value representation of observation interpretation code system
	 */
	OID_OBSERVATION_INTERPRETATION_CODE_SYSTEM ("2.16.840.1.113883.5.83"),
	/**
	 * This constant specifies value representation of role code system
	 */
	OID_ROLE_CODE ("2.16.840.1.113883.5.111"),
	/**
	 * This constant specifies value representation of HL7 route of administration code system
	 */
	OID_HL7_ROUTE_OF_ADMINISTRATION ("2.16.840.1.113883.5.112"),
	/**
	 * This constant specifies value representation of participation function code system
	 */
	OID_PARTICIPATION_FUNCTION ("2.16.840.1.113883.5.88"),
	/**
	 * This constant specifies value representation of confidentiality code system
	 */
	OID_CONFIDENTIALITY ("2.16.840.1.113883.5.25") ;
	
	
	private String value;

	EnumCodeSystem(String strValue)
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
