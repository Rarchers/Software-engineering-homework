/*
 * Created by JFormDesigner on Sat Jan 02 21:42:33 CST 2021
 */

package View;

import Model.DBManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * @author unknown
 */
public class Judge extends JFrame {
    public Judge() {
        initComponents();
    }

    private void table1MouseClicked(MouseEvent e) {
        int r = table1.getSelectedRow();
        int c = 1;
        Object value = table1.getValueAt(r,c);
        textField1.setText(value.toString());
        dialog1.dispose();
    }

    private void table1PropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void button2ActionPerformed(ActionEvent e) {
        textField3.setText("");
        textField1.setText("");
        textField4.setText("");
        textField2.setText("");
    }

    private void button5ActionPerformed(ActionEvent e) throws SQLException {
        System.out.println("cool");
        dialog1.setVisible(true);
        String Medname = textField1.getText();
        String[] list = {"药品ID", "药品名称","入库时间","过期时间","库存"};
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        for(String i : list)
            model.addColumn(i);
        Object[][] arr = new Object[0][];
        String sql = "select * from drug";
        try {
            ResultSet rs1 = DBManager.getINSTANCE().executeQuery(sql);
            rs1.last();
            int count = rs1.getRow();
            rs1.close();
            ResultSet rs = DBManager.getINSTANCE().executeQuery(sql);
            arr = new Object[count + 1][5];
            int j = 0;
            while (rs.next()) {
                String medID = rs.getString("MedID");
                String medName = rs.getString("MedName");
                String inTime = rs.getString("InTime");
                String outTime = rs.getString("OutTime");
                int num = rs.getInt("Num");
                if (medName.contains(Medname)) {
                    arr[j][0] = medID;
                    arr[j][1] = medName;
                    arr[j][2] = inTime;
                    arr[j][3] = outTime;
                    arr[j][4] = num;
                    j++;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        for (Object[] i : arr)
            model.addRow(i);
        table1.setFocusable(false);


    }

    private void button6ActionPerformed(ActionEvent e) throws SQLException {
        Dialog2.dispose();
        ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from Drug where MedName = \""+textField1.getText()+"\"");
        if(rs.next()){
            int num = rs.getInt("Num");
            System.out.println(num);
            int sells= Integer.parseInt(textField4.getText());
            System.out.println(sells);
            int unum = num - sells;
            System.out.println(unum);
            boolean res = DBManager.getINSTANCE().executeUpdate("update Drug set Num = \"" + unum + "\" where MedName = \"" + textField1.getText() + "\"");
            if(res){
                Dialog9.setVisible(true);
            }
            else{
                Dialog2.setVisible(true);
            }
        }
    }

    private void button7ActionPerformed(ActionEvent e) {
        dialog2.dispose();
        Dialog2.dispose();

    }

    private void button3ActionPerformed(ActionEvent e) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data = new Date(System.currentTimeMillis());
        String sql = "insert into orders (CustomerID,MedName,MedTime,WorkerID) values (\""+textField3.getText()+"\",\""+textField1.getText()+"\",\""+formatter.format(data)+"\",\""+textField2.getText()+"\")";
        boolean res = DBManager.getINSTANCE().executeUpdate(sql);
        if(res){

            res = DBManager.getINSTANCE().executeUpdate("insert into sell (CustomerID,MedName,SellTime,SellNum) values (\""+textField3.getText()+"\",\""+textField1.getText()+"\",\""+formatter.format(data)+"\",\""+textField4.getText()+"\")");
            if(res){
                ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from Drug where MedName = \""+textField1.getText()+"\"");
                if(rs.next()){
                    int num = rs.getInt("Num");
                    System.out.println(num);
                    int sells= Integer.parseInt(textField4.getText());
                    System.out.println(sells);
                    int unum = num - sells;
                    System.out.println(unum);
                    res = DBManager.getINSTANCE().executeUpdate("update Drug set Num = \"" + unum + "\" where MedName = \"" + textField1.getText() + "\"");
                    if(res){
                        Dialog9.setVisible(true);
                    }
                    else{
                        Dialog2.setVisible(true);
                    }
                }
            }
            else{
                Dialog11.setVisible(true);
            }
        }
        else{
            Dialog10.setVisible(true);
        }
    }

    private void button4ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button1ActionPerformed(ActionEvent e) throws SQLException {
        String ID = textField3.getText();
        String ID1= textField2.getText();
        String Medname = textField1.getText();
        ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from Customer where CustomerID = \""+ID+"\"");
        if(rs.next()) {
            rs =  DBManager.getINSTANCE().executeQuery("select * from worker where WorkerID = \""+ID1+"\"");
            if(rs.next()){
                if(rs.getString("WorkerType").equals("药师")){
                    if(textField4.getText().equals(""))
                    {
                        Dialog6.setVisible(true);
                    }
                    else{
                        rs =  DBManager.getINSTANCE().executeQuery("select * from Drug where MedName = \""+Medname+"\"");
                        if(rs.next()){
                            dialog2.setVisible(true);
                            initTable1(table2);
                        }
                        else{
                            Dialog5.setVisible(true);
                        }

                    }
                }
                else{
                    Dialog4.setVisible(true);
                }
            }
            else{
                Dialog4.setVisible(true);
            }
        }
        else
        {
            Dialog3.setVisible(true);
        }
    }

    private void initTable1(JTable table2) throws SQLException {

        String[] list = {"顾客ID","药品名称","药品数量","药师ID"};
        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        for(String i : list)
            model.addColumn(i);
        Object[][] arr;
        arr = new Object[1][4];

        arr[0][0] =textField3.getText();
        arr[0][1] = textField1.getText();
        arr[0][2]= textField4.getText();
        arr[0][3]=textField2.getText();


        for (Object[] i : arr)
            model.addRow(i);
        table2.setEnabled(false);

    }

    private void button9ActionPerformed(ActionEvent e) {
        Dialog4.dispose();
        textField2.setText("");
    }

    private void button8ActionPerformed(ActionEvent e) {
        Dialog3.dispose();
        textField3.setText("");
    }



    private void button11ActionPerformed(ActionEvent e) {
        Dialog6.dispose();
    }

    private void button10ActionPerformed(ActionEvent e) {
        Dialog5.dispose();
        textField1.setText("");
    }

    private void button15ActionPerformed(ActionEvent e) throws SQLException {
        Dialog10.dispose();
        Date data = new Date(System.currentTimeMillis());
        String sql = "insert into orders (CustomerID,MedName,MedTime,WorkerID) values (\""+textField3.getText()+"\",\""+textField1.getText()+"\",\""+data+"\",\""+textField2.getText()+"\")";
        boolean res = DBManager.getINSTANCE().executeUpdate(sql);
        if(res){
            res = DBManager.getINSTANCE().executeUpdate("insert into sell (MedID,MedName,SellTime,SellNum) values (\""+textField3.getText()+"\",\""+textField1.getText()+"\",\""+data+"\",\""+textField4.getText()+"\")");
            if(res){
                ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from Drug where MedName = \""+textField1.getText()+"\"");
                if(rs.next()){
                    int num = rs.getInt("Num");
                    int sells= Integer.parseInt(textField4.getText());
                    int unum = num - sells;
                    res = DBManager.getINSTANCE().executeUpdate("update Drug set Num = \"" + unum + "\" where MedName = \"" + textField1.getText() + "\"");
                    if(res){
                        Dialog9.setVisible(true);
                    }
                    else{
                        Dialog2.setVisible(true);
                    }
                }
            }
            else{
                Dialog2.setVisible(true);
            }
        }
        else{
            Dialog2.setVisible(true);
        }
    }

    private void button17ActionPerformed(ActionEvent e) throws SQLException {
        Dialog11.dispose();
        Date data = new Date(System.currentTimeMillis());
        boolean res = DBManager.getINSTANCE().executeUpdate("insert into sell (MedID,MedName,SellTime,SellNum) values (\""+textField3.getText()+"\",\""+textField1.getText()+"\",\""+data+"\",\""+textField4.getText()+"\")");
        if(res){
            ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from Drug where MedName = \""+textField1.getText()+"\"");
            if(rs.next()){
                int num = rs.getInt("Num");
                System.out.println(num);
                int sells= Integer.parseInt(textField4.getText());
                System.out.println(sells);
                int unum = num - sells;
                System.out.println(unum);
                res = DBManager.getINSTANCE().executeUpdate("update Drug set Num = \"" + unum + "\" where MedName = \"" + textField1.getText() + "\"");
                if(res){
                    Dialog9.setVisible(true);
                }
                else{
                    Dialog2.setVisible(true);
                }
            }
        }
        else{
            Dialog2.setVisible(true);
        }
    }

    private void button12ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button14ActionPerformed(ActionEvent e) {
        dialog2.dispose();
        Dialog9.dispose();
        textField3.setText("");
        textField1.setText("");
        textField4.setText("");
        textField2.setText("");
    }

    private void button16ActionPerformed(ActionEvent e) {
        dialog2.dispose();
        Dialog10.dispose();
    }

    private void button18ActionPerformed(ActionEvent e) {
        dialog2.dispose();
        Dialog11.dispose();
    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        this.dispose();
        new Judge().setVisible(true);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        label1 = new JLabel();
        panel5 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel8 = new JPanel();
        label9 = new JLabel();
        textField3 = new JTextField();
        panel3 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        button5 = new JButton();
        panel4 = new JPanel();
        label4 = new JLabel();
        textField4 = new JTextField();
        panel7 = new JPanel();
        label3 = new JLabel();
        textField2 = new JTextField();
        dialog1 = new JDialog();
        panel6 = new JPanel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        Dialog3 = new JDialog();
        button8 = new JButton();
        label7 = new JLabel();
        Dialog2 = new JDialog();
        label6 = new JLabel();
        panel11 = new JPanel();
        button6 = new JButton();
        button7 = new JButton();
        dialog2 = new JDialog();
        panel9 = new JPanel();
        scrollPane3 = new JScrollPane();
        table2 = new JTable();
        label5 = new JLabel();
        label8 = new JLabel();
        panel10 = new JPanel();
        button3 = new JButton();
        button4 = new JButton();
        Dialog4 = new JDialog();
        button9 = new JButton();
        label10 = new JLabel();
        Dialog5 = new JDialog();
        button10 = new JButton();
        label11 = new JLabel();
        Dialog6 = new JDialog();
        button11 = new JButton();
        label12 = new JLabel();
        Dialog9 = new JDialog();
        button14 = new JButton();
        label15 = new JLabel();
        Dialog10 = new JDialog();
        label16 = new JLabel();
        panel12 = new JPanel();
        button15 = new JButton();
        button16 = new JButton();
        Dialog11 = new JDialog();
        label17 = new JLabel();
        panel13 = new JPanel();
        button17 = new JButton();
        button18 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u754c\u9762\u5207\u6362");

                //---- menuItem1 ----
                menuItem1.setText("\u836f\u54c1\u9500\u552e");
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("\u836f\u54c1\u67e5\u8be2");
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("\u836f\u54c1\u9500\u552e");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 6f));
        contentPane.add(label1, BorderLayout.NORTH);

        //======== panel5 ========
        {
            panel5.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
            EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER,javax.swing
            .border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),
            java.awt.Color.red),panel5. getBorder()));panel5. addPropertyChangeListener(new java.beans.PropertyChangeListener()
            {@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))
            throw new RuntimeException();}});
            panel5.setLayout(new GridLayout(0, 2));

            //---- button1 ----
            button1.setText("OK");
            button1.addActionListener(e -> {
                try {
                    button1ActionPerformed(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
            panel5.add(button1);

            //---- button2 ----
            button2.setText("Cancel");
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel5.add(button2);
        }
        contentPane.add(panel5, BorderLayout.SOUTH);

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(4, 0));

            //======== panel2 ========
            {
                panel2.setLayout(new GridLayout(0, 2));

                //======== panel8 ========
                {
                    panel8.setLayout(new GridLayout(1, 3));

                    //---- label9 ----
                    label9.setText("\u987e\u5ba2ID\uff1a");
                    label9.setFont(label9.getFont().deriveFont(label9.getFont().getSize() + 3f));
                    label9.setHorizontalAlignment(SwingConstants.CENTER);
                    panel8.add(label9);
                }
                panel2.add(panel8);
                panel2.add(textField3);
            }
            panel1.add(panel2);

            //======== panel3 ========
            {
                panel3.setLayout(new GridLayout(1, 3));

                //---- label2 ----
                label2.setText("\u836f\u54c1\u540d\u79f0\uff1a");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 3f));
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(label2);
                panel3.add(textField1);

                //---- button5 ----
                button5.setText("\u9009\u62e9\u836f\u54c1\u578b\u53f7");
                button5.setFont(button5.getFont().deriveFont(button5.getFont().getSize() + 3f));
                button5.addActionListener(e -> {
                    try {
                        button5ActionPerformed(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                panel3.add(button5);
            }
            panel1.add(panel3);

            //======== panel4 ========
            {
                panel4.setLayout(new GridLayout(1, 2));

                //---- label4 ----
                label4.setText("\u836f\u54c1\u6570\u91cf\uff1a");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 3f));
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(label4);
                panel4.add(textField4);
            }
            panel1.add(panel4);

            //======== panel7 ========
            {
                panel7.setLayout(new GridLayout(1, 2));

                //---- label3 ----
                label3.setText("\u836f\u5e08ID\uff1a");
                label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 3f));
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                panel7.add(label3);
                panel7.add(textField2);
            }
            panel1.add(panel7);
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //======== dialog1 ========
        {
            var dialog1ContentPane = dialog1.getContentPane();
            dialog1ContentPane.setLayout(new BorderLayout());

            //======== panel6 ========
            {
                panel6.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
                swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border
                . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog"
                ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel6. getBorder
                ( )) ); panel6. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
                .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException
                ( ); }} );
                panel6.setLayout(new GridLayout(1, 1));

                //======== scrollPane2 ========
                {

                    //---- table1 ----
                    table1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            table1MouseClicked(e);
                        }
                    });
                    scrollPane2.setViewportView(table1);
                }
                panel6.add(scrollPane2);
            }
            dialog1ContentPane.add(panel6, BorderLayout.CENTER);
            dialog1.pack();
            dialog1.setLocationRelativeTo(dialog1.getOwner());
        }

        //======== Dialog3 ========
        {
            Dialog3.setAlwaysOnTop(true);
            var Dialog3ContentPane = Dialog3.getContentPane();
            Dialog3ContentPane.setLayout(new BorderLayout());

            //---- button8 ----
            button8.setText("\u8fd4\u56de\u9500\u552e\u754c\u9762");
            button8.addActionListener(e -> button8ActionPerformed(e));
            Dialog3ContentPane.add(button8, BorderLayout.PAGE_END);

            //---- label7 ----
            label7.setText("\u987e\u5ba2ID\u4e0d\u5b58\u5728");
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 1f));
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog3ContentPane.add(label7, BorderLayout.CENTER);
            Dialog3.setSize(200, 200);
            Dialog3.setLocationRelativeTo(null);
        }

        //======== Dialog2 ========
        {
            Dialog2.setAlwaysOnTop(true);
            var Dialog2ContentPane = Dialog2.getContentPane();
            Dialog2ContentPane.setLayout(new BorderLayout());

            //---- label6 ----
            label6.setText("\u836f\u54c1\u4fe1\u606f\u66f4\u65b0\u5931\u8d25\uff01");
            label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 1f));
            label6.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog2ContentPane.add(label6, BorderLayout.CENTER);

            //======== panel11 ========
            {
                panel11.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
                EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing
                . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
                java. awt. Color. red) ,panel11. getBorder( )) ); panel11. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
                { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () ))
                throw new RuntimeException( ); }} );
                panel11.setLayout(new GridLayout(1, 2));

                //---- button6 ----
                button6.setText("\u518d\u6b21\u5c1d\u8bd5");
                button6.addActionListener(e -> {
                    try {
                        button6ActionPerformed(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                panel11.add(button6);

                //---- button7 ----
                button7.setText("\u8fd4\u56de\u9500\u552e\u754c\u9762");
                button7.addActionListener(e -> button7ActionPerformed(e));
                panel11.add(button7);
            }
            Dialog2ContentPane.add(panel11, BorderLayout.SOUTH);
            Dialog2.setSize(180, 185);
            Dialog2.setLocationRelativeTo(null);
        }

        //======== dialog2 ========
        {
            var dialog2ContentPane = dialog2.getContentPane();
            dialog2ContentPane.setLayout(new BorderLayout());

            //======== panel9 ========
            {
                panel9.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
                swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border
                . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog"
                ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel9. getBorder
                ( )) ); panel9. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
                .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException
                ( ); }} );
                panel9.setLayout(new BorderLayout());

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(table2);
                }
                panel9.add(scrollPane3, BorderLayout.CENTER);

                //---- label5 ----
                label5.setText("\u836f\u54c1\u9500\u552e\u4fe1\u606f\u5982\u4e0b\uff1a");
                label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 1f));
                panel9.add(label5, BorderLayout.NORTH);

                //---- label8 ----
                label8.setText("\u4fe1\u606f\u65e0\u8bef\uff0c\u8bf7\u70b9\u51fbOK\u5b8c\u6210\u9500\u552e\uff01");
                label8.setHorizontalAlignment(SwingConstants.TRAILING);
                panel9.add(label8, BorderLayout.SOUTH);
            }
            dialog2ContentPane.add(panel9, BorderLayout.CENTER);

            //======== panel10 ========
            {
                panel10.setLayout(new GridLayout(0, 2));

                //---- button3 ----
                button3.setText("OK");
                button3.addActionListener(e -> {
                    try {
                        button3ActionPerformed(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                panel10.add(button3);

                //---- button4 ----
                button4.setText("Cancel");
                button4.addActionListener(e -> button4ActionPerformed(e));
                panel10.add(button4);
            }
            dialog2ContentPane.add(panel10, BorderLayout.SOUTH);
            dialog2.pack();
            dialog2.setLocationRelativeTo(dialog2.getOwner());
        }

        //======== Dialog4 ========
        {
            Dialog4.setAlwaysOnTop(true);
            var Dialog4ContentPane = Dialog4.getContentPane();
            Dialog4ContentPane.setLayout(new BorderLayout());

            //---- button9 ----
            button9.setText("\u8fd4\u56de\u9500\u552e\u754c\u9762");
            button9.addActionListener(e -> button9ActionPerformed(e));
            Dialog4ContentPane.add(button9, BorderLayout.PAGE_END);

            //---- label10 ----
            label10.setText("\u836f\u5e08ID\u4e0d\u5b58\u5728");
            label10.setFont(label10.getFont().deriveFont(label10.getFont().getSize() + 1f));
            label10.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog4ContentPane.add(label10, BorderLayout.CENTER);
            Dialog4.setSize(200, 200);
            Dialog4.setLocationRelativeTo(null);
        }

        //======== Dialog5 ========
        {
            Dialog5.setAlwaysOnTop(true);
            var Dialog5ContentPane = Dialog5.getContentPane();
            Dialog5ContentPane.setLayout(new BorderLayout());

            //---- button10 ----
            button10.setText("\u8fd4\u56de\u9500\u552e\u754c\u9762");
            button10.addActionListener(e -> button10ActionPerformed(e));
            Dialog5ContentPane.add(button10, BorderLayout.PAGE_END);

            //---- label11 ----
            label11.setText("\u836f\u54c1\u540d\u79f0\u4e0d\u5b58\u5728");
            label11.setFont(label11.getFont().deriveFont(label11.getFont().getSize() + 1f));
            label11.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog5ContentPane.add(label11, BorderLayout.CENTER);
            Dialog5.setSize(200, 200);
            Dialog5.setLocationRelativeTo(null);
        }

        //======== Dialog6 ========
        {
            Dialog6.setAlwaysOnTop(true);
            var Dialog6ContentPane = Dialog6.getContentPane();
            Dialog6ContentPane.setLayout(new BorderLayout());

            //---- button11 ----
            button11.setText("\u8fd4\u56de\u9500\u552e\u754c\u9762");
            button11.addActionListener(e -> button11ActionPerformed(e));
            Dialog6ContentPane.add(button11, BorderLayout.PAGE_END);

            //---- label12 ----
            label12.setText("\u836f\u54c1\u6570\u91cf\u4e0d\u53ef\u4e3a\u7a7a");
            label12.setFont(label12.getFont().deriveFont(label12.getFont().getSize() + 1f));
            label12.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog6ContentPane.add(label12, BorderLayout.CENTER);
            Dialog6.setSize(200, 195);
            Dialog6.setLocationRelativeTo(null);
        }

        //======== Dialog9 ========
        {
            Dialog9.setAlwaysOnTop(true);
            var Dialog9ContentPane = Dialog9.getContentPane();
            Dialog9ContentPane.setLayout(new BorderLayout());

            //---- button14 ----
            button14.setText("\u8fd4\u56de\u9500\u552e\u754c\u9762");
            button14.addActionListener(e -> button14ActionPerformed(e));
            Dialog9ContentPane.add(button14, BorderLayout.PAGE_END);

            //---- label15 ----
            label15.setText("\u836f\u54c1\u4fe1\u606f\u66f4\u65b0\u6210\u529f");
            label15.setFont(label15.getFont().deriveFont(label15.getFont().getSize() + 1f));
            label15.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog9ContentPane.add(label15, BorderLayout.CENTER);
            Dialog9.setSize(200, 200);
            Dialog9.setLocationRelativeTo(null);
        }

        //======== Dialog10 ========
        {
            Dialog10.setAlwaysOnTop(true);
            var Dialog10ContentPane = Dialog10.getContentPane();
            Dialog10ContentPane.setLayout(new BorderLayout());

            //---- label16 ----
            label16.setText("\u5f00\u836f\u8bb0\u5f55\u66f4\u65b0\u5931\u8d25\uff01");
            label16.setFont(label16.getFont().deriveFont(label16.getFont().getSize() + 1f));
            label16.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog10ContentPane.add(label16, BorderLayout.CENTER);

            //======== panel12 ========
            {
                panel12.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
                ( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border
                . TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,panel12. getBorder( )) ); panel12. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
                propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
                ; }} );
                panel12.setLayout(new GridLayout(1, 2));

                //---- button15 ----
                button15.setText("\u518d\u6b21\u5c1d\u8bd5");
                button15.addActionListener(e -> {
                    try {
                        button15ActionPerformed(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                panel12.add(button15);

                //---- button16 ----
                button16.setText("\u8fd4\u56de\u9500\u552e\u754c\u9762");
                button16.addActionListener(e -> button16ActionPerformed(e));
                panel12.add(button16);
            }
            Dialog10ContentPane.add(panel12, BorderLayout.SOUTH);
            Dialog10.setSize(180, 185);
            Dialog10.setLocationRelativeTo(null);
        }

        //======== Dialog11 ========
        {
            Dialog11.setAlwaysOnTop(true);
            var Dialog11ContentPane = Dialog11.getContentPane();
            Dialog11ContentPane.setLayout(new BorderLayout());

            //---- label17 ----
            label17.setText("\u9500\u552e\u8bb0\u5f55\u66f4\u65b0\u5931\u8d25\uff01");
            label17.setFont(label17.getFont().deriveFont(label17.getFont().getSize() + 1f));
            label17.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog11ContentPane.add(label17, BorderLayout.CENTER);

            //======== panel13 ========
            {
                panel13.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
                swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border
                . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog"
                , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel13. getBorder
                () ) ); panel13. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
                . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException
                ( ) ;} } );
                panel13.setLayout(new GridLayout(1, 2));

                //---- button17 ----
                button17.setText("\u518d\u6b21\u5c1d\u8bd5");
                button17.addActionListener(e -> {
                    try {
                        button17ActionPerformed(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                panel13.add(button17);

                //---- button18 ----
                button18.setText("\u8fd4\u56de\u9500\u552e\u754c\u9762");
                button18.addActionListener(e -> button18ActionPerformed(e));
                panel13.add(button18);
            }
            Dialog11ContentPane.add(panel13, BorderLayout.SOUTH);
            Dialog11.setSize(180, 185);
            Dialog11.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JLabel label1;
    private JPanel panel5;
    private JButton button1;
    private JButton button2;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel8;
    private JLabel label9;
    private JTextField textField3;
    private JPanel panel3;
    private JLabel label2;
    private JTextField textField1;
    private JButton button5;
    private JPanel panel4;
    private JLabel label4;
    private JTextField textField4;
    private JPanel panel7;
    private JLabel label3;
    private JTextField textField2;
    private JDialog dialog1;
    private JPanel panel6;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JDialog Dialog3;
    private JButton button8;
    private JLabel label7;
    private JDialog Dialog2;
    private JLabel label6;
    private JPanel panel11;
    private JButton button6;
    private JButton button7;
    private JDialog dialog2;
    private JPanel panel9;
    private JScrollPane scrollPane3;
    private JTable table2;
    private JLabel label5;
    private JLabel label8;
    private JPanel panel10;
    private JButton button3;
    private JButton button4;
    private JDialog Dialog4;
    private JButton button9;
    private JLabel label10;
    private JDialog Dialog5;
    private JButton button10;
    private JLabel label11;
    private JDialog Dialog6;
    private JButton button11;
    private JLabel label12;
    private JDialog Dialog9;
    private JButton button14;
    private JLabel label15;
    private JDialog Dialog10;
    private JLabel label16;
    private JPanel panel12;
    private JButton button15;
    private JButton button16;
    private JDialog Dialog11;
    private JLabel label17;
    private JPanel panel13;
    private JButton button17;
    private JButton button18;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
