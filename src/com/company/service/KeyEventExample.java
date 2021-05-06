package com.company.service;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class KeyEventExample {
    Label label;

    public KeyEventExample() {
        Frame frame = new Frame();
        TextField textField = new TextField();
        frame.add(textField, BorderLayout.NORTH);
        label = new Label();
        frame.add(label, BorderLayout.CENTER);
        frame.setSize(450, 400);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        textField.addKeyListener(new KeyAdapter() {
            /**
             * When you type the character "a" into the text field you will see
             * an information dialog box
             */
            public void keyTyped(KeyEvent ke) {
                char keyChar = ke.getKeyChar();
                if (keyChar == 'a') {
                    System.out.println("You typed 'a'");
                }
            }

            /**
             * When you type the character "b" into the text field you will see
             * an information dialog box
             */
            public void keyPressed(KeyEvent ke) {
                int keyCode = ke.getKeyCode();
                if (keyCode == 66) {
                    System.out.println("You Typed b");
                }
            }

            /**
             * When you type the character "c" into the text field you will see
             * an information dialog box
             */
            public void keyReleased(KeyEvent ke) {
                int keyCode = ke.getKeyCode();
                if (keyCode == 67) {
                    System.out.println("You Typed c");
                }
            }
        });
    }
}
