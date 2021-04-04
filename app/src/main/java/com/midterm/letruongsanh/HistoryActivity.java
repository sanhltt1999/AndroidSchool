package com.midterm.letruongsanh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DataAdapter mMyAdapter;
    private MyViewMoldel mModel;
    private List<Data> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mModel = new ViewModelProvider(this).get(MyViewMoldel.class);



        mDataList = (List<Data>) getIntent().getExtras().getSerializable("aa");
        mModel.submit(mDataList, null);
        mRecyclerView = findViewById(R.id.recyclerView);
        mMyAdapter = new DataAdapter(mDataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyAdapter);

        Toast.makeText(this, mDataList.size() + mDataList.get(0).getInput() + "", Toast.LENGTH_SHORT).show();

        mMyAdapter.notifyDataSetChanged();

        mModel.getDatas().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                mDataList = data;
                mMyAdapter.notifyDataSetChanged();
            }
        });

        mMyAdapter.setOnClickListener(new DataAdapter.OnClickListener() {
            @Override
            public void onClick(Data data, int position) {

            }

            @Override
            public void onDelete(List<Data> datas) {
                mModel.submit(datas, null);
            }
        });
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        Intent intent = new Intent();
//        intent.putExtra("aaa", (Serializable) mDataList);
//        setResult(RESULT_OK, intent);
//        finish();
//        return super.onSupportNavigateUp();
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.deleteAction: {
                mDataList.clear();
                mMyAdapter.notifyDataSetChanged();
                break;
            } case android.R.id.home: {
                Intent intent = new Intent();
                intent.putExtra("aaa", (Serializable) mDataList);
                setResult(RESULT_OK, intent);
                finish();
                break;
            }

        }
        return true;
    }
}