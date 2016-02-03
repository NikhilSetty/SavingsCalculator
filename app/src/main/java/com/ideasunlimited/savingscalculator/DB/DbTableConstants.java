package com.ideasunlimited.savingscalculator.DB;

import android.content.Intent;


public class DbTableConstants {
	public static final String CUSTOMER_TABLE_NAME = "customertable";

	public static final String CUSTOMER_NAME = "customername";
	public static final String CUSTOMER_ADDRESS = "customeraddress";
	public static final String CUSTOMER_WEBSITE = "customerwebsite";
	public static final String CUSTOMER_EMAIL_ID = "customeremailid";
	public static final String CUSTOMER_PHONE_NUMBER = "customerphonenumber";
	public static final String CUSTOMER_LANDLINE_NUMBER = "customerlandlinenumber";
	public static final String CUSTOMER_OWNER_NAME = "customerownername";
	public static final String CUSTOMER_CONTACT_PERSON_NAME = "customercontactpersonname";
	public static final String CUSTOMER_CONTACT_PERSON_CONTACT_NUMBER = "customercontactpersoncontactnumber";

	public static final String AREA_TABLE_NAME = "areatable";

	public static final String AREA_NAME = "areaname";
	public static final String AREA_CUSTOMER_ID = "areacustomerid";
	public static final String AREA_SIZE = "areasize";
	public static final String AREA_TOTAL_SAVINGS_PER_DAY = "areasavingsperday";
	public static final String AREA_TOTAL_SAVINGS_PER_MONTH = "areasavingspermonth";

	public static final String APPLIANCE_TABLE_NAME = "appliancetable";

	public static final String APPLIANCE_AREA_ID = "applianceareaid";
	public static final String APPLIANCE_NAME = "appliancename";
	public static final String APPLIANCE_WATTAGE = "appliancewattage";
	public static final String APPLIANCE_QUANTITY = "appliancequantity";
	public static final String APPLIANCE_WORKING_HOURS = "applianceworkinghours";
	public static final String APPLIANCE_ACTIVE_HOURS = "applianceactivehours";
	public static final String APPLIANCE_COST_PER_UNIT_ELECTRICITY = "appliancecostperunit";
	public static final String APPLIANCE_COST_PER_DAY = "appliancecostperday";
	public static final String APPLIANCE_COST_PER_MONTH = "appliancecostpermonth";
	public static final String APPLIANCE_SAVINGS_PER_DAY = "appliancesavingsperday";
	public static final String APPLIANCE_SAVINGS_PER_MONTH = "appliancesavingspermonth";
}
