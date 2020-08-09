package ConfigurationGame;

import javax.faces.bean.ApplicationScoped;
import java.util.ArrayList;


@ApplicationScoped
public class GamesDriver {

    private ArrayList<Game>games;
    private static GamesDriver firstInstance = null;
    public static GamesDriver getInstance() {//Singelton pattern
        if (firstInstance == null) firstInstance = new GamesDriver();
        return firstInstance;
    }
    private GamesDriver() {
        games=new ArrayList<>();
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void addGame(int id,int minutes) {
        Game game=new Game();
        game.setGameId(id);
        game.setStartGameAtTime(minutes);
        games.add(game);
    }
    public Game getGameById(int id)
    {
        for (Game game:games) {
            if(game.getGameId()==id)
                return game;
        }
            return null;
    }
}
