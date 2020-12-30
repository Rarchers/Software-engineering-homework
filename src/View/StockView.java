/*
 * Created by JFormDesigner on Wed Dec 30 20:35:28 GMT+08:00 2020
 */

package View;

import java.beans.*;
import Model.DBManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * @author rarcher
 */
public class StockView extends JFrame {
    public StockView() {
        initComponents();
    }

    private void searcheditFocusGained(FocusEvent e) {
        String temp = searchedit.getText();
        if(temp.equals("输入需要筛选的药品名")){
            searchedit.setText("");
            searchedit.setForeground(Color.BLACK);
        }
    }

    private void searcheditFocusLost(FocusEvent e) {
        String temp = searchedit.getText();
        if(temp.equals("")){
            searchedit.setText("输入需要筛选的药品名");
            searchedit.setForeground(Color.GRAY);
        }


    }

    private void table1MouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){

        }
    }

    private void queryAllActionPerformed(ActionEvent e) {
        initTable(table1,1,"");
        showError();

    }

    private void table1PropertyChange(PropertyChangeEvent e) {
        initTable(table1,1,"");
        showError();
    }

    private void searchActionPerformed(ActionEvent e) {
        String drugName = searchedit.getText();
        initTable(table1,0,drugName);
        showError();
    }

    private void button2ActionPerformed(ActionEvent e) {
        Error.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - rarcher
        ResourceBundle bundle = ResourceBundle.getBundle("View.form");
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        scrollPane1 = new JScrollPane();
        panel2 = new JPanel();
        panel3 = new JPanel();
        searchedit = new JTextField();
        search = new JButton();
        label1 = new JLabel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        panel1 = new JPanel();
        button1 = new JButton();
        Error = new JDialog();
        button2 = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText(bundle.getString("menu1.text"));

                //---- menuItem2 ----
                menuItem2.setText(bundle.getString("menuItem2.text"));
                menu1.add(menuItem2);

                //---- menuItem3 ----
                menuItem3.setText(bundle.getString("menuItem3.text"));
                menu1.add(menuItem3);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {

            //======== panel2 ========
            {
                panel2.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder
                ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border
                .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt
                . Color .red ) ,panel2. getBorder () ) ); panel2. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void
                propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
                ;} } );
                panel2.setLayout(new BorderLayout());

                //======== panel3 ========
                {
                    panel3.setLayout(new BorderLayout());

                    //---- searchedit ----
                    searchedit.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                    searchedit.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            searcheditFocusGained(e);
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            searcheditFocusLost(e);
                        }
                    });
                    panel3.add(searchedit, BorderLayout.CENTER);

                    //---- search ----
                    search.setText("\u67e5\u8be2");
                    search.addActionListener(e -> searchActionPerformed(e));
                    panel3.add(search, BorderLayout.EAST);

                    //---- label1 ----
                    label1.setText("\u836f\u54c1\u7b5b\u9009");
                    panel3.add(label1, BorderLayout.WEST);
                }
                panel2.add(panel3, BorderLayout.NORTH);

                //======== scrollPane2 ========
                {

                    //---- table1 ----
                    table1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            table1MouseClicked(e);
                        }
                    });
                    table1.addPropertyChangeListener(e -> table1PropertyChange(e));
                    scrollPane2.setViewportView(table1);
                }
                panel2.add(scrollPane2, BorderLayout.CENTER);
            }
            scrollPane1.setViewportView(panel2);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(17, 0));

            //---- button1 ----
            button1.setText("\u67e5\u8be2\u6240\u6709\u5e93\u5b58");
            button1.addActionListener(e -> queryAllActionPerformed(e));
            panel1.add(button1);
        }
        contentPane.add(panel1, BorderLayout.WEST);
        pack();
        setLocationRelativeTo(getOwner());

        //======== Error ========
        {
            Error.setAlwaysOnTop(true);
            var ErrorContentPane = Error.getContentPane();
            ErrorContentPane.setLayout(new BorderLayout());

            //---- button2 ----
            button2.setText("\u786e\u5b9a");
            button2.addActionListener(e -> button2ActionPerformed(e));
            ErrorContentPane.add(button2, BorderLayout.PAGE_END);

            //---- label2 ----
            label2.setText("\u62b1\u6b49\uff0c\u672a\u67e5\u8be2\u5230\u4efb\u4f55\u4fe1\u606f");
            ErrorContentPane.add(label2, BorderLayout.CENTER);

            //---- label3 ----
            label3.setText("\u67e5\u8be2\u5f02\u5e38\uff1a");
            ErrorContentPane.add(label3, BorderLayout.PAGE_START);
            Error.setSize(200, 200);
            Error.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - rarcher
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JScrollPane scrollPane1;
    private JPanel panel2;
    private JPanel panel3;
    private JTextField searchedit;
    private JButton search;
    private JLabel label1;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JPanel panel1;
    private JButton button1;
    private JDialog Error;
    private JButton button2;
    private JLabel label2;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private boolean showError = false;
    private void initTable(JTable table1,int type,String drugName){

        String[] list = {"药品编号", "药品名称", "生产日期","过期日期", "剩余库存"};
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        for(String i : list)
            model.addColumn(i);
        Object[][] arr;
        if (type == 1){
            arr = refreshData();
        }else {
            arr = searchData(drugName);
        }
        System.out.println(arr[0][0]);

        if (arr[0][0] == null){
            showError = true;
        }
        for (Object[] i : arr)
            model.addRow(i);
        table1.setEnabled(false);
    }

    private Object[][] refreshData(){
        Object[][] arr = new Object[0][];
        String sql = "select * from drug";
        try{
            ResultSet rs = DBManager.getINSTANCE().executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            System.out.println("count"+count);
            arr = new Object[count + 1][5];
            int j = 0;
            while (rs.next()){
                String medID = rs.getString("MedID");
                String medName = rs.getString("MedName");
                String inTime = rs.getString("InTime");
                String outTime = rs.getString("OutTime");
                int num = rs.getInt("Num");
                arr[j][0] = medID;
                arr[j][1] = medName;
                arr[j][2] = inTime;
                arr[j][3] = outTime;
                arr[j][4] = num;
                j++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arr;
    }

    private Object[][] searchData(String drugName){
        Object[][] arr = new Object[0][];
        String sql = "select * from drug";
        try{
            ResultSet rs = DBManager.getINSTANCE().executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            arr = new Object[count + 1][5];
            int j = 0;
            while (rs.next()){
                String medID = rs.getString("MedID");
                String medName = rs.getString("MedName");
                String inTime = rs.getString("InTime");
                String outTime = rs.getString("OutTime");
                int num = rs.getInt("Num");
                if (drugName.equals(medName)){
                    arr[j][0] = medID;
                    arr[j][1] = medName;
                    arr[j][2] = inTime;
                    arr[j][3] = outTime;
                    arr[j][4] = num;
                    j++;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arr;
    }

    private void showError(){
        if (showError) {
            Error.setVisible(true);
            table1.setFocusable(false);
        }
        else {
            Error.setVisible(false);
            table1.setFocusable(true);
        }
    }


}


