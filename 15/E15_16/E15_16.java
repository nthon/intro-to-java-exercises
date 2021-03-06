/*
  Write a program that displays two circles with radius 10 at location
  (40, 40) and (120, 150) with a line connecting the two circles. The distance
  between the circles is displayed along the line. The user can drag a circle.
  When that happens, the circle and its line are moved and the distance
  between the circles is updated.
*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class E15_16 extends Application {
  @Override
  public void start(Stage primaryStage) {
    final double WIDTH = 500.0;
    final double HEIGHT = 500.0;
    final double RADIUS = 10.0;

    Circle c1 = new Circle(40, 40, RADIUS);
    c1.setFill(Color.WHITE);
    c1.setStroke(Color.BLACK);
    Circle c2 = new Circle(120, 150, RADIUS);
    c2.setFill(Color.WHITE);
    c2.setStroke(Color.BLACK);

    Line line = new Line(c1.getCenterX(), c1.getCenterY(),
      c2.getCenterX(), c2.getCenterY());
    line.startXProperty().bind(c1.centerXProperty());
    line.startYProperty().bind(c1.centerYProperty());
    line.endXProperty().bind(c2.centerXProperty());
    line.endYProperty().bind(c2.centerYProperty());

    Text text = new Text(getMidX(line), getMidY(line), String.valueOf(distance(line)));

    Pane pane = new Pane();
    pane.getChildren().addAll(line, text, c1, c2);

    EventHandler<MouseEvent> dragCircle = e -> {
      ((Circle)e.getSource()).setCenterX(e.getX());
      ((Circle)e.getSource()).setCenterY(e.getY());
      text.setX(getMidX(line));
      text.setY(getMidY(line));
      text.setText(String.valueOf(distance(line)));
    };

    c1.setOnMouseDragged(dragCircle);
    c2.setOnMouseDragged(dragCircle);

    Scene scene = new Scene(pane, WIDTH, HEIGHT);
    primaryStage.setTitle("E15_16");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static double distance(Line line) {
    double x1 = line.getStartX();
    double y1 = line.getStartY();
    double x2 = line.getEndX();
    double y2 = line.getEndY();
    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
  }

  public static double getMidX(Line line) {
    return (line.getStartX() + line.getEndX()) / 2;
  }

  public static double getMidY(Line line) {
    return (line.getStartY() + line.getEndY()) / 2;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
