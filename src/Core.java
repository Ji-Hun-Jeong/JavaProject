public class Core extends Thread
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
        Camera.GetInst().Update();
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
                sleep(1);
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
        ResourceMgr.GetInst().Init();
    }
    public static Core GetInst(){return m_inst;}
}