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
 * This class specify section's status code used in clinical document
 */
public enum EnumStatusCode {

	
	/**
	 * This constant specifies value representation of status code completed
	 */
	COMPLETED("completed"),
	/**
	 * This constant specifies value representation of status code aborted
	 */
	ABORTED("aborted"),
	/**
	 * This constant specifies value representation of status code cancelled
	 */
	CANCELLED("cancelled"),
	/**
	 * This constant specifies value representation of status code new
	 */
	NEW("new"),
	/**
	 * This constant specifies value representation of status code active
	 */
	ACTIVE( "active");
	
	;
	private String value;

	EnumStatusCode(String strValue)
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
