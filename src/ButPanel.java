import java.awt.*;
import javax.swing.*;
public class ButPanel extends JPanel
{
    private JButton m_arrButton[] = new JButton[BUTTON_TYPE.END.ordinal()];
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
        m_arrButton[BUTTON_TYPE.TABLE.ordinal()] = new JButton(BUTTON_TYPE.TABLE.name());
        
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
