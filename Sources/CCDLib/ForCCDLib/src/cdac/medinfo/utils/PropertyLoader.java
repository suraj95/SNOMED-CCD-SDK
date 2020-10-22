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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * This class is used to read and write property in property files 
 *
 */
public class PropertyLoader {
	static PropertyLoader propertyLoader = null ;
	private static FileOutputStream out = null;
	private static Properties props = null;
//  static Properties p = null;
	private static InputStream input = null;
	private static InputStream in = null;
//	private static Set<String> key = new LinkedHashSet<>();
	
	private PropertyLoader()
	{
		try 
		{
			props = System.getProperties();
			in = PropertyLoader.class.getResourceAsStream("ccdconfig.properties");
			if(props!=null&&in!=null)
			{
				props.load(in);
			}
			
			
		} catch (FileNotFoundException e) {
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning", "SNOMEDDBCreation"));	
		} catch (IOException e) {
			CCDLogger objCCDLogger = CCDLogger.getLogger();
			objCCDLogger.log(ResourceBundleWrapper.getValue("warning", "SNOMEDDBCreation"));	
		} 
		finally 
		{
			if (input != null) 
			{
				try {
					input.close();
				} catch (IOException e) {
					CCDLogger objCCDLogger = CCDLogger.getLogger();
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning", "SNOMEDDBCreation"));					}
			}
			if (out != null)
			{
				try {
					out.close();
				} catch (IOException e) {
					CCDLogger objCCDLogger = CCDLogger.getLogger();
					objCCDLogger.log(ResourceBundleWrapper.getValue("warning", "SNOMEDDBCreation"));	
					}
			}
		}
	}
	/**
	 *  This method is used to create instance of Property Loader
	 *  @return propertyLoader - Instance of Property Loader
	 */
	public static PropertyLoader getInstance() 
	{ 
		if (propertyLoader == null) 
			propertyLoader = new PropertyLoader(); 
		return propertyLoader; 
	} 

	/**
	 *  This method is used to retrieve Property for given key
	 *  @param key - key whose property to be fetched
	 *  @return  String value represented value for given key in property file
	 */
	public static String getProperty(String key) 
	{ 
		if(props.containsKey(key))
		{
			return props.getProperty(key);
		}
		else 
			return null ;
	} 
	
	/**
	 *  This method is used to add property in of Property File
	 *  @param key Key to be added in property file
	 *  @param value Value to be added in property file
	 */
	public static void setProperty(String key, String value) 
	{ 
		props.setProperty(key, value);			
	} 
}
