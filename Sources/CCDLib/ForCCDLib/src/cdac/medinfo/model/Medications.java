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
 * This class specify Medications Section Details. The Medications section defines a patient's current medications and pertinent medication history. 
 * At a minimum, the currently active medications should be listed, with an entire medication history as an option, particularly when the summary document is used for comprehensive data export. 
 * <p>This class has the following attributes:</p><p>
 * 1.	Medication (String)<br>
 * 2.	Instructions (String)<br>
 * 3.   Date (EffectiveDateTime)<br>
 * 4.   Status (String)<br>
 * 5.   Dose Quantity (String)<br>
 * 6.   Period Value (String)<br>
 * 7.   Period Unit (String)<br>
 * 8.   Criterion (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Medications {

	private String medication ;
	private String instructions ;
	private EffectiveDateTime Date ;
	private String status ;
	private String doseQuantity;
	private String periodValue;
	private String periodUnit;
	private String criterion ;
	
	/**
	 * Constructs an object of Medications.
	 */
	public Medications() {
		super();
	}
	
	/**
	 * Returns the Medication Information.
	 * @return the medication
	 */
	public String getMedication() {
		return medication;
	}
	
	/**
	 * Sets the Medication Information.
	 * @param medication Specifies the Medication Information.
	 */
	public void setMedication(String medication) {
		this.medication = medication;
	}
	
	/**
	 * Returns the Instructions.
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}
	
	/**
	 * Sets the Instructions.
	 * @param instructions Specifies the Instructions.
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	/**
	 * Returns the Date of Medication.
	 * @return the Date
	 */
	public EffectiveDateTime getDate() {
		return Date;
	}
	
	/**
	 * Sets the duration of Medication.
	 * @param Date Specifies the duration of Medication.
	 */
	public void setDate(EffectiveDateTime Date) {
		this.Date = Date;
	}
	
	/**
	 * Returns the Status of Medication.
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the Status of Medication.
	 * @param status Specification the Status of Medication.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Returns the Dose Quantity.
	 * @return the doseQuantity
	 */
	public String getDoseQuantity() {
		return doseQuantity;
	}
	
	/**
	 * Sets the Dose Quantity.
	 * @param doseQuantity Specifies the Dose Quantity.
	 */
	public void setDoseQuantity(String doseQuantity) {
		this.doseQuantity = doseQuantity;
	}
	
	/**
	 * Returns the Period Value of Medication.
	 * @return the periodValue
	 */
	public String getPeriodValue() {
		return periodValue;
	}
	
	/**
	 * Sets the Period Value of Medication.
	 * @param periodValue Specifies the Period Value of Medication.
	 */
	public void setPeriodValue(String periodValue) {
		this.periodValue = periodValue;
	}
	
	/**
	 * Returns the Period Unit of Medication.
	 * @return the periodUnit
	 */
	public String getPeriodUnit() {
		return periodUnit;
	}
	
	/**
	 * Sets the Period Unit of Medication.
	 * @param periodUnit Specifies the Period Unit of Medication.
	 */
	public void setPeriodUnit(String periodUnit) {
		this.periodUnit = periodUnit;
	}
	
	/**
	 * Returns the Criterion of Medication.
	 * @return the criterion
	 */
	public String getCriterion() {
		return criterion;
	}
	
	/**
	 * Sets the Criterion of Medication.
	 * @param criterion Specifies the Criterion of Medication.
	 */
	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}
	

}
