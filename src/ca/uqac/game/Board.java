package ca.uqac.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        addMouseListener(this);
        Random rand = new Random(); //instance of random class
        try {
            pigeonImage = ImageIO.read(new File("src/resources/pigeon.png"));
            foodImage = ImageIO.read(new File("src/resources/cookie.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.createTimer();
        for (int i = 0; i < pigeonNumbers; i++) {
            Pigeon pigeon = new Pigeon(rand.nextInt(sizeX), rand.nextInt(sizeY), this,i);
            this.pigeons.add(pigeon);
            pigeon.startANewLife();
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
    public static Image newBrightness( Image source, float brightnessPercentage ) {

        BufferedImage bi = new BufferedImage(
                source.getWidth( null ),
                source.getHeight( null ),
                BufferedImage.TYPE_INT_ARGB );

        int[] pixel = { 0, 0, 0, 0 };
        float[] hsbvals = { 0, 0, 0 };

        bi.getGraphics().drawImage( source, 0, 0, null );

        // recalculare every pixel, changing the brightness
        for ( int i = 0; i < bi.getHeight(); i++ ) {
            for ( int j = 0; j < bi.getWidth(); j++ ) {

                // get the pixel data
                bi.getRaster().getPixel( j, i, pixel );

                // converts its data to hsb to change brightness
                Color.RGBtoHSB( pixel[0], pixel[1], pixel[2], hsbvals );

                // create a new color with the changed brightness
                Color c = new Color( Color.HSBtoRGB( hsbvals[0], hsbvals[1], hsbvals[2] * brightnessPercentage ) );

                // set the new pixel
                bi.getRaster().setPixel( j, i, new int[]{ c.getRed(), c.getGreen(), c.getBlue(), pixel[3] } );

            }

        }

        return bi;

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        synchronized (this){
            for (PigeonFood food: this.pigeonFoods){
                if (TimeUnit.SECONDS.convert(System.nanoTime() - food.getLifeTime() , TimeUnit.NANOSECONDS) > 30 ){ // set food is rotten if its lifetime > 30 seconds
                    food.setRotten();
                    graphics.drawImage(foodImage, food.getxPosition(), food.getyPostion(),50,50, null);
                    continue;
                }
                float scaleFactor = 1.3f; // How much brighter. 1.3 means 30% brighter
                RescaleOp op = new RescaleOp(scaleFactor, 0, null);
                Image healthyFood = op.filter(foodImage, null);;
                graphics.drawImage(healthyFood, food.getxPosition(), food.getyPostion(),50,50, null);
            }
        }
        for (Pigeon pigeon : this.pigeons) {
            graphics.drawImage(pigeonImage, pigeon.getPositionX(), pigeon.getPositionY(),50,50, null);
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
    public void mousePressed(MouseEvent e) {
        this.pigeonFoods.add(new PigeonFood(e.getX(),e.getY(),System.nanoTime()));
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

    public synchronized List<PigeonFood> getPigeonFoods() {
        return this.pigeonFoods;
    }
    private void createTimer(){
        new Timer(1, this).start();
    }

    public synchronized void removeFood(PigeonFood food){
        this.pigeonFoods.remove(food);
        repaint();
    }
}
