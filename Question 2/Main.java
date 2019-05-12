import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class Main {
  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);
    System.out.println("Enter the number of students: ");

    // Number of students.
    int numberofStudents = input.nextInt();
    input.close();

    // Create semaphores.
    SignalSemaphore wakeup = new SignalSemaphore();
    Semaphore chairs = new Semaphore(3);
    Semaphore available = new Semaphore(1);

    Random studentWait = new Random();

    for (int i = 0; i < numberofStudents; i++) {
      // create a thread for every student
      Thread student =
          new Thread(new Student(studentWait.nextInt(20), wakeup, chairs, available, i + 1));
      student.start();
    }
    // thread for the TA.
    Thread ta = new Thread(new TeachingAssistant(wakeup, chairs, available));
    ta.start();
  }
}

