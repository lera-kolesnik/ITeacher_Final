package com.example.admin.iteacher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        final String[] items = {"Учителя", "Предметы", "Диалоги", "Домашние задания"};
        ListAdapter menuAdapter = new CustomAdapter(this, items);
        ListView menuListView = findViewById(R.id.menuListView);
        menuListView.setAdapter(menuAdapter);
        menuListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String items= String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MenuActivity.this, items,Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}