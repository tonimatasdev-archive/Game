package net.tonimatasdev;

public class TickManager implements Runnable {
    Thread tickThread;

    public TickManager() {

    }
    public void startTickThread() {
        //tickThread = new Thread(this);
        //tickThread.start();
    }

    @Override
    public void run() {
        while (tickThread != null) {
        }
    }
}
