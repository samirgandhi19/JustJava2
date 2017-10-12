/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */
package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.NumberFormat;

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
    int pricePerCup = 5;
    String customerName = "Samir";
    public void submitOrder(View view) {
        String priceMessage;

        if (quantity == 0) {
            displayMessage("Nothing Ordered!");
        }
        else {
            displayMessage(createOrderSummary(customerName, quantity, calculatePrice(quantity,pricePerCup)));
        }
    }

    public void goToLogin(View view) {
        String priceMessage;

        if (quantity == 0) {
            displayMessage("Nothing Ordered!");
        }
        else {
            displayMessage(createOrderSummary(customerName, quantity, calculatePrice(quantity,pricePerCup)));
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

    private String createOrderSummary (String customerName, int qty, int total) {
        String msg = ("Name: " + customerName + "\nQuantity: " + qty + "\nTotal: " + NumberFormat.getCurrencyInstance().format(total) + "\n Thank you!");
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