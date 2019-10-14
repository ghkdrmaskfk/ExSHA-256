package com.example.ex_sha_256;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button encryptBtn, okBtn, removeBtn;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();

        encryptBtn.setOnClickListener(this);
        okBtn.setOnClickListener(this);
        removeBtn.setOnClickListener(this);
    }

    private void findViewById() {

        textView = findViewById(R.id.text_view);

        editText = findViewById(R.id.edit_text);

        encryptBtn = findViewById(R.id.encrypt_btn);
        okBtn = findViewById(R.id.ok_btn);
        removeBtn = findViewById(R.id.remove_btn);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.encrypt_btn:

                try {

                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = digest.digest(editText.getText().toString().getBytes("UTF-8"));
                    StringBuffer hexString = new StringBuffer();

                    for (int i = 0; i < hash.length; i ++) {

                        String hex = Integer.toHexString(0xff & hash[i]);
                        if (hex.length() == 1) hexString.append('0');
                        hexString.append(hex);

                    }

                    textView.setText(hexString.toString());

                } catch (Exception e) { e.printStackTrace(); }

                break;

            case R.id.remove_btn:

                editText.setText("");

                break;

            case R.id.ok_btn:

                textView.setText(editText.getText());

                break;
        }
    }
}
