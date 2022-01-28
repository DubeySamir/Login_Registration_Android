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

public class FemaleUsersFragment extends Fragment {

    UserRVAdapter userRVAdapter;
    RecyclerView feMaleUsersRecyclerView;

    public FemaleUsersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_female_users, container, false);

        feMaleUsersRecyclerView = v.findViewById(R.id.feMaleUsersRecyclerView);
        DatabaseHelper helper = new DatabaseHelper(getContext());
        userRVAdapter = new UserRVAdapter(helper.getAllMaleUsersData("Female"), getContext());

        feMaleUsersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        feMaleUsersRecyclerView.setAdapter(userRVAdapter);

        userRVAdapter.setOnItemClickListener(position -> {
            Integer userID = userRVAdapter.getUserId(position);
            Toast.makeText(getContext(), "user id = " + userID, Toast.LENGTH_SHORT).show();
        });
        return v;
    }
}