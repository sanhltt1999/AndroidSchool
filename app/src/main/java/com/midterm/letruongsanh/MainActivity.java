package com.midterm.letruongsanh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner mEventSpinner;
    private EditText mInputEditText;
    private Button mSubmitButton;
    private TextView mResultTextView;
    private List<Data> mDataList;
    private FloatingActionButton mHistoryButton;
    private List<String> mTypeEventString;
    private MyViewMoldel mModel;
    private int mPosition = 0;
    public final char SPACE = ' ';
    public final char TAB = '\t';
    public final char BREAK_LINE = '\n';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mModel = new ViewModelProvider(this).get(MyViewMoldel.class);

        mEventSpinner = findViewById(R.id.typeEventButton);
        mHistoryButton = findViewById(R.id.historyButton);
        mInputEditText = findViewById(R.id.inputEditText);
        mResultTextView = findViewById(R.id.resultTextView);
        mSubmitButton = findViewById(R.id.submitButton);

        mDataList = new ArrayList<>();
        mTypeEventString = new ArrayList<>();
        mTypeEventString.add("count-upper-lower");
        mTypeEventString.add("perfect_number");

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
                intent.putExtra("aa", (Serializable) mDataList);
                startActivityForResult(intent, 1);
            }
        });

        mModel.getDatas().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                mDataList = data;
            }
        });

        mModel.getData().observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                mInputEditText.setText(data.getInput());
                mResultTextView.setText(data.getResult());
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInputEditText.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Rong", Toast.LENGTH_SHORT).show();
                } else {
                    if (mPosition == 0 ){
                        String a = mInputEditText.getText().toString();
                        Data data = countWords(a);
                        mDataList.add(data);
                        mResultTextView.setText(data.getResult());
                        mModel.submit(mDataList, data);
                    } else {
                        String a = mInputEditText.getText().toString();
                        Data data = perfect_number(a);
                        mDataList.add(data);
                        mResultTextView.setText(data.getResult());
                        mModel.submit(mDataList, data);
                    }
                }
            }
        });


    }

    public Data countWords(String input) {
        if (input.isEmpty()) {
            return null;
        }
        int countUp = 0;
        int countLow = 0;
        int size = input.length();
        for (int i = 0; i < size; i++) {
            if (input.charAt(i) != SPACE && input.charAt(i) != TAB
                    && input.charAt(i) != BREAK_LINE
                    && Character.isUpperCase(input.charAt(i))) {
                countUp++;
            } else if (input.charAt(i) != SPACE && input.charAt(i) != TAB
                    && input.charAt(i) != BREAK_LINE
                    && Character.isLowerCase(input.charAt(i))){
                countLow++;
            }
        }

        String result = "UPPER CASE " + countUp + '\n' + "LOWER CASE " + countLow;

        Data data = new Data(mInputEditText.getText().toString(), mTypeEventString.get(mPosition), result);

        return data;
    }

    public Data perfect_number(String input) {
        if (input.isEmpty()) {
            return null;
        }
        String result = "";
        String[] words = input.split(",");

        for (int i = 0; i < words.length; i++) {
            if (check_perfect_num(Integer.parseInt(words[i]))) {
                result = result + words[i] + ",";
            }
        }

        Data data = new Data(mInputEditText.getText().toString(), mTypeEventString.get(mPosition), result);

        return data;
    }

    public static boolean check_perfect_num(int n) {

        int sum_aliquots = 0;
        for(int i = 1; i < n; i++) {
            if(n%i == 0) {
                sum_aliquots += i;
            }
        }
        if(sum_aliquots == n) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            mDataList = (List<Data>) data.getSerializableExtra("aaa");
        }

    }

}