package com.businessx.costumersmanagementapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.businessx.costumersmanagementapp.R;
import com.businessx.costumersmanagementapp.fragments.ClientDetailFragment;
import com.businessx.costumersmanagementapp.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    Fragment homeFragment;
    Fragment clientDetailFragment;

    public Fragment getClientDetailFragment() {
        return clientDetailFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFragments();
    }

    //
    public void initializeFragments(){

        homeFragment = new HomeFragment();
        clientDetailFragment = new ClientDetailFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();

    }

}