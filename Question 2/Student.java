import java.util.concurrent.Semaphore;

class Student implements Runnable {
  private int programTime;
  private int studentNum;
  private SignalSemaphore wakeup;
  private Semaphore chairs;
  private Semaphore available;
  private final int helpingTime = 10000; // in milliseconds

  /**
   * 
   * @param program Programming time for students.
   * @param w SignalSemaphore
   * @param c Semaphore for chairs.
   * @param a Semaphore for available seats outside the office.
   * @param num Student number.
   */
  public Student(int program, SignalSemaphore w, Semaphore c, Semaphore a, int num) {
    programTime = program;
    wakeup = w;
    chairs = c;
    available = a;
    studentNum = num;
    Thread.currentThread();
  }

  /**
   * The run method will infinitely loop between programming and asking for help until the thread is
   * interrupted.
   */
  @Override
  public void run() {
    while (true) {
      try {
        // Program first.
        System.out
            .println("Student " + studentNum + " is programming for " + programTime + " seconds.");
        Thread.sleep(programTime * 1000);

        // Check to see if TA is available first.
        System.out.println("Student " + studentNum + " is checking to see if TA is available.");
        if (available.tryAcquire()) {
          try {
            wakeup.take(); // occupy the TA
            System.out.println("Student " + studentNum + " woke up the TA.");
            System.out.println("Student " + studentNum + " is now working with the TA.");
            Thread.sleep(helpingTime);
            System.out.println("Student " + studentNum + " is done working with TA.");
          } catch (InterruptedException e) {
            e.printStackTrace();
            continue;
          } finally {
            available.release();
          }
        } else {
          // Check to see if any chairs are available.
          System.out.println("Student " + studentNum
              + " cannot find TA. Student is checking for available chairs.");
          if (chairs.tryAcquire()) {
            try {
              // student wait outside in chairs while waiting for other student to finish with TA.
              System.out
                  .println("Student " + studentNum + " is sitting outside the office. " + "Student "
                      + studentNum + " is #" + ((3 - chairs.availablePermits())) + " in line.");
              available.acquire();
              System.out.println("Student " + studentNum + " is now working with the TA.");
              Thread.sleep(helpingTime);
              System.out.println("Student " + studentNum + " is done working with the TA.");
              available.release();
            } catch (InterruptedException e) {
              e.printStackTrace();
              continue;
            }
          } else {
            System.out.println("Student " + studentNum
                + " Cannot find TA. There are no available chairs. Student will go back to programming!");
          }
        }
      } catch (InterruptedException e) {
        break;
      }
    }
  }
}
