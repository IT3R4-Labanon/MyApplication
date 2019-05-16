package com.example.myapplication.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapters.CriteriaAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import es.dmoral.toasty.Toasty;

public class CriteriaActivity extends AppCompatActivity {

    Context context;
    ListView lv_criteria;
    View emptyIndicator;
    TextView tv_criteriatype,tv_criteriascore;
    EditText et_inputscore;
    Button btn_save,btn_cancel;
    String[] criteriaName={"CRITERIA NO. 1","CRITERIA NO. 2","CRITERIA NO. 3","CRITERIA NO. 4"};
    String[] criteriaTotal={"30%","20%","40%","10%"};
    String[] criteriaInputScore={"0","0","0","0"};
    String[] scores = {"1","2","3"};
    Dialog inputscoreDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criteria);

        inputscoreDialog = new Dialog(this);
        context=this;


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Criteria");
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
        registerForContextMenu(lv_criteria);

    }

    private void showEmptyListIndicator(boolean show)
    {
        emptyIndicator.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    private void initializeUI()
    {
        lv_criteria = (ListView) findViewById(R.id.lv_criteria);
        emptyIndicator = (View) findViewById(R.id.viewEmptyListIndicator);

        lv_criteria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TextView tv_criteriatype = (TextView) findViewById(R.id.tv_criteriatype);
                //tv_criteriatype.setText();
                ShowPopup(context);
            }
        });

    }

    private void readRecords()
    {
        CriteriaAdapter criteriaAdapter = new CriteriaAdapter(context,criteriaName,criteriaTotal,criteriaInputScore);
        lv_criteria.setAdapter(criteriaAdapter);

        showEmptyListIndicator(criteriaName.length <= 0);
    }

    public void ShowPopup(Context c) {
        Button btn_save, btn_cancel;
        inputscoreDialog.setContentView(R.layout.popup_inputscores);
        btn_save =(Button) inputscoreDialog.findViewById(R.id.btn_save);
        btn_cancel = (Button) inputscoreDialog.findViewById(R.id.btn_cancel);
       // et_inputscore = (EditText) inputscoreDialog.findViewById(R.id.et_inputscore);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(et_inputscore.getText() == null) {
//                    Toasty.warning(context,"Please input your score",Toast.LENGTH_SHORT);
//                } else {
                    inputscoreDialog.dismiss();
                    Toasty.success(context,"Saved successfully", Toast.LENGTH_SHORT);

//                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputscoreDialog.dismiss();
            }
        });
        inputscoreDialog.show();
    }
}
