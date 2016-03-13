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
import javafx.scene.control.TextField;
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
    private TextField textAuthCode;

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        String authcode = textAuthCode.getText();
        main.connect(authcode);
        Stage stage = (Stage)textAuthCode.getScene().getWindow();
        stage.close();
    }
    public void setMain(TwitchBot main)
    {
        this.main = main;
    }
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
