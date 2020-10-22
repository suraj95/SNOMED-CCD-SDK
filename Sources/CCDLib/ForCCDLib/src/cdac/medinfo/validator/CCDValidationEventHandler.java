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
package cdac.medinfo.validator;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import cdac.medinfo.utils.CCDLogger;
/**
 * This class is used as a Event Handler for Clinical Document Validator which logs the severity,message and location(line number and column number)
 * for the Event occur
 */
public class CCDValidationEventHandler implements ValidationEventHandler {
	int count = 1;
 
    /**
     * used to handle Event 
     * Receive notification of a validation warning or error.The ValidationEvent will have a ValidationEventLocator embedded in it that
     * indicates where the error or warning occurred.If an unchecked runtime exception is thrown from this method, the JAXBprovider will
     * treat it as if the method returned false and interrupt the current unmarshal, validate, or marshal operation.
     * @param event - value for event
     */
    public boolean handleEvent(ValidationEvent event) {
         
    	CCDLogger objCCDLogger = CCDLogger.getLogger();
		objCCDLogger.log("\nEVENT : "+count++ +"\n"+ "SEVERITY:  " + event.getSeverity()+"\n"+ "MESSAGE:  " + event.getMessage()+"\n"+ "LOCATOR" +"\n"+ "> LINE NUMBER:  " + event.getLocator().getLineNumber()+"\n"+"> COLUMN NUMBER:  " + event.getLocator().getColumnNumber());
		
		return true;
    }
}
