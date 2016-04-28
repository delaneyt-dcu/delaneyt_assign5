package com.example.delaneyt.trafficcountapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

/**
 * Class which counts traffic at a 4-arm junction and adds this data to a database
 *
 * <p> This class allows the user to count traffic at a 4-arm junction and add this data to a
 * SQLiteDatabase by calling a MySQLiteHelperClass
 *
 *  <p><b>References: </b>The origins of some of the code used in this class is accredited to
 *  ProgrammingKnowledge. Ref: Android SQLite Database Tutorial </p>
 *
 *  @author Tim Delaney
 *  @version 1.0
 *  @since 2016-04-20
 *  @see "Android SQLite Database Tutorial" by ProgrammingKnowledge at:
 *  @see <a href="https://www.youtube.com/watch?v=cp2rL3sAFmI"</a>
 */
public class AddCountTableFourArmActivity extends AppCompatActivity {

    /**
     * Debug Tag for use logging debug output to LogCat
     */
    private final String TAG = "AddCountTableFourArmActivity";

    // Variables declared and initialised
    MySQLiteHelperCountTable myDb;
    SharedPreferences mLocationPref;
    Button m_BtnAddToDb, m_BtnViewAllData, m_BtnResetCountData, btnStart, btnStop;
    ImageButton m_BtnAtoB, m_BtnAtoC,  m_BtnAtoD, m_BtnBtoA, m_BtnBtoC, m_BtnBtoD,
            m_BtnCtoA, m_BtnCtoB, m_BtnCtoD, m_BtnDtoA, m_BtnDtoB, m_BtnDtoC;
    TextView m_TextViewAtoBVehs, m_TextViewAtoCVehs, m_TextViewAtoDVehs,
            m_TextViewBtoAVehs, m_TextViewBtoCVehs, m_TextViewBtoDVehs,
            m_TextViewCtoAVehs, m_TextViewCtoBVehs,  m_TextViewCtoDVehs,
            m_TextViewDtoAVehs, m_TextViewDtoBVehs,  m_TextViewDtoCVehs,
            textViewTime;
    int m_NoOfAtoBVehs =0, m_NoOfAtoBLGVs =0, m_NoOfAtoBHGVs =0,
            m_NoOfAtoCVehs =0, m_NoOfAtoCLGVs =0, m_NoOfAtoCHGVs =0,
            m_NoOfAtoDVehs =0, m_NoOfAtoDLGVs =0, m_NoOfAtoDHGVs =0,
            m_NoOfBtoAVehs =0, m_NoOfBtoALGVs =0, m_NoOfBtoAHGVs =0,
            m_NoOfBtoCVehs =0, m_NoOfBtoCLGVs =0, m_NoOfBtoCHGVs =0,
            m_NoOfBtoDVehs =0, m_NoOfBtoDLGVs =0, m_NoOfBtoDHGVs =0,
            m_NoOfCtoAVehs =0, m_NoOfCtoALGVs =0, m_NoOfCtoAHGVs =0,
            m_NoOfCtoBVehs =0, m_NoOfCtoBLGVs =0, m_NoOfCtoBHGVs =0,
            m_NoOfCtoDVehs =0, m_NoOfCtoDLGVs =0, m_NoOfCtoDHGVs =0,
            m_NoOfDtoAVehs =0, m_NoOfDtoALGVs =0, m_NoOfDtoAHGVs =0,
            m_NoOfDtoBVehs =0, m_NoOfDtoBLGVs =0, m_NoOfDtoBHGVs =0,
            m_NoOfDtoCVehs =0, m_NoOfDtoCLGVs =0, m_NoOfDtoCHGVs =0;
    String mArmAName, mArmBName,mArmCName,mArmDName;

    /**
     * Method which sets the textViews for the various arm names
     * @param mArmAName is a String variable for Arm A
     * @param mArmBName is a String variable for Arm B
     * @param mArmCName is a String variable for Arm B
     * @param mArmDName is a String variable for Arm D
     * No return
     */
    public void updateTextView(String mArmAName, String mArmBName, String mArmCName, String mArmDName) {
        TextView textViewArmA = (TextView) findViewById(R.id.textViewArmAName);
        TextView textViewArmB = (TextView) findViewById(R.id.textViewArmBName);
        TextView textViewArmC = (TextView) findViewById(R.id.textViewArmCName);
        TextView textViewArmD = (TextView) findViewById(R.id.textViewArmDName);
        textViewArmA.setText(mArmAName);
        textViewArmB.setText(mArmBName);
        textViewArmC.setText(mArmCName);
        textViewArmD.setText(mArmDName);
    }

    /**
     * Called when the activity is first created.
     * Saves the state of the application in a bundle based on the value of the savedInstance State
     * and carries out button intent actions.
     *
     * @param savedInstanceState can be passed back to onCreate if the activity needs to be created
     *                           (e.g., orientation change) so that you don't lose this prior
     *                           information. If no data was supplied, savedInstanceState is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_arm_jnt);
        myDb = new MySQLiteHelperCountTable(this);
        final CounterClass timer = new CounterClass(3600000,1000);
        m_BtnAtoB = (ImageButton) findViewById(R.id.imageButton_AtoB);
        m_BtnAtoC = (ImageButton) findViewById(R.id.imageButton_AtoC);
        m_BtnAtoD = (ImageButton) findViewById(R.id.imageButton_AtoD);
        m_BtnBtoA = (ImageButton) findViewById(R.id.imageButton_BtoA);
        m_BtnBtoC = (ImageButton) findViewById(R.id.imageButton_BtoC);
        m_BtnBtoD = (ImageButton) findViewById(R.id.imageButton_BtoD);
        m_BtnCtoA = (ImageButton) findViewById(R.id.imageButton_CtoA);
        m_BtnCtoB = (ImageButton) findViewById(R.id.imageButton_CtoB);
        m_BtnCtoD = (ImageButton) findViewById(R.id.imageButton_CtoD);
        m_BtnDtoA = (ImageButton) findViewById(R.id.imageButton_DtoA);
        m_BtnDtoB = (ImageButton) findViewById(R.id.imageButton_DtoB);
        m_BtnDtoC = (ImageButton) findViewById(R.id.imageButton_DtoC);
        m_BtnAddToDb = (Button) findViewById(R.id.button_AddToDb);
        m_BtnViewAllData = (Button) findViewById(R.id.button_ViewAllData);
        m_BtnResetCountData = (Button) findViewById(R.id.button_ResetCountData);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        mLocationPref = PreferenceManager.getDefaultSharedPreferences(AddCountTableFourArmActivity.this);
        mArmAName = mLocationPref.getString("arm_a_preference", "Arm A");
        mArmBName = mLocationPref.getString("arm_b_preference", "Arm B");
        mArmCName = mLocationPref.getString("arm_c_preference", "Arm C");
        mArmDName = mLocationPref.getString("arm_d_preference", "Arm D");
        m_TextViewAtoBVehs = (TextView) findViewById(R.id.textViewVehsAtoB);
        m_TextViewAtoCVehs = (TextView) findViewById(R.id.textViewVehsAtoC);
        m_TextViewAtoDVehs = (TextView) findViewById(R.id.textViewVehsAtoD);
        m_TextViewBtoAVehs = (TextView) findViewById(R.id.textViewVehsBtoA);
        m_TextViewBtoCVehs = (TextView) findViewById(R.id.textViewVehsBtoC);
        m_TextViewBtoDVehs = (TextView) findViewById(R.id.textViewVehsBtoD);
        m_TextViewCtoAVehs = (TextView) findViewById(R.id.textViewVehsCtoA);
        m_TextViewCtoBVehs = (TextView) findViewById(R.id.textViewVehsCtoB);
        m_TextViewCtoDVehs = (TextView) findViewById(R.id.textViewVehsCtoD);
        m_TextViewDtoAVehs = (TextView) findViewById(R.id.textViewVehsDtoA);
        m_TextViewDtoBVehs = (TextView) findViewById(R.id.textViewVehsDtoB);
        m_TextViewDtoCVehs = (TextView) findViewById(R.id.textViewVehsDtoC);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewTime.setText("00:60:00");

        // Call methods onCreate
        updateTextView(mArmAName, mArmBName, mArmCName, mArmDName);
        addData();
        viewData();
        resetCountData();

        /**
         * Method to start timer of countdown clock after button click.
         * No return.
         */
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });

        /**
         * Method to stop timer of countdown clock after button click.
         * No return.
         */
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
            }
        });

        // Arm A
        /**
         * Method to increment no. of vehs (ie totals) and LGVs for Arm A to Arm C movement
         * by 1 after button click.
         * No return
         */
        m_BtnAtoC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfAtoCVehs++;
                m_NoOfAtoCLGVs++;
                m_TextViewAtoCVehs.setText(Integer.toString(m_NoOfAtoCVehs));
            }
        });

        // Tag marker for this activity
        Log.i(TAG, "The total vehicles has been updated following Left button short pressed.");

        /**
         * Method to increment no. of vehs (ie totals) and HGVs for Arm A to Arm C movement
         * by 1 after button longclick.
         * Return boolean value
         */
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
        m_BtnAtoD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfAtoDVehs++;
                m_NoOfAtoDLGVs++;
                m_TextViewAtoDVehs.setText(Integer.toString(m_NoOfAtoDVehs));
            }
        });
        m_BtnAtoD.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfAtoDVehs++;
                m_NoOfAtoDHGVs++;
                m_TextViewAtoDVehs.setText(Integer.toString(m_NoOfAtoDVehs));
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
        m_BtnBtoD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfBtoDVehs++;
                m_NoOfBtoDLGVs++;
                m_TextViewBtoDVehs.setText(Integer.toString(m_NoOfBtoDVehs));
            }
        });
        m_BtnBtoD.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfBtoDVehs++;
                m_NoOfBtoDHGVs++;
                m_TextViewBtoDVehs.setText(Integer.toString(m_NoOfBtoDVehs));
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
        m_BtnCtoD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfCtoDVehs++;
                m_NoOfCtoDLGVs++;
                m_TextViewCtoDVehs.setText(Integer.toString(m_NoOfCtoDVehs));
            }
        });
        m_BtnCtoD.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfCtoDVehs++;
                m_NoOfCtoDHGVs++;
                m_TextViewCtoDVehs.setText(Integer.toString(m_NoOfCtoDVehs));
                return true;
            }
        });

        // Arm D
        m_BtnDtoB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfDtoBVehs++;
                m_NoOfDtoBLGVs++;
                m_TextViewDtoBVehs.setText(Integer.toString(m_NoOfDtoBVehs));
            }
        });
        m_BtnDtoB.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfDtoBVehs++;
                m_NoOfDtoBHGVs++;
                m_TextViewDtoBVehs.setText(Integer.toString(m_NoOfDtoBVehs));
                return true;
            }
        });
        m_BtnDtoA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfDtoAVehs++;
                m_NoOfDtoALGVs++;
                m_TextViewDtoAVehs.setText(Integer.toString(m_NoOfDtoAVehs));
            }
        });
        m_BtnDtoA.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfDtoAVehs++;
                m_NoOfDtoAHGVs++;
                m_TextViewDtoAVehs.setText(Integer.toString(m_NoOfDtoAVehs));
                return true;
            }
        });
        m_BtnDtoC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_NoOfDtoCVehs++;
                m_NoOfDtoCLGVs++;
                m_TextViewDtoCVehs.setText(Integer.toString(m_NoOfDtoCVehs));
            }
        });
        m_BtnDtoC.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                m_NoOfDtoCVehs++;
                m_NoOfDtoCHGVs++;
                m_TextViewDtoCVehs.setText(Integer.toString(m_NoOfDtoCVehs));
                return true;
            }
        });

        /**
         * Method to call reset count variables to zero on button click
         * No return
         */
        m_BtnResetCountData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetCountData();

                // Tag marker for this activity
                Log.i(TAG, "The count data was been resetCountData.");
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")

    /**
     * Class which extends the CountDownTimer and sets the parameters for this timer
     */
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval){
            super(millisInFuture, countDownInterval);
        }
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @SuppressLint("NewApi")

        /**
         * Method which overrides the onTick method of the CountDownTimer Class
         * and sets the units for this timer's textViewTime to a String variable hms
         * No return
         */
        @Override
        public void onTick(long millisUntilFinished){
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            System.out.println(hms);
            textViewTime.setText(hms);
        }

        /**
         * Method to set the timers textViewTime value to the string "Completed" when finished
         * No return
         */
        @Override
        public void onFinish(){
            textViewTime.setText("Completed.");
        }
    }

    /**
     * Method to add the member variable values to the Database
     */
    public void addData() {
        m_BtnAddToDb.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                boolean isInserted = myDb.insertData(
                                                        Integer.toString(m_NoOfAtoBLGVs),
                                                        Integer.toString(m_NoOfAtoBHGVs),
                                                        Integer.toString(m_NoOfAtoCLGVs),
                                                        Integer.toString(m_NoOfAtoCHGVs),
                                                        Integer.toString(m_NoOfAtoDLGVs),
                                                        Integer.toString(m_NoOfAtoDHGVs),
                                                        Integer.toString(m_NoOfBtoALGVs),
                                                        Integer.toString(m_NoOfBtoAHGVs),
                                                        Integer.toString(m_NoOfBtoCLGVs),
                                                        Integer.toString(m_NoOfBtoCHGVs),
                                                        Integer.toString(m_NoOfBtoDLGVs),
                                                        Integer.toString(m_NoOfBtoDHGVs),
                                                        Integer.toString(m_NoOfCtoALGVs),
                                                        Integer.toString(m_NoOfCtoAHGVs),
                                                        Integer.toString(m_NoOfCtoBLGVs),
                                                        Integer.toString(m_NoOfCtoBHGVs),
                                                        Integer.toString(m_NoOfCtoDLGVs),
                                                        Integer.toString(m_NoOfCtoDHGVs),
                                                        Integer.toString(m_NoOfDtoALGVs),
                                                        Integer.toString(m_NoOfDtoAHGVs),
                                                        Integer.toString(m_NoOfDtoBLGVs),
                                                        Integer.toString(m_NoOfDtoBHGVs),
                                                        Integer.toString(m_NoOfDtoCLGVs),
                                                        Integer.toString(m_NoOfDtoCHGVs));
                                                if (isInserted) {
                                                    Toast.makeText(AddCountTableFourArmActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                                }
                                                else {
                                                    Toast.makeText(AddCountTableFourArmActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }
        );}

    /**
     * Method to reset count variables to zero
     * No return
     */
    public void resetCountData() {
        m_NoOfAtoBVehs = m_NoOfAtoBLGVs = m_NoOfAtoBHGVs =
                m_NoOfAtoCVehs = m_NoOfAtoCLGVs = m_NoOfAtoCHGVs =
                        m_NoOfAtoDVehs = m_NoOfAtoDLGVs = m_NoOfAtoDHGVs =
                                m_NoOfBtoAVehs = m_NoOfBtoALGVs = m_NoOfBtoAHGVs =
                                        m_NoOfBtoCVehs = m_NoOfBtoCLGVs = m_NoOfBtoCHGVs =
                                                m_NoOfBtoDVehs = m_NoOfBtoDLGVs = m_NoOfBtoDHGVs =
                                                        m_NoOfCtoAVehs = m_NoOfCtoALGVs = m_NoOfCtoAHGVs =
                                                                m_NoOfCtoBVehs = m_NoOfCtoBLGVs = m_NoOfCtoBHGVs =
                                                                        m_NoOfCtoDVehs = m_NoOfCtoDLGVs = m_NoOfCtoDHGVs =
                                                                                m_NoOfDtoAVehs = m_NoOfDtoALGVs = m_NoOfDtoAHGVs =
                                                                                        m_NoOfDtoBVehs = m_NoOfDtoBLGVs = m_NoOfDtoBHGVs =
                                                                                                m_NoOfDtoCVehs = m_NoOfDtoCLGVs = m_NoOfDtoCHGVs =0;
        m_TextViewAtoBVehs.setText(Integer.toString(m_NoOfAtoBVehs));
        m_TextViewAtoCVehs.setText(Integer.toString(m_NoOfAtoCVehs));
        m_TextViewAtoDVehs.setText(Integer.toString(m_NoOfAtoDVehs));
        m_TextViewBtoAVehs.setText(Integer.toString(m_NoOfBtoAVehs));
        m_TextViewBtoCVehs.setText(Integer.toString(m_NoOfBtoCVehs));
        m_TextViewBtoDVehs.setText(Integer.toString(m_NoOfBtoDVehs));
        m_TextViewCtoAVehs.setText(Integer.toString(m_NoOfCtoAVehs));
        m_TextViewCtoBVehs.setText(Integer.toString(m_NoOfCtoBVehs));
        m_TextViewCtoDVehs.setText(Integer.toString(m_NoOfCtoDVehs));
        m_TextViewDtoAVehs.setText(Integer.toString(m_NoOfDtoAVehs));
        m_TextViewDtoBVehs.setText(Integer.toString(m_NoOfDtoBVehs));
        m_TextViewDtoCVehs.setText(Integer.toString(m_NoOfDtoCVehs));
        textViewTime.setText("00:60:00");
    }

    /**
     * Method to create a message containing all of the data added to database when button clicked
     * Message returned.
     */
    public void viewData() {
        m_BtnViewAllData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MySQLiteHelperCountTable myTrafficSurveysDb = new MySQLiteHelperCountTable(AddCountTableFourArmActivity.this);
                        Cursor res = myTrafficSurveysDb.getAddedData();
                        if (res.getCount() == 0) {

                            // show message
                            showMessage("Error", "No data found");
                            return;
                        }
                        StringBuilder buffer = new StringBuilder();
                        while (res.moveToNext()) {
                            buffer.append("Id: ").append(res.getString(0)).append("\nMovement Nos:\n");
                            buffer.append("AtoBLGV: ").append(res.getString(1)).append("\n");
                            buffer.append("AtoBHGV: ").append(res.getString(2)).append("\n");
                            buffer.append("AtoCLGV: ").append(res.getString(3)).append("\n");
                            buffer.append("AtoCHGV: ").append(res.getString(4)).append("\n");
                            buffer.append("AtoDLGV: ").append(res.getString(5)).append("\n");
                            buffer.append("AtoDHGV: ").append(res.getString(6)).append("\n");
                            buffer.append("BtoALGV: ").append(res.getString(7)).append("\n");
                            buffer.append("BtoAHGV: ").append(res.getString(8)).append("\n");
                            buffer.append("BtoCLGV: ").append(res.getString(9)).append("\n");
                            buffer.append("BtoCHGV: ").append(res.getString(10)).append("\n");
                            buffer.append("BtoDLGV: ").append(res.getString(11)).append("\n");
                            buffer.append("BtoDHGV: ").append(res.getString(12)).append("\n");
                            buffer.append("CtoALGV: ").append(res.getString(13)).append("\n");
                            buffer.append("CtoAHGV: ").append(res.getString(14)).append("\n");
                            buffer.append("CtoBLGV: ").append(res.getString(15)).append("\n");
                            buffer.append("CtoBHGV: ").append(res.getString(16)).append("\n");
                            buffer.append("CtoDLGV: ").append(res.getString(17)).append("\n");
                            buffer.append("CtoDHGV: ").append(res.getString(18)).append("\n");
                            buffer.append("DtoALGV: ").append(res.getString(19)).append("\n");
                            buffer.append("DtoAHGV: ").append(res.getString(20)).append("\n");
                            buffer.append("DtoBLGV: ").append(res.getString(21)).append("\n");
                            buffer.append("DtoBHGV: ").append(res.getString(22)).append("\n");
                            buffer.append("DtoCLGV: ").append(res.getString(23)).append("\n");
                            buffer.append("DtoCHGV: ").append(res.getString(24)).append("\n\n");
                        }

                        /**
                         * method which call the show message and passes the buffered data as a string
                         * return the string data
                         */
                        showMessage("Data", buffer.toString());
                    }
                });
    }

    /**
     * Method to call various builder methods used to show a message of the data added to the database
     * @param title is the String "Data"
     * @param Message is the String from a buffer
     * No return
     */
    public void showMessage (String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCountTableFourArmActivity.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    /**
     * Returns user to previous screen with up arrow set to act like a back arrow
     * @param item represent the menu item clicked, the up arrow in this case
     * @return boolean true if clicked to return user to previous screen
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}