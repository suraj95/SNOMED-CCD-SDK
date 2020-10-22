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
package cdac.medinfo.model;

/**
 * This class specify Social History Section Details. This section contains data defining the patient's occupational, personal (e.g. lifestyle), social, and environmental history and health risk factors, as well as administrative data such as marital status, race, ethnicity and religious affiliation.
 * <p>This class has the following attributes:</p><p>
 * 1.	Social History Element (String)<br>
 * 2.	Description (String)<br>
 * 3.   Effective Dates (EffectiveDateTime)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class SocialHistory {

	private String socialHistoryElement ;
	private String description ;
	private EffectiveDateTime effectiveDates ;
	
	/**
	 * Constructs an object of Social History.
	 */
	public SocialHistory() {
		super();
	}
	
	/**
	 * Returns the Social History Element.
	 * @return the socialHistoryElement
	 */
	public String getSocialHistoryElement() {
		return socialHistoryElement;
	}
	
	/**
	 * Sets the Social History Element.
	 * @param socialHistoryElement Specifies the Social History Element.
	 */
	public void setSocialHistoryElement(String socialHistoryElement) {
		this.socialHistoryElement = socialHistoryElement;
	}
	/**
	 * Returns the Description of Social History.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Sets the Description of Social History.
	 * @param description Specifies the Description of Social History.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Returns the Effective Dates of Social history Element.
	 * @return the effectiveDates
	 */
	public EffectiveDateTime getEffectiveDates() {
		return effectiveDates;
	}
	/**
	 * Sets the Effective Dates of Social history Element.
	 * @param effectiveDates Specifies the Effective Dates of Social history Element.
	 */
	public void setEffectiveDates(EffectiveDateTime effectiveDates) {
		this.effectiveDates = effectiveDates;
	}
	

}
