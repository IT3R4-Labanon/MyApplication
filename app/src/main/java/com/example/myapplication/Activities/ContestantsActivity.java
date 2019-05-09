package com.example.myapplication.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.myapplication.Adapters.ContestantsAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.UserSession;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ContestantsActivity extends AppCompatActivity {

    Context context;
    ListView lv_contestants;
    String[] conName={"Catriona M. Gray","Pia P. Wurtzbach","Juani J. Juani","Juana J. Juana","Venus V. Raj"};
    String[] conAge={"23","19","25","21","26"};
    String[] conAddress={"Cagayan de Oro City","Quezon City","Cebu","Tarlac City","Isabela  City"};
    String[] conScore={"99%","85%","90%","87%","95%"};
    int[] conIcon = {R.drawable.con_icon1,R.drawable.con_icon2,R.drawable.con_icon3,R.drawable.con_icon4,R.drawable.con_icon2};
    View emptyIndicator;
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

        onStart();
    }

    public void onStart() {
        super.onStart();

        initializeUI();
        readRecords();
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

        lv_contestants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              lv_contestants.showContextMenu();
            }
        });

    }
    private void readRecords()
    {
        ContestantsAdapter contestantsAdapter = new ContestantsAdapter(context,conName,conIcon,conAge,conAddress,conScore);
        lv_contestants.setAdapter(contestantsAdapter);

        showEmptyListIndicator(conName.length <= 0);
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
