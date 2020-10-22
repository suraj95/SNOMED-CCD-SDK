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
 * This class specify type code used in clinical document
 */
public enum EnumTypeCode {
	
	/**
	 * This constant specifies value representation of Type Code location
	 */
	LOC ("LOC"),
	/**
	 * This constant specifies value representation of Type Code coverage
	 */
	COV("COV"),
	/**
	 * This constant specifies value representation of Type Code precondition
	 */
	PRCN ("PRCN"),
	/**
	 * This constant specifies value representation of Type Code consumable
	 */
	CSM("CSM"),
	/**
	 * This constant specifies value representation of Type Code verifier
	 */
	VRF("VRF"),
	/**
	 * This constant specifies value representation of Type Code indirect participant
	 */
	IND("IND"),
	/**
	 * This constant specifies value representation of Type Code product or device
	 */
	DEV("DEV")
	;
	private String value;

	EnumTypeCode(String strValue)
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
