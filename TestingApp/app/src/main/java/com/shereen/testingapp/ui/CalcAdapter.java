package com.shereen.testingapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shereen.testingapp.R;
import com.shereen.testingapp.data.model.Calculation;

import java.util.Collections;
import java.util.List;

/**
 * Created by shereen on 12/31/18
 */

public class CalcAdapter extends RecyclerView.Adapter<CalcAdapter.ViewHolder>{

    private List<Calculation> calculationList = Collections.emptyList();;
    private Context context;

    public CalcAdapter(Context context) {
        this.context = context;
    }

    public void setCalculations(List<Calculation> calculations){
        calculationList = calculations;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView listText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listText = itemView.findViewById(R.id.listText);

        }
    }

    @NonNull
    @Override
    public CalcAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.calculation_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CalcAdapter.ViewHolder viewHolder, int i) {

        final Calculation calc = calculationList.get(i);
        String listItem = "";
        if (calc != null) {
            listItem = "Calc id: " + calc.getCalculationId();
            listItem += "  " + calc.getFirstNumber();
            listItem += "  " + calc.getOperator();
            listItem += "  " + calc.getSecondNumber();
            listItem += "  =  " + calc.getAnswer();
        }

        viewHolder.listText.setText(listItem);

    }

    @Override
    public int getItemCount() {
        return calculationList.size();
    }


}
