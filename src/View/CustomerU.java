/*
 * Created by JFormDesigner on Sat Jan 02 00:21:57 CST 2021
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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author unknown
 */
public class CustomerU extends JFrame {
    public CustomerU() {
        initComponents();
    }

    private void table1MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void table1PropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void button2ActionPerformed(ActionEvent e) {
        textField3.setText("");
        textField4.setText("");
    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        this.dispose();
        new CustomerR().setVisible(true);
    }

    private void menuItem2ActionPerformed(ActionEvent e)
    {
        this.dispose();
        new CustomerU().setVisible(true);
    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        this.dispose();
        new CustomerC().setVisible(true);
    }

    private void button1ActionPerformed(ActionEvent e) throws SQLException {
        String ID = textField3.getText();
        ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from Customer where CustomerID = \""+ID+"\"");
        if(rs.next()) {
            if(comboBox2.getSelectedItem().toString().equals("CustomerPhone")){
                if(textField4.getText().length() == 11){
                    dialog1.setVisible(true);
                    initTable(table1,ID,comboBox2.getSelectedItem().toString(),textField4.getText());
                }
                else{
                    Dialog3.setVisible(true);
                }
            }
            else{
                if(comboBox2.getSelectedItem().toString().equals("CustomerName")) {
                    if (textField4.getText().equals("")) {
                        Dialog4.setVisible(true);
                    } else {
                        dialog1.setVisible(true);
                        initTable(table1, ID, comboBox2.getSelectedItem().toString(), textField4.getText());
                    }
                }
                else{
                    initTable(table1,ID,comboBox2.getSelectedItem().toString(),textField4.getText());
                    dialog1.setVisible(true);
                }

            }
        }
        else{
            Dialog2.setVisible(true);
        }

    }
    private void initTable(JTable table1,String CustomerID,String type,String object) throws SQLException {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        Object[][] arr;
        if(type.equals("移除顾客信息")){
            String[] list = {"移除顾客信息","顾客ID", "顾客姓名"};

            model.setRowCount(0);
            model.setColumnCount(0);
            for(String i : list)
                model.addColumn(i);
            String name = "";

            arr = new Object[1][3];
            ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from Customer where CustomerID = \""+CustomerID+"\"");
            if (rs.next()) {
                name = rs.getString("CustomerName");
            }
            arr[0][0] = "移除顾客信息";
            arr[0][1] = CustomerID;
            arr[0][2] = name;
        }
        else{
            String[] list = {"顾客ID", "顾客姓名","修改属性","原信息","修改信息"};

            model.setRowCount(0);
            model.setColumnCount(0);
            for(String i : list)
                model.addColumn(i);
            String name = "";
            String origal = "";
            arr = new Object[1][5];
            ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from Customer where CustomerID = \""+CustomerID+"\"");
            if (rs.next()) {
                name = rs.getString("CustomerName");
                origal = rs.getString(type);
            }
            arr[0][0] = CustomerID;
            arr[0][1] = name;
            arr[0][2] = type;
            arr[0][3] = origal;
            arr[0][4] = object;
        }

        //System.out.println(arr[0][0]);
        for (Object[] i : arr)
            model.addRow(i);
        table1.setEnabled(false);
    }

    private void button3ActionPerformed(ActionEvent e) throws SQLException {
        if(comboBox2.getSelectedItem().toString().equals("移除顾客信息")){
            boolean rn = DBManager.getINSTANCE().executeUpdate("delete from Customer where  CustomerID = \""+textField3.getText()+"\"");
            if(rn){
                Dialog.setVisible(true);
            }
            else{
                Dialog5.setVisible(true);
            }
        }
        else {
            System.out.print("update Customer set  \"" + comboBox2.getSelectedItem().toString() + " = \"" + textField4.getText() + "\" where CustomerID = \"" + textField3.getText() + "\"");
            boolean rn = DBManager.getINSTANCE().executeUpdate("update Customer set " + comboBox2.getSelectedItem().toString() + " = \"" + textField4.getText() + "\" where CustomerID = \"" + textField3.getText() + "\"");
            if (rn) {
                Dialog.setVisible(true);
                dialog1.dispose();
            } else {
                Dialog5.setVisible(true);
                dialog1.dispose();
            }
        }
    }

    private void button6ActionPerformed(ActionEvent e) {
        Dialog2.dispose();
        textField3.setText("");
        new CustomerU().setVisible(true);
    }

    private void button7ActionPerformed(ActionEvent e) {
        Dialog3.dispose();
        textField4.setText("");
        new CustomerU().setVisible(true);
    }

    private void button9ActionPerformed(ActionEvent e) throws SQLException {
        Dialog5.dispose();
        String sql = "update Customer set " + comboBox2.getSelectedItem().toString() + " = \"" + textField4.getText() + "\" where CustomerID = \"" + textField3.getText() + "\"";
        boolean res = DBManager.getINSTANCE().executeUpdate(sql);
        if(res){
            Dialog.setVisible(true);
        }
        else{
            Dialog5.setVisible(true);
        }
    }

    private void button8ActionPerformed(ActionEvent e) {
        Dialog4.dispose();
        new CustomerU().setVisible(true);
    }

    private void button5ActionPerformed(ActionEvent e) {
        dialog1.dispose();
        Dialog.dispose();
        textField3.setText("");
        textField4.setText("");
    }

    private void button10ActionPerformed(ActionEvent e) {
        dialog1.dispose();
        Dialog5.dispose();

    }

    private void button4ActionPerformed(ActionEvent e) {
        dialog1.dispose();

    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
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
        panel9 = new JPanel();
        label12 = new JLabel();
        comboBox2 = new JComboBox<>();
        panel4 = new JPanel();
        panel10 = new JPanel();
        label15 = new JLabel();
        textField4 = new JTextField();
        dialog1 = new JDialog();
        panel6 = new JPanel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        label3 = new JLabel();
        label4 = new JLabel();
        panel7 = new JPanel();
        button3 = new JButton();
        button4 = new JButton();
        Dialog = new JDialog();
        button5 = new JButton();
        label5 = new JLabel();
        Dialog2 = new JDialog();
        button6 = new JButton();
        label6 = new JLabel();
        Dialog3 = new JDialog();
        button7 = new JButton();
        label7 = new JLabel();
        Dialog4 = new JDialog();
        button8 = new JButton();
        label8 = new JLabel();
        Dialog5 = new JDialog();
        label10 = new JLabel();
        panel11 = new JPanel();
        button9 = new JButton();
        button10 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u754c\u9762\u5207\u6362");

                //---- menuItem1 ----
                menuItem1.setText("\u4fe1\u606f\u6ce8\u518c");
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("\u4fe1\u606f\u4fee\u6539");
                menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                menu1.add(menuItem2);

                //---- menuItem3 ----
                menuItem3.setText("\u4fe1\u606f\u67e5\u8be2");
                menuItem3.addActionListener(e -> menuItem3ActionPerformed(e));
                menu1.add(menuItem3);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("\u987e\u5ba2\u4fe1\u606f\u4fee\u6539");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 6f));
        contentPane.add(label1, BorderLayout.NORTH);

        //======== panel5 ========
        {
            panel5.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel5. getBorder () ) )
            ; panel5. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;
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
            panel1.setLayout(new GridLayout(3, 0));

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
                panel3.setLayout(new GridLayout(0, 2));

                //======== panel9 ========
                {
                    panel9.setLayout(new GridLayout(1, 3));

                    //---- label12 ----
                    label12.setText("\u4fee\u6539\u7c7b\u522b\uff1a");
                    label12.setFont(label12.getFont().deriveFont(label12.getFont().getSize() + 3f));
                    label12.setHorizontalAlignment(SwingConstants.CENTER);
                    panel9.add(label12);
                }
                panel3.add(panel9);

                //---- comboBox2 ----
                comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                    "CustomerName",
                    "CustomerPhone",
                    "\u79fb\u9664\u987e\u5ba2\u4fe1\u606f"
                }));
                panel3.add(comboBox2);
            }
            panel1.add(panel3);

            //======== panel4 ========
            {
                panel4.setLayout(new GridLayout(0, 2));

                //======== panel10 ========
                {
                    panel10.setLayout(new GridLayout());

                    //---- label15 ----
                    label15.setText("\u4fee\u6539\u5185\u5bb9\uff1a");
                    label15.setFont(label15.getFont().deriveFont(label15.getFont().getSize() + 3f));
                    label15.setHorizontalAlignment(SwingConstants.CENTER);
                    panel10.add(label15);
                }
                panel4.add(panel10);
                panel4.add(textField4);
            }
            panel1.add(panel4);
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
                panel6.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
                javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax
                . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
                .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,panel6. getBorder( )) ); panel6. addPropertyChangeListener (new java. beans.
                PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .
                equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
                panel6.setLayout(new BorderLayout());

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
                panel6.add(scrollPane2, BorderLayout.CENTER);

                //---- label3 ----
                label3.setText("\u987e\u5ba2\u4fee\u6539\u4fe1\u606f\u5982\u4e0b\uff1a");
                label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 1f));
                panel6.add(label3, BorderLayout.NORTH);

                //---- label4 ----
                label4.setText("\u4fee\u6539\u4fe1\u606f\u65e0\u8bef\uff0c\u8bf7\u70b9\u51fbOK\u5b8c\u6210\u4fee\u6539\uff01");
                label4.setHorizontalAlignment(SwingConstants.TRAILING);
                panel6.add(label4, BorderLayout.SOUTH);
            }
            dialog1ContentPane.add(panel6, BorderLayout.CENTER);

            //======== panel7 ========
            {
                panel7.setLayout(new GridLayout(0, 2));

                //---- button3 ----
                button3.setText("OK");
                button3.addActionListener(e -> {
                    try {
                        button3ActionPerformed(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                panel7.add(button3);

                //---- button4 ----
                button4.setText("Cancel");
                button4.addActionListener(e -> button4ActionPerformed(e));
                panel7.add(button4);
            }
            dialog1ContentPane.add(panel7, BorderLayout.SOUTH);
            dialog1.pack();
            dialog1.setLocationRelativeTo(dialog1.getOwner());
        }

        //======== Dialog ========
        {
            Dialog.setAlwaysOnTop(true);
            var DialogContentPane = Dialog.getContentPane();
            DialogContentPane.setLayout(new BorderLayout());

            //---- button5 ----
            button5.setText("\u8fd4\u56de\u4fe1\u606f\u4fee\u6539\u754c\u9762");
            button5.addActionListener(e -> button5ActionPerformed(e));
            DialogContentPane.add(button5, BorderLayout.PAGE_END);

            //---- label5 ----
            label5.setText("\u4fe1\u606f\u4fee\u6539\u6210\u529f\uff01");
            label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 1f));
            label5.setHorizontalAlignment(SwingConstants.CENTER);
            DialogContentPane.add(label5, BorderLayout.CENTER);
            Dialog.setSize(200, 200);
            Dialog.setLocationRelativeTo(null);
        }

        //======== Dialog2 ========
        {
            Dialog2.setAlwaysOnTop(true);
            var Dialog2ContentPane = Dialog2.getContentPane();
            Dialog2ContentPane.setLayout(new BorderLayout());

            //---- button6 ----
            button6.setText("\u8fd4\u56de\u4fe1\u606f\u4fee\u6539\u754c\u9762");
            button6.addActionListener(e -> button6ActionPerformed(e));
            Dialog2ContentPane.add(button6, BorderLayout.PAGE_END);

            //---- label6 ----
            label6.setText("\u987e\u5ba2ID\u4e0d\u5b58\u5728\uff01");
            label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 1f));
            label6.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog2ContentPane.add(label6, BorderLayout.CENTER);
            Dialog2.setSize(200, 200);
            Dialog2.setLocationRelativeTo(null);
        }

        //======== Dialog3 ========
        {
            Dialog3.setAlwaysOnTop(true);
            var Dialog3ContentPane = Dialog3.getContentPane();
            Dialog3ContentPane.setLayout(new BorderLayout());

            //---- button7 ----
            button7.setText("\u8fd4\u56de\u4fe1\u606f\u4fee\u6539\u754c\u9762");
            button7.addActionListener(e -> button7ActionPerformed(e));
            Dialog3ContentPane.add(button7, BorderLayout.PAGE_END);

            //---- label7 ----
            label7.setText("\u7535\u8bdd\u683c\u5f0f\u4e0d\u6b63\u786e\uff01");
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 1f));
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog3ContentPane.add(label7, BorderLayout.CENTER);
            Dialog3.setSize(200, 200);
            Dialog3.setLocationRelativeTo(null);
        }

        //======== Dialog4 ========
        {
            Dialog4.setAlwaysOnTop(true);
            var Dialog4ContentPane = Dialog4.getContentPane();
            Dialog4ContentPane.setLayout(new BorderLayout());

            //---- button8 ----
            button8.setText("\u8fd4\u56de\u4fe1\u606f\u4fee\u6539\u754c\u9762");
            button8.addActionListener(e -> button8ActionPerformed(e));
            Dialog4ContentPane.add(button8, BorderLayout.PAGE_END);

            //---- label8 ----
            label8.setText("\u4fee\u6539\u5185\u5bb9\u4e0d\u53ef\u4e3a\u7a7a\uff01");
            label8.setFont(label8.getFont().deriveFont(label8.getFont().getSize() + 1f));
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog4ContentPane.add(label8, BorderLayout.CENTER);
            Dialog4.setSize(200, 200);
            Dialog4.setLocationRelativeTo(null);
        }

        //======== Dialog5 ========
        {
            Dialog5.setAlwaysOnTop(true);
            var Dialog5ContentPane = Dialog5.getContentPane();
            Dialog5ContentPane.setLayout(new BorderLayout());

            //---- label10 ----
            label10.setText("\u4fe1\u606f\u4fee\u6539\u5931\u8d25\uff01");
            label10.setFont(label10.getFont().deriveFont(label10.getFont().getSize() + 1f));
            label10.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog5ContentPane.add(label10, BorderLayout.CENTER);

            //======== panel11 ========
            {
                panel11.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
                javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax
                .swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java
                .awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt
                .Color.red),panel11. getBorder()));panel11. addPropertyChangeListener(new java.beans.
                PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".
                equals(e.getPropertyName()))throw new RuntimeException();}});
                panel11.setLayout(new GridLayout(1, 2));

                //---- button9 ----
                button9.setText("\u518d\u6b21\u5c1d\u8bd5");
                button9.addActionListener(e -> {
                    try {
                        button9ActionPerformed(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                panel11.add(button9);

                //---- button10 ----
                button10.setText("\u8fd4\u56de\u4fe1\u606f\u4fee\u6539\u754c\u9762");
                button10.addActionListener(e -> button10ActionPerformed(e));
                panel11.add(button10);
            }
            Dialog5ContentPane.add(panel11, BorderLayout.SOUTH);
            Dialog5.setSize(255, 200);
            Dialog5.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
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
    private JPanel panel9;
    private JLabel label12;
    private JComboBox<String> comboBox2;
    private JPanel panel4;
    private JPanel panel10;
    private JLabel label15;
    private JTextField textField4;
    private JDialog dialog1;
    private JPanel panel6;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JLabel label3;
    private JLabel label4;
    private JPanel panel7;
    private JButton button3;
    private JButton button4;
    private JDialog Dialog;
    private JButton button5;
    private JLabel label5;
    private JDialog Dialog2;
    private JButton button6;
    private JLabel label6;
    private JDialog Dialog3;
    private JButton button7;
    private JLabel label7;
    private JDialog Dialog4;
    private JButton button8;
    private JLabel label8;
    private JDialog Dialog5;
    private JLabel label10;
    private JPanel panel11;
    private JButton button9;
    private JButton button10;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
