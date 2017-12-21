package com.example.adams.tradersportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_buy:
                    openFragment(new BuyFragment(),"buy");
                    return true;
                case R.id.navigation_sell:
                    openFragment(new SellFragment(),"sell");
                    return true;
            }
            return false;
        }
    };

    private void openFragment(Fragment fragment, String TAG) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragmentManager.findFragmentByTag("buy") != null) {
            ft.remove(fragmentManager.findFragmentByTag("buy"));
        }
        if (fragmentManager.findFragmentByTag("sell") != null) {
            ft.remove(fragmentManager.findFragmentByTag("sell"));
        }
        ft.replace(R.id.main_container, fragment, TAG);
        ft.commit();
    }

    // Show menu icon on top right corner
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        setTitle("Traders Portal");
        return true;
    }

    // Handle menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_1:
                int id = item.getItemId();
                if (id == R.id.menu_item_1) {
                    firebaseAuth.signOut();
                    //closing activity
                    finish();
                    //starting login activity
                    Toast.makeText(MainActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, SigninActivity.class));
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, SigninActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_buy);
    }

}
