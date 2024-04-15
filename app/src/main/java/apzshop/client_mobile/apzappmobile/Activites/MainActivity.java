package apzshop.client_mobile.apzappmobile.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import apzshop.client_mobile.apzappmobile.Adapters.PopularListAdapter;
import apzshop.client_mobile.apzappmobile.Domain.PopularDomain;
import apzshop.client_mobile.apzappmobile.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerViewPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initRecyclerview();
        bottom_navigation();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void bottom_navigation() {

        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout gadgetBtn=findViewById(R.id.gadgetBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
        gadgetBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,CategoryActivity.class)));
    }


    private void initRecyclerview() {
        ArrayList<PopularDomain> items=new ArrayList<>();
        items.add(new PopularDomain("LG Laptop","LG gram's touchscreen makes it fast, easy and intuitive to launch and use applications.","pic1",894,4.9,45999));
        items.add(new PopularDomain("Nike Men's Shoes","Experience the perfect fusion of style and performance with the Nike Air Max SC Shoes.","pic2",101,3.1,899));
        items.add(new PopularDomain("Fortune Plant","DOUBLE THE PLANT, DOUBLE THE FUN! Save more and avail of our Double Deal Package.","pic3",15,4.2,250));


        recyclerViewPopular=findViewById(R.id.view1);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapterPopular=new PopularListAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);
    }

}