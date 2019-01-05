package com.shereen.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private JobScheduler jobScheduler;

    @BindView(R.id.status)
    TextView status;

    @BindView(R.id.startButton)
    Button startButton;

    @BindView(R.id.stopButton)
    Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.startButton)
    public void clickedStart(){
        Log.d(Constants.GLOBAL_TAG, "Start button clicked");
        scheduleJob();
        status.setText(getString(R.string.started));
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
    }

    @OnClick(R.id.stopButton)
    public void clickedStop(){
        Log.d(Constants.GLOBAL_TAG, "Stop button clicked");

        if (jobScheduler != null) {
            jobScheduler.cancelAll();
            status.setText(getString(R.string.stopped));
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }

    private void scheduleJob(){

        //choose service class
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        //set conditions
        JobInfo jobInfo = new JobInfo.Builder(12, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .build();

        //schedule job
        jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(jobInfo);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(Constants.GLOBAL_TAG, "Job scheduled!");
        } else {
            Log.d(Constants.GLOBAL_TAG, "Job not scheduled");
        }

    }
}
