package com.shereen.mylivedata.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.shereen.mylivedata.data.Constants;
import com.shereen.mylivedata.R;
import com.shereen.mylivedata.data.entity.MyNumber;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable runnable;

    @BindView(R.id.startButton)
    Button startButton;

    @BindView(R.id.stopButton)
    Button stopButton;

    @BindView(R.id.textViewDate)
    TextView dateView;

    @BindView(R.id.textViewNumber)
    TextView numberView;

    @BindView(R.id.statusView)
    TextView statusView;

    Random rand;
    MyNumber myNumber;
    MyViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize(){
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        rand = new Random();
        myNumber = new MyNumber();
        handleData();
    }

    @OnClick(R.id.startButton)
    void startClicked(){
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        startTask();
        statusView.setText(getString(R.string.start));
    }

    @OnClick(R.id.stopButton)
    void stopClicked(){
        stopButton.setEnabled(false);
        startButton.setEnabled(true);
        statusView.setText(R.string.stop);
        stopTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(stopButton.isEnabled()){
//            startTask();
//        }
        //handleData();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //stopTask();
    }

    private void startTask() {

        if (handler == null) {
            handler = new Handler();

            runnable = new Runnable() {
                public void run() {

                    myNumber.setCurDT(DateFormat.getDateTimeInstance().format(new Date()));
                    myNumber.setNumber(rand.nextInt(Constants.MAX_NUMBER));
//                    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
//                    System.out.println("Here it is: "+currentDateTimeString);
                    viewModel.insertNumber(myNumber);
                    Log.d(Constants.GLOBAL_TAG, "Here it is : " + myNumber.toString());
                    handler.postDelayed(this, 5000);
                }
            };

            handler.postDelayed(runnable, 5000);
        }
    }

    private void stopTask(){
        if (handler != null && runnable !=null) {
            handler.removeCallbacks(runnable);
        }
    }

    private void handleData(){
        viewModel.getAllNumbers()
                .observe(MainActivity.this,
                        new Observer<List<MyNumber>>() {
                            @Override
                            public void onChanged(@Nullable List<MyNumber> myNumbers) {
                                MyNumber num = myNumbers.get(0);
                                if(num!=null && numberView!=null && dateView!=null){
                                    numberView.setText(num.getNumber() + "");
                                    dateView.setText(num.getCurDT());
                                }
                            }
                        });
    }
}
