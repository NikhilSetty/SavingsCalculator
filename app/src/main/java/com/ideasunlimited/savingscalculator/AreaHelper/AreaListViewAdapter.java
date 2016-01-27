package com.ideasunlimited.savingscalculator.AreaHelper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.ideasunlimited.savingscalculator.Model.AreaModel;
import com.ideasunlimited.savingscalculator.Model.CustomerModel;
import com.ideasunlimited.savingscalculator.ViewModel.AreaListChildDViewModel;
import com.ideasunlimited.savingscalculator.ViewModel.CustomerListChildViewModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by nravishankar on 1/27/2016.
 */
public class AreaListViewAdapter extends BaseExpandableListAdapter{

    Context _context;
    List<AreaModel> _listDataHeader;
    HashMap<AreaModel, AreaListChildDViewModel> _listDataChild;

    public AreaListViewAdapter(Context context, List<AreaModel> listDataHeader,
                                   HashMap<AreaModel, AreaListChildDViewModel> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
