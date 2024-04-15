package apzshop.client_mobile.apzappmobile.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import apzshop.client_mobile.apzappmobile.Adapters.CartListAdapter;
import apzshop.client_mobile.apzappmobile.Domain.PaymentDomain;
import apzshop.client_mobile.apzappmobile.Domain.PopularDomain;
import apzshop.client_mobile.apzappmobile.Helper.CartManagement;
import apzshop.client_mobile.apzappmobile.Helper.ChangeNumberItemListener;
import apzshop.client_mobile.apzappmobile.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private CartManagement cartManagement;

    private TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt;
    private double tax;
    private ScrollView scrollView;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cartManagement = new CartManagement(this);

        initView();
        setVariable();
        initlist();
        calculateCart();

    }

    private void initlist() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(cartManagement.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });

        recyclerView.setAdapter(adapter);
        if(cartManagement.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }


    private void calculateCart() {
        double percentTax=0.02;
//        double delivery=10;
        tax=Math.round((cartManagement.getTotalFee()*percentTax*100.0)) / 100.0;

        double total=Math.round((cartManagement.getTotalFee()+tax)*100)/100;
        double itemTotal=Math.round(cartManagement.getTotalFee()*100)/100;

        totalFeeTxt.setText("₱"+itemTotal);
//        taxTxt.setText("₱"+tax);
//        deliveryTxt.setText("₱"+delivery);
        totalTxt.setText("₱"+total);
    }

    private void setVariable() {

        backBtn.setOnClickListener(v -> finish());

    }

    private void initView() {
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliverFeeTxt);
        totalTxt=findViewById(R.id.totalTxt);
        recyclerView=findViewById(R.id.view3);
        scrollView=findViewById(R.id.scrollView2);
        backBtn=findViewById(R.id.backBtn);
        emptyTxt=findViewById(R.id.emptyTxt);
    }
}