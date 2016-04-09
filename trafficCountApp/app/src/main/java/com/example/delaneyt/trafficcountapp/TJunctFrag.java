package com.example.delaneyt.trafficcountapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/*
 * This is one of two fragment activities used by the RugbyMatchActivity class to provide contained
 * functionality which allows the user to enter a teamNameA in a editText filed and chick on three
 * buttons each which increment teamScoreA textView field formatting parameters. It loads layout
 * resources from the fragment_team_a.xml file.
 * Created by delaneyt on 13/12/2015.
 */
public class TJunctFrag extends Fragment implements Button.OnClickListener {

    /**
     * Debug Tag for use logging debug output to LogCat
     */
    private final String TAG = "TJunctFrag";


    // Variables declared and some set
//    MySQLiteHelper myTrafficSurveysDb;
//    MySQLiteHelper myTrafficSurveysDb = new MySQLiteHelper(getActivity());
    Button m_BtnAddToDb, m_BtnViewAllData;

    ImageButton m_BtnAtoC, m_BtnAtoB, m_BtnBtoA, m_BtnBtoC, m_BtnCtoB, m_BtnCtoA;
    TextView m_TextViewCtoAVehs, m_TextViewCtoBVehs, m_TextViewBtoCVehs,
            m_TextViewBtoAVehs, m_TextViewAtoCVehs, m_TextViewAtoBVehs;
    EditText armCNameText, m_EditTextArmAName, m_EditTextArmBName, m_EditTextArmCName;
    EditText m_ArmBEditText;
    int counter=0, m_NoOfAtoCVehs =0, m_NoOfAtoCLGVs =0, m_NoOfAtoCHGVs =0, m_NoOfAtoBVehs =0, m_NoOfAtoBLGVs =0, m_NoOfAtoBHGVs =0,
            m_NoOfBtoAVehs =0, m_NoOfBtoALGVs =0, m_NoOfBtoAHGVs =0, m_NoOfBtoCVehs =0, m_NoOfBtoCLGVs =0, m_NoOfBtoCHGVs =0,
            m_NoOfCtoBVehs =0, m_NoOfCtoBLGVs =0, m_NoOfCtoBHGVs =0, m_NoOfCtoAVehs =0, m_NoOfCtoALGVs =0, m_NoOfCtoAHGVs =0;

    // To prevent NullPointerException error
    @Nullable

    /**
     * Public View method that inflates the layout with a value each time a button is pressed.
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.frag_t_junct, container, false);
        m_BtnAtoC = (ImageButton) rootView.findViewById(R.id.imageButton_AtoC);
        m_BtnAtoB = (ImageButton) rootView.findViewById(R.id.imageButton_AtoB);
        m_BtnBtoA = (ImageButton) rootView.findViewById(R.id.imageButton_BtoA);
        m_BtnBtoC = (ImageButton) rootView.findViewById(R.id.imageButton_BtoC);
        m_BtnCtoB = (ImageButton) rootView.findViewById(R.id.imageButton_CtoB);
        m_BtnCtoA = (ImageButton) rootView.findViewById(R.id.imageButtonCtoA);

        m_BtnAddToDb = (Button) rootView.findViewById(R.id.button_AddToDb);
        m_BtnViewAllData = (Button) rootView.findViewById(R.id.button_ViewAllData);

        m_EditTextArmAName = (EditText) rootView.findViewById(R.id.editTextArmAName);
        m_EditTextArmBName = (EditText) rootView.findViewById(R.id.editTextArmBName);
        m_EditTextArmCName = (EditText) rootView.findViewById(R.id.editTextArmCName);

        m_TextViewCtoAVehs = (TextView) rootView.findViewById(R.id.textViewVehsCtoA);
        m_TextViewCtoBVehs = (TextView) rootView.findViewById(R.id.textViewVehsCtoB);
        m_TextViewBtoCVehs = (TextView) rootView.findViewById(R.id.textViewVehsBtoC);
        m_TextViewBtoAVehs = (TextView) rootView.findViewById(R.id.textViewVehsBtoA);
        m_TextViewAtoCVehs = (TextView) rootView.findViewById(R.id.textViewVehsAtoC);
        m_TextViewAtoBVehs = (TextView) rootView.findViewById(R.id.textViewVehsAtoB);

        addData();
        viewAll();

        //armCNameText = (EditText) view.findViewById(R.id.armCEditText);

        // Arm A
        // When  short pressed, both LGVs and total no of vehicles are incremented by 1
        m_BtnAtoC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfAtoCVehs++;
                m_NoOfAtoCLGVs++;
                m_TextViewAtoCVehs.setText(Integer.toString(m_NoOfAtoCVehs));
            }
        });
        // Tag marker for this activity
        Log.i(TAG, "The total vehicles has been updated following Left button short pressed.");

        // When  long pressed, both HGVs and total no of vehicles are incremented by 1
        m_BtnAtoC.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfAtoCVehs++;
                m_NoOfAtoCHGVs++;
                m_TextViewAtoCVehs.setText(Integer.toString(m_NoOfAtoCVehs));
                return true;
            }
        });
        // Tag marker for this activity
        Log.i(TAG, "The total vehicles has been updated following Left button long pressed.");

        m_BtnAtoB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfAtoBVehs++;
                m_NoOfAtoBLGVs++;
                m_TextViewAtoBVehs.setText(Integer.toString(m_NoOfAtoBVehs));
            }
        });
        m_BtnAtoB.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfAtoBVehs++;
                m_NoOfAtoBHGVs++;
                m_TextViewAtoBVehs.setText(Integer.toString(m_NoOfAtoBVehs));
                return true;
            }
        });

        // Arm B
        m_BtnBtoA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfBtoAVehs++;
                m_NoOfBtoALGVs++;
                m_TextViewBtoAVehs.setText(Integer.toString(m_NoOfBtoAVehs));
            }
        });
        m_BtnBtoA.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfBtoAVehs++;
                m_NoOfBtoAHGVs++;
                m_TextViewBtoAVehs.setText(Integer.toString(m_NoOfBtoAVehs));
                return true;
            }
        });
        m_BtnBtoC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfBtoCVehs++;
                m_NoOfBtoCLGVs++;
                m_TextViewBtoCVehs.setText(Integer.toString(m_NoOfBtoCVehs));
            }
        });
        m_BtnBtoC.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfBtoCVehs++;
                m_NoOfBtoCHGVs++;
                m_TextViewBtoCVehs.setText(Integer.toString(m_NoOfBtoCVehs));
                return true;
            }
        });

        // Arm C
        m_BtnCtoB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfCtoBVehs++;
                m_NoOfCtoBLGVs++;
                m_TextViewCtoBVehs.setText(Integer.toString(m_NoOfCtoBVehs));
            }
        });
        m_BtnCtoB.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfCtoBVehs++;
                m_NoOfCtoBHGVs++;
                m_TextViewCtoBVehs.setText(Integer.toString(m_NoOfCtoBVehs));
                return true;
            }
        });
        m_BtnCtoA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfCtoAVehs++;
                m_NoOfCtoALGVs++;
                m_TextViewCtoAVehs.setText(Integer.toString(m_NoOfCtoAVehs));
            }
        });
        m_BtnCtoA.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfCtoAVehs++;
                m_NoOfCtoAHGVs++;
                m_TextViewCtoAVehs.setText(Integer.toString(m_NoOfCtoAVehs));
                return true;
            }
        });
        // view to be returned each time a button is pressed
        return rootView;
    }

    // Method called upon Reset in RugbyMatchActivity resetButton pressed
    public void resetText() {

        //TextView bound in fragment onCreate as member variable so that
        // score buttons above start from reset default (zero again)
        counter = 0;
        m_NoOfAtoCVehs = 0;
//        noOfConversions = 0;
//        noOfTries = 0;
        m_TextViewCtoAVehs.setText(Integer.toString(counter));

        // armCNameText set to null following a failed manual test, refer to Test Schedule
        armCNameText.setText(null);

        // Tag marker for this activity
        Log.i(TAG, "The text was been reset.");
    }

    public void addData() {
        m_BtnAddToDb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), DatabaseActivity.class);
                        i.putExtra("mvt1LGV", m_NoOfAtoCLGVs);
                        i.putExtra("mvt1HGV", m_NoOfAtoCHGVs);
                        i.putExtra("mvt2LGV", m_NoOfAtoBLGVs);
                        i.putExtra("mvt2HGV", m_NoOfAtoBHGVs);
                        i.putExtra("mvt3LGV", m_NoOfBtoALGVs);
                        i.putExtra("mvt3HGV", m_NoOfBtoAHGVs);
                        i.putExtra("mvt4LGV", m_NoOfBtoCLGVs);
                        i.putExtra("mvt4HGV", m_NoOfBtoCHGVs);
                        i.putExtra("mvt5LGV", m_NoOfCtoBLGVs);
                        i.putExtra("mvt5HGV", m_NoOfCtoBHGVs);
                        i.putExtra("mvt6LGV", m_NoOfCtoALGVs);
                        i.putExtra("mvt6HGV", m_NoOfCtoAHGVs);
                        startActivity(i);
                    }
                }
        );
    }



                        /**

                        MySQLiteHelper myTrafficSurveysDb = new MySQLiteHelper(getActivity());
                        boolean isInserted = myTrafficSurveysDb.insertData(
                                Integer.toString(m_NoOfAtoCLGVs),
                                Integer.toString(m_NoOfAtoCHGVs),
                                Integer.toString(m_NoOfAtoBLGVs),
                                Integer.toString(m_NoOfAtoBHGVs),
                                Integer.toString(m_NoOfBtoALGVs),
                                Integer.toString(m_NoOfBtoAHGVs),
                                Integer.toString(m_NoOfBtoCLGVs),
                                Integer.toString(m_NoOfBtoCHGVs),
                                Integer.toString(m_NoOfCtoBLGVs),
                                Integer.toString(m_NoOfCtoBHGVs),
                                Integer.toString(m_NoOfCtoALGVs),
                                Integer.toString(m_NoOfCtoAHGVs));
                        if (isInserted == true)
                            Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getActivity(), "Data not Inserted", Toast.LENGTH_LONG).show();

                        */



    public void viewAll() {
        m_BtnViewAllData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MySQLiteHelper myTrafficSurveysDb = new MySQLiteHelper(getActivity());
                        Cursor res = myTrafficSurveysDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "No data found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id: " + res.getString(0) + "\nMovement Nos:\n");
                            buffer.append("1LGV: " + res.getString(1) + "\n");
                            buffer.append("1HGV: " + res.getString(2) + "\n");
                            buffer.append("2LGV: " + res.getString(3) + "\n");
                            buffer.append("2HGV: " + res.getString(4) + "\n");
                            buffer.append("3LGV: " + res.getString(5) + "\n");
                            buffer.append("3HGV: " + res.getString(6) + "\n");
                            buffer.append("4LGV: " + res.getString(7) + "\n");
                            buffer.append("4HGV: " + res.getString(8) + "\n");
                            buffer.append("5LGV: " + res.getString(9) + "\n");
                            buffer.append("5HGV: " + res.getString(10) + "\n");
                            buffer.append("6LGV: " + res.getString(11) + "\n");
                            buffer.append("6HGV: " + res.getString(12) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                        return;
                    }
                });
    }

    public void showMessage (String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


 /**
public void addData() {
    m_BtnAddToDb.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MySQLiteHelper myTrafficSurveysDb = new MySQLiteHelper(getActivity());
                    boolean isInserted = myTrafficSurveysDb.insertData(m_EditTextArmAName.getText().toString(),
                            m_EditTextArmBName.getText().toString(),
                            m_EditTextArmCName.getText().toString());
                    if (isInserted == true)
                        Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getActivity(), "Data not Inserted", Toast.LENGTH_LONG).show();
                }
            }
    );
}











    */







    //Current value of counter used as teamScore in RugbyMatchActivity class
    public int getTeamAScore() {
        return counter;
    }

    //Current values used to pass as teamStats in RugbyMatchActivity class
    public String getTeamAStats() {
        return "PENALITIES- " + m_NoOfAtoCVehs + ", CONVERSION- " + ", TRIES- ";
    }

    // This method does not work, further research required!
    //public EditText getTeamAName() {
    //    return armCNameText;
    //}

    @Override
    public void onClick(View v) {
    }
}