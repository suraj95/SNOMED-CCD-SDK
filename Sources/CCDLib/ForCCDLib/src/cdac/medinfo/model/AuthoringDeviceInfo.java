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
 * This class specify the details of machine that authored the document.
 * <p>This class has the following attributes:</p><p>
 * 1.	Software Name (String)<br>
 * 2.	Manufacturer Model Name (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class AuthoringDeviceInfo {
	private String softwareName;
	private String manufacturerModelName;
	
	/**
	 * Returns the Software Name of Authoring Device.
	 * @return the softwareName
	 */
	public String getSoftwareName() {
		return softwareName;
	}
	/**
	 * Sets the Software Name of Authoring Device.
	 * @param softwareName Specifies the Software Name of Authoring Device.
	 */
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}
	/**
	 * Returns the Manufacturer Model Name of Authoring Device.
	 * @return the manufacturerModelName
	 */
	public String getManufacturerModelName() {
		return manufacturerModelName;
	}
	/**
	 * Sets the Manufacturer Model Name of Authoring Device.
	 * @param manufacturerModelName Specifies the Manufacturer Model Name of Authoring Device.
	 */
	public void setManufacturerModelName(String manufacturerModelName) {
		this.manufacturerModelName = manufacturerModelName;
	}

}
