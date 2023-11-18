import java.awt.event.*;
import javax.swing.*;
class MyMouseListener implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton)e.getSource();
        if(b.getText().equals("Action"))
        {
            b.setText("액션");
        }
        else
        {
            b.setText("Action");
        }
    }
}