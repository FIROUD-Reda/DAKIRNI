package com.example.dakirni.ui.reminders;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dakirni.AdapterReminder.Reminder;
import com.example.dakirni.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SetReminderActivity extends AppCompatActivity {

    private int hour, minute;
    private String title, text;
    private String image;
    private String voice;
    private boolean is_active, is_repeating;
    private boolean mon, tue, wed, thu, fri, sat, sun;
    private Boolean isRepeat ,is_sent;
    private List<Boolean> Reminder_days=new ArrayList<Boolean>();//= ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.26.44:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterfaceRemainder apiInterfaceRemainder = retrofit.create(ApiInterfaceRemainder.class);
        TimePicker timePicker_reminder_time = findViewById(R.id.timePicker_reminder_time);
        Button btn_cancel = findViewById(R.id.btn_cancel_reminder);
        Button btn_confirm = findViewById(R.id.btn_confirm_reminder);
        Button btn_add_image = findViewById(R.id.btn_add_reminder_image);
        Button btn_add_voice = findViewById(R.id.btn_add_reminder_voice);
        EditText input_title = findViewById(R.id.input_reminder_title);
        EditText input_text = findViewById(R.id.input_reminder_text);

        TextView tv_title_interface = findViewById(R.id.tv_set_reminder);
        TextView tv_image = findViewById(R.id.tv_reminder_image);
        TextView tv_voice = findViewById(R.id.tv_reminder_voice);
        CheckBox check_mon = findViewById(R.id.checkBox_Mon);
        CheckBox check_tue = findViewById(R.id.checkBox_Tue);
        CheckBox check_wed = findViewById(R.id.checkBox_Wed);
        CheckBox check_thu = findViewById(R.id.checkBox_Thu);
        CheckBox check_fri = findViewById(R.id.checkBox_Fri);
        CheckBox check_sat = findViewById(R.id.checkBox_Sat);
        CheckBox check_sun = findViewById(R.id.checkBox_Sun);
        CheckBox check_repeat = findViewById(R.id.checkBox_repeat);
       //String Reminder_days[] = {"false","false","false","false","false","true"};
         isRepeat = false ;
        String reminder_id;
        boolean is_new;
   /*        if(check_mon.isChecked()){


      if (check_mon.isChecked()){
          Reminder_days[0]="true";
      }else {
          Reminder_days[0] = "false";
      }
        if (check_tue.isChecked()){
            Reminder_days[1]="true";
        }else{
            Reminder_days[1] = "false";
        }

        if (check_wed.isChecked()){
            Reminder_days[2]="true";
    }else{
        Reminder_days[2] = "false";
    }
   if (check_thu.isChecked())
            Reminder_days.add("true");
        else
            Reminder_days.add("false");
        if (check_fri.isChecked())
            Reminder_days.add("true");
        else
            Reminder_days.add("false");
        if (check_sat.isChecked())
            Reminder_days.add("true");
        else
            Reminder_days.add("false");
        if (check_sun.isChecked())
            Reminder_days.add("true");
        else
            Reminder_days.add("false");*/

        if (check_repeat.isChecked()) {

        }
        check_repeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isRepeat = isChecked;
            }
        });
       check_mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Reminder_days.add(isChecked);
              }
         });
        check_tue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Reminder_days.add(isChecked);

            }
        });
        check_wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Reminder_days.add(isChecked);            }
        });
        check_thu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Reminder_days.add(isChecked);            }
        });
        check_fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Reminder_days.add(isChecked);            }
        });
         check_sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Reminder_days.add(isChecked);            }
        });
        check_sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Reminder_days.add(isChecked);            }
        });
   /*  check_tue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Reminder_days[1]=isChecked;

            }
        });
        check_wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Reminder_days[2]=isChecked;

            }
        });
        check_thu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Reminder_days[3]=isChecked;

            }
        });
        check_fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Reminder_days[4]=isChecked;

            }
        });
        check_sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Reminder_days[5]=isChecked;

                }
            });
        check_sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Reminder_days[6]=isChecked;

            }
        });

     */

            timePicker_reminder_time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute1) {
                             hour=hourOfDay;
                             minute=minute1;
                }
            });











            // To fill informations in case of update
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            is_new = extras.getBoolean("IS_NEW");
            if (!is_new) {
                // Get the information about reminder with the id from data base
                reminder_id = extras.getString("REMINDER_ID");
                // fill informations
            }
        }

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetReminderActivity.this, RemindersFragment.class);
                startActivity(intent);
            }
        });

        // Click on confirm button, allow to add a new reminder or to update a specific reminder
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
               RemainderForRetrofit remainderForRetrofit= new RemainderForRetrofit(input_text.getText().toString(),input_title.getText().toString(), isRepeat,is_sent ,Reminder_days,hour ,minute);





//                RemainderForRetrofit remainderForRetrofit= new RemainderForRetrofit("nabil","nabil");
                Call<Void> call =apiInterfaceRemainder.addReminder(remainderForRetrofit);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getApplicationContext(),"succes", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


//                Toast.makeText(getApplicationContext(),"nabil",Toast.LENGTH_LONG).show();













                // Get time (hour and minute) from the Time Picker
                hour = timePicker_reminder_time.getHour();
                minute = timePicker_reminder_time.getMinute();

                // set the title of reminder, if no title has been chosen, it is set to Reminder by default
                if (!input_title.getText().toString().equals("")) {
                    title = input_title.getText().toString();
                } else {
                    title = "Reminder";
                }

                // set the text, image and voice, for each one, if no one has been chosen, the default value is null
                if (!input_text.getText().toString().equals("")) {
                    text = tv_image.getText().toString();
                } else {
                    text = null;
                }
                if (!tv_image.getText().toString().equals("")) {
                    image = tv_image.getText().toString();
                } else {
                    image = null;
                }
                if (!tv_voice.getText().toString().equals("")) {
                    voice = tv_voice.getText().toString();
                } else {
                    voice = null;
                }

                // Repeat reminder every week set to true or false depending on the check_repeat CheckBox
             /*  is_repeating = check_repeat.isChecked();

                // When confirming, the reminder is activated
                is_active = true;

                // The boolean variables corresponding to the days, are set to true or false depending to the CheckBoxes
                mon = check_mon.isChecked();
                tue = check_tue.isChecked();
                wed = check_wed.isChecked();
                thu = check_thu.isChecked();
                fri = check_fri.isChecked();
                sat = check_sat.isChecked();
                sun = check_sun.isChecked();
*/
                // A new reminder created
                Reminder reminder = new Reminder(hour, minute, is_active, is_repeating, mon, tue, wed, thu, fri, sat, sun, title, text, image, voice);
            }
        });

    }
}