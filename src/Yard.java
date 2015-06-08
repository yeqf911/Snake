import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Created by yeqf on 2015/6/6
 * .
 */
public class Yard extends JFrame {

    public static final int ROWS = 50;
    public static final int COLS = 50;
    public static final int BLOCK_SIZE = 10;

    private Snake snake = new Snake();

    private Image offScreenImage = null;

    public void launch() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimension.width / 2 - COLS * BLOCK_SIZE / 2,
                dimension.height / 2 - ROWS * BLOCK_SIZE / 2);
        setSize((ROWS + 2) * BLOCK_SIZE - 1, (COLS + 4) * BLOCK_SIZE + 2);
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        add(new MyCanvas());
    }

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0,  null);
    }

    private class MyCanvas extends Canvas {

        public MyCanvas() {

        }

        @Override
        public void paint(Graphics g) {
            setLocation(1, 1);
            setSize(COLS * BLOCK_SIZE + 1, ROWS * BLOCK_SIZE + 1);
            setBackground(Color.CYAN);
            Color color = g.getColor();
            //»­Ïß£¨¸ñ×Ó£©
            g.setColor(Color.DARK_GRAY);
            for (int i = 0; i < ROWS + 1; i++) {
                g.drawLine(0, i * BLOCK_SIZE, COLS * BLOCK_SIZE, i * BLOCK_SIZE);
            }

            for (int i = 0; i < COLS + 1; i++) {
                g.drawLine(i * BLOCK_SIZE, 0, i * BLOCK_SIZE, COLS * BLOCK_SIZE);
            }
            g.setColor(color);
            snake.drawSnake(g);
            new Thread(new PaintThread()).start();
        }

    }

    private class PaintThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            snake.keyPressEvent(e);
        }
    }

    public static void main(String[] args) {
        Yard yard = new Yard();
        yard.launch();
    }
}
