package io.brink.thoughtfood;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    TextView titleText;

    ListView breakfastListView;
    ListView lunchListView;
    ListView snacksListView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        overridePendingTransition(0,0);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);





        breakfastListView = findViewById(R.id.breakfastListView);
        lunchListView = findViewById(R.id.lunchListView);
        snacksListView = findViewById(R.id.snacksListView);






        titleText = findViewById(R.id.todayText);

        breakfastListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1
        ,new ArrayList<String>()));

        lunchListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1
                ,new ArrayList<String>()));

        snacksListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1
                ,new ArrayList<String>()));


        new myTaskBreakfast().execute();
        new myTaskLunch().execute();
        new myTaskSnacks().execute();


//        ArrayAdapter<String> listViewAdapter1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
//                breakfastMenuItems);
//        breakfastListView.setAdapter(listViewAdapter1);

//        ArrayAdapter<String> listViewAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
//                lunchMenuItems);
//        lunchListView.setAdapter(listViewAdapter2);

//        ArrayAdapter<String> listViewAdapter3 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
//                snacksMenuItems);
//        snacksListView.setAdapter(listViewAdapter3);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {


                    case R.id.menu:
                        break;

                    case R.id.feedback:
                        Intent intent2 = new Intent(MenuActivity.this,FeedbackActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.profile:
                        Intent intent3 = new Intent(MenuActivity.this,ProfileActivity.class);
                        startActivity(intent3);
                        break;

                }


                return false;
            }
        });
    }

    class myTaskBreakfast extends AsyncTask<String,String,String> {


        Intent intent = getIntent();


        String[] breakfastMenuItems= intent.getStringArrayExtra("breakfastMenuItems");



        ArrayAdapter<String> adapter;
        @Override
        protected void onPreExecute() {
            adapter =(ArrayAdapter<String>) breakfastListView.getAdapter();
        }

        @Override
        protected String doInBackground(String... strings) {

            for(String Name: breakfastMenuItems){
                publishProgress(Name);


            }
            return "All the names were added successfully";
        }



        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);

        }
    }

    class myTaskLunch extends AsyncTask<String,String,String> {

        Intent intent = getIntent();

        String[] lunchMenuItems = intent.getStringArrayExtra("lunchMenuItems");

        ArrayAdapter<String> adapter;
        @Override
        protected void onPreExecute() {
            adapter =(ArrayAdapter<String>) lunchListView.getAdapter();
        }

        @Override
        protected String doInBackground(String... strings) {

            for(String Name: lunchMenuItems){
                publishProgress(Name);


            }
            return "All the names were added successfully";
        }



        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);

        }
    }

    class myTaskSnacks extends AsyncTask<String,String,String> {

        Intent intent = getIntent();
        String[] snacksMenuItems = intent.getStringArrayExtra("snacksMenuItems");

        ArrayAdapter<String> adapter;
        @Override
        protected void onPreExecute() {
            adapter =(ArrayAdapter<String>) snacksListView.getAdapter();
        }

        @Override
        protected String doInBackground(String... strings) {

            for(String Name: snacksMenuItems){
                publishProgress(Name);


            }
            return "All the names were added successfully";
        }



        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);

        }
    }

}
