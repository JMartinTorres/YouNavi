package com.example.usuario.younavi;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // URL shared from YouTube app
        Bundle ytBundle = getIntent().getExtras();
        String url = "";

        if (ytBundle != null) {
            url = ytBundle.getString(Intent.EXTRA_TEXT);
            openChromeWithUrl(url);
        }

        finishAndRemoveTask();

    }

    private void openChromeWithUrl(String url) {

        finishAndRemoveTask();

        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");
        try {
            getApplicationContext().startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            // Chrome browser presumably not installed so allow user to choose instead
            intent.setPackage(null);
            getApplicationContext().startActivity(intent);
        }

    }
}
