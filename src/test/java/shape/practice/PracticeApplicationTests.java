package shape.practice;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import program.components.SquareService;
import program.components.TriangleService;
import program.components.CircleService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import java.util.List;

class PracticeApplicationTests {

	private static SquareService squareService;
	private static TriangleService triangleService;
	private static CircleService circleService;

	@Test
	public void correctSquareArea() throws Exception {
		assertThat(squareService.getArea(3)).isEqualTo(9.0);
		assertThat(squareService.getArea(38.6)).isEqualTo(1489.96);
		assertThat(squareService.getArea(1)).isEqualTo(1.0);
		assertThat(squareService.getArea(.08)).isEqualTo(.0064);
	}

	@Test
	public void invalidSquareArea() throws Exception {
		assertThatThrownBy(() -> {
			double area = squareService.getArea(-3);
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double area = squareService.getArea(0);
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double area = squareService.getArea(-1.8);
		}).isInstanceOf(ResponseStatusException.class);
	}

	@Test
	public void validSquarePerimeter() throws Exception {
		assertThat(squareService.getPerimeter(5)).isEqualTo(20.0);
		assertThat(squareService.getPerimeter(27.4)).isEqualTo(109.6);
		assertThat(squareService.getPerimeter(1)).isEqualTo(4.0);
		assertThat(squareService.getPerimeter(.08)).isEqualTo(.32);
	}

	@Test
	public void validCirclePerimeter() throws Exception {
		assertThatThrownBy(() -> {
			double area = squareService.getPerimeter(-2);
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double area = squareService.getPerimeter(0);
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double area = squareService.getPerimeter(-2.71);
		}).isInstanceOf(ResponseStatusException.class);
	}

	@Test
	public void correctTriangleArea() throws Exception {
		assertThat(triangleService.getArea(List.of(3.0, 4.0, 5.0))).isEqualTo(6);
		assertThat(triangleService.getArea(List.of(15.7, 21.4, 20.6))).isEqualTo(152.70);
		assertThat(triangleService.getArea(List.of(1.0, 1.0, 1.0))).isEqualTo(.43);
		assertThat(triangleService.getArea(List.of(.7, 20.2, 20.6))).isEqualTo(5.86);
	}

	@Test
	public void invalidTriangleArea() throws Exception {
		assertThatThrownBy(() -> {
			double area = triangleService.getArea(List.of(28.9, 8.3, 13.2));
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double area = triangleService.getArea(List.of(3.0, 4.0));
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double area = triangleService.getArea(List.of(3.0, 4.0, 5.0, 6.0));
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double area = triangleService.getArea(List.of(3.0, -4.0, 5.0));
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double area = triangleService.getArea(List.of(0.0, 4.0, 5.0));
		}).isInstanceOf(ResponseStatusException.class);
	}

	@Test
	public void validTrianglePerimeter() throws Exception {
		assertThat(triangleService.getPerimeter(List.of(3.0, 4.0, 5.0))).isEqualTo(12.0);
		assertThat(triangleService.getPerimeter(List.of(15.7, 21.4, 20.6))).isEqualTo(57.7);
		assertThat(triangleService.getPerimeter(List.of(1.0, 1.0, 1.0))).isEqualTo(3.0);
		assertThat(triangleService.getPerimeter(List.of(.7, 20.2, 20.6))).isEqualTo(41.5);
	}

	@Test
	public void invalidTrianglePerimeter() throws Exception {
		assertThatThrownBy(() -> {
			double perimeter = triangleService.getPerimeter(List.of(28.9, 8.3, 13.2));
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double perimeter = triangleService.getPerimeter(List.of(3.0, 4.0));
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double perimeter = triangleService.getPerimeter(List.of(3.0, 4.0, 5.0, 6.0));
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double perimeter = triangleService.getPerimeter(List.of(3.0, -4.0, 5.0));
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double perimeter = triangleService.getPerimeter(List.of(0.0, 4.0, 5.0));
		}).isInstanceOf(ResponseStatusException.class);
	}

	@Test
	public void correctAngles() throws Exception {
		assertThat(triangleService.getAngles(List.of(3.0, 4.0, 5.0))).isEqualTo(List.of(36.87, 53.13, 90.0));
		assertThat(triangleService.getAngles(List.of(1.0, 1.0, 1.0))).isEqualTo(List.of(60.0, 60.0, 60.0));
		assertThat(triangleService.getAngles(List.of(1.0, 1.0, Math.sqrt(2)))).isEqualTo(List.of(45.0, 45.0, 90.0));
	}

	@Test
	public void invalidAngles() throws Exception {
		assertThatThrownBy(() -> {
			List<Double> angles = triangleService.getAngles(List.of(28.9, 10.3, 13.2));
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			List<Double> angles = triangleService.getAngles(List.of(4.0, 4.0));
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			List<Double> angles = triangleService.getAngles(List.of(3.0, 4.0, 4.0, 6.0));
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			List<Double> angles = triangleService.getAngles(List.of(-3.0, 4.0, 5.0));
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			List<Double> angles = triangleService.getAngles(List.of(3.0, 4.0, 0.0));
		}).isInstanceOf(ResponseStatusException.class);
	}

	@Test
	public void correctCircleArea() {
		assertThat(circleService.getArea(3)).isEqualTo(28.27);
		assertThat(circleService.getArea(1)).isEqualTo(3.14);
	}

	@Test
	public void correctCirclePerimeter() {
		assertThat(circleService.getPerimeter(4.3)).isEqualTo(27.02);
		assertThat(circleService.getPerimeter(1)).isEqualTo(6.28);
		assertThat(circleService.getPerimeter(.0001)).isEqualTo(0.0);
	}

	@Test
	public void invalidCircle() {
		assertThatThrownBy(() -> {
			double area = circleService.getArea(-3);
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double area = circleService.getArea(0);
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double area = circleService.getPerimeter(-5);
		}).isInstanceOf(ResponseStatusException.class);
		assertThatThrownBy(() -> {
			double area = circleService.getDiameter(-0.4);
		}).isInstanceOf(ResponseStatusException.class);
	}
}
