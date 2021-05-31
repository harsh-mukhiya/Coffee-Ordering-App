package com.example.coffeeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name;
    Button plus;
    Button minus;
    Button order;
    TextView quantity;
    CheckBox w,c,va;
    int no=1;
    Editable editable;
    String s;
    int price;


    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        plus=findViewById(R.id.add);
        minus=findViewById(R.id.minus);
        order=findViewById(R.id.buttonOrder);
        quantity=findViewById(R.id.Quantity);
        w=findViewById(R.id.checkBoxW);
        c=findViewById(R.id.checkBoxC);
        va=findViewById(R.id.checkBoxV);

        plus.setOnClickListener(v -> {
            if(no==100){
                Toast.makeText(MainActivity.this, "Order at least one", Toast.LENGTH_SHORT).show();
            }
            else{
                no++;
                quantity.setText(String.format("%s", no));
            }
        });
        minus.setOnClickListener(v -> {
            if(no==1){
                Toast.makeText(MainActivity.this, "Order at least one", Toast.LENGTH_SHORT).show();
            }
            else{
                no--;
                quantity.setText(String.format("%s", no));
            }

        });


        order.setOnClickListener(v ->{

            price=no*5;
            editable=name.getText();
            s=editable.toString().toUpperCase()+", your order is : \n";
            s+="No of Coffees : "+ no+ "\n";

                boolean vanilla= va.isChecked();
                boolean whipped= w.isChecked();
                boolean chocolate= c.isChecked();

            if(chocolate) {
                price = price + 2;
                s+="Chocolate Syrup added $2\n";
            }
            if(whipped) {
                price++;
                s += "Whipped Cream added $1\n";
            }
            if(vanilla) {
                price++;
                s += "Vanilla Extract added $1\n";
            }
            s+="Price : $"+ price;
            s+="\n  THANK YOU "+"!!!";

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT,"Place order by name : "+name.getText().toString());
            intent.putExtra(Intent.EXTRA_TEXT, s);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

        });
    }

}