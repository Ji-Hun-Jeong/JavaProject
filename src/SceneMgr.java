enum SCENE_TYPE
{
    WORKING_SCENE,
    END,
}
public class SceneMgr
{
    private static SceneMgr m_inst = new SceneMgr();
    private Scene m_arrScene[]=new Scene[SCENE_TYPE.END.ordinal()];
    private Scene m_curScene=null;
    private SceneMgr(){}
    public void Init()
    {
        m_arrScene[SCENE_TYPE.WORKING_SCENE.ordinal()]=new Working_Scene();
        m_curScene=m_arrScene[SCENE_TYPE.WORKING_SCENE.ordinal()];
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