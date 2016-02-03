package com.ideasunlimited.savingscalculator.AreaHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.ideasunlimited.savingscalculator.ApplianceHelpers.ApplianceDbHelper;
import com.ideasunlimited.savingscalculator.DB.DbHelper;
import com.ideasunlimited.savingscalculator.DB.DbTableConstants;
import com.ideasunlimited.savingscalculator.Model.ApplianceModel;
import com.ideasunlimited.savingscalculator.Model.AreaModel;
import com.ideasunlimited.savingscalculator.Model.CustomerModel;
import com.ideasunlimited.savingscalculator.ViewModel.AreaListChildViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nravishankar on 1/20/2016.
 */
public class AreaDbHelper {

    static DbHelper DBHELPER;
    static SQLiteDatabase db;

    public static List<AreaModel> GetAreasFromCustomerId(Context context, String customerId){
        try{

            DBHELPER = new DbHelper(context);
            db = DBHELPER.getWritableDatabase();

            List<AreaModel> list = new ArrayList<AreaModel>();

            Cursor c = db.rawQuery("SELECT * FROM " +
                    DbTableConstants.AREA_TABLE_NAME + " WHERE " + DbTableConstants.AREA_CUSTOMER_ID + " = " + customerId, null);

            if (c != null ) {
                if  (c.moveToFirst()) {
                    do {
                        AreaModel model = new AreaModel();

                        model._id = c.getString(c.getColumnIndex("_id"));
                        model.AreaName = c.getString(c.getColumnIndex(DbTableConstants.AREA_NAME));
                        model.AreaSize = c.getString(c.getColumnIndex(DbTableConstants.AREA_SIZE));
                        model.CustomerId = c.getString(c.getColumnIndex(DbTableConstants.AREA_CUSTOMER_ID));
                        model.AreaSavingsPerDay = c.getString(c.getColumnIndex(DbTableConstants.AREA_TOTAL_SAVINGS_PER_DAY));
                        model.AreaSavingsPerMonth = c.getString(c.getColumnIndex(DbTableConstants.AREA_TOTAL_SAVINGS_PER_MONTH));
                        if(model.AreaSavingsPerDay == null){
                            model.AreaSavingsPerDay = "0";
                        }
                        if(model.AreaSavingsPerMonth == null){
                            model.AreaSavingsPerMonth = "0";
                        }

                        list.add(model);
                    }while (c.moveToNext());
                }

                c.close();
            }

            return list;
        }catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            Log.e("ERROR", e.toString() );
            return new ArrayList<>();
        }
    }

    public static List<AreaModel> GetAreasFromCustomeModel(Context context, CustomerModel customerModel){
        return GetAreasFromCustomerId(context, customerModel._id);
    }

    public static boolean AddArea(Context context, AreaModel model) {
        try
        {
            DBHELPER = new DbHelper(context);
            db = DBHELPER.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DbTableConstants.AREA_CUSTOMER_ID, model.CustomerId);
            values.put(DbTableConstants.AREA_NAME, model.AreaName);
            values.put(DbTableConstants.AREA_SIZE, model.AreaSize);

            db.insert(DbTableConstants.AREA_TABLE_NAME, null, values);

            return true;
        }catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            Log.e("ERROR", e.toString() );
            return false;
        }

    }

    public static AreaListChildViewModel GetChildViewForAnArea(Context context, String areaId) {
        try
        {
            DBHELPER = new DbHelper(context);
            db = DBHELPER.getWritableDatabase();

            AreaListChildViewModel childModel = new AreaListChildViewModel();

            List<ApplianceModel> applianceModels = ApplianceDbHelper.GetAppliancesForAnArea(context, areaId);
            if(applianceModels != null){
                childModel.NumberOfAppliances = Integer.toString(applianceModels.size());

                double currentCost = 0;
                double costAfterSensor = 0;
                for(int i = 0; i < applianceModels.size(); i++){
                    currentCost += Double.parseDouble(applianceModels.get(i).ApplianceCostPerMonth);
                    costAfterSensor += Double.parseDouble(applianceModels.get(i).ApplianceCostPerMonthAfterSensor);
                }

                childModel.CurrentCost = Double.toString(currentCost);
                childModel.CostAfterMotionSensorInstalled = Double.toString(costAfterSensor);

            }else{
                return null;
            }

            return childModel;

        }catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            Log.e("ERROR", e.toString() );
            return null;
        }
    }
}
