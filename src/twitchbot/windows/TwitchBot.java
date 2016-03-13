/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitchbot.windows;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import twitchbot.ircClient;
import java.awt.Desktop;
import java.net.URI;
import javafx.stage.WindowEvent;
import twitchbot.Settings;

/**
 *
 * @author jakob.greuel
 */
public class TwitchBot extends Application {

    public ircClient myClient;
    String Username;
    Settings mySetting;

    @Override
    public void start(Stage stage) throws Exception {
        mySetting = new Settings("settings.ini");
        if (!mySetting.readSetting("Username").equals("")) 
            getAuthtoken(mySetting.readSetting("Username"));
        else
            openLoginWindow(stage);
    }

    public void openLoginWindow(Stage pStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Login.fxml"));
            Parent p;

            p = fxmlLoader.load();

            FXML_LoginController controll = (FXML_LoginController) fxmlLoader.getController();

            controll.setMain(this);
            Scene scene = new Scene(p);
            pStage.setScene(scene);
            pStage.show();
        } catch (IOException ex) {
        }
    }

    public void openConnectWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Connect.fxml"));
        try {
            Parent p = fxmlLoader.load();
            FXML_ConnectController controll = (FXML_ConnectController) fxmlLoader.getController();
            controll.setMain(this);
            controll.initialize(null, null);
            Scene scene = new Scene(p);
            Stage pStage = new Stage();
            pStage.setScene(scene);
            pStage.show();
        } catch (IOException ex) {

        }
    }

    public void getAuthtoken(String username) {
        mySetting.saveSetting("Username", username);
        this.Username = username;
        if (!mySetting.readSetting("Token").equals("")) 
        {
            connect(mySetting.readSetting("Token"));
            return;
        }
        openConnectWindow();
    }

    public void connect(String authcode) 
    {
        mySetting.saveSetting("Token", authcode);
        myClient = new ircClient("irc.twitch.tv", 6667, this.Username, authcode);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Chat.fxml"));
        try {
            Parent p = fxmlLoader.load();
            FXML_ChatController controll = (FXML_ChatController) fxmlLoader.getController();

            controll.setMain(this);
            Scene scene = new Scene(p);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle(Username);
            stage.setOnCloseRequest((WindowEvent we) -> {
                myClient.close();
            });
        } catch (IOException ex) {}
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
