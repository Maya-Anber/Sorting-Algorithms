/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import static sorting.Sorting.bubbleSortPrint;
import static sorting.Sorting.countComparisons;
import static sorting.Sorting.countingSortPrint;
import static sorting.Sorting.quickSortPrint;

/**
 *
 * @author mayam
 */
public class userinputresults extends JFrame {

    private JTextArea outputTextArea;

    public userinputresults(int[] sortedArray) {
        initializeFrame("RESULTS");
        displayArray("The Array:", sortedArray);
        displaySortingResults(sortedArray);
    }

    private void initializeFrame(String title) {
        setTitle(title);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        outputTextArea = new JTextArea();
        outputTextArea.setBackground(new Color(255, 218, 185));

        outputTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void displayArray(String header, int[] array) {
        String result = header + "\n" + Arrays.toString(array) + "\n\n";
        displayText(result);
    }

    private void displaySortingResults(int[] sortedArray) {
        Sorting.countComparisons = 0;
        Sorting.countInterchanges = 0;
        

        long startBubble = System.currentTimeMillis();
        int[] bubble = sortedArray.clone();
        System.out.println("BUBBLE SORT");

        bubbleSortPrint(bubble.clone());
        Sorting.bubbleSort(bubble);
        long endBubble = System.currentTimeMillis();
        displaySortOutput("Bubble Sort", startBubble, endBubble);
                

        Sorting.countComparisons = 0;
        Sorting.countInterchanges = 0;
        long startQuick = System.currentTimeMillis();
        int[] quick = sortedArray.clone();
        System.out.println("QUICK SORT");
        quickSortPrint(quick.clone(), 0, quick.length - 1);
        Sorting.quickSort(quick, 0, sortedArray.length - 1);
        long endQuick = System.currentTimeMillis();
        displaySortOutput("Quick Sort", startQuick, endQuick);
                

        Sorting.countComparisons = 0;
        Sorting.countInterchanges = 0;
        long startCounting = System.currentTimeMillis();
        int[] count = sortedArray.clone();
        System.out.println("COUNTING SORT");
        countingSortPrint(count.clone());
        Sorting.countingSort(count);
        long endCounting = System.currentTimeMillis();
        displaySortOutput("Counting Sort", startCounting, endCounting);
                

       displayArray("the output array:", Sorting.bubbleSort(bubble));

    }

    private void displaySortOutput(String sortName, long startTime, long endTime) {
        String result = sortName + "\n"
                + "Run time: " + (endTime - startTime) + " ms.\n"
                + "Number of comparisons: " + Sorting.countComparisons + "\n"
                + "Number of Interchanges: " + Sorting.countInterchanges + "\n\n";
        displayText(result);
    }

    public void displayText(String text) {
        outputTextArea.setFont(new Font("SansSerif", Font.PLAIN, 18));
        outputTextArea.append(text);
    }
}
