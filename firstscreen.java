/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author mayam
 */
public class firstscreen extends JFrame implements ActionListener {

    private JButton enterNumbersButton;
    private JButton generateNumbersButton;
    final Object lock1 = new Object();
    private String selectedOption;

    public firstscreen() {
        initializeFrame();
        addComponents();
    }

    private void initializeFrame() {
        setTitle("Start Frame");
        setSize(320, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(250, 182, 193));
        setLayout(new FlowLayout());
    }

    private void addComponents() {
        add(new JLabel("Input Method")).setFont(new Font("SansSerif", Font.BOLD, 28));
        enterNumbersButton = new JButton("Enter Numbers");
        generateNumbersButton = new JButton("Generate Numbers");

        enterNumbersButton.addActionListener(this);

        generateNumbersButton.addActionListener(this);
        add(enterNumbersButton);
        add(generateNumbersButton);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterNumbersButton) {
            new userinput();
            dispose();
        }
        if (e.getSource() == generateNumbersButton) {
            selectedOption = "generateNumbersButton";
            synchronized (lock1) {
                lock1.notifyAll();
            }
            dispose();
        }
    }

    String getSelectedOption() {
        return selectedOption;
    }
}
