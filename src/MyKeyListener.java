import java.awt.event.*;
enum KEY_TYPE
{
    DOWN, UP, LEFT, RIGHT, END,
}
public class MyKeyListener implements KeyListener
{
    private static MyKeyListener m_inst = new MyKeyListener();
    private MyKeyListener() { }
    private KEY_TYPE m_eKeyType = KEY_TYPE.END;
    public static MyKeyListener GetInst(){return m_inst;}

    public void keyPressed(KeyEvent e) // 키보드 누를 때 호출
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_DOWN:
                m_eKeyType = KEY_TYPE.DOWN;
                break;
            case KeyEvent.VK_UP:
                m_eKeyType = KEY_TYPE.UP;
                break;
            case KeyEvent.VK_LEFT:
                m_eKeyType = KEY_TYPE.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                m_eKeyType = KEY_TYPE.RIGHT;
                break;
            default:
                break;
        }
    }
    public void keyReleased(KeyEvent e) { } // 키보드 뗐을 때 호출
    public void keyTyped(KeyEvent e) { } // 문자키에만 반응
    public KEY_TYPE GetKeyState(){return m_eKeyType;}
    public void SetKeyType(KEY_TYPE keyType){m_eKeyType=keyType;}
}
