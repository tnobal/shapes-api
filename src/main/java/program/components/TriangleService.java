package program.components;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TriangleService {
    public static double getArea (List<Double> sides) {
        validateInput(sides);
        double a = sides.get(0);
        double b = sides.get(1);
        double c = sides.get(2);
        double halfPerimeter = (a+b+c)/2;
        double radicand = halfPerimeter*(halfPerimeter-a)*(halfPerimeter-b)*(halfPerimeter-c);
        return round(Math.sqrt(radicand));
    }

    public static double getPerimeter (List<Double> sides) {
        validateInput(sides);
        return round(sides.get(0) + sides.get(1) + sides.get(2));
    }

    public static List<Double> getAngles (List<Double> sides) {
        validateInput(sides);
        double radianConversion = 180.0/Math.PI;
        double a = sides.get(0);
        double b = sides.get(1);
        double c = sides.get(2);
        double angleA = radianConversion * Math.acos((b*b + c*c - a*a)/(2*b*c));
        double angleB = radianConversion * Math.acos((a*a + c*c - b*b)/(2*a*c));
        double angleC = 180 - angleA - angleB;
        return List.of(round(angleA), round(angleB), round(angleC));
    }

    private static void validateInput (List<Double> sides) {
        if (sides.size() != 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        double a = sides.get(0);
        double b = sides.get(1);
        double c = sides.get(2);
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (a+b < c || a+c < b || b+c < a) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private static double round(double number) {
        return Math.round(number*100.0)/100.0;
    }
}
