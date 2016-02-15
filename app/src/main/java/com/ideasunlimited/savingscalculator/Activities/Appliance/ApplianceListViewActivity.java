package com.ideasunlimited.savingscalculator.Activities.Appliance;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ideasunlimited.savingscalculator.ApplianceHelpers.ApplianceDbHelper;
import com.ideasunlimited.savingscalculator.ApplianceHelpers.ApplianceListViewAdapter;
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

    int mCostPerUnitElectricity;
    Button mButtonCostPerUnitElectricity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_list_view);

        Intent i = getIntent();
        mAreaId = i.getStringExtra(Constants.AreaIdExtraString);

        mApplianceListView = (ListView) findViewById(R.id.listViewApplianceList);
        mButtonCostPerUnitElectricity = (Button) findViewById(R.id.buttonCostPerUnit);
        mApplianceList = new ArrayList<>();
    }

    @Override
    protected void onStart(){
        super.onStart();

        mApplianceList = ApplianceDbHelper.GetAppliancesForAnArea(this, mAreaId);
        mAreaModel = AreaDbHelper.GetAreaDetailsForAreaId(this, mAreaId);

        if(mApplianceList != null) {
            if (mApplianceList.size() == 0) {
                mCostPerUnitElectricity = 5;
                setCostPerUnitButtonText();
                // todo Replace with error text
            } else {
                PopulateListView();
            }
        }
    }

    private void PopulateListView() {
        try{
            ApplianceListViewAdapter adapter = new ApplianceListViewAdapter(this, (ApplianceModel[]) mApplianceList.toArray());

            mApplianceListView.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(ApplianceListViewActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            // todo Replace with error text
        }
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

    private void setCostPerUnitButtonText(){
        mButtonCostPerUnitElectricity.setText("COST PER UNIT : " + mCostPerUnitElectricity);
    }

    public void ChangeCostPerUnit(View view) {
        try{
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View changeCpuView = layoutInflater.inflate(R.layout.alert_change_cost_per_unit, null);

            final EditText requestEditText = (EditText) changeCpuView.findViewById(R.id.editTextApplianceCostPerUnitElectricity);
            requestEditText.setText(mCostPerUnitElectricity);

            Button saveButton = (Button) changeCpuView.findViewById(R.id.button_AL_alert_Save);
            Button cancelButton = (Button) changeCpuView.findViewById(R.id.button_AL_alert_Cancel);

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setView(changeCpuView);
            alertDialogBuilder.setMessage("Cost Per Unit Electricity :");
            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String cpuEntered = requestEditText.getText().toString();
                    if (cpuEntered != null && !cpuEntered.isEmpty()) {
                        mCostPerUnitElectricity = Integer.parseInt(cpuEntered);
                        setCostPerUnitButtonText();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(ApplianceListViewActivity.this, "Please Enter a valid Cost Per Unit!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });


        }catch(Exception e){
            Toast.makeText(ApplianceListViewActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
