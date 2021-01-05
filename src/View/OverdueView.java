/*
 * Created by JFormDesigner on Thu Dec 31 14:33:14 GMT+08:00 2020
 */

package View;

import java.beans.*;
import Model.DBManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author rarcher
 */
public class OverdueView extends JFrame {
    public OverdueView() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        new StockView().setVisible(true);
        this.dispose();
    }

    private void table1PropertyChange(PropertyChangeEvent e) {
        initTable(table1);
    }

    private void button2ActionPerformed(ActionEvent e) {
        initTable(table1);
    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        new LoginView().setVisible(true);
        this.dispose();
    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void button3ActionPerformed(ActionEvent e) {
        try {
                String purchase = "delete from overdue";
                DBManager.getINSTANCE().executeUpdate(purchase);
                initTable(table1);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void button11ActionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        ResourceBundle bundle = ResourceBundle.getBundle("View.form");
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel1 = new JPanel();
        button2 = new JButton();
        button3 = new JButton();
        label3 = new JLabel();
        label4 = new JLabel();
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
        button1 = new JButton();
        label17 = new JLabel();
        Dialog6 = new JDialog();
        button11 = new JButton();
        label18 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText(bundle.getString("menu1.text_3"));

                //---- menuItem1 ----
                menuItem1.setText(bundle.getString("menuItem1.text_2"));
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText(bundle.getString("menuItem2.text_2"));
                menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                menu1.add(menuItem2);
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
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (
            new javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e"
            , javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
            , new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 )
            , java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
            panel1.setLayout(new GridLayout(17, 0));

            //---- button2 ----
            button2.setText(bundle.getString("button2.text_2"));
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button2);

            //---- button3 ----
            button3.setText(bundle.getString("button3.text"));
            button3.addActionListener(e -> button3ActionPerformed(e));
            panel1.add(button3);
            panel1.add(label3);
            panel1.add(label4);
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

            //---- button1 ----
            button1.setText(bundle.getString("button1.text_2"));
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);
        }
        contentPane.add(panel1, BorderLayout.WEST);

        //---- label17 ----
        label17.setText(bundle.getString("label17.text"));
        label17.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label17, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());

        //======== Dialog6 ========
        {
            Dialog6.setAlwaysOnTop(true);
            var Dialog6ContentPane = Dialog6.getContentPane();
            Dialog6ContentPane.setLayout(new BorderLayout());

            //---- button11 ----
            button11.setText(bundle.getString("button11.text"));
            button11.addActionListener(e -> button11ActionPerformed(e));
            Dialog6ContentPane.add(button11, BorderLayout.PAGE_END);

            //---- label18 ----
            label18.setText(bundle.getString("label18.text"));
            label18.setFont(label18.getFont().deriveFont(label18.getFont().getSize() + 1f));
            label18.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog6ContentPane.add(label18, BorderLayout.CENTER);
            Dialog6.setSize(200, 195);
            Dialog6.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel1;
    private JButton button2;
    private JButton button3;
    private JLabel label3;
    private JLabel label4;
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
    private JButton button1;
    private JLabel label17;
    private JDialog Dialog6;
    private JButton button11;
    private JLabel label18;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    Object[][] arr;

    private void initTable(JTable table1){

        String[] list = {"药品名称", "过期数量"};
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
        Object[][] arr = new Object[0][];
        String sql = "select * from overdue";
        try{
            ResultSet rs1 = DBManager.getINSTANCE().executeQuery(sql);
            rs1.last();
            int count = rs1.getRow();
            rs1.close();
            ResultSet rs = DBManager.getINSTANCE().executeQuery(sql);
            System.out.println("[*]正在查询数据库 当前执行sql语句"+sql);
            arr = new Object[count + 1][2];
            int j = 0;
            while (rs.next()){
                String medName = rs.getString("MedName");
                String OverdueNum = rs.getString("OverdueNum");
                arr[j][0] = medName;
                arr[j][1] = OverdueNum;
                j++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

}
