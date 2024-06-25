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
public class output extends JFrame implements ActionListener {

    JButton sort = new JButton("SORTED ARRAY RESULTS");
    JButton inv = new JButton("INVERTED ARRAY RESULTS");
    JButton rand = new JButton("RANDOM ARRAY RESULTS");

    public output() {
        modifyFrame();
        addElements();

    }

    public void modifyFrame() {
        setTitle("Sorting");
        setResizable(true);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setBackground(new Color(255, 182, 193));
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER));

    }

    public void addElements() {
        add(new JLabel("Results")).setFont(new Font("SansSerif", Font.BOLD, 28));
        add(sort);
        add(inv);
        add(rand);
        sort.addActionListener(this);
        inv.addActionListener(this);
        rand.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sort) {
            sorted frame = new sorted();
            frame.sortedArrayOutput(Sorting.total);
            frame.setVisible(true);
            dispose();
        }
        if (e.getSource() == inv) {
            inverse frame = new inverse();
            frame.inverseArrayOutput(Sorting.total);
            frame.setVisible(true);
            dispose();
        }
        if (e.getSource() == rand) {
            random frame = new random();
            frame.randomArrayOutput(Sorting.total);
            frame.setVisible(true);
            dispose();
        }

    }
}
