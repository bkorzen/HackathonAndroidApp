package com.example.barti.hackathon;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ResultsActivity extends AppCompatActivity {

    private LinearLayout container2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        this.container2 = (LinearLayout)findViewById(R.id.linearLayout);
        showResults();
        onClickListener();
    }

    public void onClickListener() {
        Button button_back = (Button)findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent("com.example.barti.hackathon.MainActivity");
//                startActivity(intent);
//                finish();
                showResults();
            }
        });
    }

    public void showResults() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        int dpValue=5;//value in dp
        int pxValue= (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources()
                        .getDisplayMetrics());

        params.setMargins(0,pxValue,0,0);

        EditText edt = new EditText(ResultsActivity.this);
        edt.setLayoutParams(params);
        edt.setEms(10);
        edt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        edt.setBackgroundColor(Color.parseColor("#e7e2e2"));
        edt.setPadding(0,0,0,0);

        container2.addView(edt);
    }
}
