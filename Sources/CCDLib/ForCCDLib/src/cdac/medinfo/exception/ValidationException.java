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
package cdac.medinfo.exception;
/**
 *	This Exception is raised when Validation of given XML file failed.
 */
public class ValidationException extends CCDException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initialized a new object of ParsingException
	 */
	public ValidationException()
	{
		
	}
	/**
	 * Initialized a new object of CCDException with the specified detail message and cause.
	 * @param strMessage the detail message.
	 * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
	 *  (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public ValidationException(String strMessage, Throwable cause)
	{
		super(strMessage, cause);
	}

	/**
	 * Initialized a new object of CCDException with the specified detail message.
	 * @param strMessage the detail message.
	 */
	public ValidationException(String strMessage)
	{
		super(strMessage);
	}

	/**
	 * This constructor is useful for exceptions that are little more than wrappers for other throwable
	 * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
	 *  (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public ValidationException(Throwable cause)
	{
		super(cause);
	}
}
