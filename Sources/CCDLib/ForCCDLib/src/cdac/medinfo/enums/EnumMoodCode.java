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
 * This class specify section's mood code constants used in clinical document
 */
public enum EnumMoodCode {
	/**
	 *  This constant specifies intend Mood Code
	 */
	INT("INT"),
	/**
	 *  This constant specifies arq request Mood Code
	 */
	ARQ("ARQ"),
	/**
	 *  This constant specifies promise Mood Code
	 */
	PRMS("PRMS"),
	/**
	 *  This constant specifies proposal Mood Code
	 */
	PRP("PRP"),
	/**
	 * This constant specifies goal Mood Code
	 */
	GOL("GOL"),
	/**
	 * This constant specifies request Mood Code
	 */
	RQO("RQO"),
	/**
	 *  This constant specifies event Mood Code
	 */
	EVN("EVN");

	private String value;

	EnumMoodCode(String strValue)
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
