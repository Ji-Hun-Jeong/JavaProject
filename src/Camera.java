public class Camera 
{
    private static Camera m_inst = new Camera();
    private Camera(){}
    public static Camera GetInst(){return m_inst;}
    private Vec2 m_vPos = new Vec2();
    public void Update()
    {
        KEY_TYPE keyType = MyKeyListener.GetInst().GetKeyState();
        switch(keyType)
        {
            case DOWN:
                m_vPos.y+=200*0.1;
                break;
            case UP:
                m_vPos.y-=200*0.1;
                break;
            case LEFT:
                m_vPos.x-=200*0.1;
                break;
            case RIGHT:
                m_vPos.x+=200*0.1;
                break;
            default:
                break;
        }
        MyKeyListener.GetInst().SetKeyType(KEY_TYPE.END);
    }
    public Vec2 GetCameraPos(){return m_vPos.Copy();}
}
