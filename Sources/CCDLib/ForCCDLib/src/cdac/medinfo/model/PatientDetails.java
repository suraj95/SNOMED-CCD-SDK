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
 * This class specify Patient Details.  
 * <p>This class has the following attributes:</p><p>
 * 1.	MRN (String)<br>
 * 2.	Name (String)<br>
 * 3.   Family (String)<br>
 * 4.   suffix (String)<br>
 * 5.   prefix (String)<br>
 * 6.   gender (String)<br>
 * 7.   birthTime (String)<br>
 * 8.   birthPlace (String)<br>
 * 9.   maritalStatus (String)<br>
 * 10.  religiousAffiliation (String)<br>
 * 11.  raceCode (String)<br>
 * 12   ethnicGroup (String)<br>
 * 13.  personalAddress (List<code>&lt;Address></code>)<br>
 * 14.  participant (List<code>&lt;Participant></code>)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class PatientDetails {

	private String mrn;
	private String name;
	private String family;
	private String suffix;
	private String prefix;
	private String gender;
	private String birthTime;
	private String birthPlace;
	private String maritalStatus ;
	private String religiousAffiliation;
	private String raceCode;
	private String ethnicGroup;
	private List<Address> personalAddress ;
	private List<Participant> participant ; 
	
	/**
	 * Constructs an object of Patient Details.
	 */
	public PatientDetails() {
		super();
	}

	/**
	 * Returns the Patient Name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Patient Name.
	 * @param name Specifies the Patient Name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the Patient Family Name.
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}

	/**
	 * Sets the Patient Family Name.
	 * @param family Specifies the Patient Family Name.
	 */
	public void setFamily(String family) {
		this.family = family;
	}

	/**
	 * Returns the Patient Name Suffix.
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * Sets the Patient Name Suffix.
	 * @param suffix Specifies the Patient Name Suffix.
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * Returns the Patient Name Prefix.
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * Sets the Patient Name Prefix.
	 * @param prefix Specifies the Patient Name Prefix.
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * Returns the Gender of  Patient.
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the Gender of  Patient.
	 * @param gender Specifies the Gender of  Patient.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns the Birth Time of Patient.
	 * @return the birthTime
	 */
	public String getBirthTime() {
		return birthTime;
	}

	/**
	 * Sets the Birth Time of Patient.
	 * @param birthTime Specifies the Birth Time of Patient.
	 */
	public void setBirthTime(String birthTime) {
		this.birthTime = birthTime;
	}

	/**
	 * Returns list of the Personal Address of Patient.
	 * @return the personalAddress
	 */
	public List<Address> getPersonalAddress() {

		if (personalAddress == null) {
			personalAddress = new ArrayList<Address>();
		}
		return this.personalAddress;
	}

	/**
	 * Returns the list of the Participant.(E.g. Guardian, next of kin etc.)
	 * @return the participant
	 */
	public List<Participant> getParticipant() {
		 if (participant == null) {
			 participant = new ArrayList<Participant>();
	        }
	        
		return this.participant;
	}

	/**
	 * Returns the Marital Status of Patient.
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * Sets the Marital Status of Patient.
	 * @param maritalStatus Specifies the Marital Status of Patient.
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * Returns the Religious Affiliation. 
	 * @return the religiousAffiliation
	 */
	public String getReligiousAffiliation() {
		return religiousAffiliation;
	}

	/**
	 * Sets the Religious Affiliation.
	 * @param religiousAffiliation Specifies the Religious Affiliation.
	 */
	public void setReligiousAffiliation(String religiousAffiliation) {
		this.religiousAffiliation = religiousAffiliation;
	}

	/**
	 * Returns the Race Code.
	 * @return the raceCode
	 */
	public String getRaceCode() {
		return raceCode;
	}

	/**
	 * Sets the Race Code.
	 * @param raceCode Specifies the Race Code.
	 */
	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	/**
	 * Returns the Ethnic Group.
	 * @return the ethnicGroup
	 */
	public String getEthnicGroup() {
		return ethnicGroup;
	}

	/**
	 * Sets the Ethnic Group.
	 * @param ethnicGroup specifies the Ethnic Group.
	 */
	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	/**
	 * Returns the MRN.
	 * @return the mrn
	 */
	public String getMrn() {
		return mrn;
	}

	/**
	 * Sets the MRN.
	 * @param mrn Specifies the MRN.
	 */
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	/**
	 * Returns the Birth Place of Patient.
	 * @return the birthPlace
	 */
	public String getBirthPlace() {
		return birthPlace;
	}

	/**
	 * Sets the Birth Place of Patient.
	 * @param birthPlace Specifies the Birth Place of Patient.
	 */
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
}
