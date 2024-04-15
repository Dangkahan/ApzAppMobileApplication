package apzshop.client_mobile.apzappmobile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

import java.util.ArrayList;

import apzshop.client_mobile.apzappmobile.Domain.PaymentDomain;
import apzshop.client_mobile.apzappmobile.Domain.PopularDomain;
import apzshop.client_mobile.apzappmobile.Helper.CartManagement;
import apzshop.client_mobile.apzappmobile.Helper.ChangeNumberItemListener;
import apzshop.client_mobile.apzappmobile.R;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.Viewholder> {

    ArrayList<PopularDomain> itemselected;
    private CartManagement cartManagement;

    ChangeNumberItemListener changeNumberItemListener;

    Context context;



    public PaymentAdapter(ArrayList<PopularDomain> listItemselected, Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.itemselected = listItemselected;
        cartManagement = new CartManagement(context);
        this.context = context;
        this.changeNumberItemListener = changeNumberItemListener;
    }

    public PaymentAdapter(ArrayList<PaymentDomain> items) {

    }

    @NonNull
    @Override
    public PaymentAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new Viewholder(inflate);
    }
    @Override
    public int getItemViewType(int position) {
        return itemselected.size();
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.Viewholder holder, int position){
        holder.title.setText(itemselected.get(position).getTitle());
        holder.feeEachItem.setText("₱"+itemselected.get(position).getPrice());
        holder.totalEachItem.setText("₱"+Math.round(itemselected.get(position).getNumberinCart()*itemselected.get(position).getPrice()));
        holder.num.setText(String.valueOf(itemselected.get(position).getNumberinCart()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(itemselected.get(position).getPicUrl(),
                "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30, 30, 30, 30))
                .into(holder.pic);

        holder.plusItem.setOnClickListener(v -> {
            cartManagement.plusNumberItem(itemselected, position, () -> {
                notifyDataSetChanged();
                changeNumberItemListener.change();
            });
        });

        holder.minusItem.setOnClickListener(v -> {
            cartManagement.minusNumberItem(itemselected, position, () -> {
                notifyDataSetChanged();
                changeNumberItemListener.change();
            });
        });

    }

    @Override
    public int getItemCount() {
        return itemselected.size();
    }


    public static class Viewholder extends RecyclerView.ViewHolder {

        TextView title,feeEachItem,plusItem,minusItem;
        ImageView pic;
        TextView totalEachItem, num;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            pic=itemView.findViewById(R.id.itemPic);
            plusItem=itemView.findViewById(R.id.plusBtn);
            minusItem=itemView.findViewById(R.id.minusBtn);
            num=itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
