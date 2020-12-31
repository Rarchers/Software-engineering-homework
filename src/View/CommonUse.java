/*
 * Created by JFormDesigner on Thu Dec 31 14:50:29 GMT+08:00 2020
 */

package View;

import java.beans.*;
import Model.DBManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author rarcher
 */
public class CommonUse extends JFrame {
    public CommonUse() {
        initComponents();
    }

    private void button2ActionPerformed(ActionEvent e) {
        new StockView().setVisible(true);
        this.dispose();
    }

    private void button1ActionPerformed(ActionEvent e) {
        initTable(table1);
    }

    private void table1PropertyChange(PropertyChangeEvent e) {
        initTable(table1);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - rarcher
        ResourceBundle bundle = ResourceBundle.getBundle("View.form");
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel1 = new JPanel();
        button1 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        label16 = new JLabel();
        button2 = new JButton();
        label19 = new JLabel();
        label6 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText(bundle.getString("menu1.text_4"));

                //---- menuItem1 ----
                menuItem1.setText(bundle.getString("menuItem1.text_3"));
                menu1.add(menuItem1);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.addPropertyChangeListener(e -> table1PropertyChange(e));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
            border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER
            , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font
            .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order"
            .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            panel1.setLayout(new GridLayout(17, 0));

            //---- button1 ----
            button1.setText(bundle.getString("button1.text_3"));
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);
            panel1.add(label1);
            panel1.add(label2);
            panel1.add(label3);
            panel1.add(label4);
            panel1.add(label5);
            panel1.add(label7);
            panel1.add(label8);
            panel1.add(label9);
            panel1.add(label10);
            panel1.add(label11);
            panel1.add(label12);
            panel1.add(label13);
            panel1.add(label14);
            panel1.add(label15);
            panel1.add(label16);

            //---- button2 ----
            button2.setText(bundle.getString("button2.text_3"));
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button2);
        }
        contentPane.add(panel1, BorderLayout.WEST);

        //---- label19 ----
        label19.setText(bundle.getString("label19.text"));
        label19.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label19, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());

        //---- label6 ----
        label6.setText(bundle.getString("label6.text"));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - rarcher
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel1;
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JButton button2;
    private JLabel label19;
    private JLabel label6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = Calendar.getInstance();

    Object[][] arr;
    private HashMap<String,Integer> sellMap = new HashMap<>();
    private void initTable(JTable table1){

        String[] list = {"药品名称","销售总量"};
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        for(String i : list)
            model.addColumn(i);
        arr = refreshData();
        for (Object[] i : arr)
            model.addRow(i);
        table1.setEnabled(false);
    }

    private Object[][] refreshData(){
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -7); // 设置为7天前
        Date before7days = calendar.getTime(); // 得到7天前的时间
        sellMap = new HashMap<>();
        Object[][] arr = new Object[0][];
        String sql = "select * from sell";
        try{
            ResultSet rs = DBManager.getINSTANCE().executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            System.out.println("[*]正在查询数据库 当前执行sql语句"+sql);

           // int j = 0;
            while (rs.next()){
                String medName = rs.getString("MedName");
                String SellTime = rs.getString("SellTime");
                int SellNum = rs.getInt("SellNum");
                Date ot = df.parse(SellTime);
                if (before7days.getTime()<=ot.getTime()){ //7天之内
                 sellMap.put(medName,sellMap.getOrDefault(medName,0)+SellNum);
                }
               // arr[j][0] = medName;
               // arr[j][1] = SellTime;
                //j++;
            }
            arr = new Object[sellMap.size()+1][2];
            int j = 0;
            for (String key : sellMap.keySet()){
                if (sellMap.get(key)>=50){
                    arr[j][0] = key;
                    arr[j][1] = sellMap.get(key);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }
}
