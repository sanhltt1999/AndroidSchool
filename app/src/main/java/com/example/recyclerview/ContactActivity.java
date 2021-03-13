package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.recyclerview.databinding.ActivityContactBinding;

import java.io.Serializable;
import java.util.UUID;

public class ContactActivity extends AppCompatActivity {

    ActivityContactBinding mBinding;
    int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityContactBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create:
                Create();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Create() {
        User user = new User(UUID.randomUUID().toString(), mBinding.name.getText().toString(), mBinding.phone.getText().toString(), mBinding.email.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("user", user);
        setResult(RESULT_OK, intent);
        finish();
    }
}