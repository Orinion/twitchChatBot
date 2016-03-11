package twitchbot;

import AbiturKlassen.Client;

public class ircClient extends Client
{

    public ircClient(String pIPAdresse, int pPortNr, String username, String authToken)
    {
        super(pIPAdresse, pPortNr);
        this.send("PASS oauth:" + authToken);
        this.send("NICK " + username);
    }
    private void pong(String pMessage)
    {
        this.send(pMessage.replace("PING", "PONG"));
    }
    @Override
    public void processMessage(String pMessage)
    {
        if(pMessage.contains("PING"))
            this.pong(pMessage);
        System.out.println(pMessage);
    }

}
