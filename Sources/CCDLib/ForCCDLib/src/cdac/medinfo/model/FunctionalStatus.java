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
 * This class specify Functional Status Section Details. Functional Status describes the patient's status of normal functioning at the time the Care Record was created. 
 * <p>This class has the following attributes:</p><p>
 * 1.	Functional Condition (String)<br>
 * 2.	Effective Dates (EffectiveDateTime)<br>
 * 3.	Condition Status (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class FunctionalStatus {

	
	private String functionalCondition ;
	private EffectiveDateTime effectiveDates ;
	private String conditionStatus ;
	
	/**
	 * Default constructor of Functional Status.
	 */
	public FunctionalStatus() {
		super();
	}
	
	/**
	 * Returns the Functional Condition.
	 * @return the functionalCondition
	 */
	public String getFunctionalCondition() {
		return functionalCondition;				
	}

	/**
	 * Sets the Functional Condition.
	 * @param functionalCondition Specifies the Functional Condition.
	 */
	public void setFunctionalCondition(String functionalCondition) {
		this.functionalCondition = functionalCondition;
	}
	
	/**
	 * Returns the Effective dates Functional Condition.
	 * @return the effectiveDates
	 */
	public EffectiveDateTime getEffectiveDates() {
		return effectiveDates;
	}
	
	/**
	 * Sets the Effective dates Functional Condition.
	 * @param effectiveDates Specifies the Effective dates Functional Condition.
	 */
	public void setEffectiveDates(EffectiveDateTime effectiveDates) {
		this.effectiveDates = effectiveDates;
	}
	
	/**
	 * Returns the Status of Functional Condition.
	 * @return the conditionStatus
	 */
	public String getConditionStatus() {
		return conditionStatus;
	}
	/**
	 * Sets the Status of Functional Condition.
	 * @param conditionStatus Specifies the Status of Functional Condition.
	 */
	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}

	
	}


