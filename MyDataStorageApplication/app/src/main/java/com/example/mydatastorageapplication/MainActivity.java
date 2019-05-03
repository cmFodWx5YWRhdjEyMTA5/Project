package com.example.mydatastorageapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final String MY_PREFERENCE_NAME = "mybundle";

    EditText name, lastname, qualification;
    TextView tname, tlastname, tqualification;
    Button submit, fetch, fetchAll;
    ListView listView;

    SharedPreferences sharedPreferences;
    MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name  = findViewById(R.id.name);
        lastname = findViewById(R.id.lastname);
        qualification = findViewById(R.id.qualification);
        tname = findViewById(R.id.nametext);
        tlastname = findViewById(R.id.lastnametex);
        tqualification = findViewById(R.id.qualificationtext);
        submit = findViewById(R.id.submit);
        fetch = findViewById(R.id.fetch);
        fetchAll = findViewById(R.id.fetchAll);
        listView = findViewById(R.id.listview);

        sharedPreferences = getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        myHelper = new MyHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", name.getText().toString());
                editor.putString("lastname", lastname.getText().toString());
                editor.putString("qualification", qualification.getText().toString());
                editor.commit();
                */
                Person person = new Person(name.getText().toString(), lastname.getText().toString(), qualification.getText().toString());
                myHelper.addPerson(person);

                Toast.makeText(getApplicationContext(), "Data submitted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* from SharedPreferences
                String nametext = sharedPreferences.getString("name", "");
                String lastnametext = sharedPreferences.getString("lastname", "");
                String qualificationtext = sharedPreferences.getString("qualification", "");
                */

                Person person = myHelper.getPersonById(1);
                String nametext = person.getName();
                String lastnametext = person.getLastname();
                String qualificationtext = person.getQualification();

                tname.setText(nametext);
                tlastname.setText(lastnametext);
                tqualification.setText(qualificationtext);

                listView.setVisibility(View.INVISIBLE);
                tname.setVisibility(View.VISIBLE);
                tlastname.setVisibility(View.VISIBLE);
                tqualification.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(), "Data fetched successfully", Toast.LENGTH_SHORT).show();
            }
        });

        fetchAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Person> list = myHelper.getAllPerson();
                ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, list);
                listView.setAdapter(adapter);

                tname.setVisibility(View.INVISIBLE);
                tlastname.setVisibility(View.INVISIBLE);
                tqualification.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.VISIBLE);
            }
        });
    }
}
