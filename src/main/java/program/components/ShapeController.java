package program.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import lombok.Builder;

@Controller
public class ShapeController {

    @GetMapping("/square")
    @ResponseBody
    public Shape getSquareInfo(@RequestParam double side) {
        return new Square("square", side, SquareService.getArea(side), SquareService.getPerimeter(side));
    }

    @GetMapping("/randomsquare")
    @ResponseBody
    public Shape getRandomSquareInfo() {
        double side = RandomNumberClient.getRandomNumber();
        Square randomSquare = new Square("square", side, SquareService.getArea(side), SquareService.getPerimeter(side));
        //Square square = Square.builder().type("square").side(side).area(SquareService.getArea(side)).perimeter(SquareService.getPerimeter(side)).build();
        //  return ResponseEntity.ok(randomSquare);
        return randomSquare;
    }

    @GetMapping("/triangle")
    @ResponseBody
    public Shape getTriangleInfo (@RequestParam List<Double> sides) {
        return new Triangle("triangle", TriangleService.getArea(sides), TriangleService.getPerimeter(sides), TriangleService.getAngles(sides));
    }

    @GetMapping("/circle")
    @ResponseBody
    public Shape getCircleInfo (@RequestParam double radius) {
        return new Circle("circle", CircleService.getArea(radius), CircleService.getPerimeter(radius), CircleService.getDiameter(radius));
    }

}

