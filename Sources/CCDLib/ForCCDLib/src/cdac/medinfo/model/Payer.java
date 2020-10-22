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
 * This class specify Payer Section Details. Payers contains data on the patient's payers, whether a third party insurance, self-pay, other payer or guarantor, 
 * or some combination of payers, and is used to define which entity is the responsible fiduciary for the financial aspects of a patient's care. 
 * <p>This class has the following attributes:</p><p>
 * 1.	payerName (String)<br>
 * 2.	policyType (String)<br>
 * 3.   coveredPartyID (String)<br>
 * 4.   authorization (String)<br>
 * 5.   coverageType (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Payer {

	private String payerName ;
	private String policyType ;
	private String coveredPartyID ;
	private String authorization ;
	private String coverageType;
	public Payer() {
		super();
	}

	/**
	 * Returns the Payer Name.
	 * @return the payerName
	 */
	public String getPayerName() {
		return payerName;
	}

	/**
	 * Sets the Payer Name.
	 * @param payerName Specifies the Payer Name.
	 */
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	/**
	 * Returns the Policy Type.
	 * @return the policyType
	 */
	public String getPolicyType() {
		return policyType;
	}

	/**
	 * Sets the Policy Type.
	 * @param policyType Specifies the Policy Type.
	 */
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	/**
	 * Returns the Covered Party ID.
	 * @return the coveredPartyID
	 */
	public String getCoveredPartyID() {
		return coveredPartyID;
	}

	/**
	 * Sets the Covered Party ID.
	 * @param coveredPartyID Specifies the Covered Party ID.
	 */
	public void setCoveredPartyID(String coveredPartyID) {
		this.coveredPartyID = coveredPartyID;
	}

	/**
	 * Returns the Authorization.
	 * @return the authorization
	 */
	public String getAuthorization() {
		return authorization;
	}

	/**
	 * Sets the the Authorization.
	 * @param authorization Specifies the Authorization.
	 */
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	/**
	 * Returns the Coverage Type.
	 * @return the coverageType
	 */
	public String getCoverageType() {
		return coverageType;
	}

	/**
	 * Sets the Coverage Type.
	 * @param coverageType Specifies the Coverage Type.
	 */
	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}
	
	
}
