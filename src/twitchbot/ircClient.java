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

    @Override
    public void processMessage(String pMessage)
    {
        System.out.println(pMessage);
    }

}
