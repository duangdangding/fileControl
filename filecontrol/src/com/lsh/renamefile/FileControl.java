package com.lsh.renamefile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.lang.StringUtils;

/**
 * @version 1.3
 * @author LuShaoHua
 *@ 本次更新  增加重命名的功能（把文件名字根据填写的一串字，减去这串字）
 *2017/10、24  23:42修改重命名的时候有特殊符号，不能修改的问题s
 *2017/10/30		15：38增加了搜索文本文档的功能
 */
public class FileControl implements ActionListener{
	private static String  latestPath ;
	private static Profile profile;
//	private static String imgPath="src\\img\\bg.jpg";
	private static String imgPath="img\\bg.jpg";
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
	static Font f=new Font("宋体",Font.BOLD,14);
	static Font mf=new Font("宋体",Font.BOLD,20);

	String message="本程序的功能有：\r\n1、批量重命名文件，批量重命名文件夹\r\n2、搜索文件，"
			+ "根据关键字搜索文本文档的内容是否有此关键字\r\n3、把一个目录下的所有文件都移动到"
			+ "上级目录中，把一个目录下的所有文件都移动到一个文件夹\r\n4、定时关机，定时打开一个文件"
			+ "，清理系统垃圾，强制删除文件。\r\n内容以后会不断更新的呦，如果有好的建议请发邮件"
			+ "2538808545@qq.com";
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
	
	Box vertical = Box.createVerticalBox();
	Box horizontal = Box.createHorizontalBox();

	final String con="<html>这是一个批量重命名文件的工具，版本是1.0。 <br> 希望大家多提下意见呦，QQ:2538808545</html>";
	
	public FileControl(){
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
		b11.addActionListener(this);
		b12.addActionListener(this);
		b13.addActionListener(this);
		b14.addActionListener(this);
		b15.addActionListener(this);
		b16.addActionListener(this);
		b17.addActionListener(this);
		b18.addActionListener(this);
		b19.addActionListener(this);
		b20.addActionListener(this);

		jrb1.addActionListener(this);
		jrb2.addActionListener(this);
		jrb3.addActionListener(this);
		jrb4.addActionListener(this);
		jrb5.addActionListener(this);
		jrb6.addActionListener(this);
		jrb7.addActionListener(this);
		
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this);
		jmi4.addActionListener(this);
		jmi5.addActionListener(this);
		jmi6.addActionListener(this);
		jmi7.addActionListener(this);
		jmi8.addActionListener(this);
		jmi9.addActionListener(this);
		
		t1.setBorder(BorderFactory.createLineBorder(Color.red,3));//设置边框
		t3.setBorder(BorderFactory.createLineBorder(Color.CYAN,3));
		t1.setEditable(false);
		t4.setEditable(false);
		t2.setEditable(false);
		ta.setText(con);
		ta.setForeground(Color.red);
		
		wel.setFont(new Font("", Font.BOLD, 40));
		wel.setForeground(Color.red);
		j1.setOpaque(true);
		sequenceJL.setOpaque(true);
		sequenceJL.setFont(new Font("", Font.BOLD, 16));
		
		introduce.setLineWrap(true);
		introduce.setEditable(false);
		introduce.setPreferredSize(new Dimension(320,500));//设置边框大小
		introduce.setText(message);
		introduce.setForeground(Color.red);

		bGroup.add(jrb1);
		bGroup.add(jrb2);
		bGroup.add(jrb3);
		bGroup.add(jrb5);

		t1.setPreferredSize(new Dimension(320,27));//设置边框大小
		t4.setPreferredSize(new Dimension(320,27));//设置边框大小
		t2.setPreferredSize(new Dimension(320,27));//设置边框大小
		t3.setPreferredSize(new Dimension(150,27));//设置边框大小

		b1.setMargin(new Insets(4, 4, 4, 4));//设置文字和边框之间的距离
		b2.setMargin(new Insets(4, 4, 4, 4));//设置文字和边框之间的距离
		b3.setMargin(new Insets(4, 4, 4, 4));//设置文字和边框之间的距离
		b2.setEnabled(false);
		b4.setEnabled(false);
		b7.setEnabled(false);

		String type5="com.jtattoo.plaf.bernstein.BernsteinLookAndFeel";//黄色的花
		try {
			UIManager.setLookAndFeel(type5);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		jm1.setFont(mf);
		jm2.setFont(mf);
		jm3.setFont(mf);
		jmi1.setFont(mf);
		jmi2.setFont(mf);
		jmi3.setFont(mf);
		jmi4.setFont(mf);
		jmi5.setFont(mf); 
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm2.add(jmi3);
		jm2.add(jmi4);
		jm3.add(jmi5);
		jm4.add(jmi6);
		jm4.add(jmi7);
		jm4.add(jmi8);
		jm4.add(jmi9);
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
		
		horizontal.add(new JLabel("时间："));
		horizontal.add(t3);
		horizontal.add(new JLabel("S后关机"));
		vertical.add(new JLabel("不设置时间是立即关机呦~~~~"));
		vertical.add(b14);
		vertical.add(b15);
		vertical.add(b3);
		
		introduce.setOpaque(false);
		jp1.add(wel,BorderLayout.CENTER);
		jp1.add(introduce,BorderLayout.SOUTH);
		fr.add(jp1);
		fr.setJMenuBar(jmb);
		fr.setSize(330, 600);
		fr.setResizable(false);//不能最大化还有拉伸
		fr.setLocationRelativeTo(null);//让窗口居中显示
		fr.setIconImage(ico.getImage());
		fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
		fr.setVisible(true);
	}
	/*
	 * 事件监听
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String text=t1.getText().trim();
		String path=t2.getText().trim();
		String suffix=t3.getText();
		suffix=CommenUtil.escapeExprSpecialWord(suffix);
		/**
		 * 点击菜单栏事件
		 */
		if (source==jmi1) {//选择重命名文件的时候移除其他的组件，重新添加
			//_start
			fr.remove(jp1);
			jp2.removeAll();
			title.setText("重命名文件~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			jp2.add(title);
			jp2.add(b1);
			text="";
			t1.setText(text);
			jp2.add(t1);
			CommenUtil.setButtonEnable(text, b2, t4);
			jp2.add(t4);
			j1.setText("缀(可加可不加)：");
			jp2.add(j1);
			jp2.add(t3);
			jp2.add(jrb1);
			jp2.add(jrb2);
			jp2.add(jrb3);
			jp2.add(jrb5);
			jp2.add(sequenceJL);
			jp2.add(jrb4);
			jp2.add(b2);
			jp2.add(ta);
			jp2.add(b3);
			fr.add(jp2);
			fr.setVisible(true);
		}else if (source==jmi2) {//选择重命名文件夹
			fr.remove(jp1);
			jp2.removeAll();
			title.setText("重命名文件夹~~~~~~~~~~~~~~~~~~~~~~~~~~");
			jp2.add(title);
			jp2.add(b10);
			text="";
			t1.setText(text);
			jp2.add(t1);
			CommenUtil.setButtonEnable(text, b2, t4);
			jp2.add(t4);
			jp2.add(j1);
			jp2.add(t3);
			jp2.add(jrb1);
			jp2.add(jrb2);
			jp2.add(jrb3);
			jp2.add(jrb5);
			jp2.add(sequenceJL);
			jp2.add(jrb4);
			jp2.add(b7);
			jp2.add(ta);
			jp2.add(b3);
			fr.add(jp2);
			fr.setVisible(true);
		}else if (source==jmi3) {//选择搜索文件
			fr.remove(jp1);
			jp2.removeAll();
			title.setText("搜索文件~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			jp2.add(title);
			jp2.add(b5);
			text="";
			t1.setText(text);
			jp2.add(t1);
			CommenUtil.setButtonEnable(text, b2, t4);
			jp2.add(t4);
			j1.setText("搜索的关键字：");
			jp2.add(j1);
			jp2.add(t3);
			jp2.add(b4);
			jp2.add(b3);
			fr.add(jp2);
			fr.setVisible(true);
		}else if (source==jmi4) {//选择搜索文档内容
			fr.remove(jp1);
			jp2.removeAll();
			title.setText("搜索文本内容~~~~~~~~~~~~~~~~~~~~~~~~");
			jp2.add(title);
			jp2.add(b8);
			text="";
			t1.setText(text);
			jp2.add(t1);
			CommenUtil.setButtonEnable(text, b2, t4);
			jp2.add(t4);
			j1.setText("请输入要搜索的内容：");
			jp2.add(j1);
			jp2.add(t3);
			jp2.add(b9);
			jp2.add(b3);
			fr.add(jp2);
			fr.setVisible(true);
		}else if (source==jmi5) {
			fr.remove(jp1);
			jp2.removeAll();
			title.setText("移动文件~~~~~~~~~~~~~~~~~~~~~~~~");
			text="";
			t1.setText(text);
			CommenUtil.setButtonEnable(text, b2, t4);
			jp2.add(title);
			jp2.add(b11);
			jp2.add(t1);
			jp2.add(t4);
			jp2.add(jrb6);
			jp2.add(b13);
			jp2.add(t2);
			jp2.add(b12);
			jp2.add(b3);
			fr.add(jp2);
			fr.setVisible(true);
		}else if (source==jmi6) {//清理系统垃圾
			FileUtil.clearGarbage();
		}else if (source==jmi7) {//定时关机
			fr.remove(jp1);
			jp2.removeAll();
			title.setText("定时关机~~~~~~~~~~~~~");
			jp2.add(title);
			jp2.add(horizontal,BorderLayout.CENTER);
			jp2.add(vertical);
			fr.add(jp2);
			fr.setVisible(true);
		}else if (source==jmi9) {//定时打开文件
			fr.remove(jp1);
			jp2.removeAll();
			title.setText("定时打开文件~~~~~~~~~~~~~");
			horizontal.add(new JLabel("时间："));
			horizontal.add(t3);
			horizontal.add(new JLabel("S后打开文件"));
			jp2.add(title);
			jp2.add(horizontal,new FlowLayout());
			jp2.add(new JLabel("不设置时间是立即打开文件呦~~~~"));
			jp2.add(b20);
			jp2.add(t1);
			jp2.add(b18);
			jp2.add(b19);
			jp2.add(b3);
			fr.add(jp2);
			fr.setVisible(true);
		}else if (source==jmi8) {//强制删除文件
			fr.remove(jp1);
			jp2.removeAll();
			title.setText("强制删除文件~~~~~~~~~~~~~~~~~~~~~~~~");
			jp2.add(title);
			jp2.add(b16);
			text="";
			t1.setText(text);
			jp2.add(t1);
			CommenUtil.setButtonEnable(text, b17, t4);
			jp2.add(t4);
			jp2.add(jrb7);
			jp2.add(b17);
			jp2.add(b3);
			fr.add(jp2);
			fr.setVisible(true);
		}
		profile = new Profile();//每次运行程序时创建配置文件Profile对象
		latestPath = (profile.readProfile()?profile.latestPath:"C:/");//读取配置文件里的参数Profile并赋值给latestPath
		/**
		 * 点击按钮事件
		 */
		String rootPath=text+File.separator;//文件所在目录
		if (jrb3.isSelected()) //选择 选项  全部 的时候
			jrb4.setEnabled(false);
		else 
			jrb4.setEnabled(true);
		if (jrb6.isSelected()) {
			b13.setEnabled(true);
			t4.setText("请选择目标文件~~~~~~~~~~~~~~");
		}else 
			b13.setEnabled(false);
		if (source==b1) {//重命名文件下面的选择文件
			FileUtil.chooseDir(b2, fr, t1,latestPath);
			t4.setText("可以开始重命名~~~~~~~~~~~~~~~");
		}else if (source==b10) {//重命名文件下面的选择文件
			FileUtil.chooseDir(b7, fr, t1, latestPath);
			t4.setText("可以开始重命名~~~~~~~~~~~~~~~");
		}else if (source==b5) {//搜索文件下面的选择文件
			FileUtil.chooseDir(b4, fr, t1, latestPath);
			t4.setText("可以开始搜索~~~~~~~~~~~~~~~~~~~");
		}else if (source==b8) {//搜索文本内容下面的选择文件
			FileUtil.chooseFile(b9, fr, t1,latestPath,t4);
		}else if (source==b11) {
			FileUtil.chooseDir(b12, fr, t1,latestPath);
			t4.setText("可以开始移动文件~~~~~~~~~");
		}else if (source==b13) {
			String targetDir = FileUtil.chooseTargetDir(fr, latestPath);
			if (targetDir.equals(text)||targetDir.contains(text)) {
				t2.setText("");
				JOptionPane.showMessageDialog(jp2, "emmm...目标文件夹不能和上面的文件夹相同，并且上面的文件夹不能包含目标文件夹~~~~");
			}else {
				t2.setText(targetDir);
				t4.setText("选择目标文件夹成功~~~~~~~~~");
			}
		}else if (source==b12) {
			if (jrb6.isSelected()) {
				FileUtil.moveToTarDir(text, path);
			}else 
				FileUtil.moveToParent(text);
			t4.setText("移动文件成功~~~~~~~~~");
		}else if (source==b2) {//重命名文件下面的开始重命名
			File[] files =new File(text).listFiles();
			//前缀后缀必须在加序列和缀选择一个
			t4.setText("正在修改~~~~~~~~~~~~~~~~~");
			if (FileUtil.haSymbol(suffix)==true) {
				JOptionPane.showMessageDialog(jp2, "emmm...文件名不能有| : \\ < > / ? * \"");
			}else {
				if (jrb1.isSelected()) {//加前缀
					if (suffix.length()>0&&jrb4.isSelected()) //两个同时都选择的时候
						FileUtil.updateFileNamePrefix(files, suffix, 1, rootPath);
					else if (suffix.length()>0) //当只有缀的时候
						FileUtil.updateFileNamePrefix(files,suffix,0,rootPath);
					else if (jrb4.isSelected()) //当选择序列的时候
						FileUtil.updateFileNamePrefix(files,suffix,1,rootPath);
					else 
						JOptionPane.showMessageDialog(jp2, "emmm...你要在序列和缀选择一个~~~~");
				}else if (jrb2.isSelected()) {//加后缀  方法解释同上
					if (suffix.length()>0&&jrb4.isSelected()) 
						FileUtil.updateFileNameSuffix(files, suffix, 1, rootPath);
					else if (suffix.length()>0) 
						FileUtil.updateFileNameSuffix(files,suffix,0,rootPath);
					else if (jrb4.isSelected()) 
						FileUtil.updateFileNameSuffix(files,suffix,1,rootPath);
					else 
						JOptionPane.showMessageDialog(jp2, "emmm...你要在序列和缀选择一个~~~~");
				}else if (jrb3.isSelected()) //全部重命名，序列自动选择
					FileUtil.updateFileNameNew(files,suffix,1,rootPath);
				else if (jrb5.isSelected()) {//减去文件名的一串字
					if (suffix.length()<=0) {
						JOptionPane.showMessageDialog(jp2, "emmm...请填写关键字~~~~");
					}else {
						if (jrb4.isSelected()) 
							FileUtil.cutFileName(files, suffix, 1, rootPath);
						else 
							FileUtil.cutFileName(files, suffix, 0, rootPath);
					}
				}
				t4.setText("修改完成~~~~~~~~~~~~");
			}
		}else if (source==b3) {
			fr.dispose();//释放资源
			System.exit(0);
		}else if (source==b4) {//搜索文件里面的开始搜索
			if (suffix.length()>0) {
				if (text.length()>0) {
					t4.setText("正在搜索~~~~~~~~~~~~~~");
					OutFrame.createJFrame(text, suffix, 0);
				}else {
					b4.setEnabled(false);
					t4.setText("请先选择文件~~~~~~~~~~~~~~");
				}
				t4.setText("搜索完成~~~~~~~~~~~~");
			}else {
				b4.setEnabled(false);
				JOptionPane.showMessageDialog(jp2, "emmm...请填写关键字~~~~");
			}
		}else if (source==b9) {//点击开始搜索文档内容
			if (suffix.length()>0) {
				if (text.length()>0) {
					t4.setText("正在搜索~~~~~~~~~~~~~~");
					OutFrame.createJFrame(text, suffix, 1);
				}else {
					b9.setEnabled(false);
					t4.setText("请先选择文件~~~~~~~~~~~~~~");
				}
				t4.setText("搜索完成~~~~~~~~~~~~");
			}else {
				b9.setEnabled(false);
				JOptionPane.showMessageDialog(jp2, "emmm...请填写关键字~~~~");
			}
		}else if (source==b7) {//当按钮的事件时重命名文件夹的时候
			File[] files =new File(text).listFiles();
			//前缀后缀必须在加序列和缀选择一个
			t4.setText("正在修改~~~~~~~~~~~~~~");
			if (FileUtil.haSymbol(suffix)==false) {
				if (jrb1.isSelected()) {//加前缀
					if (suffix.length()>0&&jrb4.isSelected()) 
						FileUtil.updateFileNamePrefix(files, suffix, 1, rootPath);
					else if (suffix.length()>0) 
						FileUtil.updateFileNamePrefix(files,suffix,0,rootPath);
					else if (jrb4.isSelected()) 
						FileUtil.updateFileNamePrefix(files,suffix,1,rootPath);
					else 
						JOptionPane.showMessageDialog(jp2, "emmm...你要在序列和缀选择一个~~~~");
				}else if (jrb2.isSelected()) {//加后缀
					if (suffix.length()>0&&jrb4.isSelected()) 
						FileUtil.updateFileNameSuffix(files, suffix, 1, rootPath);
					else if (suffix.length()>0) 
						FileUtil.updateFileNameSuffix(files,suffix,0,rootPath);
					else if (jrb4.isSelected()) 
						FileUtil.updateFileNameSuffix(files,suffix,1,rootPath);
					else 
						JOptionPane.showMessageDialog(jp2, "emmm...你要在序列和缀选择一个~~~~");
				}else if (jrb3.isSelected()) //全部重命名，序列自动选择
					FileUtil.updateFileNameNew(files,suffix,1,rootPath);
				else if (jrb5.isSelected()) {//减去文件名的一串字
					if (suffix.length()<=0) 
						JOptionPane.showMessageDialog(jp2, "emmm...请填写关键字~~~~");
					else {
						b2.setEnabled(true);
						if (jrb4.isSelected()) 
							FileUtil.cutFileName(files, suffix, 1, rootPath);
						else 
							FileUtil.cutFileName(files, suffix, 0, rootPath);
					}
				}
				t4.setText("修改完成~~~~~~~~~~~~");
			}else 
				JOptionPane.showMessageDialog(jp2, "emmm...文件名不能有| : \\ < > / ? * \"");
		}else if (source==b14) {
			boolean b = StringUtils.isNumeric(suffix);
			if (b==true) {
				int time=0;
				if (suffix.length()<=0) {
					time=0;
				}else 
					time=Integer.parseInt(suffix);
				FileUtil.timedShutdown(time);
			}else 
				JOptionPane.showMessageDialog(jp2, "emmm...时间必须是数字呦~~~~");
		}else if (source==b15) {
			FileUtil.cancelShutdown();
			JOptionPane.showMessageDialog(jp2, "emmm...自动关机已经取消了呦~~~~");
		}else if (source==b16) {
			FileUtil.chooseFiles(b17, fr, latestPath,t1);
			t4.setText("可以删除文件~~~~~~~~~~~~~~~~~");
		}else if (source==b17) {
			if (jrb7.isSelected()) {
				FileUtil.forceDelFiles(text);
			}else {
				FileUtil.forceDelFile(text);
			}
			t4.setText("删除完成~~~~~~~~~~~~~~~~");
		}else if (source==b18) {
			if (text.length()>0) {
				boolean b = StringUtils.isNumeric(suffix);
				if (b==true) {
					int time=0;
					if (suffix.length()<=0) {
						time=0;
					}else 
						time=Integer.parseInt(suffix);
					FileUtil.planOpenFile(text, time);
				}else 
					JOptionPane.showMessageDialog(jp2, "emmm...时间必须是数字呦~~~~");
			}else 
				JOptionPane.showMessageDialog(jp2, "emmm...要选择定时打开的文件呦~~~~");
		}else if (source==b19) {//取消任务
			FileUtil.closeCmd();
		}else if (source==b20) {//选择文件
			FileUtil.chooseFiles(b18, fr, latestPath, t1);
		}
	}
	//主方法
	public static void main(String[] args) {
		CommenUtil.InitGlobalFont();//要放在加载之前
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FileControl();
				
			}
		});
		
	}
}