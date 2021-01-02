/*
 * Created by JFormDesigner on Thu Dec 31 11:23:17 GMT+08:00 2020
 */

package View;

import Model.DBManager;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author rarcher
 */
public class Purchase extends JFrame {
    public Purchase() {
        initComponents();
    }

    private void backtostockActionPerformed(ActionEvent e) {
        new StockView().setVisible(true);
        this.dispose();
    }
    

    private void table1PropertyChange(PropertyChangeEvent e) {
        initTable(table1);
    }

    private void button1ActionPerformed(ActionEvent e) {
        initTable(table1);
    }

    private void table1MouseClicked(MouseEvent e) {
        int r= table1.getSelectedRow();
        int c = 0;
        //得到选中的单元格的值，表格中都是字符串
        Object value= table1.getValueAt(r, c);
        /*String info=r+"行"+c+"列值 : "+value.toString();
        javax.swing.JOptionPane.showMessageDialog(null,info);*/
        selectName = value.toString();
        dialog1.setVisible(true);
    }
    private void button6ActionPerformed(ActionEvent e) {
        dialog1.dispose();
    }

    private void button5ActionPerformed(ActionEvent e) {
        Purchase.purchaseMap.put(selectName,Integer.parseInt(textField1.getText()));
        try{
            DBManager.getINSTANCE().executeUpdate("update Purchase set Num = \""+textField1.getText()+"\" where MedName = \""+selectName+"\"");
        }catch (Exception e1){
            e1.printStackTrace();
        }

        initTable(table1);
        dialog1.dispose();
    }

    private void textField1KeyTyped(KeyEvent e) {
        int keyChar=e.getKeyChar();
        if (keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9){

        }else{
            e.consume();
        }

    }

    private void dialog1PropertyChange(PropertyChangeEvent e) {
        label3.setText(selectName);
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
        button2 = new JButton();
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
        label19 = new JLabel();
        label20 = new JLabel();
        backtostock = new JButton();
        label21 = new JLabel();
        dialog1 = new JDialog();
        panel3 = new JPanel();
        button5 = new JButton();
        button6 = new JButton();
        label1 = new JLabel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        panel6 = new JPanel();
        label16 = new JLabel();
        textField1 = new JTextField();
        label17 = new JLabel();
        label18 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText(bundle.getString("menu1.text_2"));

                //---- menuItem1 ----
                menuItem1.setText(bundle.getString("menuItem1.text"));
                menu1.add(menuItem1);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.addPropertyChangeListener(e -> table1PropertyChange(e));
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
            .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER ,javax
            . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,
            12 ) ,java . awt. Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans
            .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e.
            getPropertyName () ) )throw new RuntimeException( ) ;} } );
            panel1.setLayout(new GridLayout(17, 0));

            //---- button1 ----
            button1.setText(bundle.getString("button1.text"));
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);

            //---- button2 ----
            button2.setText(bundle.getString("button2.text"));
            panel1.add(button2);
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
            panel1.add(label19);
            panel1.add(label20);

            //---- backtostock ----
            backtostock.setText(bundle.getString("backtostock.text"));
            backtostock.addActionListener(e -> backtostockActionPerformed(e));
            panel1.add(backtostock);
        }
        contentPane.add(panel1, BorderLayout.WEST);

        //---- label21 ----
        label21.setText(bundle.getString("label21.text"));
        label21.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label21, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());

        //======== dialog1 ========
        {
            dialog1.addPropertyChangeListener(e -> dialog1PropertyChange(e));
            var dialog1ContentPane = dialog1.getContentPane();
            dialog1ContentPane.setLayout(new BorderLayout());

            //======== panel3 ========
            {
                panel3.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
                .EmptyBorder(0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax.swing.border.TitledBorder.CENTER,javax
                .swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dialo\u0067",java.awt.Font.BOLD,
                12),java.awt.Color.red),panel3. getBorder()));panel3. addPropertyChangeListener(new java.beans
                .PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("borde\u0072".equals(e.
                getPropertyName()))throw new RuntimeException();}});
                panel3.setLayout(new GridLayout(0, 2));

                //---- button5 ----
                button5.setText(bundle.getString("button5.text_2"));
                button5.addActionListener(e -> button5ActionPerformed(e));
                panel3.add(button5);

                //---- button6 ----
                button6.setText(bundle.getString("button6.text"));
                button6.addActionListener(e -> button6ActionPerformed(e));
                panel3.add(button6);
            }
            dialog1ContentPane.add(panel3, BorderLayout.SOUTH);

            //---- label1 ----
            label1.setText(bundle.getString("label1.text"));
            dialog1ContentPane.add(label1, BorderLayout.NORTH);

            //======== panel4 ========
            {
                panel4.setLayout(new GridLayout(2, 0));

                //======== panel5 ========
                {
                    panel5.setLayout(new BorderLayout());

                    //---- label2 ----
                    label2.setText(bundle.getString("label2.text"));
                    panel5.add(label2, BorderLayout.WEST);

                    //---- label3 ----
                    label3.setText(bundle.getString("label3.text"));
                    panel5.add(label3, BorderLayout.CENTER);
                }
                panel4.add(panel5);

                //======== panel6 ========
                {
                    panel6.setLayout(new BorderLayout());

                    //---- label16 ----
                    label16.setText(bundle.getString("label16.text"));
                    panel6.add(label16, BorderLayout.WEST);

                    //---- textField1 ----
                    textField1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                    textField1.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            textField1KeyTyped(e);
                        }
                    });
                    panel6.add(textField1, BorderLayout.CENTER);
                    panel6.add(label17, BorderLayout.NORTH);
                    panel6.add(label18, BorderLayout.SOUTH);
                }
                panel4.add(panel6);
            }
            dialog1ContentPane.add(panel4, BorderLayout.CENTER);
            dialog1.setSize(255, 200);
            dialog1.setLocationRelativeTo(dialog1.getOwner());
        }
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
    private JButton button2;
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
    private JLabel label19;
    private JLabel label20;
    private JButton backtostock;
    private JLabel label21;
    private JDialog dialog1;
    private JPanel panel3;
    private JButton button5;
    private JButton button6;
    private JLabel label1;
    private JPanel panel4;
    private JPanel panel5;
    private JLabel label2;
    private JLabel label3;
    private JPanel panel6;
    private JLabel label16;
    private JTextField textField1;
    private JLabel label17;
    private JLabel label18;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    Object[][] arr;
    private String selectName = "";
    public static final HashMap<String,Integer> purchaseMap = new HashMap<>();
    private void initTable(JTable table1){

        String[] list = {"药品名称", "补货数量"};
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        for(String i : list)
            model.addColumn(i);
        arr = refreshData();
        for (Object[] i : arr)
            model.addRow(i);
        table1.setFocusable(false);
    }

    private Object[][] refreshData(){
        Object[][] arr = new Object[0][];
        String sql = "select * from Purchase";
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
                arr[j][0] = medName;
                int num = rs.getInt("Num");
                if (!Purchase.purchaseMap.containsKey(medName)){
                    Purchase.purchaseMap.put(medName,num);
                }
                arr[j][1] = num;
                j++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }


}
