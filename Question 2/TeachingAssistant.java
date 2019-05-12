import java.util.concurrent.Semaphore;

class TeachingAssistant implements Runnable {
  private SignalSemaphore wakeupSignal;
  private Semaphore chairs;
  private final int sleepTime = 10000;

  /**
   * 
   * @param signal SignalSemaphore class
   * @param chairs Semaphore class for chairs
   * @param availChairs Semaphore class for available chairs
   */
  public TeachingAssistant(SignalSemaphore signal, Semaphore chairs, Semaphore availChairs) {
    Thread.currentThread();
    wakeupSignal = signal;
    this.chairs = chairs;
  }

  @Override
  public void run() {
    while (true) {
      try {
        System.out.println("No students left to help. The TA will nap.");
        wakeupSignal.release(); // will wake up the TA
        Thread.sleep(sleepTime);

        // If other students waiting.
        if (chairs.availablePermits() != 3) {
          do {
            Thread.sleep(sleepTime);
            chairs.release();
          } while (chairs.availablePermits() != 3);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
        continue;
      }
    }
  }
}
