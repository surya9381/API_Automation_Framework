package api.utilities;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

public class DataProviders {

	String path=System.getProperty("user.dir")+"//testData//UserData.xlsx";
	@DataProvider(name="Data")
	public String[][] getAllData() throws EncryptedDocumentException, IOException
	{
		XLUtility xl=new XLUtility(path);
		int rowcount=xl.getRowCount("Sheet1");
		int cellcount=xl.getCellCount("Sheet1", 1);
		String[][] apidata=new String[rowcount][cellcount];
		for(int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<cellcount;j++)
			{
				apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserName() throws EncryptedDocumentException, IOException
	{
		XLUtility xl=new XLUtility(path);
		int rowcount=xl.getRowCount("Sheet1");
		String[] apidata=new String[rowcount];
		for(int i=1;i<=rowcount;i++)
		{
			apidata[i-1]=xl.getCellData("Sheet1", i, 1);
		}
		return apidata;
	}
}
