package com.example.crex;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;

import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<Match> matchObjects;
    private Context context;
    private int flag;
    private ArrayList<Match> finishedMatchObjects;
    public static final int LayoutOne = 0;
    public static final int LayoutTwo = 1;
    public static final int LayoutThree =2;


    // creating a constructor for our variables.
    public RvAdapter(ArrayList<Match> matchObjects, Context context,ArrayList<Match> finishedMatches, int flag) {
        Log.e("Apurva","Calling! RV Adapter");
        this.matchObjects = matchObjects;
        this.context = context;
        this.flag = flag; //0 -> upcoming 1-> finished
        this.finishedMatchObjects = finishedMatches;
    }

    @NonNull
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardf, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        Log.e("Apurva","Binding! check flag");

        if(flag == 0) {

            Log.e("Apurva","Binding!");
            Match modal = matchObjects.get(position);
            if(modal.getClubbeddate() != null)
            {
                //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date, parent, false);
            }
            holder.m.setText(modal.getMatch_no());
            holder.t1.setText(modal.getT1());
            holder.t2.setText(modal.getT2());
            Date date1= new Date(modal.getDate());
            DateFormat dateFormat2 = new SimpleDateFormat(("d MMM"));
            String dateData= dateFormat2.format(date1);
            holder.t.setText(dateData);
            Timestamp ts = new Timestamp(Long.parseLong(modal.getT()));
            Date date = new Date(ts.getTime());
            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            String strDate = dateFormat.format(date);
            holder.date.setText(strDate);
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            Calendar now= Calendar.getInstance();

           // SimpleDateFormat dateFormat1 = new SimpleDateFormat("ss S");
            long diff=0;
            try {
                SimpleDateFormat dateFormat3 = new SimpleDateFormat("hh:mm:ss");
                Date firstParsedDate = dateFormat3.parse(ts.toString());
                Date secondParsedDate = dateFormat3.parse(currentTimestamp.toString());

                diff =   secondParsedDate.getTime()-firstParsedDate.getTime();


            }
            catch (Exception e){}
           // diff=20000;
           if(diff<10800000|| diff>0)
            {

             // holder.winrun.setText("Starting in:");
               new CountDownTimer(diff, 1000) {

    public void onTick(long millisUntilFinished) {
      holder.w.setText(" "+millisUntilFinished / 1000);

    }

                   @Override
                   public void onFinish() {
                     // holder.w.setText("00:00:00");
                   }


               }.start();



            }

            //Date d = new Date(modal.getT());
           // DateFormat dateFormat = new SimpleDateFormat("dd MM");
           // String strDate = dateFormat.format(d);
           // holder.t.setText(strDate);

            holder.od1.setText(modal.getRate());
            holder.od2.setText(modal.getRate2());

            Picasso.get().load(modal.getT1_flag()).into(holder.t1_flag);
            Picasso.get().load(modal.getT2_flag()).into(holder.t2_flag);
            if(modal.getRate() == null || TextUtils.isEmpty(modal.getRate()))
            {
                holder.odditem.setVisibility(View.GONE);
            }else{
                holder.odditem.setVisibility(View.VISIBLE);
            }
            //set visibile
        }else{
            Match modal = finishedMatchObjects.get(position);
            holder.m.setText(modal.getMatch_no());
            holder.t1.setText(modal.getT1());
            holder.t2.setText(modal.getT2());
            holder.t.setText(modal.getDate());
            holder.date.setText(modal.getT());
           String wonrun ="";
            int indexOfby=0;
            try{
                indexOfby = (modal.getRate_team()).indexOf("by");
                wonrun=(modal.getRate_team()).substring(indexOfby);
           }
            catch (Exception e)
            {

            }
            holder.w.setText(wonrun);
            holder.winrun.setText(modal.getResult() + "  WON  ");
            holder.winrun.setTextColor(context.getResources().getColor(R.color.win_text));
            holder.winrun.setTextSize(15);
           // holder.winrun.setTextSize(16);
            holder.score1.setText(modal.getScore1());
            holder.score2.setText(modal.getScore2());
            holder.over1.setText(modal.getOvers1());
            holder.over2.setText(modal.getOvers2());
            Picasso.get().load(modal.getT1_flag()).into(holder.t1_flag);
            Picasso.get().load(modal.getT2_flag()).into(holder.t2_flag);
            holder.tab1.setVisibility(View.VISIBLE);
            holder.tab2.setVisibility(View.VISIBLE);
            holder.tab3.setVisibility(View.VISIBLE);
            holder.tab4.setVisibility(View.VISIBLE);
            holder.l1.setVisibility(View.INVISIBLE);
            holder.winrun.setTypeface(null, Typeface.BOLD);

           // holder.w.setVisibility(View.INVISIBLE);
          //  holder.odditem.setBackgroundColor(Color.parseColor(#252F72));
          //  holder.od1.setBackgroundColor(#005E9A);
           // holder.od2.setBackgroundColor(#A64040);
            //set visible zero
        }


    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        if(flag == 0) {
            return matchObjects.size();
        }else{
            return finishedMatchObjects.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView m,t1,t2,date,t,od1,od2,w,winrun,score1,score2,over1,over2,odditem;
        private ImageView t1_flag,t2_flag;
        private RelativeLayout l1;
        TextView tab1,tab2,tab3,tab4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            m = itemView.findViewById(R.id.matchName);
            t1 = itemView.findViewById(R.id.idTeamOneName);
            t2 = itemView.findViewById(R.id.idTeamTwoName);
            date= itemView.findViewById(R.id.idTimeUp);
            t=itemView.findViewById(R.id.idTimeDown);
            t1_flag = itemView.findViewById(R.id.idTeamOneImg);
            t2_flag=itemView.findViewById(R.id.idTeamTwoImg);
            od1=itemView.findViewById(R.id.favour_odds_tv);
            od2=itemView.findViewById((R.id.against_odds_tv));
            w=itemView.findViewById((R.id.idTimeDown));
            winrun=itemView.findViewById((R.id.idTimeUp));
            score1=itemView.findViewById((R.id.team_1_score_tv));
            score2=itemView.findViewById((R.id.team_2_score_tv));
            over1=itemView.findViewById((R.id.team_1_over_tv));
            over2=itemView.findViewById((R.id.team_2_over_tv));
            l1=itemView.findViewById((R.id.odds_layout));
            tab1=itemView.findViewById(R.id.team_1_score_tv);
            tab2=itemView.findViewById(R.id.team_1_over_tv);
            tab3=itemView.findViewById(R.id.team_2_score_tv);
            tab4=itemView.findViewById(R.id.team_2_over_tv);
            odditem=itemView.findViewById(R.id.favour_team_name_tv);
        }
    }


}


