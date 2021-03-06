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

import java.util.Date;

import cdac.medinfo.utils.ResourceBundleWrapper;
/**
 * This Exception is raised when Signals invalid serialization.
 */
public class SerializeException extends CCDException
{
	private static final long serialVersionUID = 1L;
	private String strMsg =ResourceBundleWrapper.getValue("exceptions", "SerializeFailed");

	/**
	 * Initializes a new object of SerializeException .
	 */
	public SerializeException()
	{
		super();
	}

	/**
	 * Initialized a new object of SerializeException with the specified detail message and cause
	 * @param strMessage the detail message.
	 * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
	 *  (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public SerializeException(String strMessage, Throwable cause)
	{
		super(strMessage, cause);
		strMsg += strMessage;
	}

	/**
	 * Initialized a new object of SerializeException with the specified detail message
	 * @param strMessage the detail message.
	 */
	public SerializeException(String strMessage)
	{
		super(strMessage);
		strMsg += strMessage;
	}

	/**
	 * This constructor is useful for exceptions that are little more than wrappers for other throwable
	 * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
	 *  (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public SerializeException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * Retrieves the detail message string of this throwable. 
	 * @return String - the detail message string of this Throwable instance (which may be null).
	 */
	public String getMessage()
	{
		return new Date().toString()+":"+strMsg;
	}
}
