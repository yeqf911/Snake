import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by yeqf on 2015/6/6.
 */
public class Snake {

    private Node head = null;
    private Node tail = null;
    private int size;
    Node node = new Node(2, 2, Direction.RIGHT);

    public Snake() {
        head = node;
        tail = node;
        size = 1;
    }


    public void addToTail() {
        Node node = null;
        switch (tail.dir) {
            case LEFT:
                node = new Node(tail.row, tail.col + 1, tail.dir);
                break;
            case RIGHT:
                node = new Node(tail.row, tail.col - 1, tail.dir);
                break;
            case UP:
                node = new Node(tail.row - 1, tail.col, tail.dir);
                break;
            case DOWN:
                node = new Node(tail.row + 1, tail.col, tail.dir);
                break;
        }

        tail.next = node;
        tail = tail.next;
        size++;
    }

    public void addToHead() {
        Node node = null;
        switch (head.dir) {
            case LEFT:
                node = new Node(head.row, head.col - 1, head.dir);
                break;
            case RIGHT:
                node = new Node(head.row, head.col + 1, head.dir);
                break;
            case UP:
                node = new Node(head.row + 1, head.col, head.dir);
                break;
            case DOWN:
                node = new Node(head.row - 1, head.col, head.dir);
                break;
        }
        node.next = head;
        head = node;
        size++;
    }

    public void drawSnake(Graphics g) {
        Node node = head;
        move();
        while (node != null) {
            node.drawNode(g);
            node = node.next;
            //System.out.println(size);
        }

    }

    public void delFromTail() {
        Node tnode = head;
        if (tail == head) {
            return;
        }
        while (tnode.next != tail) {
            tnode = tnode.next;
        }

        tail = tnode;
        tail.next = null;
        size--;
    }

    private void move() {
        addToHead();
        delFromTail();
    }

    public void keyPressEvent(KeyEvent e) {
        switch (e.getExtendedKeyCode()) {
            case KeyEvent.VK_LEFT:
                head.dir = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                head.dir = Direction.RIGHT;
                break;
            case KeyEvent.VK_UP:
                head.dir = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                head.dir = Direction.DOWN;
                break;
            default:
                break;
        }
    }

    private class Node {
        private int weight = Yard.BLOCK_SIZE;
        private int height = Yard.BLOCK_SIZE;
        private Color color = Color.DARK_GRAY;
        private int row;
        private int col;
        private Direction dir = Direction.LEFT;
        private Node next;

        public Node(int row, int col, Direction dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            next = null;
        }

        private void drawNode(Graphics g) {
            Color c = g.getColor();
            g.setColor(color);
            g.fillRect((col - 1) * Yard.BLOCK_SIZE, (row - 1) * Yard.BLOCK_SIZE, weight, height);
            g.setColor(c);
        }
    }
}


