import javax.swing.*;
import java.awt.event.*;
public class MyInputListener implements ActionListener
{
    private static MyInputListener m_inst = new MyInputListener();
    private MyInputListener(){}
    public static MyInputListener GetInst(){return m_inst;}
    public void actionPerformed(ActionEvent e)
    {
        JButton btn = (JButton)e.getSource();
    }
}