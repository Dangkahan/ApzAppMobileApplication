package apzshop.client_mobile.apzappmobile.Adapters;

import android.content.Context;
import android.content.Intent;
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

import apzshop.client_mobile.apzappmobile.Activites.DetailActivity;
import apzshop.client_mobile.apzappmobile.Activites.itemCategoryActivity;
import apzshop.client_mobile.apzappmobile.Domain.CategoryDomain;
import apzshop.client_mobile.apzappmobile.Domain.PopularDomain;
import apzshop.client_mobile.apzappmobile.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewholder>{

    ArrayList<CategoryDomain> items;
    Context context;

    public CategoryAdapter(ArrayList<CategoryDomain> items) {this.items=items;}
//
//    public CategoryAdapter(ArrayList<CategoryDomain> items) { this.items =items;
//    }

    @NonNull
    @Override
    public CategoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        context=parent.getContext();
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.Viewholder holder, int position) {
        holder.type.setText(items.get(position).getType());
        int drawableResourceId=holder.itemView.getResources().getIdentifier(items.get(position).getCatpicUrl(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);

        holder.itemView.setOnClickListener(v ->{
            Intent intent= new Intent(holder.itemView.getContext(),itemCategoryActivity.class);
            intent.putExtra("object", items.get(position));
            holder.itemView.getContext().startActivity(intent);
                });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView type;
        ImageView categoryPic;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.typeTxt);
            categoryPic = itemView.findViewById(R.id.categoryPic);
        }
    }
}
