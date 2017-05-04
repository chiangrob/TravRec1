package com.example.yvtc.travrec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
    }
    public void clickAdd(View v)
    {
        File myfile;
        ArrayList<ConsData> mylist = null;
        myfile = new File(getFilesDir() + File.separator + "mydata.json");
        Gson gson = new Gson();
        if (myfile.exists())
        {
            try {
                FileReader fr = new FileReader(myfile);
                BufferedReader br = new BufferedReader(fr);
                String data = br.readLine();

                mylist = gson.fromJson(data, new TypeToken<ArrayList<ConsData>>(){}.getType());
                br.close();
                fr.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            mylist = new ArrayList<>();
        }
        mylist.add(new ConsData(ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString()));
        try {
            FileWriter fw = new FileWriter(myfile);
            BufferedWriter bw = new BufferedWriter(fw);
            String str = gson.toJson(mylist);
            bw.write(str);
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finish();
    }
}
