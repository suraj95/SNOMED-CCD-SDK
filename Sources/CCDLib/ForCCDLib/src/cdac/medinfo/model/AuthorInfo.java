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
 * This class specify the details of humans and/or machines that authored the document.
 * <p>This class has the following attributes:</p><p>
 * 1.	id (List<code>&lt;Identifiers></code>)<br>
 * 2.	function Code (String)<br>
 * 3.	time (EffectiveDateTime)<br>
 * 4.	name (String)<br>
 * 5.	family (String)<br>
 * 6.	suffix (String)<br>
 * 7.	prefix (String)<br>
 * 8.	Organization (OrganizationInfo)<br>
 * 8    Authoring Device (AuthoringDeviceInfo)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class AuthorInfo {
	
	 private List<Identifiers> id;
	 private String functionCode;
	 private String time;
	 private String name;
	 private String family;
	 private String suffix;
	 private String prefix;
	 private OrganizationInfo Organization;
	 private AuthoringDeviceInfo authoringDevice;
	
   /**
    *  Returns the List of ID of Assigned Author.
	 * @return the id
	 */
	public List<Identifiers> getId() {
		if (id == null) {
			id = new ArrayList<Identifiers>();
		}
		return id;
	}
	 /**
	  * Returns the Function Code of Author.
	 * @return the functionCode
	 */
	public String getFunctionCode() {
		return functionCode;
	}
	/**
	 * Sets the Function Code of Author.
	 * @param functionCode Specifies addition detail about the function of the Author (e.g. scrub nurse, third assistant).
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	/**
	 * Returns the time during which the Author is involved in the activity.
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * Sets the time during which the Author is involved in the activity.
	 * @param time Specifies the time during which the Author is involved in the activity.
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * Returns the Name or Given Name of Author.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the Name or Given Name of Author.
	 * @param name Specifies the Name or Given Name of Author.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns the Family Name of Author.
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}
	/**
	 * Sets the Family Name of Author.
	 * @param family Specifies the Family Name of Author.
	 */
	public void setFamily(String family) {
		this.family = family;
	}
	/**
	 * Returns the Suffix of Author.
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}
	/**
	 * Sets the Suffix of Author.
	 * @param suffix Specifies the Suffix of Author.
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	/**
	 * Returns the Prefix of Author.
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * Sets the Prefix of Author.
	 * @param prefix Specifies the Prefix of Author.
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * Returns the Representation Organization of Author.
	 * @return the Organization
	 */
	public OrganizationInfo getOrganization() {
		return Organization;
	}
	/**
	 * Sets the Representation Organization of Author.
	 * @param objOrganization Specifies the Representation Organization of Author.
	 */
	public void setObjOrganization(OrganizationInfo objOrganization) {
		this.Organization = objOrganization;
	}
	/**
	 * Returns the Authoring Device Details.
	 * @return the authoringDevice
	 */
	public AuthoringDeviceInfo getAuthoringDevice() {
		return authoringDevice;
	}
	/**
	 * Sets the Authoring Device Details.
	 * @param authoringDevice Specifies the Authoring Device Details.
	 */
	public void setAuthoringDevice(AuthoringDeviceInfo authoringDevice) {
		this.authoringDevice = authoringDevice;
	}
}
