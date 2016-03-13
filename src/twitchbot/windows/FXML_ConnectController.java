/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitchbot.windows;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jakob
 */
public class FXML_ConnectController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private TwitchBot main;
    @FXML
    private WebView webview;

    public void setMain(TwitchBot main)
    {
        this.main = main;
        WebEngine webEngine = webview.getEngine();
        webEngine.load("https://api.twitch.tv/kraken/oauth2/authorize"
                    + "?response_type=token"
                    + "&client_id=nhtjgrmje2p662nifmn8u4fnp6i16af"
                    + "&redirect_uri=https://github.com/Orinion/twitchChatBot/"
                    + "&scope=chat_login");
        webEngine.locationProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.contains("access_token"))
                {
                    String authcode = newValue.substring(newValue.indexOf("access_token=")+"access_token=".length(),newValue.indexOf("&scope"));
                    main.connect(authcode);
                    Stage stage = (Stage)webview.getScene().getWindow();
                    stage.close();
                }
            }
        });
    }
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    
}
