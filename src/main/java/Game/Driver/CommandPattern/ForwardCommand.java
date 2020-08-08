package Game.Driver.CommandPattern;

import Constants.WallType;
import Game.Driver.Map.Room;
import Game.Driver.Player.MazePlayer;
import Game.Driver.WallObjects.Door;

public class ForwardCommand implements Command {

    MazePlayer player;
    WallType typeOfWall;
    Door door;
    Room currentRoom;


    public ForwardCommand(MazePlayer player) {
        this.player = player;
    }

    @Override
  public String execute(String optionalParameter) {
        typeOfWall=player.getFacingWall().getWallType();
        currentRoom=player.getPlayerStatus().getCurrentRoom();
        if(player.getFacingWall().getObjectInWall().getObjectName().equals("Door"))
        {
        door=(Door)player.getFacingWall().getObjectInWall();
        if(isPlayerFacingOpenDoor(door)){
            if(door.getNextRoom().getPlayersCount()==0){
                if(door.getNextRoom().isMazeRoom())
                {
                    return "maze";
                }
                door.getNextRoom().addPlayerToRoom(player);
                player.getPlayerStatus().setStartingRoom(door.getNextRoom());
                currentRoom.removePlayer(player);
                return "you entered next room.";
            }else if(door.getNextRoom().getPlayersCount()==1)
            {
                door.getNextRoom().addPlayerToRoom(player);
                player.getPlayerStatus().setStartingRoom(door.getNextRoom());
                currentRoom.removePlayer(player);
                return door.getNextRoom().fight();
                //fight(MazePlayer player inOutside)
            }else return "Wait there is two players in room";

        }
        else if(isPlayerFacingClosedDoor(door))
            door.checkObject(player.getPlayerStatus());
        else{
            return "No rooms next, you facing " + player.getFacingWall().getWallType() + " not door.";
        }

    }
        return "No rooms next, you facing " + player.getFacingWall().getWallType() + " not door.";

  }

  private boolean isPlayerFacingOpenDoor(Door door)
  {
    return (door.isDoorOpen());
  }

  private boolean isPlayerFacingClosedDoor(Door door)
  {
    return (!door.isDoorOpen());
  }

}
