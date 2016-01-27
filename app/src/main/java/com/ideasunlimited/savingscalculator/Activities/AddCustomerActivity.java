package com.ideasunlimited.savingscalculator.Activities;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ideasunlimited.savingscalculator.CustomerHelpers.CustomerDbHelper;
import com.ideasunlimited.savingscalculator.Model.CustomerModel;
import com.ideasunlimited.savingscalculator.R;

public class AddCustomerActivity extends AppCompatActivity {

    EditText _editTextCustomerName;
    EditText _editTextCustomerAddress;
    EditText _editTextCustomerEmailId;
    EditText _editTextCustomerWebsite;
    EditText _editTextCustomerLandlineNumber;
    EditText _editTextCustomerOwnerName;
    EditText _editTextCustomerContactNumber;
    EditText _editTextCustomerContactPerson;
    EditText _editTextCustomerContactPersonContactNumber;

    String _customerName;
    String _customerAddress;
    String _customerEmailId;
    String _customerWebsite;
    String _customerLandlineNumber;
    String _customerOwnerName;
    String _customerContactNumber;
    String _customerContactPerson;
    String _customerContactPersonContactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        _editTextCustomerName = (EditText) findViewById(R.id.edittextCustomerName);
        _editTextCustomerAddress = (EditText) findViewById(R.id.edittextCustomerLocation);
        _editTextCustomerEmailId = (EditText) findViewById(R.id.edittextCustomerEmailId);
        _editTextCustomerWebsite = (EditText) findViewById(R.id.edittextCustomerWebsite);
        _editTextCustomerLandlineNumber = (EditText) findViewById(R.id.edittextCustomerLandlineNumber);
        _editTextCustomerOwnerName = (EditText) findViewById(R.id.edittextCustomerOwnerName);
        _editTextCustomerContactNumber = (EditText) findViewById(R.id.edittextCustomerPhoneNumber);
        _editTextCustomerContactPerson = (EditText) findViewById(R.id.edittextCustomerContactPersonName);
        _editTextCustomerContactPersonContactNumber = (EditText) findViewById(R.id.edittextCustomerContactPersonContactNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_customer, menu);
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

    public void SaveCustomerDetails(View view) {
        try{
            CustomerModel customerModel = new CustomerModel();

            _customerName = _editTextCustomerName.getText().toString();
            if(_customerName.isEmpty()){
                Toast.makeText(AddCustomerActivity.this, "Please Enter Customer Name!", Toast.LENGTH_LONG).show();
                _editTextCustomerName.getBackground().setColorFilter(getResources().getColor(R.color.primaryColor), PorterDuff.Mode.SRC_ATOP);
                return;
            }

            _customerAddress = _editTextCustomerAddress.getText().toString();
            if(_customerAddress.isEmpty()){
                Toast.makeText(AddCustomerActivity.this, "Please Enter Customer Address!", Toast.LENGTH_LONG).show();
                _editTextCustomerAddress.getBackground().setColorFilter(getResources().getColor(R.color.primaryColor), PorterDuff.Mode.SRC_ATOP);
                return;
            }

            _customerLandlineNumber = _editTextCustomerLandlineNumber.getText().toString();
            if(_customerLandlineNumber.isEmpty()){
                Toast.makeText(AddCustomerActivity.this, "Please Enter Contact Number for the Customer!", Toast.LENGTH_LONG).show();
                _editTextCustomerLandlineNumber.getBackground().setColorFilter(getResources().getColor(R.color.primaryColor), PorterDuff.Mode.SRC_ATOP);
                return;
            }

            _customerEmailId = _editTextCustomerEmailId.getText().toString();
            _customerWebsite = _editTextCustomerWebsite.getText().toString();
            _customerContactPerson = _editTextCustomerContactPerson.getText().toString();
            _customerOwnerName = _editTextCustomerOwnerName.getText().toString();
            _customerContactNumber = _editTextCustomerContactNumber.getText().toString();
            _customerContactPersonContactNumber = _editTextCustomerContactPersonContactNumber.getText().toString();

            customerModel.CustomerName = _customerName;
            customerModel.CustomerLocation = _customerAddress;
            customerModel.CustomerEmailId = _customerEmailId;
            customerModel.CustomerWebsite = _customerWebsite;
            customerModel.CustomerLandlineNumber = _customerLandlineNumber;
            customerModel.CustomerOwnerName = _customerOwnerName;
            customerModel.CustomerPhoneNumber = _customerContactNumber;
            customerModel.CustomerContactPersonName = _customerContactPerson;
            customerModel.CustomerContactPersonContactNumber = _customerContactPersonContactNumber;

            CustomerDbHelper.AddCustomerToDb(getApplicationContext(), customerModel);
            Toast.makeText(AddCustomerActivity.this, "New Customer Added.", Toast.LENGTH_SHORT).show();

            finish();
        }catch (Exception e){
            Toast.makeText(AddCustomerActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
