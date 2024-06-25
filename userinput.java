/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mayam
 */
public class userinput extends JFrame {

    private JTextField inputTextField;
    private JButton processButton;
    private int[] numbers;

    public userinput() {
        initializeFrame();
        addComponents();
    }

    private void initializeFrame() {
        setTitle("Number Input GUI");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(250, 182, 193));
        setLayout(new FlowLayout());
    }

    private void addComponents() {
        inputTextField = new JTextField(20);
        processButton = new JButton("Process Input");

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });

        add(new JLabel("Enter numbers separated by space:")).setFont(new Font("SansSerif", Font.BOLD, 28));
        add(inputTextField);
        add(processButton);
        setVisible(true);
    }

    private void processInput() {
        String input = inputTextField.getText();
        numbers = validateArray(input);

        if (numbers != null) {
            new userinputresults(numbers);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error: Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int[] getNumbers() {
        return numbers;
    }

    public static int[] validateArray(String elements) {
        String[] tokens = elements.split("\\s+");
        int size = tokens.length;
        int[] arr = new int[size];
        try {
            for (int i = 0; i < size; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }
        } catch (NumberFormatException e) {
            return null; // Return null if validation fails
        }
        return arr;
    }
}
