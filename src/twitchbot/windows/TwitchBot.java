/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitchbot.windows;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitchbot.windows.FXML_LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import twitchbot.ircClient;
import twitchbot.windows.FXML_ConnectController;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
/**
 *
 * @author jakob.greuel
 */
public class TwitchBot extends Application
{
    public ircClient myClient;
    String Username;
    String Password;
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Login.fxml"));
        Parent p = fxmlLoader.load();
        FXML_LoginController controll = (FXML_LoginController)fxmlLoader.getController();

        controll.setMain(this);
        Scene scene = new Scene(p);
        stage.setScene(scene);
        stage.show();
        
    }

    public void getAuthtoken(String username, String password)
    {
        this.Username = username;
        this.Password = password;
        if(!Desktop.isDesktopSupported())
            return;
        try {
            Desktop.getDesktop().browse(new URI("https://api.twitch.tv/kraken/oauth2/authorize"
                    + "?response_type=token"
                    + "&client_id=nhtjgrmje2p662nifmn8u4fnp6i16af"
                    + "&redirect_uri=https://github.com/Orinion/twitchChatBot/"
                    + "&scope=chat_login"));
        } catch (Exception ex) {
           
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Connect.fxml"));
        Parent p;
        try {
            p = fxmlLoader.load();
            FXML_ConnectController controll = (FXML_ConnectController)fxmlLoader.getController();
 
            controll.setMain(this);
            Scene scene = new Scene(p);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            
        }
        
    }

    public void connect(String ip,int Port,String authcode)
    {
        myClient = new ircClient(ip, Port,this.Username, authcode);
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Chat.fxml"));
        Parent p;
        try {
            p = fxmlLoader.load();
            FXML_ChatController controll = (FXML_ChatController)fxmlLoader.getController();
         
            controll.setMain(this);
            Scene scene = new Scene(p);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
