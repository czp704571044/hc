package cn.yfyue.comm;

import java.io.File;

public class RecursionDelFile {
	/**
	 * 删除目录中的.svn目录
	 * 
	 * @param f
	 */
	public static void delDotsvn(File f,String fileName) {
	    File[] files = f.listFiles();
	    if (files != null)
	        for (File file : files)
	            if (fileName.equals(file.getName()))
	                del(file);
	            else
	                delDotsvn(file,fileName);
	}
	/**
	 * 删除文件或目录
	 */
	public static void del(File file) {
	    File[] files = file.listFiles();
	    if (files != null)
	        for (File f : files)
	         del(f);
	         file.delete();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f=new File("E:\\workCode\\eclipsews\\mysql_lte_sys_wmp");
		delDotsvn(f,".svn");
		delDotsvn(f,"._.svn");
	}
}
