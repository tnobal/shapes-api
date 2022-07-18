package program.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Controller
public class ShapeController {

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    @GetMapping("/square")
    @ResponseBody
    public String getSquareInfo(@RequestParam double side) throws JsonProcessingException {
        Square square = new Square("square", side, SquareService.getArea(side), SquareService.getPerimeter(side));
        return ow.writeValueAsString(square);
    }

    @GetMapping("/randomsquare")
    @ResponseBody
    public String getRandomSquareInfo() throws JsonProcessingException {
        System.out.println("second");
        double side = RandomNumberClient.getRandomNumber();
        Square randomSquare = new Square("square", side, SquareService.getArea(side), SquareService.getPerimeter(side));
        return ow.writeValueAsString(randomSquare);
    }

    @GetMapping("/triangle")
    @ResponseBody
    public String getTriangleInfo (@RequestParam List<Double> sides) throws JsonProcessingException {
        Triangle triangle = new Triangle("triangle", TriangleService.getArea(sides), TriangleService.getPerimeter(sides), TriangleService.getAngles(sides));
        return ow.writeValueAsString(triangle);
    }

    @GetMapping("/circle")
    @ResponseBody
    public String getCircleInfo (@RequestParam double radius) throws JsonProcessingException {
        Circle circle = new Circle("circle", CircleService.getArea(radius), CircleService.getPerimeter(radius), CircleService.getDiameter(radius));
        return ow.writeValueAsString(circle);
    }

}

