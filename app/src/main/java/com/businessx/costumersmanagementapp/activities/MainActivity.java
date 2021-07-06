package com.businessx.costumersmanagementapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.businessx.costumersmanagementapp.R;
import com.businessx.costumersmanagementapp.fragments.ClientDetailFragment;
import com.businessx.costumersmanagementapp.fragments.HomeFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    Fragment activeFragment;

    Fragment homeFragment;
    Fragment clientDetailFragment;

    /*public Fragment getHomeFragment() {
        return homeFragment;
    }*/

    public Fragment getClientDetailFragment() {
        return clientDetailFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle("My Activity title");

        getSupportActionBar().setHomeButtonEnabled(true);

        initializeFragments();
    }

    private void initializeFragments () {
        fragmentManager = getSupportFragmentManager();

        homeFragment = new HomeFragment();
        clientDetailFragment = new ClientDetailFragment();

        activeFragment = homeFragment;

        //We dont hide the first added fragment as it is the one the user will see first
        fragmentManager.beginTransaction().add(R.id.fragment_container, homeFragment, getString(R.string.home_fragment_tag)).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, clientDetailFragment, getString(R.string.client_detail_fragment_tag)).hide(clientDetailFragment).commit();
    }

    // Change the current fragment visible to the desired one (if they're not the same)
    public void showSelectedFragment (Fragment fragmentToShow) {
        if(fragmentToShow != activeFragment) {
            fragmentManager.beginTransaction()
                    .hide(activeFragment)
                    .show(fragmentToShow)
                    .commit();
            activeFragment = fragmentToShow;
        }
    }

    @Override
    public void onBackPressed() {
        // Here we handle the logic when we press the 'back' btn
        if(activeFragment.equals(clientDetailFragment)) {
            showSelectedFragment(homeFragment);
        }else {
            super.onBackPressed();
        }
    }
}