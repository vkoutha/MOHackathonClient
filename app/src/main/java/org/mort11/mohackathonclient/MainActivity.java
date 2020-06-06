package org.mort11.mohackathonclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView loginNavigation = findViewById(R.id.loginNavigation);
        loginNavigation.setOnNavigationItemSelectedListener(loginNavListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.loginFragmentContainer, new LoginFragment()).commit();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if(Client.socket == null) {
            Client.connectToServer();
        }else{
            Client.closeConnection();
            Client.connectToServer();
        }
        Log.d("test", "Connected to server");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener loginNavListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()) {
                        case R.id.login:
                            fragment = new LoginFragment();
                            break;
                        case R.id.register:
                            fragment = new RegisterFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.loginFragmentContainer, fragment).commit();
                    return true;
                }
            };

}
