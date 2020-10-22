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
package cdac.medinfo.base;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import cdac.medinfo.utils.CCDLogger;
import cdac.medinfo.utils.ResourceBundleWrapper;
/**
 * This class defines Standard methods used in CCD lib
 */
public class StandardMethods {


static String strTempDirPath;
static String strCCDFilePath;
	static 
	{
		strTempDirPath = System.getProperty("java.io.tmpdir");
		strCCDFilePath= System.getProperty("user.home");
		if(strTempDirPath.endsWith(File.separator) == false)
			strTempDirPath = strTempDirPath + File.separator;
		strCCDFilePath= System.getProperty("user.home");
		if(strCCDFilePath.endsWith(File.separator) == false)
			strCCDFilePath = strCCDFilePath + File.separator;
	}

	/**
	 * This method generates the unique identifier.
	 * @return String - unique identifier.
	 */
	public static String generateUUID()
	{
		String strUUID = null;
		strUUID=UUID.randomUUID().toString();
		return strUUID;
	}
	/**
	 * Retrieves the temp directory storage path ( "java.io.tmpdir")
	 * @return String - path.
	 */
	public static String getTempDirectoryStorage() 
	{
		return strTempDirPath;
	}
	/**
	 * Retrieves the CCD File storage path ( "user.home")
	 * @return String - path.
	 */
	public static String getCCDFileStorage() 
	{
		return strCCDFilePath;
	}
	/**
	 * This method generates the Date in Calendar format e.g.Nov 14, 1999 .
	 * @param dateValue - date value in YYYYMMDD format
	 * @return String - Date Value.
	 */
	public static String getCalendarDate(String dateValue){
		
		String formattedDate = null;
		try {
		if(dateValue!= null && !dateValue.isEmpty() && dateValue.matches("^[0-9]*$")){
			if(dateValue.length()==4){
				DateFormat originalFormat = new SimpleDateFormat("yyyy");
				DateFormat targetFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
				Date date = originalFormat.parse(dateValue);	
				formattedDate = targetFormat.format(date); 
			}
			else if(dateValue.length()==6){
				DateFormat originalFormat = new SimpleDateFormat("yyyyMM");
				DateFormat targetFormat = new SimpleDateFormat("MMMM, yyyy", Locale.ENGLISH);
				Date date = originalFormat.parse(dateValue);	
				formattedDate = targetFormat.format(date); 
			}
			else if(dateValue.length()==8){
				DateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
				DateFormat targetFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
				Date date = originalFormat.parse(dateValue);	
				formattedDate = targetFormat.format(date); 
			}
			else if(dateValue.length()==10){
				DateFormat originalFormat = new SimpleDateFormat("yyyyMMddhh");
				DateFormat targetFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
				Date date = originalFormat.parse(dateValue);	
				formattedDate = targetFormat.format(date); 
			}
			else if(dateValue.length()==12){
				DateFormat originalFormat = new SimpleDateFormat("yyyyMMddhhmm");
				DateFormat targetFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
				Date date = originalFormat.parse(dateValue);	
				formattedDate = targetFormat.format(date); 
			}
			else if(dateValue.length()==14){
				DateFormat originalFormat = new SimpleDateFormat("yyyyMMddhhmmss");
				DateFormat targetFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
				Date date = originalFormat.parse(dateValue);	
				formattedDate = targetFormat.format(date); 
			}else
				throw new ParseException("", 0);
		}
		else{
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","InvalidDate")+" : "+dateValue);
			return dateValue;
		}
		} catch (ParseException e) {
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning","InvalidDate")+" : "+dateValue);
			return dateValue;
		}
		return formattedDate;		
	}
}
