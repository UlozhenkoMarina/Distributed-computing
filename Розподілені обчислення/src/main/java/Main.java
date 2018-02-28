import javafx.scene.control.Slider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class Main {

    public static JSlider  slider=new JSlider();
    public static  TThread2 t2=new TThread2();
    public static  Thread1 t1=new Thread1();
    public static  JButton button=new JButton("Start");


    public static void main(String [] args)
    {
        //creation of the frame  , slider and button
        JFrame frame=new JFrame("Slider");
        frame.setSize(700,500);
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
        button.setBounds(0,0,250,100);
        slider.setLocation(0,150);
        frame.add(slider);
        frame.add(button);
        frame.setVisible(true);


//actions on the button click event
        // to start two threads which task is to change slider position
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // start t1 (Thread1(slider in 10)) and t2 (TThread2(slider in 90))
                    t1.start();
                    t2.start();
                    t1.setPriority(Thread.MAX_PRIORITY);
                    // using setPriority() we could change priority of the threads
                   for (int i=0;i<10;i++) {
                        t1.setPriority(Thread.MAX_PRIORITY);
                        t2.setPriority(Thread.MIN_PRIORITY);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e1) {
                        }
                        System.out.println("t1..."+t1.getPriority()+":" + slider.getValue());
                           t2.setPriority(Thread.MAX_PRIORITY);
                           t1.setPriority(Thread.MIN_PRIORITY);
                        System.out.println("t1..."+t1.getPriority()+":" + slider.getValue());
                    }
                    // to prevent more than one starting of the threads t1 and t2
                button.setEnabled(false);
            }
        });


    }
}
