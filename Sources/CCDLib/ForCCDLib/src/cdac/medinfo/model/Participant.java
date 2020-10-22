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
 * This class specify Participant Details. 
 * <p>This class has the following attributes:</p><p>
 * 1.	participantName (String)<br>
 * 2.	participantFamily (String)<br>
 * 3.   participantSuffix (EffectiveDateTime)<br>
 * 4.   participantPrefix (String)<br>
 * 5.   participantAddress (List<code>&lt;Address></code>)<br>
 * 6.   participantContact (List<code>&lt;Contact></code>)<br>
 * 7.   isNOK (boolean)<br>
 * 8.   isGaurdian (boolean)<br>
 * 9.   isEmergencyContactPresent (boolean)<br>
 * 10.  isCareGiverPresent (boolean)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Participant {

	private String participantName;
	private String participantFamily;
	private String participantSuffix;
	private String participantPrefix;
	private String participantOrganisation;
	private String participantRelation;
	private List<Address> participantAddress;
	private List<Contact> participantContact;
	private boolean isNOK= false;
	private boolean isGaurdian= false;
	private boolean isEmergencyContactPresent = false;
	private boolean isCareGiverPresent = false;

	public Participant(){

	}

	/**
	 * Returns the Participant Name.
	 * @return the participantName
	 */
	public String getParticipantName() {
		return participantName;
	}

	/**
	 * Sets the Participant Name.
	 * @param participantName Specifies the Participant Name.
	 */
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	/**
	 * Returns the Participant Family Name.
	 * @return the participantFamily
	 */
	public String getParticipantFamily() {
		return participantFamily;
	}

	/**
	 * Sets the Participant Family Name.
	 * @param participantFamily Specifies the Participant Family Name.
	 */
	public void setParticipantFamily(String participantFamily) {
		this.participantFamily = participantFamily;
	}

	/**
	 * Returns the Participant Name Suffix.
	 * @return the participantSuffix
	 */
	public String getParticipantSuffix() {
		return participantSuffix;
	}

	/**
	 * Sets the Participant Name Suffix.
	 * @param participantSuffix Specifies the Participant Name Suffix.
	 */
	public void setParticipantSuffix(String participantSuffix) {
		this.participantSuffix = participantSuffix;
	}

	/**
	 * Returns the Participant Name Prefix.
	 * @return the participantPrefix
	 */
	public String getParticipantPrefix() {
		return participantPrefix;
	}

	/**
	 * Sets the Participant Name Prefix.
	 * @param participantPrefix Specifies the Participant Name Prefix.
	 */
	public void setParticipantPrefix(String participantPrefix) {
		this.participantPrefix = participantPrefix;
	}

	/**
	 * Returns the list od the Participant Address.
	 * @return the participantAddress
	 */
	public List<Address> getParticipantAddress() {
		if (participantAddress == null) {
			participantAddress = new ArrayList<Address>();
		}
		return this.participantAddress;
	}

	/**
	 * Returns the list of Participant Contact information.
	 * @return the participantContact
	 */
	public List<Contact> getParticipantContact() {
		if (participantContact == null) {
			participantContact = new ArrayList<Contact>();
		}
		return this.participantContact;
	}

	/**
	 * Returns the Participant Organization.
	 * @return the participantOrganisation
	 */
	public String getParticipantOrganisation() {
		return participantOrganisation;
	}

	/**
	 * Sets the Participant Organization.
	 * @param participantOrganisation Specifies the Participant Organization.
	 */
	public void setParticipantOrganisation(String participantOrganisation) {
		this.participantOrganisation = participantOrganisation;
	}

	/**
	 * Returns the flag value(true or false) of next of kin.
	 * @return the isNOK
	 */
	public boolean isNOK() {
		return isNOK;
	}

	/**
	 * Sets the flag value(true or false) of next of kin.
	 * @param isNOK Specifies the value(true or false) of next of kin.
	 */
	public void setNOK(boolean isNOK) {
		this.isNOK = isNOK;
	}

	/**
	 * Returns the flag value( true or false) of Gaurdian.
	 * @return the isGaurdian
	 */
	public boolean isGaurdian() {
		return isGaurdian;
	}

	/**
	 * Sets the flag value( true or false) of Gaurdian.
	 * @param isGaurdian Specifies the flag value( true or false) of Gaurdian.
	 */
	public void setGaurdian(boolean isGaurdian) {
		this.isGaurdian = isGaurdian;
	}

	/**
	 * Returns the flag value( true or false) of Emergency Contact Present.
	 * @return the isEmergencyContactPresent
	 */
	public boolean isEmergencyContactPresent() {
		return isEmergencyContactPresent;
	}

	/**
	 * Sets the flag value( true or false) of Emergency Contact Present.
	 * @param isEmergencyContactPresent Specifies the flag value( true or false) of Emergency Contact Present.
	 */
	public void setEmergencyContactPresent(boolean isEmergencyContactPresent) {
		this.isEmergencyContactPresent = isEmergencyContactPresent;
	}

	/**
	 * Sets the Participant Relation.
	 *	@return the participantRelation Specifies the Participant Relation.
	 */
	public String getParticipantRelation() {
		return participantRelation;
	}

	/**
	 * Sets the Participant Relation.
	 * @param participantRelation the participantRelation to set
	 */
	public void setParticipantRelation(String participantRelation) {
		this.participantRelation = participantRelation;
	}

	/**
	 * Returns the flag value( true or false) of Care Giver Present.
	 * @return the isCareGiverPresent
	 */
	public boolean isCareGiverPresent() {
		return isCareGiverPresent;
	}

	/**
	 * Sets the flag value( true or false) of Care Giver Present.
	 * @param isCareGiverPresent Specifies the flag value( true or false) of Care Giver Present.
	 */
	public void setCareGiverPresent(boolean isCareGiverPresent) {
		this.isCareGiverPresent = isCareGiverPresent;
	}
}
