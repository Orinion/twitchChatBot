/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitchbot.windows;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import twitchbot.ircClient;

/**
 * FXML Controller class
 *
 * @author Jakob
 */
public class FXML_ChatController implements Initializable {

    private TwitchBot main;
    private ircClient myClient;
    @FXML
    private TextField textInput;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab newTab;
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        main.myClient.send(textInput.getText());
    }
    
     @FXML
    private void handleNewTab(ActionEvent event)
    {
        //TODO
        //Create Select and Name new Tab
    }
    public void setMain(TwitchBot main)
    {
        this.main = main;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
