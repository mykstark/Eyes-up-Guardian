package com.example.mstark.destiny;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask <Void, Void, Void> {
    String jsonInString;
    String chicken;
//    String key;
//
//    public String getStrings(Context context){
//
//       String key = context.getString(R.string.key);
//       Log.i("Mykal", key);
//       this.key = key;
//
//       return key;
//    }


    @Override
    public Void doInBackground(Void... voids) {
        try {
            String apiKey = MainActivity.key;
            URL url = new URL("https://www.bungie.net/platform/Destiny/Manifest/InventoryItem/1274330687/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("X-API-KEY", apiKey);
            InputStream inputStream = httpURLConnection.getInputStream();

            //Read and build Json from api into String
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            //Turn sb into Json
            jsonInString = sb.toString();


            //Turn jsonInString into jObject
             JSONObject jobject = new JSONObject(sb.toString());

             Log.i("Mykal", jobject.toString());

            JSONObject response = jobject.getJSONObject("Response");
            JSONObject data = response.getJSONObject("data");
            JSONObject inventoryItem = data.getJSONObject("inventoryItem");
            //Pulling items from inventoryItem
            String itemName = "ItemName: " + inventoryItem.getString("itemName");
            String itemDescription = "Description: " + inventoryItem.getString("itemDescription");
            String tier = "Rarity: " + inventoryItem.getString("tierTypeName");

            chicken = itemName + System.lineSeparator() + itemDescription + System.lineSeparator() + tier;




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(chicken);
    }

}
