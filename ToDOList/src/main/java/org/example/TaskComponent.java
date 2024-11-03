package org.example;

import javax.swing.*;

public class TaskComponent extends JPanel {
    private JCheckBox checkBox;
    private JTextPane taskField;
    private JButton deleteButton;

    public JTextPane getTaskField() {
        return taskField;
    }

    //this panel is used to make updates to the task component panel when deleting tasks
    private JPanel parentPanel;

    public TaskComponent(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        //task field
        taskField = new JTextPane();
        taskField.setPreferredSize(CommonConstants.TASK_FIELD_SIZE);
        taskField.setContentType("text/html");

        //checkBox
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);

        //delete button
        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);

        //add to this taskComponent
        add(taskField);
        add(checkBox);
        add(deleteButton);
    }
}
