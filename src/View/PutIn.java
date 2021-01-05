/*
 * Created by JFormDesigner on Sun Jan 03 13:55:51 CST 2021
 */

package View;

import Model.DBManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author yufei
 */
public class PutIn extends JFrame {
    public PutIn() {
        initComponents();
    }


    private void button1ActionPerformed(ActionEvent e) {
        initTable(table1);
    }

    Object[][] arr;
    public String selectName = "";
    private void initTable(JTable table1){

        String[] list = {"药品名称", "数量","入库时间","到期时间"};
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
        String sql = "select * from checks";
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
                    String InNum = rs.getString("InNum");
                    arr[j][0] = medName;
                    arr[j][1] = InNum;
                    j++;
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public int r;
    public int l;
    private void table1MouseClicked(MouseEvent e) {
        r= table1.rowAtPoint(e.getPoint());
        int c = 0;
        //得到选中的单元格的值，表格中都是字符串
        Object value= table1.getValueAt(r, c);
       /*
        javax.swing.JOptionPane.showMessageDialog(null,info);*/
        selectName = value.toString();
        label4.setText(selectName);
        //dialog1.setVisible(true);
        //l=table1.columnAtPoint(e.getPoint());
        //String info=r+"行"+l+"列值 : "+value.toString();
        //System.out.println(info);
        //int num=Integer.parseInt(table1.getValueAt(r,1).toString());
        dialog1.setVisible(true);
        //table1.setValueAt(bao,r,2);
        /*try{//填入药品信息表
            DBManager.getINSTANCE().executeUpdate("insert into drug medname,intime,outtime,num values('"+selectName+"','"+intime+"','"+outtime+"',"+num+");");
        }catch (Exception q){
            q.printStackTrace();
        }*/
    }

    private void button2ActionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void button4ActionPerformed(ActionEvent e) {
        dialog1.dispose();
    }


    public String intime;
    public String outime;
    public int bao;
    private void button3ActionPerformed(ActionEvent e) {//入库日期和出库日期
        bao=Integer.parseInt(textField1.getText().toString());
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        intime=time.format(date);
        //Calendar cal=Calendar.getInstance();
        //cal.setTime(date);
        //intime=cal.getTime().toString();
        //cal.add(cal.YEAR,+bao);
        outime=time.format(new Date(date.getTime()+ (long) bao *30*24*60*60*1000));
        table1.setValueAt(intime,r,2);
        table1.setValueAt(outime,r,3);
        int num=Integer.parseInt(table1.getValueAt(r,1).toString());
        String Medid=getMedID();
        System.out.println("insert into drug (medname,intime,outtime,num) values('"+selectName+"','"+intime+"','"+outime+"',"+num+")");
        try{//填入药品信息表
            DBManager.getINSTANCE().executeUpdate("insert into drug (medid,medname,intime,outtime,num) values('"+Medid+"','"+selectName+"','"+intime+"','"+outime+"',"+num+")");
        }catch (Exception q){
            q.printStackTrace();
        }
        dialog1.dispose();
    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
    }


    public static String getMedID(){
        String str="0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<9;i++){
            int number=random.nextInt(9);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void button1MouseClicked(MouseEvent e) {
        int k=0,c=0;
        table1.setValueAt("abc",k,c);
        c++;
        table1.setValueAt(50,k,c);
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - yufei
        label1 = new JLabel();
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
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

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //---- label1 ----
        label1.setText("\u836f\u54c1\u4fe1\u606f\u5f55\u5165");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1, BorderLayout.NORTH);

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
            EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing
            . border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ),
            java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () ))
            throw new RuntimeException( ); }} );
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
            var dialog1ContentPane = dialog1.getContentPane();
            dialog1ContentPane.setLayout(new BorderLayout());

            //---- label2 ----
            label2.setText("\u836f\u54c1\u4fdd\u8d28\u671f\u586b\u5199");
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            dialog1ContentPane.add(label2, BorderLayout.NORTH);

            //======== panel2 ========
            {
                panel2.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
                0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
                . BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
                red) ,panel2. getBorder( )) ); panel2. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
                beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
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
                    label5.setText("\u4fdd\u8d28\u671f\uff1a");
                    panel4.add(label5);
                    panel4.add(textField1);
                }
                panel2.add(panel4);

                //======== panel5 ========
                {
                    panel5.setLayout(new GridLayout(1, 2));

                    //---- button3 ----
                    button3.setText("\u786e\u5b9a");
                    button3.addActionListener(e -> button3ActionPerformed(e));
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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void dialog1WindowOpened(WindowEvent e) {
        selectName=table1.getValueAt(r,0).toString();
        label4.setText(selectName);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - yufei
    private JLabel label1;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
