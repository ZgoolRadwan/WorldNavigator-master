package Controllers;

import com.company.Configuration.ConfigurationGame;

import javax.faces.bean.ApplicationScoped;
import java.util.ArrayList;


@ApplicationScoped
public class Server {

    private ArrayList<ConfigurationGame>games;
    private static Server firstInstance = null;
    public static Server getInstance() {//Singelton pattern
        if (firstInstance == null) firstInstance = new Server();
        return firstInstance;
    }
    private Server() {
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
