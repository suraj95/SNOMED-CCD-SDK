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
 * This class specify Family History Section Details. This section contains data defining the patient's genetic relatives in terms of possible or relevant health risk factors that have a potential impact on the patient's healthcare risk profile. 
 * <p>This class has the following attributes:</p><p>
 * 1.	Relation With Patient (String)<br>
 * 2.	Vital Status (String)<br>
 * 3.	Diagnosis (String)<br>
 * 4.   Age At Onset (String)<br>
 * 5.   Gender (String)<br>
 * 6.	Birth Time (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class FamilyHistory {

	private String relationWithPatient;
	private String vitalStatus; 
	private String diagnosis ;
	private String ageAtOnset ;
	private String gender;
	private String birthTime;
	
	/**
	 * Default constructor of Family History.
	 */
	public FamilyHistory() {
		super();
	}
	
	/**
	 * Returns the Relation with Patient.
	 * @return the relationWithPatient
	 */
	public String getRelationWithPatient() {
		return relationWithPatient;
	}
	
	/**
	 * Sets the Relation with Patient.
	 * @param relationWithPatient Specifies the Relation with Patient.
	 */
	public void setRelationWithPatient(String relationWithPatient) {
		this.relationWithPatient=relationWithPatient;
	}
	
	/**
	 * Returns the Vital Status of Patient's Relative.
	 * @return the vitalStatus 
	 */
	public String getVitalStatus() {
		return vitalStatus;
	}
	
	/**
	 * Sets the Vital Status of Patient's Relative.
	 * @param vitalStatus Specifies the the Vital Status of Patient's Relative. E.g. if Relative is alive or deceased
	 */
	public void setVitalStatus(String vitalStatus) {
		this.vitalStatus = vitalStatus;
	}
	
	/**
	 * Returns the Diagnosis.  
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}
	
	/**
	 * Sets the Diagnosis.
	 * @param diagnosis Specifies the diagnosis
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	/**
	 * Returns the Age At Onset
	 * @return the ageAtOnset
	 */
	public String getAgeAtOnset() {
		return ageAtOnset;
	}
	
	/**
	 * Sets the Age At Onset
	 * @param ageAtOnset Specifies the age at which the disease was diagnosed
	 */
	public void setAgeAtOnset(String ageAtOnset) {
		this.ageAtOnset = ageAtOnset;
	}
	
	/**
	 * Returns the Gender.
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Sets the Gender.
	 * @param gender Specifies the gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns the Birth Time.
	 * @return the birthTime
	 */
	public String getBirthTime() {
		return birthTime;
	}
	
	/**
	 * Sets the Birth Time.
	 * @param birthTime Specifies the birth time 
	 */
	public void setBirthTime(String birthTime) {
		this.birthTime = birthTime;
	}

}
