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
 * This class specify Contact Details. A telephone number (voice or fax), e-mail address, or other locator for a resource (information or service) mediated by telecommunication equipment. 
 * The address is specified as a Universal Resource Locator (URL) qualified by time specification and use codes that help in deciding which address to use for a given time and purpose.
 * <p>This class has the following attributes:</p><p>
 * 1.	Useable Period (List<code>&lt;String></code>)<br>
 * 2.	Use (List<code>&lt;String></code>)<br>
 * 3.	Contact Number (String)<br>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Contact {
	
	private List<String> useablePeriod;
	private List<String> use;
	private String contactNumber ;
	private String EmailAddress;
	private String Url;
	
	/**
	 * Get the URL
	 * @return the url
	 */
	public String getUrl() {
		return Url;
	}
	/**
	 * Sets the URL
	 * @param url Specifies the URL
	 */
	public void setUrl(String url) {
		Url = url;
	}
	/**
	 * @param use the use to set
	 */
	public void setUse(List<String> use) {
		this.use = use;
	}
	/**
	 * Returns the List of Usaeable Period Contact Details.
	 * @return the useablePeriod
	 */
	public List<String> getUseablePeriod() {
		if (useablePeriod == null) {
			useablePeriod = new ArrayList<String>();
		}
		return this.useablePeriod;
	}
	/**
	 * Returns the List of Use of Contact Details.
	 * @return the use
	 */
	public List<String> getUse() {
		if (use == null) {
			use = new ArrayList<String>();
		}
		return this.use;
	}
	/**
	 * Returns the Contact Number.
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}
	
	/**
	 * Sets the Contact Number.
	 * @param contactNumber Specifies the Contact Number.
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * Returns the Email Address
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return EmailAddress;
	}
	/**
	 * Sets the Email Address
	 * @param emailAddress Specifies the Email Address
	 */
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	
	
}
