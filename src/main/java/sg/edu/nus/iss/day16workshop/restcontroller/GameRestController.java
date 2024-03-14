package sg.edu.nus.iss.day16workshop.restcontroller;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.day16workshop.model.Game;
import sg.edu.nus.iss.day16workshop.service.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "/api/games")
public class GameRestController {

    @Autowired
    GameService gameService;

    // CREATE (PostMapping)
    @PostMapping("")
    public ResponseEntity<String> postGame(@RequestBody String requestPayload) throws ParseException {
        JsonReader jReader = Json.createReader(new StringReader(requestPayload));
        JsonObject jObject = jReader.readObject();
        
        Game game = new Game();
        game.setGameId(jObject.getString("gameId"));
        game.setHomeTeam(jObject.getString("homeTeam"));
        game.setOppTeam(jObject.getString("oppTeam"));
        game.setVenue(jObject.getString("venue"));
        String gameDate = jObject.getString("gameDate");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date gDate = sdf.parse(gameDate);
        game.setGameDate(gDate);

        System.out.println("Post Game: " + game);
        gameService.createGame(game);
        
        // add and modify it to fit day 16 workshop task 1

        return ResponseEntity.ok("Game created successfully");
    }

    // READ - GET ALL (GetMapping)
    @GetMapping("")
    public ResponseEntity<List<Game>> getAllGames() {

        Map<String, Game> games = gameService.getGameList();

        List<Game> gameList = new ArrayList<>();

        for (Map.Entry<String, Game> game : games.entrySet()) {
            gameList.add(game.getValue());
        }
        return ResponseEntity.ok().body(gameList);
    }

    // READ - GET BY ID (GetMapping)

    // UPDATE (PutMapping)

    // DELETE (DeleteMapping)

}
