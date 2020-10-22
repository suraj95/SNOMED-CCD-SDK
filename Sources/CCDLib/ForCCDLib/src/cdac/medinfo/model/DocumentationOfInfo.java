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
 * This class specify the details of Documentation Of.
 * <p>This class has the following attributes:</p><p>
 * 1.	EffectiveDateofDocument (EffectiveDateTime)<br>
 * 2.	Performer (List<code>&lt;Performer></code>)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class DocumentationOfInfo {

	private EffectiveDateTime EffectiveDateofDocument;
	private List<Performer> Performer;
	/**
	 * Returns the time the actual event (as opposed to the encounter surrounding the event) took place. 
	 * @return the objEffectiveDateofDocument
	 */
	public EffectiveDateTime getEffectiveDateofDocument() {
		return EffectiveDateofDocument;
	}
	/**
	 * Sets time the actual event (as opposed to the encounter surrounding the event) took place.
	 * @param objEffectiveDateofDocument Specifies time the actual event (as opposed to the encounter surrounding the event) took place.
	 */
	public void setEffectiveDateofDocument(EffectiveDateTime objEffectiveDateofDocument) {
		this.EffectiveDateofDocument = objEffectiveDateofDocument;
	}
	/**
	 * Returns the List of Performer.
	 * @return the objPerformer
	 */
	public List<Performer> getPerformer() {
		if (Performer == null) {
			Performer = new ArrayList<Performer>();
		}
		return Performer;
	}

	
}
