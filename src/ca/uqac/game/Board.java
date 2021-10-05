package ca.uqac.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements MouseListener, ActionListener {
    List<Pigeon> pigeons = new ArrayList<>();
    private int sizeX;
    private int sizeY;

    public Board(int pigeonNumbers, int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        for (int i = 0; i < pigeonNumbers; i++) {
            this.pigeons.add(new Pigeon());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
