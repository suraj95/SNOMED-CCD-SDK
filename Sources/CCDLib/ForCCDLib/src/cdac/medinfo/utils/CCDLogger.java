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
package cdac.medinfo.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import cdac.medinfo.base.StandardMethods;
/**
 * This class is used to generate Log 
 *
 */
public class CCDLogger {

	private String strLogFile ;
	/**
	 * Specifies name of log file.
	 */
	public static final String CCD_LOGGER_NAME = "CCD_LOGGER_V3"; 

	/**
	 * the object CCD logger.
	 */
	private static  CCDLogger objCCDLogger = null;
	/**
	 * the object logger.
	 */
	private static Logger objLogger = null;

	/**
	 * Retrieves empty instance of CCDLogger enabled for logging in the error log file.
	 * @return CCDLogger - instance of CCDLogger.
	 */
	public static CCDLogger getLogger()
	{
		if(objCCDLogger == null)
			objCCDLogger = new CCDLogger();
		return objCCDLogger;
	}

	public static CCDLogger getLogger(String strLogFile)
	{
		if(objCCDLogger == null)
			objCCDLogger = new CCDLogger(strLogFile);
		return objCCDLogger;
	}
	/**
	 * This is the private constructor for CCDLogger.
	 */
	private CCDLogger()
	{
		try
		{
			objLogger = Logger.getLogger(CCD_LOGGER_NAME);
			DateFormat df = new SimpleDateFormat("ddMMyyHHmmss");
			Date dateobj = new Date();
			String tempFileName ="CCDLog_"+df.format(dateobj)+ ".log";
			String strPath = StandardMethods.getTempDirectoryStorage() + "CDAC-SDK/CCD/Logs";
			File logDir =  new File(strPath);

			logDir.mkdirs();
			strLogFile = logDir.getPath() +"/"+ tempFileName;

			FileHandler objFileHandler = new FileHandler(strLogFile, true);
			objFileHandler.setFormatter(new SimpleFormatter());
			objLogger.addHandler(objFileHandler);
		}
		catch(Exception e)
		{			
			throw new LoggerException(ResourceBundleWrapper.getValue("exceptions", "LoggingException","CCDLogger"));
		}
	}


	/**
	 * This is the private constructor for CCDLogger.
	 */
	private CCDLogger(String strLogFile)
	{
		try
		{
			if(strLogFile == null||strLogFile.trim().isEmpty())
			{
				getLogger();
				CCDLogger objCCDLogger = CCDLogger.getLogger();
				objCCDLogger.log(ResourceBundleWrapper.getValue("status","DefaultLoggerPath"));
			}
			else{
				if(strLogFile.trim().isEmpty()){
					getLogger();
					CCDLogger objCCDLogger = CCDLogger.getLogger();
					objCCDLogger.log(ResourceBundleWrapper.getValue("status","DefaultLoggerPath"));
				}
				else{
				String tempFileName = null ;	
				File logDir =  new File(strLogFile);
				objLogger = Logger.getLogger(CCD_LOGGER_NAME);
				this.strLogFile = strLogFile;
				DateFormat df = new SimpleDateFormat("ddMMyyHHmmss");
				Date dateobj = new Date();
				tempFileName = "CCDLog_"+df.format(dateobj)+ ".log";
				if(strLogFile==null || strLogFile=="")		
					strLogFile = StandardMethods.getTempDirectoryStorage() + "CDAC-SDK/CCD/Logs";
				if(!logDir.isDirectory()){
					if(getFileExtension(logDir).equalsIgnoreCase("log"))
					{	
						String dir = strLogFile.substring(0, strLogFile.lastIndexOf(File.separator));
						if(!new File(dir).exists())
							new File(dir).mkdir();
						tempFileName="" ;
					}
					else
					logDir.mkdirs();
				}
				
				if(tempFileName.isEmpty())
					strLogFile = logDir.getPath();
				else
					strLogFile = logDir.getPath() +File.separator+ tempFileName;
				FileHandler objFileHandler = new FileHandler(strLogFile, true);
				objFileHandler.setFormatter(new SimpleFormatter());
				objLogger.addHandler(objFileHandler);
				
				}
			}
		}
		catch(Exception e)
		{		
			e.printStackTrace();
			throw new LoggerException(ResourceBundleWrapper.getValue("exceptions", "MandatoryMissing","CCDLogger"));
		}
	}

	/**
	 * Logs the error  message with the level specified. 
	 * @param strLogMessage Message to be logged.
	 */
	public void log(String strLogMessage)
	{

		objLogger.log(Level.INFO,strLogMessage);
	}

	/*
	 * Used to retrieve Extension for a given File
	 */
	private String getFileExtension(File file) {
		String fileName = file.getName();
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".")+1);
		else return "";
	}
}
