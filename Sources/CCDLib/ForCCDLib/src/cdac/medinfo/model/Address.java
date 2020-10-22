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
 * This class specify mailing and home or office addresses. A sequence of address parts, such as street or post office Box, city, postal code, country, etc.
 * <p>This class has the following attributes:</p><p>
 * 1.	deliveryInstallationType (String)<br>
 * 2.	census Tract (String)<br>
 * 3.	delivery Installation Qualifier (String)<br>
 * 4.	precinct (String)<br>
 * 5.	house Number Numeric (String)<br>
 * 6.	delivery Mode (String)<br>
 * 7.	delivery Address Line (String)<br>
 * 8.	delivery Installation Area (String)<br>
 * 9.   post Box (String)<br>
 * 10.	direction (String)<br>
 * 11.  postal Code (String)<br>
 * 13.  delimiter (String)<br>
 * 14.  care Of (String)<br>
 * 15.  street Address Line (String)<br>
 * 16.  house Number (String)<br>
 * 17.  street Name Type (String)<br>
 * 18.  street Name Base (String)<br>
 * 19.  state (String)<br>
 * 20.  unit Type (String)<br>
 * 21.  building Number Suffix (String)<br>
 * 22.  county (String)<br>
 * 23.  additional Locator (String)<br>
 * 24.  city (String)<br>
 * 25.  streetName (String)<br>
 * 26.  deliveryModeIdentifier (String)<br>
 * 27.  unitID (String)<br></p>
 * <p>
 * This class provides accessors and mutators for the elements mentioned above.</p>
 */
public class Address {

    private String deliveryInstallationType;
    private String censusTract;
    private String deliveryInstallationQualifier;
    private String precinct;
    private String houseNumberNumeric;
    private String deliveryMode;
    private String deliveryAddressLine;
    private String deliveryInstallationArea;
    private String postBox;
    private String direction;
    private String postalCode;
    private String delimiter;
    private String careOf;
    private String streetAddressLine;
    private String houseNumber;
    private String streetNameType;
    private String streetNameBase;
    private String state;
    private String unitType;
    private String buildingNumberSuffix;
    private String county;
    private String additionalLocator;
    private String city;
    private String streetName;
    private String deliveryModeIdentifier;
    private String unitID;
    

	/**
	 * Constructs an object of Address.
	 */ 
    public Address() {
    }

	/**
	 * Returns the Delivery Installation Type.
	 * @return the deliveryInstallationType
	 */
	public String getDeliveryInstallationType() {
		return deliveryInstallationType;
	}

	/**
	 * Sets the Delivery Installation Type. 
	 * @param deliveryInstallationType Specifies the type of delivery installation 
	 * (the facility to which the mail will be delivered prior to final shipping via the delivery mode.) 
	 * Example: post office, letter carrier depot, community mail center, station, etc. 
	 */
	public void setDeliveryInstallationType(String deliveryInstallationType) {
		this.deliveryInstallationType = deliveryInstallationType;
	}

	

	/**
	 * Returns the Census Tract.
	 * @return the censusTract
	 */
	public String getCensusTract() {
		return censusTract;
	}

	/**
	 * Sets the Census Tract.
	 * @param censusTract Specifies the geographic sub-unit delineated for demographic purposes.
	 */
	public void setCensusTract(String censusTract) {
		this.censusTract = censusTract;
	}

	/**
	 * Returns the Delivery Installation Qualifier.
	 * @return the deliveryInstallationQualifier
	 */
	public String getDeliveryInstallationQualifier() {
		return deliveryInstallationQualifier;
	}

	/**
	 * Sets the Delivery Installation Qualifier.
	 * @param deliveryInstallationQualifier Specifies the number, letter or name identifying a delivery installation. 
	 * E.g., for Station A, the delivery installation qualifier would be 'A'. 
	 */
	public void setDeliveryInstallationQualifier(
			String deliveryInstallationQualifier) {
		this.deliveryInstallationQualifier = deliveryInstallationQualifier;
	}

	/**
	 * Returns the Precinct.
	 * @return the precinct
	 */
	public String getPrecinct() {
		return precinct;
	}

	/**
	 * Sets the Precinct.
	 * @param precinct Specifies the subsection of a municipality
	 */
	public void setPrecinct(String precinct) {
		this.precinct = precinct;
	}

	/**
	 * Returns the House Number Numeric.
	 * @return the houseNumberNumeric
	 */
	public String getHouseNumberNumeric() {
		return houseNumberNumeric;
	}

	/**
	 * Sets the House Number Numeric.
	 * @param houseNumberNumeric Specifies the numeric portion of a house or building number
	 */
	public void setHouseNumberNumeric(String houseNumberNumeric) {
		this.houseNumberNumeric = houseNumberNumeric;
	}

	/**
	 * Returns the Delivery Mode.
	 * @return the deliveryMode
	 */
	public String getDeliveryMode() {
		return deliveryMode;
	}

	/**
	 * Sets the Delivery Mode.
	 * @param deliveryMode Specifies the type of service offered, method of delivery. For example: post office box, rural route, general delivery, etc.
	 */
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	/**
	 * Returns the Delivery Address Line.
	 * @return the deliveryAddressLine
	 */
	public String getDeliveryAddressLine() {
		return deliveryAddressLine;
	}

	/**
	 * Sets the Delivery Address Line.
	 * @param deliveryAddressLine Specifies the delivery address line is frequently used instead of breaking out delivery mode, delivery installation, etc.
	 * An address generally has only a delivery address line or a street address line, but not both. 
	 */
	public void setDeliveryAddressLine(String deliveryAddressLine) {
		this.deliveryAddressLine = deliveryAddressLine;
	}

	/**
	 * Returns the Delivery Installation Area.
	 * @return the deliveryInstallationArea
	 */
	public String getDeliveryInstallationArea() {
		return deliveryInstallationArea;
	}

	/**
	 * Sets the Delivery Installation Area.
	 * @param deliveryInstallationArea Specifies the location of the delivery installation, usually a town or city, and is only required if the area is different from the municipality. 
	 * Area to which mail delivery service is provided from any postal facility or service such as an individual letter carrier, rural route, or postal route. 
	 */
	public void setDeliveryInstallationArea(String deliveryInstallationArea) {
		this.deliveryInstallationArea = deliveryInstallationArea;
	}

	/**
	 * Returns the Post Box
	 * @return the postBox
	 */
	public String getPostBox() {
		return postBox;
	}

	/**
	 * Sets the Post Box.
	 * @param postBox Specifies the numbered box located in a post station.
	 */
	public void setPostBox(String postBox) {
		this.postBox = postBox;
	}

	/**
	 * Returns the Direction.
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Sets the Direction.
	 * @param direction Specifies the direction (e.g., N, S, W, E)
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * Returns the Postal Code.
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the Postal Code.
	 * @param postalCode Specifies the postal code designating a region defined by the postal service.
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Returns the Delimiter.
	 * @return the delimiter
	 */
	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * Sets the Delimiter.
	 * @param delimiter Specifies the delimiters are printed without framing white space. If no value component is provided, the delimiter appears as a line break.
	 */
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	/**
	 * Returns the Care Of.
	 * @return the careOf
	 */
	public String getCareOf() {
		return careOf;
	}

	/**
	 * Sets the Care Of.
	 * @param careOf Specifies the name of the party who will take receipt at the specified address, and will take on responsibility for ensuring delivery to the target recipient 
	 */
	public void setCareOf(String careOf) {
		this.careOf = careOf;
	}

	/**
	 * Returns the Street Address Line.
	 * @return the streetAddressLine
	 */
	public String getStreetAddressLine() {
		return streetAddressLine;
	}

	/**
	 * Sets the Street Address Line.
	 * @param streetAddressLine Specifies the street address line in terms of street name, house or building number, direction.
	 */
	public void setStreetAddressLine(String streetAddressLine) {
		this.streetAddressLine = streetAddressLine;
	}

	/**
	 * Returns the House Number.
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}

	/**
	 * Sets the House Number.
	 * @param houseNumber Specifies the number of a building, house or lot alongside the street. Also known as "primary street number". This does not number the street but rather the building. 
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * Returns the Street Name Type.
	 * @return the streetNameType
	 */
	public String getStreetNameType() {
		return streetNameType;
	}

	/**
	 * Sets the Street Name Type.
	 * @param streetNameType Specifies the designation given to the street. (e.g. Street, Avenue, Crescent, etc.)
	 */
	public void setStreetNameType(String streetNameType) {
		this.streetNameType = streetNameType;
	}

	/**
	 * Return the Street Name Base.
	 * @return the streetNameBase
	 */
	public String getStreetNameBase() {
		return streetNameBase;
	}

	/**
	 * Sets the Street Name Base.
	 * @param streetNameBase Specifies the base name of a roadway or artery recognized by a municipality (excluding street type and direction)
	 */
	public void setStreetNameBase(String streetNameBase) {
		this.streetNameBase = streetNameBase;
	}

	/**
	 * Returns the State.
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the State.
	 * @param state Specifies the sub-unit of a country with limited sovereignty in a federally organized country.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns the Unit Type.
	 * @return the unitType
	 */
	public String getUnitType() {
		return unitType;
	}

	/**
	 * Sets the Unit Type.
	 * @param unitType Specifies the type of specific unit contained within a building or complex. E.g. Apartment, Floor.
	 */
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	/**
	 * Returns the Building Number Suffix.
	 * @return the buildingNumberSuffix
	 */
	public String getBuildingNumberSuffix() {
		return buildingNumberSuffix;
	}

	/**
	 * Sets the Building Number Suffix.
	 * @param buildingNumberSuffix Specifies any alphabetic character, fraction or other text that may appear after the numeric portion of a building number
	 */
	public void setBuildingNumberSuffix(String buildingNumberSuffix) {
		this.buildingNumberSuffix = buildingNumberSuffix;
	}

	/**
	 * Returns the County.
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * Sets the County.
	 * @param county Specifies the sub-unit of a state or province. 
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * Returns the Additional Locator.
	 * @return the additionalLocator
	 */
	public String getAdditionalLocator() {
		return additionalLocator;
	}

	/**
	 * Sets the Additional Locator.
	 * @param additionalLocator Specifies the unit designator, such as apartment number, suite number, or floor. There may be several unit designators in an address (e.g., "3rd floor, Appt. 342"). 
	 * This can also be a designator pointing away from the location, rather than specifying a smaller location within some larger one (e.g., Dutch "t.o." means "opposite to" for house boats located across the street facing houses). 
	 */
	public void setAdditionalLocator(String additionalLocator) {
		this.additionalLocator = additionalLocator;
	}

	/**
	 * Returns the City.
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the City.
	 * @param city Specifies the name of the city, town, village, or other community or delivery center.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Returns the Street name.
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * Sets the Street Name
	 * @param streetName Specifies the Street Name in terms of street name base and street type.
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * Returns the Delivery Mode Identifier.
	 * @return the deliveryModeIdentifier
	 */
	public String getDeliveryModeIdentifier() {
		return deliveryModeIdentifier;
	}

	/**
	 * returns the Delivery Mode Identifier.
	 * @param deliveryModeIdentifier Specifies the Represents the routing information such as a letter carrier route number. It is the identifying number of the designator (the box number or rural route number). 
	 */
	public void setDeliveryModeIdentifier(String deliveryModeIdentifier) {
		this.deliveryModeIdentifier = deliveryModeIdentifier;
	}

	/**
	 * Returns the Unit ID .
	 * @return the unitID
	 */
	public String getUnitID() {
		return unitID;
	}

	/**
	 * Sets the Unit ID.
	 * @param unitID Specifies the number or name of a specific unit contained within a building or complex, as assigned by that building or complex.
	 */
	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}
    
}
