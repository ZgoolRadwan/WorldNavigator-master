package com.company.CommandPattern;

import com.company.Enumerations.Fight;
import com.company.Enumerations.WallType;
import com.company.Maps.Room;
import com.company.ObjectsForWalls.Door;
import com.company.Player.MazePlayer;

public class RockCommand implements Command{

    private MazePlayer mazePlayer;
    public RockCommand(MazePlayer player) {
        this.mazePlayer=player;
    }

    @Override
    public String execute(String optionalParameter) {
        mazePlayer.setFightModeChoice(Fight.ROCK);
        return "Rock";
    }
}
