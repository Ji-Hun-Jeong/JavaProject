import java.awt.*;
import javax.swing.*;
public class IOPanel extends JPanel
{
    public IOPanel()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
        setBackground(Color.LIGHT_GRAY);

        JButton in = new JButton("INPUT");
        in.addActionListener(MyInputListener.GetInst());
            
        add(new JLabel("           INPUT           "));
        add(new TextField(15));
        add(in);
        add(new JLabel("           OUTPUT          "));
        add(new TextField(15));
    }
}