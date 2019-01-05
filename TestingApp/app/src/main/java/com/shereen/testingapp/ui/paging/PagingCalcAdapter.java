package com.shereen.testingapp.ui.paging;

import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shereen.testingapp.R;
import com.shereen.testingapp.data.model.Calculation;

import java.util.List;

/**
 * Created by shereen on 1/1/19
 */

public class PagingCalcAdapter extends PagedListAdapter<Calculation, PagingCalcAdapter.ViewHolder> {

    private PagedList<Calculation> calculationList;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView listText;
        //need to implement binding later
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listText = itemView.findViewById(R.id.listText);

        }
    }

    @NonNull
    @Override
    public PagingCalcAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.calculation_item, viewGroup, false);

        return new PagingCalcAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
//        final Calculation calc = calculationList.get(i);
        final Calculation calc = getItem(position);

        String listItem = "";
        if (calc != null) {
            listItem = "Calc id: " + calc.getCalculationId();
            listItem += "  " + calc.getFirstNumber();
            listItem += "  " + calc.getOperator();
            listItem += "  " + calc.getSecondNumber();
            listItem += "  =  " + calc.getAnswer();
            //viewHolder.bindTo()
        }else{
            //viewHolder.clear();
        }

        viewHolder.listText.setText(listItem);
    }

    public PagingCalcAdapter(){
        super(DIFF_CALLBACK);
    }

    public void addMoreCalculations(List<Calculation> newCalculations){
        calculationList.addAll(newCalculations);
        submitList(calculationList);
    }

    public static final DiffUtil.ItemCallback<Calculation> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Calculation>() {
                @Override
                public boolean areItemsTheSame(@NonNull Calculation oldCalc, @NonNull Calculation newCalc) {
                    return oldCalc.getCalculationId() == newCalc.getCalculationId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Calculation oldCalc, @NonNull Calculation newCalc) {
                    return (oldCalc.getFirstNumber().equals(newCalc.getFirstNumber())) &&
                            (oldCalc.getSecondNumber().equals(newCalc.getSecondNumber())) &&
                            (oldCalc.getOperator().equals(newCalc.getOperator())) &&
                            (oldCalc.getAnswer().equals(newCalc.getAnswer()));
                }
            };


}
