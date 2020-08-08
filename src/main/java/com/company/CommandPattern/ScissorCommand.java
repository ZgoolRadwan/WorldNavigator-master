package com.company.CommandPattern;

import com.company.Enumerations.Fight;
import com.company.Player.MazePlayer;

public class ScissorCommand implements Command{
    private MazePlayer mazePlayer;
    public ScissorCommand(MazePlayer player) {
        this.mazePlayer=player;
    }

    @Override
    public String execute(String optionalParameter) {
        mazePlayer.setFightModeChoice(Fight.SCISSOR);
        return "Scissor";
    }
}
