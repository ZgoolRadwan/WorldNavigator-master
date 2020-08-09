package ConfigurationGame.Player;

import ConfigurationGame.Map.Room;
import ConfigurationGame.Map.Walls.GeneralWall;
import Constants.Directions;
import Constants.FightingWords;
import ConfigurationGame.PlayerItems.GeneralItem;

import java.util.ArrayList;
import java.util.Objects;

public class MazePlayer {
  private PlayerStatus playerStatus;
  private PlayerCommand playerCommand;
  private String playerName;
  private boolean isAllowedToPlayGame;
  private int gameId;
  private int playerPower;
  private FightingWords fightModeChoise;

  public int getGameId() {
    return gameId;
  }

  public void setGameId(int gameId) {
    this.gameId = gameId;
  }

  public FightingWords getFightModeChoice() {
    return fightModeChoise;
  }

  public void setFightModeChoice(FightingWords fightModeChoise) {
    this.fightModeChoise = fightModeChoise;
  }

  public boolean isAllowedToPlayGame() {
    return isAllowedToPlayGame;
  }

  public void setAllowedToPlayGame(boolean allowedToPlayGame) {
    isAllowedToPlayGame = allowedToPlayGame;
  }

  public void setPlayerStatus(PlayerStatus playerStatus) {
    this.playerStatus = playerStatus;
  }

  public void setPlayerCommand(PlayerCommand playerCommand) {
    this.playerCommand = playerCommand;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public MazePlayer(PlayerBuilder playerBuilder)
  {
    this.playerName=playerBuilder.playerName;
    this.playerStatus=new PlayerStatus();
    playerStatus.setStartingRoom(playerBuilder.currentRoom);
      playerStatus.setPlayerDirection(playerBuilder.playerDirection);
      playerStatus.setPlayerBag(playerBuilder.playerItems);
      playerStatus.setPlayerGolds(playerBuilder.playerGolds);
    this.playerCommand = new PlayerCommand(this);
  }

  public PlayerStatus getPlayerStatus() {
    return playerStatus;
  }

  public PlayerCommand getPlayerCommand() {
    return playerCommand;
  }


  public GeneralWall getFacingWall() {
    GeneralWall facingWall = null;

    switch (playerStatus.getPlayerDirection()) {
      case NORTH:
        facingWall = playerStatus.getCurrentRoom().getNorthWall();
        break;
      case EAST:
        facingWall = playerStatus.getCurrentRoom().getEastWall();
        break;
      case WEST:
        facingWall = playerStatus.getCurrentRoom().getWestWall();
        break;
      default:
        facingWall = playerStatus.getCurrentRoom().getSouthWall();
    }
    if (facingWall == null) throw new NullPointerException("facing wall is null check XML file.");
    return facingWall;
  }

  public int getPlayerPower()
  {
    int count=playerStatus.getPlayerGolds();
    for(GeneralItem playerItem:playerStatus.getPlayerBag())
      count+=playerItem.getPrice();
    return count;
  }

  public GeneralWall getBehindWall() {

    GeneralWall behindWall = null;
    switch (playerStatus.getPlayerDirection()) {
      case NORTH:
        behindWall = playerStatus.getCurrentRoom().getSouthWall();
        break;
      case EAST:
        behindWall = playerStatus.getCurrentRoom().getWestWall();
        break;
      case WEST:
        behindWall = playerStatus.getCurrentRoom().getEastWall();
        break;
      default:
        behindWall = playerStatus.getCurrentRoom().getNorthWall();
    }
    if (behindWall == null) throw new NullPointerException("behind GeneralWall is null check XML file.");
    return behindWall;
  }


  /*public void handleCommand(String userInput) {
    switch (userInput) {
      case "look":
        if (getPlayerStatus().getCurrentRoom().isRoomLit()) getPlayerCommand().look();
        else System.out.println("Dark room");
        break;
      case "check":
        if (getPlayerStatus().getCurrentRoom().isRoomLit()) getPlayerCommand().checkWall();
        else System.out.println("Dark room");
        break;
      case "right":
        getPlayerCommand().turnRight();
        break;
      case "left":
        getPlayerCommand().turnLeft();
        break;
      case "use_key":
        getPlayerCommand().usingKey();
        break;
      case "open":
        getPlayerCommand().openDoor();
        break;
      case "forward":
        getPlayerCommand().moveForward();
        break;
      case "status":
        getPlayerCommand().printStatus();
        break;
      case "backward":
        getPlayerCommand().moveBackward();
        break;
      case "trade":
        getPlayerCommand().startTrade();
        break;
      case "switchlight":
        getPlayerCommand().switchLight();
        break;
      case "flashlight":
        getPlayerCommand().useFlashLight();
        break;
      default:
        System.out.println("Enter valid command");
    }
  }
*/
  public static class PlayerBuilder
  {
    private int playerGolds;
    private Directions playerDirection;
    private Room currentRoom;
    private String playerName;

    private ArrayList<GeneralItem> playerItems;

    public PlayerBuilder(Directions playerDirection, String name, Room startRoom)
    {
      this.playerName=name;
      this.playerDirection=playerDirection;
      this.currentRoom=startRoom;
    }
    public PlayerBuilder setPlayerGolds(int playerGolds) {
      if (playerGolds <= 0) throw new NumberFormatException("invalid golds amount.");
      else this.playerGolds = playerGolds;
      return this;
    }

    public PlayerBuilder setPlayerBag(ArrayList<GeneralItem>playerBag) {
      this.playerItems = playerBag;
      return this;
    }
    public MazePlayer build()
    {
      return new MazePlayer(this);
    }
  }
  @Override
  public String toString() {
    return "MazePlayer{" +
            "playerStatus=" + playerStatus.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MazePlayer that = (MazePlayer) o;
    return playerName.equals(that.playerName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerStatus, playerCommand, playerName, isAllowedToPlayGame);
  }
}
