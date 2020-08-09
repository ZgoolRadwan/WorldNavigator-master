package WebPagesControllers;

import ConfigurationGame.Game;
import Constants.WallType;
import ConfigurationGame.PlayerItems.GeneralItem;
import ConfigurationGame.WallObjects.Seller;
import ConfigurationGame.GamesDriver;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.LinkedHashMap;
import java.util.Map;
/*
Players actions class, created by Radwan Zghool, 20-July-2020
*/
@SessionScoped
@ManagedBean(name="player")
public class PlayersActions {

    /*60000 milli seconds equals to one minute*/
    private final int ONE_MINUTE=60000;

    /*selected game id when player select game from drop down list*/
    private int selectedGameID;

    private String playerName;

    /*message to player after every action on game*/
    private String messageToPlayer;

    /*parameter used to check if player facing object has lock*//*, then decide to use key or not.*/
    private boolean isFacingWallHasLock;

    /*parameter used to check if fighting mode active or not.*/
    private boolean isFightModeActive;

    /*The result of fight mode sent to player as message.*/
    private String fightResult;

    /*game id that will be stored in the list after player create a new game..*/
    private String newGameId;

    /*parameter used to specify when the game will be started.*/
    private String minutesToStartGameAfter;

    /*when admin need to remove game, he choose the game id*/
    private int gameIdToRemove;

    /*parameter used to check if the trade mode started.*/
    private boolean tradeModeStarted;

    /*list of seller items, used to fill the drop down list.*/
    private Map<String,String>itemListForSeller;

    /*list of games, used to fill the drop down list.*/
    private Map<Integer,Integer>gamesList;

    private String selectedItem;

    /*Sengelton object for the whole web application, this object contains list og games.*/
    private GamesDriver server;

    public PlayersActions()
    {

        messageToPlayer="";
       server = GamesDriver.getInstance();
       gamesList=new LinkedHashMap<>();
        for(Game game:server.getGames())
            gamesList.put(game.getGameId(),game.getGameId());
    }

    public String play()
    {
        if(server.getGames().size()!=0)
        {
            if(!playerName.isEmpty()){
            if(isPlayerTryToPlayAfterGameStartedPlusOneMinute()){
                messageToPlayer="Game is already started, started before more than one minute";
                return messageToPlayer;
            }
            else if(isPlayerTryToPlayBeforeGameStarted())
            {
                messageToPlayer="The Game not started yet, wait few minutes";
                return messageToPlayer;
            }
            else
            {
                if( server.getGameById(selectedGameID).getPlayerByName(playerName)!=null)
                {
                    messageToPlayer="create new game, you are not allowed to play in this game.";
                }else{
                        server.getGameById(selectedGameID).addPlayer(playerName,selectedGameID);
                        server.getGameById(selectedGameID).getPlayerByName(playerName).setAllowedToPlayGame(true);
                        server.getGameById(selectedGameID).setGameActive(true);
                        if(server.getGameById(selectedGameID).getPlayers().size()==1){
                            server.getGameById(selectedGameID).stopGameAfterTime(8);
                            server.getGameById(selectedGameID).start();
                        }
                    }
                    messageToPlayer="";
                    return "game_space.xhtml?faces-redirect = true";
                }
            }else
            {
                messageToPlayer="Enter player name.";
                return messageToPlayer;
            }
        }else{
            messageToPlayer="Create game first.";
            return messageToPlayer;
        }
    }

    public boolean checkIfFacingLockObject()
    {
        isFacingWallHasLock=server.getGameById(selectedGameID).getPlayerByName(playerName).getFacingWall().getObjectInWall().hasLock();
        return isFacingWallHasLock;
    }


    public void lock()
    {
        if(isGameActive()) {
            messageToPlayer = server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().look();
        }
    }


    public void Left()
    {
        if(isGameActive()) {
            messageToPlayer = server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().turnLeft();
        }
    }
    public void Right()
    {
        if(isGameActive()) {
        messageToPlayer= server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().turnRight();
        }
    }
    public void Forward()
    {

        if(isGameActive()) {
            String res=server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().moveForward();
            if(res.equals("maze"))
            {
                messageToPlayer="Congratulations!!, you win";
                server.getGameById(selectedGameID).getPlayers().clear();
                server.getGameById(selectedGameID).getGameRooms().clear();
                server.getGameById(selectedGameID).setGameActive(false);
            }else
                messageToPlayer=res;
        }
    }

    public void scissor()
    {
        if(isGameActive()) {
        if(checkIfFightModeActive())
            server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().scissorCommand();
        }
    }

    public void paper()
    {
        if(isGameActive()) {
        if(checkIfFightModeActive())
            server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().paperCommand();
        }
    }

    public void rock()
    {
        if(isGameActive()) {
            if (checkIfFightModeActive())
                server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().rockCommand();
        }
    }
    public void Backward()
    {
        if(isGameActive()) {
            messageToPlayer = server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().moveBackward();
        }
    }
    public void Check()
    {
        if(isGameActive()) {
        messageToPlayer= server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().checkWall();
        }
    }

    public void CheckRoof()
    {
        if(isGameActive()) {
        messageToPlayer= server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().checkRoof();
        }
    }
    public void OpenDoor()
    {
        if(isGameActive()) {
            messageToPlayer = server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().openDoor();
        }
    }

    public Map<Integer, Integer> getGamesList() {
        return gamesList;
    }

    public void setGamesList(Map<Integer, Integer> gamesList) {
        this.gamesList = gamesList;
    }

    public Map<String, String> getItemListForSeller() {
        return itemListForSeller;
    }

    public void setItemListForSeller(Map<String, String> itemListForSeller) {
        this.itemListForSeller = itemListForSeller;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public boolean isTradeModeStarted() {
        if(isGameActive()) {
        return tradeModeStarted;
        }return false;
    }

    public void setTradeModeStarted(boolean tradeModeStarted) {
        this.tradeModeStarted = tradeModeStarted;

    }

    public void StartTrade()
    {
        if(isGameActive()) {
        if(server.getGameById(selectedGameID).getPlayerByName(playerName).getFacingWall().getWallType()== WallType.SELLER)
        {
            Seller sellerMan=(Seller) server.getGameById(selectedGameID).getPlayerByName(playerName).getFacingWall().getObjectInWall();
            tradeModeStarted=true;
            messageToPlayer="Trade mode, select type of trade you want.";
            itemListForSeller=new LinkedHashMap<>();
            for (GeneralItem item :sellerMan.sellerItems())
                itemListForSeller.put(item.getName()+" "+String.valueOf(item.getPrice())+"$",item.getName());
        }
        else
            messageToPlayer="Can't start trade mode, you are not facing seller person";
       }
    }
    public void FinishTrade()
    {
        if(isGameActive()) {
         tradeModeStarted=false;
         messageToPlayer="You got out of trade mode.";
        }
    }
    public void SellItem()
    {if(isGameActive()) {
        if (tradeModeStarted)
            messageToPlayer = server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().startTrade("sell " + selectedItem);
    }
    }

    public void Quit()
    {
        if(isGameActive()) {
            messageToPlayer = server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().quitCommand();
        }
    }

    public void BuyItem()
    {
        if(isGameActive()) {
            if (tradeModeStarted)
                messageToPlayer = server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().startTrade("buy " + selectedItem);
        }
    }

    public void UseSwitchLight()
    {   if(isGameActive()) {
        messageToPlayer = server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().switchLight();
    }
    }

    public void PrintPlayerStatus()
    {   if(isGameActive()) {
        messageToPlayer = server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().printStatus();
    }
    }

    public void UsingKey()
    {
        if(isGameActive()) {
            if (checkIfFacingLockObject()) {
                String key;
                String getResponseFromCheckObject = server.getGameById(selectedGameID).
                        getPlayerByName(playerName).getPlayerCommand().checkWall();
                String[] words = getResponseFromCheckObject.split("\\s");
                if (words.length > 4)
                    key = words[4];
                else
                    key = "open";
                messageToPlayer = server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerCommand().usingKey(key);
            }
        }

    }

    public String saveNewGame()
    {
        gamesList=new LinkedHashMap<>();
        if(!newGameId.isEmpty() && !minutesToStartGameAfter.isEmpty()){
        int id=Integer.valueOf(newGameId);
        if(server.getGameById(id)==null){
            int minutes=Integer.valueOf(minutesToStartGameAfter);
            server.addGame(id,minutes);
        for(Game game:server.getGames())
            gamesList.put(game.getGameId(),game.getGameId());
            return "setup_game.xhtml";
        }else
            for(Game game:server.getGames())
                gamesList.put(game.getGameId(),game.getGameId());
            messageToPlayer="This game already exist.";
        }
        return null;
    }
    public int getNumberOfGames()
    {
        for(Game game:server.getGames())
            gamesList.put(game.getGameId(),game.getGameId());
        return server.getGames().size();
    }
    public boolean isPlayerAllowedToPlay()
    {
        if(isGameActive()) {
            if (playerName != null)
                return server.getGameById(selectedGameID).getPlayerByName(playerName).isAllowedToPlayGame();
        }return false;
    }

    public void fight()
    {
        if(isGameActive()) {
        if(isFightModeActive()){
        messageToPlayer=server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerStatus().getCurrentRoom().finishTheFight();
        String words[]=messageToPlayer.split("\\s");
        if(words[0].equals("finish"))
            isFightModeActive=checkIfFightModeActive();
        }
        }
    }
    public boolean checkIfFightModeActive()
    {
        if(isGameActive()) {
            return server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerStatus().getCurrentRoom().isFightModeActive();
        }return false;
    }

    public boolean renderGameForm()
    {
        return !isFightModeActive() && isPlayerAllowedToPlay()&&!tradeModeStarted;
    }

    public boolean isGameActive()
    {
        return server.getGameById(selectedGameID).isGameActive();
    }

    public boolean isPlayerTryToPlayAfterGameStartedPlusOneMinute()
    {
        return (System.currentTimeMillis()>=server.getGameById(selectedGameID).getStartGameAtTime()+ONE_MINUTE);
    }

    public boolean isPlayerTryToPlayBeforeGameStarted()
    {
        return (System.currentTimeMillis()<server.getGameById(selectedGameID).getStartGameAtTime());
    }

    public int currentRoomID()
    {
        if(isGameActive())
            return server.getGameById(selectedGameID).getPlayerByName(playerName).getPlayerStatus().getCurrentRoom().getRoomId();
        return 0;
    }

    public String removeOneGame()
    {
        for(int i=0;i<server.getGames().size();i++)
        {
            if(server.getGames().get(i).getGameId()==gameIdToRemove) {
                server.getGames().remove(i);
                messageToPlayer="Game Id: "+gameIdToRemove+" removed.";
                gamesList.clear();
                break;
            }else
                messageToPlayer="Can't remove this game.";
        }
        for(Game game:server.getGames())
            gamesList.put(game.getGameId(),game.getGameId());
        return "setup_game.xhtml?faces-redirect = true";
    }


    public int getGameIdToRemove() {
        return gameIdToRemove;
    }

    public void setGameIdToRemove(int gameIdToRemove) {
        this.gameIdToRemove = gameIdToRemove;
    }


    public String getMinutesToStartGameAfter() {
        return minutesToStartGameAfter;
    }

    public void setMinutesToStartGameAfter(String minutesToStartGameAfter) {
        this.minutesToStartGameAfter = minutesToStartGameAfter;
    }

    public boolean isFightModeActive() {
        isFightModeActive=checkIfFightModeActive();
        return isFightModeActive;
    }

    /*
    *
    *  Setters and Getters
    *
     */
    public void setFightModeActive(boolean fightModeActive) {
        isFightModeActive = fightModeActive;
    }

    public String getFightResult() {
        return fightResult;
    }

    public void setFightResult(String fightResult) {
        this.fightResult = fightResult;
    }

    public String getNewGameId() {
        return newGameId;
    }

    public void setNewGameId(String newGameId) {
        this.newGameId = newGameId;
    }

    public String getMessageToPlayer() {
        return messageToPlayer;
    }

    public void setMessageToPlayer(String messageToPlayer) {
        this.messageToPlayer = messageToPlayer;
    }

    public int getSelectedGameID() {
        return selectedGameID;
    }

    public void setSelectedGameID(int selectedGameID) {
        this.selectedGameID = selectedGameID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


}
