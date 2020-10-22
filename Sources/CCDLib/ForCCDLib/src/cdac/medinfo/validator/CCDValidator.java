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

import java.io.File;
import java.net.URISyntaxException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import cdac.medinfo.ccd.templates.ClinicalDocument;
import cdac.medinfo.exception.ValidationException;
import cdac.medinfo.utils.CCDLogger;
import cdac.medinfo.utils.ResourceBundleWrapper;
/**
 * This class is used as a Validator for Clinical Document Validation which logs the severity,message and location(line number and column number)
 * for the Event occur
 */
public class CCDValidator {
	static Unmarshaller un = null;
	static JAXBContext context = null;
	/**
	 * This Method is used to perform validation on a given xml file 
	 * @param unmarshaller - UnMarshalled-object of CCD Document
	 */
	private static void validate(Unmarshaller unmarshaller)
	{
		String localPath = null;
		try {

			if(unmarshaller!= null)
			{

				File jarPath = new File(CCDValidator.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
				SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 	
				localPath= jarPath.getParentFile().getParent().concat(File.separator+"Resources"+File.separator+"CDASchemas"+File.separator+"cda"+File.separator+"Schemas"+File.separator+"CDA.xsd");
				File xslFile = new File(localPath);
				Schema schema = sf.newSchema(xslFile);
				if(schema== null)
					throw new ValidationException();
				unmarshaller.setSchema(schema);
				unmarshaller.setEventHandler(new CCDValidationEventHandler());
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("status","ValidationStatus"));
			}
			else
				throw new ValidationException();

		} catch (JAXBException |SAXException | URISyntaxException e) {
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","InvalidXML"));
		}catch (ValidationException ve) {
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","ValidationFailed"));
		} 
	}
	/**
	 * This Method is used to perform validation on a given xml file 
	 * @param xmlFilePath - Xml file Path to be validate 
	 */
	public static void validate(String xmlFilePath)
	{
		CCDLogger objCCDLog = CCDLogger.getLogger();
		objCCDLog.log(ResourceBundleWrapper.getValue("status","ValidatingDoc"));
		try {

			Source source = new StreamSource(xmlFilePath);
			context = JAXBContext.newInstance(ClinicalDocument.class);
			if(context==null || source == null)
				throw new JAXBException("Validation Failed");
			Unmarshaller un = context.createUnmarshaller();
			validate(un);
			un.unmarshal(source, ClinicalDocument.class);
		} 
		catch (JAXBException e) {
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("exceptions","InvalidXML"));
		}  
	}
}
