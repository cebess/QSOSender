package com.cebess.qsocreator;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import android.util.Log;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.io.IOException;
import java.util.NoSuchElementException;

public class MainActivity extends AppCompatActivity {

    public static final String ProjectName = "com.cebess.qsocreator";
    public static Boolean DEBUG = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    // Declare the User Interface elements
    EditText generatedQSOEditText;
    EditText XmitSpeededitText;
    Button generateButton;

    int XmitSpeed = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define the graphic elements
        generatedQSOEditText = (EditText)findViewById(R.id.generatedQSOEditText);
        XmitSpeededitText = (EditText)findViewById(R.id.XmitSpeededitText);
        generateButton = (Button)findViewById(R.id.generateButton);

        //install listeners
        generateButton.setOnClickListener(btnConnectListener);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        try {
            XmitSpeed = Integer.parseInt(XmitSpeededitText.getText().toString());
            if (XmitSpeed < 5 || XmitSpeed > 50) {
                //display in short period of time
                Toast.makeText(getApplicationContext(), "The transmit speed must be between 5 and 50, inclusive.", Toast.LENGTH_SHORT).show();
                XmitSpeed = 5;
                XmitSpeededitText.setText("5");
            } else {
                RandomQSO myQSO = new RandomQSO();
                String QSOString = myQSO.getQSO(XmitSpeed);
                generatedQSOEditText.setText(QSOString);
            }
        } catch (IOException e) {
            Log.e(MainActivity.ProjectName,"IO exception: " + e.getMessage());
            finish();
        } catch (java.text.ParseException e) {
            Log.e(MainActivity.ProjectName,"Parse exception: " + e.getMessage());
            finish();
        }

    }

    private OnClickListener btnConnectListener = new OnClickListener() {
        public void onClick(View v){
            try {
                XmitSpeed = Integer.parseInt(XmitSpeededitText.getText().toString());
                if (XmitSpeed < 5 || XmitSpeed > 50) {
                    //display in short period of time
                    Toast.makeText(getApplicationContext(), "The transmit speed must be between 5 and 50, inclusive.", Toast.LENGTH_SHORT).show();
                    XmitSpeed = 5;
                    XmitSpeededitText.setText("5");
                } else {
                    RandomQSO myQSO = new RandomQSO();
                    String QSOString = myQSO.getQSO(XmitSpeed);
                    generatedQSOEditText.setText(QSOString);
                }
            } catch (IOException e) {
                Log.e(MainActivity.ProjectName,"IO exception: " + e.getMessage());
                finish();
            } catch (java.text.ParseException e) {
                Log.e(MainActivity.ProjectName,"Parse exception: " + e.getMessage());
                finish();
            }
        }
    };

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
