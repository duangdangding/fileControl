package com.lsh.renamefile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OutFrame {
	/**
	 * 单例模式只创建一个窗口
	 */
	private static OutFrame instance=null;
	public static synchronized OutFrame getInstance(){
		if(instance==null)
			instance=new OutFrame();
		return instance;
	}
	private OutFrame(){}
	static JFrame out = new JFrame();
	static ImageIcon ico1=new ImageIcon("img\\snow_leopard_11.png");
	static ImageIcon ico2=new ImageIcon("img\\png-0395.png");
	static JButton jb=new JButton("关闭");
	static JTextArea ta_log = new JTextArea(10,10);
	static JPanel outPanel=new JPanel();
	static JScrollPane p_log=new JScrollPane(ta_log, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	public static void createJFrame(String text,String suffix,int id){
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.dispose();//失去资源
			}
		});
		ta_log.setEditable(false);
		ta_log.setWrapStyleWord(true);
		ta_log.setForeground(Color.red);
		ta_log.setLineWrap(true);
		ta_log.setText(null);
		outPanel.setLayout(new BorderLayout());
		outPanel.add(p_log,BorderLayout.CENTER);
		outPanel.add(jb,BorderLayout.SOUTH);
		out.add(outPanel);
		out.setBounds(200,30,500,600);
		out.setDefaultCloseOperation(out.DISPOSE_ON_CLOSE);//关闭当前窗体
		out.setVisible(true);
		if (id==0) {
			out.setTitle("搜索文件结果");
			out.setIconImage(ico2	.getImage());
			FileUtil.fromRecursionGetFileSum(text, suffix, ta_log);
		}
		if(id==1){
			out.setIconImage(ico1	.getImage());
			out.setTitle("搜索文本内容结果");
			FileUtil.fromFileToWord(text, suffix, ta_log);
		}
		if (ta_log.getText().trim().length()<=0) 
			ta_log.setText("没有搜索到内容~~~~~~~~~~~~~~");
	}
}