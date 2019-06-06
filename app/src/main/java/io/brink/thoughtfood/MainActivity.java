package io.brink.thoughtfood;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(0,0);

        final String[] lunchMenuItems = {"fulka","Butter masala","Rice","Sambhar","Rasam","Puli kulambu"};
        final String[] breakfastMenuItems = {"Chappathi","Idli","Dosa","Chutney","jumka","kibaka"};
        final String[] snacksMenuItems = {"samosa","bhajji","coffee","tea"};






        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {


                    case R.id.menu:
                        Intent intent1 = new Intent(MainActivity.this,MenuActivity.class);
                        intent1.putExtra("breakfastMenuItems",breakfastMenuItems);
                        intent1.putExtra("lunchMenuItems",lunchMenuItems);
                        intent1.putExtra("snacksMenuItems",snacksMenuItems);
                        startActivity(intent1);
                        break;

                    case R.id.feedback:
                        Intent intent2 = new Intent(MainActivity.this,FeedbackActivity.class);
                        intent2.putExtra("breakfastMenuItems",breakfastMenuItems);
                        intent2.putExtra("lunchMenuItems",lunchMenuItems);
                        intent2.putExtra("snacksMenuItems",snacksMenuItems);
                        startActivity(intent2);
                        break;

                    case R.id.profile:
                        Intent intent3 = new Intent(MainActivity.this,ProfileActivity.class);
                        startActivity(intent3);
                        break;

                }


                    return false;
            }
        });
    }
}
