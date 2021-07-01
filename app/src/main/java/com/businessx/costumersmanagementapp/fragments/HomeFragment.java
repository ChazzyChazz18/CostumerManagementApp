package com.businessx.costumersmanagementapp.fragments;

import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {

    private ClientsAdapter clientsAdapter;
    private AppCompatButton addClientBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setupViews(view);

        setupRecyclerview(view);

        //loadRecyclerviewData();
        testData();

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

    private void testData () {
        int i;
        int totalTestUsers = 50;
        ArrayList<Client> clientsArrayTest = new ArrayList<>();
        ArrayList<String> clientsAddressArrayTest = new ArrayList<>();

        clientsAddressArrayTest.add("Calle Sabaneta #39 Esanchez Mano Guallabo");
        clientsAddressArrayTest.add("Calle Trinidad #09 Ciudad Nueva");
        clientsAddressArrayTest.add("Calle 28 #56 Ensanchez Isabelita");
        for (i = 0; i < totalTestUsers; i++){
            clientsArrayTest.add(new Client("Cliente " + (i+1), clientsAddressArrayTest));
        }

        clientsAdapter.addClientList(clientsArrayTest);
    }

    private void loadRecyclerviewData () {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
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
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(@NonNull Call<ClientResponse> call,@NonNull Throwable t) {
                // If there is not response and something happened
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }

}
