import java.util.TreeMap;
import javax.swing.ImageIcon;
public class ResourceMgr 
{
    private TreeMap<String,ImageIcon> m_mapImageIcons = new TreeMap<String,ImageIcon>();
    public void LoadImage(String inKey, String inFinalPath)
    {
        ImageIcon imageIcon = new ImageIcon(inFinalPath);
        m_mapImageIcons.put(inKey,imageIcon);
    }
    public ImageIcon GetImageIcon(String inKey)
    {
        return m_mapImageIcons.get(inKey);
    }
    public void Init()
    {
        LoadImage("AND","./Logic Gate/AND.png");
        LoadImage("OR","./Logic Gate/OR.png");
        LoadImage("NAND","./Logic Gate/NAND.png");
        LoadImage("NOR","./Logic Gate/NOR.png");
        LoadImage("XOR","./Logic Gate/XOR.png");
        LoadImage("XNOR","./Logic Gate/XNOR.png");
        LoadImage("INVERTER","./Logic Gate/INVERTER.png");
        LoadImage("BUFFER","./Logic Gate/BUFFER.png");
        LoadImage("Result0_Blue","./Logic Gate/Result0_Blue.png");
        LoadImage("Result0_Blue_Small","./Logic Gate/Result0_Blue_Small.png");
        LoadImage("Result0_Red","./Logic Gate/Result0_Red.png");
        LoadImage("Result0_Red_Small","./Logic Gate/Result0_Red_Small.png");
        LoadImage("INPUT","./Logic Gate/Result0_Red.png");
        LoadImage("OUTPUT","./Logic Gate/Result0_Red.png");
        LoadImage("Result1_Blue","./Logic Gate/Result1_Blue.png");
        LoadImage("Result1_Red","./Logic Gate/Result1_Red.png");
        LoadImage("Result1_Blue_Small","./Logic Gate/Result1_Blue_Small.png");
        LoadImage("Port","./Logic Gate/Port.png");
        LoadImage("BackGround", "./Background/Background.png");
        LoadImage("BackGround1", "./Background/Background1.png");
    }
    private static ResourceMgr m_inst = new ResourceMgr();
    public static ResourceMgr GetInst() {return m_inst;}
    private ResourceMgr(){}
}
