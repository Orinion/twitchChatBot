package twitchbot;

import AbiturKlassen.Client;

public class ircClient extends Client
{
    private String ClientID = "nhtjgrmje2p662nifmn8u4fnp6i16af";
    public ircClient(String pIPAdresse, int pPortNr, String username, String authToken)
    {
        super(pIPAdresse, pPortNr);
        //this.send("PASS oauth:" + authToken);
        //this.send("NICK " + username);
        System.out.println("connecting");
    }
    private void pong(String pMessage)
    {
        this.send(pMessage.replace("PING", "PONG"));
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
