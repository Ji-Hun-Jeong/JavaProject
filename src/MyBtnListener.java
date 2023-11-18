import javax.swing.*;
import java.awt.event.*;
class MyBtnListener implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        JButton btn = (JButton)e.getSource();
        switch (btn.getText())
        {
            case "AND":
            
            case "OR":

            case "NAND":

            case "NOR":

            case "XOR":

            case "XNOR":

            case "BUFFER":

            case "INVERTER":

            case "LINE":

            case "TABLE":

            default:

        }
    }
}