package com.ideasunlimited.savingscalculator.DB;

public class Schema {

	public static final String CREATE_TABLE_CUSOMTER = "create table if not exists " + DbTableConstants.CUSTOMER_TABLE_NAME +
			"( _id integer primary key autoincrement, "
			+ DbTableConstants.CUSTOMER_NAME + " string, "
			+ DbTableConstants.CUSTOMER_ADDRESS + " string, "
			+ DbTableConstants.CUSTOMER_EMAIL_ID + " string, "
			+ DbTableConstants.CUSTOMER_PHONE_NUMBER + " string, "
			+ DbTableConstants.CUSTOMER_LANDLINE_NUMBER + " string, "
			+ DbTableConstants.CUSTOMER_OWNER_NAME + " string, "
			+ DbTableConstants.CUSTOMER_WEBSITE+ " string, "
			+ DbTableConstants.CUSTOMER_CONTACT_PERSON_CONTACT_NUMBER + " string, "
			+ DbTableConstants.CUSTOMER_CONTACT_PERSON_NAME + " string )";

    public static final String CREATE_TABLE_AREA = "create table if not exists " + DbTableConstants.AREA_TABLE_NAME +
            "( _id integer primary key autoincrement,"
			+ DbTableConstants.AREA_NAME + " string, "
			+ DbTableConstants.AREA_SIZE + " string, "
			+ DbTableConstants.AREA_TOTAL_SAVINGS_PER_DAY + " string, "
			+ DbTableConstants.AREA_TOTAL_SAVINGS_PER_MONTH + " string, "
			+ DbTableConstants.AREA_CUSTOMER_ID + " string )";

	public static final String CREATE_TABLE_APPLIANCE = "create table if not exists " + DbTableConstants.APPLIANCE_TABLE_NAME +
			"( _id integer primary key autoincrement,"
			+ DbTableConstants.APPLIANCE_NAME + " string, "
			+ DbTableConstants.APPLIANCE_QUANTITY + " string, "
			+ DbTableConstants.APPLIANCE_ACTIVE_HOURS + " string, "
			+ DbTableConstants.APPLIANCE_AREA_ID + " string, "
			+ DbTableConstants.APPLIANCE_COST_PER_DAY + " string, "
			+ DbTableConstants.APPLIANCE_COST_PER_MONTH + " string, "
			+ DbTableConstants.APPLIANCE_COST_PER_UNIT_ELECTRICITY + " string, "
			+ DbTableConstants.APPLIANCE_WATTAGE + " string, "
			+ DbTableConstants.APPLIANCE_WORKING_HOURS + " string, "
			+ DbTableConstants.APPLIANCE_SAVINGS_PER_DAY + " string, "
			+ DbTableConstants.APPLIANCE_SAVINGS_PER_MONTH + " string )";

	}


