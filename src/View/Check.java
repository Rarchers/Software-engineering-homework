/*
 * Created by JFormDesigner on Sun Jan 03 12:04:01 CST 2021
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
 * @author yufei
 */
public class Check extends JFrame {
    public Check() {
        initComponents();
    }


    private void table1PropertyChange(PropertyChangeEvent e) {
        initTable(table1);
    }

    private void button1ActionPerformed(ActionEvent e) {
        initTable(table1);
    }

    Object[][] arr;
    public String selectName ;
    private void initTable(JTable table1){
        String[] list = {"药品名称", "补货数量","到货数量","退货数量","退货原因"};
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
                    String jNum = rs.getString("Num");
                    arr[j][0] = medName;
                    arr[j][1] = jNum;
                    j++;
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public  int r;
    public int l;

    private void table1MouseClicked(MouseEvent e)  {

        r= table1.rowAtPoint(e.getPoint());
        int c = 0;
        //得到选中的单元格的值，表格中都是字符串
        Object value= table1.getValueAt(r, c);
       /*
        javax.swing.JOptionPane.showMessageDialog(null,info);*/
        selectName = value.toString();
        //dialog1.setVisible(true);
        label4.setText(selectName);
        label9.setText(selectName);
        label12.setText(selectName);
        l=table1.columnAtPoint(e.getPoint());
        String info=r+"行"+l+"列值 : "+value.toString();
        System.out.println(info);
        switch (l){
            case 2:{
                dialog1.setVisible(true);
                if (dialog2.isVisible())
                dialog2.dispose();
                if (dialog3.isVisible())
                dialog3.dispose();
                table1.setValueAt(DaoNum,r,l);
                DaoNum=0;
                break;
            }
            case 3:{
                dialog2.setVisible(true);
                dialog3.setVisible(false);
                dialog1.setVisible(false);
                table1.setValueAt(yanNum,r,l);
                yanNum=0;
                break;
            }
            case 4:{
                dialog3.setVisible(true);
                dialog1.setVisible(false);
                dialog2.setVisible(false);
                table1.setValueAt(reason,r,l);
                reason=null;
                break;
            }
        }

    }

    public int DaoNum=0;
    public int yanNum=0;

    private String reason=null;

    private void dialog1PropertyChange(PropertyChangeEvent e) {
        selectName=table1.getValueAt(r,0).toString();
        label4.setText(selectName);
    }
    private void dialog2PropertyChange(PropertyChangeEvent e) {
        selectName=table1.getValueAt(r,0).toString();
        label9.setText(selectName);
    }
    private void dialog3PropertyChange(PropertyChangeEvent e) {
        selectName=table1.getValueAt(r,0).toString();
        label12.setText(selectName);
    }

    private void button5ActionPerformed(ActionEvent e) {
        yanNum=Integer.parseInt(textField2.getText());
        table1.setValueAt(yanNum,r,l);
        yanNum=0;
        dialog2.dispose();
    }

    private void button4ActionPerformed(ActionEvent e) {//关闭dialog1
        // TODO add your code here
        dialog1.dispose();
    }

    private void button6ActionPerformed(ActionEvent e) {
        // TODO add your code here
        dialog2.dispose();
    }

    private void button3ActionPerformed(ActionEvent e) {
        DaoNum=Integer.parseInt(textField1.getText());
        table1.setValueAt(DaoNum,r,l);
        DaoNum=0;
        dialog1.dispose();
    }

    private void button8ActionPerformed(ActionEvent e) {
        // TODO add your code here
        dialog3.dispose();
    }

    private void dialog1WindowOpened(WindowEvent e) {
        selectName=table1.getValueAt(r,0).toString();
        label4.setText(selectName);
    }

    private void dialog3WindowOpened(WindowEvent e) {
        selectName=table1.getValueAt(r,0).toString();
        label12.setText(selectName);
    }

    private void dialog2WindowOpened(WindowEvent e) {
        selectName=table1.getValueAt(r,0).toString();
        label9.setText(selectName);
    }
    private void button7ActionPerformed(ActionEvent e) {
        reason=textField3.getText();
        table1.setValueAt(reason,r,l);
        reason=null;
        dialog3.dispose();
    }

    private void button2ActionPerformed(ActionEvent e) {//插入数据库，切换窗口
        int r=1;//行
        int c=0;//列
        int rows=table1.getRowCount();
        Worker=textField4.getText().toString();

        try{
            for(int i=0;i<rows;i++){
                int c2,c3,c4;
                String c1,c5;
                c1=table1.getValueAt(i,c).toString();//药品名称
                System.out.println(c1);
                c++;
                c2=Integer.parseInt(table1.getValueAt(i,c).toString());//订货数量
                System.out.println(c2);
                c++;
                c3=Integer.parseInt(table1.getValueAt(i,c).toString());//到货数量
                System.out.println(c3);
                c++;
                c4=Integer.parseInt(table1.getValueAt(i,c).toString());//退货数量
                System.out.println(c4);
                if(c4!=0){
                c3=c3-c4;//验收数量
                c5=table1.getValueAt(i,++c).toString();
                }
               else{
                   c5=null;
                }
                c=0;
                DBManager.getINSTANCE().executeUpdate("insert into checks (medname,jnum,innum,workerid) values('"+c1+"',"+c2+","+c3+",'"+Worker+"')");
                if(c4!=0){//退货清单
                    DBManager.getINSTANCE().executeUpdate("insert into returngoods (medname,OutNum,reason,workerid) values('"+c1+"',"+c4+",'"+c5+"','"+Worker+"')");
                }
            }
        }  catch (Exception q ){
            q.printStackTrace();
        }
        PutIn ca=new PutIn();
        ca.setVisible(true);
        this.dispose();
    }

    public String Worker;
    private void textField4ActionPerformed(ActionEvent e) {
        Worker=textField4.getText().toString();
    }

    private void button1MouseClicked(MouseEvent e) {//刷新
        initTable(table1);

    }

    private void button3MouseClicked(MouseEvent e) {
        DaoNum=Integer.parseInt(textField1.getText());
        textField1.getText();
        textField1.setText("");
        table1.setValueAt(DaoNum,r,l);
        DaoNum=0;
        table1.setValueAt(0,r,(l+1));
        table1.setValueAt("",r,(l+2));
        dialog1.dispose();
    }

    private void button5MouseClicked(MouseEvent e) {
        yanNum=Integer.parseInt(textField2.getText());
        textField2.getText();
        textField2.setText("");
        table1.setValueAt(yanNum,r,l);
        yanNum=0;
        dialog2.dispose();
    }

    private void button7MouseClicked(MouseEvent e) {
        reason=textField3.getText();
        textField3.getText();
        textField3.setText("");
        table1.setValueAt(reason,r,l);
        reason=null;
        dialog3.dispose();
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - yufei
        label1 = new JLabel();
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        label14 = new JLabel();
        textField4 = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        dialog1 = new JDialog();
        label2 = new JLabel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        label3 = new JLabel();
        label4 = new JLabel();
        panel4 = new JPanel();
        label5 = new JLabel();
        textField1 = new JTextField();
        panel5 = new JPanel();
        button3 = new JButton();
        button4 = new JButton();
        dialog2 = new JDialog();
        label6 = new JLabel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        label7 = new JLabel();
        label9 = new JLabel();
        panel8 = new JPanel();
        label8 = new JLabel();
        textField2 = new JTextField();
        panel9 = new JPanel();
        button5 = new JButton();
        button6 = new JButton();
        dialog3 = new JDialog();
        label10 = new JLabel();
        panel10 = new JPanel();
        panel11 = new JPanel();
        label11 = new JLabel();
        label12 = new JLabel();
        panel12 = new JPanel();
        label13 = new JLabel();
        textField3 = new JTextField();
        panel13 = new JPanel();
        button7 = new JButton();
        button8 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //---- label1 ----
        label1.setText("\u836f\u54c1\u68c0\u67e5");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1, BorderLayout.NORTH);

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
            . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing
            .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
            Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
            ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
            public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName (
            ) ) )throw new RuntimeException( ) ;} } );
            panel1.setLayout(new GridLayout(10, 0));

            //---- button1 ----
            button1.setText("\u5237\u65b0");
            button1.addActionListener(e -> button1ActionPerformed(e));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            panel1.add(button1);

            //---- button2 ----
            button2.setText("\u5b8c\u6210");
            button2.addActionListener(e -> button2ActionPerformed(e));
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            panel1.add(button2);

            //---- label14 ----
            label14.setText("\u5458\u5de5\u53f7\uff1a");
            panel1.add(label14);

            //---- textField4 ----
            textField4.addActionListener(e -> textField4ActionPerformed(e));
            panel1.add(textField4);
        }
        contentPane.add(panel1, BorderLayout.WEST);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //======== dialog1 ========
        {
            dialog1.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    dialog1WindowOpened(e);
                }
            });
            var dialog1ContentPane = dialog1.getContentPane();
            dialog1ContentPane.setLayout(new BorderLayout());

            //---- label2 ----
            label2.setText("\u5230\u8d27\u4fe1\u606f\u586b\u5199");
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            dialog1ContentPane.add(label2, BorderLayout.NORTH);

            //======== panel2 ========
            {
                panel2.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (
                new javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e"
                , javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
                , new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 )
                , java. awt. Color. red) ,panel2. getBorder( )) ); panel2. addPropertyChangeListener (
                new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
                ) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( )
                ; }} );
                panel2.setLayout(new GridLayout(3, 0));

                //======== panel3 ========
                {
                    panel3.setLayout(new GridLayout(1, 2));

                    //---- label3 ----
                    label3.setText("\u836f\u54c1\u540d\u79f0\uff1a");
                    panel3.add(label3);

                    //---- label4 ----
                    label4.setText("text");
                    panel3.add(label4);
                }
                panel2.add(panel3);

                //======== panel4 ========
                {
                    panel4.setLayout(new GridLayout(1, 2));

                    //---- label5 ----
                    label5.setText("\u5230\u8d27\u6570\u91cf\uff1a");
                    panel4.add(label5);
                    panel4.add(textField1);
                }
                panel2.add(panel4);

                //======== panel5 ========
                {
                    panel5.setLayout(new GridLayout(1, 2));

                    //---- button3 ----
                    button3.setText("\u786e\u5b9a");
                    button3.addActionListener(e -> {
			button3ActionPerformed(e);
			button3ActionPerformed(e);
		});
                    button3.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            button3MouseClicked(e);
                        }
                    });
                    panel5.add(button3);

                    //---- button4 ----
                    button4.setText("\u53d6\u6d88");
                    button4.addActionListener(e -> button4ActionPerformed(e));
                    panel5.add(button4);
                }
                panel2.add(panel5);
            }
            dialog1ContentPane.add(panel2, BorderLayout.CENTER);
            dialog1.pack();
            dialog1.setLocationRelativeTo(dialog1.getOwner());
        }

        //======== dialog2 ========
        {
            var dialog2ContentPane = dialog2.getContentPane();
            dialog2ContentPane.setLayout(new BorderLayout());

            //---- label6 ----
            label6.setText("\u9000\u8d27\u4fe1\u606f\u586b\u5199");
            label6.setHorizontalAlignment(SwingConstants.CENTER);
            dialog2ContentPane.add(label6, BorderLayout.NORTH);

            //======== panel6 ========
            {
                panel6.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
                . swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing
                . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
                Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
                ) ,panel6. getBorder( )) ); panel6. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
                public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName (
                ) )) throw new RuntimeException( ); }} );
                panel6.setLayout(new GridLayout(3, 0));

                //======== panel7 ========
                {
                    panel7.setLayout(new GridLayout(1, 2));

                    //---- label7 ----
                    label7.setText("\u836f\u54c1\u540d\u79f0\uff1a");
                    panel7.add(label7);

                    //---- label9 ----
                    label9.setText("text");
                    panel7.add(label9);
                }
                panel6.add(panel7);

                //======== panel8 ========
                {
                    panel8.setLayout(new GridLayout(1, 2));

                    //---- label8 ----
                    label8.setText("\u9000\u8d27\u6570\u91cf\uff1a");
                    panel8.add(label8);
                    panel8.add(textField2);
                }
                panel6.add(panel8);

                //======== panel9 ========
                {
                    panel9.setLayout(new GridLayout(1, 2));

                    //---- button5 ----
                    button5.setText("\u786e\u5b9a");
                    button5.addActionListener(e -> button5ActionPerformed(e));
                    button5.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            button5MouseClicked(e);
                        }
                    });
                    panel9.add(button5);

                    //---- button6 ----
                    button6.setText("\u53d6\u6d88");
                    button6.addActionListener(e -> button6ActionPerformed(e));
                    panel9.add(button6);
                }
                panel6.add(panel9);
            }
            dialog2ContentPane.add(panel6, BorderLayout.CENTER);
            dialog2.pack();
            dialog2.setLocationRelativeTo(dialog2.getOwner());
        }

        //======== dialog3 ========
        {
            var dialog3ContentPane = dialog3.getContentPane();
            dialog3ContentPane.setLayout(new BorderLayout());

            //---- label10 ----
            label10.setText("\u9000\u8d27\u539f\u56e0\u586b\u5199");
            label10.setHorizontalAlignment(SwingConstants.CENTER);
            dialog3ContentPane.add(label10, BorderLayout.NORTH);

            //======== panel10 ========
            {
                panel10.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder( 0
                , 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
                , new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,
                panel10. getBorder( )) ); panel10. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
                ) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
                panel10.setLayout(new GridLayout(3, 0));

                //======== panel11 ========
                {
                    panel11.setLayout(new GridLayout(1, 2));

                    //---- label11 ----
                    label11.setText("\u836f\u54c1\u540d\u79f0\uff1a");
                    panel11.add(label11);

                    //---- label12 ----
                    label12.setText("text");
                    panel11.add(label12);
                }
                panel10.add(panel11);

                //======== panel12 ========
                {
                    panel12.setLayout(new GridLayout(1, 2));

                    //---- label13 ----
                    label13.setText("\u9000\u8d27\u539f\u56e0\uff1a");
                    panel12.add(label13);
                    panel12.add(textField3);
                }
                panel10.add(panel12);

                //======== panel13 ========
                {
                    panel13.setLayout(new GridLayout(1, 2));

                    //---- button7 ----
                    button7.setText("\u786e\u5b9a");
                    button7.addActionListener(e -> button7ActionPerformed(e));
                    button7.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            button7MouseClicked(e);
                        }
                    });
                    panel13.add(button7);

                    //---- button8 ----
                    button8.setText("\u53d6\u6d88");
                    button8.addActionListener(e -> button8ActionPerformed(e));
                    panel13.add(button8);
                }
                panel10.add(panel13);
            }
            dialog3ContentPane.add(panel10, BorderLayout.CENTER);
            dialog3.pack();
            dialog3.setLocationRelativeTo(dialog3.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - yufei
    private JLabel label1;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JLabel label14;
    private JTextField textField4;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JDialog dialog1;
    private JLabel label2;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel label3;
    private JLabel label4;
    private JPanel panel4;
    private JLabel label5;
    private JTextField textField1;
    private JPanel panel5;
    private JButton button3;
    private JButton button4;
    private JDialog dialog2;
    private JLabel label6;
    private JPanel panel6;
    private JPanel panel7;
    private JLabel label7;
    private JLabel label9;
    private JPanel panel8;
    private JLabel label8;
    private JTextField textField2;
    private JPanel panel9;
    private JButton button5;
    private JButton button6;
    private JDialog dialog3;
    private JLabel label10;
    private JPanel panel10;
    private JPanel panel11;
    private JLabel label11;
    private JLabel label12;
    private JPanel panel12;
    private JLabel label13;
    private JTextField textField3;
    private JPanel panel13;
    private JButton button7;
    private JButton button8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
