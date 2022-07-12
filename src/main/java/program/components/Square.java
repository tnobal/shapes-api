package program.components;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Square extends Shape{
    private final double side;
    public Square(String type, double side, double area, double perimeter) {
        super(type, area, perimeter);
        this.side = side;
    }

    public double getSide() {
        return side;
    }
}
