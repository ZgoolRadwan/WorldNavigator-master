package com.company.Player;

import com.company.CommandPattern.*;

public class PlayerCommand {
  private Command  lookCommand, leftCommand, rightCommand, forwardCommand, backwardCommand, flashLightCommand;
  private Command checkCommand,checkRoofCommand,quitCommand,
      openCommand,
      tradeCommand,
      switchLightCommand,finishFight,
      usingKeyCommand;
  private Command printStatusCommand,paperCommand,ScissorCommand,RockCommand;

  private MazePlayer mazePlayer;

  private String optionalParameter;

  public PlayerCommand(MazePlayer player) {
    this.mazePlayer = player;
    this.leftCommand = new LeftCommand( this.mazePlayer);
    this.rightCommand = new RightCommand( this.mazePlayer);
    this.lookCommand = new LookCommand( this.mazePlayer);
    this.forwardCommand = new ForwardCommand( this.mazePlayer);
    this.backwardCommand = new BackwardCommand( this.mazePlayer);
    this.flashLightCommand = new FlashLightCommand( this.mazePlayer);
    this.checkCommand = new CheckCommand( this.mazePlayer);
    this.openCommand = new OpenCommand( this.mazePlayer);
    this.tradeCommand = new TradeCommand( this.mazePlayer);
    this.switchLightCommand = new SwitchLightCommand( this.mazePlayer);
    this.quitCommand=new QuitCommand(this.mazePlayer);
    this.usingKeyCommand = new UsingKeyCommand( this.mazePlayer);
    this.printStatusCommand = new PrintStatusCommand( this.mazePlayer);
    this.checkRoofCommand=new CheckRoofCommand(this.mazePlayer);
    this.ScissorCommand=new ScissorCommand(this.mazePlayer);
    this.paperCommand=new PaperCommand(this.mazePlayer);
    this.RockCommand=new RockCommand(this.mazePlayer);
    this.finishFight=new FightCommand(this.mazePlayer);
  }

  public String checkRoof(){
    return checkRoofCommand.execute(optionalParameter);
  }

  public String turnLeft() {
   return leftCommand.execute(optionalParameter);
  }

  public String turnRight() {
    return rightCommand.execute(optionalParameter);
  }

  public String moveForward() {
    return forwardCommand.execute(optionalParameter);
  }

  public String moveBackward() {
    return backwardCommand.execute(optionalParameter);
  }

  public String quitCommand()
  {
    return quitCommand.execute(optionalParameter);
  }
  public String useFlashLight()
      {
    return flashLightCommand.execute(optionalParameter);
  }

  public String checkWall()
      {
    return checkCommand.execute(optionalParameter);
  }

  public String startTrade(String typeOfTrade)
  {
    this.optionalParameter=typeOfTrade;
    return tradeCommand.execute(optionalParameter);
  }

  public String switchLight()
      {
    return switchLightCommand.execute(optionalParameter);
  }

  public String openDoor()
      {
    return openCommand.execute(optionalParameter);
  }

  public String look() {
     return lookCommand.execute(optionalParameter);
  }

  public String usingKey(String key) {
    this.optionalParameter=key;
    return usingKeyCommand.execute(optionalParameter);
  }

  public String printStatus() {
    return printStatusCommand.execute(optionalParameter);
  }

  public String scissorCommand(){
    return ScissorCommand.execute(optionalParameter);
  }
  public String paperCommand(){
    return paperCommand.execute(optionalParameter);
  }
  public String rockCommand(){
    return RockCommand.execute(optionalParameter);
  }

  public String fightCommand()
  {
    return finishFight.execute(optionalParameter);
  }

  @Override
  public String toString() {
    return "Player Command{Left, right, forward, backward, flashlight, use_key, switchlight, check, look, restart, quit }";
  }
}
