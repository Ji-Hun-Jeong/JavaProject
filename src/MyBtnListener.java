import javax.swing.*;
import java.awt.event.*;
public class MyBtnListener implements ActionListener
{
    private static MyBtnListener m_inst=new MyBtnListener();
    private MyBtnListener(){}
    public static MyBtnListener GetInst(){return m_inst;}
    private BUTTON_TYPE m_eButtonType= BUTTON_TYPE.END;
    public void actionPerformed(ActionEvent e)
    {
        JButton btn = (JButton)e.getSource();
        switch (btn.getText())
        {
            case "AND":
                m_eButtonType = BUTTON_TYPE.AND;
                break;
            case "OR":
                m_eButtonType = BUTTON_TYPE.OR;
                break;
            case "NAND":
                m_eButtonType = BUTTON_TYPE.NAND;
                break;
            case "NOR":
                m_eButtonType = BUTTON_TYPE.NOR;
                break;
            case "XOR":
                m_eButtonType = BUTTON_TYPE.XOR;
                break;
            case "XNOR":
                m_eButtonType = BUTTON_TYPE.XNOR;
                break;
            case "BUFFER":
                m_eButtonType = BUTTON_TYPE.BUFFER;
                break;
            case "INVERTER":
                m_eButtonType = BUTTON_TYPE.INVERTER;
                break;
            case "LINE":
                m_eButtonType = BUTTON_TYPE.LINE;
                break;
            case "TABLE":
                m_eButtonType = BUTTON_TYPE.TABLE;
                break;
            default:
                m_eButtonType = BUTTON_TYPE.END;
                break;
        }
    }
    public BUTTON_TYPE GetBtnType(){return m_eButtonType;}
}