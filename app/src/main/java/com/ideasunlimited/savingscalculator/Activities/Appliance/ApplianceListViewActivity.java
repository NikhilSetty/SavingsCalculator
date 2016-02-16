package com.ideasunlimited.savingscalculator.Activities.Appliance;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ideasunlimited.savingscalculator.ApplianceHelpers.ApplianceDbHelper;
import com.ideasunlimited.savingscalculator.ApplianceHelpers.ApplianceListViewAdapter;
import com.ideasunlimited.savingscalculator.AreaHelpers.AreaDbHelper;
import com.ideasunlimited.savingscalculator.Constants;
import com.ideasunlimited.savingscalculator.DB.DbHelper;
import com.ideasunlimited.savingscalculator.Model.ApplianceModel;
import com.ideasunlimited.savingscalculator.Model.AreaModel;
import com.ideasunlimited.savingscalculator.R;

import java.util.ArrayList;
import java.util.List;

public class ApplianceListViewActivity extends AppCompatActivity implements AdapterViewCompat.OnItemSelectedListener
{
    static DbHelper DBHELPER;
    static SQLiteDatabase db;

    ListView mApplianceListView;
    String mAreaId;
    ApplianceModel[] mApplianceArray;
    AreaModel mAreaModel;

    AlertDialog alertDialog;
    int mCostPerUnitElectricity;
    Button mButtonCostPerUnitElectricity;

    Button _buttonSaveApplianceDetails;
    Button _buttonCancelApplianceDetails;

    Spinner _spinnerApplianceName;

    EditText _editTextApplianceWattage;
    EditText _editTextApplianceQuantity;
    EditText _editTextApplianceWorkingHours;
    EditText _editTextApplianceActiveHours;
    EditText _editTextApplianceCostPerUnitElectricity;

    String _ApplianceName;
    String _ApplianceWattage;
    String _ApplianceQuantity;
    String _ApplianceWorkingHours;
    String _ApplianceActiveHours;
    String _ApplianceCostPerUnitElectricity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_list_view);

        Intent i = getIntent();
        mAreaId = i.getStringExtra(Constants.AreaIdExtraString);

        mApplianceListView = (ListView) findViewById(R.id.listViewApplianceList);
        mButtonCostPerUnitElectricity = (Button) findViewById(R.id.buttonCostPerUnit);
    }

    @Override
    protected void onStart(){
        super.onStart();

        mApplianceArray = ApplianceDbHelper.GetAppliancesAsArrayForAnArea(this, mAreaId);
        mAreaModel = AreaDbHelper.GetAreaDetailsForAreaId(this, mAreaId);

        if(mApplianceArray != null) {
            if (mApplianceArray.length == 0) {
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
            ApplianceListViewAdapter adapter = new ApplianceListViewAdapter(this, mApplianceArray);

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

        if (id == R.id.add_appliance)
        {
            CreateAddApplianceDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    // kartik added this method
    public String checkEditText(EditText editText, String message)
    {
        String _content_of_editText = editText.getText().toString();
        if(_content_of_editText.isEmpty())
        {
            Toast.makeText(ApplianceListViewActivity.this, message, Toast.LENGTH_LONG).show();
            editText.getBackground().setColorFilter(getResources().getColor(R.color.primaryColor), PorterDuff.Mode.SRC_ATOP);
            return "";
        }
        else
        {
            return _content_of_editText;
        }
    }

    // kartik added this method
    public void SaveApplianceDetails(View view)
    {
        ApplianceModel applianceModel = new ApplianceModel();

        _ApplianceWattage = checkEditText(_editTextApplianceWattage,"Please Enter Appliance Wattage");
        _ApplianceQuantity = checkEditText(_editTextApplianceQuantity,"Please Enter Appliance Quantity");
        _ApplianceWorkingHours = checkEditText(_editTextApplianceWorkingHours,"Please Appliance Enter Working Hours");
        _ApplianceActiveHours = checkEditText(_editTextApplianceActiveHours,"Please Appliance Active Hours");
        _ApplianceCostPerUnitElectricity = checkEditText(_editTextApplianceCostPerUnitElectricity,"Please Enter Appliance Cost per Unit Electricity");

        applianceModel.ApplianceAreaId = "12345";
        applianceModel.ApplianceName = _ApplianceName;
        applianceModel.ApplianceWattage = _ApplianceWattage;
        applianceModel.ApplianceQuantity = _ApplianceQuantity;
        applianceModel.ApplianceWorkingHours = _ApplianceWorkingHours;
        applianceModel.ApplianceActiveHours = _ApplianceActiveHours;
        applianceModel.ApplianceCostPerUnitElectricity = _ApplianceCostPerUnitElectricity;
        applianceModel.ApplianceCostPerDay = "120";
        applianceModel.ApplianceCostPerMonth = "3600";
        applianceModel.ApplianceCostPerDayAfterSensor = "100";
        applianceModel.ApplianceCostPerMonthAfterSensor = "3000";

        if(_ApplianceName == "Select an Item")
        {
            Toast.makeText(this, "Please select a valid appliance", Toast.LENGTH_SHORT).show();
        }
        else if(_ApplianceWattage == "")
        {
            Toast.makeText(this,"Please enter Appliance Wattage",Toast.LENGTH_SHORT).show();
        }
        else if(_ApplianceQuantity == "")
        {
            Toast.makeText(this,"Please enter Appliance Quantity",Toast.LENGTH_SHORT).show();
        }
        else if(_ApplianceWorkingHours == "")
        {
            Toast.makeText(this,"Please enter Appliance Working Hours",Toast.LENGTH_SHORT).show();
        }
        else if(_ApplianceActiveHours == "")
        {
            Toast.makeText(this,"Please enter Appliance Active Hours",Toast.LENGTH_SHORT).show();
        }
        else if(_ApplianceCostPerUnitElectricity == "")
        {
            Toast.makeText(this,"Please enter Appliance Cost per unit Electricity",Toast.LENGTH_SHORT).show();
        }
        else
        {
            ApplianceDbHelper.AddAppliancetoDB(this, applianceModel);
            Toast.makeText(this, "New Appliance has been added", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void CancelApplianceDetails(View view)
    {
        dismissAlertDialog();
    }

    // kartik added this method
    public void dismissAlertDialog()
    {
        alertDialog.dismiss();
    }

    // kartik added this method
    public void CreateAddApplianceDialog()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View addApplianceView = layoutInflater.inflate(R.layout.alert_add_appliance, null);

        _buttonSaveApplianceDetails = (Button)addApplianceView.findViewById(R.id.button_appliance_save);
        _buttonCancelApplianceDetails = (Button)addApplianceView.findViewById(R.id.button_appliance_cancel);

        _editTextApplianceWattage = (EditText)addApplianceView.findViewById(R.id.edittext_appliance_wattage);
        _editTextApplianceQuantity = (EditText)addApplianceView.findViewById(R.id.edittext_appliance_quantity);
        _editTextApplianceWorkingHours = (EditText)addApplianceView.findViewById(R.id.edittext_appliance_working_hours);
        _editTextApplianceActiveHours = (EditText)addApplianceView.findViewById(R.id.edittextappliance_active_hours);
        _editTextApplianceCostPerUnitElectricity = (EditText)addApplianceView.findViewById(R.id.edittext_appliance_cost_per_unit_electricity);

        _spinnerApplianceName = (Spinner)addApplianceView.findViewById(R.id.spinnerApplianceName);

        ArrayAdapter<CharSequence> _applianceArrayAdapter = ArrayAdapter.createFromResource(this, R.array.appliances, android.R.layout.simple_spinner_dropdown_item);
        _spinnerApplianceName.setAdapter(_applianceArrayAdapter);


        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(addApplianceView);
        alertDialogBuilder.setTitle("Add new Appliance");

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onItemSelected(AdapterViewCompat<?> parent, View view, int position, long id)
    {
        CharSequence charSequence;
        charSequence = (CharSequence)parent.getItemAtPosition(position);
        _ApplianceName = charSequence.toString();
    }

    @Override
    public void onNothingSelected(AdapterViewCompat<?> parent)
    {

    }

    private void setCostPerUnitButtonText(){
        mButtonCostPerUnitElectricity.setText("COST PER UNIT : " + mCostPerUnitElectricity);
    }

    public void ChangeCostPerUnit(View view) {
        try{
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View changeCpuView = layoutInflater.inflate(R.layout.alert_change_cost_per_unit, null);

            final EditText requestEditText = (EditText) changeCpuView.findViewById(R.id.editTextApplianceCostPerUnitElectricity);
            requestEditText.setText(String.valueOf(mCostPerUnitElectricity));

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
