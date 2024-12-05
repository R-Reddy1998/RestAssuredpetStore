package api.utilities;

import java.io.IOException;

public class DataProvider {

	// DataProvide1
		@org.testng.annotations.DataProvider(name="Data")
		public String[][] getAllData() throws IOException {
			String path = System.getProperty("user.dir")+"//testdata//Userdata.xlsx";
			
			Excelutility xlutil = new Excelutility(path);

			int rownum = xlutil.getRowCount("Sheet1");
			int colcount = xlutil.getCellCount("Sheet1", 1);

			String apidata[][] = new String[rownum][colcount];

			for (int i=1; i<=rownum; i++)
			{
				for (int j=0; j<colcount; j++)
					
				{
					apidata[i-1][j] = xlutil.getCellData("sheet1", i, j);
				}
			}

			return apidata;
		}

		
		@org.testng.annotations.DataProvider(name="UserName")
		public String[]getUserName() throws IOException
		
		{
	String path = System.getProperty("user.dir")+"//testdata//Userdata.xlsx";
			
			Excelutility xlutil = new Excelutility(path);

			int rownum=xlutil.getRowCount("Sheet1");
			
					String apidata []=new String[rownum];
					for (int i=1;i<=rownum;i++)
					{
						apidata[i-1]=xlutil.getCellData("Sheet1",i,1);
					}
					return apidata;
		}
		
}
 