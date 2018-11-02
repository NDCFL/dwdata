package com.dwsj.util;

import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.*;

//import it.sauronsoftware.jave.Encoder;
//import it.sauronsoftware.jave.EncoderException;
//import it.sauronsoftware.jave.EncodingAttributes;
//import it.sauronsoftware.jave.InputFormatException;
//import it.sauronsoftware.jave.MultimediaInfo;
//import it.sauronsoftware.jave.VideoAttributes;
//import it.sauronsoftware.jave.VideoSize;

public final class FileUtil {
	
	/**
	 * 文件路径分隔符
	 */
	public static final String SP = System.getProperty("file.separator");
	
	/**
	 * 获取path下第一个文件名
	 * @param path
	 * @return
	 */
	public static String getFirstFileName(String path){
		List<String> allFileNames = getAllFileNames(path);
		if (allFileNames == null || allFileNames.size() == 0){
			return null;
		}
		return allFileNames.get(0);
	}
	/**
	 * 获取path下最后一个文件名
	 * @param path
	 * @return
	 */
	public static String getLastFileName(String path){
		List<String> allFileNames = getAllFileNames(path);
		if (allFileNames == null || allFileNames.size() == 0){
			return null;
		}
		return allFileNames.get(allFileNames.size()-1);
	}

	/**
	 * 获取指定path下的所有文件(非隐藏文件)的文件名
	 * @param path
	 * @return
	 */
	public static List<String> getAllFileNames(String path){
		
		if (path == null) return Collections.emptyList();
		List<String> names = null;
		
		File dir = new File(path);
		
		if (dir.isDirectory()){
			names = new ArrayList<String>();
			File[] files = dir.listFiles();
			
			for (File file : files){
				String name = file.getName();
				if (file.isHidden()) {
					continue;
				}
				names.add(name);
			}
		} else {
			names = Collections.emptyList();
		}
		
		return names;
	}
	
	/**
	 * 获取指定path下的所有文件夹
	 * @param path
	 * @return
	 */
	public static List<String> getAllFoldNames(String path){
		
		if (path == null) return Collections.emptyList();
		List<String> names = null;
		
		File dir = new File(path);
		
		if (dir.isDirectory()){
			names = new ArrayList<String>();
			File[] files = dir.listFiles();
			
			for (File file : files){
				if (file.isDirectory()){
					String name = file.getName();
					names.add(name);
				}
			}
		} else {
			names = Collections.emptyList();
		}
		
		return names;
	}
	
	/**
	 * 获取用户所有专辑(包括资源)
	 * @param path
	 * @return List<Map<String,Object>>
	 */
	public static List<Map<String,Object>> getAllAlbums(String path){
		
		/**[
		 * {
		 * 	name: albumName
		 * 	resources : List<String>
		 *  count: 123
		 * },
		 *	{
		 * 	name: albumName
		 * 	resources : List<String>
		 *  count: 123
		 * } 
		 * ]
		 */
		
		
		if (path == null) return Collections.emptyList();
		
		List<Map<String,Object>> allAlbums = new ArrayList<Map<String,Object>>();
		
		List<String> allAlbumNames = getAllFoldNames(path);
		for(String albumName : allAlbumNames){
			List<String> resNames = getAllFileNames(path+SP+albumName);
			
			Map<String,Object> album = new HashMap<String,Object>();
			album.put("name", albumName);
			album.put("resources", resNames);
			album.put("count", resNames.size());
			allAlbums.add(album);
		}
		
		return allAlbums;
	}
	
/*	public static void main(String[] args) {
		String path = "F:/apache-tomcat-8.0.43/webapps/upload/pic/8/album/";
		Map<String, List<String>> allAlbums = getAllAlbums(path);
		Iterator<Entry<String, List<String>>> iterator = allAlbums.entrySet().iterator();
		
		while (iterator.hasNext()){
			Entry<String, List<String>> album = iterator.next();
			String albumName = album.getKey();
			System.out.println(albumName+"========="+album.getValue().size());
			List<String> resNames = album.getValue();
			for (String resName : resNames){
				System.out.println(resName);
			}
		}
	}*/
	
	/**
	 * 保存 base64编码的图片
	 * @param base64Img
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String uploadBase64Imgs(String base64Img, String path) throws Exception{
		List<String> newNames = uploadBase64Imgs(new String[]{base64Img}, path);
//		if (Collections3.isEmpty(newNames)){
//			return null;
//		}
		return newNames.get(0);
	}
	
	/**
	 * 批量保存 base64编码的图片
	 * @param base64Imgs
	 * @param path
	 * @return 文件的 新名字 集合
	 * @throws Exception
	 */
	public static List<String> uploadBase64Imgs(String[] base64Imgs, String path) throws Exception{
		
		String sp = System.getProperty("file.separator");
		BASE64Decoder decoder = new BASE64Decoder();
		List<String> newNames = new ArrayList<String>();
		for (String b64Img : base64Imgs){
			String image = b64Img.replaceFirst("data:image/\\w{1,};base64,", "");
			byte[] buf = decoder.decodeBuffer(image);
			// 数据头: data:image/([\\w]{1,});base64
			int startIndex = b64Img.indexOf("/");
			int endIndex = b64Img.indexOf(";");
			String suffix = b64Img.substring(startIndex+1, endIndex);
			String newName = System.currentTimeMillis()+"."+suffix;
			saveFile(buf,path+sp+newName);
			newNames.add(newName);
		}
		
		return newNames;
	}
	
	/**
	 * 保存文件到指定文件
	 * @param buf
	 * @param fullPath 文件
	 * @throws Exception
	 */
	public static boolean saveFile(byte[] buf, String fullPath) throws Exception{
		if (fullPath == null || fullPath.trim().equals("")) return false;
		File dir = new File(new File(fullPath).getParent());
		
		if (!dir.exists()){
			dir.mkdirs();
		}
		
		FileOutputStream fos = new FileOutputStream(fullPath);

		try {
			fos.write(buf);
			fos.flush();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			fos.close();
		}
		
	}
	
	/**
	 * 从路径中获取文件名
	 * @return
	 */
	public static String getFileNameFromPath(String fullPath){
		if (StringUtil.isEmpty(fullPath)) return null;
		String fileName = new File(fullPath).getName();
		return fileName;
	}
	
	/**
	 * 获取路径某一层级的名称
	 * @param path	路径
	 * @param level	层级(从右开始，level若为负数，则默认为0,若level层级过大，则返回null)
	 * @return	层级名称，如果path为null，则抛出IllegalArgumentException<br>
	 * 举例："E:\\aaa\\cunnar\\17702718868\\xyz.pdf"，level=3时，返回aaa
	 */
	public static String getLevelNameFromRight(String path, int level){
		if (path == null || path.trim().equals("")) throw new IllegalArgumentException("路径不能为空");
		level = level < 0 ? 0 : level;
		File f = new File(path);
		for (int i=0; i<level; i++){
			String p = f.getParent();
			if (p == null) return null;
			f = new File(p);
		}
		return f.getName();
	}
	
	/**
	 * 分页查找
	 * @param page
	 * @return
	 */
	public static List<String> findByPage(String path, Page page){
		
		File dir = new File(path);
		
		if (!dir.isDirectory()) return Collections.emptyList();
		
		List<String> allFileNames = getAllFileNames(path);
		
		if (allFileNames == null || allFileNames.size() == 0) return Collections.emptyList();
		
		String[] array = (String[]) allFileNames.toArray(new String[allFileNames.size()]);
		
		
		int nextStart = page.getNextStart();
		int len = (nextStart + page.size) > array.length ? array.length - nextStart : page.size;
		
		String[] pageFileNames = new String[len];
		
		System.arraycopy(array, page.getNextStart(), pageFileNames, 0, len);
		
		List<String> names = Arrays.asList(pageFileNames);
		
		return names;
	}
	
	/**
	 * 分页
	 * @author cgw
	 * @date 2017年8月14日
	 */
	public static class Page{
		public int size = 10;
		public int currPage = 0;
		public int totalCount = 0;
		
		public Page getNextPage(){
			this.currPage++;
			return this;
		}
		
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		public int getCurrPage() {
			return currPage;
		}
		public void setCurrPage(int currPage) {
			this.currPage = currPage;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		public int getTotalPageCount() {
			
			if (size == 0){
				return 0;
			}
			
			int a =  totalCount / size ;
			int b = totalCount % size;
			int totalPageCount = a + (b > 0 ? 1 : 0);
			
			return totalPageCount;
		}
		public int getNextStart() {
			int next = currPage == 0 ? 0 : currPage - 1;
			System.out.println(next*size);
			return next * size;
		}
	}
	
	/**
	 * 创建文件夹
	 * @param dirName
	 */
	public static boolean createFold(String dirName){
		if (StringUtil.isEmpty(dirName)) return false;
		File dir = new File(dirName);
		if(dir.exists()) return true;
		boolean created = dir.mkdirs();
		return created;
	}
	
	/**
	 * 删除文件夹或文件
	 * @param path
	 */
	public static void delPathes(String path){
		
		if (StringUtil.isEmpty(path)) return ;
		
		File dir = new File(path);
		if (!dir.exists()){
			return ;
		}
		
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				delPathes(path +SP+ children[i]);
			}
		}
		dir.delete();
	}

	/**
	 * 生成新的文件名称（时间戳+文件后缀）
	 * @param fileName
	 * @return
	 */
	public static String reName(String fileName) {
		return new StringBuffer().append(new Date().getTime()).append(fileName.substring(fileName.indexOf("."))).toString();
	}

	/**
	 * 以流的形式保存文件
	 * @param is 
	 * @param fos 
	 */
	public static void copy(InputStream is, OutputStream fos) {
		try {
            byte[] tmp = new byte[1024];
            int len = -1;
            while ((len = is.read(tmp)) != -1)
            	fos.write(tmp, 0, len);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	/**
	 * 重命名dir/oldName为dir/newName
	 * @param dir
	 */
	public static boolean rename(String dir,String oldName, String newName){
		File o = new File(dir+File.separator+oldName);
		File n = new File(dir+File.separator+newName);
		if (!o.exists()){
			return false;
		}
		return o.renameTo(n);
	}
	
	/**
	 * 读取文本文件,并把内容转换成字符串(包含换行符)
	 * @param fullPath
	 * @return 如果文件不存在,则返回null
	 */
	public static String readTextFileAsString(String fullPath){
		
		if (fullPath == null){
			return null;	
		}
		
		File info = new File(fullPath);
		if (!info.exists()){
			return null;
		}
		
		String line = null;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(info));
			while ((line = br.readLine()) != null){
				sb.append(line+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取视频文件的时长
	 * @param video 视频文件
	 * @return 时长的毫秒数
	 */
//	public static long getVideoTimeLengthByLong(File video) {
//		Encoder encoder = new Encoder();
//		long timeLength = 0;
//		try {
//			MultimediaInfo m = encoder.getInfo(video);
//			timeLength = m.getDuration() / 1000;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return timeLength;
//	}
	
	/**
	 * 获取视频文件的时长
	 * @param video 视频文件
	 * @return 时长的字符串形式  00:00:00
	 */
//	public static String getVideoTimeLengthByStr(File video) {
//		Encoder encoder = new Encoder();
//		String timeLength = "";
//		try {
//			MultimediaInfo m = encoder.getInfo(video);
//			long ls = m.getDuration() / 1000;
//			int hour = (int) (ls / 3600);
//			int minute = (int) (ls % 3600) / 60;
//			int second = (int) (ls - hour * 3600 - minute * 60);
//			String hour_str = hour<10?"0"+hour:hour+"";
//			String minute_str = minute<10?"0"+minute:minute+"";
//			String second_str = second<10?"0"+second:second+"";
//			timeLength = hour_str + ":" + minute_str + ":" + second_str;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return timeLength;
//	}
	
	
	/**
	 * 保存视频文件的第一帧图片
	 * @param sourceFilePath 源视频路径
	 * @param targetFilePath	第一帧视频图片保存的位置
	 */
	public static void saveVideoFirstImg(String sourceFilePath, String targetFilePath) {
//		File source = new File(sourceFilePath);
//		File target = new File(targetFilePath);// 转图片
//		VideoAttributes video = new VideoAttributes();
//		video.setCodec("png");// 转图片
//		video.setSize(new VideoSize(400, 300));
//		EncodingAttributes attrs = new EncodingAttributes();
//		attrs.setFormat("image2");// 转图片
//		attrs.setOffset(3f);// 设置偏移位置，即开始转码位置（3秒）
//		attrs.setDuration(0.01f);// 设置转码持续时间（1秒）
//		attrs.setVideoAttributes(video);
//		Encoder encoder = new Encoder();
//		long beginTime = System.currentTimeMillis();
//		try {
//			// 获取时长
//			MultimediaInfo m = encoder.getInfo(source);
//			System.out.println(m.getDuration());
//			System.out.println("获取时长花费时间是：" + (System.currentTimeMillis() - beginTime));
//			beginTime = System.currentTimeMillis();
//			encoder.encode(source, target, attrs);
//			System.out.println("视频转码花费时间是：" + (System.currentTimeMillis() - beginTime));
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (InputFormatException e) {
//			e.printStackTrace();
//		} catch (EncoderException e) {
//			System.out.println("这个异常可以不处理");
//		}
	}
	/**
	 * 移动文件
	 * @param from	原文件路径
	 * @param to	目标文件路径
	 */
	public static boolean moveTo(String from, String to) {
		File fromFile = new File(from);
		boolean copyOk = copyTo(from,to);
		if (copyOk){
			if (fromFile.delete()){
				return true;
			} else {
				copyTo(to,from);
			}
		}
		
		return false;
	}
	
	/**
	 * 拷贝文件
	 * @param from	原文件路径
	 * @param to	目标文件路径
	 * @return
	 */
	public static boolean copyTo(String from, String to){
		File fromFile = new File(from);
		File toFile = new File(to);
		if (!toFile.getParentFile().exists()){
			boolean created = createFold(toFile.getParent());
			if (!created) return false;
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(fromFile);
			out = new FileOutputStream(toFile);
			copy(in,out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e2) {
			}
		}
		
		return toFile.exists();
	}
	
	/**
	 * 写文本内容到指定文件
	 * @param text		文本内容(不能为空)
	 * @param desFile	目标文件(不能为空，若不存在，则新建；若存在，则覆盖)
	 * @return
	 */
	public static boolean writeToFile(String text, String desFile){
		if (text == null || desFile == null) return false;
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(desFile));
			out.write(text.getBytes("UTF-8"));
			out.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				out.close();
			} catch (Exception e2) {
			}
		}
	}
}
