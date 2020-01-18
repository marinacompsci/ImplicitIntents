package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openWebsite(View view) {
        EditText messageWebsiteEditText;

        messageWebsiteEditText = findViewById(R.id.website_edittext);
        String url = messageWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    public void openLocation(View view) {
        EditText messageLocationEditText;
        messageLocationEditText = findViewById(R.id.location_edittext);
        String loc = messageLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    public void shareText(View view) {
        EditText messageShareEditText;
        messageShareEditText = findViewById(R.id.share_edittext);
        String txt = messageShareEditText.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }
}
