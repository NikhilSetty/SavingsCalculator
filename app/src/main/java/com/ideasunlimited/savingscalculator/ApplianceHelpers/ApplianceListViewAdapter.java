package com.ideasunlimited.savingscalculator.ApplianceHelpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ideasunlimited.savingscalculator.Model.ApplianceModel;
import com.ideasunlimited.savingscalculator.R;

/**
 * Created by nravishankar on 2/15/2016.
 */
public class ApplianceListViewAdapter extends ArrayAdapter<ApplianceModel> {

    private Context mContext;
    private ApplianceModel[] mApplianceList;

    public ApplianceListViewAdapter(Context context, ApplianceModel[] values) {
        super(context, -1, values);
        this.mContext = context;
        this.mApplianceList = values;
    }

    @Override
    public View getView(int position, View ConvertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.appliance_list_view_item, parent, false);

        ApplianceModel model = mApplianceList[position];

        TextView textViewApplianceName = (TextView) rowView.findViewById(R.id.textViewApplianceApplianceName);
        TextView textViewApplianceWattage = (TextView) rowView.findViewById(R.id.textViewApplianceWattage);
        TextView textViewApplianceQuantity = (TextView) rowView.findViewById(R.id.textViewApplianceQuantity);
        TextView textViewActiveHours = (TextView) rowView.findViewById(R.id.textViewApplianceActiveHours);
        TextView textViewWorkingHours = (TextView) rowView.findViewById(R.id.textViewApplianceWorkingHours);
        TextView textViewCostPerDay = (TextView) rowView.findViewById(R.id.textViewApplianceCostPerDay);
        TextView textViewCostPerMonth = (TextView) rowView.findViewById(R.id.textViewApplianceCostPerMonth);
        TextView textViewCostPerDayAfterSensor = (TextView) rowView.findViewById(R.id.textViewApplianceCostAfterInstallingSensorPerDay);
        TextView textViewCostPerMonthAfterSensor = (TextView) rowView.findViewById(R.id.textViewApplianceCostAfterInstallingSensorPerMonth);

        textViewApplianceName.setText(model.ApplianceName);
        textViewApplianceWattage.setText(model.ApplianceWattage);
        textViewApplianceQuantity.setText(model.ApplianceQuantity);
        textViewActiveHours.setText(model.ApplianceActiveHours);
        textViewWorkingHours.setText(model.ApplianceWorkingHours);
        textViewCostPerDay.setText(model.ApplianceCostPerDay);
        textViewCostPerMonth.setText(model.ApplianceCostPerMonth);
        textViewCostPerDayAfterSensor.setText(model.ApplianceCostPerDayAfterSensor);
        textViewCostPerMonthAfterSensor.setText(model.ApplianceCostPerMonthAfterSensor);

        return rowView;
    }

}
