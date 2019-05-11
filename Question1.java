import java.util.*;
import java.lang.*;

public class Question1 {

    static int LOW = 0,
               HIGH = 199, // 4999 for the homework
               REQUESTS = 8, // 1000 for the homework
               INF = Integer.MAX_VALUE;

    public static void main(String arg[]) {

        int initPos = 53; // arg[1] for homework
        ArrayList<Integer> queue = new ArrayList<Integer>(
            Arrays.asList(98, 183, 37, 122, 14, 124, 65, 67)); // Randomly generated for homework

        fcfs(queue, initPos);
        sstf(queue, initPos);
        // scan(queue, initPos);
        // cscan(queue, initPos);
        // look(queue, initPos);
        // clook(queue, initPos);
    }


    // Algorithms

    // FCFS
    public static void fcfs(ArrayList<Integer> queue, int initPos) {

        int prevPos = initPos,
            currPos,
            headMvmt = 0;

        // Go through the queue in order
        for (int i = 0; i < REQUESTS; i++) {

            currPos = queue.get(i);
            headMvmt += Math.abs(currPos - prevPos);
            prevPos = currPos;
        }

        System.out.println("FCFS: " + headMvmt + " cylinders.");
    }

    // SSTF
    public static void sstf(ArrayList<Integer> queue, int initPos) {

        queue = new ArrayList<Integer>(queue);
        queue.sort(null);

        int leftIdx,
            rightIdx = 0,
            currPos = initPos,
            leftReq = LOW,
            rightReq = HIGH,
            leftDist,
            rightDist,
            headMvmt = 0;

        while (rightIdx < REQUESTS && queue.get(rightIdx) < initPos) {
            rightIdx++;
        }
        leftIdx = rightIdx - 1;

        System.out.println(currPos);
        for (int n = 0; n < REQUESTS; n++) {

            // Calculate distances to requests
            if (leftIdx >= 0) {
                leftReq = queue.get(leftIdx);
                leftDist = currPos - leftReq;
            }
            else {
                leftDist = INF;
            }

            if (rightIdx < REQUESTS) {
                rightReq = queue.get(rightIdx);
                rightDist = rightReq - currPos;
            }
            else {
                rightDist = INF;
            }

            System.out.println("leftReq: " + leftReq);
            System.out.println("rightReq: " + rightReq);

            // Go to the closer request
            if (leftDist <= rightDist) {
                currPos = leftReq;
                leftIdx -= 1;
                headMvmt += leftDist;
            }
            else {
                currPos = rightReq;
                rightIdx += 1;
                headMvmt += rightDist;
            }
            System.out.println(currPos);
        }

        System.out.println("SSTF: " + headMvmt + " cylinders.");
    }

    // SCAN
    public static void scan(ArrayList<Integer> queue, int initPos) {

        // queue = new ArrayList<Integer>(queue);
        // queue.add(initPos);
        // int firstCyl = 3;
        // boolean goingUp = initPos < (HIGH - LOW) / 2;



        // queue = new ArrayList<Integer>(queue);
        // queue.add(initPos);

        // boolean downFirst = initPos < (HIGH - LOW) / 2;
        // if (downFirst) {
        //     queue.add(LOW);
        // }
        // else {
        //     queue.add(HIGH);
        // }

        // queue.sort(null);


        // // Figure out the order
        // int initI = queue.indexOf(initPos);
        // ArrayList<Integer> lowerPoses = new ArrayList<Integer>(queue.subList(0, initI));
        // removeArrRange(queue, 0, initI);
        // reverseArr(lowerPoses);
        // if (downFirst) {
        //     queue.addAll(1, lowerPoses);
        // }
        // else {
        //     queue.addAll(lowerPoses);
        // }

        // System.out.println("SCAN");
        // printArr(queue);
        // System.out.println(calcHeadMvmt(queue));
    }

    // C-SCAN
    public static void cscan(ArrayList<Integer> queue, int initPos) {

        queue = new ArrayList<Integer>(queue);
        queue.sort(null);

        int initIdx = 0,
            currPos = initPos,
            nextReq,
            headMvmt = 0;

        while (initIdx < REQUESTS && queue.get(initIdx) < initPos) {
            init++;
        }

        for (int i = initIdx; initIdx < REQUESTS; i++) {
            nextReq = queue.get(i);
            headMvmt;
        }
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
}
