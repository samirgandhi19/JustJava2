/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.res.ConfigurationHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

import static android.R.id.edit;
import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.ACTION_SENDTO;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 0;
    boolean hasWhippedCream;
    String customerName;
    public void submitOrder(View view) {
        int pricePerCup = 5;
        CheckBox whipppedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_topping_checkbox);
        hasWhippedCream = whipppedCreamCheckBox.isChecked();
        EditText order_name = (EditText) findViewById(R.id.orderer_name);
        customerName = order_name.getText().toString();

        if (quantity == 0) {
            displayMessage("Nothing Ordered!");
        }
        else {
            if (hasWhippedCream == false){
                emailOrder(createOrderSummary(customerName, quantity, calculatePrice(quantity,pricePerCup), hasWhippedCream));
            }
            else {
                pricePerCup = pricePerCup + 2;
                emailOrder(createOrderSummary(customerName, quantity, calculatePrice(quantity,pricePerCup), hasWhippedCream));
            }

        }

    }


    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }
    public void decrement(View view) {
        quantity--;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method calculates the price by multiplying pricePerCup and Quantity.
     */
    private int calculatePrice(int quantity, int pricePerCup) {
        return (quantity * pricePerCup);
    }
    private void emailOrder(String body){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your Order Summary");
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private String createOrderSummary (String customerName, int qty, int total, boolean addWhippedCream) {
        String msg = ("Name: " + customerName +
                "\nAdd whipped cream? " + addWhippedCream +
                "\nQuantity: " +
                qty +
                "\nTotal: " + NumberFormat.getCurrencyInstance().format(total) + "\n Thank you!");

        return msg;
    }

    /**
     * This defines a method that manipulates a TextView that has been made in the corresponding .xml file.
     * first you must capture the TextView as an object. then you call a factory method on the objext.
     * End Result: TextView in .xml is ajusted to have the submitted text.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
        Log.i("MainActivity.java", "you have just called the display Message");
    }





}