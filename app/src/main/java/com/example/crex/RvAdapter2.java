package com.example.crex;

import android.content.Context;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class RvAdapter2 extends RecyclerView.Adapter {


    private ArrayList<MultiViewClass> upcomingMatchObjects;

    private Context context;
    private int flag;
    private ArrayList<MultiViewClass> finishedMatchObjects;
    // finished toast card

    public RvAdapter2(ArrayList<MultiViewClass> matchObjects, Context context, ArrayList<MultiViewClass> finishedMatches, int flag) {
        Log.e("Apurva", "Calling! RV Adapter");
        this.upcomingMatchObjects = matchObjects;
        this.context = context;
        this.flag = flag; //0 -> upcoming 1-> finished
        this.finishedMatchObjects = finishedMatches;
    }



    // Override the getItemViewType method.
    // This method uses a switch statement
    // to assign the layout to each item
    // depending on the viewType passed

    @Override
    public int getItemViewType(int position) {
        if(upcomingMatchObjects != null) {
            switch (upcomingMatchObjects.get(position).cardType) {
                case MATCH:
                    return 0;
                case DATE:
                    CARD:
                    return 1;
                case END:
                    return 2;
            }
        }else{
            switch (finishedMatchObjects.get(position).cardType) {
                case MATCH:
                    return 0;
                case DATE:
                    CARD:
                    return 1;
                case END:
                    return 2;
            }
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        try{
        if(flag==0){
        return upcomingMatchObjects.size();}
        else {
            return finishedMatchObjects.size();}}
        catch (Exception e){

        }
return 0;
    }
// Create classes for each layout ViewHolder.
//for the match card


    // In the onCreateViewHolder, inflate the
    // xml layout as per the viewType.
    // This method returns either of the
    // ViewHolder classes defined above,
    // depending upon the layout passed as a parameter.

    @NonNull
    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType) {

        switch (viewType) {
            case 0:
                View layoutOne
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cardf, parent,
                                false);
                return new LayoutOneViewHolder(layoutOne);
            case 1:
                View layoutTwo
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.date, parent,
                                false);
                return new LayoutTwoViewHolder(layoutTwo);
            case 2:
                View layoutThree
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card, parent,
                                false);
                return new LayoutThreeViewHolder(layoutThree);
            case 3:
                View layoutFour
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card2, parent,
                                false);
                return new LayoutThreeViewHolder(layoutFour);

            default:
                return null;
        }
    }

    // In onBindViewHolder, set the Views for each element
    // of the layout using the methods defined in the
    // respective ViewHolder classes.

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder, int position) {
        MultiViewClass multiViewClass=null;
        if(flag==0) {
            multiViewClass = upcomingMatchObjects.get(position);
        }
        else{
          multiViewClass = finishedMatchObjects.get(position);
        }
        switch (multiViewClass.cardType) {
            case MATCH:
                ((LayoutOneViewHolder)holder).setView(multiViewClass);
                break;
            case DATE:
                ((LayoutTwoViewHolder)holder).setView(multiViewClass);

                break;
            case END:
                ((LayoutThreeViewHolder)holder).setView(flag);
                if(flag==0) {
                    ((LayoutThreeViewHolder) holder)
                            .relativeLayout.setOnClickListener(
                                    new View.OnClickListener() {


                                            @Override
                                            public void onClick(View view)
                                            {

                                                Toast
                                                        .makeText(
                                                                view.getContext(),
                                                                "Open All Upcoming matches",
                                                                Toast.LENGTH_SHORT)
                                                        .show();
                                            }


                                    });
                }
                else
                {
                    ((LayoutThreeViewHolder) holder)
                            .relativeLayout.setOnClickListener(
                                    new View.OnClickListener() {


                                            @Override
                                            public void onClick(View view)
                                            {

                                                Toast
                                                        .makeText(
                                                                view.getContext(),
                                                                "Open All Finished matches",
                                                                Toast.LENGTH_SHORT)
                                                        .show();
                                            }


                                    });
                }

                break;

                default:
                return;
        }
    }


    // This method returns the count of items present in the
    // RecyclerView at any given time.
    class LayoutOneViewHolder
            extends RecyclerView.ViewHolder {

        private TextView m, t1, t2, date, t, od1, od2, w, winrun, score1, score2, over1, over2, odditem;
        private ImageView t1_flag, t2_flag;
        private RelativeLayout l1;
        TextView tab1, tab2, tab3, tab4;
        private LinearLayout linearLayout;

        public LayoutOneViewHolder(@NonNull View itemView) {
            super(itemView);


            // initializing our views with their ids.
            m = itemView.findViewById(R.id.matchName);
            t1 = itemView.findViewById(R.id.idTeamOneName);
            t2 = itemView.findViewById(R.id.idTeamTwoName);
            date = itemView.findViewById(R.id.idTimeUp);
            t = itemView.findViewById(R.id.idTimeDown);
            t1_flag = itemView.findViewById(R.id.idTeamOneImg);
            t2_flag = itemView.findViewById(R.id.idTeamTwoImg);
            od1 = itemView.findViewById(R.id.favour_odds_tv);
            od2 = itemView.findViewById((R.id.against_odds_tv));
            score2 = itemView.findViewById((R.id.team_2_score_tv));
            over1 = itemView.findViewById((R.id.team_1_over_tv));
            over2 = itemView.findViewById((R.id.team_2_over_tv));
            l1 = itemView.findViewById((R.id.odds_layout));
            tab1 = itemView.findViewById(R.id.team_1_score_tv);
            tab2 = itemView.findViewById(R.id.team_1_over_tv);
            tab3 = itemView.findViewById(R.id.team_2_score_tv);
            tab4 = itemView.findViewById(R.id.team_2_over_tv);
            odditem = itemView.findViewById(R.id.favour_team_name_tv);
            w = itemView.findViewById(R.id.idTimeDown);
            winrun=itemView.findViewById(R.id.idTimeUp);
            score1=itemView.findViewById(R.id.team_1_score_tv);
        }

        private void setView(MultiViewClass modal) {
            Log.e("Apurva","Binding Card!");
            if(flag == 0) {

                Log.e("Apurva","Binding!");

                if(modal.m.getClubbeddate() != null)
                {
                    //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date, parent, false);
                }
                m.setText(modal.m.getMatch_no());
                t1.setText(modal.m.getT1());
               t2.setText(modal.m.getT2());
                Date date1= new Date(modal.m.getDate());
                DateFormat dateFormat2 = new SimpleDateFormat(("d MMM"));
                String dateData= dateFormat2.format(date1);
              t.setText(dateData);
                Timestamp ts = new Timestamp(Long.parseLong(modal.m.getT()));
                Date datef = new Date(ts.getTime());
                DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                String strDate = dateFormat.format(datef);
                date.setText(strDate);
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                Calendar now= Calendar.getInstance();


                // SimpleDateFormat dateFormat1 = new SimpleDateFormat("ss S");
                long diff=0;
try {
    //SimpleDateFormat dateFormat3 = new SimpleDateFormat();
   // Date firstParsedDate = dateFormat3.parse(ts.toString());
    //Date secondParsedDate = dateFormat3.parse(currentTimestamp.toString());
    long a = ts.getTime();
    long b = currentTimestamp.getTime();
    diff = a - b;
}
catch(Exception e){}

                if(diff<=3600000 && diff>0) {

                    winrun.setText("Starting in:");
                    w.setTextColor(context.getResources().getColor(R.color.win_text));

                    new CountDownTimer(diff, 1000) {

                        public void onTick(long millisUntilFinished) {
                            long millis = millisUntilFinished;

                            String hms = String.format("%02dm:%02ds",

                                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                            //String hms = String.format("%02d:%02d:%02d",
                            //   TimeUnit.MILLISECONDS.toHours(millis),
                            //  TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                            // w.setText(" "+millisUntilFinished / 1000);
                            w.setText(hms);


                        }

                        @Override
                        public void onFinish() {
                            //  w.setText("00:00:00");
                        }


                    }.start();
                }
                    // diff=20000;
                    if ( diff > 3600000 && diff <= 10800000) {

                        winrun.setText("Starting in:");
                        w.setTextColor(context.getResources().getColor(R.color.win_text));

                        new CountDownTimer(diff, 1000) {

                            public void onTick(long millisUntilFinished) {
                                long millis = millisUntilFinished;

                                String hms = String.format("%02dh:%02dm",
                                        TimeUnit.MILLISECONDS.toHours(millis),
                                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));
                                //String hms = String.format("%02d:%02d:%02d",
                                //   TimeUnit.MILLISECONDS.toHours(millis),
                                //  TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                                // w.setText(" "+millisUntilFinished / 1000);
                                w.setText(hms);


                            }

                            @Override
                            public void onFinish() {
                                //  w.setText("00:00:00");
                            }


                        }.start();


                    }


                //Date d = new Date(modal.getT());
                // DateFormat dateFormat = new SimpleDateFormat("dd MM");
                // String strDate = dateFormat.format(d);
                // holder.t.setText(strDate);

               od1.setText(modal.m.getRate());
             od2.setText(modal.m.getRate2());

                Picasso.get().load(modal.m.getT1_flag()).into(t1_flag);
                Picasso.get().load(modal.m.getT2_flag()).into(t2_flag);
                if(modal.m.getRate() == null || TextUtils.isEmpty(modal.m.getRate()))
                {
                    odditem.setVisibility(View.GONE);
                }else{
                    odditem.setVisibility(View.VISIBLE);
                }
                //set visibile
            }else{

                m.setText(modal.m.getMatch_no());
               t1.setText(modal.m.getT1());
               t2.setText(modal.m.getT2());
                t.setText(modal.m.getDate());
               date.setText(modal.m.getT());
                String wonrun ="";
                int indexOfby=0;
                try{
                    indexOfby = (modal.m.getResult()).indexOf("by");
                    wonrun=(modal.m.getResult()).substring(indexOfby);
                }
                catch (Exception e)
                {

                }
                w.setText(wonrun);
                winrun.setText(modal.m.getWinner()+" WON ");
                winrun.setTextColor(context.getResources().getColor(R.color.win_text));
                winrun.setTextSize(18);
                // holder.winrun.setTextSize(16);
                score1.setText(modal.m.getScore1());
                score2.setText(modal.m.getScore2());
                over1.setText(modal.m.getOvers1());
                over2.setText(modal.m.getOvers2());
                Picasso.get().load(modal.m.getT1_flag()).into(t1_flag);
                Picasso.get().load(modal.m.getT2_flag()).into(t2_flag);
                tab1.setVisibility(View.VISIBLE);
                tab2.setVisibility(View.VISIBLE);
                tab3.setVisibility(View.VISIBLE);
                tab4.setVisibility(View.VISIBLE);
                l1.setVisibility(View.INVISIBLE);
                winrun.setTypeface(null, Typeface.BOLD);

                // holder.w.setVisibility(View.INVISIBLE);
                //  holder.odditem.setBackgroundColor(Color.parseColor(#252F72));
                //  holder.od1.setBackgroundColor(#005E9A);
                // holder.od2.setBackgroundColor(#A64040);
                //set visible zero
            }


        }
    }

    // similarly a class for the second layout is also
// created.
//for the date
    class LayoutTwoViewHolder
            extends RecyclerView.ViewHolder {

        private TextView clubbedDate;
        private LinearLayout linearLayout;

        private final SimpleDateFormat dateSDF = new SimpleDateFormat("MM/dd/yyyy");
        private final SimpleDateFormat clubbedDateSDF = new SimpleDateFormat("EEE, d MMM");
        public LayoutTwoViewHolder(@NonNull View itemView) {
            super(itemView);

            clubbedDate = itemView.findViewById(R.id.clubbedDate);
        }

        private void setView(MultiViewClass modal) {
            String mockDate="";
            mockDate=getDayValue(modal.getDate());

          clubbedDate.setText(mockDate);
        }
        public String getDayValue(String dateString){
            try
            {
                Date date= dateSDF.parse(dateString);
                clubbedDateSDF.applyPattern("EEE, d MMMM");
                String str = clubbedDateSDF.format(date);
                if(DateUtils.isToday(date.getTime())){
                    return "Today"+str.substring(3);
                }else if(DateUtils.isToday(date.getTime() - DateUtils.DAY_IN_MILLIS)){
                    return "Tomorrow"+str.substring(3);
                }else{
                    return str;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

    // for the last card in upcoming
    class LayoutThreeViewHolder
            extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView toastMessage;
        //  private LinearLayout linearLayout;
        public LayoutThreeViewHolder(@NonNull View itemView) {

            super(itemView);
          relativeLayout=itemView.findViewById(R.id.idfinalcard);
          toastMessage = itemView.findViewById(R.id.toastMessage);
        }

        private void setView(int flag) {
            if(flag == 0){
                toastMessage.setText("All Upcoming Matches");
            }else{
                toastMessage.setText("All Finished Matches");
            }
        }
    }

    //for the last card in finished
    class LayoutFourViewHolder
            extends RecyclerView.ViewHolder {

        //  private LinearLayout linearLayout;
        RelativeLayout relativeLayout;
        public LayoutFourViewHolder(@NonNull View itemView) {
            super(itemView);

relativeLayout= itemView.findViewById(R.id.idfinalcard2);
        }

        private void setView() {


        }
    }
}
