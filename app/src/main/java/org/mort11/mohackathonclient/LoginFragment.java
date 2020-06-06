package org.mort11.mohackathonclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.mort11.mohackathonclient.admin.AdminActivity;
import org.mort11.mohackathonclient.student.StudentActivity;

import java.io.IOException;

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        final Switch accountTypeSwitch = view.findViewById(R.id.loginAccountTypeSwitch);
        final EditText username = view.findViewById(R.id.usernameLoginEditText);
        final EditText password = view.findViewById(R.id.passwordLoginEditText);
        Button loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Log.d("test", !accountTypeSwitch.isChecked() ? "ADMIN" : "STUDENT");
                    Log.d("test", username.getEditableText().toString());
                    Log.d("test", password.getEditableText().toString());
                    System.out.println("CLICKINGGGGG");
                    Client.login(!accountTypeSwitch.isChecked() ? "ADMIN" : "STUDENT",
                            username.getEditableText().toString(),
                            password.getEditableText().toString());
                    Log.d("test", "GOT TO HEREEEE");
                    while(!Client.serverRecievedLogin)
                        ;
                    if (!Client.accountFoundFromLogin) {
                        Toast.makeText(getContext(), "Account info not found! Try again", Toast.LENGTH_SHORT).show();
//                        Client.closeConnection();
//                        Client.connectToServer();
                        Log.d("test", "account info not found");
                        Client.serverRecievedLogin = false;
                        Client.accountFoundFromLogin = false;
                        Client.loginJSONReceived = false;
                        Client.loginJSON = "";
                    } else {
                        Toast.makeText(getContext(), "Successfully logged in", Toast.LENGTH_SHORT).show();
                        String json = Client.getLoginJSON();
                        Log.d("test", "Received json: " + json);
                        login(!accountTypeSwitch.isChecked() ? "ADMIN" : "STUDENT", json);
                    }
                    Client.serverRecievedLogin = false;
                    Client.accountFoundFromLogin = false;
                    Client.loginJSONReceived = false;
                    Client.loginJSON = "";
                }catch (Exception e){
                    e.printStackTrace();
                    Client.closeConnection();
                    Client.connectToServer();
                    Client.serverRecievedLogin = false;
                    Client.accountFoundFromLogin = false;
                    Client.loginJSONReceived = false;
                    Client.loginJSON = "";
                }
            }
        });
        return view;
    }

    private void login(String accountType, String json){
        Log.d("test", "Changing activities now!!");
        Intent intent;
        if(accountType.equals("ADMIN")){
            intent = new Intent(getActivity(), AdminActivity.class);
        }else{
            intent = new Intent(getActivity(), StudentActivity.class);
        }
        Client.serverRecievedLogin = false;
        Client.accountFoundFromLogin = false;
        Client.loginJSONReceived = false;
        Client.loginJSON = "";
        intent.putExtra("json", json);
        startActivity(intent);
    }

}
