package ch.hslu.enlab.conway;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generate_world")
public class WorldController {

    @PostMapping
    public World generateWorld(@RequestBody GenerationRequest request) {
        World world = new World(request.getWorld().getRows());
        for (int i = 0; i < request.getGeneration(); i++) {
            world = world.nextGeneration();
        }
        return world;
    }

    @Setter
    @Getter
    public static class GenerationRequest {
        // Getter und Setter
        private WorldRequest world;
        private int generation;

    }

    @Setter
    @Getter
    public static class WorldRequest {
        // Getter und Setter
        private String[] rows;
    }
}