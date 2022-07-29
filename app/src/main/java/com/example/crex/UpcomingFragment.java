package com.example.crex;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpcomingFragment extends Fragment {

    String url = "https://mocki.io/v1/30786c0a-390e-41d5-9ad8-549ed26cba64";
    private RecyclerView upcomingFragmentRecyclerView;
    private RvAdapter upcomingFragmentAdapter;
    private ArrayList<Match> upcomingMatches;
    public static UpcomingFragment newInstance() {
        return new UpcomingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // initializing our variables.
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        upcomingFragmentRecyclerView = view.findViewById(R.id.upcomingFragmentRV);
        // progressBar = findViewById(R.id.idPB);

        // below line we are creating a new array list
        upcomingMatches = new ArrayList<>();
        getData();

        // calling method to
        // build recycler view.
        buildRecyclerView();
        return view;
    }




    private void getData() {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getContext());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
              //  progressBar.setVisibility(View.GONE);
             //   upcomingMatches.setVisibility(View.VISIBLE);
                Log.e("Apurva",response.toString());
                for (int i = 0; i < response.length(); i++) {
                    // creating a new json object and
                    // getting each object from our json array.
                    try {
                        // we are getting each json object.
                        JSONObject responseObj = response.getJSONObject(i);

                        // now we get our response from API in json object format.
                        // in below line we are extracting a string with
                        // its key value from our json object.
                        // similarly we are extracting all the strings from our json object.

                         String clubbeddate,t1,t2,t1_flag,t2_flag,match_no,date,t,rate=null,rate2=null,rate_team=null,rate1=null,score1=null,score2=null,overs1=null,overs2=null,winner=null,result=null;

                         clubbeddate=responseObj.getString("date");

                         //INFLATE
                        JSONArray matchesArray = responseObj.getJSONArray("m");
                        for(int j=0;j<matchesArray.length();j++){

                            JSONObject m=matchesArray.getJSONObject(j);
//                            t1=m.getString("t1");
                            t1=m.has("t1")?m.getString("t1"):null;
                            t2=m.getString("t2");
                            t1_flag=m.getString("t1flag");
                            t2_flag=m.getString("t2flag");
                            match_no=m.getString("match_no");
                            date=m.getString("date");
                            t=m.getString("t");
                            //STORE NOT EXISTENT ODDS VALUES AS NULL
                            JSONObject odds=m.has("odds")?m.getJSONObject("odds"):null;
                            if(odds != null) {
                                rate = odds.getString("rate");
                                rate2 = odds.getString("rate2");
                                rate_team = odds.getString("rate_team");
                            }
                            upcomingMatches.add(new Match(clubbeddate,t1,t2,t1_flag,t2_flag,match_no,date,t,rate,rate1,rate2,rate_team,score1,score2,overs1,overs2,winner,result));
                        }

                        buildRecyclerView();
                    } catch (JSONException e) {
                        Log.e("Apurva","PArsing error!"+e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             //   Toast.makeText(UpcomingFragment., "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }
    private void buildRecyclerView() {

        // initializing our adapter class.
        Log.e("Apurva","um :-"+upcomingMatches.size());
        upcomingFragmentAdapter = new RvAdapter(upcomingMatches, getContext(),null,0);

        // adding layout manager
        // to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        upcomingFragmentRecyclerView.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        upcomingFragmentRecyclerView.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        upcomingFragmentRecyclerView.setAdapter(upcomingFragmentAdapter);
    }
    public UpcomingFragment(){}
}
