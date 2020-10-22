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
 * This class specify Plan Section Details. The plan of care section contains data defining pending orders, interventions, encounters, services, and procedures for the patient. 
 * <p>This class has the following attributes:</p><p>
 * 1.	Planned Activity (String)<br>
 * 2.	Planned Date (EffectiveDateTime)<br>
 * 3.   Plan Of Care Activity Name (String)<br>
 * 4.   Activity Mood Code (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Plan {

	private String plannedActivity ;
	private EffectiveDateTime plannedDate ;
	private String planOfCareActivityName ;
	private String activityMoodCode;
	
	/**
	 * Constructs an object of Plan.
	 */
	public Plan() {
		super();
	}
	
	/**
	 * Returns the Plan of Care Activity Name.
	 * @return the planOfCareActivityName
	 */
	public String getPlanOfCareActivityName() {
		return planOfCareActivityName;
	}
	
	/**
	 * Sets the Plan of Care Activity Name.
	 * @param planOfCareActivityName Specifies the Plan of Care Activity Name.
	 */
	public void setPlanOfCareActivityName(String planOfCareActivityName) {
		this.planOfCareActivityName = planOfCareActivityName;
	}
	
	/**
	 * Returns the Activity Mood Code.
	 * @return the activityMoodCode
	 */
	public String getActivityMoodCode() {
		return activityMoodCode;
	}
	
	/**
	 * Sets the Activity Mood Code.
	 * @param activityMoodCode Specifies the Activity Mood Code.
	 */
	public void setActivityMoodCode(String activityMoodCode) {
		this.activityMoodCode = activityMoodCode;
	}
	
	/**
	 * Returns the Planned Activity.
	 * @return the plannedActivity
	 */
	public String getPlannedActivity() {
		return plannedActivity;
	}
	
	/**
	 * Sets the Planned Activity.
	 * @param plannedActivity Specifies the Planned Activity.
	 */
	public void setPlannedActivity(String plannedActivity) {
		this.plannedActivity = plannedActivity;
	}
	
	/**
	 * Returns the Date of Planned Activity.
	 * @return the plannedDate
	 */
	public EffectiveDateTime getPlannedDate() {
		return plannedDate;
	}
	
	/**
	 * Sets the Date of Planned Activity.
	 * @param plannedDate Specifies the Date of Planned Activity.
	 */
	public void setPlannedDate(EffectiveDateTime plannedDate) {
		this.plannedDate = plannedDate;
	}

}
