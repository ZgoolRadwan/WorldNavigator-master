package Game.Driver.CommandPattern;

import Game.Driver.Map.Room;
import Game.Driver.Player.MazePlayer;

public class FightCommand implements Command {

    private MazePlayer player;
    private Room currentRoom;

    public FightCommand(MazePlayer player) {
        this.player = player;
    }

    @Override
    public String execute(String optionalParameter) {
        currentRoom=player.getPlayerStatus().getCurrentRoom();
        if(player.getFightModeChoice()!=null)
        {
            return currentRoom.finishTheFight();
        }else
            return "Chose command first";

    }
}
