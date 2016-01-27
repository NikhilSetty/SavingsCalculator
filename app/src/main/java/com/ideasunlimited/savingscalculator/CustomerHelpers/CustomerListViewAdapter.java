package com.ideasunlimited.savingscalculator.CustomerHelpers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ideasunlimited.savingscalculator.Activities.Area.AreaListActivity;
import com.ideasunlimited.savingscalculator.Constants;
import com.ideasunlimited.savingscalculator.Model.CustomerModel;
import com.ideasunlimited.savingscalculator.R;
import com.ideasunlimited.savingscalculator.ViewModel.CustomerListChildViewModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by nravishankar on 1/18/2016.
 */
public class CustomerListViewAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<CustomerModel> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<CustomerModel, CustomerListChildViewModel> _listDataChild;

    public CustomerListViewAdapter(Context context, List<CustomerModel> listDataHeader,
                                 HashMap<CustomerModel, CustomerListChildViewModel> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        CustomerListChildViewModel child = _listDataChild.get(_listDataHeader.get(groupPosition));
        return child;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {


        final CustomerListChildViewModel childModel = (CustomerListChildViewModel) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.customer_list_child, null);
        }

        TextView textViewContactNumber = (TextView) convertView.findViewById(R.id.textView_CL_contactNumber);
        textViewContactNumber.setText(childModel.ContactNumber);
        TextView textViewNumberOfAreas = (TextView) convertView.findViewById(R.id.textView_CL_areaNumber);
        textViewNumberOfAreas.setText(childModel.NumberOfAreas);
        TextView textViewTotalSavings = (TextView) convertView.findViewById(R.id.textView_CL_TotalSavings);
        textViewTotalSavings.setText(childModel.TotalSavings);

        Button buttonDetails = (Button) convertView.findViewById(R.id.button_CL_Details);
        buttonDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(_context, AreaListActivity.class);
                i.putExtra(Constants.CustomerIdExtraString , _listDataHeader.get(groupPosition)._id);
                _context.startActivity(i);
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        CustomerModel model = (CustomerModel) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.customer_list_group, null);
        }

        TextView textViewListHeader = (TextView) convertView.findViewById(R.id.textViewCustomerName);
        TextView textViewListSubText = (TextView) convertView.findViewById(R.id.textViewCustomerLocation);
        textViewListHeader.setText(model.CustomerName);
        textViewListSubText.setText(model.CustomerLocation);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

