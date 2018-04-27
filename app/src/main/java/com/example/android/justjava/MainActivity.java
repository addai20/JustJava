/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.hardware.camera2.params.BlackLevelPattern;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toast.makeText(this, "Hello, welcome to Just Java Coffee Shop!", Toast.LENGTH_LONG).show();


        }

        /**
         * VideoView videoView = (VideoView) findViewById(R.id.coffeeVideoView);
         MediaController mediaController = new MediaController(this);
         mediaController.setAnchorView(videoView);
         String uriPath = "android.resource://com.example.JustJava/" + R.raw.coffeevideoview;
         Uri video = Uri.parse(uriPath);
         videoView.setMediaController(mediaController);
         videoView.setVideoURI(video);
         videoView.start();
         *
         * NOTES: THE ABOVE CODE IS EXPERIMENTAL AND AIMS TO INCLUDE A VideoView WITHIN THE APP DEPICTING A COFFEE BEAN FARM
         */



    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(String nameField) {

        /**String priceMessage = "Thank you!";
         displayMessage(priceMessage);*/


        /** The below code is used to print to the log and see if the above variable works correctly by returning a Boolean Value
         * Log.v("MainActivity" , "Has whipped cream: " + hasWhippedCream);
         */

        EditText text = (EditText) findViewById(R.id.editText);
        nameField = text.getText().toString();

        // Returns true is user selected whipped cream as a topping

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedcream);
        Boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Returns true if user selected chocolate as a topping

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        Boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasChocolate, hasWhippedCream);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, nameField) + "\n" + "Thank you!";
        displayMessage(priceMessage);
    }

    /**
     * Intent intent = new Intent(Intent.ACTION_SENDTO);
     intent.setData(Uri.parse("mailto:")); // only email apps should handle this
     intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for: " + nameField);
     intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
     if (intent.resolveActivity(getPackageManager()) != null) {
     startActivity(intent);
     }
     */

    /**
     * Calculates the price of the order based on the current quantity.
     *@param hasChocolate whether or not customer wants chocolate in their coffee
     *@param hasWhippedCream  whether or not customer wants wwhipped cream in their coffee
     *
     * @return the total price
     */
    private int calculatePrice(boolean hasChocolate, boolean hasWhippedCream) {
        // base price of coffee @ $5 per cup
        int basePrice = 5;
        // whipped cream in coffee? T/F
        if (hasWhippedCream) {
            basePrice = basePrice + 1;
        }
        // chocolate in coffee? T/F
        if (hasChocolate) {
            basePrice = basePrice + 2;
        }
        return quantity *5;
    }

    /**
     *
     * @param price - the price of the order
     * @param hasWhippedCream - whether or not the customer prefers whipped cream
     * @param hasChocolate - whether or not the customer prefers chocolate
     * @param nameField - name of the customer
     * @return Text Summary
     */

    public String createOrderSummary(int price, Boolean hasWhippedCream, Boolean hasChocolate, String nameField) {

        String priceMessage = "Name: " + nameField + "\n";
        priceMessage += "Whipped cream added?: " + hasWhippedCream + "\n";
        priceMessage += "Chocolate added?: " + hasChocolate + "\n";
        priceMessage += "Quantity: " + quantity + "\n";
        priceMessage += "Total: $" + price;
        return priceMessage;

    }

    /**
     * This method is called when the (+) button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this,"You cannot have more that 100 coffees",Toast.LENGTH_LONG).show();
            return;

        }
        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    /**
     * This method is called when the (-) button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this,"You cannot have less than 1 coffee",Toast.LENGTH_LONG).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);

    }
}