package com.example.dakirni.ui.reminders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.AdapterReminder.Reminder;
import com.example.dakirni.AdapterReminder.ReminderAdapter;
import com.example.dakirni.R;
import com.example.dakirni.databinding.FragmentRemindersBinding;
import com.example.dakirni.env;
import com.example.dakirni.environements.environementVariablesOfDakirni;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemindersFragment extends Fragment {

    private RemindersViewModel remindersViewModel;
    private FragmentRemindersBinding binding;

    private Button btn_add_reminder;
    private RecyclerView recyclerView_reminders;
    private LinearLayoutManager linearLayoutManager;
    private ReminderAdapter reminderAdapter;
    private List<Reminder> reminders;
    ApiInterfaceRemainder apiInterfaceRemainder;
    Retrofit retrofit;
    View root;

    @Override
    public void onResume() {
        super.onResume();
        initReminders();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        remindersViewModel =
                new ViewModelProvider(this).get(RemindersViewModel.class);

        binding = FragmentRemindersBinding.inflate(inflater, container, false);
        root = binding.getRoot();
//        Bundle extras = getActivity().getIntent().getExtras() ;
//
//        if (extras != null) {
//            String msg = extras.getString("msgrmove");
//
//            Toast.makeText(getContext(),  "msg", Toast.LENGTH_SHORT).show();
//        }
        retrofit = new Retrofit.Builder()
                .baseUrl(environementVariablesOfDakirni.backEndUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterfaceRemainder = retrofit.create(ApiInterfaceRemainder.class);

        btn_add_reminder = root.findViewById(R.id.btn_add_reminder);

        btn_add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetReminderActivity.class);
                startActivity(intent);
            }
        });

//        initReminders();
//        initRemindersRecyclerView(root);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void initRemindersRecyclerView(View root, List<Reminder> reminders) {
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
        reminders.clear();
        Call<List<Reminder>> call =apiInterfaceRemainder.getRemaiders(environementVariablesOfDakirni.key) ;
        call.enqueue(new Callback<List<Reminder>>() {
            @Override
            public void onResponse(Call<List<Reminder>> call, Response<List<Reminder>> response) {
                for (Reminder a : response.body()) {
                     reminders.add(a);

            }


           //     Toast.makeText(getContext(),  response.body().toString()  response.body().toString(), Toast.LENGTH_SHORT).show();
            initRemindersRecyclerView(root, reminders);

            }

            @Override
            public void onFailure(Call<List<Reminder>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("nabil",t.getMessage());
            }
        });



    }


    }
