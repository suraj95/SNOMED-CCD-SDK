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
 * This class specify class codes used in clinical document
 */
public enum EnumClassCode {
	
	/**
	 * This constant specifies value representation of Class Code Substance administrator
	 */
	SBADM ("SBADM"),
	/**
	 * This constant specifies value representation of Class Code observation
	 */
	OBS ("OBS"),
	/**
	 * This constant specifies value representation of Class Code event
	 */
	EVN ("EVN"),
	/**
	 * This constant specifies value representation of Class Code encounter
	 */
	ENC ("ENC"),
	/**
	 * This constant specifies value representation of Class Code procedure
	 */
	PROC ("PROC"),
	/**
	 * This constant specifies value representation of Class Code role
	 */
	ROL ("ROL"),
	/**
	 * This constant specifies value representation of Class Code manufactured
	 */
	MANU ("MANU"),
	/**
	 * This constant specifies value representation of Class Code care provision
	 */
	PCPR ("PCPR"),
	/**
	 * This constant specifies value representation of Class Code guardian
	 */
	GUAR ("GUAR"),
	/**
	 * This constant specifies value representation of Class Code service delivery location
	 */
	SDLOC ("SDLOC"),
	/**
	 * This constant specifies value representation of Class Code place
	 */
	PLC ("PLC"),
	/**
	 * This constant specifies value representation of Class Code next of kin
	 */
	NOK ("NOK"),
	/**
	 * This constant specifies value representation of Class Code emergency contact
	 */
	ECON ("ECON"),
	/**
	 * This constant specifies value representation of Class Code caregiver
	 */
	CAREGIVER ("CAREGIVER"),
	/**
	 * This constant specifies value representation of Class Code manufactured material
	 */
	MMAT ("MMAT");
	
	private String value;

	EnumClassCode(String strValue)
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
