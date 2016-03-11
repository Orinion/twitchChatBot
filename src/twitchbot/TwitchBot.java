/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitchbot;

import twitchbot.windows.FXML_LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jakob.greuel
 */
public class TwitchBot extends Application
{
    ircClient myClient;

    @Override
    public void start(Stage stage) throws Exception
    {
        //FXMLDocumentController controller = new FXMLDocumentController();
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent p = fxmlLoader.load(getClass().getResource("FXMLDocument.fxml"));
        FXML_LoginController controll = (FXML_LoginController)fxmlLoader.getController();
        controll.setMain(this);
        Scene scene = new Scene(p);

        stage.setScene(scene);
        stage.show();
    }

    public void getAuthtoken(String username, String password)
    {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
