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
public class SquareService {

    /**
     * Function to find the area of a square
     * @param s length of sides
     * @return the area of the square
     */
    public static double getArea (double s) {
        if (s <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return s*s;
    }

    public static double getPerimeter (double s) {
        if (s <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return s*4;
    }
}
