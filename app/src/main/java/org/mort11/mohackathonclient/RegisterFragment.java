package org.mort11.mohackathonclient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.mort11.mohackathonclient.admin.Admin;
import org.mort11.mohackathonclient.student.Student;

import java.io.IOException;

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.register_fragment, container, false);
        final TextView teachersTextView = view.findViewById(R.id.teachersListTextView);
        final Spinner teacherSpinners = view.findViewById(R.id.teacherListSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.teacherNames, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        teacherSpinners.setAdapter(adapter);
        final Switch accountTypeSwitch = view.findViewById(R.id.registerUserTypeSwitch);
        final EditText fullName = view.findViewById(R.id.fullNameRegisterEditText);
        final EditText username = view.findViewById(R.id.usernameRegisterEditText);
        final EditText password = view.findViewById(R.id.passwordRegisterEditText);
        accountTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    teachersTextView.setVisibility(View.INVISIBLE);
                    teacherSpinners.setVisibility(View.INVISIBLE);
                }else{
                    teachersTextView.setVisibility(View.VISIBLE);
                    teacherSpinners.setVisibility(View.VISIBLE);
                }
            }
        });
        Button registerButton = view.findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    String json = "";
                    if(!accountTypeSwitch.isChecked()){
                        Admin adminAccount = new Admin(fullName.getEditableText().toString(),
                                username.getEditableText().toString(), password.getEditableText().toString());
                        json = gson.toJson(adminAccount);
                    }else{
                        Student studentAccount = new Student(fullName.getEditableText().toString(),
                                username.getEditableText().toString(), password.getEditableText().toString(), teacherSpinners.getSelectedItem().toString());
                        json = gson.toJson(studentAccount);
                    }
                    Client.register(!accountTypeSwitch.isChecked() ? "ADMIN" : "STUDENT", json);
                    Toast.makeText(getContext(), "Account created!", Toast.LENGTH_LONG).show();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

}
