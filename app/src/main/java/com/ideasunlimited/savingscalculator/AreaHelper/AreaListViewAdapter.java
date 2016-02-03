package com.ideasunlimited.savingscalculator.AreaHelper;

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
import com.ideasunlimited.savingscalculator.Model.AreaModel;
import com.ideasunlimited.savingscalculator.R;
import com.ideasunlimited.savingscalculator.ViewModel.AreaListChildViewModel;
import com.ideasunlimited.savingscalculator.ViewModel.CustomerListChildViewModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by nravishankar on 1/27/2016.
 */
public class AreaListViewAdapter extends BaseExpandableListAdapter{

    Context _context;
    List<AreaModel> _listDataHeader;
    HashMap<AreaModel, AreaListChildViewModel> _listDataChild;

    public AreaListViewAdapter(Context context, List<AreaModel> listDataHeader,
                                   HashMap<AreaModel, AreaListChildViewModel> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return _listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return this._listDataHeader.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return _listDataChild.get(_listDataHeader.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int i, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        AreaModel model = (AreaModel) getGroup(i);

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.area_list_group, null);
        }

        TextView textViewListHeader = (TextView) view.findViewById(R.id.textView_AL_areaName);
        TextView textViewListSubText = (TextView) view.findViewById(R.id.textView_AL_areaSize);
        textViewListHeader.setText(model.AreaName);
        textViewListSubText.setText(model.AreaSize);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {

        final AreaListChildViewModel childModel = (AreaListChildViewModel) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.area_list_child, null);
        }

        TextView textViewNumberOfAppliances = (TextView) convertView.findViewById(R.id.textView_AL_numberOfAppliances);
        textViewNumberOfAppliances.setText(childModel.NumberOfAppliances);
        TextView textViewTotalCost = (TextView) convertView.findViewById(R.id.textView_AL_totalCost);
        textViewTotalCost.setText(childModel.CurrentCost);
        TextView textViewTotalCostAfterSensor = (TextView) convertView.findViewById(R.id.textView_AL_costAfterSensor);
        textViewTotalCostAfterSensor.setText(childModel.CostAfterMotionSensorInstalled);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
