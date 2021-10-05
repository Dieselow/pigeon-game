package ca.uqac.game;

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
        JFrame frame = new JFrame("Super Pigeon Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(Main::initInterface);
    }
}
