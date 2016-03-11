package twitchbot;

import AbiturKlassen.Client;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ircClient extends Client
{    
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
        
    }
    private void pong(String pMessage)
    {
        this.send(pMessage.replace("PING", "PONG"));
    }
    
    public void sendChat(String chanelName,String message)
    {
        this.send("PRIVMSG #"+chanelName+" :"+message);
    }
    public void joinChat(String chanelName)
    {
        this.send("JOIN #"+chanelName);
    }
     public void leaveChat(String chanelName)
    {
        this.send("PART #"+chanelName);
    }
    
    @Override
    public void send(String pMessage)
    {
        System.out.println(pMessage);
        super.send(pMessage);
    }
    @Override
    public void processMessage(String pMessage)
    {
        if(pMessage.contains("PING"))
            this.pong(pMessage);

        System.out.println(pMessage);
        
    }

    
}
