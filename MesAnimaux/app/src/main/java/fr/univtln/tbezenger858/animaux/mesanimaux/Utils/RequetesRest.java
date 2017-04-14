package fr.univtln.tbezenger858.animaux.mesanimaux.Utils;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import fr.univtln.tbezenger858.Animaux.Animal;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chat;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chien;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Mammifere;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Aigle;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Oiseau;
import fr.univtln.tbezenger858.Animaux.Oiseaux.Pigeon;

/**
 * Created by Screetts on 11/05/2016.
 */

public class RequetesRest {
    public static String get(String path){
        String url = Config.URL + path;
        try {
            return new Requete().execute(url,"GET").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String put_post(Animal animal,String type){
        ObjectMapper mapper= new ObjectMapper();
        String url = "";
        if (animal.getClass() == Chien.class) {
            url = Config.URL+"/mammiferes/chien";
            System.out.println(url);
        }
        else if (animal.getClass() == Chat.class) {
            url = Config.URL+"/mammiferes/chat";
        }
        else if (animal.getClass() == Pigeon.class) {
            url = Config.URL+"/oiseaux/pigeon";
        }
        else if (animal.getClass() == Aigle.class) {
            url = Config.URL+"/oiseaux/aigle";
        }
        String json = "";
        try {
            json = mapper.writeValueAsString(animal);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        try {
            return new Requete().execute(url,type,json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String delete(Animal animal) {
        String url = Config.URL;
        if (animal.getClass()== Chien.class || animal.getClass()== Chat.class){
            System.out.println(animal);
            url+="/mammiferes/"+Integer.toString(animal.getId());
        }
        else if (animal.getClass()== Aigle.class || animal.getClass()== Pigeon.class){
            System.out.println(animal);
            url+="/oiseaux/"+Integer.toString(animal.getId());
        }
        try {
            System.out.println("appel a REQUETE()"+url);
            return new Requete().execute(url,"DEL").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }


    private static class Requete extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... params) {
            String returnValue = "";
            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (params[1]=="GET") {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                else if(params[1]=="PUT" || params[1]=="POST"){
                    // params 2 doit etre un json
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setRequestMethod(params[1]);
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    BufferedOutputStream output = new BufferedOutputStream(urlConnection.getOutputStream());
                    System.out.println("param2"+params[2]);
                    output.write(params[2].getBytes());
                    output.flush();
                    returnValue = urlConnection.getResponseMessage();
                }
                else if (params[1]=="DEL"){
                    System.out.println("lancement de la requete");
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setRequestMethod("DELETE");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                urlConnection.disconnect();
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
            return returnValue;
        }

    }
}
