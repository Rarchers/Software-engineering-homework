
import Been.DrugBeen;
import Model.DBManager;
import View.Check;
import View.StockView;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws SQLException, IOException {

//        String key = "123";
//        ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from Purchase where MedName = \""+key+"\"");
//        if (!rs.next()){
//            System.out.println("不存在信息");
//            String insert = "insert into Purchase (MedName) values (\""+key+"\")";
//            DBManager.getINSTANCE().executeUpdate(insert);
//        }else {
//            System.out.println("存在信息");
//        }

       // new Purchase().setVisible(true);
       /* ArrayList<DrugBeen> list = new Test().ReadFromExcel("D:\\data.xls");

        int i = 0;
        for (DrugBeen drugBeen : list){
            String medID = getMedID();
            ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from Drug where MedID = \""+medID+"\"");
            while (rs.next()&&i<=1000){
                medID = getMedID();
                rs =  DBManager.getINSTANCE().executeQuery("select * from Drug where MedID = \""+medID+"\"");
            }
            String sql = "insert into Drug (MedID,MedName,InTime,OutTime,Num) values (\""+medID+"\",\""+drugBeen.getDrugName()+"\",\""+drugBeen.getInTime()+"\",\""+drugBeen.getOutTime()+"\",\""+
                    drugBeen.getNum()+"\")";
            boolean res = DBManager.getINSTANCE().executeUpdate(sql);
            if (res) System.out.println("第 "+i+" 条数据插入成功");
            i++;
        }*/

        new Check().setVisible(true);



    }


    public static String getMedID(){
        String str="0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<9;i++){
            int number=random.nextInt(9);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


    public ArrayList<DrugBeen> ReadFromExcel(String fileName) throws IOException {

        InputStream is = new FileInputStream(fileName);

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        ArrayList<DrugBeen> list = new ArrayList<DrugBeen>();
        int size = hssfWorkbook.getNumberOfSheets();

        for (int numSheet = 0; numSheet < size; numSheet++) {

            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }

            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                int minColIx = hssfRow.getFirstCellNum();
                int maxColIx = hssfRow.getLastCellNum();
                DrugBeen drugBeen = new DrugBeen();
                int i = 1;
                for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                    HSSFCell cell = hssfRow.getCell(colIx);
                    if (cell == null) {
                        continue;
                    }
                    if (i == 1) {
                        drugBeen.setDrugName(cell.getStringCellValue());
                    } else if (i == 2) {
                        drugBeen.setInTime(cell.getStringCellValue());
                    } else if (i == 3) {
                        drugBeen.setOutTime(cell.getStringCellValue());
                    } else if (i == 5) {
                        drugBeen.setNum(Integer.parseInt(cell.getStringCellValue()));
                    }
                    i++;
                }
                list.add(drugBeen);
               // System.out.println(drugBeen.toString());

            }

        }
        hssfWorkbook.close();
        return  list;
    }


}
