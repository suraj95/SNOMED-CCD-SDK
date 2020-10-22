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
/*******************************************************************************
 *  Copyright 2017-2018 Centre for Development of Advanced Computing(C-DAC), Pune
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *   http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package cdac.medinfo.utils;
import java.util.ResourceBundle;

/**
 * Resource bundles Wrapper contain locale-specific objects. When your program needs a locale-specific resource, 
 * a String for example, your program can load it from the resource bundle that is appropriate for 
 * the current user's locale.
 */
public class ResourceBundleWrapper
{
	/**
	 * Retrieves the Value for specified from specified Locale object. 
	 * @param strResourceName the resource name .
	 * @param strKey the key whose value to be found.
	 * @param section the name of section in Clinical 
	 * @return String 
	 */
	public static String getValue(String strResourceName,String strKey,String section)
	{
		
		String strBundleName =  "cdac.medinfo.resources."+  strResourceName;
		ResourceBundle bundle = ResourceBundle.getBundle(strBundleName);
				
		return bundle.getString(strKey)+section;
	}
	/**
	 * Retrieves the Value for specified from specified Locale object. 
	 * @param strResourceName the resource name .
	 * @param strKey the key whose value to be found.
	 * @return String 
	 */
	public static String getValue(String strResourceName,String strKey)
	{
		
		String strBundleName =  "cdac.medinfo.resources."+  strResourceName;
		ResourceBundle bundle = ResourceBundle.getBundle(strBundleName);
				
		return bundle.getString(strKey);
	}
}
