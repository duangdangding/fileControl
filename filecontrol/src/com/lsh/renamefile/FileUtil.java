package com.lsh.renamefile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileUtil{
	private static Profile profile=new Profile();
	/**
	 * 判断是否含有特殊字符
	 *
	 * @param str
	 * @return true为包含，false为不包含
	 */
	public static boolean haSymbol(String str) {
		return Pattern.compile("[|:\\<>/?*\"]").matcher(str).find();
	}
	/**
	 * 当选择全部重命名
	 * @param files
	 * @param suffix
	 * @param id
	 * @param rootPath
	 */
	public static void updateFileNameNew(File[] files,String suffix,int id,String rootPath){
		for (int i =1; i <=files.length; i++) {
			if (files[i-1].isFile()) {
				String name = files[i-1].getName();
				int index=name.lastIndexOf(".");
				if (index>=0) {
					String fileStyle = name.substring(index, name.length());//文件后缀名
					files[i-1].renameTo(new File(rootPath+suffix+(id++)+fileStyle));
				}else 
					files[i-1].renameTo(new File(rootPath+suffix+(id++)));
			}
		}
	}
	/**
	 * 减去文件名字的一串字
	 * @param files
	 * @param suffix
	 * @param id
	 * @param rootPath
	 */
	public static void cutFileName(File[] files,String suffix,int id,String rootPath){
		for (int i = 0; i <files.length; i++) {
			if (files[i].isFile()) {
				String name = files[i].getName();
				int index=name.lastIndexOf(".");
				if (index>=0) {
					String cutNoStyleName="";
					String fileStyle = name.substring(index, name.length());//文件后缀名
					String noStyleName=name.substring(0,index);//不带文件类型的文件名
					String[] cutNoStyleNames = noStyleName.split(suffix);
					if (cutNoStyleNames.length>0) {
						for (int j = 0; j < cutNoStyleNames.length; j++) {
							cutNoStyleName+=cutNoStyleNames[j];
						}
						if (id==0) {
							files[i].renameTo(new File(rootPath+cutNoStyleName+fileStyle));
						}else 
							files[i].renameTo(new File(rootPath+cutNoStyleName+(id++)+fileStyle));
					}else 
						JOptionPane.showConfirmDialog(null, "emmm...你把文件名都给吃完了耶~~~~", "错误", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				}else {
					String[] cutNames=name.split(suffix);
					if (cutNames.length>0) {
						String cutName="";
						for (int j = 0; j < cutNames.length; j++) {
							cutName+=cutNames[j];
						}
						if (id==0) {
							files[i].renameTo(new File(rootPath+cutName));
						}else 
							files[i].renameTo(new File(rootPath+cutName+(id++)));
					}else 
						JOptionPane.showConfirmDialog(null, "emmm...你把文件名都给吃完了耶~~~~", "错误", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	/**
	 * 根据前缀重命名
	 * @param files
	 * @param suffix
	 * @param id
	 * @param rootPath
	 */
	public static void updateFileNamePrefix(File[] files,String suffix,int id,String rootPath){
		for (int i =1; i <=files.length; i++) {
			if (files[i-1].isFile()) {
				String name = files[i-1].getName();
				int index=name.lastIndexOf(".");
				if (index>=0) {
					String fileStyle = name.substring(index, name.length());//文件后缀名
					String noStyleName=name.substring(0,index);//不带文件类型的文件名
					if (id==0) 
						files[i-1].renameTo(new File(rootPath+suffix+noStyleName+fileStyle));
					else 
						files[i-1].renameTo(new File(rootPath+(id++)+suffix+noStyleName+fileStyle));
				}else {
					if (id==0) 
						files[i-1].renameTo(new File(rootPath+suffix+name));
					else 
						files[i-1].renameTo(new File(rootPath+(id++)+suffix+name));
				}
			}
		}
	}
	/**
	 * 根据后缀重命名
	 * @param files
	 * @param suffix
	 * @param id
	 * @param rootPath
	 */
	public static void updateFileNameSuffix(File[] files,String suffix,int id,String rootPath){
		for (int i =1; i <=files.length; i++) {
			if (files[i-1].isFile()) {
				String name = files[i-1].getName();
				int index=name.lastIndexOf(".");
				if (index>=0) {
					String fileStyle = name.substring(index, name.length());//文件后缀名
					String noStyleName=name.substring(0,index);//不带文件类型的文件名
					if (id==0) {
						files[i-1].renameTo(new File(rootPath+noStyleName+suffix+fileStyle));
					}else 
						files[i-1].renameTo(new File(rootPath+noStyleName+suffix+(id++)+fileStyle));
				}else {
					if (id==0) {
						files[i-1].renameTo(new File(rootPath+name+suffix));
					}else 
						files[i-1].renameTo(new File(rootPath+name+suffix+(id++)));
				}
			}
		}
	}
	/**
	 * 根据给出的关键字查询文本有没有这个内容
	 * @param path
	 * @param suffix
	 * @param rootPath
	 * @param ta
	 * @throws IOException
	 */
	public static void fromFileToWord(String path,String suffix,JTextArea ta){
		String len="";
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			while ((len=br.readLine())!=null) {
				if(len.contains(suffix)){
					char[] cs = len.toCharArray();
					for (char c : cs) {
						ta.append(String.valueOf(c));
						ta.paintImmediately(ta.getBounds());
					}
					ta.append("\r\n");//追加内容
					ta.paintImmediately(ta.getBounds());
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 递归遍历搜索到的文件
	 * @param dir
	 * @param word
	 */
	public static void fromRecursionGetFileSum(String dir,String word,JTextArea ta_log){
		File[] files = new File(dir+File.separator).listFiles();
		if (files!=null&&files.length>=0) {
			for (int i = 0; i < files.length; i++) {
				if(files[i].isDirectory())
					fromRecursionGetFileSum(files[i].getAbsolutePath(),word, ta_log);  //递归遍历每一个子文件
				if (files[i].isFile()) {
					if (files[i].getName().contains(word)) {
						ta_log.append(files[i].getAbsolutePath().toString());
						ta_log.append("\r\n");//追加内容
						ta_log.paintImmediately(ta_log.getBounds());
					}
				}
			}
		}
	}
	/**
	 * 选择文件
	 * @param jb
	 * @param fr
	 * @param t1
	 */
	public static void chooseFile(JButton jb,JFrame fr,JTextField t1,String latestPath,JTextField t4){
		JFileChooser jfc=new JFileChooser(latestPath);
		jfc.setFileSelectionMode(0);//只能选择文件
		int state = jfc.showOpenDialog(fr);
		if(state==JFileChooser.APPROVE_OPTION){
			File file = jfc.getSelectedFile();
			latestPath = file.getParent();//每次退出文件选择器后更新目录Properties
			if (latestPath==null) 
				latestPath=file.getAbsolutePath();
			profile.writeProfile(latestPath);//每次退出程序时把最后一次打开的目录写入到配置文件
			t1.setText(file.getAbsolutePath());//选择到的目录
			t4.setText("可以开始搜索~~~~~~~~~~~~~~~~~~");
			jb.setEnabled(true);
		}
	}
	/*
	 * 以下是操作文件夹的时候
	 */
	/**
	 * 选择文件  保存上一次退出保存的地址
	 * @param jb
	 * @param fr
	 * @param t1
	 * @param latestPath
	 */
	public static void chooseDir(JButton jb,JFrame fr,JTextField t1,String latestPath){
		JFileChooser jfc=new JFileChooser(latestPath);
		jfc.setFileSelectionMode(1);//设置只能选择文件夹
		int state = jfc.showOpenDialog(fr);
		if(state==JFileChooser.APPROVE_OPTION){
			File file = jfc.getSelectedFile();
			latestPath=file.getAbsolutePath();//每次退出文件选择器后更新目录Properties
			profile.writeProfile(latestPath);//每次退出程序时把最后一次打开的目录写入到配置文件
			t1.setText(latestPath);//选择到的目录
			jb.setEnabled(true);
		}
	}
	/**
	 * 选择目标文件的时候，判断是不是目录相同
	 * @param text
	 * @param fr
	 * @param t5
	 * @param latestPath
	 */
	public static String chooseTargetDir(JFrame fr,String latestPath){
		JFileChooser jfc=new JFileChooser(latestPath);
		jfc.setFileSelectionMode(1);//设置只能选择文件夹
		int state = jfc.showOpenDialog(fr);
		if(state==JFileChooser.APPROVE_OPTION){
			latestPath = jfc.getSelectedFile().getAbsolutePath();
			profile.writeProfile(latestPath);//每次退出程序时把最后一次打开的目录写入到配置文件
		}
		return latestPath;
	}
	/**
	 * 可以选择全部文件
	 * @param fr
	 * @param latestPath
	 * @return
	 */
	public static String chooseFiles(JButton jb,JFrame fr,String latestPath,JTextField t1){
		JFileChooser jfc=new JFileChooser(latestPath);
		jfc.setFileSelectionMode(2);//设置选择文件夹或者文件
		int state = jfc.showOpenDialog(fr);
		if(state==JFileChooser.APPROVE_OPTION){
			latestPath = jfc.getSelectedFile().getAbsolutePath();
			profile.writeProfile(latestPath);//每次退出程序时把最后一次打开的目录写入到配置文件
			t1.setText(latestPath);
			jb.setEnabled(true);
		}
		return latestPath;
	}
	/**
	 * 将本目录下的文件移动到上级目录
	 * @param path
	 */
	public static void moveToParent(String path){
		File[] files = new File(path+File.separator).listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) 
				moveToParent(files[i].getAbsolutePath());
			if (files[i].isFile()) {
				String parent=new File(files[i].getParent()).getParent();
				if (parent==null) 
					parent=files[i].getParent();
				files[i].renameTo(new File(parent+File.separator+files[i].getName()));
			}
		}
	}
	/**
	 * 清理系统垃圾
	 */
	public static void clearGarbage(){
		try {
			Runtime.getRuntime().exec("cmd /c start "+new File("img\\clear.bat").getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据设置的事件设置定时关机
	 * @param time
	 */
	public static void timedShutdown(int time){
		try {
			Runtime.getRuntime().exec("shutdown /s /t "+time);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 取消自动关机
	 */
	public static void cancelShutdown(){
		try {
			Runtime.getRuntime().exec("shutdown -a");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 计划多少时间之后打开文件
	 * 先创建一个临时的bat文件
	 * @param path
	 * @param time
	 */
	public static void planOpenFile(String path,int time){
		File file=new File("src\\img\\open.bat");
		try {
			if (!file.exists()) 
				file.createNewFile();
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"gbk"));
			bw.write("@echo off \r\n");
			bw.write("choice /t "+time+" /d y /n >nul \r\n");
			bw.write("start "+path+" \r\n");
			bw.write("exit");
			bw.close();
			Runtime.getRuntime().exec("cmd /c start /min "+file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 关闭cmd窗口
	 */
	public static void closeCmd(){
		try {//关闭cmd窗口
			Runtime.getRuntime().exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
			Runtime.getRuntime().exec("taskkill /f /fi 'windowtitle eq open.bat'");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 强制删除某个文件
	 * @param path
	 */
	public static void forceDelFile(String path){
		try {
			String commed="";
			if (new File(path).isDirectory()) {
				commed="cmd /c rd "+path+" /s/q";//强制删除文件夹
			}else 
				commed="del /F /S /Q "+path;//强制删除文件
			new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(commed).getInputStream(),"gbk"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 强制删除该文件夹下面的所有文件
	 * @param path
	 */
	public static void forceDelFiles(String path){
		for (File file : new File(path).listFiles()) {
			forceDelFile(file.getAbsolutePath());
		}
	}
	/**
	 * 把这个文件夹下面的所有文件都移动到指定文件
	 * @param souDir
	 * @param tarDir
	 */
	public static void moveToTarDir(String souDir,String tarDir){
		File[] files = new File(souDir+File.separator).listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) 
				moveToTarDir(files[i].getAbsolutePath(), tarDir);
			if (files[i].isFile()) {
				files[i].renameTo(new File(tarDir+File.separator+files[i].getName()));
			}
		}
	}
	/**
	 * 当选择全部重命名
	 * @param files
	 * @param suffix
	 * @param id
	 * @param rootPath
	 */
	public static void updateDirNameNew(File[] files,String suffix,int id,String rootPath){
		for (int i =0; i <files.length; i++) {
			if (files[i].isDirectory()) {
				files[i].renameTo(new File(rootPath+suffix+(id++)));
			}
		}
	}
	/**
	 * 减去文件名字的一串字
	 * @param files
	 * @param suffix
	 * @param id
	 * @param rootPath
	 */
	public static void cutDirName(File[] files,String suffix,int id,String rootPath){
		for (int i = 0; i <files.length; i++) {
			if (files[i].isDirectory()) {
				String name = files[i].getName();
				String[] cutNames=name.split(suffix);
				if (cutNames.length>0) {
					String cutName="";
					for (int j = 0; j < cutNames.length; j++) {
						cutName+=cutNames[j];
					}
					if (id==0) {
						files[i].renameTo(new File(rootPath+cutName));
					}else 
						files[i].renameTo(new File(rootPath+cutName+(id++)));
				}else 
					JOptionPane.showConfirmDialog(null, "emmm...你把文件名都给吃完了耶~~~~", "错误", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	/**
	 * 根据前缀重命名
	 * @param files
	 * @param suffix
	 * @param id
	 * @param rootPath
	 */
	public static void updateDirNamePrefix(File[] files,String suffix,int id,String rootPath){
		for (int i =1; i <=files.length; i++) {
			if (files[i-1].isDirectory()) {
				String name = files[i-1].getName();
				if (id==0) 
					files[i-1].renameTo(new File(rootPath+suffix+name));
				else 
					files[i-1].renameTo(new File(rootPath+(id++)+suffix+name));
			}
		}
	}
	/**
	 * 根据后缀重命名
	 * @param files
	 * @param suffix
	 * @param id
	 * @param rootPath
	 */
	public static void updateDirNameSuffix(File[] files,String suffix,int id,String rootPath){
		for (int i =1; i <=files.length; i++) {
			if (files[i-1].isDirectory()) {
				String name = files[i-1].getName();
				if (id==0) {
					files[i-1].renameTo(new File(rootPath+name+suffix));
				}else 
					files[i-1].renameTo(new File(rootPath+name+suffix+(id++)));
			}
		}
	}
}
