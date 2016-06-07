package net.pwnedgalaxy.tenki.simplepso2alert;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.SystemClock;
import android.support.annotation.DrawableRes;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private ResponseReceiver receiver;
    private PendingIntent pendingIntent;
    private AlarmManager manager;
    public View view;
    String JSONin = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BackgroundService(this).execute("http://www.pwnedgalaxy.net/pso2/?ship=10");
        //Intent serviceIntent = new Intent(this, DataService.class);
        //startService(serviceIntent);

        // Retrieve a PendingIntent that will perform a broadcast
        //Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        //pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        //this.initiateAlarm(view);

        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);



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
        }


        showNotification(JSONin);*/

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

    /*
    public void initiateAlarm(View view){
        String alarm = Context.ALARM_SERVICE;
        AlarmManager am = ( AlarmManager ) getSystemService( alarm );

        Intent intent = new Intent( "PLS_REFRESH" );
        PendingIntent pi = PendingIntent.getBroadcast( this, 0, intent, 0 );

        int type = AlarmManager.ELAPSED_REALTIME_WAKEUP;
        //long interval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
        long interval = 1000; //1 secs for testing
        long triggerTime = SystemClock.elapsedRealtime() + interval;

        am.setRepeating( type, triggerTime, interval, pi );
    }*/

    public class ResponseReceiver extends BroadcastReceiver{
        public static final String ACTION_RESP =
                "net.pwnedgalaxy.tenki.simplepso2alert.intent.action.MESSAGE_PROCESSED";

        @Override
        public void onReceive(Context context, Intent intent) {

            showNotification(JSONin);

        }

    }
}
