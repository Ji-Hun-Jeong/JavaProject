public class SceneMgr
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