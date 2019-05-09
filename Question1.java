import java.util.*;
import java.lang.*;

public class Question1 {

    static int LOW = 0,
               HIGH = 199; // 4999 for the homework

    

    public static void main(String arg[]) {

        int initPos = 53; // arg[1] for homework
        ArrayList<Integer> queue = new ArrayList<Integer>(
            Arrays.asList(98, 183, 37, 122, 14, 124, 65, 67)); // Randomly generated for homework

        // fcfs(queue, initPos);
        // sstf(queue, initPos);
        scan(queue, initPos);
        // cscan(queue, initPos);
        // look(queue, initPos);
        // clook(queue, initPos);
    }

    // Methods

    // Calculating total head movement
    public static int calcHeadMvmt(ArrayList<Integer> positions) {
        int total = 0,
            arrSize = positions.size(),
            a,
            b = positions.get(0);
        for (int i = 0; i < arrSize - 1; i++) {
            a = b;
            b = positions.get(i + 1);

            if (!((a == LOW && b == HIGH) || (a == HIGH && b == LOW)))
                total += Math.abs(b - a);
        }

        return total;
    }

    // Print array
    public static void printArr(ArrayList<Integer> arr) {
        int arrSize = arr.size();
        for (int i = 0; i < arrSize - 1; i++) {
            System.out.print(arr.get(i));
            System.out.print(", ");
        }
        System.out.println(arr.get(arrSize - 1));
    }

    // Remove array range
    public static void removeArrRange(ArrayList<Integer> arr, int fromIndex, int toIndex) {
        int rangeLen = toIndex - fromIndex;
        for (int i = 0; i < rangeLen; i++) {
            arr.remove(fromIndex);
        }
    }

    // Reverse array
    public static void reverseArr(ArrayList<Integer> arr) {
        int arrSize = arr.size(),
            halfPoint = arrSize / 2;
        for (int i = 0; i < halfPoint; i++) {
            int a = arr.get(i),
                b = arr.get(arrSize - 1 - i);

            arr.set(i, b);
            arr.set(arrSize - 1 - i, a);
        }
    }

    // Algorithms

    // SCAN
    public static void scan(ArrayList<Integer> queue, int initPos) {

        ArrayList<Integer> seq = new ArrayList<Integer>(queue);
        seq.add(initPos);

        boolean downFirst = initPos < (HIGH - LOW) / 2;
        if (downFirst) {
            seq.add(LOW);
        }
        else {
            seq.add(HIGH);
        }

        seq.sort(null);


        // Figure out the order
        int initI = seq.indexOf(initPos);
        ArrayList<Integer> lowerPoses = new ArrayList<Integer>(seq.subList(0, initI));
        removeArrRange(seq, 0, initI);
        reverseArr(lowerPoses);
        if (downFirst) {
            seq.addAll(1, lowerPoses);
        }
        else {
            seq.addAll(lowerPoses);
        }

        System.out.println("SCAN");
        printArr(seq);
        System.out.println(calcHeadMvmt(seq));
    }
}
