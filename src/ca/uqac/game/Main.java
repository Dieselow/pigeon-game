package ca.uqac.game;

import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void initInterface(){
        int height,width,pigeonNumber;
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter the dimension of the game:");
        System.out.println("Height :");
        height = Integer.parseInt(scanner.nextLine());
        System.out.println("Width: ");
        width = Integer.parseInt(scanner.nextLine());
        System.out.println("How much do you want pigeon in your game ?");
        pigeonNumber = Integer.parseInt(scanner.nextLine());
        Board gameBoard = new Board(pigeonNumber, height, width);
        JFrame frame = new JFrame("Super Pigeon Game");
        frame.add(gameBoard);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(100, 100));
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(100, 100));
        frame.setLocation(500, 500);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(Main::initInterface);
    }
}
