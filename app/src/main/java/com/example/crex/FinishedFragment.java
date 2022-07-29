package com.example.crex;

import android.content.Context;
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

public class FinishedFragment extends Fragment {

    String url = "https://mocki.io/v1/2389d44c-81aa-4e04-bd2e-b8c7e17572c0";
    private RecyclerView finishedFragmentRecyclerView;
    private RvAdapter finishedFragmentAdapter;
    private ArrayList<Match> finishedMatches;
    public static FinishedFragment newInstance() {
        return new FinishedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.fragment_main2);
        View view = inflater.inflate(R.layout.fragment_main2, container, false);
        finishedFragmentRecyclerView = view.findViewById(R.id.finishedFragmentRV);
        finishedMatches = new ArrayList<>();
        getData();

        buildRecyclerView();
        return view;
    }


    private void getData() {
        // creating a new variable for our request queue
        Context context;
        RequestQueue queue = Volley.newRequestQueue(getContext());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //  progressBar.setVisibility(View.GONE);
              //  finishedMatches.setVisibility(View.VISIBLE);
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


                        String clubbeddate, t1, t2, t1_flag, t2_flag, match_no, date, t, rate = null, rate2 = null, rate_team = null, rate1 = null, score1 = null, score2 = null, overs1 = null, overs2 = null, winner = null, result = null;
                        clubbeddate = responseObj.getString("date");
                        //INFLATE
                        JSONArray matchArray = responseObj.getJSONArray("m");
                        for (int k = 0; k < matchArray.length();k++) {
                            JSONObject m = matchArray.getJSONObject(k);
                            t1 = m.getString("t1");
                            t2 = m.getString("t2");
                            t1_flag = m.getString("t1flag");
                            t2_flag = m.getString("t2flag");
                            score1 = m.getString("score1");
                            score2 = m.getString("score2");
                            overs1 = m.getString("overs1");
                            overs2 = m.getString("overs2");
                            winner = m.getString("winner");
                            match_no = m.getString("match_no");
                            result = m.getString("result");
                            date = m.getString("date");
                            t = m.getString("t");


                            finishedMatches.add(new Match(clubbeddate,t1, t2, t1_flag, t2_flag, match_no, date, t, rate, rate1, rate2, rate_team, score1, score2, overs1, overs2, winner, result));
                        } buildRecyclerView();
                    } catch (JSONException e) {
                        Log.e("Apurva","PArsing error2!"+e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }
    private void buildRecyclerView() {

        // initializing our adapter class.
        Log.e("Apurva","um :-"+finishedMatches.size());
        finishedFragmentAdapter = new RvAdapter(null, getContext(),finishedMatches,1);

        // adding layout manager
        // to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        finishedFragmentRecyclerView.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        finishedFragmentRecyclerView.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        finishedFragmentRecyclerView.setAdapter(finishedFragmentAdapter);
    }
    public FinishedFragment()
    {

    }
}
