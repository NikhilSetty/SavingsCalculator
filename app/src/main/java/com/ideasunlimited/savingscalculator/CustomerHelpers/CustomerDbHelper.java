package com.ideasunlimited.savingscalculator.CustomerHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.ideasunlimited.savingscalculator.AreaHelper.AreaDbHelper;
import com.ideasunlimited.savingscalculator.DB.DbHelper;
import com.ideasunlimited.savingscalculator.DB.DbTableConstants;
import com.ideasunlimited.savingscalculator.Model.AreaModel;
import com.ideasunlimited.savingscalculator.Model.CustomerModel;
import com.ideasunlimited.savingscalculator.ViewModel.ChildDisplayModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nravishankar on 1/19/2016.
 */
public class CustomerDbHelper {

    static DbHelper DBHELPER;
    static SQLiteDatabase db;

    public static boolean AddCustomerToDb(Context context, CustomerModel model){
        try{
            DBHELPER = new DbHelper(context);
            db = DBHELPER.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(DbTableConstants.CUSTOMER_NAME, model.CustomerName);
            values.put(DbTableConstants.CUSTOMER_ADDRESS, model.CustomerLocation);
            values.put(DbTableConstants.CUSTOMER_EMAIL_ID, model.CustomerEmailId);
            values.put(DbTableConstants.CUSTOMER_WEBSITE, model.CustomerWebsite);
            values.put(DbTableConstants.CUSTOMER_LANDLINE_NUMBER, model.CustomerLandlineNumber);
            values.put(DbTableConstants.CUSTOMER_OWNER_NAME, model.CustomerOwnerName);
            values.put(DbTableConstants.CUSTOMER_PHONE_NUMBER, model.CustomerPhoneNumber);
            values.put(DbTableConstants.CUSTOMER_CONTACT_PERSON_NAME, model.CustomerContactPersonName);
            values.put(DbTableConstants.CUSTOMER_CONTACT_PERSON_CONTACT_NUMBER, model.CustomerContactPersonContactNumber);

            db.insert(DbTableConstants.CUSTOMER_TABLE_NAME, null, values);

            return true;
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static List<CustomerModel> GetAllCustomers(Context context) {
        try{
            DBHELPER = new DbHelper(context);
            db = DBHELPER.getWritableDatabase();

            List<CustomerModel> list = new ArrayList<CustomerModel>();

            Cursor c = db.rawQuery("SELECT * FROM " +
                    DbTableConstants.CUSTOMER_TABLE_NAME, null);

            if (c != null ) {
                if  (c.moveToFirst()) {
                    do {
                        CustomerModel model = new CustomerModel();

                        model.CustomerName = c.getString(c.getColumnIndex(DbTableConstants.CUSTOMER_NAME));
                        model.CustomerLocation = c.getString(c.getColumnIndex(DbTableConstants.CUSTOMER_ADDRESS));
                        model.CustomerEmailId = c.getString(c.getColumnIndex(DbTableConstants.CUSTOMER_EMAIL_ID));
                        model.CustomerWebsite = c.getString(c.getColumnIndex(DbTableConstants.CUSTOMER_WEBSITE));
                        model.CustomerLandlineNumber = c.getString(c.getColumnIndex(DbTableConstants.CUSTOMER_LANDLINE_NUMBER));
                        model.CustomerOwnerName = c.getString(c.getColumnIndex(DbTableConstants.CUSTOMER_PHONE_NUMBER));
                        model.CustomerPhoneNumber = c.getString(c.getColumnIndex(DbTableConstants.CUSTOMER_PHONE_NUMBER));
                        model.CustomerContactPersonName = c.getString(c.getColumnIndex(DbTableConstants.CUSTOMER_CONTACT_PERSON_NAME));
                        model.CustomerContactPersonContactNumber = c.getString(c.getColumnIndex(DbTableConstants.CUSTOMER_CONTACT_PERSON_CONTACT_NUMBER));
                        model._id = c.getString(c.getColumnIndex("_id"));

                        list.add(model);
                    }while (c.moveToNext());
                }
            }

            return list;
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return new ArrayList<CustomerModel>();
        }
    }

    public static ChildDisplayModel GetChildDetailsForCustomer(Context context, CustomerModel customerModel){

        try {
            DBHELPER = new DbHelper(context);
            db = DBHELPER.getWritableDatabase();

            List<AreaModel> areaList = AreaDbHelper.GetAreasFromCustomerId(context, customerModel._id);

            ChildDisplayModel model = new ChildDisplayModel();

            model.ContactNumber = customerModel.CustomerLandlineNumber;
            model.NumberOfAreas = Integer.toString(areaList.size());

            double totalSavings = 0;

            for(int i = 0; i < areaList.size(); i++){
                totalSavings += Double.parseDouble(areaList.get(i).AreaSavingsPerMonth);
            }

            model.TotalSavings = Double.toString(totalSavings);

            return model;
            
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return new ChildDisplayModel();
        }
    }
}
