/*
 * Created by JFormDesigner on Mon Dec 28 17:43:01 GMT+08:00 2020
 */

package View;

import Model.DBManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author rarcher
 */
public class LoginView extends JFrame {
    public LoginView() {
        initComponents();
    }

    private void button2ActionPerformed(ActionEvent e) {

    }

    private void buttonBarMouseClicked(MouseEvent e) {

    }


    private void okButtonActionPerformed(ActionEvent e) {

        // 检查登记条件
        String ID = textField1.getText();
        String password = Arrays.toString(passwordField1.getPassword());
        if(checkID(ID)) {
            if(check(ID,password)){

                if(gettype(ID).equals("员工"))
                {
                    new CustomerR().setVisible(true);
                    closeAll();
                    this.dispose();
                }
                else{
                    if(gettype(ID).equals("管理员")){
                        new workerR().setVisible(true);
                        closeAll();
                        this.dispose();
                    }
                    else{
                        if(gettype(ID).equals("药师")){
                            new Judge().setVisible(true);
                            closeAll();
                            this.dispose();
                        }
                        else{
                            if(gettype(ID).equals("验收员")){
                            new Judge().setVisible(true);
                            closeAll();
                            this.dispose();}
                            else{
                                Dialog2.setVisible(true);
                            }
                        }
                    }
                }
            }
            else{
                Dialog1.setVisible(true);
            }

        }
        else
        {
            Dialog.setVisible(true);
        }

    }

    private void cancelButtonActionPerformed(ActionEvent e) {

    }
    private boolean check(String ID,String password){
        //String sql = "delete from overdue";
        try {
            ResultSet rs = DBManager.getINSTANCE().executeQuery("select WorkerPassword from worker where WorkerID = \"" + ID + "\"");
            if(rs.next()){
                if(rs.getString("WorkerPassword").equals(password))
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
         return false;
    }

    private boolean checkID(String ID) {
        //String sql = "delete from overdue";
        try {
            // DBManager.getINSTANCE().executeUpdate(sql);

                ResultSet rs =  DBManager.getINSTANCE().executeQuery("select * from worker where WorkerID = \""+ID+"\"");
                if(rs.next()){
                    return true;
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private String gettype(String ID) {
        //String sql = "delete from overdue";
        String type = "";
        try {
            // DBManager.getINSTANCE().executeUpdate(sql);

            ResultSet rs = DBManager.getINSTANCE().executeQuery("select WorkerType from worker where WorkerID = \"" + ID + "\"");
            if(rs.next()){
                type = rs.getString("WorkerType");
                return type;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return type;
    }



    private void closeAll(){
        if (Dialog.isVisible())
            Dialog.dispose();
        if (Dialog1.isVisible())
            Dialog1.dispose();

    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        ResourceBundle bundle = ResourceBundle.getBundle("View.form");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel8 = new JPanel();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        textField1 = new JTextArea();
        panel4 = new JPanel();
        panel10 = new JPanel();
        label15 = new JLabel();
        passwordField1 = new JPasswordField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        Dialog = new JDialog();
        button2 = new JButton();
        label3 = new JLabel();
        label4 = new JLabel();
        Dialog1 = new JDialog();
        button3 = new JButton();
        label5 = new JLabel();
        label6 = new JLabel();
        Dialog2 = new JDialog();
        button4 = new JButton();
        label7 = new JLabel();
        label8 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (e -> {if ("\u0062or\u0064er" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); });
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout());

                //---- label1 ----
                label1.setText(bundle.getString("label1.text_2"));
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 6f));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label1, BorderLayout.NORTH);

                //======== panel1 ========
                {
                    panel1.setLayout(new GridLayout(2, 0));

                    //======== panel2 ========
                    {
                        panel2.setLayout(new GridLayout(0, 1));

                        //======== panel8 ========
                        {
                            panel8.setLayout(new GridLayout(1, 2));

                            //---- label2 ----
                            label2.setText(bundle.getString("label2.text_2"));
                            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 3f));
                            label2.setHorizontalAlignment(SwingConstants.CENTER);
                            panel8.add(label2);

                            //======== scrollPane1 ========
                            {
                                scrollPane1.setViewportView(textField1);
                            }
                            panel8.add(scrollPane1);
                        }
                        panel2.add(panel8);
                    }
                    panel1.add(panel2);

                    //======== panel4 ========
                    {
                        panel4.setLayout(new GridLayout(0, 2));

                        //======== panel10 ========
                        {
                            panel10.setLayout(new GridLayout());

                            //---- label15 ----
                            label15.setText(bundle.getString("label15.text"));
                            label15.setFont(label15.getFont().deriveFont(label15.getFont().getSize() + 3f));
                            label15.setHorizontalAlignment(SwingConstants.CENTER);
                            panel10.add(label15);
                        }
                        panel4.add(panel10);
                        panel4.add(passwordField1);
                    }
                    panel1.add(panel4);
                }
                contentPanel.add(panel1, BorderLayout.CENTER);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        buttonBarMouseClicked(e);
                    }
                });
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText(bundle.getString("okButton.text"));
                okButton.addActionListener(this::okButtonActionPerformed);
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText(bundle.getString("cancelButton.text"));
                cancelButton.addActionListener(this::cancelButtonActionPerformed);
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //======== Dialog ========
        {
            Dialog.setAlwaysOnTop(true);
            var DialogContentPane = Dialog.getContentPane();
            DialogContentPane.setLayout(new BorderLayout());

            //---- button2 ----
            button2.setText("\u91cd\u65b0\u767b\u5f55");
            button2.addActionListener(this::button2ActionPerformed);
            DialogContentPane.add(button2, BorderLayout.PAGE_END);

            //---- label3 ----
            label3.setText("\u5458\u5de5ID\u4e0d\u5b58\u5728\uff01");
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 1f));
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            DialogContentPane.add(label3, BorderLayout.CENTER);

            //---- label4 ----
            label4.setText("\u767b\u5f55\u9519\u8bef\uff1a");
            DialogContentPane.add(label4, BorderLayout.PAGE_START);
            Dialog.setSize(200, 200);
            Dialog.setLocationRelativeTo(null);
        }

        //======== Dialog1 ========
        {
            Dialog1.setAlwaysOnTop(true);
            var Dialog1ContentPane = Dialog1.getContentPane();
            Dialog1ContentPane.setLayout(new BorderLayout());

            //---- button3 ----
            button3.setText("\u91cd\u65b0\u767b\u5f55");
            button3.addActionListener(this::button2ActionPerformed);
            Dialog1ContentPane.add(button3, BorderLayout.PAGE_END);

            //---- label5 ----
            label5.setText("\u5bc6\u7801\u8f93\u5165\u9519\u8bef\uff01");
            label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 1f));
            label5.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog1ContentPane.add(label5, BorderLayout.CENTER);

            //---- label6 ----
            label6.setText("\u767b\u5f55\u9519\u8bef\uff1a");
            Dialog1ContentPane.add(label6, BorderLayout.PAGE_START);
            Dialog1.setSize(200, 200);
            Dialog1.setLocationRelativeTo(null);
        }

        //======== Dialog2 ========
        {
            Dialog2.setAlwaysOnTop(true);
            var Dialog2ContentPane = Dialog2.getContentPane();
            Dialog2ContentPane.setLayout(new BorderLayout());

            //---- button4 ----
            button4.setText("\u91cd\u65b0\u767b\u5f55");
            button4.addActionListener(this::button2ActionPerformed);
            Dialog2ContentPane.add(button4, BorderLayout.PAGE_END);

            //---- label7 ----
            label7.setText("\u5458\u5de5\u7c7b\u578b\u83b7\u53d6\u9519\u8bef\uff01");
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 1f));
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            Dialog2ContentPane.add(label7, BorderLayout.CENTER);

            //---- label8 ----
            label8.setText("\u767b\u5f55\u9519\u8bef\uff1a");
            Dialog2ContentPane.add(label8, BorderLayout.PAGE_START);
            Dialog2.setSize(200, 200);
            Dialog2.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel8;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JTextArea textField1;
    private JPanel panel4;
    private JPanel panel10;
    private JLabel label15;
    private JPasswordField passwordField1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private JDialog Dialog;
    private JButton button2;
    private JLabel label3;
    private JLabel label4;
    private JDialog Dialog1;
    private JButton button3;
    private JLabel label5;
    private JLabel label6;
    private JDialog Dialog2;
    private JButton button4;
    private JLabel label7;
    private JLabel label8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
