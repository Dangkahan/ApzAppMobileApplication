package apzshop.client_mobile.apzappmobile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apzshop.client_mobile.apzappmobile.Model.AddressModel;
import apzshop.client_mobile.apzappmobile.R;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    List<AddressModel> addressModelList;
    Context context;
    SelectedAddress selectedAddress;

    private RadioButton selectedRadioBtn;

    public AddressAdapter(List<AddressModel> addressModelList, Context context, SelectedAddress selectedAddress) {
        this.addressModelList = addressModelList;
        this.context = context;
        this.selectedAddress = selectedAddress;
    }

    @NonNull
    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.ViewHolder holder, int position) {

        AddressModel currentAddress = addressModelList.get(position);
        holder.address.setText(currentAddress.getUserAddress());

        // Set the RadioButton state based on the selected state of the current address
        holder.radioButton.setChecked(currentAddress.isSelected());

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (AddressModel address : addressModelList) {
                    address.setSelected(false);
                }
                // Set the selected state of the current address to true
                currentAddress.setSelected(true);
                notifyDataSetChanged(); // Notify the adapter that data has changed
            }
        });

    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView address;
        RadioButton radioButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            address = itemView.findViewById(R.id.address_add);
            radioButton = itemView.findViewById(R.id.select_address);
        }
    }

    public  interface SelectedAddress{
        void setAddress(String address);
    }
}
