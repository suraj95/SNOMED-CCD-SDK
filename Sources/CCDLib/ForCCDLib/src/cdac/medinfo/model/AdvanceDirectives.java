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
 * This class specify Advance Directives Section Details. This section contains data defining the patient's advance directives and any reference to supporting documentation. 
 * <p>This class has the following attributes:</p><p>
 * 1.	directive (String)<br>
 * 2.	description (String)<br>
 * 3.	verification (String)<br>
 * 4.	verification Date (String)<br>
 * 5.	supporting Document (String)<br>
 * 6.	media Type (String)<br>
 * 7.	supporting Document Link (String)<br>
 * 8.	effective Times (EffectiveDateTime)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class AdvanceDirectives {

	private String directive ;
	private String description ;
	private String verification ;
	private EffectiveDateTime effectiveTimes ;
	private String verificationDate;
	private String supportingDocument ;
	private String mediaType;
	private String supportingDocumentLink;
	
	/**
	 * Constructs an object of Advance Directives.
	 */
	public AdvanceDirectives() {
		super();
	}
	/**
	 * Returns the Directive.
	 * @return the directive
	 */
	public String getDirective() {
		return directive;
	}
	/**
	 * Sets the Directive.
	 * @param directive Specifies the advance directive name.
	 */
	public void setDirective(String directive) {
		this.directive = directive;
	}
	/**
	 * Returns the Description.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Sets the Description.
	 * @param description Specifies the advance directive description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Returns the Verification.
	 * @return the verification
	 */
	public String getVerification() {
		return verification;
	}
	/**
	 * Sets the Verification.
	 * @param verification Specifies the verification of an advance directive.
	 */
	public void setVerification(String verification) {
		this.verification = verification;
	}
	/**
	 * Returns the Verification Date.
	 * @return the verificationDate 
	 */
	public String getVerificationDate() {
		return verificationDate;
	}
	/**
	 * Sets the Verification Date.
	 * @param verificationDate Specifies the verification date of an advance directive.
	 */
	public void setVerificationDate(String verificationDate) {
		this.verificationDate = verificationDate;
	}
	/**
	 * Returns the Supporting Document.
	 * @return the supportingDocument
	 */
	public String getSupportingDocument() {
		return supportingDocument;
	}
	/**
	 * Sets the Supporting Document.
	 * @param supportingDocument Specifies the supporting document name of advance directive.
	 */
	public void setSupportingDocument(String supportingDocument) {
		this.supportingDocument = supportingDocument;
	}
	/**
	 * Return the Media Type.
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}
	
	/**
	 * Sets the Media Type.
	 * @param mediaType Specifies the media type of supporting document
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	/**
	 * Returns the Supporting Document Link.
	 * @return the supportingDocumentLink
	 */
	public String getSupportingDocumentLink() {
		return supportingDocumentLink;
	}
	
	/**
	 * Sets the Supporting Document Link.
	 * @param supportingDocumentLink Specifies the link of supporting document for advance directive
	 */
	public void setSupportingDocumentLink(String supportingDocumentLink) {
		this.supportingDocumentLink = supportingDocumentLink;
	}
	/**
	 * Returns the Effective Times.
	 * @return the effectiveTimes
	 */
	public EffectiveDateTime getEffectiveTimes() {
		return effectiveTimes;
	}
	/**
	 * Sets the Effective Times.
	 * @param effectiveTimes Specifies the effective time of the advance directive.
	 */
	public void setEffectiveTimes(EffectiveDateTime effectiveTimes) {
		this.effectiveTimes = effectiveTimes;
	}

}
