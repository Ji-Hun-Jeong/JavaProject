import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Scene extends JFrame
{
    Container m_container = null;
    public Scene(String str)
    {
        super(str);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        m_container = getContentPane();   
    }
    public void Update()
    {

    }
    public void Render()
    {
        getContentPane().repaint();
    }
}
class ButPanel extends JPanel
{
    public ButPanel()
    {
        GridLayout grid = new GridLayout(5, 2);
        grid.setVgap(50);
        grid.setHgap(20);
        setBackground(Color.DARK_GRAY);
        setLayout(grid);
        // FlowLayout에서 텍스트 내용을 입력하고, 입력칸의 크기를 조정
    }
}
class MainPanel extends JPanel
{
    Vec2 m_vFirstPos = new Vec2();
    Vec2 m_vCurPos = new Vec2();
    public MainPanel()
    {
        setLayout(null);
        setBackground(Color.WHITE);
    }
    public void SetMousePos(Vec2 inFirst,Vec2 inCur){m_vFirstPos = inFirst; m_vCurPos = inCur;}
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.drawLine(m_vFirstPos.x,m_vFirstPos.y,m_vCurPos.x,m_vCurPos.y);
    }
}
class IOPanel extends JPanel
{
    public IOPanel()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
        setBackground(Color.LIGHT_GRAY);

        JButton in = new JButton("INPUT");
        in.addActionListener(new InputActionListener());
            
        add(new JLabel("           INPUT           "));
        add(new TextField(15));
        add(in);
        add(new JLabel("           OUTPUT          "));
        add(new TextField(15));
    }
}