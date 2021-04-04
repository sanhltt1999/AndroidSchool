package com.midterm.letruongsanh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner mEventSpinner;
    private FloatingActionButton mHistoryButton;
    private List<String> mTypeEventString;
    private int mPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEventSpinner = findViewById(R.id.typeEventButton);
        mHistoryButton = findViewById(R.id.historyButton);

        mTypeEventString = new ArrayList<>();
        mTypeEventString.add("count-letter-digit");
        mTypeEventString.add("remove-event");

        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, mTypeEventString);

        mEventSpinner.setAdapter(spinnerAdapter);

        mEventSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, mTypeEventString.get(position) + "", Toast.LENGTH_SHORT).show();
                mPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}