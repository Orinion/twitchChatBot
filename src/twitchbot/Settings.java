/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitchbot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Jakob
 */
public class Settings {
    String Filename;
    Properties settings = new Properties();
    public Settings(String Filename) {
        this.Filename = Filename;
        try {
            settings.load(new FileInputStream(Filename));
        } 
        catch (FileNotFoundException ex) {}
        catch (IOException ex) {}
    }
    public String readSetting(String key)
    {
        if(!settings.containsKey(key))
            settings.setProperty(key, "");
        return settings.getProperty(key);
        
    }
    public void saveSetting(String key,String Setting)
    {
        try {
            settings.setProperty(key, Setting);
            settings.store(new FileOutputStream(Filename), "Settings for TwitchClient");
         }
        catch (FileNotFoundException ex) {}
        catch (IOException ex) {}
    }
}
