package org.mort11.mohackathonclient;

import android.content.Intent;
import android.os.Bundle;
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
                    boolean success = Client.login(!accountTypeSwitch.isChecked() ? "ADMIN" : "STUDENT",
                            username.getEditableText().toString(),
                            password.getEditableText().toString());
                    if (!success) {
                        Toast.makeText(getContext(), "Account info not found! Try again", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "Successfully logged in", Toast.LENGTH_SHORT).show();
                        String json = Client.getInputStream().readUTF();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    private void login(String accountType, String json){
        Intent intent;
        if(accountType.equals("ADMIN")){
            intent = new Intent(getActivity(), AdminActivity.class);
        }else{
            intent = new Intent(getActivity(), StudentActivity.class);
        }
        intent.putExtra("json", json);
        startActivity(intent);
    }

}
