package apzshop.client_mobile.apzappmobile.Activites;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import apzshop.client_mobile.apzappmobile.Adapters.CategoryAdapter;
import apzshop.client_mobile.apzappmobile.Adapters.PopularListAdapter;
import apzshop.client_mobile.apzappmobile.Domain.CategoryDomain;
import apzshop.client_mobile.apzappmobile.R;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterCategory;

    private RecyclerView recyclerViewCategory;

    private ImageView backBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initRecyclerview();
        setVariable();
        initView();
    }

    private void initRecyclerview() {
       ArrayList<CategoryDomain> items=new ArrayList<>();


        items.add(new CategoryDomain( "Mobile Phone","catpic1"));
        items.add(new CategoryDomain( "Laptop","catpic2"));
        items.add(new CategoryDomain( "Smart Watch","catpic3"));

        recyclerViewCategory=findViewById(R.id.view68);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapterCategory=new CategoryAdapter(items);
        recyclerViewCategory.setAdapter(adapterCategory);
    }
    private void setVariable() {
        // Initialize backBtn
        backBtn = findViewById(R.id.backBtn);

        // Set OnClickListener for backBtn
        backBtn.setOnClickListener(v -> {
            finish();
        });
    }
    private void initView(){

        backBtn=findViewById(R.id.backBtn);
        recyclerViewCategory=findViewById(R.id.view68);
    }
}