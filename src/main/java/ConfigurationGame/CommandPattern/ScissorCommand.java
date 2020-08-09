package ConfigurationGame.CommandPattern;

import ConfigurationGame.Player.MazePlayer;
import Constants.FightingWords;

public class ScissorCommand implements Command{
    private MazePlayer mazePlayer;
    public ScissorCommand(MazePlayer player) {
        this.mazePlayer=player;
    }

    @Override
    public String execute(String optionalParameter) {
        mazePlayer.setFightModeChoice(FightingWords.SCISSOR);
        return "Scissor";
    }
}
