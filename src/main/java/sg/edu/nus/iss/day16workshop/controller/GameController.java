package sg.edu.nus.iss.day16workshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.day16workshop.model.Game;
import sg.edu.nus.iss.day16workshop.service.GameService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(path = "/games")
public class GameController {

    @Autowired
    GameService gameService;

    
    @GetMapping("/list")
    public String gameListing(Model model) {
        Map<String, Game> games = gameService.getGameList();

        List<Game> gameList = new ArrayList<>();

        for (Map.Entry<String, Game> game : games.entrySet()) {
            gameList.add(game.getValue());
        }
       
        model.addAttribute("games", gameList);
        return "gamelist";
    }
    
}
