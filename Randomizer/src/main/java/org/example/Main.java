package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Randomizer randomizer = new Randomizer();
            randomizer.setVisible(true); // Зробити вікно видимим
        });
    }
}
