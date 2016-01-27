package com.ideasunlimited.savingscalculator.Activities.Area;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ideasunlimited.savingscalculator.AreaHelper.AreaDbHelper;
import com.ideasunlimited.savingscalculator.Constants;
import com.ideasunlimited.savingscalculator.CustomerHelpers.CustomerDbHelper;
import com.ideasunlimited.savingscalculator.Model.AreaModel;
import com.ideasunlimited.savingscalculator.Model.CustomerModel;
import com.ideasunlimited.savingscalculator.R;
import com.ideasunlimited.savingscalculator.ViewModel.AreaListChildDViewModel;
import com.ideasunlimited.savingscalculator.ViewModel.CustomerListChildViewModel;

import java.util.HashMap;
import java.util.List;

public class AreaListActivity extends AppCompatActivity {

    String mCustomerId;
    CustomerModel mCustomerModel;
    CustomerListChildViewModel mCustomerListChildViewModel;
    List<AreaModel> mAreaList;
    HashMap<AreaModel, AreaListChildDViewModel> mChildViewList;

    TextView mTextViewCustomerEmailId;
    TextView mTextViewCustomerName;
    TextView mTextViewCustomerAddress;
    TextView mTextViewCustomerOwnerName;
    TextView mTextViewCustomerContactPersonName;
    TextView mTextViewCustomerTotalSavings;

    Button mAddAreaButton;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_list);

        Intent intent = getIntent();
        mCustomerId = intent.getStringExtra(Constants.CustomerIdExtraString);

        mTextViewCustomerName = (TextView) findViewById(R.id.textView_AL_CustomerName);
        mTextViewCustomerEmailId = (TextView) findViewById(R.id.textView_AL_emailId);
        mTextViewCustomerAddress = (TextView) findViewById(R.id.textView_AL_CustomerAddress);
        mTextViewCustomerOwnerName = (TextView) findViewById(R.id.textView_AL_CustomerOwnerName);
        mTextViewCustomerContactPersonName = (TextView) findViewById(R.id.textView_AL_CustomerContactPersonName);
        mTextViewCustomerTotalSavings = (TextView) findViewById(R.id.textView_AL_TotalSavings);

        mAddAreaButton = (Button) findViewById(R.id.button_AL_AddArea);
    }

    @Override
    protected void onStart(){
        super.onStart();

        mContext = this;

        if(mCustomerId != null) {
            mCustomerModel = CustomerDbHelper.GetCustomerModelFromId(getApplicationContext(), mCustomerId);
            mAreaList = AreaDbHelper.GetAreasFromCustomerId(getApplicationContext(), mCustomerId);
            mCustomerListChildViewModel = CustomerDbHelper.GetChildDetailsForCustomer(getApplicationContext(), mCustomerModel);

            if(mCustomerModel != null){
                mTextViewCustomerName.setText(mCustomerModel.CustomerName);
                mTextViewCustomerEmailId.setText(mCustomerModel.CustomerEmailId);
                mTextViewCustomerAddress.setText(mCustomerModel.CustomerLocation);
                mTextViewCustomerOwnerName.setText("Owner Name : " + mCustomerModel.CustomerOwnerName);
                mTextViewCustomerContactPersonName.setText("Contact Person : " + mCustomerModel.CustomerContactPersonName);
                mTextViewCustomerTotalSavings.setText("Total Savings : " + mCustomerListChildViewModel.TotalSavings);

                mAddAreaButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(mContext, AddAreaActivity.class);
                        i.putExtra(Constants.CustomerIdExtraString, mCustomerId);
                        startActivity(i);
                    }
                });

                PopulateDataInListView();

            }else{
                Toast.makeText(AreaListActivity.this, "Something Went Wrong.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void PopulateDataInListView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_area_list, menu);
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
