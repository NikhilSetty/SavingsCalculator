package com.ideasunlimited.savingscalculator.Activities.Area;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ideasunlimited.savingscalculator.AreaHelper.AreaDbHelper;
import com.ideasunlimited.savingscalculator.Constants;
import com.ideasunlimited.savingscalculator.Model.AreaModel;
import com.ideasunlimited.savingscalculator.R;

public class AddAreaActivity extends AppCompatActivity {

    EditText _editTextAreaName;
    EditText _editTextAreaSize;

    String _areaName;
    String _areaSize;

    String mCustomerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_area);

        _editTextAreaName = (EditText) findViewById(R.id.edittextAreaName);
        _editTextAreaSize = (EditText) findViewById(R.id.edittextAreaSize);

        Intent intent = getIntent();
        mCustomerId = intent.getStringExtra(Constants.CustomerIdExtraString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_area, menu);
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

    public void AddArea(View view) {
        AddAreaToDb();
        finish();
    }

    public void AddAreaToDb(){
        AreaModel model = new AreaModel();

        _areaName = _editTextAreaName.getText().toString();
        if(_areaName.isEmpty()){
            Toast.makeText(AddAreaActivity.this, "Please Enter Area Name!", Toast.LENGTH_LONG).show();
            _editTextAreaName.getBackground().setColorFilter(getResources().getColor(R.color.primaryColor), PorterDuff.Mode.SRC_ATOP);
            return;
        }

        _areaSize = _editTextAreaSize.getText().toString();

        model.AreaName = _areaName;
        model.AreaSize = _areaSize;
        model.CustomerId = mCustomerId;

        AreaDbHelper.AddArea(getApplicationContext(), model);
    }
}
