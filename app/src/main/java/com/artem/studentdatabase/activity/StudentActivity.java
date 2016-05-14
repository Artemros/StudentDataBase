package com.artem.studentdatabase.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.artem.studentdatabase.R;
import com.artem.studentdatabase.entity.Student;
import com.artem.studentdatabase.provider.InformationProvider;
import com.artem.studentdatabase.provider.RemoteInformationProvider;

import java.util.ArrayList;

public class StudentActivity extends Activity {

    InformationProvider informationProvider;
    private ListView groupList;
    private EditText filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        informationProvider = new RemoteInformationProvider();
        ArrayList<Student> arrayList = informationProvider.getAllStudents();
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        filter = (EditText) findViewById(R.id.editTextStudentName);
        groupList = (ListView) findViewById(R.id.listView);
        groupList.setAdapter(arrayAdapter);
        groupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student) parent.getAdapter().getItem(position);
                Bundle dataBundle = new Bundle();
                dataBundle.putLong("id", student.getId());
                Intent intent = new Intent(getApplicationContext(), PersonalInformationsActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void goToPersonalInformationsActivity(View view) {
        Intent intent = new Intent(this, PersonalInformationsActivity.class);
        startActivity(intent);
    }
}
