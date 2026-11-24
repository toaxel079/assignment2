import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // The delay (ms) corresponds to 20 updates/second (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // TODO A list of cars, modify if needed
    ArrayList<Cars> cars = new ArrayList<>();

    public void startTimer() {
        this.timer.start();
    }

    /* Each step, the TimerListener moves all cars in the list and tells the
     * view to update its images. TODO Change this method to your needs.
     */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Cars car : cars) {
                double currentX = car.getX();
                double currentY = car.getY();
                double currentSpeed = car.getCurrentSpeed();
                double nextX = currentX + currentSpeed;
                double nextY;
                int panelWidth = frame.drawPanel.getWidth();
                int carWidth = frame.drawPanel.getVolvoImage().getWidth();
                int maxX = panelWidth - carWidth;
                String carDirection = car.getDirection();
                int carHeight = frame.drawPanel.getVolvoImage().getHeight();
                int panelHeight = frame.drawPanel.getHeight();
                int maxY = panelHeight - carHeight;
                int saabWidth = frame.drawPanel.saabImage.getWidth();
                int saabHeight = frame.drawPanel.saabImage.getHeight();
                System.out.println(carWidth);
                System.out.println(carHeight);
                System.out.println(saabHeight);
                System.out.println(saabWidth);

                if (carDirection.equals("right") && nextX >= maxX) {
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();
                }

                if (carDirection.equals("left")) {
                    nextX = currentX - currentSpeed;
                    if (nextX <= 0) {
                        car.stopEngine();
                        car.turnRight();
                        car.turnRight();
                        car.startEngine();
                    }
                }

                if (carDirection.equals("up")) {
                    nextY = currentY - currentSpeed;
                    if (nextY <= 0 && currentSpeed > 0) {
                        car.stopEngine();
                        car.turnRight();
                        car.turnRight();
                        car.startEngine();
                    }
                }

                if (carDirection.equals("down")) {
                    nextY = currentY + currentSpeed;
                    if (nextY >= maxY && currentSpeed > 0) {
                        car.stopEngine();
                        car.turnRight();
                        car.turnRight();
                        car.startEngine();
                    }
                }


                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.drawPanel.moveit(x, y, car);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.revalidate();
                frame.drawPanel.repaint();
            }
        }
    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Cars car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount ) / 100;
        for (Cars car : cars) {
            car.brake(brake);
        }
    }

    void startEngine() {
        for (Cars car : cars) {
            car.startEngine();
        }
    }

    void stopEngine() {
        for (Cars car : cars) {
            car.stopEngine();
        }
    }

    void turnLeft() {
        for (Cars car : cars) {
            car.turnLeft();
        }
    }

    void turnRight() {
        for (Cars car : cars) {
            car.turnRight();
        }
    }

    void TurboOn() {
        for (Cars car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void TurboOff() {
        for (Cars car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }
}
