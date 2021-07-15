package com.businessx.costumersmanagementapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.businessx.costumersmanagementapp.activities.MainActivity;
import com.businessx.costumersmanagementapp.R;
import com.businessx.costumersmanagementapp.models.Client;
import com.businessx.costumersmanagementapp.viewModel.SharedViewModel;

import java.util.ArrayList;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {

    private final ArrayList<Client> dataset;
    private final Context context;
    private final SharedViewModel model;

    public ClientsAdapter(Context context, SharedViewModel model) {
        this.context = context;
        this.model = model;
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.client_layout_item,
                viewGroup, false);

        return new ViewHolder(view);
    }

    private String concatText (String str1, String str2) {
        return str1 + " " + str2;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final Client client = dataset.get(i);
        viewHolder.clientName.setText(client.getFullName());
        viewHolder.clientAdressListNumber.setText(
                concatText(context.getString(R.string.addresses), client.getAdressListSizeStr())
        );
        viewHolder.clientFirstAdress.setText(
                concatText(context.getString(R.string.first_address), client.getAddressList().get(0).getFullAddress())
        );

        viewHolder.itemView.setOnClickListener(v -> {
            model.selectClient(client);

            Fragment clientDetailFrag = ((MainActivity)context).getClientDetailFragment();
            FragmentTransaction fragmentTransaction = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,clientDetailFrag);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addClientList (ArrayList<Client> clientList) {
        dataset.addAll(clientList);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView clientName;
        private final TextView clientAdressListNumber;
        private final TextView clientFirstAdress;

        ViewHolder(View itemView) {
            super(itemView);
            clientName = itemView.findViewById(R.id.client_layout_name);
            clientAdressListNumber = itemView.findViewById(R.id.client_layout_address_number);
            clientFirstAdress = itemView.findViewById(R.id.client_layout_first_address);
        }
    }

}
