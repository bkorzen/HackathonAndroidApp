package com.example.barti.hackathon;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button button_add, button_submit;
    private List<Integer> fields = new ArrayList<>();
    private StudyService service;
    private LinearLayout container;
    private LinearLayout container2;
    private List<EditText> lista = new ArrayList<>();

    private static Button btn;

    MainActivity() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://4a23.buddy.show/")
                .build();

        this.service = retrofit.create(StudyService.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.container = (LinearLayout)findViewById(R.id.linearLayout);
        this.container2 = (LinearLayout)findViewById(R.id.linearLayout2);
        createPlainText();
        buttonOnClick();
    }

    public void createPlainText() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        int dpValue=15;//value in dp
        int pxValue= (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources()
                        .getDisplayMetrics());

        params.setMargins(0,pxValue,0,0);

        EditText edt = new EditText(MainActivity.this);
        edt.setLayoutParams(params);
        edt.setEms(10);
        edt.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        edt.setBackgroundColor(Color.parseColor("#e7e2e2"));
        edt.setPadding(0,0,0,0);

        container.addView(edt);

        lista.add(edt);
    }

    public void createMultilineText(String str) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        int dpValue=5;//value in dp
        int pxValue= (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources()
                        .getDisplayMetrics());

        params.setMargins(0,pxValue,0,0);

        EditText edt = new EditText(MainActivity.this);
        edt.setLayoutParams(params);
        edt.setEms(10);
        edt.setText(str);
        edt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        edt.setBackgroundColor(Color.parseColor("#e7e2e2"));
        edt.setPadding(0,0,0,0);

        container2.addView(edt);
    }

    public void buttonOnClick() {

        button_add = (Button)findViewById(R.id.button_add);
        button_submit = (Button)findViewById(R.id.button_submit);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<String> txt = new HashSet<>();

                for (EditText t : lista) {

                    txt.addAll(Arrays.asList(t.getText()
                                    .toString()
                                    .trim()
                                    .split("\\s+")
                            )
                    );
                }
                container2.removeAllViewsInLayout();

                String str = "";

                for (String s : txt) {
                    if (!s.equals(""))
                        str += "," + s;
                }

                if (!str.equals("")) {
                    str = str.substring(1, str.length());
                    System.out.println(str);
                    System.out.println(lista.size());

                    Call<List<Study>> studies = service.listStudies(str);
                    System.out.println(studies.request().url());
                    studies.enqueue(new StudiesCallback()); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                    Intent intent = new Intent("com.example.barti.hackathon.ResultsActivity");
//                    startActivity(intent);
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            "Brak danych!",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPlainText();
            }
        });
    }

    class StudiesCallback implements Callback<List<Study>> {

        @Override
        public void onResponse(Call<List<Study>> call, Response<List<Study>> response) {
            System.out.println("git");
            for(Study study:response.body()){
                //System.out.println(study.getName());
                String str = study.getDepartament() + "\n" + study.getName();
                createMultilineText(str);
            }
        }

        @Override
        public void onFailure(Call<List<Study>> call, Throwable t) {
            System.out.println("wyebane est");
        }
    }
}
