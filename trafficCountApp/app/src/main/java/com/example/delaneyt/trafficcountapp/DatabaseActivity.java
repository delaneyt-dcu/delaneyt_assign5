package com.example.delaneyt.trafficcountapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by delaneyt on 06/04/2016.
 */
public class DatabaseActivity extends ActionBarActivity {
    MySQLiteHelper myD;
    EditText editName;
    Button btnAddData;
    Integer m_Mvt1LGV, m_Mvt1HGV, m_Mvt2LGV, m_Mvt2HGV,m_Mvt3LGV, m_Mvt3HGV, m_Mvt4LGV, m_Mvt4HGV,
    m_Mvt5LGV, m_Mvt5HGV, m_Mvt6LGV, m_Mvt6HGV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        myD = new MySQLiteHelper(this);
        btnAddData = (Button) findViewById(R.id.button_AddToDb);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                m_Mvt1LGV = null;
                m_Mvt1LGV = null;
                m_Mvt2LGV = null;
                m_Mvt2LGV = null;
                m_Mvt3LGV = null;
                m_Mvt3LGV = null;
                m_Mvt4LGV = null;
                m_Mvt4LGV = null;
                m_Mvt5LGV = null;
                m_Mvt5LGV = null;
                m_Mvt6LGV = null;
                m_Mvt6LGV = null;
            } else {
                m_Mvt1LGV = extras.getInt("mvt1LGV");
                m_Mvt1HGV = extras.getInt("mvt1HGV");
                m_Mvt2LGV = extras.getInt("mvt2LGV");
                m_Mvt2HGV = extras.getInt("mvt2HGV");
                m_Mvt3LGV = extras.getInt("mvt3LGV");
                m_Mvt3HGV = extras.getInt("mvt3HGV");
                m_Mvt4LGV = extras.getInt("mvt4LGV");
                m_Mvt4HGV = extras.getInt("mvt4HGV");
                m_Mvt5LGV = extras.getInt("mvt5LGV");
                m_Mvt5HGV = extras.getInt("mvt5HGV");
                m_Mvt6LGV = extras.getInt("mvt6LGV");
                m_Mvt6HGV = extras.getInt("mvt6HGV");
            }
        } else {
            m_Mvt1LGV = (Integer) savedInstanceState.getSerializable("mvt1LGV");
            m_Mvt1HGV = (Integer) savedInstanceState.getSerializable("mvt1HGV");
            m_Mvt2LGV = (Integer) savedInstanceState.getSerializable("mvt2LGV");
            m_Mvt2HGV = (Integer) savedInstanceState.getSerializable("mvt2HGV");
            m_Mvt3LGV = (Integer) savedInstanceState.getSerializable("mvt3LGV");
            m_Mvt3HGV = (Integer) savedInstanceState.getSerializable("mvt3HGV");
            m_Mvt4LGV = (Integer) savedInstanceState.getSerializable("mvt4LGV");
            m_Mvt4HGV = (Integer) savedInstanceState.getSerializable("mvt4HGV");
            m_Mvt5LGV = (Integer) savedInstanceState.getSerializable("mvt5LGV");
            m_Mvt5HGV = (Integer) savedInstanceState.getSerializable("mvt5HGV");
            m_Mvt6LGV = (Integer) savedInstanceState.getSerializable("mvt6LGV");
            m_Mvt6HGV = (Integer) savedInstanceState.getSerializable("mvt6HGV");
        };
        boolean isInserted = myD.insertData(
                Integer.toString(m_Mvt1LGV),
                Integer.toString(m_Mvt1HGV),
                Integer.toString(m_Mvt2LGV),
                Integer.toString(m_Mvt2HGV),
                Integer.toString(m_Mvt3LGV),
                Integer.toString(m_Mvt3HGV),
                Integer.toString(m_Mvt4LGV),
                Integer.toString(m_Mvt4HGV),
                Integer.toString(m_Mvt5LGV),
                Integer.toString(m_Mvt5HGV),
                Integer.toString(m_Mvt6LGV),
                Integer.toString(m_Mvt6HGV));
        if (isInserted == true)
            Toast.makeText(DatabaseActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(DatabaseActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
