package com.developer.annant.jj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    int quantity = 0;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    // public void onCheckboxClicked(View view) {}

//public void ShowSummary(){}

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name) {


        String summary;
        summary = "Name = " + name;
        summary = summary + "\nAdd Chocolate  = " + addChocolate;
        summary = summary + "\nAdd Whipped Cream  = " + addWhippedCream;
        summary = summary + "\nQuantity = " + quantity;
        summary = summary + "\nPrice : Rs.  " + price;
        summary = summary + "\nThank You :)";
        return (summary);
    }


    /**
     * To increase the quantity
     */
    public void increment(View view) {
        quantity = quantity + 1;
        if (quantity >= 100) {//toast
            quantity = 100;
            Toast.makeText(this, "Max no Of coffee is 100 ", Toast.LENGTH_SHORT).show();
        }

        displayQuantity(quantity);

    }

    /**
     * To decrease the  quantity
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        if (quantity < 1) {//toast
            quantity = 1;
            Toast.makeText(this, "Cannot have less than one cup of coffee", Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantity);
    }


    public void showtheSummary(View view) {

        String printData = fullOrderSummary();
        Intent startIntent = new Intent(StartActivity.this, ShowSummary.class);
        startIntent.putExtra("printValue", printData);
        startActivity(startIntent);

        //        methodToCall(false);

    }


    public void methodToCall() {

        {
            String priceMessage = fullOrderSummary();
            sendIntentEmail(priceMessage);
        }
    }


    public String fullOrderSummary() {

        EditText name_edit = (EditText) findViewById(R.id.name_edit);
        String name = name_edit.getText().toString();
//above  code is for getting text name from user

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //above line create CheckBox type whipped cream the below line is for chocolate */

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocloate_cream_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();


        int price = calculatePrice(hasWhippedCream, hasChocolate);

        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);

        return priceMessage;
    }

    public void submitOrder(View view) {

        methodToCall();


        //  String email_message = priceMessage ;


        // startActivity(Intent.createChooser(, "Send Email"));


    }

    /**
     * This method displays the given quantity value on the screen.
     */


    public void sendIntentEmail(String priceMessage) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); //only email app should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "1420ant@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order  Coffee");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }

    private int calculatePrice(boolean hasWhip, boolean hasChoco) {
        int basePrice = 10;

        if (quantity < 0) {
            return 0;

        } else {
            if (hasWhip == true) {
                basePrice = basePrice + 3;
            }

            if (hasChoco == true) {
                basePrice = basePrice + 5;
            }

            return (basePrice * quantity);
        }
    }


    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen........
     */


}
