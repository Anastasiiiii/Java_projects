package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToDoList toDoList = new ToDoList();
            toDoList.setVisible(true);
        });
    }
}
