import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CircularSpriteAnimation extends JFrame {

    private SpriteModel model;
    private SpriteView view;
    private SpriteController controller;

    public CircularSpriteAnimation() {
        setTitle("Circular Sprite Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new SpriteModel();
        view = new SpriteView();
        controller = new SpriteController(model, view);

        add(view);

        Timer timer = new Timer(50, e -> controller.moveSprite());
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CircularSpriteAnimation animation = new CircularSpriteAnimation();
            animation.setVisible(true);
        });
    }
}

class SpriteModel {
    private double angle;

    public SpriteModel() {
        angle = 0;
    }

    public void increaseAngle() {
        angle += Math.toRadians(5);
    }

    public int calculateX(int width) {
        return (int) (width / 2 + Math.cos(angle) * 200);
    }

    public int calculateY(int height) {
        return (int) (height / 2 + Math.sin(angle) * 200);
    }
}

class SpriteView extends JPanel {

    private ImageIcon spriteImage;
    private int x, y;

    public SpriteView() {
        spriteImage = new ImageIcon(getClass().getResource("/sprite.png"));
        setSize(spriteImage.getIconWidth(), spriteImage.getIconHeight());
        x = getWidth() / 2;
        y = getHeight() / 2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(spriteImage.getImage(), x, y, this);
    }

    public void setSpritePosition(int x, int y) {
        this.x = x - spriteImage.getIconWidth() / 2;
        this.y = y - spriteImage.getIconHeight() / 2;
        repaint();
    }
}

class SpriteController {

    private SpriteModel model;
    private SpriteView view;

    public SpriteController(SpriteModel model, SpriteView view) {
        this.model = model;
        this.view = view;
    }

    public void moveSprite() {
        model.increaseAngle();
        int x = model.calculateX(view.getWidth());
        int y = model.calculateY(view.getHeight());
        view.setSpritePosition(x, y);
    }
}
