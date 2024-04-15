package apzshop.client_mobile.apzappmobile.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import apzshop.client_mobile.apzappmobile.Domain.PopularDomain;
import apzshop.client_mobile.apzappmobile.Helper.CartManagement;
import apzshop.client_mobile.apzappmobile.R;

public class DetailActivity extends AppCompatActivity {

    private Button addToCartBtn,buyNowBtn;
    private TextView titleTxt,feeTxt,descriptionTxt,reviewTxt,rateTxt;
    private ImageView picFood,backBtn;
    private PopularDomain object;
    private int numberOrder = 1;
    private CartManagement cartManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cartManagement = new CartManagement(this);

        initView();
        getBundle();

    }

    private void getBundle() {
        object=(PopularDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPicUrl(),"drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("₱"+object.getPrice());
        descriptionTxt.setText(object.getDescription());
        reviewTxt.setText(object.getReview()+"");
        rateTxt.setText(object.getRate()+"");



        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this,PaymentActivity.class));
            }
        });

        addToCartBtn.setOnClickListener(v -> {
            object.setNumberinCart(numberOrder);
            cartManagement.insertFood(object);
        });


        backBtn.setOnClickListener(v -> finish());



    }

    private void initView() {
        buyNowBtn=findViewById(R.id.buyNowBtn);
        addToCartBtn=findViewById(R.id.addToCartBtn);
        feeTxt=findViewById(R.id.priceTxt);
        titleTxt=findViewById(R.id.titleTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        reviewTxt=findViewById(R.id.reviewTxt);
        rateTxt=findViewById(R.id.rateTxt);
        picFood=findViewById(R.id.itemPic);
        backBtn=findViewById(R.id.backBtn);
    }
}