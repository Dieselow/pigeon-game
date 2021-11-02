package ca.uqac.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends JPanel implements MouseListener, ActionListener {
    List<Pigeon> pigeons = new ArrayList<>();
    List<PigeonFood> pigeonFoods = new ArrayList<>();
    private BufferedImage pigeonImage;
    private BufferedImage foodImage;

    private int sizeX;
    private int sizeY;

    public Board(int pigeonNumbers, int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        Random rand = new Random(); //instance of random class
        for (int i = 0; i < pigeonNumbers; i++) {
            this.pigeons.add(new Pigeon(rand.nextInt(sizeX), rand.nextInt(sizeY), this));
        }
        try {
            pigeonImage = ImageIO.read(new File("src/resources/pigeon.png"));
            foodImage = ImageIO.read(new File("src/resources/cookie.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        addMouseListener(this);
        Graphics2D graphics = (Graphics2D) g;
        for (Pigeon pigeon : this.pigeons) {
            graphics.drawImage(pigeonImage, pigeon.getPositionX(), pigeon.getPositionY(),50,50, null);
        }
        synchronized (this){
            for (PigeonFood food: this.pigeonFoods){
                graphics.drawImage(foodImage, food.getxPosition(), food.getyPostion(),50,50, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public synchronized void mousePressed(MouseEvent e) {
        this.pigeonFoods.add(new PigeonFood(e.getX(),e.getY()));
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public List<PigeonFood> getPigeonFoods() {
        return pigeonFoods;
    }
}
