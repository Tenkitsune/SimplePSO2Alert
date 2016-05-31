package net.pwnedgalaxy.tenki.simplepso2alert;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BackgroundService(this).execute("http://www.pwnedgalaxy.net/pso2/?ship=10");
    }

    /** Called when the user touches the butt */
    public void sendMessage(View view) throws JSONException {
        // Do something in response to button click
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pwnedgalaxy.net/pso2/?ship=10"));
        startActivity(myIntent);

        //should put this in a new class...not going to rn. rip


        String JSONin = "";


        //set up a connection to the page

        /*try {
            // Create a URL for the desired page
            URL url = new URL("http://www.pwnedgalaxy.net/pso2/eq/eqapi.php");

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                JSONin += str + "\n";
            }
            in.close();
        } catch (MalformedURLException e) {
            // leave this blank, should only happen if you fuck up the URL
        } catch (IOException e) {
            // Internet died or Pwned Galaxy died
        }*/


        //showNotification(JSONin);

    }

    public void showNotification(String JSON)
    {
        boolean upcomingJSON = false;
        boolean inProgressJSON = false;

        try {
            JSONObject reader = new JSONObject(JSON);

            upcomingJSON = reader.getBoolean("upcoming");
            inProgressJSON = reader.getBoolean("inProgress");

            if(upcomingJSON)
            {
                //notification
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.notification_icon).setContentTitle("PSO2 EQ Notification").setContentText("An EQ is coming up in the next hour!");

                // Sets an ID for the notification
                int mNotificationId = 001;
                // Gets an instance of the NotificationManager service
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // Builds the notification and issues it.
                mNotifyMgr.notify(mNotificationId, mBuilder.build());
            }

            if(inProgressJSON)
            {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.notification_icon).setContentTitle("PSO2 EQ Notification").setContentText("An EQ is going on right now!");

                // Sets an ID for the notification
                int mNotificationId = 001;
                // Gets an instance of the NotificationManager service
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // Builds the notification and issues it.
                mNotifyMgr.notify(mNotificationId, mBuilder.build());
            }

            else
            {

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
