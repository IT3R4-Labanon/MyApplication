package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

public class CriteriaAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] criteriaName;
    String[] criteriaTotal;
    String[] criteriaScore;

    public CriteriaAdapter(Context c, String[] n, String[] i, String[] d) {
        criteriaName = n;
        criteriaTotal = i;
        criteriaScore = d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return criteriaName.length;
    }

    @Override
    public Object getItem(int position) {
        return criteriaName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.listrow_criteria,null);
        TextView tv_criterianame = (TextView) v.findViewById(R.id.tv_criterianame);
        TextView tv_criteriatotal = (TextView) v.findViewById(R.id.tv_criteriatotal);
        TextView tv_criteriascore  = (TextView) v.findViewById(R.id.tv_criteriascore);

        String name = criteriaName[position];
        String total = criteriaTotal[position];
        String score = criteriaScore[position];

        tv_criterianame.setText(name);
        tv_criteriatotal.setText(total);
        tv_criteriascore.setText(score);
        return v;
    }
}

