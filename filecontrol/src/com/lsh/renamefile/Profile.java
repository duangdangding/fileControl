package com.lsh.renamefile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * 创建一个内部类保存上次退出的文件地址
 * @author Administrator
 *创建一个配置文件
 */
public class Profile{
	String latestPath = "C:/";
	File file = new File("/set.ini"); 
	public Profile(){}
	/**
	 * 创建配置文件
	 * @return
	 */
	boolean createProfile(){
		boolean b=true;
		if(file!=null){
			File dir=file.getParentFile();//获得文件的父目录
			if (!dir.exists()) {//目录不存在就创建
				b=dir.mkdirs();
			}else {
				if (!file.exists()) {//配置文件不存在就创建
					try {
						b=file.createNewFile();
					} catch (IOException e) {
						b=false;
					}
				}
			}
		}
		return b;
	} 
	/**
	 * 读取配置文件
	 * @return
	 */
	boolean readProfile(){
		boolean b=true;
		Properties pro;
		FileInputStream fis=null;
		if (!file.exists()) {
			b=createProfile();
			if (b) {//创建成功后
				b=writeProfile(latestPath);//初始化
			}else 
				JOptionPane.showConfirmDialog(null, "对不起，不存在配置文件！", "错误", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		}else {
			try {
				fis=new FileInputStream(file);
				pro=new Properties();
				try {
					pro.load(fis);//读取属性
					latestPath=pro.getProperty("latestPath");
					fis.close();
				} catch (IOException e) {
					b=false;
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				b=false;
				e.printStackTrace();
			}
		}
		return b;
	}
	/**
	 * 往配置文件里面存地址
	 * @param path
	 * @return
	 */
	boolean writeProfile(String path){
		boolean b=true;
		Properties pro=null;
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(file);
			pro=new Properties();
			pro.setProperty("latestPath", path);
			try {
				pro.store(fos, null);//写入到配置文件
				fos.close();
			} catch (IOException e) {
				b=false;
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			b=false;
			e.printStackTrace();
		}
		return b;
	}
}
