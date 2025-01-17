package com.example.listcity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addCity,deleteCity, confirm;
    EditText edits;
    ActionBar topAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //create the initial list to view
        cityList = findViewById(R.id.city_list);
        String []cities = {"Edmonton","Vancouver","Moscow","Sydney","Berlin","Vienna","Tokyo","Beijing","Osaka","New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this,R.layout.content,dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        //create ur entities
        addCity = findViewById(R.id.addCity);
        deleteCity = findViewById(R.id.deleteCity);
        confirm = findViewById(R.id.confirm);
        edits = findViewById(R.id.edits);

        //check when item in list clicked
        cityList.setOnItemClickListener((parent, view, position, id) -> cityList.setItemChecked(position, true));



        //add city
        addCity.setOnClickListener(v -> {
            edits.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.VISIBLE);
        });

        // confirm
        confirm.setOnClickListener(v -> {
            String cName = edits.getText().toString();
            dataList.add(cName);
            edits.onEditorAction(EditorInfo.IME_ACTION_DONE); //closes keyboard
            edits.setVisibility(View.GONE);
            confirm.setVisibility(View.GONE);
            cityAdapter.notifyDataSetChanged();
        });

        // delete
        deleteCity.setOnClickListener(v -> {
            int selectedPosition = cityList.getCheckedItemPosition();
            if (selectedPosition != AdapterView.INVALID_POSITION) { //just a check to see if selected position valid
                dataList.remove(cityAdapter.getItem(selectedPosition));
                cityAdapter.notifyDataSetChanged();

            }
        });
    }
}