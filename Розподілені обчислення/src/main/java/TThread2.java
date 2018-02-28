//thread ,which task is to set slider to position 90


public class TThread2 extends Thread {
    @Override
    public void run()
    {
while (true)
{
    if(Main.slider.getValue()+10<90)
        Main.slider.setValue(Main.slider.getValue()+10);
    else
        Main.slider.setValue(Main.slider.getValue()+(90-Main.slider.getValue()));
}
    }
}
