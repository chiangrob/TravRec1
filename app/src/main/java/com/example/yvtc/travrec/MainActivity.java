package com.example.yvtc.travrec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList<ConsData> mylist;
    ArrayList<Map<String, String>> showdata;
    File myfile;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myfile = new File(getFilesDir() + File.separator + "mydata.json");
        lv = (ListView) findViewById(R.id.listView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        showdata = new ArrayList<>();
        if (myfile.exists()) {
            try {
                FileReader fr = new FileReader(myfile);
                BufferedReader br = new BufferedReader(fr);
                String data = br.readLine();
                Gson gson = new Gson();
                mylist = gson.fromJson(data, new TypeToken<ArrayList<ConsData>>() {
                }.getType());
                for (ConsData s : mylist) {
                    Map m1 = new HashMap();
                    m1.put("Landscap", s.Landscape);
                    m1.put("Visittim", s.Visittim);
                    m1.put("Descript", s.Descript);
                    showdata.add(m1);
                }
                SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, showdata,
                        R.layout.myitem, new String[]{"Landscap", "Visittim", "Descript"}, new int[]{R.id.textView,
                        R.id.textView2, R.id.textView3});
                lv.setAdapter(adapter);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("景點查詢");
        menu.add("旅遊規劃");
        menu.add("新增旅遊紀錄");


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getTitle().toString()){
                case "景點查詢":
                    Intent it1 = new Intent(MainActivity.this, FindActivity.class);
                    startActivity(it1);
                    break;
                case "旅遊規劃":
                    Intent it2 = new Intent(MainActivity.this, PlanActivity.class);
                    startActivity(it2);
                    break;
                case "新增旅遊紀錄":
                    Intent it3 = new Intent(MainActivity.this, AddActivity.class);
                    startActivity(it3);
                    break;

            }

        return super.onOptionsItemSelected(item);
    }
}
