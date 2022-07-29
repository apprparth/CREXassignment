package com.example.crex;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

public class FinishedFragmentFirebase extends Fragment {
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference;

    private RecyclerView finishedFragmentRecyclerView;
    private RvAdapter2 finishedFragmentAdapter;
    private ArrayList<Match> finishedMatches;
    private String currDate = "";
    private ArrayList<MultiViewClass> finishedMatchObjects;
    private ArrayList<MultiViewClass> finalArrayList = new ArrayList<>();
    public static com.example.crex.FinishedFragment newInstance() {
        return new com.example.crex.FinishedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.fragment_main2);
        View view = inflater.inflate(R.layout.fragment_main2, container, false);
        finishedFragmentRecyclerView = view.findViewById(R.id.finishedFragmentRV);
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get
        // reference for our database.
        databaseReference = firebaseDatabase.getReference("liveMatches2");
        finishedMatches = new ArrayList<>();
        getData();

        //buildRecyclerView();
        return view;
    }



    private void getData() {
        // creating a new variable for our request queue
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                //String value = snapshot.getValue(String.class);
                String clubbeddate, t1, t2, t1_flag, t2_flag, match_no, date, t, rate = null, rate2 = null, rate_team = null, rate1 = null, score1 = null, score2 = null, overs1 = null, overs2 = null, winner = null, result = null, status = null;
                for (int i = 0; i < snapshot.getChildrenCount(); i++) {
                    DataSnapshot parseNo = snapshot.child(Integer.toString(i));
                    try {
                        clubbeddate = parseNo.child("date").getValue(String.class);
                        t1 = parseNo.child("t1").getValue(String.class);
                        t2 = parseNo.child("t2").getValue(String.class);
                        t1_flag = parseNo.child("t1flag").getValue(String.class);
                        t2_flag = parseNo.child("t2flag").getValue(String.class);
                        match_no = parseNo.child("match_no").getValue(String.class);
                        date = parseNo.child("date").getValue(String.class);
                        t = String.valueOf(parseNo.child("t").getValue(Long.class));
                        rate = parseNo.child("rate").getValue(String.class);
                        rate2 = parseNo.child("rate2").getValue(String.class);
                        rate_team = parseNo.child("rate_team").getValue(String.class);
                        rate1 = parseNo.child("rate1").getValue(String.class);
                        score1 = parseNo.child("score1").getValue(String.class);
                        score2 = parseNo.child("score2").getValue(String.class);
                        overs1 = parseNo.child("overs1").getValue(String.class);
                        overs2 = parseNo.child("overs2").getValue(String.class);
                        winner = parseNo.child("winner").getValue(String.class);
                        result = parseNo.child("result").getValue(String.class);
                        status = String.valueOf(parseNo.child("status").getValue(Long.class));
                        if (status.equals("2")) {
                            finishedMatches.add(new Match(clubbeddate, t1, t2, t1_flag, t2_flag, match_no, date, t, rate, rate1, rate2, score1, score2, overs1, overs2, winner, result,rate_team));
                        }
                    } catch (Exception e) {
                        Log.e("Apurva","PArsing error!"+e.getMessage());
                        e.printStackTrace();
                    }
                }
                Collections.sort(finishedMatches, new FinishedFragmentFirebase.MatchDateComparator());
                for(Match match : finishedMatches){
                    if(!match.getDate().equals(currDate)){
                        MultiViewClass multiViewClass = new MultiViewClass(CardType.DATE,null,match.getDate());
                        MultiViewClass multiViewClass2 = new MultiViewClass(CardType.MATCH,match,null);
                        finalArrayList.add(multiViewClass);
                        finalArrayList.add(multiViewClass2);
                        currDate = match.getDate();
                    }else{
                        MultiViewClass multiViewClass2 = new MultiViewClass(CardType.MATCH,match,null);
                        finalArrayList.add(multiViewClass2);
                    }
                }

                finalArrayList.add(new MultiViewClass(CardType.END,null,null));

                for(MultiViewClass multiViewClass : finalArrayList){
                    Log.e("Apurva",multiViewClass.toString());
                }
                buildRecyclerView();
            }
                // after getting the value we are setting
                // our value to our text view in below line.
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // calling on cancelled method when we receive
                    // any error or we are not able to get the data.
                    Toast.makeText(getContext(),"Failed to get data", Toast.LENGTH_SHORT).show();
                }
            });
        }



            private void buildRecyclerView() {

                // initializing our adapter class.
                Log.e("Apurva", "um :-" + finishedMatches.size());
                finishedFragmentAdapter = new RvAdapter2(null, getContext(), finalArrayList, 1);

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

            public FinishedFragmentFirebase() {}
    public class MatchDateComparator implements Comparator<Match> {

        @Override
        public int compare(Match match, Match t1) {
            try {
                Date matchDate = new SimpleDateFormat("MM/dd/yyyy").parse(match.getDate());
                Date t1Date = new SimpleDateFormat("MM/dd/yyyy").parse(t1.getDate());
                if(matchDate.compareTo(t1Date) == 0){
                    return Long.compare(Long.parseLong(match.getT()),Long.parseLong(t1.getT()));
                }else{
                    return matchDate.compareTo(t1Date);
                }
            } catch (Exception e) {
                e.getMessage();
            }
            return 0;
        }
    }
        }
