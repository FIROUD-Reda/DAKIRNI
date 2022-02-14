package com.example.dakirni.AdapterReminder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.R;
import com.example.dakirni.ui.reminders.SetReminderActivity;

import java.util.List;

// create a custom adapter
public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private List<Reminder> reminders;
    private Context context;

    // constructor with a list of reminders and a context
    public ReminderAdapter(List<Reminder> reminders, Context context) {
        this.reminders = reminders;
        this.context = context;
    }

    // create the view
    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_reminder, parent, false);
        return new ReminderViewHolder(view);
    }

    // on bind, set the data to be shown using the setData function
    @Override
    public void onBindViewHolder(ReminderViewHolder holder, int position) {

        String repeatingDays = reminders.get(position).getRepeatingDays();
        String title = reminders.get(position).getTitle();
      // String text = reminders.get(position).getText();
//        String image = reminders.get(position).getImage();
//        String voice = reminders.get(position).getVoice();
        int hour = reminders.get(position).getHour();
        int minute = reminders.get(position).getMinute();
        boolean is_checked = reminders.get(position).isIs_repeating();
        boolean is_active = reminders.get(position).isIs_active();
        String time = hour + ":" + minute;
        holder.setData(time, title, repeatingDays, is_checked, is_active);

    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }


    public class ReminderViewHolder extends RecyclerView.ViewHolder {
        private TextView reminderTime;
        private CheckBox reminderRepeat;
        private ImageView imageView_repeat;
        private TextView reminderRepeatingDays;
        private TextView reminderTitle;
        private TextView reminderText;
        private TextView reminderImage;
        private TextView reminderVoice;
        private Switch reminderStarted;

        public ReminderViewHolder(View view) {
            super(view);
            reminderTime = view.findViewById(R.id.tv_reminder_time);
            reminderStarted = view.findViewById(R.id.switch_reminder);
            reminderRepeat = view.findViewById(R.id.checkBox_repeat);
            reminderRepeatingDays = view.findViewById(R.id.tv_reminder_days);
            reminderTitle = view.findViewById(R.id.tv_reminder_title);
            imageView_repeat = view.findViewById(R.id.imageView_repeat);

            // set a click event, when we click on the reminder
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Reminder reminder = reminders.get(position);
                    Intent intent = new Intent(context, SetReminderActivity.class);
                    intent.putExtra("Hour", reminder.getHour());
                    intent.putExtra("Minute", reminder.getMinute());
                    intent.putExtra("Title", reminder.getTitle());
                    intent.putExtra("Text", reminder.getText());
                    intent.putExtra("voice", reminder.getVoice());
                    intent.putExtra("repeat", reminder.isIs_repeating());
                    intent.putExtra("Image", reminder.getImage());
                    intent.putExtra("Mon", reminder.isMon());
                    intent.putExtra("Wed", reminder.isWed());
                    intent.putExtra("Sun", reminder.isSun());
                    intent.putExtra("Fri", reminder.isFri());
                    intent.putExtra("Sat", reminder.isSat());
                    intent.putExtra("Thu", reminder.isThu());
                    intent.putExtra("Tue", reminder.isTue());
                    intent.putExtra("REMINDER_ID", reminder.getReminder_id());
                    context.startActivity(intent);
                }
            });

            // set a checked change event, when we click on the switch to activate or deactivate the reminder
            reminderStarted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = getAdapterPosition();
                    Reminder reminder = reminders.get(position);
                    reminder.setis_active(isChecked);
                }
            });
        }

        // set the data to show in the view, data for each reminder
        public void setData(String time,String title, String days, boolean is_repeating, boolean is_active) {
            reminderTime.setText(time);
            reminderTitle.setText(title);
            reminderRepeatingDays.setText(days);
            reminderStarted.setChecked(is_active);
            if (is_repeating) {
                imageView_repeat.setVisibility(View.VISIBLE);
            }
            else {
                imageView_repeat.setVisibility(View.GONE);
            }
        }

    }
}