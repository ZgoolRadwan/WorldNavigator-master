package com.company.CommandPattern;

import com.company.Enumerations.Fight;
import com.company.Maps.Room;
import com.company.Player.MazePlayer;

public class PaperCommand implements Command {

    private MazePlayer mazePlayer;
    public PaperCommand(MazePlayer player) {
    this.mazePlayer=player;
    }

    @Override
    public String execute(String optionalParameter) {
        mazePlayer.setFightModeChoice(Fight.PAPER);
        return "Paper";
    }
}
