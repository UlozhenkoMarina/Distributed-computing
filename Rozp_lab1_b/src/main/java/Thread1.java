//thread ,which task is to set slider to position 10


public class Thread1 extends Thread{
    @Override
    public void run() {
        //using semaphore for interrupting work of the thread
        while (Main.semaphore==1) {
            if (Main.slider.getValue() - 10 > 10) {
                Main.slider.setValue(Main.slider.getValue() - 10);
            } else
                Main.slider.setValue(Main.slider.getValue() - (Main.slider.getValue() - 10));
        }
    }
}
