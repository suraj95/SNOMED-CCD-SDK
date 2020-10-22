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

import java.util.ArrayList;
import java.util.List;

/**
 * This class specify the Organization Part Of Details.
 * <p>This class has the following attributes:</p><p>
 * 1.	id (List<code>&lt;Identifiers></code>)<br>
 * 2.	realm Code (List<code>&lt;String></code>)<br>
 * 3.	effective Time (EffectiveDateTime)<br>
 * 4.	whole Organization (OrganizationInfo)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class OrganizationPartOfInfo {

	 private List<Identifiers> id;
	 private List<String> realmCode;
	 private EffectiveDateTime effectiveTime;
	 private OrganizationInfo wholeOrganization;
	 
	/**
	 * Returns List of the ID
	 * @return the id
	 */
	public List<Identifiers> getId() {
		if (id == null) {
			id = new ArrayList<Identifiers>();
		}
		return id;
	}
	
	/**
	 * Returns the List of Realm Code.
	 * @return the realmCode
	 */
	public List<String> getRealmCode() {
		if (realmCode == null) {
			realmCode = new ArrayList<String>();
		}
		return realmCode;
	}
	
	/**
	 * Returns the Effective time of Organization Part Of.
	 * @return the effectiveTime
	 */
	public EffectiveDateTime getEffectiveTime() {
		return effectiveTime;
	}
	/**
	 * Sets the Effective time of Organization Part Of.
	 * @param effectiveTime Specifies the Effective time of Organization Part Of.
	 */
	public void setEffectiveTime(EffectiveDateTime effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	/**
	 * Returns the Details of Whole Organization.
	 * @return the wholeOrganization
	 */
	public OrganizationInfo getWholeOrganization() {
		return wholeOrganization;
	}
	/**
	 * Sets the Details of Whole Organization.
	 * @param wholeOrganization Specifies the Details of Whole Organization.
	 */
	public void setWholeOrganization(OrganizationInfo wholeOrganization) {
		this.wholeOrganization = wholeOrganization;
	}
	
}
