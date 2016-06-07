package net.pwnedgalaxy.tenki.simplepso2alert;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tenki on 5/30/2016.
 */
public class DataService extends IntentService {

    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";

    private MainActivity activity;
    //public DataService(MainActivity activity){
    //    super("DataService");
    //    this.activity = activity;
    //}

    //Intent i = new Intent(this, MainActivity.class);
    //private MainActivity activity;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service has started", Toast.LENGTH_SHORT).show();
        //return super.onStartCommand(intent,flags,startId);
        return START_STICKY;
    }


    public DataService() {
        super("DataService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    //public DataService(String name) {
    //    super(name);
    //}

    @Override
    protected void onHandleIntent(Intent intent) {
        String dataString = intent.getDataString();

            String result = "";
            try {
                URL url = new URL("http://www.pwnedgalaxy.net/pso2/eq/eqapi.php");

                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = null;

                while ((line = in.readLine()) != null) {
                    //get lines
                    result+=line;
                }
                in.close();


            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }

        //activity.showNotification(result);

        // processing done here….
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(MainActivity.ResponseReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(PARAM_OUT_MSG, result);
        sendBroadcast(broadcastIntent);

            //return result;
    }
    //private BackgroundService background;
    //public AlarmReceiver(BackgroundService background){
    //    this.background = background;
    //}
}
