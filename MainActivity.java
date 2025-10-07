package com.arshahrear.dictonaryapp;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //👍Layout xml create করতে হবে। New>>XML>>Layout XML File >>name:item >>ok
    //Chrome search : english to bangla sqlite database >> go nmustakim or any other >> assets >>dictionary.db>> download
    //DB Browser app open >> file phaste here >> Check table name in DB structure or>>watch data in Browse Data
    //Chrome search : sqlite asset helper android >>jgilfelt github library file open>>Setup>>Gradle>>
    //compile 'com.readystatesoftware.sqliteasset:sqliteassethelper:+' এখানে compile এর পরিবর্তে implementation use করবো । এটা অনেক পুরাতন library
    //build.gradle(Module:app) এ minSdk 28 দিতে হবে
    //Library file upload rulls:
    //rulls: Android(উপরে বামে file icon এর সাথে) to Project shift করবো >>app>src>>main>
    //rulls: > main এর মধ্যে mouse right click :New:Directory: assets এ click করবো ok >
    //rulls: > assets  মধ্যে mouse right click :New:Directory:databases লিখে  enter করবো>> এখানে download dictionary.db database file paste করবো

    //👍Mouse right in MainActivity.java>>New>>Java class >>Class>>DatabaseHelper.java

    ListView listView;
    DatabaseHelper dbHelper;
    EditText edSearch;



    ArrayList<HashMap<String, String>> arrayList;
    HashMap<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);
        edSearch = findViewById(R.id.edSearch);

        loadData(dbHelper.getAllData());

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String key = edSearch.getText().toString();
                loadData(dbHelper.searchData(key));

            }
        });




    }



    public void loadData(Cursor cursor){

        //Cursor cursor = dbHelper.getAllData();


        if (cursor != null && cursor.getCount() > 0) {

            arrayList = new ArrayList<>();

            while (cursor.moveToNext()) {
                int id = cursor.getInt( 0);
                String word = cursor.getString( 1);
                String meaning = cursor.getString( 2);
                String partsOfSpeech = cursor.getString( 3);
                String example = cursor.getString( 4);

                HashMap hashMap = new HashMap<>();
                hashMap.put("id", "" + id);
                hashMap.put("word", "" + word);
                hashMap.put("meaning", "" + meaning);
                hashMap.put("partsOfSpeech", "" + partsOfSpeech);
                hashMap.put("example", "" + example);
                arrayList.add(hashMap);
            }

            listView.setAdapter(new MyAdapter());
        }


    }

    //----------------------------------------------


    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.item, parent,false);

            TextView tvWord = myView.findViewById(R.id.tvWord);
            TextView tvMeaning = myView.findViewById(R.id.tvMeaning);
            TextView tvExample = myView.findViewById(R.id.tvExample);

            hashMap = arrayList.get(position);
            String word = hashMap.get("word");
            String meaning = hashMap.get("meaning");
            String partsOfSpeech = hashMap.get("partsOfSpeech");
            String example = hashMap.get("example");

            tvWord.setText(word + " (" + partsOfSpeech + ")");
            tvMeaning.setText(meaning);
            tvExample.setText(example);


            return myView;
        }
    }
    //------------------------------

}
