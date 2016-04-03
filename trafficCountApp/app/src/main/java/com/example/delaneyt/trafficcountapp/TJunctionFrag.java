package com.example.delaneyt.trafficcountapp;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/*
 * This is one of two fragment activities used by the RugbyMatchActivity class to provide contained
 * functionality which allows the user to enter a teamNameA in a editText filed and chick on three
 * buttons each which increment teamScoreA textView field formatting parameters. It loads layout
 * resources from the fragment_team_a.xml file.
 * Created by delaneyt on 13/12/2015.
 */
public class TJunctionFrag extends Fragment implements Button.OnClickListener {

    /**
     * Debug Tag for use logging debug output to LogCat
     */
    private final String TAG = "TJunctionFrag";


    // Variables declared and some set
    MySQLiteHelper myTrafficSurveysDb;
    Button btnAddData;

    ImageButton m_Btn1, m_Btn2, m_Btn3, m_Btn4, m_Btn5, m_Btn6;
    TextView m_CountCRightText, m_CountCRightDownText, m_CountBLeftUpText,
            m_CountBRightUpText, m_CountALeftText, m_CountALeftDownText;
    EditText armCNameText;
    EditText m_ArmBEditText;
    int counter=0, m_NoOfALeftVehs =0, noOfConversions=0, noOfTries=0,
            m_NoOfALeftLGVs =0, m_NoOfALeftHGVs =0, m_NoOfALeftDownVehs =0,
            m_NoOfALeftDownLGVs =0, m_NoOfALeftDownHGVs =0,
            m_NoOfBRightUpVehs =0, m_NoOfBRightUpLGVs =0, m_NoOfBRightUpHGVs =0,
            m_NoOfBLeftUpVehs =0, noOfBLeftUpLGVs=0, m_NoOfBLeftUpHGVs =0,
            m_NoOfCRightDownVehs =0, m_NoOfCRightDownLGVs =0, m_NoOfCRightDownHGVs =0,
            m_NoOfCRightVehs =0, m_NoOfCRightLGVs =0, m_NoOfCRightHGVs =0;

    // To prevent NullPointerException error
    @Nullable

    /**
     * Public View method that inflates the layout with a value each time a button is pressed.
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_t_junction, container, false);
        m_Btn1 = (ImageButton) rootView.findViewById(R.id.leftImageButton);
        m_Btn2 = (ImageButton) rootView.findViewById(R.id.leftdownImageButton);
        m_Btn3 = (ImageButton) rootView.findViewById(R.id.rightupImageButton);
        m_Btn4 = (ImageButton) rootView.findViewById(R.id.leftupImageButton);
        m_Btn5 = (ImageButton) rootView.findViewById(R.id.rightdownImageButton);
        m_Btn6 = (ImageButton) rootView.findViewById(R.id.rightImageButton);
        btnAddData = (Button) rootView.findViewById(R.id.button_add);

        m_CountCRightText = (TextView) rootView.findViewById(R.id.countCRightTextView);
        m_CountCRightDownText = (TextView) rootView.findViewById(R.id.countCRightDownTextView);
        m_CountBLeftUpText = (TextView) rootView.findViewById(R.id.countBLeftUpTextView);
        m_CountBRightUpText = (TextView) rootView.findViewById(R.id.countBRightUpTextView);
        m_CountALeftText = (TextView) rootView.findViewById(R.id.countALeftTextView);
        m_CountALeftDownText = (TextView) rootView.findViewById(R.id.countALeftDownTextView);
        m_ArmBEditText = (EditText) rootView.findViewById(R.id.armBEditText);
        AddData();

        //armCNameText = (EditText) view.findViewById(R.id.armCEditText);

        // Arm A
        // When  short pressed, both LGVs and total no of vehicles are incremented by 1
        m_Btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfALeftVehs++;
                m_NoOfALeftLGVs++;
                m_CountALeftText.setText(Integer.toString(m_NoOfALeftVehs));
            }
        });
        // Tag marker for this activity
        Log.i(TAG, "The total vehicles has been updated following Left button short pressed.");

        // When  long pressed, both HGVs and total no of vehicles are incremented by 1
        m_Btn1.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfALeftVehs++;
                m_NoOfALeftHGVs++;
                m_CountALeftText.setText(Integer.toString(m_NoOfALeftVehs));
                return true;
            }
        });
        // Tag marker for this activity
        Log.i(TAG, "The total vehicles has been updated following Left button long pressed.");

        m_Btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfALeftDownVehs++;
                m_NoOfALeftDownLGVs++;
                m_CountALeftDownText.setText(Integer.toString(m_NoOfALeftDownVehs));
            }
        });
        m_Btn2.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfALeftDownVehs++;
                m_NoOfALeftDownHGVs++;
                m_CountALeftDownText.setText(Integer.toString(m_NoOfALeftDownVehs));
                return true;
            }
        });

        // Arm B
        m_Btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfBRightUpVehs++;
                m_NoOfBRightUpLGVs++;
                m_CountBRightUpText.setText(Integer.toString(m_NoOfBRightUpVehs));
            }
        });
        m_Btn3.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfBRightUpVehs++;
                m_NoOfBRightUpHGVs++;
                m_CountBRightUpText.setText(Integer.toString(m_NoOfBRightUpVehs));
                return true;
            }
        });
        m_Btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfBLeftUpVehs++;
                noOfBLeftUpLGVs++;
                m_CountBLeftUpText.setText(Integer.toString(m_NoOfBLeftUpVehs));
            }
        });
        m_Btn4.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfBLeftUpVehs++;
                m_NoOfBLeftUpHGVs++;
                m_CountBLeftUpText.setText(Integer.toString(m_NoOfBLeftUpVehs));
                return true;
            }
        });

        // Arm C
        m_Btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfCRightDownVehs++;
                m_NoOfCRightDownLGVs++;
                m_CountCRightDownText.setText(Integer.toString(m_NoOfCRightDownVehs));
            }
        });
        m_Btn5.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfCRightDownVehs++;
                m_NoOfCRightDownHGVs++;
                m_CountCRightDownText.setText(Integer.toString(m_NoOfCRightDownVehs));
                return true;
            }
        });
        m_Btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfCRightVehs++;
                m_NoOfCRightLGVs++;
                m_CountCRightText.setText(Integer.toString(m_NoOfCRightVehs));
            }
        });
        m_Btn6.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfCRightVehs++;
                m_NoOfCRightHGVs++;
                m_CountCRightText.setText(Integer.toString(m_NoOfCRightVehs));
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
        m_NoOfALeftVehs = 0;
        noOfConversions = 0;
        noOfTries = 0;
        m_CountCRightText.setText(Integer.toString(counter));

        // armCNameText set to null following a failed manual test, refer to Test Schedule
        armCNameText.setText(null);

        // Tag marker for this activity
        Log.i(TAG, "The text was been reset.");
    }

    public void AddData() {
        btnAddData.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = myTrafficSurveysDb.insertData(
                            "tr", "td", "tr", "td","tr", "td", "tr", "td", "tr", "td", "tr", "td" );
                    if(isInserted)
                        Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getActivity(), "Data not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        );
    }













    //Current value of counter used as teamScore in RugbyMatchActivity class
    public int getTeamAScore() {
        return counter;
    }

    //Current values used to pass as teamStats in RugbyMatchActivity class
    public String getTeamAStats() {
        return "PENALITIES- " + m_NoOfALeftVehs + ", CONVERSION- " + noOfConversions + ", TRIES- " + noOfTries;
    }


    // This method does not work, further research required!
    //public EditText getTeamAName() {
    //    return armCNameText;
    //}

    @Override
    public void onClick(View v) {
    }
}