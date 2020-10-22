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
 * This class specify Alerts Section Details. This section is used to list and describe any allergies, adverse reactions, and alerts that are pertinent to the patient's current or past medical history. 
 * At a minimum, currently active and any relevant historical allergies and adverse reactions should be listed. 
 * <p>This class has the following attributes:</p><p>
 * 1.	substance (String)<br>
 * 2.	reaction (String)<br>
 * 3.	status (String)<br>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Alerts {

	private String substance ;
	private String reaction ;
	private String status ;
	
	/**
	 * Constructs an object of Alerts.
	 */
	public Alerts() {
		super();
	}
	/**
	 * Returns the Substance Name.
	 * @return the substance
	 */
	public String getSubstance() {
		return substance;
	}
	
	/**
	 * Sets the Substance Name.
	 * @param substance Specifies the name of Substance or agent that is the cause of the allergy or adverse reaction.
	 */
	public void setSubstance(String substance) {
		this.substance = substance;
	}
	
	/**
	 * Returns the Reaction.
	 * @return the reaction
	 */
	public String getReaction() {
		return reaction;
	}
	
	/**
	 * Sets the Reaction.
	 * @param reaction Specifies an adverse event due to an administered or exposed substance.
	 */
	public void setReaction(String reaction) {
		this.reaction = reaction;
	}
	/**
	 * Returns the Status.
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * Sets the Status.
	 * @param status Specifies an alert status observation.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	

}
