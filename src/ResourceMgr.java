import java.util.TreeMap;
import javax.swing.ImageIcon;

public class ResourceMgr 
{
    private TreeMap<String, ImageIcon> m_mapImages = new TreeMap<String, ImageIcon>();
    public void LoadImage(String inKey, String inFinalPath)
    {
        if(m_mapImages.containsKey(inKey))
            return;
        ImageIcon imageIcon = new ImageIcon(inFinalPath);
        m_mapImages.put(inKey, imageIcon);
    }
    public ImageIcon GetImageIcon(String inKey)
    {
        ImageIcon imageIcon = m_mapImages.get(inKey);
        return imageIcon;
    }
    public void Init()
    {
        LoadImage("AND","./Logic Gate/AND.png");
        LoadImage("OR","../Logic Gate/OR.png");
        LoadImage("NAND","./Logic Gate/NAND.png");
        LoadImage("NOR","./Logic Gate/NOR.png");
        LoadImage("XOR","./Logic Gate/XOR.png");
        LoadImage("XNOR","./Logic Gate/XNOR.png");
        LoadImage("INVERTER","./Logic Gate/INVERTER.png");
        LoadImage("BUFFER","./Logic Gate/BUFFER.png");
    }
    private static ResourceMgr m_inst = new ResourceMgr();
    private ResourceMgr(){}
    public static ResourceMgr GetInst() {return m_inst;}
}
