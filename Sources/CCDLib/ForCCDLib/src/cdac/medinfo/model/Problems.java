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
 * This class specify Problems Section Details. This section lists and describes all relevant clinical problems at the time the summary is generated. 
 * At a minimum, all pertinent current and historical problems should be listed. 
 * <p>This class has the following attributes:</p><p>
 * 1.	Condition (String)<br>
 * 2.	Effective Dates (EffectiveDateTime)<br>
 * 3.   Condition Status (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Problems {

	
	private String condition ;
	private EffectiveDateTime effectiveDates ;
	private String conditionStatus ;
	
	/**
	 * Constructs an object of Problems.
	 */
	public Problems() {
		super();
	}
	
	/**
	 * Return the Condition.
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * Sets the Condition.
	 * @param condition Specifies the Condition.
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	/**
	 * Returns the Effective Dates of Problem or Condition.
	 * @return the effectiveDates
	 */
	public EffectiveDateTime getEffectiveDates() {
		return effectiveDates;
	}
	
	/**
	 * Sets Effective Dates of Problem or Condition.
	 * @param effectiveDates Specifies the timing of the concern (e.g. the interval of time for which the problem is a concern).
	 */
	public void setEffectiveDates(EffectiveDateTime effectiveDates) {
		this.effectiveDates = effectiveDates;
	}
	/**
	 * Returns the Condition Status.
	 * @return the conditionStatus
	 */
	public String getConditionStatus() {
		return conditionStatus;
	}
	/**
	 * Sets the Condition Status.
	 * @param conditionStatus Specifies the Condition Status.
	 */
	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}

	
	}
