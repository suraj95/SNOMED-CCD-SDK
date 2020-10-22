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
package cdac.medinfo.codesytem;

import java.io.File;
import java.util.Set;

import cdac.medinfo.base.CCDConstants;
import cdac.medinfo.utils.CCDLogger;
import cdac.medinfo.utils.ResourceBundleWrapper;
import in.cdac.medinfo.csnotk.csnolib.agents.SNOMEDAgent;
import in.cdac.medinfo.csnotk.csnolib.model.CompositeDescription;
/**
 * This class used for generating SNOMED-CT id for SNOMED specified Code System
 */
public class SNOMEDCode {

	private static SNOMEDAgent agent = new SNOMEDAgent();
	private static String dir = null;
	private static boolean dbStatus = false ;
	/**
	 *  Method is used to check file SNOMED CT database path is valid
	 *  @param dbPath - Database path for SNOMED CT database
	 */
	public static void checkForConnection(String dbPath) {
		dir	= dbPath ;
		dbStatus =checkDataBase(dbPath);
		CCDLogger objCCDLogger = CCDLogger.getLogger();
		objCCDLogger.log(ResourceBundleWrapper.getValue("status", "SNOMEDDbPath",dbPath));
		
	} 
	/**
	 *  Method is used to retrieve SNOMED CT code
	 *  @param term - whose SNOMED CT value to be fetched
	 *  @return code - SNOMED CT value for given term
	 */
	public static String getCode(String term)
	{

		String code = null ;
		if(dbStatus){
			agent.init(dir,dir,1000,500,50);
			if(term!=null)
			{	Set<CompositeDescription> descriptions = agent.search(term,1, false,null,null);
				if(!descriptions.isEmpty()&&descriptions!=null)
				code = descriptions.iterator().next().getConceptId().toString();
			}
			
		}
		return code;

	}
	/**
	 *  Method is used to check DB connection 
	 *  @param path - Database path for SNOMED CT database
	 */
	private static boolean checkDataBase(String path)
	{
		String maindirpath = path; 
		if(maindirpath!= null)
		{
			// File object 
			File maindir = new File(maindirpath); 

			if(maindir.exists() && maindir.isDirectory()) 
			{ 
				File arr[] = maindir.listFiles(); 
				return  fileSearch(arr);
			}  
		}
		CCDLogger objCCDLogger = CCDLogger.getLogger();
		objCCDLogger.log(ResourceBundleWrapper.getValue("warning", "SNOMEDDBCreation",dir));
		return false;
	}
	/**
	 *  Method is used to check Database directory present   
	 *  @param arr - list of file in array 
	 */
	private static boolean fileSearch(File[] arr)  
	{ 
		// terminate condition 
		if(arr.length==0) 
			return false ; 

		// for files 
		for(int index =0;index!=arr.length;index++)
		{	
			if(arr[index].isFile()&&arr[index].getName().equals(CCDConstants.SNOMED_DB_FILE)||arr[index].isDirectory()&&arr[index].getName().equals(CCDConstants.SNOMED_INDEX_FILE)) 
			{
				return true;
			}
		}
		return false;
	} 
}