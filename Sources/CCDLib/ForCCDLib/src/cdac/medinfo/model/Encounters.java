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
 * This class specify Encounter Section Details. This section is used to list and describe any healthcare encounters pertinent to the patient's current health status or historical health history. 
 * <p>This class has the following attributes:</p><p>
 * 1.	encounter (String)<br>
 * 2.	location (List<code>&lt;String></code>)<br>
 * 3.	date (EffectiveDateTime)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Encounters {

	private String encounter ;
	private List<String> location ;
	private EffectiveDateTime date ;
	
	/**
	 * Constructs an object of Encounters.
	 */
	public Encounters() {
		super();
	}
	
	/**
	 * Returns the Encounter Activity.
	 * @return the encounter
	 */
	public String getEncounter() {
		return encounter;
	}
	/**
	 * Sets the Encounter Activity.
	 * @param encounter Specifies the Encounter Activity.
	 */
	public void setEncounter(String encounter) {
		this.encounter = encounter;
	}
	/**
	 * Returns the list of Encounter Locations.
	 * @return the List of location
	 */
	public List<String> getLocation() {
		 if (location == null) {
			 location = new ArrayList<String>();
	        }
		return location;
	}
	
	/**
	 * Returns the date, time, and/or duration of an encounter.
	 * @return the date
	 */
	public EffectiveDateTime getDate() {
		return date;
	}
	
	/**
	 * Sets the Encounter Date.
	 * @param date Specifies the date, time, and/or duration of an encounter.
	 */
	public void setDate(EffectiveDateTime date) {
		this.date = date;
	}
	
	
}
