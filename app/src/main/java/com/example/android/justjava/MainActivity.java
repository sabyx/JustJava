
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

 package com.example.android.justjava;



         import android.content.Context;
         import android.content.Intent;
         import android.net.Uri;
         import android.os.Bundle;
         import android.support.v7.app.AppCompatActivity;
         import android.util.Log;
         import android.view.View;
         import android.widget.CheckBox;
         import android.widget.EditText;
         import android.widget.TextView;
         import android.widget.Toast;

         import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    boolean hasWhippedCream = false;
    boolean hasChocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void composeEmail(String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//        displayMessage(createOrderSummary(calculatePrice()));
        String subject = getResources().getString(R.string.subject) + " " + getNameText();
        String body = createOrderSummary(calculatePrice());
        composeEmail(subject, body);
    }

    public void setWhippedCream(View view){
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        hasWhippedCream = whippedCream.isChecked();
    }

    public void setChocolate(View view){
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkBox);
        hasChocolate = chocolate.isChecked();
    }

    private String getNameText(){
        EditText nameHint = (EditText) findViewById(R.id.name_text);
        String nameValue =  nameHint.getText().toString();
        return nameValue;
    }


    private String createOrderSummary(int price){
        String priceMessage = getResources().getString(R.string.oredr_name) + " "  + getNameText();
        priceMessage += "\n" + getResources().getString(R.string.whipped_cream) + " "  + hasWhippedCream;
        priceMessage += "\n" + getResources().getString(R.string.chocolate) + " "  + hasChocolate;
        priceMessage += "\n" + getResources().getString(R.string.order_quantity) + " "  + quantity;
        priceMessage += "\n" + getResources().getString(R.string.order_total) + price;
        priceMessage += "\n" + getResources().getString(R.string.order_thank_you);
        return priceMessage;
    }
    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        int coffeePrice = 5;
        if (hasWhippedCream){
            coffeePrice += 1;
        }
        if (hasChocolate) {
            coffeePrice += 2;
        }
        return quantity * coffeePrice;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            showToast(getResources().getString(R.string.toast_increment));
        } else {
            quantity = quantity + 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            showToast(getResources().getString(R.string.toast_decrement));
        } else {
            quantity = quantity - 1;
        }
        displayQuantity(quantity);
    }

    private void showToast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(message);
//    }
}