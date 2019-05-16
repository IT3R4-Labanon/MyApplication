package com.example.myapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapplication.Adapters.ContestantAdapter;
import com.example.myapplication.Models.Contestants;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Debugger;
import com.example.myapplication.Utils.HttpProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import es.dmoral.toasty.Toasty;

public class ContestantsActivity extends AppCompatActivity {

    Context context;
    ListView lv_contestants;
    View emptyIndicator;
    ArrayList<Contestants> contestantArrayList;
    ContestantAdapter contestantAdapter;

    private SearchView etSearch;
    private static String POPUP_CONSTANT = "mPopup";
    private static String POPUP_FORCE_SHOW_ICON = "setForceShowIcon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contestants);
        context=this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Contestants");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        HttpProvider.post(context, "contestants/read/", null, new JsonHttpResponseHandler(){

            @Override
            public void onStart() {
                super.onStart();
                Debugger.logD("CONTESTANTS NI");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    contestantArrayList = new Gson().fromJson(response.getJSONArray("records").toString(), new TypeToken<ArrayList<Contestants>>(){}.getType());
                    readRecords("");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }


    public void onStart() {
        super.onStart();

        initializeUI();
        registerForContextMenu(lv_contestants);
    }

    private void showEmptyListIndicator(boolean show)
    {
        emptyIndicator.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    private void initializeUI()
    {
        lv_contestants = (ListView) findViewById(R.id.lv_contestants);
        emptyIndicator = (View) findViewById(R.id.viewEmptyListIndicator);
        etSearch = (SearchView)  findViewById(R.id.etSearch);

        lv_contestants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lv_contestants.showContextMenu();
                Toasty.success(context, contestantAdapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        etSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                readRecords(s);
                return true;
            }
        });
    }
    private void readRecords(String search)
    {
        contestantAdapter = new ContestantAdapter(context, contestantArrayList );
        lv_contestants.setAdapter(contestantAdapter);
        showEmptyListIndicator(contestantArrayList.size() <= 0);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.lv_contestants)
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.popup_menu, menu);
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.popup_input:
                startActivity(new Intent(this,CriteriaActivity.class));
                return true;
            case R.id.popup_view:
                startActivity(new Intent(this,ViewScoresActivity.class));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
