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
 * This class specifies Test Reports for the Result Section. It contains the report of observations generated by laboratories, imaging procedures, and other procedures. 
 * <p>This class has the following attributes:</p><p>
 * 1.	resultTypeCode (String)<br>
 * 2.	testName (String)<br>
 * 3.	range (String)<br>
 * 4.	unit (String)<br>
 * 5.	value (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class TestReport {

	private String resultTypeCode;
	private String testName ;
	private String range;
	private String unit;
	private String value ;
	
	/**
	 * Constructs an object of Test Report
	 */
	public TestReport() {
		super();
	}
	
	/**
	 * This parameterized constructor initializes the fields.
	 * @param resultTypeCode specifies the Result Type
	 * @param testName specifies Test type name
	 * @param range specifies reference range for the report value
	 * @param unit specifies unit of test result value
	 * @param value specifies specifes the test result value of the report
	 */
	public TestReport(String resultTypeCode, String testName, String range,String unit, String value) {
		super();
		this.resultTypeCode = resultTypeCode;
		this.testName = testName;
		this.range = range;
		this.unit = unit;
		this.value = value;
	}
	
	/**
	 * Returns the Result Type Code.
	 * @return the Result Type Code.
	 */
	public String getResultTypeCode() {
		return resultTypeCode;
	}
	
	/**
	 * Sets the ResultTypeCode.
	 * @param resultTypeCode specifies the type of Result
	 */
	public void setResultTypeCode(String resultTypeCode) {
		this.resultTypeCode = resultTypeCode;
	}
	
	/**
	 * Returns the Test type name
	 * @return the name of type of Test
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * Sets the Test Name name of a Result.
	 * @param testName specifies the Test Type name
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * Returns the reference range for the report value.
	 * @return the reference range for the report value.
	 */
	public String getRange() {
		return range;
	}

	/**
	 * Sets the Reference range for the report value.
	 * @param range specifies the reference range for the report value
	 */
	public void setRange(String range) {
		this.range = range;
	}

	/**
	 * Returns the unit of test result value.
	 * @return the unit of test result value.
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Sets the unit of test result.
	 * @param unit specifies the unit of test result
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * Returns the test result values of the report.
	 * @return the test result values of the report.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of test result value of the report
	 * @param value specifes the test result value of the report
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
}