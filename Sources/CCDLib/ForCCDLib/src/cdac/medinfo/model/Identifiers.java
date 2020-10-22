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
 * This class specify the identifier that uniquely identifies a thing or object.
 * <p>This class has the following attributes:</p><p>
 * 1.	Root Identifier (String)<br>
 * 2.	Extension (String)<br>
 * 3.	Assigning Authority Name (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Identifiers {
	
	 private String rootIdentifier;
	 private String extension;
	 private String assigningAuthorityName;
	
	 /**
	  * Returns the unique identifier.
	 * @return the rootIdentifier
	 */
	public String getRootIdentifier() {
		return rootIdentifier;
	}
	/**
	 * Sets the unique identifier.
	 * @param rootIdentifier Specifies the unique identifier.
	 */
	public void setRootIdentifier(String rootIdentifier) {
		this.rootIdentifier = rootIdentifier;
	}
	/**
	 * Returns the character string as a unique identifier within the scope of the identifier root. 
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}
	/**
	 * Sets the character string as a unique identifier within the scope of the identifier root. 
	 * @param extension Specifies character string as a unique identifier within the scope of the identifier root. 
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	/**
	 * Returns the human readable name or mnemonic for the assigning authority.
	 * @return the assigningAuthorityName
	 */
	public String getAssigningAuthorityName() {
		return assigningAuthorityName;
	}
	/**
	 * Sets the human readable name or mnemonic for the assigning authority.
	 * @param assigningAuthorityName Specifies the human readable name or mnemonic for the assigning authority.
	 */
	public void setAssigningAuthorityName(String assigningAuthorityName) {
		this.assigningAuthorityName = assigningAuthorityName;
	}

}
