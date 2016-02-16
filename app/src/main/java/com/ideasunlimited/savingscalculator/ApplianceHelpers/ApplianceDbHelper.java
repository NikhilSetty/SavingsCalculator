package com.ideasunlimited.savingscalculator.ApplianceHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.ideasunlimited.savingscalculator.DB.DbHelper;
import com.ideasunlimited.savingscalculator.DB.DbTableConstants;
import com.ideasunlimited.savingscalculator.Model.ApplianceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nravishankar on 1/28/2016.
 */
public class ApplianceDbHelper {

    static DbHelper DBHELPER;
    static SQLiteDatabase db;

    public static List<ApplianceModel> GetAppliancesForAnArea(Context context, String areaId){
        try {
            DBHELPER = new DbHelper(context);
            db = DBHELPER.getWritableDatabase();

            List<ApplianceModel> list = new ArrayList<>();

            Cursor c = db.rawQuery("SELECT * FROM " +
                    DbTableConstants.APPLIANCE_TABLE_NAME, null);

            if (c != null ) {
                if  (c.moveToFirst()) {
                    do {
                        ApplianceModel model = new ApplianceModel();

                        model.ApplianceName = c.getString(c.getColumnIndex(DbTableConstants.APPLIANCE_NAME));
                        model.ApplianceAreaId = c.getString(c.getColumnIndex(DbTableConstants.APPLIANCE_AREA_ID));
                        model.ApplianceWattage = c.getString(c.getColumnIndex(DbTableConstants.APPLIANCE_WATTAGE));
                        model.ApplianceQuantity = c.getString(c.getColumnIndex(DbTableConstants.APPLIANCE_QUANTITY));
                        model.ApplianceWorkingHours = c.getString(c.getColumnIndex(DbTableConstants.APPLIANCE_WORKING_HOURS));
                        model.ApplianceActiveHours = c.getString(c.getColumnIndex(DbTableConstants.APPLIANCE_ACTIVE_HOURS));
                        model.ApplianceCostPerUnitElectricity = c.getString(c.getColumnIndex(DbTableConstants.APPLIANCE_COST_PER_UNIT_ELECTRICITY));
                        model.ApplianceCostPerDay = c.getString(c.getColumnIndex(DbTableConstants.APPLIANCE_COST_PER_DAY));
                        model.ApplianceCostPerDayAfterSensor = c.getString(c.getColumnIndex(DbTableConstants.APPLIANCE_SAVINGS_PER_DAY));
                        model.ApplianceCostPerMonth = c.getString(c.getColumnIndex(DbTableConstants.APPLIANCE_COST_PER_MONTH));
                        model.ApplianceCostPerMonthAfterSensor = c.getString(c.getColumnIndex(DbTableConstants.APPLIANCE_SAVINGS_PER_MONTH));
                        model._id = c.getString(c.getColumnIndex("_id"));

                        list.add(model);
                    }while (c.moveToNext());
                }
                c.close();
            }

            return list;

        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public static ApplianceModel[] GetAppliancesAsArrayForAnArea(Context context, String areaId){
        List<ApplianceModel> list = GetAppliancesForAnArea(context, areaId);
        if(list !=null) {
            ApplianceModel[] array = new ApplianceModel[list.size()];

            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
            }

            return array;
        }else{
            return null;
        }
    }

    public static boolean AddAppliancetoDB(Context context, ApplianceModel model)
    {
        try
        {
            DBHELPER = new DbHelper(context);
            db = DBHELPER.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DbTableConstants.APPLIANCE_AREA_ID, model.ApplianceAreaId);
            values.put(DbTableConstants.APPLIANCE_NAME, model.ApplianceName);
            values.put(DbTableConstants.APPLIANCE_WATTAGE, model.ApplianceWattage);
            values.put(DbTableConstants.APPLIANCE_QUANTITY, model.ApplianceQuantity);
            values.put(DbTableConstants.APPLIANCE_WORKING_HOURS, model.ApplianceWorkingHours);
            values.put(DbTableConstants.APPLIANCE_ACTIVE_HOURS, model.ApplianceActiveHours);
            values.put(DbTableConstants.APPLIANCE_COST_PER_UNIT_ELECTRICITY, model.ApplianceCostPerUnitElectricity);
            values.put(DbTableConstants.APPLIANCE_COST_PER_DAY, model.ApplianceCostPerDay);
            values.put(DbTableConstants.APPLIANCE_COST_PER_MONTH, model.ApplianceCostPerMonth);
            values.put(DbTableConstants.APPLIANCE_SAVINGS_PER_DAY, model.ApplianceCostPerDayAfterSensor);
            values.put(DbTableConstants.APPLIANCE_SAVINGS_PER_MONTH, model.ApplianceCostPerMonthAfterSensor);

            db.insert(DbTableConstants.APPLIANCE_TABLE_NAME, null, values);
            return true;
        }
        catch(Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("DB ERROR", e.toString());
            return false;
        }

    }
}
