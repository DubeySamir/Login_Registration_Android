package com.sdcode.login_registration_ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sdcode.login_registration_ui.R;
import com.sdcode.login_registration_ui.adapters.UserRVAdapter;
import com.sdcode.login_registration_ui.database.DatabaseHelper;

public class MaleUsersFragment extends Fragment {

    UserRVAdapter userRVAdapter;
    RecyclerView maleUsersRecyclerView;

    public MaleUsersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_male_users, container, false);

        maleUsersRecyclerView = v.findViewById(R.id.maleUsersRecyclerView);
        DatabaseHelper helper = new DatabaseHelper(getContext());
        userRVAdapter = new UserRVAdapter(helper.getAllMaleUsersData("Male"), getContext());

        maleUsersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        maleUsersRecyclerView.setAdapter(userRVAdapter);

        userRVAdapter.setOnItemClickListener(position -> {
            Integer userID = userRVAdapter.getUserId(position);
            Toast.makeText(getContext(), "user id = " + userID, Toast.LENGTH_SHORT).show();
        });

        return v;
    }
}