package program.components;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CircleService {

    public static double getArea(double radius) {
        validateInput(radius);
        return round(Math.PI * radius * radius);
    }

    public static double getPerimeter(double radius) {
        validateInput(radius);
        return round(Math.PI * radius * 2);
    }

    public static double getDiameter(double radius) {
        validateInput(radius);
        return round(radius * 2);
    }

    public static void validateInput(double radius) {
        if (radius <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private static double round(double number) {
        return Math.round(number*100.0)/100.0;
    }

}
