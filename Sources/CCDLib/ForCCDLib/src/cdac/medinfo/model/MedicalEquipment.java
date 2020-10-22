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
 * This class specify Medical Equipment Section Details. The Medical Equipment section defines a patient's implanted and external medical devices and equipment that their health status depends on, as well as any pertinent equipment or device history. 
 * This section is also used to itemize any pertinent current or historical durable medical equipment (DME) used to help maintain the patient's health status. 
 * <p>This class has the following attributes:</p><p>
 * 1.	Supply Device (String)<br>
 * 2.	Date Supplied (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class MedicalEquipment {

	private String supplyDevice ;
	private String dateSupplied ;
	
	/**
	 * Default constructor of Medical Equipment.
	 */
	public MedicalEquipment() {
		super();
	}
	
	/**
	 * Returns the Supply Device Information.
	 * @return the supplyDevice
	 */
	public String getSupplyDevice() {
		return supplyDevice;
	}
	
	/**
	 * Sets the Supply Device Information.
	 * @param supplyDevice Specifies the Supply Device Information.
	 */
	public void setSupplyDevice(String supplyDevice) {
		this.supplyDevice = supplyDevice;
	}
	
	/**
	 * Returns the Date of dispensing of Medical Equipment.
	 * @return the dateSupplied
	 */
	public String getDateSupplied() {
		return dateSupplied;
	}
	
	/**
	 * Sets the Date of dispensing of Medical Equipment.
	 * @param dateSupplied Specifies the actual or intended time of dispensing of Medical Equipment.
	 */
	public void setDateSupplied(String dateSupplied) {
		this.dateSupplied = dateSupplied;
	}
	

}
