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
/**
 * This class specify Care Giver Details 
 */
import java.util.List;
/**
 * This class specify Care Giver Details.
 * <p>This class has the following attributes:</p><p>
 * 1.	Care Giver Name (String)<br>
 * 2.	Care Giver Family (String)<br>
 * 3.	Care Giver Suffix (String)<br>
 * 4.   Care Giver Prefix (String)<br>
 * 5.   Care Giver Gender (String)<br>
 * 6.   Care Giver Birth Time (String)<br>
 * 7.   Care Giver Contact (List<code>&lt;String></code>)<br>
 * 8.   Care Giver Address (List<code>&lt;Address></code>)<br>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class CareGiver {

	private String careGiverName;
	private String careGiverFamily;
	private String careGiverSuffix;
	private String careGiverPrefix;
	private String careGiverGender;
	private String careGiverBirthTime;
	private List<String> careGiverContact;
	private List<Address> careGiverAddress;

	/**
	 * Constructs an object of Care Giver.
	 */
	public CareGiver() {
		super();
	}

	/**
	 * Returns the Care Giver Name.
	 * @return the careGiverName
	 */
	public String getCareGiverName() {
		return careGiverName;
	}

	/**
	 * Sets the Care Giver Name.
	 * @param careGiverName Specifies the Care Giver Name.
	 */
	public void setCareGiverName(String careGiverName) {
		this.careGiverName = careGiverName;
	}

	/**
	 * Returns the Care Giver's Family Name.
	 * @return the careGiverFamily
	 */
	public String getCareGiverFamily() {
		return careGiverFamily;
	}

	/**
	 * Sets the Care Giver's Family Name.
	 * @param careGiverFamily Specifies the Care Giver's Family Name.
	 */
	public void setCareGiverFamily(String careGiverFamily) {
		this.careGiverFamily = careGiverFamily;
	}

	/**
	 * Returns the Care Giver Name Suffix.
	 * @return the careGiverSuffix
	 */
	public String getCareGiverSuffix() {
		return careGiverSuffix;
	}

	/**
	 * Sets the Care Giver Name Suffix.
	 * @param careGiverSuffix Specifies the Care Giver Name Suffix.
	 */
	public void setCareGiverSuffix(String careGiverSuffix) {
		this.careGiverSuffix = careGiverSuffix;
	}

	/**
	 * Returns the the Care Giver Name Prefix.
	 * @return the careGiverPrefix
	 */
	public String getCareGiverPrefix() {
		return careGiverPrefix;
	}

	/**
	 * Sets the the Care Giver Name Prefix.
	 * @param careGiverPrefix Specifies the Care Giver Name Prefix.
	 */
	public void setCareGiverPrefix(String careGiverPrefix) {
		this.careGiverPrefix = careGiverPrefix;
	}

	/**
	 * Returns the Care Giver Gender.
	 * @return the careGiverGender
	 */
	public String getCareGiverGender() {
		return careGiverGender;
	}

	/**
	 * Sets the Care Giver Gender.
	 * @param careGiverGender Specifies the Care Giver Gender.
	 */
	public void setCareGiverGender(String careGiverGender) {
		this.careGiverGender = careGiverGender;
	}

	/**
	 * Returns the Care Giver Birth Time.
	 * @return the careGiverBirthTime
	 */
	public String getCareGiverBirthTime() {
		return careGiverBirthTime;
	}

	/**
	 *  Sets the Care Giver Birth Time.
	 * @param careGiverBirthTime Specifies the Care Giver Birth Time.
	 */
	public void setCareGiverBirthTime(String careGiverBirthTime) {
		this.careGiverBirthTime = careGiverBirthTime;
	}

	/**
	 * Returns the List of Care Giver Contact.
	 * @return the careGiverContact
	 */
	public List<String> getCareGiverContact() {

		if (careGiverContact == null) {
			careGiverContact = new ArrayList<String>();
		}
		return this.careGiverContact;
	}

	/**
	 * Returns the List of Care Giver Address.
	 * @return the careGiverAddress
	 */
	public List<Address> getCareGiverAddress() {
		if (careGiverAddress == null) {
			careGiverAddress = new ArrayList<Address>();
		}
		return this.careGiverAddress;
	}

}
