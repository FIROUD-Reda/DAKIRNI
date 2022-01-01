package com.example.dakirni.ui.reminders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.AdapterReminder.ReminderAdapter;
import com.example.dakirni.R;
import com.example.dakirni.AdapterReminder.Reminder;
import com.example.dakirni.databinding.FragmentRemindersBinding;

import java.util.ArrayList;
import java.util.List;

public class RemindersFragment extends Fragment {

    private RemindersViewModel remindersViewModel;
    private FragmentRemindersBinding binding;

    private Button btn_add_reminder;
    private RecyclerView recyclerView_reminders;
    private LinearLayoutManager linearLayoutManager;
    private ReminderAdapter reminderAdapter;
    private List<Reminder> reminders;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        remindersViewModel =
                new ViewModelProvider(this).get(RemindersViewModel.class);

        binding = FragmentRemindersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btn_add_reminder = root.findViewById(R.id.btn_add_reminder);

        btn_add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetReminderActivity.class);
                startActivity(intent);
            }
        });

        initReminders();
        initRemindersRecyclerView(root);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void initRemindersRecyclerView(View root) {
        recyclerView_reminders = root.findViewById(R.id.recyclerView_reminders);
        linearLayoutManager = new LinearLayoutManager(root.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView_reminders.setLayoutManager(linearLayoutManager);
        reminderAdapter = new ReminderAdapter(reminders, root.getContext());
        recyclerView_reminders.setAdapter(reminderAdapter);
        reminderAdapter.notifyDataSetChanged();
    }

    private void initReminders() {
        reminders = new ArrayList<>();

        reminders.add(new Reminder( 10, 10, true,false,
                true,false,true,false,true,false,true,
                "Reminder1","text1","image1","voice1"));
        reminders.add(new Reminder( 23, 10, true,false,
                true,false,true,false,true,false,true,
                "Reminder2","text1","image1","voice1"));
        reminders.add(new Reminder( 17, 10, true,false,
                true,false,true,false,true,false,true,
                "Reminder3","text1","image1","voice1"));
        reminders.add(new Reminder( 13, 10, true,false,
                true,false,true,false,true,false,true,
                "Reminder4","text1","image1","voice1"));
        reminders.add(new Reminder( 12, 10, true,false,
                true,false,true,false,true,false,true,
                "Reminder5", "text1", "image1", "voice1"));
    }
}