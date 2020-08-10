package WebPagesControllers;


import ConfigurationGame.GamesDriver;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="navigations")
public class PagesNavigations {

    private GamesDriver server;
    private String messageToPlayer;


    public PagesNavigations() {
        this.server = GamesDriver.getInstance();
    }

    public String goToAddNewGame()
    {

        messageToPlayer="";
        return "/pages/add_new_game.xhtml?faces-redirect = true";
    }

    public String goToSetupGame()
    {
        return "/pages/setup_game.xhtml";
    }

    public String goToGameSpace()
    {
        return "/pages/game_space.xhtml?faces-redirect = true";

    }

    public String goToClearGame()
    {
        if(server.getGames().size()>0)
            return "/pages/clearGame.xhtml?faces-redirect = true";
        messageToPlayer="No games to remove";
        return null;

    }

    public String getMessageToPlayer() {
        return messageToPlayer;
    }

    public void setMessageToPlayer(String messageToPlayer) {
        this.messageToPlayer = messageToPlayer;
    }
}
