package ConfigurationGame.CommandPattern;

import ConfigurationGame.Map.Room;
import ConfigurationGame.Player.MazePlayer;

public class QuitCommand implements Command {
    MazePlayer player;
    Room room;

    public QuitCommand(MazePlayer player) {
        this.player = player;
    }

    @Override
    public String execute(String optionalParameter) {
        this.room=player.getPlayerStatus().getCurrentRoom();
        room.putPlayerItemsOnTheRoof(player.getPlayerStatus());
        room.madePlayerLoser(player);
        room.removePlayer(player);
        player.setAllowedToPlayGame(false);
        return player.getPlayerName()+" lost.";

    }
}
