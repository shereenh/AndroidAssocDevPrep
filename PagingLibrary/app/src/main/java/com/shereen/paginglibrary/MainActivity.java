package com.shereen.paginglibrary;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.shereen.paginglibrary.data.entity.ans.AnsItem;
import com.shereen.paginglibrary.data.entity.ques.QuesItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAns;
    private RecyclerView recyclerViewQues;
    private ItemViewModel itemViewModel;
    private AnsItemAdapter ansAdapter;
    private QuesItemAdapter quesAdapter;

    @BindView(R.id.ansButton)
    Button answerButton;

    @BindView(R.id.quesButton)
    Button quesButton;

    @OnClick(R.id.ansButton)
    public void ansClicked(){
        answerButton.setEnabled(false);
        quesButton.setEnabled(true);
        recyclerViewAns.setVisibility(View.VISIBLE);
        recyclerViewQues.setVisibility(View.GONE);
    }

    @OnClick(R.id.quesButton)
    public void quesClicked(){
        quesButton.setEnabled(false);
        answerButton.setEnabled(true);
        recyclerViewAns.setVisibility(View.GONE);
        recyclerViewQues.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        ansClicked();

    }

    private void initialize(){
        ButterKnife.bind(this);
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        ansAdapter = new AnsItemAdapter(this);
        quesAdapter = new QuesItemAdapter(this);

        recyclerViewAns = findViewById(R.id.recyclerViewAns);
        recyclerViewAns.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAns.setHasFixedSize(true);
        recyclerViewAns.setAdapter(ansAdapter);

        recyclerViewQues = findViewById(R.id.recyclerViewQues);
        recyclerViewQues.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewQues.setHasFixedSize(true);
        recyclerViewQues.setAdapter(quesAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

        itemViewModel.ansItemPagedList.observe(this, new Observer<PagedList<AnsItem>>() {
            @Override
            public void onChanged(@Nullable PagedList<AnsItem> ansItems) {
                ansAdapter.submitList(ansItems);
            }
        });

        itemViewModel.quesItemPagedList.observe(this, new Observer<PagedList<QuesItem>>() {
            @Override
            public void onChanged(@Nullable PagedList<QuesItem> quesItems) {
                quesAdapter.submitList(quesItems);
            }
        });
    }
}
