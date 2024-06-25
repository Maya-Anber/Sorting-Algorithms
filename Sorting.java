package sorting;

import java.util.Random;

/**
 *
 * @author mayam
 */
public class Sorting {

    static int countComparisons;
    static int countInterchanges;
    static int total;
    static int[] random;

    public static void main(String[] args) {
        countComparisons = 0;
        countInterchanges = 0;

        firstscreen firstScreen = new firstscreen();
        synchronized (firstScreen.lock1) {
            try {
                firstScreen.lock1.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String userChoice = firstScreen.getSelectedOption();

        if ("generateNumbersButton".equals(userChoice)) {
            inputArray Size = new inputArray();
            synchronized (Size.lock) {
                try {
                    Size.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int size = Size.getSizeInt();
            updateTotal(size);
            random = randomArray(size);

            System.out.println("THE SIZE OF THE ARRAY:" + size);

            sortedArrayOutput(size);

            inverseArrayOutput(size);

            randomArrayOutput(size);

        }
    }

    private static synchronized void updateTotal(int size) {
        total = size;
        Sorting.class.notifyAll();
    }

    public static int[] sortedArray(int s) {
        int[] sorted = new int[s];
        for (int i = 0; i < s; i++) {
            sorted[i] = i + 1;
        }
        return sorted;
    }

    public static int[] inverseSortedArray(int s) {
        int[] inverse = new int[s];
        for (int i = 0; i < s; i++) {
            inverse[i] = s - i;
        }
        return inverse;
    }

    public static int[] randomArray(int s) {
        int[] random = new int[s];
        Random rand = new Random();
        for (int i = 0; i < s; i++) {
            random[i] = rand.nextInt(10000);
        }
        return random;
    }

    public static String getHighlightedArray(int[] arr, int index1, int index2) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == index1 || i == index2) {
                result.append("[").append(arr[i]).append("]");
            } else {
                result.append(arr[i]);
            }

            if (i < arr.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    countInterchanges += 1;
                }
                countComparisons += 1;
            }
            if (countInterchanges == 0) {
                break;
            }
        }
        return arr;
    }

    public static int[] quickSort(int[] arr, int l, int h) {
        if (l < h) {
            int pivLoc = partition(arr, l, h);
            quickSort(arr, l, pivLoc - 1);
            quickSort(arr, pivLoc + 1, h);
        }
        return arr;
    }

    public static int partition(int[] arr, int iBegin, int jEnd) {
        int i = iBegin;
        int j = jEnd;
        int pivLoc = i;
        while (true) {
            while (arr[pivLoc] <= arr[j] && pivLoc != j) {
                j--;
                countComparisons += 1;
            }
            countComparisons += 1;
            if (pivLoc == j) {
                break;
            } else if (arr[pivLoc] > arr[j]) {
                int temp = arr[j];
                arr[j] = arr[pivLoc];
                arr[pivLoc] = temp;
                pivLoc = j;
                countInterchanges += 1;
            }
            while (arr[pivLoc] >= arr[i] && pivLoc != i) {
                i++;
                countComparisons += 1;
            }
            countComparisons += 1;
            if (pivLoc == i) {
                break;
            } else if (arr[pivLoc] < arr[i]) {
                int temp = arr[i];
                arr[i] = arr[pivLoc];
                arr[pivLoc] = temp;
                pivLoc = i;
                countInterchanges += 1;
            }
        }
        return pivLoc;
    }

    public static int[] countingSort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }

        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int size;
        if (min < 0) {
            size = max - min + 1;
        } else {
            size = max + 1;
            min = 0; // To cancel its effect on index calculations.
        }
        int[] count = new int[size];
        for (int i = 0; i < array.length; i++) {
            count[array[i] + (-min)]++; // to shift elements if there are any negative numbers.
        }

        for (int i = 1; i < size; i++) {
            count[i] += count[i - 1];
        }

        int[] result = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            result[count[array[i] + (-min)] - 1] = array[i];
            count[array[i] - min]--;
        }
        return result;
    }

    public static void randomArrayOutput(int s) {
//        random = Arrays.copyOf(s);

        System.out.println("THE RANDOM ARRAY:");
        for (int i = 0; i < s; i++) {
            System.out.print(random[i] + " ");
        }
        System.out.println("");
        countComparisons = 0;
        countInterchanges = 0;

//        long startbubble = System.currentTimeMillis();
        int[] bubble = random.clone();
//        bubbleSort(bubble);
//        long endbubble = System.currentTimeMillis();
        System.out.println("BUBBLE SORT");
//        System.out.println("Run time: " + (endbubble - startbubble) + " ms.\n"
//                + "Number of comparisons: " + countComparisons + "\n"
//                + "Number of Interchanges: " + countInterchanges + "\n");
        bubbleSortPrint(bubble);

        countComparisons = 0;
        countInterchanges = 0;
        System.out.println("");
//        long startquick = System.currentTimeMillis();
        int[] quick = random.clone();
//        quickSort(quick, 0, s - 1);
//        long endquick = System.currentTimeMillis();
        System.out.println("QUICK SORT");
//        System.out.println("Run time: " + (endquick - startquick) + " ms.\n"
//                + "Number of comparisons: " + countComparisons + "\n"
//                + "Number of Interchanges: " + countInterchanges + "\n");
        quickSortPrint(quick, 0, s - 1);

        countComparisons = 0;
        countInterchanges = 0;
        System.out.println("");
//        long startcount = System.currentTimeMillis();
        int[] count = random.clone();
//        countingSort(count);
//        long endcount = System.currentTimeMillis();
        System.out.println("COUNTING SORT");
//        System.out.println("Run time: " + (endcount - startcount) + " ms.\n"
//                + "Number of comparisons: " + countComparisons + "\n"
//                + "Number of Interchanges: " + countInterchanges + "\n");
        countingSortPrint(count);
        System.out.println("_______________________________________________________________________________");
        countComparisons = 0;
        countInterchanges = 0;

    }

    public static void sortedArrayOutput(int s) {
        int[] sorted = sortedArray(s);

        System.out.println("THE SORTED ARRAY:");
        for (int i = 0; i < s; i++) {
            System.out.print(sorted[i] + " ");
        }
        System.out.println("");
        countComparisons = 0;
        countInterchanges = 0;

//        long startbubble = System.currentTimeMillis();
        int[] bubble = sorted.clone();
//        bubbleSort(bubble);
//        long endbubble = System.currentTimeMillis();
        System.out.println("BUBBLE SORT");
//        System.out.println("Run time: " + (endbubble - startbubble) + " ms.\n"
//                + "Number of comparisons: " + countComparisons + "\n"
//                + "Number of Interchanges: " + countInterchanges + "\n");
        bubbleSortPrint(bubble);

        countComparisons = 0;
        countInterchanges = 0;
        System.out.println("");
//        long startquick = System.currentTimeMillis();
        int[] quick = sorted.clone();
//        quickSort(quick, 0, s - 1);
//        long endquick = System.currentTimeMillis();
        System.out.println("QUICK SORT");
//        System.out.println("Run time: " + (endquick - startquick) + " ms.\n"
//                + "Number of comparisons: " + countComparisons + "\n"
//                + "Number of Interchanges: " + countInterchanges + "\n");
        quickSortPrint(quick, 0, s - 1);

        countComparisons = 0;
        countInterchanges = 0;
        System.out.println("");
//        long startcount = System.currentTimeMillis();
        int[] count = sorted.clone();
//        countingSort(count);
//        long endcount = System.currentTimeMillis();
        System.out.println("COUNTING SORT");
//        System.out.println("Run time: " + (endcount - startcount) + " ms.\n"
//                + "Number of comparisons: " + countComparisons + "\n"
//                + "Number of Interchanges: " + countInterchanges + "\n");
        countingSortPrint(count);
        System.out.println("_______________________________________________________________________________");
        countComparisons = 0;
        countInterchanges = 0;

    }

    public static void inverseArrayOutput(int s) {
        int[] inv = inverseSortedArray(s);

        System.out.println("THE INVERSELY SORTED ARRAY:");
        for (int i = 0; i < s; i++) {
            System.out.print(inv[i] + " ");
        }
        System.out.println("");
        countComparisons = 0;
        countInterchanges = 0;

//        long startbubble = System.currentTimeMillis();
        int[] bubble = inv.clone();
//        bubbleSort(bubble);
//        long endbubble = System.currentTimeMillis();
        System.out.println("BUBBLE SORT");
//        System.out.println("Run time: " + (endbubble - startbubble) + " ms.\n"
//                + "Number of comparisons: " + countComparisons + "\n"
//                + "Number of Interchanges: " + countInterchanges + "\n");
        bubbleSortPrint(bubble);

        countComparisons = 0;
        countInterchanges = 0;
        System.out.println("");
//        long startquick = System.currentTimeMillis();
        int[] quick = inv.clone();
//        quickSort(quick, 0, s - 1);
//        long endquick = System.currentTimeMillis();
        System.out.println("QUICK SORT");
//        System.out.println("Run time: " + (endquick - startquick) + " ms.\n"
//                + "Number of comparisons: " + countComparisons + "\n"
//                + "Number of Interchanges: " + countInterchanges + "\n");
        quickSortPrint(quick, 0, s - 1);

        countComparisons = 0;
        countInterchanges = 0;
        System.out.println("");
//        long startcount = System.currentTimeMillis();
        int[] count = inv.clone();
//        countingSort(count);
//        long endcount = System.currentTimeMillis();
        System.out.println("COUNTING SORT");
//        System.out.println("Run time: " + (endcount - startcount) + " ms.\n"
//                + "Number of comparisons: " + countComparisons + "\n"
//                + "Number of Interchanges: " + countInterchanges + "\n");
        countingSortPrint(count);
        System.out.println("_______________________________________________________________________________");
        countComparisons = 0;
        countInterchanges = 0;

    }

    public static int[] bubbleSortPrint(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    countInterchanges += 1;
                    // Print the array after each swap with swapped numbers highlighted
                    System.out.println("swaping: " + getHighlightedArray(arr, j, j + 1));
                }
                countComparisons += 1;
            }
            if (countInterchanges == 0) {
                System.out.println("No swaping happened ");
                break;
            }
        }
        return arr;
    }

    public static int[] countingSortPrint(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }

        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int size;
        if (min < 0) {
            size = max - min + 1;
        } else {
            size = max + 1;
            min = 0; // To cancel its effect on index calculations.
        }
        int[] count = new int[size];
        for (int i = 0; i < array.length; i++) {
            count[array[i] + (-min)]++; // to shift elements if there are any negative numbers.
        }

        for (int i = 1; i < size; i++) {
            count[i] += count[i - 1];
        }

        int[] result = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            result[count[array[i] + (-min)] - 1] = array[i];
            count[array[i] - min]--;
            displayArrayCounting(result);
        }
        return result;
    }

    public static void displayArrayCounting(int[] array) {
        System.out.print("The array: ");
        System.out.print("[ ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print(" ]");
        System.out.println();
    }

    public static void quickSortPrint(int[] arr, int l, int h) {

        if (l < h) {
            int pivLoc = partitionPrint(arr, l, h);
            quickSortPrint(arr, l, pivLoc - 1);
            quickSortPrint(arr, pivLoc + 1, h);

            // Print the highlighted array after each swap
            printHighlightedArray(arr, pivLoc, -1);
        }
    }

    public static int partitionPrint(int[] arr, int iBegin, int jEnd) {
        int i = iBegin;
        int j = jEnd;
        int pivLoc = i;
        while (true) {
            while (arr[pivLoc] <= arr[j] && pivLoc != j) {
                j--;
                countComparisons += 1;
            }
            countComparisons += 1;
            if (pivLoc == j) {
                break;
            } else if (arr[pivLoc] > arr[j]) {
                int temp = arr[j];
                arr[j] = arr[pivLoc];
                arr[pivLoc] = temp;
                pivLoc = j;
                countInterchanges += 1;
                printHighlightedArray(arr, pivLoc, -1);

            }
            while (arr[pivLoc] >= arr[i] && pivLoc != i) {
                i++;
                countComparisons += 1;
            }
            countComparisons += 1;
            if (pivLoc == i) {
                break;
            } else if (arr[pivLoc] < arr[i]) {
                int temp = arr[i];
                arr[i] = arr[pivLoc];
                arr[pivLoc] = temp;
                pivLoc = i;
                countInterchanges += 1;
                printHighlightedArray(arr, pivLoc, -1);

            }
        }
        return pivLoc;
    }

    public static void printHighlightedArray(int[] arr, int index1, int index2) {
        System.out.println("swap: " + getHighlightedArray(arr, index1, index2));
    }

}
