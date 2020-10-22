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
 * This class specify VitalSigns Details.This section contains current and historically relevant vital signs, such as blood pressure, heart rate, respiratory rate, height, weight, body mass index, head circumference, crown-to-rump length, and pulse oximetry.
 * <p>This class has the following attributes:</p><p>
 * 1.	date (String)<br>
 * 2.	vitalSigns (String)<br>
 * 3.	value (String)<br>
 * 4.	unit (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class VitalSigns {
	
    private String date;
    private String vitalSigns;
    private String value ;
    private String unit ;
    
    /**
	 * Constructs an object of VitalSigns.
	 */
	public VitalSigns() {
		super();
	}

	/**
	 * Returns the date of vital sign.
	 * @return the date of vital sign.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date of vital sign.
	 * @param date specifies the date of the vital sign
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Returns the vital sign.
	 * @return the vitalSigns.
	 */
	public String getVitalSigns() {
		return vitalSigns;
	}

	/**
	 * Sets the vital sign type.
	 * @param vitalSigns specifies vital sign type.
	 */
	public void setVitalSigns(String vitalSigns) {
		this.vitalSigns = vitalSigns;
	}

	/**
	 * Returns the value for the vital sign.
	 * @return the value for the vital signs.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value for the vital sign.
	 * @param value specifies value for the vital sign.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Returns the value for the vital sign.
	 * @return the value for the vital sign.
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Sets the unit for the value of vital sign.
	 * @param unit specifies unit for the value of vital sign.
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

}
