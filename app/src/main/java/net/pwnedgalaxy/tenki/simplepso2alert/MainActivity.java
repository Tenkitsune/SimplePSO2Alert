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
    }

    /** Called when the user touches the button */
    public void sendMessage(View view) {
        // Do something in response to button click
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pwnedgalaxy.net/pso2/?ship=10"));
        startActivity(myIntent);

        /*try{
            // Create a URL for the desired page
            URL url = new URL("http://www.pwnedgalaxy.net/pso2/?ship=10");

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                str = in.readLine().toString();
                //System.out.println(str);
                // str is one line of text; readLine() strips the newline character(s)
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }*/



        //notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.notification_icon).setContentTitle("My notification").setContentText("Hello World!");


// Sets an ID for the notification
        int mNotificationId = 001;
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
