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
* This class specify Effective Time or Date Details.  
* <p>This class has the following attributes:</p><p>
* 1.	low (String)<br>
* 2.	high (String)<br>
* 3.	center (String)<br>
* 4.	width (String)<br>
* 5.	value (String)<br></p>
* <p>
* This class provides accessors and mutators for the elements mentioned above.</p>
*/
public class EffectiveDateTime {
	//Low value of effective date/Time
	private String low;
	//High value of effective date/Time
	private String high;
	//Center or Average value of effective date/Time
	private String center;
	//Duration of effective date/Time
	private String width;
	//To set effective date/time if low, high, center, duration is unknown 
	private String value;

	/**
	 * Constructs an object of EffectiveDateTime.
	 */
	public EffectiveDateTime(){}
	
	/**
	 * Returns the Low.
	 * @return the low value of effective date/Time
	 */
	public String getLow() {
		return low;
	}
	
	/**
	 * Sets the Low.
	 * @param low Specifies the low value of effective date/Time
	 */
	public void setLow(String low) {
		this.low = low;
	}
	/**
	 * Returns the High.
	 * @return the high value of effective date/Time
	 */
	public String getHigh() {
		return high;
	}
	
	/**
	 * Sets the High.
	 * @param high Specifies the high value of effective date/Time
	 */
	public void setHigh(String high) {
		this.high = high;
	}
	
	/**
	 * Returns the Center.
	 * @return the center or arithmetic mean of low and high effective date/Time
	 */
	public String getCenter() {
		return center;
	}
	/**
	 * Sets the Center
	 * @param center Specifies the center or arithmetic mean of low and high effective date/Time 
	 */
	public void setCenter(String center) {
		this.center = center;
	}
	
	/**
	 * Returns the Width.
	 * @return the width the difference between high and low boundary
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * Sets the Width
	 * @param width Specifies the difference between high and low boundary
	 */
	public void setWidth(String width) {
		this.width = width;
	}
	
	/**
	 * Returns the Value of Effective date/time.
	 * @return the value of effective date/time if low, high, center, width is unknown
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the Value of Effective date/time.
	 * @param value Specifies the value to set effective date/time if low, high, center, width is unknown
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
