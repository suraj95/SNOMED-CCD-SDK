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
 * This class specify Procedures Section Details. This section defines all interventional, surgical, diagnostic, or therapeutic procedures or treatments pertinent to the patient historically at the time the document is generated. 
 * The section may contain all procedures for the period of time being summarized, but should include notable procedures. 
 * <p>This class has the following attributes:</p><p>
 * 1.	Procedure (String)<br>
 * 2.	Date (String)<br>
 * 3.   Observe (String)<br>
 * 4.   Laterality (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Procedures {

	private String procedure ;
	private String date ;
	private String observe ;
	private String laterality;
	private String procedureActivityName;
	private String procedureStatusCode;
	
	/**
	 * Constructs an object of Procedures.
	 */
	public Procedures() {
		super();
	}
	
	/**
	 * Returns the Observe.
	 * @return the procedure
	 */
	public String getObserve() {
		return observe;
	}
	
	/**
	 * Sets the Observe.
	 * @param observe Specifies the Observe.
	 */
	public void setObserve(String observe) {
		this.observe = observe;
	}
	
	/**
	 * Returns the Procedure Activity.
	 * @return the procedure
	 */
	public String getProcedure() {
		return procedure;
	}
	
	/**
	 * Sets the Procedure Activity.
	 * @param procedure Specifies the Procedure Activity.
	 */
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	
	/**
	 * Returns the Date of Procedure Performed.
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Sets the Date of Procedure Performed.
	 * @param date Specifies the Date of Procedure Performed.
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Sets the Laterality.
	 * @param laterality Specifies the Laterality.
	 */
	public void setLaterality(String laterality)
	{
		this.laterality = laterality;
	}
	/**
	 * Returns the Laterality.
	 * @return the laterality
	 */
	public String getLaterality()
	{
		return laterality;
	}

	/**
	 * Returns the Procedure Activity Name.
	 * @return the procedureActivityName
	 */
	public String getProcedureActivityName() {
		return procedureActivityName;
	}

	/**
	 * Sets the Procedure Activity Name.
	 * @param procedureActivityName Specifies the Procedure Activity Name (E.g. "Act", "Observation", "Procedure").
	 */
	public void setProcedureActivityName(String procedureActivityName) {
		this.procedureActivityName = procedureActivityName;
	}

	/**
	 * Returns the Status Code of Procedure.
	 * @return the procedureStatusCode
	 */
	public String getProcedureStatusCode() {
		return procedureStatusCode;
	}

	/**
	 * Sets the Status Code of Procedure.
	 * @param procedureStatusCode Specifies the Status Code of Procedure.
	 */
	public void setProcedureStatusCode(String procedureStatusCode) {
		this.procedureStatusCode = procedureStatusCode;
	}
}
