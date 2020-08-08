package Game.Driver.CommandPattern;

import Constants.FightingWords;
import Game.Driver.Player.MazePlayer;

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
