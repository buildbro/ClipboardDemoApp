package com.buildbrothers.clipboarddemoapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView1, textView2;
    private EditText textField;

    private TextView copyBtn1, copyBtn2, pasteBtn, clearBtn;

    private ClipboardManager mClipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        initializeUI();

        copyBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyText(v);
            }
        });

        copyBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyText(v);
            }
        });

        pasteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteText();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
            }
        });

    }

    //Initialize all the views this activity will use.
    private void initializeUI() {
        textView1 = findViewById(R.id.first_text);
        textView2 = findViewById(R.id.second_text);
        textField = findViewById(R.id.text_field);

        copyBtn1 = findViewById(R.id.copy1);
        copyBtn2 = findViewById(R.id.copy2);
        pasteBtn = findViewById(R.id.paste_btn);
        clearBtn = findViewById(R.id.clear_btn);
    }

    //reads text from either textviews and add it to the device clipboard
    public void copyText(View view) {
        String currentText;
        if (view.getId() == R.id.copy1) {
            currentText = textView1.getText().toString();
        } else if (view.getId() == R.id.copy2) {
            currentText = textView2.getText().toString();
        } else {
            currentText = null;
        }

        if (currentText != null) {
            mClipboardManager.setPrimaryClip(ClipData.newPlainText("text", currentText));
            Toast.makeText(this, "Text Copied!", Toast.LENGTH_SHORT).show();
        }
    }

    //reads text from the device clipboard and set it as current text for our editText
    private void pasteText() {
        String clipboardText;

        clipboardText = mClipboardManager.getPrimaryClip().getItemAt(0)
                .coerceToText(getApplicationContext()).toString();

        textField.setText(clipboardText);
    }

    //clears our editText by setting its content to ""
    private void clearText() {
        textField.setText("");
    }
}
