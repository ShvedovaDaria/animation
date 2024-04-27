import javax.swing.*;
import java.awt.*;

public class CircularSpriteAnimation extends JFrame {

    private SpritePanel spritePanel;
    private Timer timer;
    private double angle;

    public CircularSpriteAnimation() {
        setTitle("Circular Sprite Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        spritePanel = new SpritePanel();
        add(spritePanel);

        angle = 0;

        timer = new Timer(50, e -> {
            moveSprite();
            spritePanel.repaint();
        });
        timer.start();
    }

    private void moveSprite() {
        // Збільшуємо кут для кругового руху
        angle += Math.toRadians(5);
        // Обчислюємо нові координати спрайта
        int x = (int) (getWidth() / 2 + Math.cos(angle) * 200); // Радіус 200 пікселів
        int y = (int) (getHeight() / 2 + Math.sin(angle) * 200); // Радіус 200 пікселів
        // Встановлюємо нові координати для спрайта
        spritePanel.setSpritePosition(x, y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CircularSpriteAnimation animation = new CircularSpriteAnimation();
            animation.setVisible(true);
        });
    }

    private class SpritePanel extends JPanel {

        private ImageIcon spriteImage;
        private int x, y;

        public SpritePanel() {
            // Завантажуємо зображення спрайта з ресурсів
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
}
