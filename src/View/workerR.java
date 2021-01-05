/*
 * Created by JFormDesigner on Sat Jan 02 00:37:01 CST 2021
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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author unknown
 */
public class workerR extends JFrame {
    private String gettime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmm");
        Date data = new Date(System.currentTimeMillis());
        String WorkerID = formatter.format(data).toString();
        return WorkerID;
    }
    public workerR() {
        initComponents();
    }

    private void table1MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void table1PropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void button3ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button4ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button2ActionPerformed(ActionEvent e) {
        textField1.setText("");
        textField4.setText("");
        dialog2.dispose();
    }

    private void button5ActionPerformed(ActionEvent e) throws SQLException {
        dialog2.dispose();
        String name =textField1.getText();
        String sex=comboBox1.getSelectedItem().toString();
        String type = comboBox2.getSelectedItem().toString();
        String phone=textField4.getText();
        String WorkerID = table2.getValueAt(0,0).toString();
        String password = table2.getValueAt(0,5).toString();
        String sql = "insert into worker (WorkerID,WorkerName,WorkerSex,WorkerType,WorkerPhone,WorkerPassword) values (\""+WorkerID+"\",\""+name+"\",\""+sex+"\",\""+type+"\",\""+phone+"\",\""+password+"\")";
        boolean res = DBManager.getINSTANCE().executeUpdate(sql);
        if(res){
            Dialog.setVisible(true);
        }
        else{
            Dialog2.setVisible(true);
        }
    }

    private void button6ActionPerformed(ActionEvent e) {
        dialog2.dispose();
    }

    private void button7ActionPerformed(ActionEvent e) {
        textField1.setText("");
        textField4.setText("");
        Dialog.dispose();
        dialog2.dispose();
    }
    private void initTable(JTable table2,String workerID,String name,String sex,String type,String phone){

        String[] list = {"员工编号", "员工姓名", "员工性别","员工类型","员工电话","员工密码"};
        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        for(String i : list)
            model.addColumn(i);

        Object[][] arr;
        arr = new Object[1][6];

        arr[0][0] = workerID;
        arr[0][1] = name;
        arr[0][2] = sex;
        arr[0][3] = type;
        arr[0][4] = phone;
        arr[0][5] = "000000";
        //System.out.println(arr[0][0]);
        for (Object[] i : arr)
            model.addRow(i);
        table2.setEnabled(false);
    }

    private void button1ActionPerformed(ActionEvent e) {
        String name =textField1.getText();
        String sex=comboBox1.getSelectedItem().toString();
        String type = comboBox2.getSelectedItem().toString();
        String phone=textField4.getText();
        String WorkerID = gettime();
        if(textField1.getText().equals("")){
            Dialog4.setVisible(true);
        }
        else{
            if(phone.length()!=11)
            {
                Dialog3.setVisible(true);
            }
            else {
                dialog2.setVisible(true);
                initTable(table2, WorkerID, name, sex, type, phone);
            }
        }
    }

    private void button8ActionPerformed(ActionEvent e) throws SQLException {
        Dialog2.dispose();
        String name =textField1.getText();
        String sex=comboBox1.getSelectedItem().toString();
        String type = comboBox2.getSelectedItem().toString();
        String phone=textField4.getText();
        String WorkerID = table2.getValueAt(0,0).toString();
        String password = table2.getValueAt(5,0).toString();
        String sql = "insert into worker (WorkerID,WorkerName,WorkerSex,WorkerType,WorkerPhone,WorkerPassword) values (\""+WorkerID+"\",\""+name+"\",\""+sex+"\",\""+type+"\",\""+phone+"\",\""+password+"\")";
        boolean res = DBManager.getINSTANCE().executeUpdate(sql);
        if(res){
            Dialog.setVisible(true);
        }
        else{
            Dialog2.setVisible(true);
        }
    }

    private void button10ActionPerformed(ActionEvent e) {
        textField4.setText("");
        Dialog3.dispose();
    }

    private void button9ActionPerformed(ActionEvent e) {
        Dialog2.dispose();
        dialog2.dispose();
    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        this.dispose();
        new workerR().setVisible(true);
    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        this.dispose();
        new WorkerU().setVisible(true);
    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        this.dispose();
        new WorkerC().setVisible(true);
    }

    private void button11ActionPerformed(ActionEvent e) {
        Dialog4.dispose();
    }

    private void menuItem4ActionPerformed(ActionEvent e) {
        this.dispose();
        new LoginView().setVisible(true);
    }

    private void menuItem5ActionPerformed(ActionEvent e) {
        System.exit(0);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menu2 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        label1 = new JLabel();
        panel5 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel8 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        panel11 = new JPanel();
        label3 = new JLabel();
        comboBox1 = new JComboBox<>();
        panel3 = new JPanel();
        panel9 = new JPanel();
        label12 = new JLabel();
        comboBox2 = new JComboBox<>();
        panel4 = new JPanel();
        panel10 = new JPanel();
        label15 = new JLabel();
        textField4 = new JTextField();
        dialog2 = new JDialog();
        panel12 = new JPanel();
        scrollPane3 = new JScrollPane();
        table2 = new JTable();
        label4 = new JLabel();
        label5 = new JLabel();
        panel13 = new JPanel();
        button5 = new JButton();
        button6 = new JButton();
        Dialog = new JDialog();
        button7 = new JButton();
        label6 = new JLabel();
        Dialog2 = new JDialog();
        label7 = new JLabel();
        panel14 = new JPanel();
        button8 = new JButton();
        button9 = new JButton();
        Dialog3 = new JDialog();
        button10 = new JButton();
        label8 = new JLabel();
        Dialog4 = new JDialog();
        button11 = new JButton();
        label9 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u754c\u9762\u5207\u6362");

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

            //======== menu2 ========
            {
                menu2.setText("\u8bbe\u7f6e");

                //---- menuItem4 ----
                menuItem4.setText("\u767b\u51fa");
                menuItem4.addActionListener(e -> menuItem4ActionPerformed(e));
                menu2.add(menuItem4);

                //---- menuItem5 ----
                menuItem5.setText("\u9000\u51fa\u7cfb\u7edf");
                menuItem5.addActionListener(e -> menuItem5ActionPerformed(e));
                menu2.add(menuItem5);
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("\u5458\u5de5\u4fe1\u606f\u6ce8\u518c");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 6f));
        contentPane.add(label1, BorderLayout.NORTH);

        //======== panel5 ========
        {
            panel5.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
            javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax
            .swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java
            .awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt
            .Color.red),panel5. getBorder()));panel5. addPropertyChangeListener(new java.beans.
            PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".
            equals(e.getPropertyName()))throw new RuntimeException();}});
            panel5.setLayout(new GridLayout(0, 2));

            //---- button1 ----
            button1.setText("OK");
            button1.addActionListener(e -> button1ActionPerformed(e));
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
                    panel8.setLayout(new GridLayout(1, 2));

                    //---- label2 ----
                    label2.setText("\u59d3\u540d\uff1a");
                    label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 3f));
                    label2.setHorizontalAlignment(SwingConstants.CENTER);
                    panel8.add(label2);
                    panel8.add(textField1);
                }
                panel2.add(panel8);

                //======== panel11 ========
                {
                    panel11.setLayout(new GridLayout(1, 2));

                    //---- label3 ----
                    label3.setText("\u6027\u522b\uff1a");
                    label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 3f));
                    label3.setHorizontalAlignment(SwingConstants.CENTER);
                    panel11.add(label3);

                    //---- comboBox1 ----
                    comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                        "\u7537",
                        "\u5973"
                    }));
                    panel11.add(comboBox1);
                }
                panel2.add(panel11);
            }
            panel1.add(panel2);

            //======== panel3 ========
            {
                panel3.setLayout(new GridLayout(0, 2));

                //======== panel9 ========
                {
                    panel9.setLayout(new GridLayout(1, 3));

                    //---- label12 ----
                    label12.setText("\u5458\u5de5\u7c7b\u578b\uff1a");
                    label12.setFont(label12.getFont().deriveFont(label12.getFont().getSize() + 3f));
                    label12.setHorizontalAlignment(SwingConstants.CENTER);
                    panel9.add(label12);
                }
                panel3.add(panel9);

                //---- comboBox2 ----
                comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u5458\u5de5",
                    "\u9a8c\u6536\u5458",
                    "\u7ba1\u7406\u5458",
                    "\u836f\u5e08"
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
                    label15.setText("\u7535\u8bdd\u53f7\u7801\uff1a");
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

        //======== dialog2 ========
        {
            var dialog2ContentPane = dialog2.getContentPane();
            dialog2ContentPane.setLayout(new BorderLayout());

            //======== panel12 ========
            {
                panel12.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .
                EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER ,javax . swing
                . border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,
                java . awt. Color .red ) ,panel12. getBorder () ) ); panel12. addPropertyChangeListener( new java. beans .PropertyChangeListener ( )
                { @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )
                throw new RuntimeException( ) ;} } );
                panel12.setLayout(new BorderLayout());

                //======== scrollPane3 ========
                {

                    //---- table2 ----
                    table2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            table1MouseClicked(e);
                        }
                    });
                    table2.addPropertyChangeListener(e -> table1PropertyChange(e));
                    scrollPane3.setViewportView(table2);
                }
                panel12.add(scrollPane3, BorderLayout.CENTER);

                //---- label4 ----
                label4.setText("\u5458\u5de5\u6ce8\u518c\u4fe1\u606f\u5982\u4e0b\uff1a");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 1f));
                panel12.add(label4, BorderLayout.NORTH);

                //---- label5 ----
                label5.setText("\u4fe1\u606f\u65e0\u8bef\uff0c\u8bf7\u70b9\u51fbOK\u5b8c\u6210\u6ce8\u518c\uff01");
                label5.setHorizontalAlignment(SwingConstants.TRAILING);
                panel12.add(label5, BorderLayout.SOUTH);
            }
            dialog2ContentPane.add(panel12, BorderLayout.CENTER);

            //======== panel13 ========
            {
                panel13.setLayout(new GridLayout(0, 2));

                //---- button5 ----
                button5.setText("OK");
                button5.addActionListener(e -> {
                    try {
                        button5ActionPerformed(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                panel13.add(button5);

                //---- button6 ----
                button6.setText("Cancel");
                button6.addActionListener(e -> button6ActionPerformed(e));
                panel13.add(button6);
            }
            dialog2ContentPane.add(panel13, BorderLayout.SOUTH);
            dialog2.pack();
            dialog2.setLocationRelativeTo(dialog2.getOwner());
        }

        //======== Dialog ========
        {
            Dialog.setAlwaysOnTop(true);
            var DialogContentPane = Dialog.getContentPane();
            DialogContentPane.setLayout(new BorderLayout());

            //---- button7 ----
            button7.setText("\u8fd4\u56de\u6ce8\u518c\u754c\u9762");
            button7.addActionListener(e -> button7ActionPerformed(e));
            DialogContentPane.add(button7, BorderLayout.PAGE_END);

            //---- label6 ----
            label6.setText("\u6ce8\u518c\u6210\u529f\uff01");
            label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 1f));
            label6.setHorizontalAlignment(SwingConstants.CENTER);
            DialogContentPane.add(label6, BorderLayout.CENTER);
            Dialog.setSize(200, 200);
            Dialog.setLocationRelativeTo(null);
        }

        //======== Dialog2 ========
        {
            Dialog2.setAlwaysOnTop(true);
            var Dialog2ContentPane = Dialog2.getContentPane();
            Dialog2ContentPane.setLayout(new BorderLayout());

            //---- label7 ----
            label7.setText("\u4fe1\u606f\u63d2\u5165\u5931\u8d25\uff01");
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 1f));
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog2ContentPane.add(label7, BorderLayout.CENTER);

            //======== panel14 ========
            {
                panel14.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
                swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border
                . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067"
                ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel14. getBorder
                ( )) ); panel14. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
                .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException
                ( ); }} );
                panel14.setLayout(new GridLayout(1, 2));

                //---- button8 ----
                button8.setText("\u518d\u6b21\u5c1d\u8bd5");
                button8.addActionListener(e -> {
                    try {
                        button8ActionPerformed(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                panel14.add(button8);

                //---- button9 ----
                button9.setText("\u8fd4\u56de\u6ce8\u518c\u754c\u9762");
                button9.addActionListener(e -> button9ActionPerformed(e));
                panel14.add(button9);
            }
            Dialog2ContentPane.add(panel14, BorderLayout.SOUTH);
            Dialog2.setSize(200, 200);
            Dialog2.setLocationRelativeTo(null);
        }

        //======== Dialog3 ========
        {
            Dialog3.setAlwaysOnTop(true);
            var Dialog3ContentPane = Dialog3.getContentPane();
            Dialog3ContentPane.setLayout(new BorderLayout());

            //---- button10 ----
            button10.setText("\u8fd4\u56de\u6ce8\u518c\u754c\u9762");
            button10.addActionListener(e -> button10ActionPerformed(e));
            Dialog3ContentPane.add(button10, BorderLayout.PAGE_END);

            //---- label8 ----
            label8.setText("\u7535\u8bdd\u683c\u5f0f\u4e0d\u6b63\u786e\uff01");
            label8.setFont(label8.getFont().deriveFont(label8.getFont().getSize() + 1f));
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog3ContentPane.add(label8, BorderLayout.CENTER);
            Dialog3.setSize(200, 200);
            Dialog3.setLocationRelativeTo(null);
        }

        //======== Dialog4 ========
        {
            Dialog4.setAlwaysOnTop(true);
            var Dialog4ContentPane = Dialog4.getContentPane();
            Dialog4ContentPane.setLayout(new BorderLayout());

            //---- button11 ----
            button11.setText("\u8fd4\u56de\u6ce8\u518c\u754c\u9762");
            button11.addActionListener(e -> button11ActionPerformed(e));
            Dialog4ContentPane.add(button11, BorderLayout.PAGE_END);

            //---- label9 ----
            label9.setText("\u59d3\u540d\u4e0d\u53ef\u4e3a\u7a7a\uff01");
            label9.setFont(label9.getFont().deriveFont(label9.getFont().getSize() + 1f));
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog4ContentPane.add(label9, BorderLayout.CENTER);
            Dialog4.setSize(200, 200);
            Dialog4.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenu menu2;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JLabel label1;
    private JPanel panel5;
    private JButton button1;
    private JButton button2;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel8;
    private JLabel label2;
    private JTextField textField1;
    private JPanel panel11;
    private JLabel label3;
    private JComboBox<String> comboBox1;
    private JPanel panel3;
    private JPanel panel9;
    private JLabel label12;
    private JComboBox<String> comboBox2;
    private JPanel panel4;
    private JPanel panel10;
    private JLabel label15;
    private JTextField textField4;
    private JDialog dialog2;
    private JPanel panel12;
    private JScrollPane scrollPane3;
    private JTable table2;
    private JLabel label4;
    private JLabel label5;
    private JPanel panel13;
    private JButton button5;
    private JButton button6;
    private JDialog Dialog;
    private JButton button7;
    private JLabel label6;
    private JDialog Dialog2;
    private JLabel label7;
    private JPanel panel14;
    private JButton button8;
    private JButton button9;
    private JDialog Dialog3;
    private JButton button10;
    private JLabel label8;
    private JDialog Dialog4;
    private JButton button11;
    private JLabel label9;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
