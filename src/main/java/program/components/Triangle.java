package program.components;

import java.util.List;

public class Triangle extends Shape{
    private final List<Double> angles;

    public Triangle(String type, double area, double perimeter, List<Double> angles) {
        super(type, area, perimeter);
        this.angles = angles;
    }

    public List<Double> getAngles() {
        return angles;
    }
}
