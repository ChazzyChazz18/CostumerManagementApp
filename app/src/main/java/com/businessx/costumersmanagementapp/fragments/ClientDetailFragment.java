package com.businessx.costumersmanagementapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.businessx.costumersmanagementapp.R;
import com.businessx.costumersmanagementapp.adapters.AddressesAdapter;
import com.businessx.costumersmanagementapp.models.Client;

public class ClientDetailFragment extends Fragment {

    private Client selectedClient;
    private TextView clientNameTextview;
    private TextView clientAddressNumberTextview;
    private AddressesAdapter addressesAdapter;
    private AppCompatButton modifyBtn;
    private AppCompatButton deleteBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_detail, container, false);

        setupViews(view);

        setupRecyclerview(view);

        handleOnclickEvents();

        return view;
    }

    private void setupViews (View view) {
        clientNameTextview = view.findViewById(R.id.client_detail_layaout_name);
        clientAddressNumberTextview = view.findViewById(R.id.client_detail_layaout_address_number);
        modifyBtn = view.findViewById(R.id.modify_btn);
        deleteBtn = view.findViewById(R.id.delete_btn);
    }

    private void setupRecyclerview (View view) {
        RecyclerView recyclerView = view.findViewById(R.id.fragment_home_recyclerview);
        addressesAdapter = new AddressesAdapter(getContext());
        recyclerView.setAdapter(addressesAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void handleOnclickEvents () {
        modifyBtn.setOnClickListener(view -> {
            // Handle the request to modify client info
            Toast.makeText(getContext(), "Modifying client", Toast.LENGTH_LONG).show();
        });
        deleteBtn.setOnClickListener(view -> {
            // Handle the request to delete the client or any of the client info (address)
            Toast.makeText(getContext(), "Deleting client", Toast.LENGTH_LONG).show();
        });
    }

    public void setSelectedClient (Client client) {
        selectedClient = client;
        setViewsData();
    }

    private void setViewsData () {
        clientNameTextview.setText(selectedClient.getName());
        String clientAdressNumberStr = getString(R.string.total_address_number) + selectedClient.getAdressListSizeStr();
        clientAddressNumberTextview.setText(clientAdressNumberStr);
        addressesAdapter.addAddressList(selectedClient.getAddressList());
    }

}