package com.businessx.costumersmanagementapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.businessx.costumersmanagementapp.models.Client;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<Client> selectedClient = new MutableLiveData<>();

    public void selectClient(Client client) {
        selectedClient.setValue(client);
    }

    public LiveData<Client> getSelectedClient() {
        return selectedClient;
    }

}
