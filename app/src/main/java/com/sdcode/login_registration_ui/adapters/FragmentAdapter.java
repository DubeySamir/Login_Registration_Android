package com.sdcode.login_registration_ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sdcode.login_registration_ui.fragments.AccountFragment;
import com.sdcode.login_registration_ui.fragments.CartFragment;
import com.sdcode.login_registration_ui.fragments.HomeFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new CartFragment();
            case 2:
                return new AccountFragment();
        }

        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
