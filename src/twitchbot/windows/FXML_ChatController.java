/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitchbot.windows;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
    private TextField newChanelName;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab newTab;

    @FXML

    void handleButtonAction(ActionEvent event) {
        String chanelName = tabPane.getSelectionModel().getSelectedItem().getText();
        main.myClient.sendChat(chanelName, textInput.getText());
        printMessage(chanelName, "you", textInput.getText());
        textInput.setText("");
    }

    @FXML
    void handleNewTab(Event event) {
        String input = newChanelName.getText();
        if (input.contains("twitch.tv/")) {
            input = input.substring(input.indexOf("twitch.tv/") + "twitch.tv/".length());
        }
        this.addTab(input);
        newChanelName.setText("");
    }

    private Tab getTab(String tabName) {
        Tab[] array = tabPane.getTabs().toArray(new Tab[tabPane.getTabs().size()]);
        for (Tab array1 : array) {
            if (array1.getText().toLowerCase().equals(tabName.toLowerCase())) {
                return array1;
            }
        }
        return null;
    }

    public Tab addTab(String neuerTab) {
        if (neuerTab.equals("")) {
            return null;
        }
        if (getTab(neuerTab) != null) {
            return getTab(neuerTab);
        }
        tabPane.getSelectionModel().clearSelection();
        myClient.joinChat(neuerTab);
        Tab addedTab = new Tab(neuerTab);
        TextFlow textFlow = new TextFlow(new Text(">" + neuerTab));
        ScrollPane sb = new ScrollPane(textFlow);
        addedTab.setContent(sb);
        addedTab.setOnClosed((Event event) -> {
            myClient.leaveChat(neuerTab);
        });
        tabPane.getTabs().remove(newTab);
        tabPane.getTabs().add(addedTab);
        tabPane.getSelectionModel().select(addedTab);
        tabPane.getTabs().add(newTab);
        return addedTab;
    }

    public void removeTab(String tab) {
        Tab act = getTab(tab);
        if (act != null) {
            tabPane.getTabs().remove(act);
        }
        myClient.leaveChat(tab);
    }

    public void printMessage(String tab, String username, String message) {
        Tab act = getTab(tab);
        if (act == null) {
            act = addTab(tab);
        }
        ScrollPane spane = (ScrollPane) act.getContent();
        TextFlow txtFlow = (TextFlow)spane.getContent();
        Text clrUsername = new Text("\n" + username + ": ");
        switch (username.charAt(0) % 7) {
            case 0:
                clrUsername.setFill(Color.BLACK);
                break;
            case 1:
                clrUsername.setFill(Color.RED);
                break;
            case 2:
                clrUsername.setFill(Color.BLUE);
                break;
            case 3:
                clrUsername.setFill(Color.DARKORANGE);
                break;
            case 4:
                clrUsername.setFill(Color.GREEN);
                break;
            case 5:
                clrUsername.setFill(Color.BLUEVIOLET);
                break;
            case 6:
                clrUsername.setFill(Color.PINK);
                break;
        }
        clrUsername.setFont(Font.font(clrUsername.getFont().getName(), FontWeight.BOLD, clrUsername.getFont().getSize()));
        txtFlow.getChildren().addAll(clrUsername, new Text(message));
        spane.setVvalue(1.0);
    }

    public void setMain(TwitchBot main) {
        this.main = main;
        myClient = main.myClient;
        myClient.chatController = this;
        newTab.setClosable(false);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
