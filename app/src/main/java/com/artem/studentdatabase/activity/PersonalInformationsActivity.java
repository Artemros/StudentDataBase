package com.artem.studentdatabase.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.artem.studentdatabase.R;
import com.artem.studentdatabase.entity.Group;
import com.artem.studentdatabase.entity.Student;
import com.artem.studentdatabase.provider.InformationProvider;
import com.artem.studentdatabase.provider.RemoteInformationProvider;

import java.util.List;

public class PersonalInformationsActivity extends Activity {

    private TextView name;
    private TextView lastName;
    private TextView thirdName;
    private Spinner spinner;
    private InformationProvider informationProvider;
    private Button deleteButton;
    private Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_informations);
        name = (TextView) findViewById(R.id.editTextName);
        lastName = (TextView) findViewById(R.id.editTextSecondName);
        thirdName = (TextView) findViewById(R.id.editTextThirdName);
        informationProvider = new RemoteInformationProvider();
        Bundle extras = getIntent().getExtras();
        long id = 0;
        if (extras != null) {
            id = extras.getLong("id");
        }
        List<Group> groups = informationProvider.getAllGroups();
        spinner = (Spinner) findViewById(R.id.spinnerGroupStudent);
        final ArrayAdapter groupAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, groups);
        spinner.setAdapter(groupAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                group = (Group) parent.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (id > 0) {
            Student student = informationProvider.getStudentById(id);
            String name = student.getName();
            String secondname = student.getLastName();
            String thirdname = student.getThirdName();
            this.name.setText(name);
            lastName.setText(secondname);
            thirdName.setText(thirdname);
            group = informationProvider.getStudentGroup(id);
            int pos = groupAdapter.getPosition(group);
            spinner.setSelection(pos);
        } else {
            deleteButton = (Button) findViewById(R.id.button6);
            deleteButton.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal_informations, menu);
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


    public void save(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long id = extras.getLong("id");
            if (id > 0) {
                if (informationProvider.updateStudent(id, name.getText().toString(), lastName.getText().toString(),
                        thirdName.getText().toString(), group)) {
                    Toast.makeText(getApplicationContext(), "Изменено", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT);
                }
            }

        } else {
            if (informationProvider.insertStudent(name.getText().toString(), lastName.getText().toString(),
                    thirdName.getText().toString(), group)) {
                Toast.makeText(getApplicationContext(), "Сохранено", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
        startActivity(intent);
    }

    public void deleteStudent(View view) {
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("id");
        informationProvider.deleteStudent(id);
        Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
        startActivity(intent);
    }


}
