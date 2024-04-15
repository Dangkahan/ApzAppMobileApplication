package apzshop.client_mobile.apzappmobile.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import apzshop.client_mobile.apzappmobile.Activites.PaymentActivity;
import apzshop.client_mobile.apzappmobile.Activites.SoldoutActivity;
import apzshop.client_mobile.apzappmobile.Activites.itemCategoryActivity;
import apzshop.client_mobile.apzappmobile.Model.ModePaymentModel;
import apzshop.client_mobile.apzappmobile.R;

public class ModePaymentAdapter extends RecyclerView.Adapter<ModePaymentAdapter.Viewholder> {

    ArrayList<ModePaymentModel> type;


    SelectedMode selectedMode;
    Context context;

    public ModePaymentAdapter(ArrayList<ModePaymentModel> items) {this.type=items;} {
//        this.adapters = adapters;
//        this.selectedMode = selectedMode;
//        this.context = context;
    }

    private RadioButton selectedRadioBtn;

    @NonNull
    @Override
    public ModePaymentAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_item,parent,false);
        context=parent.getContext();
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ModePaymentAdapter.Viewholder holder, int position) {
        holder.mode.setText(type.get(position).getType());

        holder.itemView.setOnClickListener(v ->{
            Intent intent= new Intent(holder.itemView.getContext(), SoldoutActivity.class);
            intent.putExtra("object", (CharSequence) type.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return type.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView mode;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            mode = itemView.findViewById(R.id.payment_add);
        }
    }
    public  interface SelectedMode{
        void setMode(String mode);
    }
}
