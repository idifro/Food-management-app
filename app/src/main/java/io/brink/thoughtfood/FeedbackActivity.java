package io.brink.thoughtfood;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity {

    public int var=5;

    ArrayList<String> mItemName;

    List<String> data_list;


    public String emailID;
    private EditText dish1score;
    private EditText dish1comment;


    private EditText dish2score;
    private EditText dish2comment;

    private EditText dish3score;
    private EditText dish3comment;


    private EditText dish4score;
    private EditText dish4comment;


    private EditText dish5score;
    private EditText dish5comment;


    private EditText dish6score;
    private EditText dish6comment;


    private EditText dish7score;
    private EditText dish7comment;


    private EditText dish8score;
    private EditText dish8comment;


    private EditText dish9score;
    private EditText dish9comment;
    private Button dish9submit;
    private TextView tvIsConnected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        overridePendingTransition(0, 0);

        tvIsConnected = findViewById(R.id.tvIsConnected);

        dish1score = findViewById(R.id.dish1Score);
        dish1comment = findViewById(R.id.dish1Comment);


        dish2score = findViewById(R.id.dish2Score);
        dish2comment = findViewById(R.id.dish2Comment);


        dish3score = findViewById(R.id.dish3Score);
        dish3comment = findViewById(R.id.dish3Comment);


        dish4score = findViewById(R.id.dish4Score);
        dish4comment = findViewById(R.id.dish4Comment);


        dish5score = findViewById(R.id.dish5Score);
        dish5comment = findViewById(R.id.dish5Comment);

        dish6score = findViewById(R.id.dish6Score);
        dish6comment = findViewById(R.id.dish6Comment);


        dish7score = findViewById(R.id.dish7Score);
        dish7comment = findViewById(R.id.dish7Comment);

        dish8score = findViewById(R.id.dish8Score);
        dish8comment = findViewById(R.id.dish8Comment);


        dish9score = findViewById(R.id.dish9Score);
        dish9comment = findViewById(R.id.dish9Comment);
        dish9submit = findViewById(R.id.dish9Submit);

        checkNetworkConnection();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();


            emailID = email;
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }




//        Intent intent = getIntent();
//        String[] lunchMenuItems = intent.getStringArrayExtra("lunchMenuItems");
//        String[] breakfastMenuItems= intent.getStringArrayExtra("breakfastMenuItems");
//        String[] snacksMenuItems = intent.getStringArrayExtra("snacksMenuItems");
//
//
//
//
//
//        data_list = new ArrayList<String>(Arrays.asList(breakfastMenuItems));


//
//
//        mItemName = new ArrayList<>();
//        mItemName.add("Idli");
//        mItemName.add("Dosa");
//        mItemName.add("vada");
//        mItemName.add("rice");
//        mAdapter  = new MainAdapter((ArrayList<String>) data_list);


//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
//
//        for (int i = 0; i < mItemName.size(); i++) {
//            Log.d("onCreate",mItemName.get(i));
//        }


        final String[] lunchMenuItems = {"fulka", "Butter masala", "Rice", "Sambhar", "Rasam", "Puli kulambu"};
        final String[] breakfastMenuItems = {"Chappathi", "Idli", "Dosa", "Chutney", "jumka", "kibaka"};
        final String[] snacksMenuItems = {"samosa", "bhajji", "coffee", "tea"};

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {


                    case R.id.menu:
                        Intent intent1 = new Intent(FeedbackActivity.this, MenuActivity.class);
                        intent1.putExtra("breakfastMenuItems", breakfastMenuItems);
                        intent1.putExtra("lunchMenuItems", lunchMenuItems);
                        intent1.putExtra("snacksMenuItems", snacksMenuItems);
                        startActivity(intent1);
                        break;

                    case R.id.feedback:
                        break;

                    case R.id.profile:
                        Intent intent3 = new Intent(FeedbackActivity.this, ProfileActivity.class);
                        intent3.putExtra("var",var);
                        startActivity(intent3);
                        break;

                }


                return false;
            }
        });
    }

    public boolean checkNetworkConnection() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isConnected = false;
        if (networkInfo != null && (isConnected = networkInfo.isConnected())) {
            // show "Connected" & type of network "WIFI or MOBILE"

            // change background color to red



        } else {
            // show "Not Connected"

            // change background color to green
        }

        return isConnected;
    }

    private String httpPost(String myUrl) throws IOException, JSONException {
        String result = "";

        URL url = new URL(myUrl);

        // 1. create HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        // 2. build JSON object
        JSONObject jsonObject = buidJsonObject();

        // 3. add JSON content to POST request body
        setPostRequestContent(conn, jsonObject);

        // 4. make POST request to the given URL
        conn.connect();

        // 5. return response message
        return conn.getResponseMessage()+"";

    }

    private class HTTPAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            try {
                try {
                    return httpPost(urls[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "Error!";
                }
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Log.d("post done","post success");
        }
    }

    public void send(View view) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        // perform HTTP POST request
        if(checkNetworkConnection()) {
            new HTTPAsyncTask().execute("http://2728b0d4.ngrok.io");
            var = var+5;
        }
        else
            Toast.makeText(this, "Not Connected!", Toast.LENGTH_SHORT).show();

    }

    private JSONObject buidJsonObject() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("Email", emailID);
        jsonObject.accumulate("dish1", "Idli");
        jsonObject.accumulate("score1", dish1score.getText().toString());
        jsonObject.accumulate("comment1",  dish1comment.getText().toString());
        jsonObject.accumulate("dish2", "Dosa");
        jsonObject.accumulate("score2", dish2score.getText().toString());
        jsonObject.accumulate("comment2",  dish2comment.getText().toString());
        jsonObject.accumulate("dish3", "vada");
        jsonObject.accumulate("score3", dish3score.getText().toString());
        jsonObject.accumulate("comment3",  dish3comment.getText().toString());
        jsonObject.accumulate("dish5", "Sambhar");
        jsonObject.accumulate("score5", dish5comment.getText().toString());
        jsonObject.accumulate("comment5",  dish5comment.getText().toString());
        jsonObject.accumulate("dish6", "Rasam");
        jsonObject.accumulate("score6", dish6score.getText().toString());
        jsonObject.accumulate("comment6",  dish6comment.getText().toString());
        jsonObject.accumulate("dish7", "Samosa");
        jsonObject.accumulate("score7", dish7score.getText().toString());
        jsonObject.accumulate("comment7",  dish7comment.getText().toString());
        jsonObject.accumulate("dish8", "Tea");
        jsonObject.accumulate("score8", dish8score.getText().toString());
        jsonObject.accumulate("comment8",  dish8comment.getText().toString());
        jsonObject.accumulate("dish9", "Coffee");
        jsonObject.accumulate("score9", dish9score.getText().toString());
        jsonObject.accumulate("comment9",  dish9comment.getText().toString());
        jsonObject.accumulate("dish4", "Rice");
        jsonObject.accumulate("score4", dish4score.getText().toString());
        jsonObject.accumulate("comment4",  dish4comment.getText().toString());




        return jsonObject;
    }

    private void setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException {

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonObject.toString());
        Log.i(MainActivity.class.toString(), jsonObject.toString());
        writer.flush();
        writer.close();
        os.close();
    }



}
