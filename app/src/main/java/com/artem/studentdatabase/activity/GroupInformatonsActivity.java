package com.artem.studentdatabase.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.artem.studentdatabase.R;
import com.artem.studentdatabase.entity.Group;
import com.artem.studentdatabase.provider.InformationProvider;
import com.artem.studentdatabase.provider.RemoteInformationProvider;

public class GroupInformatonsActivity extends Activity {

    TextView name;
    Button deleteButton;
    private InformationProvider groupProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupProvider = new RemoteInformationProvider();
        setContentView(R.layout.activity_group_informatons);
        name = (TextView) findViewById(R.id.editTextNumber);
        deleteButton = (Button) findViewById(R.id.button101);
        Bundle extras = getIntent().getExtras();
        long id = 0;
        if (extras != null) {
            id = extras.getLong("id");
            Group group = groupProvider.getGroupById(id);
            name.setText(group.getName());
        } else {
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


    public void saveGroup(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long groupId = extras.getLong("id");
            if (groupId > 0) {
                if (groupProvider.updateGroup(groupId, name.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Изменено", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), GroupActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT);
                }
            }

        } else {
            if (groupProvider.insertGroup(name.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Сохранено", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT);
            }
        }
        Intent intent = new Intent(getApplicationContext(), GroupActivity.class);
        startActivity(intent);
    }

    public void deleteGroup(View view) {
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("id");
        groupProvider.deleteGroup(id);
        Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
        startActivity(intent);
    }
}
