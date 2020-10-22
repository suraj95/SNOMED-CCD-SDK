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
 * This class specify the Organization Details.
 * <p>This class has the following attributes:</p><p>
 * 1.	Organization Name (String)<br>
 * 2.	id (List<code>&lt;Identifiers></code>)<br>
 * 3.	telecom (List<code>&lt;Contact></code>)<br>
 * 4.	Address (List<code>&lt;Address></code>)<br>
 * 5.	standard Industry Class Code (String)<br>
 * 6.	as Organization Part Of Info (OrganizationPartOfInfo)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class OrganizationInfo {
	
	private String OrganizationName ;
	private List<Identifiers> id;
	private List<Contact> telecom;
	private List<Address> Address;
	private String standardIndustryClassCode;
	private OrganizationPartOfInfo asOrganizationPartOfInfo;

	
	
	/**
	 * Returns theList of Id.
	 * @return the id
	 */
	public List<Identifiers> getId() {
		if (id == null) {
			id = new ArrayList<Identifiers>();
		}
		return id;
	}
	
	/**
	 * Returns the List of TeleCommunication Details.
	 * @return the telecom
	 */
	public List<Contact> getTelecom() {
		if (telecom == null) {
			telecom = new ArrayList<Contact>();
		}
		return telecom;
	}

	/**
	 * Returns the Address Details.
	 * @return the addr
	 */
	public List<Address> getAddr() {
		if (Address == null) {
			Address = new ArrayList<Address>();
		}
		return Address;
	}

	/**
	 * Returns the Standard Industry Class Code of Organization.
	 * @return the standardIndustryClassCode
	 */
	public String getStandardIndustryClassCode() {
		return standardIndustryClassCode;
	}

	/**
	 * Sets the Standard Industry Class Code of Organization.
	 * @param standardIndustryClassCode Specifies the Standard Industry Class Code of Organization.
	 */
	public void setStandardIndustryClassCode(String standardIndustryClassCode) {
		this.standardIndustryClassCode = standardIndustryClassCode;
	}

	/**
	 * Returns the Details of organization part of.
	 * @return the asOrganizationPartOf
	 */
	public OrganizationPartOfInfo getAsOrganizationPartOfInfo() {
		return asOrganizationPartOfInfo;
	}

	/**
	 * Sets the Details of organization part of.
	 * @param asOrganizationPartOfInfo Specifies the Details of organization part of.
	 */
	public void setAsOrganizationPartOfInfo(OrganizationPartOfInfo asOrganizationPartOfInfo) {
		this.asOrganizationPartOfInfo = asOrganizationPartOfInfo;
	}


	
	/**
	 * Returns the Organization Name.
	 * @return the OrganizationName
	 */
	public String getOrganizationName() {
		return OrganizationName;
	}

	/**
	 * Sets the Organization Name.
	 * @param organizationName Specifies the Organization Name.
	 */
	public void setOrganizationName(String organizationName) {
		OrganizationName = organizationName;
	}
	

}
