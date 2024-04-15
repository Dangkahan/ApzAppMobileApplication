package apzshop.client_mobile.apzappmobile.Activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import apzshop.client_mobile.apzappmobile.R;

public class AddAddressActivity extends AppCompatActivity {

    EditText name,address,city,postalcode,phoneNumber;
    Button addAddressBtn;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_address);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        auth= FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        name=findViewById(R.id.ad_name);
        address=findViewById(R.id.ad_address);
        city=findViewById(R.id.ad_city);
        postalcode=findViewById(R.id.ad_code);
        phoneNumber=findViewById(R.id.ad_phone);
        addAddressBtn=findViewById(R.id.add_address_btn);

        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = name.getText().toString();
                String userAddress = address.getText().toString();
                String userCity = city.getText().toString();
                String userCode = postalcode.getText().toString();
                String userNumber = phoneNumber.getText().toString();

                String final_address= "";

                if (!userName.isEmpty()){
                    final_address+=userName;
                }
                if (!userAddress.isEmpty()){
                    final_address+=userAddress;
                }

                if (!userCity.isEmpty()){
                    final_address+=userCity;
                }

                if (!userCode.isEmpty()){
                    final_address+=userCode;
                }

                if (!userNumber.isEmpty()){
                    final_address+=userNumber;
                }

                if (!userName.isEmpty() && !userAddress.isEmpty() && !userCity.isEmpty() && !userCode.isEmpty() && !userNumber.isEmpty()){


                    Map<String,String> map = new HashMap<>();
                    map.put("userAddress",final_address);

                    firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AddAddressActivity.this, "Added Address", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(AddAddressActivity.this,"Please Fill the blanks",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}