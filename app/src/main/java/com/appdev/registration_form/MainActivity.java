package com.appdev.registration_form;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.appdev.registration_form.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        binding.showDataBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,showActivity.class)));
        binding.loginBtn.setOnClickListener(v -> {
            CheckBox[] checkboxes = {
                    binding.checkboxJava,
                    binding.checkboxKotlin,
                    binding.checkboxAndroid,
                    binding.checkboxXml,
                    binding.checkboxFirebase
                    // Add more checkboxes if needed
            };
            List<String> skills = new ArrayList<>();
            for (CheckBox checkbox : checkboxes) {
                if (checkbox.isChecked()) {
                    skills.add(checkbox.getText().toString());
                } else if(!checkbox.isChecked()){
                    skills.remove(checkbox.getText().toString());
                }
            }
            String country = binding.spinner.getText().toString();
            int genderRadioButtonId = binding.dailyWeeklyButtonView.getCheckedRadioButtonId();
            RadioButton genderRadioButton = findViewById(genderRadioButtonId);
            String gender = genderRadioButton.getText().toString();
            String name = Objects.requireNonNull(binding.suUser.getText()).toString();
            if (!country.isEmpty() && !skills.isEmpty() && !name.isEmpty()) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.loginBtn.setEnabled(false);
                UserInfo userInfo = new UserInfo(name, skills, country, gender);
                myRef.push().setValue(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        binding.progressBar.setVisibility(View.GONE);
                        binding.loginBtn.setEnabled(true);
                        binding.suUser.setText("");
                        for (CheckBox checkbox : checkboxes) {
                            checkbox.setChecked(false);
                        }
                        binding.spinner.clearSelectedItem();
                        showToast("Data has been Saved Successfully !");
                    }
                });
            } else if (name.isEmpty()) {
                showToast("Please enter your username !");
            } else if (skills.isEmpty()) {
                showToast("Select at least one skill !");
            } else if (country.isEmpty()) {
                showToast("Select Country !");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}