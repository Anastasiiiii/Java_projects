package org.games.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.TextLayout;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.event.KeyEvent.VK_SPACE;

public class SnakeGame extends JPanel implements ActionListener {
    private final int width;
    private final int height;
    private final int cellSize;
    private final Random random = new Random();
    private static final int FRAME_RATE = 20;
    private int timerRate = 10;
    private boolean gameStarted = false;
    private boolean gameOver = false;
    private int points = 0;
    private GamePoint food;
    private Color foodColor;
    private final Color[] foodColors = new Color[]{Color.cyan, Color.ORANGE, Color.GREEN, Color.PINK, Color.RED};
    private Direction direction = Direction.LEFT;
    private Direction newDirection = Direction.RIGHT;

    private final ArrayList<GamePoint> snake = new ArrayList<>();

    public SnakeGame(final int width, final int height) {
        super();
        this.width = width;
        this.height = height;
        this.cellSize = width / (FRAME_RATE * 2);
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.black);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setTimerRate(int timerRate) {
        this.timerRate = timerRate;
    }

    public void startGame() {
        resetGame();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                handleKeyEvent(e.getKeyCode());
            }
        });

        new Timer(1000 / timerRate, this).start();
        System.out.println(timerRate);
    }

    private void handleKeyEvent(final int keyCode) {
        if(!gameStarted) {
            if(keyCode == VK_SPACE){
                gameStarted = true;
            }
        } else if(!gameOver) {
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    if(direction != Direction.DOWN) {
                        newDirection = Direction.UP;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != Direction.UP){
                        newDirection = Direction.DOWN;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != Direction.LEFT){
                        newDirection = Direction.RIGHT;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(direction != Direction.RIGHT) {
                        newDirection = Direction.LEFT;
                    }
                    break;
            }
        } else {
            if(keyCode == VK_SPACE){
                gameOver = false; // Знімаємо статус gameOver
                restartGame();    // Перезапускаємо гру
                gameStarted = true;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(!gameStarted) {
            graphics.setColor(Color.WHITE);
            graphics.setFont(graphics.getFont().deriveFont(30F));
            int currentHeight = height / 3;
            final var graphics2D = (Graphics2D) graphics;
            final var frc = graphics2D.getFontRenderContext();
            final String message = "Press space to\nstart the game";
            for (final var line : message.split("\n")) {
                final var layout = new TextLayout(line, graphics.getFont(), frc);
                final var bounds = layout.getBounds();
                final var targetWidth = (float) (width - bounds.getWidth()) / 2;
                layout.draw(graphics2D, targetWidth, currentHeight);
                currentHeight += graphics.getFontMetrics().getHeight();
            }
        } else if(gameOver) {
            graphics.setColor(Color.WHITE);
            graphics.setFont(graphics.getFont().deriveFont(30F));
            int currentHeight = height / 3;
            final var graphics2D = (Graphics2D) graphics;
            final var frc = graphics2D.getFontRenderContext();
            String pointsMessage = "Your score: " + getPoints() +"\n Press space to start the game again";
            for(final var line : pointsMessage.split("\n")) {
                final var layout = new TextLayout(line, graphics.getFont(), frc);
                final var bounds = layout.getBounds();
                final var targetWidth = (float) (width - bounds.getWidth()) / 2;
                layout.draw(graphics2D, targetWidth, currentHeight);
                currentHeight += graphics.getFontMetrics().getHeight();
            }
        } else {
            graphics.setColor(foodColor);
            graphics.fillRect(food.x, food.y, cellSize, cellSize);
            Color snakeColor = Color.MAGENTA;
            for (final var point : snake){
                graphics.setColor(snakeColor);
                graphics.fillRect(point.x, point.y, cellSize, cellSize);
                final int newMagenta = (int) Math.round(snakeColor.getRed() * (0.95));
                snakeColor = new Color(newMagenta, 0, 220);
            }
        }
    }

    private void restartGame() {
        setPoints(0);
        setTimerRate(1);
        gameOver = false;
        gameStarted = false;
        timerRate = 1;
        resetGame();
        repaint();
    }

    private void resetGame() {
        snake.clear();
        snake.add(new GamePoint(width / 2, height / 2));
        generateFood();
    }

    private void generateFood(){
        foodColor = foodColors[(int) (Math.random() * foodColors.length)];
        do {
            food = new GamePoint(random.nextInt(width / cellSize) * cellSize, random.nextInt(height/ cellSize) * cellSize);
        } while (snake.contains(food));
    }

    private void move() {
        direction = newDirection;

        final GamePoint currentHead = snake.getFirst();
        final GamePoint newHead = switch(direction) {
            case UP -> new GamePoint(currentHead.x, currentHead.y - cellSize);
            case DOWN -> new GamePoint(currentHead.x, currentHead.y + cellSize);
            case RIGHT -> new GamePoint(currentHead.x + cellSize, currentHead.y);
            case LEFT -> new GamePoint(currentHead.x - cellSize, currentHead.y);
        };
        snake.addFirst(newHead);

        if(newHead.equals(food)){
            generateFood();
            points += 1;
            setPoints(points);
            timerRate++;
        } else if(checkCollision()){
            gameOver = true;
            snake.removeFirst();
        } else {
            snake.removeLast();
        }
    }

    private boolean checkCollision() {
        final GamePoint head = snake.getFirst();
        final var invalidWidth = (head.x < 0) || (head.x >= width);
        final var invalidHeight = (head.y < 0) || (head.y >= height);
        return invalidWidth || invalidHeight;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if(gameStarted && !gameOver) {
            move();
        }
        repaint();
    }

    private record GamePoint(int x, int y){}

    private enum Direction {
        UP, DOWN, RIGHT, LEFT
    }
}
