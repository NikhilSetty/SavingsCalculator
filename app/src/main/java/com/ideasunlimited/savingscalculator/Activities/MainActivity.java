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
import com.ideasunlimited.savingscalculator.ViewModel.ChildDisplayModel;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    CustomerListViewAdapter listAdapter;

    List<CustomerModel> listDataHeader;
    HashMap<CustomerModel, ChildDisplayModel> listDataChild;

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
        /*listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);*/

        listDataChild = new HashMap<CustomerModel, ChildDisplayModel>();
        listDataHeader = CustomerDbHelper.GetAllCustomers(getApplicationContext());

        for(int i = 0; i < listDataHeader.size(); i++){
            ChildDisplayModel childModel = CustomerDbHelper.GetChildDetailsForCustomer(getApplicationContext(), listDataHeader.get(i));
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

    public void Details(View view) {


    }

    public void CallCustomer(View view) throws Exception {
        throw new Exception("Not implemented");
    }
}
