/*
 * Created by JFormDesigner on Sun Jan 03 16:00:24 CST 2021
 */

package View;

import Model.DBManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author unknown
 */
public class WorkerC extends JFrame {
    public WorkerC() {
        initComponents();
    }

    private void searcheditFocusGained(FocusEvent e) {
        // TODO add your code here
    }

    private void searcheditFocusLost(FocusEvent e) {
        // TODO add your code here
    }

    private void searchActionPerformed(ActionEvent e)  {
        if(searchedit.getText().equals("")){
            try {
                initTableAll(table1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            try {
                initTable(table1,comboBox1.getSelectedItem().toString(),searchedit.getText());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void table1MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void table1PropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void queryAllActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button2ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button5ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void viewoutdueActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void CommonlyActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button8ActionPerformed(ActionEvent e) {
        this.dispose();
    }
    private void initTable(JTable table1,String type,String check) throws SQLException {

        String[] list = {"员工ID", "员工姓名","员工性别","员工类型","员工电话","员工密码"};
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        for(String i : list)
            model.addColumn(i);
        Object[][] arr;
        arr = new Object[100][6];
        int j=0;
        ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from worker where "+ type + " = \"" + check + "\"");
        while(rs.next()) {
            arr[j][0] = rs.getString("WorkerID");
            arr[j][1] = rs.getString("WorkerName");
            arr[j][2] = rs.getString("WorkerSex");
            arr[j][3]= rs.getString("WorkerType");
            arr[j][4]= rs.getString("WorkerPhone");
            arr[j][5]= rs.getString("WorkerPassword");
            //System.out.println(arr[0][0]);
            j++;
        }

        for (Object[] i : arr)
            model.addRow(i);
        table1.setEnabled(false);

    }

    private void initTableAll(JTable table1) throws SQLException {

        String[] list = {"员工ID", "员工姓名","员工性别","员工类型","员工电话","员工密码"};
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        for(String i : list)
            model.addColumn(i);
        Object[][] arr;
        arr = new Object[100][6];
        int j=0;
        ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from worker");
        while(rs.next()) {
            arr[j][0] = rs.getString("WorkerID");
            arr[j][1] = rs.getString("WorkerName");
            arr[j][2] = rs.getString("WorkerSex");
            arr[j][3]= rs.getString("WorkerType");
            arr[j][4]= rs.getString("WorkerPhone");
            arr[j][5]= rs.getString("WorkerPassword");
            //System.out.println(arr[0][0]);
            j++;
        }

        for (Object[] i : arr)
            model.addRow(i);
        table1.setEnabled(false);

    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        this.dispose();
        new workerR().setVisible(true);
    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        this.dispose();
        new WorkerU().setVisible(true);
    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        this.dispose();
        new LoginView().setVisible(true);
    }

    private void menuItem4ActionPerformed(ActionEvent e) {
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
        menuItem1 = new JMenuItem();
        menuItem4 = new JMenuItem();
        scrollPane1 = new JScrollPane();
        panel2 = new JPanel();
        panel3 = new JPanel();
        searchedit = new JTextField();
        search = new JButton();
        label1 = new JLabel();
        panel4 = new JPanel();
        label2 = new JLabel();
        comboBox1 = new JComboBox<>();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        Dialog3 = new JDialog();
        button8 = new JButton();
        label7 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u754c\u9762\u5207\u6362");

                //---- menuItem2 ----
                menuItem2.setText("\u4fe1\u606f\u6ce8\u518c");
                menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                menu1.add(menuItem2);

                //---- menuItem3 ----
                menuItem3.setText("\u4fe1\u606f\u4fee\u6539");
                menuItem3.addActionListener(e -> menuItem3ActionPerformed(e));
                menu1.add(menuItem3);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u8bbe\u7f6e");

                //---- menuItem1 ----
                menuItem1.setText("\u767b\u51fa");
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu2.add(menuItem1);

                //---- menuItem4 ----
                menuItem4.setText("\u9000\u51fa\u7cfb\u7edf");
                menuItem4.addActionListener(e -> menuItem4ActionPerformed(e));
                menu2.add(menuItem4);
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {

            //======== panel2 ========
            {
                panel2.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
                0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
                . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
                red) ,panel2. getBorder( )) ); panel2. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
                beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
                panel2.setLayout(new BorderLayout());

                //======== panel3 ========
                {
                    panel3.setLayout(new BorderLayout());

                    //---- searchedit ----
                    searchedit.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                    searchedit.setToolTipText("\u5728\u8fd9\u91cc\u8f93\u5165\u7b5b\u9009\u836f\u54c1\u540d");
                    panel3.add(searchedit, BorderLayout.CENTER);

                    //---- search ----
                    search.setText("\u67e5\u8be2");
                    search.addActionListener(e -> searchActionPerformed(e));
                    panel3.add(search, BorderLayout.EAST);

                    //---- label1 ----
                    label1.setText("\u5458\u5de5\u7b5b\u9009\uff1a");
                    label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 1f));
                    panel3.add(label1, BorderLayout.WEST);

                    //======== panel4 ========
                    {
                        panel4.setLayout(new GridLayout(1, 2));

                        //---- label2 ----
                        label2.setText("\u9009\u62e9\u67e5\u8be2\u5c5e\u6027\uff1a");
                        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 1f));
                        label2.setHorizontalAlignment(SwingConstants.CENTER);
                        panel4.add(label2);

                        //---- comboBox1 ----
                        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                            "WorkerID",
                            "WorkerName",
                            "WorkerSex",
                            "WorkerType",
                            "WorkerPhone",
                            "WorkerPassword"
                        }));
                        panel4.add(comboBox1);
                    }
                    panel3.add(panel4, BorderLayout.NORTH);
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
        pack();
        setLocationRelativeTo(getOwner());

        //======== Dialog3 ========
        {
            Dialog3.setAlwaysOnTop(true);
            var Dialog3ContentPane = Dialog3.getContentPane();
            Dialog3ContentPane.setLayout(new BorderLayout());

            //---- button8 ----
            button8.setText("\u8fd4\u56de\u67e5\u8be2\u754c\u9762");
            button8.addActionListener(e -> button8ActionPerformed(e));
            Dialog3ContentPane.add(button8, BorderLayout.PAGE_END);

            //---- label7 ----
            label7.setText("\u67e5\u8be2\u5185\u5bb9\u4e0d\u53ef\u4e3a\u7a7a\uff01");
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 1f));
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog3ContentPane.add(label7, BorderLayout.CENTER);
            Dialog3.setSize(200, 200);
            Dialog3.setLocationRelativeTo(null);
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
    private JMenuItem menuItem1;
    private JMenuItem menuItem4;
    private JScrollPane scrollPane1;
    private JPanel panel2;
    private JPanel panel3;
    private JTextField searchedit;
    private JButton search;
    private JLabel label1;
    private JPanel panel4;
    private JLabel label2;
    private JComboBox<String> comboBox1;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JDialog Dialog3;
    private JButton button8;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
