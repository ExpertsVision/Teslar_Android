package com.eveati.sajid.teslar;

public class Constants {
    public static final String BASE_URL = "http://18.216.115.254/";

    // public static final String BASE_URL = "http://13.59.132.182/";
    public static final String REGISTER_URL = BASE_URL + "reg";
    public static final String LOGIN_URL = BASE_URL + "login";
    public static final String FORGOT_PASSWORD_URL = BASE_URL + "recovery";
    public static final String LOGOUT_URL = BASE_URL + "logout";
    public static final String ADD_DISPENSER_URL = BASE_URL + "savedispenser";
    public static final String DISPENSER_SETTINGS = BASE_URL + "updatedispensersetting";
    public static final String DISPENSER_SHOW_SETTINGS = BASE_URL + "showdispensersetting";
    public static final String GET_DISPENSER_LIST_URL = BASE_URL + "send_dispenser_info";
    public static final String CHECK_DISPENSER_URL = BASE_URL + "checkdispenser";
    public static final String ADD_CARE_GIVER_URL = BASE_URL + "addcaregiver";
    public static final String ADD_MEDICATION_URL = BASE_URL + "addMedi";
    public static final String LIST_MEDICATION_URL = BASE_URL + "listMedi";
    public static final String LIST_CARE_GIVER_URL = BASE_URL + "listCaregiver";
    public static final String LIST_MEDICATION_FREQUENCY_URL = BASE_URL + "listfreq";
    public static final String ADD_MEDICATION_FREQUENCY_URL = BASE_URL + "medfreq";
    public static final String DELETE_MEDICATION_FREQUENCY_URL = BASE_URL + "delete-freq-by-id";
    public static final String DELETE_CARE_GIVER_URL = BASE_URL + "delete-caregiver-by-id";
    public static final String UPDATE_MEDICATION_FREQUENCY_URL = BASE_URL + "updatefreq";
    public static final String UPDATE_CARE_GIVER_URL = BASE_URL + "updatecaregiverinfo";


    public static final String DOCUMENT_RESPONSE_MSG = "response_msg";
    public static final String DISPENSER_OBJECT_ARRAY = "dispenserObjectArray";
    public static final String USER_NAME = "username";
    public static final String EMAIL = "email";
    public static final String USER_ID = "userid";
    public static final String DISPENSER_ARRAY = "dispenserArray";
    public static final String U_ID = "uid";
    public static final String PHONE = "mobile";
    public static final String PASSWORD = "password";
    public static final String DISPENSER_NAME = "dname";
    public static final String DISPENSER_MODEL = "dmodel";
    public static final String DISPENSER_SERIAL = "dserial";
    public static final String DISPENSER_MAC = "dmac";
    public static final String DISPENSER_CLOCK = "dclock";
    public static final String DISPENSER_VOLUME = "dvolume";
    public static final String DISPENSER_BACKLIGHT = "dbacklight";
    public static final String DISPENSER_TONE = "dtones";
    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String SUBSCRIPTION = "subscription";
    public static final String MEDICINE_ID = "medid";
    public static final String MEDICINE_ARRAY = "medicineArray";
    public static final String CAREGIVER_ARRAY = "caregiverArray";
    public static final String FREQUENCY_ARRAY = "freqArray";
    public static final String FREQUENCY_ID = "frequencyId";

    public static final String REG_MSG_INSERT_SUCCESS = "Record Inserted successfully";
    public static final String REG_MSG_INSERT_UNSUCCESSFUL = "Error while Inserting";
    public static final String REG_MSG_INSERT_UNSUCCESSFUL_EMAIL_EXIST = "Email address already exist";
    public static final String REG_MSG_INSERT_UNSUCCESSFUL_DISPENSER_EXIST = "Dispenser already exist";
    public static final String LOGIN_MSG_SUCCESS = "Login Successfully";
    public static final String LOGIN_MSG_FAILED = "Invalid Information";
    public static final String LOGIN_MSG_FAILED_CONNECT = "Failed to connect";
    public static final String LOGOUT_MSG_SUCCESS = "Logout Successfully";
    public static final String LOGOUT_MSG_ERROR = "Error while Logout";
    public static final String DISPENSER_ADD_RES_SUCCESS = "Dispenser Added Successfully";
    public static final String DISPENSER_ADD_RES_FAIL = "Fail To Add Dispenser";
    public static final String DISPENSER_ADD_RES_FAIL_EXIST = "Fail To Add, Dispenser Already Exist";
    public static final String DISPENSER_SETTINGS_ADD_RES_SUCCESS = "Dispenser Settings Updated Successfully";
    public static final String DISPENSER_SETTINGS_ADD_RES_FAIL = "Fail To Update Dispenser Settings";
    public static final String DISPENSER_SETTINGS_ADD_RES_FAIL_EXIST = "Fail To Add, Dispenser Settings Already Exist";
    public static final String SHOW_DISPENSER_SETTINGS_ADD_RES_SUCCESS = "Getting Details Successfully";
    public static final String SHOW_DISPENSER_SETTINGS_ADD_RES_FAIL = "Fail To Get Dispenser Details";
    public static final String USER_DETAIL_MSG_SUCCESS = "Getting Detail successfully";
    public static final String USER_DETAIL_MSG_UNSUCCESSFUL = "Record Not Found";

    public static final String INTENT_BLUETOOTH_DEVICE = "bluetoothDevice";
    public static final String INTENT_DISPENSER = "dispenserDevice";

    public static final String LOGIN_USERNAME = "name";
    public static final String LOGIN_EMAIL = "email";
    public static final String LOGIN_PASSWORD = "password";
    public static final String LOGIN_PASSWORD_C = "password_confirmation";

    private static final int REQUEST_CODE_BLUETOOTH_ON = 1001;
    private static final int REQUEST_CODE_VISIBILITY_ON = 1002;

    public static final String PARAMETER_CARE_GIVER_NAME = "caregiver";
    public static final String PARAMETER_DOSE_TAKEN = "dosetaken";
    public static final String PARAMETER_DOSE_NOT_TAKEN = "doesnottaken";
    public static final String PARAMETER_BATTERY_LOW = "battery";
    public static final String PARAMETER_DEVICE_ERROR = "device";
    public static final String PARAMETER_FEW_DOSE_REMAINING = "doesremaining";
    public static final String PARAMETER_REMAINING_DOSE_AMOUNT = "doesremainingamount";
    public static final String PARAMETER_SMS_NOTIFICATION = "smsalert";
    public static final String PARAMETER_EMAIL_NOTIFICATION = "emailalert";
    public static final String PARAMETER_MOBILE_NUMBER = "phone";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_STARTING_TIME_UNIX = "timeFrom";
    public static final String PARAMETER_ENDING_TIME_UNIX = "timeTo";
    public static final String PARAMETER_NOTIFICATIONS = "notifications";
    public static final String PARAMETER_MEDICATION_TIME = "medTime";
    public static final String PARAMETER_MEDICATION_FREQUENCY = "medFrequency";
    public static final String PARAMETER_MEDICINE_NAME = "medicinename";
    public static final String PARAMETER_CID = "cid";
    public static final String PARAMETER_DISPENSER_ID = "dispenserid";
    public static final String PARAMETER_PATTERN = "pattern";
    public static final String PARAMETER_DAYS = "days";
    public static final String PARAMETER_FREQUENCY = "frequency";
    public static final String PARAMETER_FREQUENCY_ID = "id";
    public static final String PARAMETER_PATTERNTYPE = "patternType";
    public static final String PARAMETER_FREQUENCY_TIME = "frequencyTime";
    public static final String PARAMETER_MEDICINE_ID = "medid";
    public static final String CARE_GIVER_ID = "caregiverid";

    public static final String REQUEST = "request";
    public static final String PROVIDE = "provide";
    public static final String RESPONSE = "response";
    public static final String STATUS = "status";
    public static final String DEVICE_DETAIL = "device_detail";
    public static final String DEVICE_SETTING = "device_settings";
    public static final String PROVIDE_DEVICE_DETAIL = PROVIDE + "_device_detail";
    public static final String PROVIDE_DEVICE_SETTING = PROVIDE + "_device_settings";
    public static final String RESPONSE_DEVICE_DETAIL = RESPONSE + "_device_detail";
    public static final String RESPONSE_DEVICE_SETTING = RESPONSE + "_device_settings";

    public static final String INTENT_SERIALIZABLE_APPLICATION_USER = "applicationUser";
    public static final String INTENT_SERIALIZABLE_MEDICINE = "medicine";
    public static final String INTENT_SERIALIZABLE_SELECTED_DISPENSER = "selectedDispenser";

    public static final String TIME_STAMP = "ts";
}
