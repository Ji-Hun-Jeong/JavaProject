import java.awt.*;
import javax.swing.*;
public class ButPanel extends MyPanel
{
    private JButton m_arrButton[] = new JButton[BUTTON_TYPE.END.ordinal()];
    public void Update(){}
    public ButPanel()
    {
        GridLayout grid = new GridLayout(5, 2);
        grid.setVgap(50);
        grid.setHgap(20);

        m_arrButton[BUTTON_TYPE.BUFFER.ordinal()] = new JButton(BUTTON_TYPE.BUFFER.name());
        m_arrButton[BUTTON_TYPE.INVERTER.ordinal()] = new JButton(BUTTON_TYPE.INVERTER.name());
        m_arrButton[BUTTON_TYPE.AND.ordinal()] = new JButton(BUTTON_TYPE.AND.name());
        m_arrButton[BUTTON_TYPE.OR.ordinal()] = new JButton(BUTTON_TYPE.OR.name());
        m_arrButton[BUTTON_TYPE.NAND.ordinal()] = new JButton(BUTTON_TYPE.NAND.name());
        m_arrButton[BUTTON_TYPE.XOR.ordinal()] = new JButton(BUTTON_TYPE.XOR.name());
        m_arrButton[BUTTON_TYPE.NOR.ordinal()] = new JButton(BUTTON_TYPE.NOR.name());
        m_arrButton[BUTTON_TYPE.XNOR.ordinal()] = new JButton(BUTTON_TYPE.XNOR.name());
        m_arrButton[BUTTON_TYPE.LINE.ordinal()] = new JButton(BUTTON_TYPE.LINE.name());
        m_arrButton[BUTTON_TYPE.INPUT.ordinal()] = new JButton(BUTTON_TYPE.INPUT.name());
        m_arrButton[BUTTON_TYPE.OUTPUT.ordinal()] = new JButton(BUTTON_TYPE.OUTPUT.name());
        m_arrButton[BUTTON_TYPE.CHANGE.ordinal()] = new JButton(BUTTON_TYPE.CHANGE.name());
        m_arrButton[BUTTON_TYPE.CALCULATE.ordinal()] = new JButton(BUTTON_TYPE.CALCULATE.name());
        //m_arrButton[BUTTON_TYPE.DELETE.ordinal()] = new JButton(BUTTON_TYPE.DELETE.name());
        m_arrButton[BUTTON_TYPE.CLEAR.ordinal()] = new JButton(BUTTON_TYPE.CLEAR.name());

        //m_arrButton[BUTTON_TYPE.DELETE.ordinal()].setBackground(Color.YELLOW);
        m_arrButton[BUTTON_TYPE.INPUT.ordinal()].setBackground(Color.YELLOW);
        m_arrButton[BUTTON_TYPE.OUTPUT.ordinal()].setBackground(Color.YELLOW);
        m_arrButton[BUTTON_TYPE.CLEAR.ordinal()].setBackground(Color.YELLOW);
        m_arrButton[BUTTON_TYPE.CHANGE.ordinal()].setBackground(Color.YELLOW);
        m_arrButton[BUTTON_TYPE.CALCULATE.ordinal()].setBackground(Color.YELLOW);
        m_arrButton[BUTTON_TYPE.LINE.ordinal()].setBackground(Color.CYAN);
        
        for(int i=0;i<BUTTON_TYPE.END.ordinal();++i)
        {
            m_arrButton[i].addActionListener(MyBtnListener.GetInst());
            add(m_arrButton[i]);
        }

        setBackground(Color.DARK_GRAY);
        setLayout(grid);
        // FlowLayout에서 텍스트 내용을 입력하고, 입력칸의 크기를 조정
    }
    public JButton GetButtons(BUTTON_TYPE type){return m_arrButton[type.ordinal()];}
}
