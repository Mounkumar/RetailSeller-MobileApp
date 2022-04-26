package java.ac.uk.tees.w9547666.retailstoreapplication.view.activities;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.ac.uk.tees.w9547666.retailstoreapplication.R;
import java.ac.uk.tees.w9547666.retailstoreapplication.model.Repository;
import java.ac.uk.tees.w9547666.retailstoreapplication.model.entities.Money;
import java.ac.uk.tees.w9547666.retailstoreapplication.model.entities.Product;
import java.ac.uk.tees.w9547666.retailstoreapplication.util.Utils;
import java.ac.uk.tees.w9547666.retailstoreapplication.util.Utils.AnimationType;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.fragment.HomeFragment;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author w9547666
 */

public class HomeActivity extends AppCompatActivity {

    private int itemCount = 0;
    private BigDecimal checkoutAmount = new BigDecimal(BigInteger.ZERO);
    private DrawerLayout mDrawerLayout;

    private TextView checkOutAmount, itemCountTextView;
    private TextView offerBanner;

    private ContentLoadingProgressBar progressBar;

    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        itemCount = Repository.getCenterRepository().getListOfProductsInShoppingList()
                .size();



        offerBanner = ((TextView) findViewById(R.id.new_offers_banner));

        itemCountTextView = (TextView) findViewById(R.id.item_count);
        itemCountTextView.setSelected(true);
        itemCountTextView.setText(String.valueOf(itemCount));

        checkOutAmount = (TextView) findViewById(R.id.checkout_amount);
        checkOutAmount.setSelected(true);
        checkOutAmount.setText(Money.rupees(checkoutAmount).toString());
        offerBanner.setSelected(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        progressBar = (ContentLoadingProgressBar) findViewById(R.id.loading_bar);

        checkOutAmount.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Utils.vibrate(getApplicationContext());

                Utils.switchContent(R.id.frag_container,
                        Utils.SHOPPING_LIST_TAG, HomeActivity.this,
                        AnimationType.SLIDE_UP);

            }
        });


        if (itemCount != 0) {
            for (Product product : Repository.getCenterRepository()
                    .getListOfProductsInShoppingList()) {

                updateCheckOutAmount(
                        BigDecimal.valueOf(Long.valueOf(product.getSellMRP())),
                        true);
            }
        }

        findViewById(R.id.item_counter).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Utils.vibrate(getApplicationContext());
                        Utils.switchContent(R.id.frag_container,
                                Utils.SHOPPING_LIST_TAG,
                                HomeActivity.this, AnimationType.SLIDE_UP);

                    }
                });

        findViewById(R.id.checkout).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Utils.vibrate(getApplicationContext());

                        showPurchaseDialog();

                    }
                });

        Utils.switchFragmentWithAnimation(R.id.frag_container,
                new HomeFragment(), this, Utils.HOME_FRAGMENT,
                AnimationType.SLIDE_UP);

        toggleBannerVisibility();

        mNavigationView
                .setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.home:

                                mDrawerLayout.closeDrawers();

                                Utils.switchContent(R.id.frag_container,
                                        Utils.HOME_FRAGMENT,
                                        HomeActivity.this,
                                        AnimationType.SLIDE_LEFT);

                                return true;

                            case R.id.my_cart:

                                mDrawerLayout.closeDrawers();

                                Utils.switchContent(R.id.frag_container,
                                        Utils.SHOPPING_LIST_TAG,
                                        HomeActivity.this,
                                        AnimationType.SLIDE_LEFT);
                                return true;

                            case R.id.contact_us:

                                mDrawerLayout.closeDrawers();

                                Utils.switchContent(R.id.frag_container,
                                        Utils.CONTACT_US_FRAGMENT,
                                        HomeActivity.this,
                                        AnimationType.SLIDE_LEFT);
                                return true;

                            case R.id.settings:

                                mDrawerLayout.closeDrawers();

                                Utils.switchContent(R.id.frag_container,
                                        Utils.SETTINGS_FRAGMENT_TAG,
                                        HomeActivity.this,
                                        AnimationType.SLIDE_LEFT);
                                return true;
                            default:
                                return true;
                        }
                    }
                });

    }

    public ContentLoadingProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateItemCount(boolean ifIncrement) {
        if (ifIncrement) {
            itemCount++;
            itemCountTextView.setText(String.valueOf(itemCount));

        } else {
            itemCountTextView.setText(String.valueOf(itemCount <= 0 ? 0
                    : --itemCount));
        }

        toggleBannerVisibility();
    }

    public void updateCheckOutAmount(BigDecimal amount, boolean increment) {

        if (increment) {
            checkoutAmount = checkoutAmount.add(amount);
        } else {
            if (checkoutAmount.signum() == 1)
                checkoutAmount = checkoutAmount.subtract(amount);
        }

        checkOutAmount.setText(Money.rupees(checkoutAmount).toString());
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Store Shopping Cart in DB
        /*new TinyDB(getApplicationContext()).putListObject(
                PreferenceHelper.MY_CART_LIST_LOCAL, Repository
                        .getCenterRepository().getListOfProductsInShoppingList());*/
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /*
     * Toggles Between Offer Banner and Checkout Amount. If Cart is Empty SHow
     * Banner else display total amount and item count
     */
    public void toggleBannerVisibility() {
        if (itemCount == 0) {

            findViewById(R.id.checkout_item_root).setVisibility(View.GONE);
            findViewById(R.id.new_offers_banner).setVisibility(View.VISIBLE);

        } else {
            findViewById(R.id.checkout_item_root).setVisibility(View.VISIBLE);
            findViewById(R.id.new_offers_banner).setVisibility(View.GONE);
        }
    }

    /*
     * get total checkout amount
     */
    public BigDecimal getCheckoutAmount() {
        return checkoutAmount;
    }


    /*
     * Get Number of items in cart
     */
    public int getItemCount() {
        return itemCount;
    }

    /*
     * Get Navigation drawer
     */
    public DrawerLayout getmDrawerLayout() {
        return mDrawerLayout;
    }


    public void showPurchaseDialog() {

        AlertDialog.Builder exitScreenDialog = new AlertDialog.Builder(HomeActivity.this, R.style.PauseDialog);

        exitScreenDialog.setTitle("Order Confirmation")
                .setMessage("Would you like to place this order ?");
        exitScreenDialog.setCancelable(true);

        exitScreenDialog.setPositiveButton(
                "Place Order",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //finish();
                        dialog.cancel();

                        ArrayList<String> productId = new ArrayList<String>();

                        for (Product productFromShoppingList : Repository.getCenterRepository().getListOfProductsInShoppingList()) {

                            //add product ids to array
                            productId.add(productFromShoppingList.getProductId());
                        }

                        //pass product id array to Apriori ALGO
                        Repository.getCenterRepository()
                                .addToItemSetList(new HashSet<>(productId));



                        //clear all list item
                        Repository.getCenterRepository().getListOfProductsInShoppingList().clear();

                        toggleBannerVisibility();

                        itemCount = 0;
                        itemCountTextView.setText(String.valueOf(0));
                        checkoutAmount = new BigDecimal(BigInteger.ZERO);
                        checkOutAmount.setText(Money.rupees(checkoutAmount).toString());

                    }
                });

        exitScreenDialog.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        exitScreenDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Snackbar.make(HomeActivity.this.getWindow().getDecorView().findViewById(android.R.id.content)
                        , "Order Placed Successfully, Happy Shopping !!", Snackbar.LENGTH_LONG);

            }
        });

        AlertDialog alert11 = exitScreenDialog.create();
        alert11.show();

    }

}
