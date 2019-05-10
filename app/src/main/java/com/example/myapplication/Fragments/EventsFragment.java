package com.example.myapplication.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Activities.ContestantsActivity;
import com.example.myapplication.Adapters.EventAdapter;
import com.example.myapplication.Adapters.EventsAdapter;
import com.example.myapplication.Models.Event;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Debugger;
import com.example.myapplication.Utils.HttpProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import es.dmoral.toasty.Toasty;

public class EventsFragment extends Fragment {

    private View view;
    private Context context;
    ListView listrow_events;
    String[] eventName={"MS. UNIVERSE 2018","MR. and MS. IT 2019","PAINTING CONTEST","MISS CDO 2019","INTRAMURALS 2019"};
    String[] eventDate={"05/21/19","05/28/19","05/26/19","06/12/19","06/26/19"};
    String[] eventTime={"8pm","10am","12pm","4pm","1pm"};
    String[] eventLocation={"Resorts World","USTP Gym","LKK Atrium","Ustp Gym","Grand Caprice"};
    int[] eventIcon = {R.drawable.eventicon,R.drawable.eventicon2,R.drawable.eventicon3,R.drawable.eventicon2,R.drawable.eventicon4};
    View emptyIndicator;

    ArrayList<Event> eventArrayList;
    EventsAdapter eventAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_events, container, false);
        context = getContext();
        setPageTitle("Events");

        HttpProvider.post(context, "event/read/", null, new JsonHttpResponseHandler(){

            @Override
            public void onStart() {
                super.onStart();
                Debugger.logD("NAG START");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    eventArrayList = new Gson().fromJson(response.getJSONArray("records").toString(), new TypeToken<ArrayList<Event>>(){}.getType());
                    readRecords();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;

    }


    public void onStart() {
        super.onStart();

        initializeUI();
        registerForContextMenu(listrow_events);
    }

    private void showEmptyListIndicator(boolean show)
    {
        emptyIndicator.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    private void initializeUI()
    {
        listrow_events = (ListView) view.findViewById(R.id.lv_events);
        emptyIndicator = (View) view.findViewById(R.id.viewEmptyListIndicator);

        listrow_events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toasty.success(context, eventAdapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();

//                startActivity(new Intent(view.getContext(), ContestantsActivity.class));




            }
        });


    }
    private void readRecords()
    {
        eventAdapter = new EventsAdapter(context, eventArrayList );
        listrow_events.setAdapter(eventAdapter);
        showEmptyListIndicator(eventArrayList.size() <= 0);
    }



    public void setPageTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>"+title+ "</font>"));
    }
}


