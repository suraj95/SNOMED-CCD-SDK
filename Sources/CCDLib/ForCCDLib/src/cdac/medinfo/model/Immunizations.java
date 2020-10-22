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
 * This class specify Immunizations Section Details. The Immunizations section defines a patient's current immunization status and pertinent immunization history. 
 * <p>This class has the following attributes:</p><p>
 * 1.	Vaccine (String)<br>
 * 2.	date (EffectiveDateTime)<br>
 * 3.   Status (String)<br>
 * 4.   moodOfVaccine (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Immunizations {

	private String vaccine ;
	private EffectiveDateTime date ;
	private String status ;
	private String moodOfVaccine ;
	
	/**
	 * Default constructor of Immunizations.
	 */
	public Immunizations() {
		super();
	}
	
	/**
	 * Returns the Vaccine.
	 * @return the vaccine
	 */
	public String getVaccine() {
		return vaccine;
	}
	
	/**
	 * Sets the Vaccine.
	 * @param vaccine Specifies the Vaccine. 
	 */
	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}
	
	/**
	 * Returns the Date of Vaccination.
	 * @return the date
	 */
	public EffectiveDateTime getDate() {
		return date;
	}
	
	/**
	 * Sets the Date of Vaccination.
	 * @param date Specifies the Date of Vaccination.
	 */
	public void setDate(EffectiveDateTime date) {
		this.date = date;
	}
	
	/**
	 * Returns the Status of Vaccination.
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/** 
	 * Sets the Status of Vaccination.
	 * @param status Specifies the Status of Vaccination.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Returns the Mood Of Vaccine.
	 * @return the moodOfVaccine
	 */
	public String getMoodOfVaccine() {
		return moodOfVaccine;
	}
	
	/**
	 * Sets the Mood Of Vaccine.
	 * @param moodOfVaccine Specifies the Mood Of Vaccine.
	 */
	public void setMoodOfVaccine(String moodOfVaccine) {
		this.moodOfVaccine = moodOfVaccine;
	}

}
