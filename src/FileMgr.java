import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class FileMgr 
{
    private String m_strAbsolutePath = new File("").getAbsolutePath()+"\\";
    public void WriteFile(String relativePath, String inFormula,Vector<Integer> vecValues,Vector<Character> vecSymbols,int inResult)
    {
        try
        {
            Integer result = inResult;
            String finalPath = m_strAbsolutePath+relativePath;
            BufferedReader fileReader = new BufferedReader(new FileReader(finalPath));
            
            String readStr = null;
            String finalStr = new String();

            while((readStr = fileReader.readLine())!=null)
            {
                finalStr += readStr+"\n";
            }
            FileWriter fileWriter = new FileWriter(finalPath);
            for(int i=0;i<vecSymbols.size();++i)
            {
                finalStr+=vecSymbols.get(i)+" = "+vecValues.get(i)+"\n";
            }
            finalStr+=inFormula+" = "+result.toString()+"\n\n";
            fileWriter.write(finalStr);
            fileWriter.close();
            fileReader.close();
        }
        catch(IOException e)
        {
            System.err.println("입출력 오류");
        }
    }
    public void ClearFile(String relativePath)
    {
        try
        {
            String finalPath = m_strAbsolutePath+relativePath;
            FileWriter fileWriter = new FileWriter(finalPath);
            fileWriter.close();
        }
        catch(IOException e)
        {
            System.err.println("입출력 오류");
        }
    }

    private static FileMgr m_inst = new FileMgr();
    private FileMgr(){}
    public static FileMgr GetInst(){return m_inst;}
}
