import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class Main {
    public static JSlider  slider=new JSlider();
    public static TThread2 t2;
    public static Thread1 t1;
    public static JButton button_s1=new JButton("Start1");
    public static JButton button_s2=new JButton("Start2");
    public static JButton button_c1=new JButton("Cancel1");
    public static JButton button_c2=new JButton("Cancel2");
    public static JLabel label=new JLabel();

    public static int semaphore =0;

    public static void main(String [] args)
    {
        //creation of the frame  , slider and buttons
        JFrame frame=new JFrame("Slider");
        frame.setSize(700,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLayout(null);

        slider.setSize(500,300);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        Hashtable<Integer,JLabel>position=new Hashtable<Integer, JLabel>();
        for (int i=0;i<110;i+=10)
        {
            position.put(i,new JLabel(Integer.toString(i)));
        }
        slider.setLabelTable(position);
        button_s1.setBounds(0,0,250,100);
        button_s2.setBounds(300,0,250,100);
        button_c1.setBounds(0,150,250,100);
        button_c2.setBounds(300,150,250,100);
        slider.setLocation(0,450);
        frame.add(slider);
        frame.add(button_s1);
        frame.add(button_s2);
        frame.add(button_c1);
        frame.add(button_c2);
        label.setBounds(100,750,200,100);
        frame.add(label);
        label.setVisible(true);
        frame.setVisible(true);
        label.setOpaque(true);

//actions on the button click events
        // click on button_s1 start thread t1(if it is possible) and give it minimal priority
        // and block button_c2(cancel button of the another thread)
        //and change semaphore value (make it free)
        button_s1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(semaphore==0)
                {  semaphore =1;
                t1=new Thread1();
         t1.start();
         t1.setPriority(Thread.MIN_PRIORITY);
         button_c2.setEnabled(false);}
         else
                {
                    label.setBackground(Color.red);
                    label.setText("ANOTHER THREAD");
                }
            }
        });

        // click on button_s2 start thread t2(if it is possible) and give it maximal priority
        // and block button_c2(cancel button of the another thread)
        //and change semaphore value(make it free)
        button_s2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(semaphore==0)
                { semaphore=1;
                t2=new TThread2();
                t2.start();
                t2.setPriority(Thread.MAX_PRIORITY);
                button_c1.setEnabled(false);}
                else
                {
                    label.setBackground(Color.red);
                    label.setText("ANOTHER THREAD");
                }
            }
        });

        //click on button_c1 stop working t1 thread and make enable button_c2(cancel button of another thread)
        //and change semaphore value (make it not free)
        button_c1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f();
                button_c2.setEnabled(true);
            }
        });

        //click on button_c2 stop working t2 thread and make enable button_c1(cancel button of another thread)
        //and change semaphore value (make it not free)
        button_c2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f();
                button_c1.setEnabled(true);
            }
        });

    }


    //function for release of the semaphore
    //and cleaning the label (it can have text message("Another thread"))
    public static void f()
    {
        semaphore =0;
        label.setText("");
        label.setBackground(Color.lightGray);
    }
}
