package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    private List<User> mUserList = new ArrayList<>();
    private SearchView mSearchView;

    private AppDatabase db;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserList.add(new User(UUID.randomUUID().toString(),"Nguyen Van A", "0918199744", "aaaaaaaaaaa"));
        mUserList.add(new User(UUID.randomUUID().toString(),"Nguyen Van B", "0918199744", "aaaaaaaaaaa"));
        mUserList.add(new User(UUID.randomUUID().toString(),"Nguyen Van C", "0918199744", "aaaaaaaaaaa"));


        mSearchView = findViewById(R.id.searchView);
        mRecyclerView = findViewById(R.id.recyclerView);
        mMyAdapter = new MyAdapter(mUserList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyAdapter);

        mMyAdapter.setOnClickListener((user, position) -> {
        });

        db = AppDatabase.getInstance(this);
        dao = db.userDao();

        User user = new User(UUID.randomUUID().toString(),"a","b","c");
        User user1 = new User(UUID.randomUUID().toString(),"e","b","c");

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
//                dao.insert(user, user1);
                mUserList.addAll(dao.getAll());
            }
        });

        mMyAdapter.notifyDataSetChanged();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

    }

    private void filter(String newText) {
        List<User> users = new ArrayList<>();

        for (User item : mUserList) {
            if (item.getName().toLowerCase().contains(newText.toLowerCase())) {
                users.add(item);
            }
        }

        if (users.isEmpty()) {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        } else {
            mMyAdapter.filterList(users);
        }

    }

    public void createUser(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
                User result= (User) data.getSerializableExtra("user");
                Toast.makeText(this, result.getEmail(), Toast.LENGTH_SHORT).show();

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        db.userDao().insert(result);
                    }
                });

                mUserList.add(result);
                mMyAdapter.notifyDataSetChanged();

        }

    }
}