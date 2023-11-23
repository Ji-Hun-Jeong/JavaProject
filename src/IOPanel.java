import java.awt.*;
import javax.swing.*;
public class IOPanel extends MyPanel
{
    private TextField m_outputText = new TextField(15);
    public void Update(){}
    public IOPanel()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
        setBackground(Color.LIGHT_GRAY);

        add(new JLabel("           OUTPUT          "));
        add(m_outputText);
    }
}