package com.example.lutemon;

import android.content.Context;

import com.example.lutemon.lutemons.BlackLutemon;
import com.example.lutemon.lutemons.GreenLutemon;
import com.example.lutemon.lutemons.Lutemon;
import com.example.lutemon.lutemons.OrangeLutemon;
import com.example.lutemon.lutemons.PinkLutemon;
import com.example.lutemon.lutemons.WhiteLutemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FileHandler {
    //saves and loads lutemons using json files
    public static void saveLutemons(Context context, List<Lutemon> lutemons) {
        // lutemon -> json object -> json array -> write to file
        JSONArray jsonArray = new JSONArray();

        for (Lutemon lutemon : lutemons) {
            JSONObject jsonObject = lutemonToJsonObject(lutemon);
            jsonArray.put(jsonObject);
        }

        //write to file
        try {
            FileWriter fileWriter = new FileWriter(context.getFilesDir() + "/lutemons.json");
            fileWriter.write(jsonArray.toString(2)); // we make it look cleaner
            fileWriter.close();
            System.out.println("Lutemons saved successfully");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static List<Lutemon> loadLutemons(Context context) throws JSONException {
        ArrayList<Lutemon> lutemons = new ArrayList<>();
        // read and parse json
        String jsonContent = readJsonFile(context);
        JSONArray jsonArray = new JSONArray(jsonContent);

        //create lutemons
        for (int i=0;i<jsonArray.length();i++){
            JSONObject lutemonData = jsonArray.getJSONObject(i);
            String name = lutemonData.getString("name");
            String color = lutemonData.getString("color");
            int experience = lutemonData.getInt("experience");
            int winCounter = lutemonData.getInt("winCounter");
            int battleCounter = lutemonData.getInt("battleCounter");
            int trainingCounter = lutemonData.getInt("trainingCounter");

            //initialize lutemon | if color is invalid create White lutemon
            Lutemon lutemon;
            switch (color){
                case "Black":
                    lutemon = new BlackLutemon(name, experience);
                    break;
                case "Green":
                    lutemon = new GreenLutemon(name, experience);
                    break;
                case "Orange":
                    lutemon = new OrangeLutemon(name, experience);
                    break;
                case "Pink":
                    lutemon = new PinkLutemon(name, experience);
                    break;
                default:
                    lutemon = new WhiteLutemon(name, experience);
                    break;
            }

            // update counters
            lutemon.setBattleCounter(battleCounter);
            lutemon.setWinCounter(winCounter);
            lutemon.setTrainingCounter(trainingCounter);
            // add lutemon to list
            lutemons.add(lutemon);
        }


        return lutemons;
    }


    private static String readJsonFile(Context context) {
        // read the json file and return the content as a string
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = context.getAssets().open("lutemons.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return stringBuilder.toString();
    }


    private static JSONObject lutemonToJsonObject(Lutemon lutemon) {
        // create a json object from a lutemon
        // we need name | color | experience | win | battle | training
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", lutemon.getName());
            jsonObject.put("color", lutemon.getColor());
            jsonObject.put("experience", lutemon.getExperience());
            jsonObject.put("winCounter", lutemon.getWinCounter());
            jsonObject.put("battleCounter", lutemon.getBattleCounter());
            jsonObject.put("trainingCounter", lutemon.getTrainingCounter());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return jsonObject;
    }


}
