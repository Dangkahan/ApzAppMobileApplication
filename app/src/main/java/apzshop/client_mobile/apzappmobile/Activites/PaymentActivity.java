package apzshop.client_mobile.apzappmobile.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apzshop.client_mobile.apzappmobile.Adapters.CartListAdapter;
import apzshop.client_mobile.apzappmobile.Adapters.CategoryAdapter;
import apzshop.client_mobile.apzappmobile.Adapters.PaymentAdapter;
import apzshop.client_mobile.apzappmobile.Domain.CategoryDomain;
import apzshop.client_mobile.apzappmobile.Domain.PaymentDomain;
import apzshop.client_mobile.apzappmobile.Helper.CartManagement;
import apzshop.client_mobile.apzappmobile.Helper.ChangeNumberItemListener;
import apzshop.client_mobile.apzappmobile.Model.ModePaymentModel;
import apzshop.client_mobile.apzappmobile.R;

public class PaymentActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCart,recyclerViewpayment;

    private List<ModePaymentModel> modelist;

    TextView totalFeeTxt,totalTxt,emptyTxt;

    String mPayment =" ";


    private double tax;

    Button paymentBtn;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    private CartManagement cartManagement;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        auth= FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        cartManagement = new CartManagement(this);

        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        totalTxt=findViewById(R.id.totalTxt);




        setVariable();
        initView();
        initlist();
//        initRecyclerview();

        paymentBtn=findViewById(R.id.pay_btn);
        recyclerViewpayment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            String final_order= "";


            Map<String,String> map = new HashMap<>();


            @Override
            public void onClick(View v) {
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("OrderList").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            return;
                        }

                    }
                });
            }
        });


        paymentBtn.setOnClickListener(v -> startActivity(new Intent(PaymentActivity.this,SoldoutActivity.class)));
    }

//    private void initRecyclerview() {ArrayList<PaymentDomain> items=new ArrayList<>();
//
//        items.add(new PaymentDomain("Cash On Delivery"));
//        items.add(new PaymentDomain("GCash"));
//        items.add(new PaymentDomain("Bank"));
//
//        recyclerViewpayment=findViewById(R.id.paymentview);
//        recyclerViewpayment.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//
//        adapter=new PaymentAdapter(items);
//        recyclerViewpayment.setAdapter(adapter);
//    }


    private void initlist() {
        recyclerViewCart=findViewById(R.id.cartview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCart.setLayoutManager(linearLayoutManager);
        adapter = new PaymentAdapter(cartManagement.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void change() {
//                calculateCart();
            }
        });

        recyclerViewCart.setAdapter(adapter);
        if (cartManagement.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }


    private void setVariable() {
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

    private void initView() {
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        totalTxt=findViewById(R.id.taxTxt);
        recyclerViewpayment=findViewById(R.id.paymentview);
        recyclerViewCart=findViewById(R.id.cartview);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView=findViewById(R.id.scrollView3);
    }

}