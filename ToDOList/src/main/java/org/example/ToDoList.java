package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoList extends JFrame implements ActionListener {
    //taskPanel is a container for taskComponentPanel
    //taskComponentPanel stores all the taskComponents
    private JPanel taskPanel, taskComponentPanel;

    public ToDoList() {
        super("To Do List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        pack();
        setResizable(false);
        setLayout(null);

        addGuiComponents();
    }

    private void addGuiComponents(){
        JLabel bannerLabel = new JLabel("To Do List");
        bannerLabel.setBounds(
                (CommonConstants.GUI_SIZE.width - bannerLabel.getPreferredSize().width)/2,
                15,
                CommonConstants.BANNER_SIZE.width,
                CommonConstants.BANNER_SIZE.height
        );
        //taskPanel
        taskPanel = new JPanel();

        //taskComponentPanel
        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        //add scrolling to the task panel
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(8, 70, CommonConstants.TASK_PANEL_SIZE.width, CommonConstants.TASK_PANEL_SIZE.height);
        scrollPane.setMaximumSize(CommonConstants.TASK_PANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //add task button
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(-5, CommonConstants.GUI_SIZE.height - 88,
                CommonConstants.ADD_BUTTON_SIZE.width, CommonConstants.ADD_BUTTON_SIZE.height);

        addTaskButton.addActionListener(this);

        //add to frame
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("Add Task")){
            //create a task command
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            //make the task field request focus after creation
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }
}
