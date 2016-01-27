package com.ideasunlimited.savingscalculator.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper{
	public DbHelper(Context context) {
		super(context, DatabaseHelperMeta.DB_NAME, null, DatabaseHelperMeta.DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v("creating " , "DB");
		db.execSQL(Schema.CREATE_TABLE_CUSOMTER);
        db.execSQL(Schema.CREATE_TABLE_AREA);
        db.execSQL(Schema.CREATE_TABLE_APPLIANCE);
		Log.v("created " , "DB");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO
	}

}
