package apzshop.client_mobile.apzappmobile.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

import java.util.ArrayList;

import apzshop.client_mobile.apzappmobile.Activites.DetailActivity;
import apzshop.client_mobile.apzappmobile.Domain.PopularDomain;
import apzshop.client_mobile.apzappmobile.R;

public class PopularListAdapter extends RecyclerView.Adapter<PopularListAdapter.Viewholder> {
    ArrayList<PopularDomain> items;

    public PopularListAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
    }

    Context context;



    @Override
    public PopularListAdapter.Viewholder onCreateViewHolder( ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pop_list,parent,false);
        context= parent.getContext();
        return new Viewholder(inflate);

    }

    @Override
    public void onBindViewHolder( PopularListAdapter.Viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
//        holder.reviewTxt.setText(items.get(position).getReview());
        holder.feeTxt.setText("₱"+items.get(position).getPrice());
        holder.RateTxt.setText(""+items.get(position).getRate());
        int drawableResourceId=holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(),
                "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("object", items.get(position));
            holder.itemView.getContext().startActivity(intent);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView titleTxt,feeTxt,RateTxt;
        ImageView pic;
        public Viewholder(View itemView) {
            super(itemView);

            titleTxt=itemView.findViewById(R.id.titleTxt);
            feeTxt=itemView.findViewById(R.id.feeTxt);
            RateTxt=itemView.findViewById(R.id.rateTxt);
//            reviewTxt=itemView.findViewById(R.id.reviewTxt);
            pic=itemView.findViewById(R.id.itemPic);
        }
    }
}