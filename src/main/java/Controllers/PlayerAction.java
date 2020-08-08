package Controllers;

import com.company.Configuration.ConfigurationGame;
import com.company.Enumerations.WallType;
import com.company.Items.GameItem;
import com.company.Items.Key;
import com.company.ObjectsForWalls.Seller;
import com.company.Player.MazePlayer;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@SessionScoped
@ManagedBean(name="player")
public class PlayerAction {


    private final int ONE_MINUTE=60000;
    private int gameID;
    private int mapId;
    private int numberOfPlayers;
    private String playerName;
    private String action;
    private boolean isFacingWallHasLock;
    private int selectedGameID;
    private int numberOfPlayerInCurrenRoom;
    private boolean isFightModeActive;
    private String fightResult;
    private int newGameId;
    private ArrayList<Integer>gameList;
    private int minutesToStartGameAfter;
    private int gameIdToRemove;

    public int getGameIdToRemove() {
        return gameIdToRemove;
    }

    public void setGameIdToRemove(int gameIdToRemove) {
        this.gameIdToRemove = gameIdToRemove;
    }

    public int getMinutesToStartGameAfter() {
        return minutesToStartGameAfter;
    }

    public void setMinutesToStartGameAfter(int minutesToStartGameAfter) {
        this.minutesToStartGameAfter = minutesToStartGameAfter;
    }

    public boolean isFightModeActive() {
        isFightModeActive=checkIfFightModeActive();
        return isFightModeActive;
    }

    public ArrayList<Integer> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Integer> gameList) {
        this.gameList = gameList;
    }

    public void setFightModeActive(boolean fightModeActive) {
        isFightModeActive = fightModeActive;
    }

    public String getFightResult() {
        return fightResult;
    }

    public void setFightResult(String fightResult) {
        this.fightResult = fightResult;
    }

    public int getNewGameId() {
        return newGameId;
    }

    public void setNewGameId(int newGameId) {
        this.newGameId = newGameId;
    }

    public boolean isFacingWallHasLock() {

        return isFacingWallHasLock;
    }

    public int getNumberOfPlayerInCurrenRoom() {
        return numberOfPlayerInCurrenRoom;
    }

    public void setNumberOfPlayerInCurrenRoom(int numberOfPlayerInCurrenRoom) {
        this.numberOfPlayerInCurrenRoom = numberOfPlayerInCurrenRoom;
    }

    public void setFacingWallHasLock(boolean facingWallHasLock) {
        isFacingWallHasLock = facingWallHasLock;
    }


    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }




    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    private Server server;

    public PlayerAction()
    {

        action="";
       server = Server.getInstance();
       selectedGameForPlayer=new LinkedHashMap<>();
        for(ConfigurationGame game:server.getGames())
            selectedGameForPlayer.put(game.getGameId(),game.getGameId());
    }

    public String play()
    {
        if(server.getGames().size()!=0)
        {
            if(!playerName.isEmpty()){
            if(isPlayerTryToPlayAfterGameStartedPlusOneMinute()){
                action="Game is already started, started before more than one minute";
                return action;
            }
            else if(isPlayerTryToPlayBeforeGameStarted())
            {
                action="The Game not started yet, wait few minutes";
                return action;
            }
            else
            {
                server.getGameById(gameID).addPlayer(playerName,gameID);
                //server.getGameById(gameID).addPlayer(playerName,gameID);
                server.getGameById(gameID).getPlayerByName(playerName).setAllowedToPlayGame(true);
                //server.getGameById(gameID).getPlayerByName(playerName).setAllowedToPlayGame(true);
                server.getGameById(gameID).setGameActive(true);

                if(server.getGameById(gameID).getPlayers().size()==1){
                    server.getGameById(gameID).stopGameAfterTime(8);
                    server.getGameById(gameID).start();
                }

                numberOfPlayerInCurrenRoom=server.getGameById(gameID).getPlayerByName(playerName).getPlayerStatus().getCurrentRoom().getPlayersInRoom().size();
                numberOfPlayers=server.getGameById(gameID).getPlayers().size();
                action="";
                return "game_space.xhtml";
                }
            }else
            {
                action="Enter player name.";
                return action;
            }
        }else{
            action="Create game first.";
            return action;
        }
    }


    public boolean checkIfFacingLockObject()
    {
        isFacingWallHasLock=server.getGameById(gameID).getPlayerByName(playerName).getFacingWall().getObjectInWall().hasLock();
        return isFacingWallHasLock;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void lock()
    {
        if(isGameActive()) {
            action = server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().look();
        }
    }

    public boolean checkIfGameHasPlayer()
    {
        if(isGameActive())
        {
            if(server.getGameById(gameID).getPlayers().size()>0)
                return true;
        }
        return false;
    }
    public void Left()
    {
        if(isGameActive()) {
            action = server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().turnLeft();
        }
    }
    public void Right()
    {
        if(isGameActive()) {
        action= server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().turnRight();
        }
    }
    public void Forward()
    {

        if(isGameActive()) {
            String res=server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().moveForward();
            if(res.equals("maze"))
            {

                action="Congratulations!!, you win";
                server.getGameById(gameID).getPlayers().clear();
                server.getGameById(gameID).getGameRooms().clear();
                server.getGameById(gameID).setGameActive(false);
            }else
                action=res;
        }
        /*if(action.equals("fight"))
            isFightModeActive=true;
        else
            isFightModeActive=false;*/
    }

    public void scissor()
    {
        if(isGameActive()) {
        if(checkIfFightModeActive())
            server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().scissorCommand();
        }
    }

    public void paper()
    {
        if(isGameActive()) {
        if(checkIfFightModeActive())
            server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().paperCommand();
        }
    }

    public void rock()
    {
        if(isGameActive()) {
            if (checkIfFightModeActive())
                server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().rockCommand();
        }
    }
    public void Backward()
    {
        if(isGameActive()) {
            action = server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().moveBackward();
        }
    }
    public void Check()
    {
        if(isGameActive()) {
        action= server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().checkWall();
        }
    }

    public void CheckRoof()
    {
        if(isGameActive()) {
        action= server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().checkRoof();
        }
    }
    public void OpenDoor()
    {
        if(isGameActive()) {
            action = server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().openDoor();
        }
    }



    /*****/
    /*
     *
     * */
    /*Trade mode*/

    private boolean tradeModeStarted;
    private Map<String,String>itemListForSeller;
    private Map<Integer,Integer>selectedGameForPlayer;
    private String selectedItem;

    public Map<Integer, Integer> getSelectedGameForPlayer() {
        return selectedGameForPlayer;
    }

    public void setSelectedGameForPlayer(Map<Integer, Integer> selectedGameForPlayer) {
        this.selectedGameForPlayer = selectedGameForPlayer;
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
        if(server.getGameById(gameID).getPlayerByName(playerName).getFacingWall().getWallType()== WallType.SELLER)
        {
            Seller sellerMan=(Seller) server.getGameById(gameID).getPlayerByName(playerName).getFacingWall().getObjectInWall();
            tradeModeStarted=true;
            action="Trade mode, select type of trade you want.";
            itemListForSeller=new LinkedHashMap<>();
            for (GameItem item :sellerMan.sellerItems())
                itemListForSeller.put(item.getName()+" "+String.valueOf(item.getPrice())+"$",item.getName());
        }
        else
            action="Can't start trade mode, you are not facing seller person";
       }
    }
    public void FinishTrade()
    {
        if(isGameActive()) {
         tradeModeStarted=false;
         action="You got out of trade mode.";
        }
        // action= server.getGames().get(indexOfGame).getPlayerByName(playerName).getPlayerCommand().startTrade();
    }
    public void SellItem()
    {if(isGameActive()) {
        if (tradeModeStarted)
            action = server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().startTrade("sell " + selectedItem);
    }
    }

    public void Quit()
    {
        if(isGameActive()) {
            action = server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().quitCommand();
        }
    }

    public void BuyItem()
    {
        if(isGameActive()) {
            if (tradeModeStarted)
                action = server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().startTrade("buy " + selectedItem);
        }
    }

    public void UseSwitchLight()
    {   if(isGameActive()) {
        action = server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().switchLight();
    }
    }

    public void PrintPlayerStatus()
    {   if(isGameActive()) {
        action = server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().printStatus();
    }
    }

    public void UsingKey()
    {
        if(isGameActive()) {
            if (checkIfFacingLockObject()) {
                String key;
                String getResponseFromCheckObject = server.getGameById(gameID).
                        getPlayerByName(playerName).getPlayerCommand().checkWall();
                String[] words = getResponseFromCheckObject.split("\\s");
                if (words.length > 4)
                    key = words[4];
                else
                    key = "open";
                action = server.getGameById(gameID).getPlayerByName(playerName).getPlayerCommand().usingKey(key);
                //action="Lock key:"+keyForFacingLock;

            }
        }

    }

   /* public ArrayList<String> getGamesList()
    {

        ArrayList<String>arrayList=new ArrayList<>();
        for (MazePlayer player:server.getGames().get(0).getPlayers())
            arrayList.add(String.valueOf(player.getPlayerName()));
        return arrayList;
    }*/


    public int getGameByID(int id)
    {
        for(int i=0;i<server.getGames().size();i++)
            if(server.getGames().get(i).getGameId()==id)
                return i;
            return -1;
    }


    public int getSelectedGameID() {

        return selectedGameID;
    }

    public void setSelectedGameID(int selectedGameID) {
        this.selectedGameID = selectedGameID;
    }

    public String addNewGame()
    {
        return "add_new_game.xhtml";
    }

    public String saveNewGame()
    {
        selectedGameForPlayer=new LinkedHashMap<>();
        server.addGame(newGameId,minutesToStartGameAfter);
        for(ConfigurationGame game:server.getGames())
            selectedGameForPlayer.put(game.getGameId(),game.getGameId());
        return "setup_game.xhtml";
    }
    public int getNumberOfGames()
    {
        return server.getGames().size();
    }
    public boolean isPlayerAllowedToPlay()
    {
        if(isGameActive()) {
            boolean check;
            if (playerName != null) {
                return server.getGameById(gameID).getPlayerByName(playerName).isAllowedToPlayGame();
            }
            return false;
        }return false;
    }

    public void fight()
    {
        if(isGameActive()) {
        if(isFightModeActive()){
        action=server.getGameById(gameID).getPlayerByName(playerName).getPlayerStatus().getCurrentRoom().finishTheFight();
        String words[]=action.split("\\s");
        if(words[0].equals("finish"))
            isFightModeActive=checkIfFightModeActive();
        }
        }
    }
    public boolean checkIfFightModeActive()
    {
        if(isGameActive()) {
            return server.getGameById(gameID).getPlayerByName(playerName).getPlayerStatus().getCurrentRoom().isFightModeActive();
        }return false;
    }

    public boolean renderGameForm()
    {
        return !isFightModeActive() && isPlayerAllowedToPlay()&&!tradeModeStarted;
    }

    public boolean isGameActive()
    {
        return server.getGameById(gameID).isGameActive();
    }

    public boolean isPlayingTimeCorrect()
    {
        return (System.currentTimeMillis()>=server.getGameById(gameID).getStartGameAtTime() && System.currentTimeMillis()<=server.getGameById(gameID).getStartGameAtTime()+ONE_MINUTE);
    }

    public boolean isPlayerTryToPlayAfterGameStartedPlusOneMinute()
    {
        return (System.currentTimeMillis()>=server.getGameById(gameID).getStartGameAtTime()+ONE_MINUTE);
    }

    public boolean isPlayerTryToPlayBeforeGameStarted()
    {
        return (System.currentTimeMillis()<server.getGameById(gameID).getStartGameAtTime());
    }

    public int currentRoomID()
    {
        if(isGameActive()) {
        return server.getGameById(gameID).getPlayerByName(playerName).getPlayerStatus().getCurrentRoom().getRoomId();
        }
        return 0;
    }

    public String removeOneGame()
    {
        for(int i=0;i<server.getGames().size();i++)
        {
            if(server.getGames().get(i).getGameId()==gameIdToRemove) {
                server.getGames().remove(i);
                action="Game Id: "+gameIdToRemove+" removed.";
                selectedGameForPlayer.clear();
                break;
            }else
                action="Can't remove this game.";
        }
        return "setup_game.xhtml";
    }
    public String removeGamePage()
    {
        if(server.getGames().size()>0)
        return "clearGame.xhtml";
        else
            action="no games to remove.";
        return "setup_game.xhtml";
    }
}
