package ConfigurationGame.CommandPattern;

import ConfigurationGame.Player.MazePlayer;
import Constants.FightingWords;

public class RockCommand implements Command{

    private MazePlayer mazePlayer;
    public RockCommand(MazePlayer player) {
        this.mazePlayer=player;
    }

    @Override
    public String execute(String optionalParameter) {
        mazePlayer.setFightModeChoice(FightingWords.ROCK);
        return "Rock";
    }
}
