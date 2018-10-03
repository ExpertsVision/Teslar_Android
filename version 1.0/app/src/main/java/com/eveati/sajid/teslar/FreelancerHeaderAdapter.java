package com.eveati.sajid.teslar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FreelancerHeaderAdapter extends ArrayAdapter<Freelancer> {


    public FreelancerHeaderAdapter (@NonNull Context context, ArrayList<Freelancer> resource) {
        super(context, 0,resource);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View newView ;

        newView = convertView ;

        if (newView == null){

            newView = LayoutInflater.from(getContext()).inflate(R.layout.freelancer_list_view , null);

        }

        Freelancer freelancer = getItem(position);


        TextView textView1 = (TextView) newView.findViewById(R.id.text1);
        TextView textView2 = (TextView) newView.findViewById(R.id.text2);
        TextView textView3 = (TextView) newView.findViewById(R.id.text3);
        TextView textView4 = (TextView) newView.findViewById(R.id.text4);

        TextView textView5 = (TextView) newView.findViewById(R.id.text5);
        TextView textView6 = (TextView) newView.findViewById(R.id.text6);
        TextView textView7 = (TextView) newView.findViewById(R.id.text7);
        TextView textView8 = (TextView) newView.findViewById(R.id.text8);


        textView1.setText("Complaint ID");
        textView2.setText("Title");
        textView3.setText("Description");
        textView4.setText("Status");

        textView5.setText("Submissive Date");
        textView6.setText("Last Visit Date");
        textView7.setText("Remarks");
        textView8.setText("Closing Date");


        return newView ;
    }
}

