import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class SceneMgr
{
    private static SceneMgr m_inst = new SceneMgr();
    private Scene m_arrScene[]=null;
    private Scene m_curScene=null;
    private SceneMgr(){}
    public void Init()
    {
        m_arrScene=new Scene[1];
        m_arrScene[0]=new Scene();
        m_curScene=m_arrScene[0];
    }
    public void Update()
    {
        m_curScene.Update();
    }
    public void Render()
    {
        m_curScene.Render();
    }
    public static SceneMgr GetInst(){return m_inst;}
}
class Scene extends JFrame
{
    boolean m_bChange=false;
    JButton b=null;
    public Scene()
    {
        setTitle("몰라");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(new FlowLayout());
        b = new JButton("Action");
        b.setSize(100,200);
        b.addActionListener(new MyMouseListener());
        add(b);

        setVisible(true);
    }
    public void Update()
    {      
        if(!m_bChange)
        {
            b.setText("액션");
        }
        else
        {
            b.setText("Action");
        }
        m_bChange=!m_bChange;
    }
    public void Render()
    {
        getContentPane().repaint();
    }
}
class Core extends Thread
{
    private static Core m_inst = new Core();
    private Core(){}
    private void Progress()
    {
       	Update();
       	Render();
    }
    private void Update()
    {
       	SceneMgr.GetInst().Update();
    }
    private void Render()
    {
    	SceneMgr.GetInst().Render();
    }
    public void run()
    {
        while(true)
        {
            try
            {
                Progress();
                sleep(10);
            }
            catch(InterruptedException e)
            {
                return;
            }
        }
    }
    public void Init()
    {
        SceneMgr.GetInst().Init();
    }
    public static Core GetInst(){return m_inst;}
}
public class Main extends JFrame
{
    public static void main(String args[])
    {
        Core.GetInst().Init();
        Core.GetInst().start();
    }
}
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