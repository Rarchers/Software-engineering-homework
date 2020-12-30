import Model.DBManager;
import View.StockView;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        //DBManager manager = new DBManager();
        //manager.executeQuery("select * from worker");

        new StockView().setVisible(true);
    }
}
