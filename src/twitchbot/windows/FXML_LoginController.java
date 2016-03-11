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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import twitchbot.TwitchBot;

/**
 *
 * @author jakob.greuel
 */
public class FXML_LoginController implements Initializable
{
    private TwitchBot main;
    @FXML
    private TextField textUsername;
    @FXML
    private TextField textPassword;

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        String username = textUsername.getText();
        String pw = textPassword.getText();
        this.main.getAuthtoken(username, pw);
        Stage stage = (Stage)textUsername.getScene().getWindow();
        stage.close();
    }

    public void setMain(TwitchBot main)
    {
        this.main = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}
