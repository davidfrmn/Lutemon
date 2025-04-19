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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class FileHandler {
    private static final String FILE_NAME = "lutemons.json";
    //saves and loads lutemons using json files
    public static void saveLutemons(Context context, List<Lutemon> lutemons) {
        // lutemon -> json object -> json array -> write to file
        JSONArray jsonArray = new JSONArray();

        for (Lutemon lutemon : lutemons) {
            JSONObject jsonObject = lutemonToJsonObject(lutemon);
            jsonArray.put(jsonObject);
        }

        try(FileWriter fileWriter = new FileWriter( new File(context.getFilesDir(), FILE_NAME), false)){
            fileWriter.write(jsonArray.toString(2)); // we make it look cleaner
            System.out.println("Lutemons saved successfully");
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static ArrayList<Lutemon> loadLutemons(Context context) throws JSONException {
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
        File file = new File(context.getFilesDir(), FILE_NAME);
        if (!file.exists()) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
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
