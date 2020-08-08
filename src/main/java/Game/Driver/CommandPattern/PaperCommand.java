package Game.Driver.CommandPattern;

import Constants.FightingWords;
import Game.Driver.Player.MazePlayer;

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
