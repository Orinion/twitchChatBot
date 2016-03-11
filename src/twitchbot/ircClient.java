package twitchbot;

import AbiturKlassen.Client;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import twitchbot.windows.FXML_ChatController;

public class ircClient extends Client
{    
    public FXML_ChatController chatController;
    public ircClient(String pIPAdresse, int pPortNr, String username, String authToken)
    {
        super(pIPAdresse, pPortNr);
        System.out.println("connecting");
        while(!istVerbunden())
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(ircClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println("connected");
        this.send("PASS oauth:"+authToken);
        this.send("NICK "+username.toLowerCase());
        //this.send("CAP REQ :twitch.tv/commands");
        
        
    }
    private void pong(String pMessage)
    {
        this.send(pMessage.replace("PING", "PONG"));
    }
    
    public void sendChat(String chanelName,String message)
    {
        this.send("PRIVMSG #"+chanelName.toLowerCase()+" :"+message);
    }
    public void joinChat(String chanelName)
    {
        this.send("JOIN #"+chanelName.toLowerCase());
    }
     public void leaveChat(String chanelName)
    {
        this.send("PART #"+chanelName.toLowerCase());
    }
    
    @Override
    public void send(String pMessage)
    {
        pMessage.replace("ä","Ã¤");
        pMessage.replace( "Ä", "Ã„");
        pMessage.replace("ö", "Ã¶");
        pMessage.replace("Ö", "Ã–");
        pMessage.replace("ü", "Ã¼");
        pMessage.replace("Ü", "Ãœ");
        pMessage.replace("ß", "ÃŸ");
        //System.out.println("<"+pMessage);
        super.send(pMessage);
    }
    @Override
    public void processMessage(String pMessage)
    {
        pMessage.replace("Ã¤", "ä");
        pMessage.replace("Ã„", "Ä");
        pMessage.replace("Ã¶", "ö");
        pMessage.replace("Ã–", "Ö");
        pMessage.replace("Ã¼", "ü");
        pMessage.replace("Ãœ", "Ü");
        pMessage.replace("ÃŸ", "ß");
        
        
        //System.out.println(">"+pMessage);
        if(pMessage.contains("PING"))
            this.pong(pMessage);     
        else
            if(pMessage.contains("PRIVMSG"))
            {
                String username = pMessage.substring(1,pMessage.indexOf("!"));
                int indexChanelName1 = pMessage.indexOf(" #")+2;
                int indexChanelName2 = indexChanelName1 +pMessage.substring(indexChanelName1).indexOf(" :");
                String chanelname = pMessage.substring(indexChanelName1,indexChanelName2);
                String message = pMessage.substring(indexChanelName2+2); 
                Platform.runLater(new Runnable() {
                 @Override public void run() {
                     chatController.printMessage(chanelname,username+":"+ message);
                 }
                });
                
                
            }
    }

    
}
