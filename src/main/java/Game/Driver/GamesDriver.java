package Game.Driver;

import javax.faces.bean.ApplicationScoped;
import java.util.ArrayList;


@ApplicationScoped
public class GamesDriver {

    private ArrayList<ConfigurationGame>games;
    private static GamesDriver firstInstance = null;
    public static GamesDriver getInstance() {//Singelton pattern
        if (firstInstance == null) firstInstance = new GamesDriver();
        return firstInstance;
    }
    private GamesDriver() {
        games=new ArrayList<>();
    }

    public ArrayList<ConfigurationGame> getGames() {
        return games;
    }

    public void addGame(int id,int minutes) {
        ConfigurationGame game=new ConfigurationGame();
        game.setGameId(id);
        game.setStartGameAtTime(minutes);
        games.add(game);
    }
    public ConfigurationGame getGameById(int id)
    {
        for (ConfigurationGame game:games) {
            if(game.getGameId()==id)
                return game;
        }
            return null;
    }
}
