package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class EventAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] eventName;
    String[] eventDate;
    String[] eventTime;
    String[] eventLocation;
    int[] eventIcon;

    public EventAdapter(Context c, String[] ename, int[] eventIcon, String[] edate,String[] etime, String[] elocation) {
        eventName = ename;
        this.eventIcon = eventIcon;
        eventDate = edate;
        eventTime = etime;
        eventLocation = elocation;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return eventName.length;
    }

    @Override
    public Object getItem(int position) {
        return eventName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.listrow_events,null);
        TextView tv_eventname = (TextView) v.findViewById(R.id.tv_eventname);
        ImageView iv_eventicon = (ImageView) v.findViewById(R.id.iv_eventicon);
        TextView tv_eventdate = (TextView) v.findViewById(R.id.tv_eventdate);
        TextView tv_eventtime = (TextView) v.findViewById(R.id.tv_eventtime);
        TextView tv_eventlocation = (TextView) v.findViewById(R.id.tv_eventlocation);

//        String name = eventName[position];
//        String desc = eventDesc[position];

        tv_eventname.setText(eventName[position]);
        iv_eventicon.setImageResource(eventIcon[position]);
        tv_eventdate.setText(eventDate[position]);
        tv_eventtime.setText(eventTime[position]);
        tv_eventlocation.setText(eventLocation[position]);
       // tv_eventdate.setText(eve);
        return v;
    }
}
