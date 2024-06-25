/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author mayam
 */
public class random extends JFrame implements ActionListener {

    Sorting sorta = new Sorting();
    private JTextArea outputTextArea;

    public random() {
        initializeFrame("Random");
    }

    private void initializeFrame(String title) {
        super.setTitle(title);

        outputTextArea = new JTextArea();
        outputTextArea.setBackground(new Color(255, 218, 185));

        outputTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new output();
                dispose(); // Close the current frame
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(goBackButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void randomArrayOutput(int s) {
        int[] random = Sorting.random;

        displayArray("the random array:", random);

        Sorting.countComparisons = 0;
        Sorting.countInterchanges = 0;

        long startBubble = System.currentTimeMillis();
        int[] bubble = random.clone();
        Sorting.bubbleSort(bubble);
        long endBubble = System.currentTimeMillis();
        displaySortOutput("Bubble Sort", startBubble, endBubble);

        Sorting.countComparisons = 0;
        Sorting.countInterchanges = 0;
        long startQuick = System.currentTimeMillis();
        int[] quick = random.clone();
        Sorting.quickSort(quick, 0, s - 1);
        long endQuick = System.currentTimeMillis();
        displaySortOutput("Quick Sort", startQuick, endQuick);

        Sorting.countComparisons = 0;
        Sorting.countInterchanges = 0;
        long startCounting = System.currentTimeMillis();
        int[] count = random.clone();
        Sorting.countingSort(count);
        long endCounting = System.currentTimeMillis();
        displaySortOutput("Counting Sort", startCounting, endCounting);

       displayArray("the output array:", Sorting.bubbleSort(bubble));
    }

    public void displayText(String text) {
        outputTextArea.setFont(new Font("SansSerif", Font.PLAIN, 18));
        outputTextArea.append(text);
    }

    private void displayArray(String header, int[] array) {
        String result = header + "\n" + Arrays.toString(array) + "\n";
        displayText(result);
    }

    private void displaySortOutput(String sortName, long startTime, long endTime) {
        String result = sortName + "\n"
                + "Run time: " + (endTime - startTime) + " ms.\n"
                + "Number of comparisons: " + Sorting.countComparisons + "\n"
                + "Number of Interchanges: " + Sorting.countInterchanges + "\n\n";

        displayText(result);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
