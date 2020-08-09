package ConfigurationGame.CommandPattern;

import ConfigurationGame.Player.MazePlayer;
import Constants.FightingWords;

public class PaperCommand implements Command {

    private MazePlayer mazePlayer;
    public PaperCommand(MazePlayer player) {
    this.mazePlayer=player;
    }

    @Override
    public String execute(String optionalParameter) {
        mazePlayer.setFightModeChoice(FightingWords.PAPER);
        return "Paper";
    }
}
