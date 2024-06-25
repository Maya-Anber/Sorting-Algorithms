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
import javax.swing.JTextField;

/**
 *
 * @author mayam
 */
public class inputArray extends JFrame implements ActionListener {

    JButton Generate = new JButton("Generate");
    JTextField Size = new JTextField(15);
    int SizeInt;
    final Object lock = new Object();

    public inputArray() {
        modifyFrame();
        addElements();

    }

    public void modifyFrame() {
        setTitle("Sorting program");
        setResizable(true);
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setBackground(new Color(250, 182, 193));
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER));

    }

    public void addElements() {
        add(new JLabel("Enter the number of elements to be sorted")).setFont(new Font("SansSerif", Font.BOLD, 28));
        add(Size);
        add(Generate);
        Generate.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Generate) {
            String SizeString = Size.getText();

            if (SizeString.isEmpty()) {
                AlertGUI.showAlert("enter the required data.");
                return;
            }
            try {
                SizeInt = Integer.parseInt(SizeString);
            } catch (NumberFormatException ex) {
                AlertGUI.showAlert("enter numeric values.");
                return;
            }

            if (SizeInt < 0) {
                AlertGUI.showAlert("cannot be negative.");
                return;
            }
            if (SizeInt > 10000) {
                AlertGUI.showAlert("the maximum size is 10,000.");
                return;
            }
            synchronized (lock) {
                lock.notifyAll();
            }
            output Result = new output();
            Result.setVisible(true);
            dispose();

        }
    }

    public int getSizeInt() {
        return SizeInt;
    }
}
