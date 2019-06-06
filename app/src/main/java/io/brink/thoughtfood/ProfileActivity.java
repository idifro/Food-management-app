package io.brink.thoughtfood;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName;
    Button btnlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        overridePendingTransition(0,0);
        profileName = findViewById(R.id.profileName);

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        int car = 20;

        Intent intent = getIntent();


        progressBar.setMax(100);

        progressBar.setProgress(Integer.parseInt(String.valueOf(car)));

        final String[] lunchMenuItems = {"fulka","Butter masala","Rice","Sambhar","Rasam","Puli kulambu"};
        final String[] breakfastMenuItems = {"Chappathi","Idli","Dosa","Chutney","jumka","kibaka"};
        final String[] snacksMenuItems = {"samosa","bhajji","coffee","tea"};



        btnlogout = findViewById(R.id.btnlogout);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            profileName.setText(email);
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {


                    case R.id.menu:
                        Intent intent1 = new Intent(ProfileActivity.this,MenuActivity.class);
                        intent1.putExtra("breakfastMenuItems",breakfastMenuItems);
                        intent1.putExtra("lunchMenuItems",lunchMenuItems);
                        intent1.putExtra("snacksMenuItems",snacksMenuItems);

                        startActivity(intent1);
                        break;

                    case R.id.feedback:
                        Intent intent2 = new Intent(ProfileActivity.this,FeedbackActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.profile:
                        break;

                }


                return false;
            }
        });
    }
}
