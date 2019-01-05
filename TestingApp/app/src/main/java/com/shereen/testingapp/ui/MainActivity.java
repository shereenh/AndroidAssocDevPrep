package com.shereen.testingapp.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shereen.testingapp.R;
import com.shereen.testingapp.domain.Action;
import com.shereen.testingapp.domain.CalculationDataProvider;
import com.shereen.testingapp.data.model.Calculation;
import com.shereen.testingapp.ui.paging.PagingCalcAdapter;
import com.shereen.testingapp.ui.paging.PagingViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Calculation calculation;
    private RecyclerView recyclerView;

    private ViewModel viewModel;
    private PagingViewModel pagingViewModel;

    private CalcAdapter adapter;
    private PagingCalcAdapter pagingAdapter;

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.number1)
    EditText firstNum;

    @BindView(R.id.number2)
    EditText secondNum;

    @BindView(R.id.operatorText)
    TextView operatorView;

    @BindView(R.id.resultText)
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initial();
        ButterKnife.bind(this);

        calculation = new Calculation("2", "3", "-");
        setUpCalc();

        //viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        pagingViewModel = ViewModelProviders.of(this).get(PagingViewModel.class);

        setUpRecyclerView();
    }

//    private void initial(){
//        firstNum = findViewById(R.id.number1);
//        secondNum = findViewById(R.id.number2);
//        operatorView = findViewById(R.id.operatorText);
//        resultView = findViewById(R.id.resultText);
//    }

    private void setUpCalc(){

        firstNum.setText(calculation.getFirstNumber());
        secondNum.setText(calculation.getSecondNumber());
        operatorView.setText(calculation.getOperator());
    }

    @Override
    protected void onResume() {
        super.onResume();

        handlePagingStorage();

    }

    private void handlePagingStorage(){

        pagingViewModel.getPagedCalculations()
                .observe(MainActivity.this,
                        new Observer<PagedList<Calculation>>() {
                            @Override
                            public void onChanged(@Nullable PagedList<Calculation> calculations) {
                                if(calculations == null || calculations.size() ==0){
                                    Log.d(TAG, "calc list empty");
                                }else{
                                    Log.d(TAG, "calc list not empty");
                                }
                                pagingAdapter.submitList(calculations);
                            }
                        });

    }

    private void handleStorage(){

        for (Calculation calc : CalculationDataProvider.calculationList) {
            viewModel.createCalc(calc);
        }

        viewModel.getCalcList()
                    .observe(MainActivity.this,
                        new Observer<List<Calculation>>(){
                            @Override
                            public void onChanged(@Nullable List<Calculation> calculations) {
                                adapter.setCalculations(calculations);
                                adapter.notifyDataSetChanged();
                            }
                        });
    }

    private void setUpRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new CalcAdapter(this);
        pagingAdapter = new PagingCalcAdapter();
        //viewModel.pagedCalcList.observe(this, pagingAdapter::submitList );
        recyclerView.setAdapter(pagingAdapter);
    }

    public void add(View view){
        getVals();
        calculation.setOperator(getString(R.string.add));
        setUpCalc();
        Log.d(TAG, "on click: " + calculation);
    }

    public void minus(View view){
        getVals();
        calculation.setOperator(getString(R.string.minus));
        setUpCalc();
        Log.d(TAG, "on click: " + calculation);
    }

    public void multiply(View view){
        getVals();
        calculation.setOperator(getString(R.string.multiply));
        setUpCalc();
        Log.d(TAG, "on click: " + calculation);
    }

    public void equals(View view){
        getVals();
        calculation.setAnswer(Action.operate(calculation).getAnswer());
        resultView.setText(calculation.getAnswer());
        pagingViewModel.insertCalculation(calculation);
        Log.d(TAG, "after operation: " + calculation);
    }

    public void trash(View view){
        pagingViewModel.deleteAllCalculations();

        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinator);
        Snackbar snackbar = Snackbar
                .make( coordinatorLayout, "Trashed!", Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    public void getVals(){

        String s = Long.parseLong(secondNum.getText().toString()) +"";

        calculation.setFirstNumber(firstNum.getText().toString());
        calculation.setSecondNumber(s);
        calculation.setOperator(operatorView.getText().toString());

        Log.d(TAG, "saved Val: " + calculation);

    }

}
