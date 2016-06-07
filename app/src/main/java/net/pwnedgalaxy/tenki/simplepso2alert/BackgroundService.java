package net.pwnedgalaxy.tenki.simplepso2alert;

import android.os.AsyncTask;
import android.os.Handler;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tenki on 5/30/2016.
 */
public class BackgroundService extends AsyncTask<String, URL, String>{

    private MainActivity activity;
    public BackgroundService(MainActivity activity){
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
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
        return result;
    }

    protected void onProgressUpdate() {
        //called when the background task makes any progress
    }

    protected void onPreExecute() {
        //called before doInBackground() is started
    }

    @Override
    protected void onPostExecute(String result) {
        //this.textViewToSet.setText(result);

       activity.showNotification(result);
    }

}
