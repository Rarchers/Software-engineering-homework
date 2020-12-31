import Model.DBManager;
import View.Purchase;
import View.StockView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {

//        String key = "123";
//        ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from Purchase where MedName = \""+key+"\"");
//        if (!rs.next()){
//            System.out.println("不存在信息");
//            String insert = "insert into Purchase (MedName) values (\""+key+"\")";
//            DBManager.getINSTANCE().executeUpdate(insert);
//        }else {
//            System.out.println("存在信息");
//        }

       new Purchase().setVisible(true);
    }
}
