package com.ideasunlimited.savingscalculator.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.ideasunlimited.savingscalculator.CustomerHelpers.CustomerDbHelper;
import com.ideasunlimited.savingscalculator.CustomerHelpers.CustomerListViewAdapter;
import com.ideasunlimited.savingscalculator.Model.CustomerModel;
import com.ideasunlimited.savingscalculator.R;
import com.ideasunlimited.savingscalculator.ViewModel.CustomerListChildViewModel;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    CustomerListViewAdapter listAdapter;

    List<CustomerModel> listDataHeader;
    HashMap<CustomerModel, CustomerListChildViewModel> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListViewCustomer);

    }

    @Override
    protected void onStart(){
        super.onStart();

        // preparing list data
        prepareListData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_customer) {
            Intent i = new Intent(this, AddCustomerActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        prepareListData();
    }

    private void prepareListData() {

        listDataChild = new HashMap<CustomerModel, CustomerListChildViewModel>();
        listDataHeader = CustomerDbHelper.GetAllCustomers(getApplicationContext());

        for(int i = 0; i < listDataHeader.size(); i++){
            CustomerListChildViewModel childModel = CustomerDbHelper.GetChildDetailsForCustomer(getApplicationContext(), listDataHeader.get(i));
            listDataChild.put(listDataHeader.get(i), childModel);
        }

        listAdapter = new CustomerListViewAdapter(this, listDataHeader, listDataChild);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d("onGroupClick:", "worked");
                parent.smoothScrollToPosition(groupPosition);

                if (parent.isGroupExpanded(groupPosition)) {
                    parent.collapseGroup(groupPosition);
                } else {
                    parent.expandGroup(groupPosition);
                }

                return true;
            }
        });

        // setting list adapter
        expandableListView.setAdapter(listAdapter);
    }

    public void CallCustomer(View view) throws Exception {
        throw new Exception("Not implemented");
    }
}
