package com.businessx.costumersmanagementapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.businessx.costumersmanagementapp.R;
import com.businessx.costumersmanagementapp.adapters.ClientsAdapter;
import com.businessx.costumersmanagementapp.api_service.ApiService;
import com.businessx.costumersmanagementapp.api_service.ClientResponse;
import com.businessx.costumersmanagementapp.models.Client;
import com.businessx.costumersmanagementapp.retrofit.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private ClientsAdapter clientsAdapter;
    private AppCompatButton addClientBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Set up all the views reference
        setupViews(view);
        setupRecyclerview(view);

        // Get the data from the RestApi to the Recyclerview
        loadRecyclerviewData();

        // Handle all the Onclick events on this fragment
        handleOnclickEvents();

        return view;
    }

    private void setupViews (View view) {
        addClientBtn = view.findViewById(R.id.add_client_btn);
    }

    private void setupRecyclerview (View view) {
        RecyclerView recyclerView = view.findViewById(R.id.fragment_home_recyclerview);
        clientsAdapter = new ClientsAdapter(getContext());
        recyclerView.setAdapter(clientsAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void handleOnclickEvents () {
        addClientBtn.setOnClickListener(view -> {
            // Handle the request to add a new client
            Toast.makeText(getContext(), "Adding client", Toast.LENGTH_LONG).show();
        });
    }

    private void loadRecyclerviewData () {

        //String TAG = "Debugging";

        RetrofitClient.getClient(getString(R.string.api_base_url));

        ApiService service = RetrofitClient.getRetrofit().create(ApiService.class);
        Call<ClientResponse> clientResponseCall = service.obtainClientList();

        clientResponseCall.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(@NonNull Call<ClientResponse> call,@NonNull Response<ClientResponse> response) {
                if (response.isSuccessful()) { // If there is response and is successful
                    ClientResponse clientResponse = response.body();

                    if (clientResponse != null) {
                        ArrayList<Client> clientsList = clientResponse.getData();

                        if (clientsList != null) {
                            clientsAdapter.addClientList(clientsList);
                        }
                    }
                } else { // If there is response but is unsuccessful
                    //Log.e(TAG, "onResponse: " + response.errorBody());
                    assert response.errorBody() != null;
                    Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<ClientResponse> call,@NonNull Throwable t) {
                // If there is not response and something happened
                //Log.e(TAG, " onFailure: " + t.getMessage());
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
