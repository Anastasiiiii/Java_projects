package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Randomizer extends JFrame implements ActionListener {

    private JTextField minTextField, maxTextField, generatedNumber;
    private int randomNumber = 0;
    private JLabel generatedNumberLabel;

    public Randomizer() {
        super("Randomizer App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        pack();
        setResizable(false);
        setLayout(null);

        getContentPane().setBackground(new Color(255, 105, 180));

        addGuiComponents();
    }

    private void addGuiComponents() {
        JLabel bannerLabel = new JLabel("Girly Randomizer".toUpperCase());
        bannerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        bannerLabel.setHorizontalAlignment(SwingUtilities.CENTER);
        bannerLabel.setVerticalAlignment(SwingUtilities.TOP);
        bannerLabel.setBounds(
                0,
                15,
                CommonConstants.GUI_SIZE.width,
                CommonConstants.BANNER_SIZE.height
        );
        bannerLabel.setForeground(Color.WHITE);

        //add min number component
        JLabel minLabel = new JLabel("Min Value:");
        minLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        minLabel.setBounds(50, 100, 100, 25);
        minLabel.setForeground(Color.WHITE);

        minTextField = new JTextField();
        minTextField.setBounds(150, 100, 100, 25);
        minTextField.setBackground(new Color(255, 182, 193));
        minTextField.setForeground(Color.DARK_GRAY);

        //add max number component
        JLabel maxLabel = new JLabel("Max Value:");
        maxLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        maxLabel.setBounds(50, 140, 100, 25);
        maxLabel.setForeground(Color.WHITE);

        maxTextField = new JTextField();
        maxTextField.setBounds(150, 140, 100, 25);
        maxTextField.setBackground(new Color(255, 182, 193));
        maxTextField.setForeground(Color.DARK_GRAY);

        //add generate button
        JButton generateNumberButton = new JButton("Generate");
        generateNumberButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        generateNumberButton.setBounds(50, 200, 100, 30);
        generateNumberButton.setBackground(new Color(255, 182, 193));
        generateNumberButton.setBorder(new LineBorder(new Color(255, 182, 193), 2));
        generateNumberButton.setForeground(Color.DARK_GRAY);

        generateNumberButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){
                generateNumberButton.setBackground(new Color(255, 20, 147));
                generateNumberButton.setBorder(new LineBorder(new Color(255, 20, 147), 2));
                generateNumberButton.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                generateNumberButton.setBackground(new Color(255, 182, 193));
                generateNumberButton.setBorder(new LineBorder(new Color(255, 182, 193), 2));
                generateNumberButton.setForeground(Color.DARK_GRAY);
            }
        });

        generateNumberButton.addActionListener(this);

        //add generated number text field
        generatedNumberLabel = new JLabel(String.valueOf(randomNumber));
        generatedNumberLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        generatedNumberLabel.setBounds(350, 100, 100, 40);

        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(generatedNumberLabel);
        this.getContentPane().add(minLabel);
        this.getContentPane().add(minTextField);
        this.getContentPane().add(maxLabel);
        this.getContentPane().add(maxTextField);
        this.getContentPane().add(generateNumberButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int minValue = 0, maxValue = 0;
        try {
            minValue = Integer.parseInt(minTextField.getText().trim());
            maxValue = Integer.parseInt(maxTextField.getText().trim());

            //check if minValue smaller than maxValue
            if(minValue >= maxValue){
                JOptionPane.showMessageDialog(Randomizer.this,
                        "Min value must be less than Max value",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                randomNumber = (int) ((Math.random() * (maxValue - minValue)) + minValue);
                generatedNumberLabel.setText(String.valueOf(randomNumber));
            }
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(Randomizer.this,
                    "Please enter valid number",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
