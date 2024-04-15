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

import apzshop.client_mobile.apzappmobile.Domain.PopularDomain;
import apzshop.client_mobile.apzappmobile.Helper.CartManagement;
import apzshop.client_mobile.apzappmobile.Helper.ChangeNumberItemListener;
import apzshop.client_mobile.apzappmobile.R;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.Viewholder> {
    ArrayList<PopularDomain> listItemselected;
    private CartManagement cartManagement;
    ChangeNumberItemListener changeNumberItemListener;

    public CartListAdapter(ArrayList<PopularDomain> listItemselected,Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.listItemselected = listItemselected;
        cartManagement = new CartManagement(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @NonNull
    @Override
    public CartListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.Viewholder holder, int position) {
        holder.title.setText(listItemselected.get(position).getTitle());
        holder.feeEachItem.setText("₱"+listItemselected.get(position).getPrice());
        holder.totalEachItem.setText("₱"+Math.round(listItemselected.get(position).getNumberinCart()*listItemselected.get(position).getPrice()));
        holder.num.setText(String.valueOf(listItemselected.get(position).getNumberinCart()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(listItemselected.get(position).getPicUrl(),
                "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30, 30, 30, 30))
                .into(holder.pic);

        holder.plusItem.setOnClickListener(v -> {
            cartManagement.plusNumberItem(listItemselected, position, () -> {
                notifyDataSetChanged();
                changeNumberItemListener.change();
            });
        });

        holder.minusItem.setOnClickListener(v -> {
            cartManagement.minusNumberItem(listItemselected, position, () -> {
                notifyDataSetChanged();
                changeNumberItemListener.change();
            });
        });

    }

    @Override
    public int getItemCount() {
        return listItemselected.size();
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
