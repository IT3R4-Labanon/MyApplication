package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Activities.ContestantsActivity;
import com.example.myapplication.Activities.CriteriaActivity;
import com.example.myapplication.R;

public class ContestantsAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] tv_conName;
    String[] tv_conAge;
    String[] tv_conAddress;
    String[] tv_conScore;
    int[] iv_conIcon;

    public ContestantsAdapter(Context c, String[] c_name, int[] c_icon, String[] c_age, String[] c_add,String[] c_score) {
        tv_conName = c_name;
        this.iv_conIcon = c_icon;
        tv_conAge = c_age;
        tv_conAddress = c_add;
        tv_conScore = c_score;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return tv_conName.length;
    }

    @Override
    public Object getItem(int position) {
        return tv_conName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.listrow_contestants,null);
        TextView conName = (TextView) v.findViewById(R.id.tv_conName);
        ImageView conIcon = (ImageView) v.findViewById(R.id.iv_conIcon);
        TextView conAge = (TextView) v.findViewById(R.id.tv_conAge);
        TextView conAddress = (TextView) v.findViewById(R.id.tv_conAddress);
        TextView conScore = (TextView) v.findViewById(R.id.tv_conScore);

        String name = tv_conName[position];
        String age = tv_conAge[position];
        String address = tv_conAddress[position];
        String score = tv_conScore[position];

        conName.setText(name);
        conIcon.setImageResource(iv_conIcon[position]);
        conAge.setText(age);
        conAddress.setText(address);
        conScore.setText(score);


        return v;
    }
}
