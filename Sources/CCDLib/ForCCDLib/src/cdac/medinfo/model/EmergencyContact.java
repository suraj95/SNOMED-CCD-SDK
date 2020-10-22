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
 * This class specify Emergency Contact Details. A contact specifically designated to be used for emergencies. This is the first choice in emergencies, independent of any other use codes. 
 * <p>This class has the following attributes:</p><p>
 * 1.	Emergency Contact Name (String)<br>
 * 2.	Emergency Contact Family (String)<br>
 * 3.	Emergency Contact Suffix (String)<br>
 * 4.   Emergency Contact Prefix (String)<br>
 * 5.   Emergency Contact Gender (String)<br>
 * 6.   Emergency Contact Birth Time (String)<br>
 * 7.   Emergency Contact Number (List<code>&lt;String></code>)
 * 8.   Emergency Contact Address (List<code>&lt;Address></code>)
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class EmergencyContact {

	private String emergencyContactName;
	private String emergencyContactFamily;
	private String emergencyContactSuffix;
	private String emergencyContactPrefix;
	private String emergencyContactGender;
	private String emergencyContactBirthTime;
	private List<String> emergencyContactNumber;
	private List<Address> emergencyContactAddress;
	
	/**
	 * Default constructor of Emergency Contact.
	 */
	public EmergencyContact() {
		super();
	}

	/**
	 * Returns the Emergency Contact Person Name.
	 * @return the emergencyContactName
	 */
	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	/**
	 * Sets the Emergency Contact Person Name.
	 * @param emergencyContactName Specifies the Emergency Contact Person Name.
	 */
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	/**
	 * Returns the Emergency Contact Family Name.
	 * @return the emergencyContactFamily
	 */
	public String getEmergencyContactFamily() {
		return emergencyContactFamily;
	}

	/**
	 * Sets the Emergency Contact Family Name.
	 * @param emergencyContactFamily Specifies the Emergency Contact Family Name.
	 */
	public void setEmergencyContactFamily(String emergencyContactFamily) {
		this.emergencyContactFamily = emergencyContactFamily;
	}

	/**
	 * Returns the Emergency Contact Name Suffix.
	 * @return the emergencyContactSuffix
	 */
	public String getEmergencyContactSuffix() {
		return emergencyContactSuffix;
	}

	/**
	 * Sets the Emergency Contact Name Suffix.
	 * @param emergencyContactSuffix Specifies the Emergency Contact Name Suffix.
	 */
	public void setEmergencyContactSuffix(String emergencyContactSuffix) {
		this.emergencyContactSuffix = emergencyContactSuffix;
	}

	/**
	 * Returns the Emergency Contact Name Prefix.
	 * @return the emergencyContactPrefix
	 */
	public String getEmergencyContactPrefix() {
		return emergencyContactPrefix;
	}

	/**
	 * Sets the Emergency Contact Name Prefix.
	 * @param emergencyContactPrefix Specifies the Emergency Contact Name Prefix.
	 */
	public void setEmergencyContactPrefix(String emergencyContactPrefix) {
		this.emergencyContactPrefix = emergencyContactPrefix;
	}

	/**
	 * Returns the Emergency Contact Person's Gender.
	 * @return the emergencyContactGender
	 */
	public String getEmergencyContactGender() {
		return emergencyContactGender;
	}

	/**
	 * Sets the Emergency Contact Person's Gender.
	 * @param emergencyContactGender Specifies the Emergency Contact Person's Gender.
	 */
	public void setEmergencyContactGender(String emergencyContactGender) {
		this.emergencyContactGender = emergencyContactGender;
	}

	/**
	 * Returns the Emergency Contact Person's Birth Time.
	 * @return the emergencyContactBirthTime
	 */
	public String getEmergencyContactBirthTime() {
		return emergencyContactBirthTime;
	}

	/**
	 * Sets the Emergency Contact Person's Birth Time.
	 * @param emergencyContactBirthTime Specifies the Emergency Contact Person's Birth Time.
	 */
	public void setEmergencyContactBirthTime(String emergencyContactBirthTime) {
		this.emergencyContactBirthTime = emergencyContactBirthTime;
	}

	/**
	 * Returns list of the Emergency Contact Number.
	 * @return the emergencyContactNumber
	 */
	public List<String> getEmergencyContactNumber() {
		 if (emergencyContactNumber == null) {
			 emergencyContactNumber = new ArrayList<String>();
	        }
	        return this.emergencyContactNumber;
	}

	/**
	 * Returns list of the Emergency Contact Address.
	 * @return the emergencyContactAddress
	 */
	public List<Address> getEmergencyContactAddress() {
		 if (emergencyContactAddress == null) {
			 emergencyContactAddress = new ArrayList<Address>();
	        }
		return this.emergencyContactAddress;
	}

}
