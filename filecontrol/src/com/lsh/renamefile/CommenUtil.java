package com.lsh.renamefile;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.lang.StringUtils;

public class CommenUtil {
	
	static Font mf=new Font("黑体",Font.BOLD,18);

	static String message="本程序的功能有：\r\n1、批量重命名文件，批量重命名文件夹\r\n2、搜索文件，"
			+ "根据关键字搜索文本文档的内容是否有此关键字\r\n3、把一个目录下的所有文件都移动到"
			+ "上级目录中，把一个目录下的所有文件都移动到一个文件夹\r\n4、定时关机，定时打开一个文件"
			+ "，清理系统垃圾，强制删除文件。\r\n内容以后会不断更新的呦，如果有好的建议请发邮件"
			+ "2538808545@qq.com";
	/**
	 * 设置全局字体
	 * @param font
	 */
	public static void InitGlobalFont( ) {  
		 Font font=new Font("黑体",Font.BOLD,16);
		FontUIResource fontRes = new FontUIResource(font);  
		for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {  
			Object key = keys.nextElement();  
			Object value = UIManager.get(key);  
			if (value instanceof FontUIResource) 
				UIManager.put(key, fontRes);  
		}  
	}
	/**
	 * 重新选择文件（当点击菜单的时候）
	 * @param text
	 * @param jb
	 * @param t4
	 */
	public static void setButtonEnable(String text,JButton jb,JTextField t4){
		if (text.length()<=0) {
			jb.setEnabled(false);
			t4.setText("emmmmm......请先选择文件~~~~~~~~~~");
		}
	}
	/** 
	 * 转义正则特殊字符 （$()*+.[]?\^{},|） 
	 * @param keyword 
	 * @return 
	 */  
	public static String escapeExprSpecialWord(String keyword) {  
	    if (StringUtils.isNotBlank(keyword)) {  
	        String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };  
	        for (String key : fbsArr) {  
	            if (keyword.contains(key)) {  
	                keyword = keyword.replace(key, "\\" + key);  
	            }  
	        }  
	    }  
	    return keyword;  
	}  
}