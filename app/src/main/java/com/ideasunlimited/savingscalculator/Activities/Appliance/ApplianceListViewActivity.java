package com.ideasunlimited.savingscalculator.Activities.Appliance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.ideasunlimited.savingscalculator.ApplianceHelpers.ApplianceDbHelper;
import com.ideasunlimited.savingscalculator.AreaHelpers.AreaDbHelper;
import com.ideasunlimited.savingscalculator.Constants;
import com.ideasunlimited.savingscalculator.Model.ApplianceModel;
import com.ideasunlimited.savingscalculator.Model.AreaModel;
import com.ideasunlimited.savingscalculator.R;

import java.util.ArrayList;
import java.util.List;

public class ApplianceListViewActivity extends AppCompatActivity {

    ListView mApplianceListView;
    String mAreaId;
    List<ApplianceModel> mApplianceList;
    AreaModel mAreaModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_list_view);

        Intent i = getIntent();
        mAreaId = i.getStringExtra(Constants.AreaIdExtraString);

        mApplianceListView = (ListView) findViewById(R.id.listViewApplianceList);
        mApplianceList = new ArrayList<>();
    }

    @Override
    protected void onStart(){
        super.onStart();

        mApplianceList = ApplianceDbHelper.GetAppliancesForAnArea(this, mAreaId);
        mAreaModel = AreaDbHelper.GetAreaDetailsForAreaId(this, mAreaId);

        if(mApplianceList != null) {
            if (mApplianceList.size() == 0) {

            } else {
                PopulateListView();
            }
        }
    }

    private void PopulateListView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appliance_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
