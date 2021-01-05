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
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * @author rarcher
 */
public class StockView extends JFrame {
    public StockView() {
        initComponents();
        initTable(table1,1,"");
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

    private void queryAllActionPerformed(ActionEvent e) {
        initTable(table1,1,"");
        showError();

    }

    private void table1PropertyChange(PropertyChangeEvent e) {
       /* System.out.println("[*] table1PropertyChange");

        showError();*/

    }

    private void searchActionPerformed(ActionEvent e) {
        String drugName = searchedit.getText();
        System.out.println(drugName);
        if (drugName.equals("")||drugName.length() == 0)
            initTable(table1,1,"");
        else
            initTable(table1,0,drugName);
      //  showError();
    }

    private void button2ActionPerformed(ActionEvent e) {
        Error.dispose();
    }

    private void button3ActionPerformed(ActionEvent e) {
        new Purchase().setVisible(true);
        closeAll();
        this.dispose();
    }

    private void button4ActionPerformed(ActionEvent e) {
        dialog1.dispose();
    }

    private void button5ActionPerformed(ActionEvent e) {
        // 查看库存不足

            new Purchase().setVisible(true);
            closeAll();
            this.dispose();


    }

    private void button6ActionPerformed(ActionEvent e) {
        None.dispose();
    }

    private void button7ActionPerformed(ActionEvent e) {
        dialog2.dispose();
    }

    private void button9ActionPerformed(ActionEvent e) {
        dialog3.dispose();
    }

    private void button8ActionPerformed(ActionEvent e) {
        //查看过期
        new OverdueView().setVisible(true);
        closeAll();
        this.dispose();
    }

    private void viewoutdueActionPerformed(ActionEvent e) {

            new OverdueView().setVisible(true);
            closeAll();
            this.dispose();


    }

    private void CommonlyActionPerformed(ActionEvent e) {
        new CommonUse().setVisible(true);
        this.dispose();
    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        new Judge().setVisible(true);
        this.dispose();
    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        new LoginView().setVisible(true);
        this.dispose();
    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - rarcher
        ResourceBundle bundle = ResourceBundle.getBundle("View.form");
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem2 = new JMenuItem();
        menu2 = new JMenu();
        menuItem1 = new JMenuItem();
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
        label5 = new JLabel();
        label6 = new JLabel();
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
        label17 = new JLabel();
        button5 = new JButton();
        viewoutdue = new JButton();
        Commonly = new JButton();
        Error = new JDialog();
        button2 = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        dialog1 = new JDialog();
        label4 = new JLabel();
        panel4 = new JPanel();
        button3 = new JButton();
        button4 = new JButton();
        None = new JDialog();
        button6 = new JButton();
        label20 = new JLabel();
        dialog2 = new JDialog();
        label21 = new JLabel();
        button7 = new JButton();
        dialog3 = new JDialog();
        label22 = new JLabel();
        panel5 = new JPanel();
        button8 = new JButton();
        button9 = new JButton();

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
                menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText(bundle.getString("menu2.text"));

                //---- menuItem1 ----
                menuItem1.setText(bundle.getString("menuItem1.text"));
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu2.add(menuItem1);

                //---- menuItem3 ----
                menuItem3.setText(bundle.getString("menuItem3.text"));
                menuItem3.addActionListener(e -> menuItem3ActionPerformed(e));
                menu2.add(menuItem3);
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {

            //======== panel2 ========
            {
                panel2.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
                (0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax.swing.border
                .TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt
                .Color.red),panel2. getBorder()));panel2. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
                propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException()
                ;}});
                panel2.setLayout(new BorderLayout());

                //======== panel3 ========
                {
                    panel3.setLayout(new BorderLayout());

                    //---- searchedit ----
                    searchedit.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                    searchedit.setToolTipText("\u5728\u8fd9\u91cc\u8f93\u5165\u7b5b\u9009\u836f\u54c1\u540d");
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
            panel1.add(label5);
            panel1.add(label6);
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
            panel1.add(label17);

            //---- button5 ----
            button5.setText(bundle.getString("button5.text"));
            button5.addActionListener(e -> button5ActionPerformed(e));
            panel1.add(button5);

            //---- viewoutdue ----
            viewoutdue.setText("\u67e5\u770b\u8fc7\u671f\u836f\u54c1");
            viewoutdue.addActionListener(e -> viewoutdueActionPerformed(e));
            panel1.add(viewoutdue);

            //---- Commonly ----
            Commonly.setText("\u67e5\u770b\u5e38\u7528\u836f\u54c1");
            Commonly.addActionListener(e -> CommonlyActionPerformed(e));
            panel1.add(Commonly);
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

        //======== dialog1 ========
        {
            dialog1.setAlwaysOnTop(true);
            var dialog1ContentPane = dialog1.getContentPane();
            dialog1ContentPane.setLayout(new BorderLayout());

            //---- label4 ----
            label4.setText("\u53d1\u73b0\u5b58\u5728\u65b0\u589e\u5e93\u5b58\u4e0d\u8db3\uff0c\u662f\u5426\u7acb\u5373\u67e5\u770b");
            dialog1ContentPane.add(label4, BorderLayout.CENTER);

            //======== panel4 ========
            {
                panel4.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
                EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER,javax.swing
                .border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),
                java.awt.Color.red),panel4. getBorder()));panel4. addPropertyChangeListener(new java.beans.PropertyChangeListener()
                {@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))
                throw new RuntimeException();}});
                panel4.setLayout(new GridLayout(0, 2));

                //---- button3 ----
                button3.setText("\u7acb\u5373\u67e5\u770b");
                button3.addActionListener(e -> button3ActionPerformed(e));
                panel4.add(button3);

                //---- button4 ----
                button4.setText("\u7a0d\u540e\u67e5\u770b");
                button4.addActionListener(e -> button4ActionPerformed(e));
                panel4.add(button4);
            }
            dialog1ContentPane.add(panel4, BorderLayout.SOUTH);
            dialog1.setSize(200, 200);
            dialog1.setLocationRelativeTo(dialog1.getOwner());
        }

        //======== None ========
        {
            var NoneContentPane = None.getContentPane();
            NoneContentPane.setLayout(new BorderLayout());

            //---- button6 ----
            button6.setText("\u786e\u5b9a");
            button6.addActionListener(e -> button6ActionPerformed(e));
            NoneContentPane.add(button6, BorderLayout.SOUTH);

            //---- label20 ----
            label20.setText("\u6682\u65e0\u5e93\u5b58\u4e0d\u8db3");
            NoneContentPane.add(label20, BorderLayout.CENTER);
            None.setSize(200, 200);
            None.setLocationRelativeTo(None.getOwner());
        }

        //======== dialog2 ========
        {
            var dialog2ContentPane = dialog2.getContentPane();
            dialog2ContentPane.setLayout(new BorderLayout());

            //---- label21 ----
            label21.setText("\u6682\u65e0\u8fc7\u671f\u4fe1\u606f");
            dialog2ContentPane.add(label21, BorderLayout.CENTER);

            //---- button7 ----
            button7.setText("\u786e\u5b9a");
            button7.addActionListener(e -> button7ActionPerformed(e));
            dialog2ContentPane.add(button7, BorderLayout.SOUTH);
            dialog2.setSize(200, 200);
            dialog2.setLocationRelativeTo(dialog2.getOwner());
        }

        //======== dialog3 ========
        {
            var dialog3ContentPane = dialog3.getContentPane();
            dialog3ContentPane.setLayout(new BorderLayout());

            //---- label22 ----
            label22.setText("\u53d1\u73b0\u5b58\u5728\u65b0\u589e\u8fc7\u671f\u836f\u54c1");
            dialog3ContentPane.add(label22, BorderLayout.CENTER);

            //======== panel5 ========
            {
                panel5.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
                javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax
                . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
                .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,panel5. getBorder( )) ); panel5. addPropertyChangeListener (new java. beans.
                PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .
                equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
                panel5.setLayout(new GridLayout(0, 2));

                //---- button8 ----
                button8.setText("\u7acb\u5373\u67e5\u770b");
                button8.addActionListener(e -> button8ActionPerformed(e));
                panel5.add(button8);

                //---- button9 ----
                button9.setText("\u7a0d\u540e\u67e5\u770b");
                button9.addActionListener(e -> button9ActionPerformed(e));
                panel5.add(button9);
            }
            dialog3ContentPane.add(panel5, BorderLayout.SOUTH);
            dialog3.setSize(200, 200);
            dialog3.setLocationRelativeTo(dialog3.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - rarcher
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem2;
    private JMenu menu2;
    private JMenuItem menuItem1;
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
    private JLabel label5;
    private JLabel label6;
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
    private JLabel label17;
    private JButton button5;
    private JButton viewoutdue;
    private JButton Commonly;
    private JDialog Error;
    private JButton button2;
    private JLabel label2;
    private JLabel label3;
    private JDialog dialog1;
    private JLabel label4;
    private JPanel panel4;
    private JButton button3;
    private JButton button4;
    private JDialog None;
    private JButton button6;
    private JLabel label20;
    private JDialog dialog2;
    private JLabel label21;
    private JButton button7;
    private JDialog dialog3;
    private JLabel label22;
    private JPanel panel5;
    private JButton button8;
    private JButton button9;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private boolean showError = false;
    private boolean needPurchase = false;
    private boolean isOutTime = false;
    private HashMap<String,Integer> purchaseMap = new HashMap<>();
    private HashMap<String,Integer> drugMap = new HashMap<>();
    private HashMap<String,Integer> outDue = new HashMap<>();
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private Date Today = new Date();
    private void initTable(JTable table1,int type,String drugName){

        String[] list = {"药品编号", "药品名称", "入库日期","过期日期", "剩余库存"};
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
        //System.out.println(arr[0][0]);


        for (Object[] i : arr)
            model.addRow(i);
        table1.setEnabled(false);
    }

    private Object[][] refreshData(){
        drugMap = new HashMap<>();
        purchaseMap = new HashMap<>();
        outDue = new HashMap<>();
        Object[][] arr = new Object[0][];
        String sql = "select * from drug";
        try{
            ResultSet rs1 = DBManager.getINSTANCE().executeQuery(sql);
            rs1.last();
            int count = rs1.getRow();
            rs1.close();
            ResultSet rs = DBManager.getINSTANCE().executeQuery(sql);
            System.out.println(count);
            System.out.println("[*]正在查询数据库 当前执行sql语句"+sql);
            arr = new Object[count + 1][5];
            int j = 0;
            while (rs.next()){
                String medID = rs.getString("MedID");
                String medName = rs.getString("MedName");
                String inTime = rs.getString("InTime");
                String outTime = rs.getString("OutTime");
                int num = rs.getInt("Num");
                //System.out.println(num);
                drugMap.put(medName,drugMap.getOrDefault(medName,0)+num);
                Date ot = df.parse(outTime);
                if (ot.before(Today)) {
                    outDue.put(medName,num);
                   // DBManager.getINSTANCE().executeUpdate("delete from drug where MedID = \""+medID+"\"");
                }
                arr[j][0] = medID;
                arr[j][1] = medName;
                arr[j][2] = inTime;
                arr[j][3] = outTime;
                arr[j][4] = num;
                j++;
            }
            rs.close();
            for (String key:outDue.keySet()){
                DBManager.getINSTANCE().executeUpdate("delete from drug where medName = \""+key+"\"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




        for (String key : drugMap.keySet()){
           // System.out.println("key "+key+"value "+drugMap.get(key));
            if (drugMap.get(key)<10)
                purchaseMap.put(key,drugMap.get(key));
        }

        if (outDue.size()>0) isOutTime = true;
        if (purchaseMap.size()>0) needPurchase = true;

        UpdateOverdue();
        UpdatePurchase();


        return arr;
    }

    private Object[][] searchData(String drugName){
        Object[][] arr = new Object[0][];
        String sql = "select * from drug";
        try{
            ResultSet rs1 = DBManager.getINSTANCE().executeQuery(sql);
            rs1.last();
            int count = rs1.getRow();
            rs1.close();
            ResultSet rs = DBManager.getINSTANCE().executeQuery(sql);
            arr = new Object[count + 1][5];
            int j = 0;
            while (rs.next()){
                String medID = rs.getString("MedID");
                String medName = rs.getString("MedName");
                String inTime = rs.getString("InTime");
                String outTime = rs.getString("OutTime");
                int num = rs.getInt("Num");
                if (medName.contains(drugName)){
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
        Error.setVisible(showError);
        table1.setFocusable(!showError);
        dialog3.setVisible(isOutTime);
        dialog1.setVisible(needPurchase);
    }

    private void closeAll(){
        if (Error.isVisible())
            Error.dispose();
        if (dialog1.isVisible())
            dialog1.dispose();
        if (None.isVisible())
            None.dispose();
    }



    private void UpdateOverdue(){
        //String sql = "delete from overdue";
        try {
           // DBManager.getINSTANCE().executeUpdate(sql);
            for (String key : outDue.keySet()){
                System.out.println("插入值："+key+" len "+key.length()+"res "+outDue.get(key));
                String insert = "insert into overdue (MedName,OverdueNum) values (\""+key+"\","+outDue.get(key)+")";
                String purchase = "insert into Purchase (MedName,Num) values (\""+key+"\","+outDue.get(key)+")";
                DBManager.getINSTANCE().executeUpdate(insert);
                DBManager.getINSTANCE().executeUpdate(purchase);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void UpdatePurchase(){
       // String sql = "delete from Purchase";
        try {
          //  DBManager.getINSTANCE().executeUpdate(sql);
            for (String key : purchaseMap.keySet()){
                ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from Purchase where MedName = \""+key+"\"");
                if (!rs.next()){
                    String insert = "insert into Purchase (MedName,Num) values (\""+key+"\",50)";
                    DBManager.getINSTANCE().executeUpdate(insert);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}


