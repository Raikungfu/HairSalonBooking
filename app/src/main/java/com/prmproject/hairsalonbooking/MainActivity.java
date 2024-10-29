package com.prmproject.hairsalonbooking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prmproject.hairsalonbooking.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private static final int PAYMENT_REQUEST_CODE = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        BottomNavigationView navView = binding.navView;

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_cart,
                R.id.navigation_notifications,
                R.id.navigation_profile)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (navController.getCurrentDestination() != null && navController.getCurrentDestination().getId() == itemId) {
                return true;
            }
            navController.popBackStack(itemId, false);
            return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYMENT_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String paymentStatus = data.getStringExtra("payment_status");
                if ("success".equals(paymentStatus)) {

                    showPaymentSuccessDialog();
                } else {
                    showPaymentFailureDialog();
                }
            }
        }
    }

    private void showPaymentSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Thanh toán thành công")
                .setMessage("Cảm ơn bạn đã thanh toán. Đơn hàng của bạn đã được xác nhận.")
                .setPositiveButton("OK", null)
                .show();
    }

    private void showPaymentFailureDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Thanh toán thất bại")
                .setMessage("Có lỗi xảy ra trong quá trình thanh toán. Vui lòng thử lại.")
                .setPositiveButton("OK", null)
                .show();
    }

}
