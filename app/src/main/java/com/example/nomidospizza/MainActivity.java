package com.example.nomidospizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name_editText;
    TextView quantity_textview;
    Button increment_btn;
    Button decrement_btn;
    Button submit_btn;
    CheckBox cheese_topping;
    CheckBox chilli_topping;

    int quantity = 0, price = 0;
    String message;

@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    name_editText = findViewById(R.id.editTextTextPersonName);
    quantity_textview = findViewById(R.id.quantity_text_view);
    increment_btn = findViewById(R.id.increment_button);
    decrement_btn = findViewById(R.id.decrement_button);
    submit_btn = findViewById(R.id.submit_order_button);
    cheese_topping = findViewById(R.id.cheese_checkBox);
    chilli_topping = findViewById(R.id.chilli_checkBox);

        //Quantity increment button
        increment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                display();
            }
        });

        //Quantity decrement button
        decrement_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity >=1) {
                    quantity--;
                    display();
                }
                else
                    Toast.makeText(MainActivity.this,"Can't order less than one",Toast.LENGTH_SHORT).show();
            }
        });

        //Order Submitting Button
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createOrderSummary();
            }
        });

        };


  public void display(){
      quantity_textview.setText(""+quantity);
  }
  public void createOrderSummary(){
      message = getString(R.string.name) + name_editText.getText().toString();
      message += "\n" + getString(R.string.Cheese_topping) + cheese_topping.isChecked();
      message += "\n" + getString(R.string.Chilli_topping) + chilli_topping.isChecked();
      message += "\n" + getString(R.string.grand_total) + (quantity * 5) ;
      message += "\n" + getString(R.string.thank_you_message);
      sendMail();
  }
  private void sendMail(){
      Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
      mailIntent.setData(Uri.parse("mailto:"));
      mailIntent.putExtra(Intent.EXTRA_SUBJECT,"You'r Pizza order..");
      mailIntent.putExtra(Intent.EXTRA_TEXT,message);
      try{
      startActivity(mailIntent);
      }catch(Exception e){
          Toast.makeText(MainActivity.this,"Install Gmail App",Toast.LENGTH_SHORT).show();
      }
  }

}
