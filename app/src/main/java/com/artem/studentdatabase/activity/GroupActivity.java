package com.artem.studentdatabase.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.artem.studentdatabase.R;
import com.artem.studentdatabase.entity.Group;
import com.artem.studentdatabase.provider.InformationProvider;
import com.artem.studentdatabase.provider.RemoteInformationProvider;

import java.util.ArrayList;

public class GroupActivity extends Activity {

    InformationProvider informationProvider;
    private ListView obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        informationProvider = new RemoteInformationProvider();
        ArrayList arrayList = informationProvider.getAllGroups();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        obj = (ListView) findViewById(R.id.groupListView);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Group group = (Group) parent.getItemAtPosition(position);
                Bundle dataBundle = new Bundle();
                dataBundle.putLong("id", group.getId());

                Intent intent = new Intent(getApplicationContext(), GroupInformatonsActivity.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group, menu);
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
    public void goToGroupInformationsActivity(View view){
        Intent intent = new Intent(this, GroupInformatonsActivity.class);
        startActivity(intent);
    }
}
