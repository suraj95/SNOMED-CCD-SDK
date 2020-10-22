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
 * This class specify the details of CCD Header.
 * <p>This class has the following attributes:</p><p>
 * 1.	Patient Details (List<code>&lt;PatientDetails></code>)<br>
 * 2.	Author (List<code>&lt;AuthorInfo></code>)<br>
 * 3.	DocumentationOf (List<code>&lt;DocumentationOfInfo></code>)<br>
 * 4.	Organization (OrganizationInfo)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class CCDHeader {

	private List<PatientDetails> PatientDetails;
	private List<AuthorInfo> Author;
	private List<DocumentationOfInfo> DocumentationOf;
	private OrganizationInfo Organization;
	
	/**
	 * Returns the List of Patient Details.
	 * @return the objPatientDetails
	 */
	public List<PatientDetails> getPatientDetails() {
		if (PatientDetails == null) {
			PatientDetails = new ArrayList<PatientDetails>();
		}
		return PatientDetails;
	}
	
	/**
	 * Returns the List of Author Details.
	 * @return the objAuthor
	 */
	public List<AuthorInfo> getAuthor() {
		if (Author == null) {
			Author = new ArrayList<AuthorInfo>();
		}
		return Author;
	}

	/**
	 * Returns the List of DocumentstionOf Details.
	 * @return the objDocumentationOf
	 */
	public List<DocumentationOfInfo> getDocumentationOf() {
		if (DocumentationOf == null) {
			DocumentationOf = new ArrayList<DocumentationOfInfo>();
		}
		return DocumentationOf;
	}

	/**
	 * Returns the Organization Details.
	 * @return the Organization
	 */
	public OrganizationInfo getOrganization() {
		return Organization;
	}
	/**
	 * Sets the Organization Details.
	 * @param objOrganization Specifies the Organization Details.
	 */
	public void setOrganization(OrganizationInfo objOrganization) {
		this.Organization = objOrganization;
	}
	


}
