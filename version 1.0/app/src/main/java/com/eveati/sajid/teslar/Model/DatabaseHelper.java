package com.eveati.sajid.teslar.Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "teslarProject";    // Database Name
    Integer usage,appliancetype;


    // private static final String TABLE_NAME = "people_table";



    /********************************************************************
     *                                                                  *
     *                       Declaring Tables                          *
     *                                                                  *
     ********************************************************************/

    private static final String TABLE_USER = "Users";   // Table Name
    private static final String TABLE_PUP = "PerUnitPrice";   // Table Name
    private static final String TABLE_TIMESPAN = "Timespan";   // Table Name
    private static final String TABLE_POWER = "Power";   // Table Name
    private static final String TABLE_SCHEDULEROUTPUT = "schedulerOutput";   // Table Name
    private static final String TABLE_SCHEDULERINPUT = "schedulerInput";   // Table Name






    /********************************************************************
     *                                                                  *
     *                       User Columns                    *
     *                                                                  *
     ********************************************************************/

    private static final String COL_USER_NAME = "name";
    private static final String COL_USER_EMAIL = "email";
    private static final String COL_USER_PASSWORD = "password";
    private static final String COL_USER_ID = "userid";   // primary key
   // private static final String COL_USER_TYPE = "usertype";


    /********************************************************************
     *                                                                  *
     *                       Per Unit Price Columns                    *
     *                                                                  *
     ********************************************************************/

    private static final String COL_PUP_ID = "_id";
    private static final String COL_PUP_TYPE = "type";
    private static final String COL_PUP_PRICE = "price";

    /********************************************************************
     *                                                                  *
     *                       Time Span Columns                    *
     *                                                                  *
     ********************************************************************/

    private static final String COL_TIMESPAN_ID = "_id";
    private static final String COL_TIMESPAN_TIME= "time";
    private static final String COL_TIMESPAN_TYPE = "type";



    /********************************************************************
     *                                                                  *
     *                       POWER Columns                    *
     *                                                                  *
     ********************************************************************/

    private static final String COL_ID = "id";
    private static final String COL_POWER_AC = "power_ac";
    private static final String COL_POWER_DC = "power_dc";
    private static final String COL_POWER_SYSTEMID= "systemid";      // foreign key of USER table
    private static final String COL_POWER_TIME = "timespanid";       // foreign key of TIMESPAN




    /********************************************************************
     *                                                                  *
     *                       SCHEDULER INPUT Columns                    *
     *                                                                  *
     ********************************************************************/

    private static final String COL_SCHEDULERINPUT_ID= "id";
    private static final String COL_SCHEDULERINPUT_DEVICE_NAME= "device_name";

    private static final String COL_SCHEDULERINPUT__APPLIANCE_TYPE= "appliance_type";  //  sajid

    private static final String COL_SCHEDULERINPUT_MAXIMUMTIME= "maximum_time";    //  sajid
    private static final String COL_SCHEDULERINPUT_USAGE= "usage";            //  sajid
    private static final String COL_SCHEDULERINPUT_RATING= "rating";       //  sajid

    private static final String COL_SCHEDULERINPUT_LOWER_LIMIT1= "lower_limit1";
    private static final String COL_SCHEDULERINPUT_LOWER_LIMIT2= "lower_limit2";
    private static final String COL_SCHEDULERINPUT_UPPER_LIMIT1= "upper_limit1";
    private static final String COL_SCHEDULERINPUT_UPPER_LIMIT2= "upper_limit2";



    /********************************************************************
     *                                                                  *
     *                       SCHEDULER OUTPUT Columns                   *
     *                                                                  *
     ********************************************************************/

    private static final String COL_SCHEDULEROUTPUT_DEVICE_NAME= "device_name";
    private static final String COL_SCHEDULEROUTPUT__DAY= "day";
    private static final String COL_SCHEDULEROUTPUT_TIME= "time";
    private static final String COL_SCHEDULERINGOUTPUT_STARTING_DATE= "starting_date";
    private static final String COL_SCHEDULEROUTPUT_LOWER_LIMIT= "lower_limit";
    private static final String COL_SCHEDULEROUTPUT_UPPER_LIMIT= "upper_limit";



    /********************************************************************
     *                                                                  *
     *                       Create Tables                              *
     *                                                                  *
     ********************************************************************/

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER +
            " ("+COL_USER_ID+" INTEGER PRIMARY KEY,"
            +COL_USER_NAME +" VARCHAR(255),"
            +COL_USER_PASSWORD+" VARCHAR(255),"
            +COL_USER_EMAIL+" VARCHAR(225));";









    private static final String CREATE_TABLE_POWER = "CREATE TABLE " + TABLE_POWER +
            " ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COL_POWER_SYSTEMID +" VARCHAR(255),"
            +COL_POWER_AC+ " VARCHAR(255),"
            +COL_POWER_DC+ " VARCHAR(255),"
            +COL_TIMESPAN_ID+ " VARCHAR(255),"
            +COL_USER_EMAIL+" VARCHAR(225));";






    private static final String CREATE_TABlE_SCHEDULERINPUT = "CREATE TABLE " + TABLE_SCHEDULERINPUT +
            " ("+COL_SCHEDULERINPUT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COL_SCHEDULERINPUT_DEVICE_NAME+" VARCHAR(225),"
            +COL_SCHEDULERINPUT__APPLIANCE_TYPE +" INTEGER,"
            +COL_SCHEDULERINPUT_MAXIMUMTIME+ " DOUBLE,"
            +COL_SCHEDULERINPUT_USAGE+ " INTEGER,"
            +COL_SCHEDULERINPUT_RATING+ " DOUBLE,"
            +COL_SCHEDULERINPUT_LOWER_LIMIT1+" VARCHAR(225),"
            +COL_SCHEDULERINPUT_LOWER_LIMIT2+" VARCHAR(225),"
            +COL_SCHEDULERINPUT_UPPER_LIMIT1+" VARCHAR(225),"
            +COL_SCHEDULERINPUT_UPPER_LIMIT2+" VARCHAR(225));";



    /********************************************************************
     *                                                                  *
     *                       SCHEDULER INPUT Columns                    *
     *                                                                  *
     ********************************************************************/

    // private static final String COL_SCHEDULERINPUT_ID= "id";
    //private static final String COL_SCHEDULERINPUT_DEVICE_NAME= "device_name";


    //  private static final String COL1 = "ID";
    //private static final String COL2 = "device_name";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

        // super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        /********************************************************************
         *                                                                  *
         *                       Create Tables                              *
         *                                                                  *
         ********************************************************************/

        try {
            db.execSQL(CREATE_TABLE_USER);
            db.execSQL(CREATE_TABLE_POWER);
            db.execSQL(CREATE_TABlE_SCHEDULERINPUT);
        } catch (Exception e) {
            //Message.message(getApplicationContext(),""+e);
        }


        //    String CREATE_TABlE_SCHEDULERINPUT =  "CREATE TABLE " + TABLE_SCHEDULERINPUT + " (id  INTEGER PRIMARY KEY AUTOINCREMENT, " +
        //        COL_SCHEDULERINPUT_DEVICE_NAME +" TEXT)";

        //db.execSQL(CREATE_TABlE_SCHEDULERINPUT);


        //String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
        //      COL2 +" TEXT)";
        //db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_SCHEDULERINPUT);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_USER);
        onCreate(db);

        // db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        //onCreate(db);
    }




    public boolean addUserData(int userid,String username,String email,String password)
    //public boolean addData(String devicename,String usage, String rating)



    {



        // Changing String values to Integer values




        //int userid = Integer.parseInt(myuserid);     //sajid
        //int appliancetype = Integer.parseInt(appliancetype_string);    //sajid

        //int apptype = Integer.parseInt(rating);


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_ID, userid);
        contentValues.put(COL_USER_NAME, username);
        contentValues.put(COL_USER_PASSWORD, password);
        contentValues.put(COL_USER_EMAIL, email);



        //  contentValues.put(COL2, item);



        //Log.d(TAG, "addData: Adding " + item + " to " + TABLE_SCHEDULERINPUT);

        long result = db.insert(TABLE_USER, null, contentValues);

        // long result = db.insert(TABLE_NAME, null, contentValues);



        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
























    public boolean addData(String devicename,String appliancetype_string,String maxtime_string,String usage_string,String rating_string,String ll1,String ll2, String ul1,String  ul2)
    //public boolean addData(String devicename,String usage, String rating)



    {



        // Changing String values to Integer values

        Double power=Double.parseDouble(rating_string);


        //int power = Integer.parseInt(rating_string);
        power=power/1000;     // converting to watts


        Double maxtime = Double.parseDouble(maxtime_string);
        maxtime=maxtime/24;  // converting to hours

        //sajid

        if (usage_string.equalsIgnoreCase("Once a week"))
        {
            usage=1;


            toastMessage("Usage is"+usage.toString());
        }

        else if (usage_string.equalsIgnoreCase("everyday"))
        {
            usage=0;

            toastMessage("Usage is"+usage.toString());
        }
        else if (usage_string.equalsIgnoreCase("twice a week"))
        {
            usage=2;

            toastMessage("Usage is"+usage.toString());
        }

        else if (usage_string.equalsIgnoreCase("thrice a week"))
        {
            usage=3;

            toastMessage("Usage is"+usage.toString());
        }

        else if (usage_string.equalsIgnoreCase("four days a week"))
        {
            usage=4;

            toastMessage("Usage is"+usage.toString());
        }


        else if (usage_string.equalsIgnoreCase("five days a week"))
        {
            usage=5;

            toastMessage("Usage is"+usage.toString());
        }

        else if (usage_string.equalsIgnoreCase("six days a week"))
        {
            usage=6;

            toastMessage("Usage is"+usage.toString());
        }








        if (appliancetype_string.equalsIgnoreCase("Refrigerator")||appliancetype_string.equalsIgnoreCase("Laundary"))
        {
            appliancetype=1;

            toastMessage("Appliance Type is"+appliancetype.toString());
        }

        else if (appliancetype_string.equalsIgnoreCase("laundary"))
        {
            appliancetype=3;
            toastMessage("Appliance Type is"+appliancetype.toString());
        }
        else
        {
            appliancetype=3;
            toastMessage("Appliance Type is"+appliancetype.toString());
        }



        toastMessage("You must put something in the text field!");



        //int usage = Integer.parseInt(usage_string);     //sajid
        //int appliancetype = Integer.parseInt(appliancetype_string);    //sajid

        //int apptype = Integer.parseInt(rating);


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SCHEDULERINPUT_DEVICE_NAME, devicename);
        contentValues.put(COL_SCHEDULERINPUT__APPLIANCE_TYPE, appliancetype);
        contentValues.put(COL_SCHEDULERINPUT_MAXIMUMTIME, maxtime);
        contentValues.put(COL_SCHEDULERINPUT_USAGE, usage);
        contentValues.put(COL_SCHEDULERINPUT_RATING, power);
        contentValues.put(COL_SCHEDULERINPUT_LOWER_LIMIT1, ll1);
        contentValues.put(COL_SCHEDULERINPUT_LOWER_LIMIT2, ll2);
        contentValues.put(COL_SCHEDULERINPUT_UPPER_LIMIT1, ul1);
        contentValues.put(COL_SCHEDULERINPUT_UPPER_LIMIT2, ul2);

        //  contentValues.put(COL2, item);



        //Log.d(TAG, "addData: Adding " + item + " to " + TABLE_SCHEDULERINPUT);

        long result = db.insert(TABLE_SCHEDULERINPUT, null, contentValues);

        // long result = db.insert(TABLE_NAME, null, contentValues);



        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    private void toastMessage(String s) {





    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SCHEDULERINPUT;

        //String query = "SELECT * FROM " + TABLE_NAME;

        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getUserData(){



        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER;

        //String query = "SELECT * FROM " + TABLE_NAME;

        Cursor data = db.rawQuery(query, null);
        return data;
    }






    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_SCHEDULERINPUT_ID + " FROM " + TABLE_SCHEDULERINPUT +
                " WHERE " + COL_SCHEDULERINPUT_DEVICE_NAME + " = '" + name + "'";

        // String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
        //       " WHERE " + COL2 + " = '" + name + "'";

        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_SCHEDULERINPUT + " SET " + COL_SCHEDULERINPUT_DEVICE_NAME +
                " = '" + newName + "' WHERE " + COL_SCHEDULERINPUT_ID + " = '" + id + "'" +
                " AND " + COL_SCHEDULERINPUT_DEVICE_NAME + " = '" + oldName + "'";

        //String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
        //      " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
        //    " AND " + COL2 + " = '" + oldName + "'";

        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_SCHEDULERINPUT + " WHERE "
                + COL_SCHEDULERINPUT_ID + " = '" + id + "'" +
                " AND " + COL_SCHEDULERINPUT_DEVICE_NAME + " = '" + name + "'";

           db.execSQL(query);
    }

}
