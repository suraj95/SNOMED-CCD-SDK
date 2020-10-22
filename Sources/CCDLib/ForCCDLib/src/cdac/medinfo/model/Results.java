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

import java.util.List;

/**
 * This class specifies Result Section Details. This section contains the results of observations generated by laboratories, imaging procedures, and other procedures for the period of time being summarized. 
 * <p>This class has the following attributes:</p><p>
 * 1.	testDate (String)<br>
 * 2.	testDetails (List<code>&lt;TestReport></code>)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Results {
	private String testDate;
	private List<TestReport> testDetails ;
	
	/**
	 * Constructs an object of Result
	 */
	public Results()
	{
		super();
	}

	/**
	 * Returns the date of test result.
	 * @return the date of test result.
	 */
	public String getTestDate() {
		return testDate;
	}

	/**
	 * Sets the date of result.
	 * @param testDate Specifies the date of Result
	 */
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	/**
	 * Returns the list of TestDetails.
	 * @return the list of TestDetails.
	 */
	public List<TestReport> getTestDetails() {
		return testDetails;
	}

	/**
	 * Sets the Test Details.
	 * @param testDetails Specifies list of Test Reports
	 */
	public void setTestDetails(List<TestReport> testDetails) {
		this.testDetails = testDetails;
	}
	
	
}