package program.components;

public class Shape {

    private final String type;
    private final double area;
    private final double perimeter;

    public Shape (String type, double area, double perimeter) {
        this.type = type;
        this.area = area;
        this.perimeter = perimeter;
    }

    public String getType() {
        return type;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

}
