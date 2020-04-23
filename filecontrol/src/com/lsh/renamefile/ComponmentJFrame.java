package com.lsh.renamefile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ComponmentJFrame {
	static String  latestPath ;
	static Profile profile;
	static String imgPath="img\\bg.jpg";
	ImageIcon ico=new ImageIcon("img\\curious-minion-icon.png");
	//父组件
	JFrame fr=new JFrame("文件操作小功能~~~~~~~~~~~~~~~~~~~~");
	JMenuBar jmb=new JMenuBar();
	JMenu jm1=new JMenu("重命名");
	JMenu jm2=new JMenu("搜索");
	JMenu jm3=new JMenu("移动");
	JMenu jm4=new JMenu("小工具");
	JMenuItem jmi1=new JMenuItem("批量重命名文件");
	JMenuItem jmi2=new JMenuItem("批量重命名文件夹");
	JMenuItem jmi3=new JMenuItem("搜索文件");
	JMenuItem jmi4=new JMenuItem("搜索文档内容");
	JMenuItem jmi5=new JMenuItem("移动文件");
	JMenuItem jmi6=new JMenuItem("清理系统垃圾");
	JMenuItem jmi7=new JMenuItem("定时关机");
	JMenuItem jmi9=new JMenuItem("定时打开文件");
	JMenuItem jmi8=new JMenuItem("强制删除文件");
	JTextArea updateExplain=new JTextArea(10,10);
	JLabel title=new JLabel();
	//选项按钮
	JRadioButton jrb1=new JRadioButton("只加前缀");
	JRadioButton jrb2=new JRadioButton("只加后缀");
	JRadioButton jrb3=new JRadioButton("全改(1,2,3...)",true);
	JRadioButton jrb4=new JRadioButton("加",true);
	JRadioButton jrb5=new JRadioButton("减去连续的一串字");
	JRadioButton jrb6=new JRadioButton("把文件夹下面的文件全部移动到目标文件");
	JRadioButton jrb7=new JRadioButton("把文件夹下面的文件**全部删除**");
	ButtonGroup bGroup=new ButtonGroup();
//字体
	
	JLabel wel=new JLabel("欢迎使用~~~", JLabel.CENTER);
	JTextArea introduce=new JTextArea();
	//首页
	JPanel jp1=new JPanel(){
		public void paintComponent(Graphics g) {
			ImageIcon icon=new ImageIcon(imgPath);
			g.drawImage(icon.getImage(), 0, 0,fr.getSize().width,fr.getSize().height, fr);// 图片随窗体大小而变化
		}
	};   
//第二个界面
	JPanel jp2=new JPanel(){
		public void paintComponent(Graphics g) {
			ImageIcon icon=new ImageIcon(imgPath);
			g.drawImage(icon.getImage(), 0, 0,fr.getSize().width,fr.getSize().height, fr);// 图片随窗体大小而变化
		}
	};
	//重命名文件
	JButton b1=new JButton("选择文件夹");
	JButton b2=new JButton("重命名文件");
	//重命名文件夹
	JButton b5=new JButton("选择文件夹");
	JButton b7=new JButton("重命名文件夹");
	//搜索文件
	JButton b8=new JButton("选择文件");
	JButton b4=new JButton("开始搜索");
	//搜索文本内容
	JButton b10=new JButton("选择文件夹");
	JButton b9=new JButton("开始搜索");
	//移动文件
	JButton b11=new JButton("选择文件夹");
	JButton b12=new JButton("移动文件");
	JButton b13=new JButton("目标文件");
	JTextField t2=new JTextField();//目标文件路径
	//定时关机
	JButton b14=new JButton("设置时间");
	JButton b15=new JButton("取消关机");
	//强制删除文件
	JButton b16=new JButton("选择文件");
	JButton b17=new JButton("开始删除");
	//定时打开文件
	JButton b18=new JButton("开始任务");
	JButton b19=new JButton("取消任务");
	JButton b20=new JButton("选择文件");
	
	JButton b3=new JButton("关闭该软件");
	JLabel j1=new JLabel("缀(可加可不加)：");
	JLabel ta=new JLabel();
	JLabel sequenceJL=new JLabel("加序号如：1(缀)X.x,(缀)X2.x");
	JTextField t1=new JTextField();
	JTextField t3=new JTextField();
	JTextField t4=new JTextField();

	final String con="<html>这是一个批量重命名文件的工具，版本是1.0。 <br> 希望大家多提下意见呦，QQ:2538808545</html>";

	public static String getLatestPath() {
		return latestPath;
	}

	public static void setLatestPath(String latestPath) {
		ComponmentJFrame.latestPath = latestPath;
	}

	public static Profile getProfile() {
		return profile;
	}

	public static void setProfile(Profile profile) {
		ComponmentJFrame.profile = profile;
	}

	public static String getImgPath() {
		return imgPath;
	}

	public static void setImgPath(String imgPath) {
		ComponmentJFrame.imgPath = imgPath;
	}

	public ImageIcon getIco() {
		return ico;
	}

	public void setIco(ImageIcon ico) {
		this.ico = ico;
	}

	public JFrame getFr() {
		return fr;
	}

	public void setFr(JFrame fr) {
		this.fr = fr;
	}

	public JMenuBar getJmb() {
		return jmb;
	}

	public void setJmb(JMenuBar jmb) {
		this.jmb = jmb;
	}

	public JMenu getJm1() {
		return jm1;
	}

	public void setJm1(JMenu jm1) {
		this.jm1 = jm1;
	}

	public JMenu getJm2() {
		return jm2;
	}

	public void setJm2(JMenu jm2) {
		this.jm2 = jm2;
	}

	public JMenu getJm3() {
		return jm3;
	}

	public void setJm3(JMenu jm3) {
		this.jm3 = jm3;
	}

	public JMenu getJm4() {
		return jm4;
	}

	public void setJm4(JMenu jm4) {
		this.jm4 = jm4;
	}

	public JMenuItem getJmi1() {
		return jmi1;
	}

	public void setJmi1(JMenuItem jmi1) {
		this.jmi1 = jmi1;
	}

	public JMenuItem getJmi2() {
		return jmi2;
	}

	public void setJmi2(JMenuItem jmi2) {
		this.jmi2 = jmi2;
	}

	public JMenuItem getJmi3() {
		return jmi3;
	}

	public void setJmi3(JMenuItem jmi3) {
		this.jmi3 = jmi3;
	}

	public JMenuItem getJmi4() {
		return jmi4;
	}

	public void setJmi4(JMenuItem jmi4) {
		this.jmi4 = jmi4;
	}

	public JMenuItem getJmi5() {
		return jmi5;
	}

	public void setJmi5(JMenuItem jmi5) {
		this.jmi5 = jmi5;
	}

	public JMenuItem getJmi6() {
		return jmi6;
	}

	public void setJmi6(JMenuItem jmi6) {
		this.jmi6 = jmi6;
	}

	public JMenuItem getJmi7() {
		return jmi7;
	}

	public void setJmi7(JMenuItem jmi7) {
		this.jmi7 = jmi7;
	}

	public JMenuItem getJmi9() {
		return jmi9;
	}

	public void setJmi9(JMenuItem jmi9) {
		this.jmi9 = jmi9;
	}

	public JMenuItem getJmi8() {
		return jmi8;
	}

	public void setJmi8(JMenuItem jmi8) {
		this.jmi8 = jmi8;
	}

	public JTextArea getUpdateExplain() {
		return updateExplain;
	}

	public void setUpdateExplain(JTextArea updateExplain) {
		this.updateExplain = updateExplain;
	}

	public JLabel getTitle() {
		return title;
	}

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JRadioButton getJrb1() {
		return jrb1;
	}

	public void setJrb1(JRadioButton jrb1) {
		this.jrb1 = jrb1;
	}

	public JRadioButton getJrb2() {
		return jrb2;
	}

	public void setJrb2(JRadioButton jrb2) {
		this.jrb2 = jrb2;
	}

	public JRadioButton getJrb3() {
		return jrb3;
	}

	public void setJrb3(JRadioButton jrb3) {
		this.jrb3 = jrb3;
	}

	public JRadioButton getJrb4() {
		return jrb4;
	}

	public void setJrb4(JRadioButton jrb4) {
		this.jrb4 = jrb4;
	}

	public JRadioButton getJrb5() {
		return jrb5;
	}

	public void setJrb5(JRadioButton jrb5) {
		this.jrb5 = jrb5;
	}

	public JRadioButton getJrb6() {
		return jrb6;
	}

	public void setJrb6(JRadioButton jrb6) {
		this.jrb6 = jrb6;
	}

	public JRadioButton getJrb7() {
		return jrb7;
	}

	public void setJrb7(JRadioButton jrb7) {
		this.jrb7 = jrb7;
	}

	public ButtonGroup getbGroup() {
		return bGroup;
	}

	public void setbGroup(ButtonGroup bGroup) {
		this.bGroup = bGroup;
	}

	public JLabel getWel() {
		return wel;
	}

	public void setWel(JLabel wel) {
		this.wel = wel;
	}

	public JTextArea getIntroduce() {
		return introduce;
	}

	public void setIntroduce(JTextArea introduce) {
		this.introduce = introduce;
	}

	public JPanel getJp1() {
		return jp1;
	}

	public void setJp1(JPanel jp1) {
		this.jp1 = jp1;
	}

	public JPanel getJp2() {
		return jp2;
	}

	public void setJp2(JPanel jp2) {
		this.jp2 = jp2;
	}

	public JButton getB1() {
		return b1;
	}

	public void setB1(JButton b1) {
		this.b1 = b1;
	}

	public JButton getB2() {
		return b2;
	}

	public void setB2(JButton b2) {
		this.b2 = b2;
	}

	public JButton getB5() {
		return b5;
	}

	public void setB5(JButton b5) {
		this.b5 = b5;
	}

	public JButton getB7() {
		return b7;
	}

	public void setB7(JButton b7) {
		this.b7 = b7;
	}

	public JButton getB8() {
		return b8;
	}

	public void setB8(JButton b8) {
		this.b8 = b8;
	}

	public JButton getB4() {
		return b4;
	}

	public void setB4(JButton b4) {
		this.b4 = b4;
	}

	public JButton getB10() {
		return b10;
	}

	public void setB10(JButton b10) {
		this.b10 = b10;
	}

	public JButton getB9() {
		return b9;
	}

	public void setB9(JButton b9) {
		this.b9 = b9;
	}

	public JButton getB11() {
		return b11;
	}

	public void setB11(JButton b11) {
		this.b11 = b11;
	}

	public JButton getB12() {
		return b12;
	}

	public void setB12(JButton b12) {
		this.b12 = b12;
	}

	public JButton getB13() {
		return b13;
	}

	public void setB13(JButton b13) {
		this.b13 = b13;
	}

	public JTextField getT2() {
		return t2;
	}

	public void setT2(JTextField t2) {
		this.t2 = t2;
	}

	public JButton getB14() {
		return b14;
	}

	public void setB14(JButton b14) {
		this.b14 = b14;
	}

	public JButton getB15() {
		return b15;
	}

	public void setB15(JButton b15) {
		this.b15 = b15;
	}

	public JButton getB16() {
		return b16;
	}

	public void setB16(JButton b16) {
		this.b16 = b16;
	}

	public JButton getB17() {
		return b17;
	}

	public void setB17(JButton b17) {
		this.b17 = b17;
	}

	public JButton getB18() {
		return b18;
	}

	public void setB18(JButton b18) {
		this.b18 = b18;
	}

	public JButton getB19() {
		return b19;
	}

	public void setB19(JButton b19) {
		this.b19 = b19;
	}

	public JButton getB20() {
		return b20;
	}

	public void setB20(JButton b20) {
		this.b20 = b20;
	}

	public JButton getB3() {
		return b3;
	}

	public void setB3(JButton b3) {
		this.b3 = b3;
	}

	public JLabel getJ1() {
		return j1;
	}

	public void setJ1(JLabel j1) {
		this.j1 = j1;
	}

	public JLabel getTa() {
		return ta;
	}

	public void setTa(JLabel ta) {
		this.ta = ta;
	}

	public JLabel getSequenceJL() {
		return sequenceJL;
	}

	public void setSequenceJL(JLabel sequenceJL) {
		this.sequenceJL = sequenceJL;
	}

	public JTextField getT1() {
		return t1;
	}

	public void setT1(JTextField t1) {
		t1.setBorder(BorderFactory.createLineBorder(Color.red,3));//设置边框
		this.t1 = t1;
	}

	public JTextField getT3() {
		return t3;
	}

	public void setT3(JTextField t3) {
		this.t3 = t3;
	}

	public JTextField getT4() {
		return t4;
	}

	public void setT4(JTextField t4) {
		this.t4 = t4;
	}

	public String getCon() {
		return con;
	}
	
}
