package program.components;

public class Circle extends Shape{

    private final double diameter;
    public Circle(String type, double area, double perimeter, double diameter) {
        super(type, area, perimeter);
        this.diameter = diameter;
    }

    public double getDiameter() {
        return diameter;
    }
}
