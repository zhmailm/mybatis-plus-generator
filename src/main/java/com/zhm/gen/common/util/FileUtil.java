package com.zhm.gen.common.util;

/**
 * <p>Description: File 工具类</p>
 * <p>Copyright: Copyright (c)2019</p>
 * <p>Company: elite</p>
 * <P>Created Date :2020-04-22</P>
 *
 * @author huty
 * @version 1.0
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static final Log logger = LogFactory.getLog(FileUtil.class);
    private static String ENCODING = "UTF-8";

    /**
     * @throws Exception
     * @Description:上传附件
     * @create: 2019-07-01
     * @update logs
     * @author huty
     */
    public String saveFile(MultipartFile file, HttpServletRequest request, String strPath) {
        String strFilePath = "";
        if (file == null || file.isEmpty()) {
            return strFilePath;
        }
        String path = strPath;
        String fileName = file.getOriginalFilename().replaceAll(" ", "");
        String strTemp = UUIDUtil.generateInteger(9);

        String regex = "^[a-z0-9A-Z]+$";
        if (!strTemp.matches(regex)) {
            strTemp = fileName.substring(fileName.lastIndexOf("."));
        } else if (fileName.contains(".")) {
            strTemp += fileName.substring(fileName.lastIndexOf("."));
        } else {
            logger.info("file Name may be error:---------->" + fileName);
            strTemp = fileName;
        }
        File targetFile = new File(path, System.currentTimeMillis() + "_" + strTemp);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 保存
        try {
            file.transferTo(targetFile);
            strFilePath = targetFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strFilePath;
    }

    public static void copy(String sourceUrl, String destUrl) {
        File source = new File(sourceUrl);
        File dest = new File(destUrl);
        try {
            Files.copy(source.toPath(), dest.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * down load File 下载文件
     *
     * @param imageName URL
     * @param urlList
     */
    public static void downloadFile(String imageName, String urlList) {
        URL url = null;

        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            File file = new File(imageName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ByteArrayOutputStream output = new ByteArrayOutputStream();

                byte[] buffer = new byte[102400];
                int length;

                while ((length = dataInputStream.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
                fileOutputStream.write(output.toByteArray());
                dataInputStream.close();
                fileOutputStream.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param * @param null
     * @return
     * @Description:从网络Url中下载文件
     * @Author huty
     * @date 2020/6/14
     */
    public static void downloadFromUrl(String urlStr, String fileName, String savePath, String toekn) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if (toekn != null && !("").equals(toekn)) {
                conn.setRequestProperty("lfwywxqyh_token", toekn);
            }

            //得到输入流
            InputStream inputStream = conn.getInputStream();
            //获取自己数组
            byte[] getData = readInputStream(inputStream);

            //文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param * @param null
     * @return
     * @Description:从输入流中获取字节数组
     * @Author huty
     * @date 2020/6/14
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * down load File 流下载文件
     *
     * @param inputStream
     * @param fileName
     */
    public static void downloadFile(InputStream inputStream, String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ByteArrayOutputStream output = new ByteArrayOutputStream();

                byte[] buffer = new byte[102400];
                int length;

                while ((length = inputStream.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
                fileOutputStream.write(output.toByteArray());
                inputStream.close();
                fileOutputStream.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete File 删除文件
     *
     * @param path
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * delete Folder All File 路径下文件删除 self true 删除文件夹 false 保留路径
     *
     * @param path
     */
    public static void deleteFolder(String path, boolean self) {
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            String[] filePath = file.list();
            if (filePath != null && filePath.length > 0) {
                for (int i = 0; i < filePath.length; i++) {
                    File childFile = new File(path + File.separator + filePath[i]);
                    if (childFile.exists()) {
                        if (childFile.isFile()) {
                            System.gc();    //加上确保文件能删除，不然可能删不掉
                            childFile.delete();
                        } else if (childFile.isDirectory()) {
                            deleteFolder(path + File.separator + filePath[i], true);
                        }
                    }
                }
            }
            if (self) {
                System.gc();    //加上确保文件能删除，不然可能删不掉
                file.delete();
            }
        }
    }

    /**
     * enumFile 递归得目录的FileType结尾的文件
     *
     * @param FileType
     * @param sourcePath
     */
    public static List<String> enumFile(String sourcePath, String FileType) {

        try {
            List<String> list = new ArrayList<String>();
            File dir = new File(sourcePath); // 要遍历的目录,用递归拿文件,文件不能太多
            recursion(dir.listFiles(), list, FileType); // 递归函数
            String[] ss = new String[list.size()];
            for (int i = 0; i < ss.length; i++) {
                ss[i] = list.get(i);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * recursion 递归得所有子目录的FileType结尾的文件
     *
     * @param fs
     * @param list
     */
    private static void recursion(File[] fs, List<String> list, String FileType) {

        for (File f : fs) {
            if (f.isDirectory()) {
                recursion(f.listFiles(), list, FileType);
            } else {
                if (f.getName().endsWith(FileType)) {
                    list.add(f.getAbsolutePath());
                }
            }
        }
    }

    /**
     * 文件流输出文件
     *
     * @param inputStream fileName
     */
    public static void outputFile(InputStream inputStream, String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                File fileParent = file.getParentFile();
                if (!fileParent.exists()) {
                    fileParent.mkdirs();
                }
                file.createNewFile();

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ByteArrayOutputStream output = new ByteArrayOutputStream();

                byte[] buffer = new byte[102400];
                int length;

                while ((length = inputStream.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
                fileOutputStream.write(output.toByteArray());
                inputStream.close();
                fileOutputStream.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * String 输出文件
     *
     * @param filePath
     * @param content
     * @param encoding
     */
    public static void outputFile(String filePath, String content, String encoding) {
        if (encoding == null) {
            encoding = ENCODING;
        }

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File fileParent = file.getParentFile();
                if (!fileParent.exists()) {
                    fileParent.mkdirs();
                }
                file.createNewFile();
            }
            if (content != null && !("").equals(content)) {
                //打开文件
                FileOutputStream fos = new FileOutputStream(file);
                //设置编码集
                OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
                //将字符输出流包装为字符缓冲输出流
                BufferedWriter out = new BufferedWriter(osw);
                out.write(content);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * String 输出文件
     *
     * @param filePath
     * @param senList
     * @param encoding
     */
    public static void outputFile(String filePath, List<String> senList, String encoding) {
        if (encoding == null) {
            encoding = ENCODING;
        }

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File fileParent = file.getParentFile();
                if (!fileParent.exists()) {
                    fileParent.mkdirs();
                }
                file.createNewFile();
            }

            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), encoding);
            BufferedWriter bw = new BufferedWriter(write);

            for (String string : senList) {
                bw.write(string);
                bw.newLine();
            }

            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件读行
     *
     * @param filePath
     * @param encoding
     * @return
     */
    public static List<String> inputFile(String filePath, String encoding) {

        if (encoding == null) {
            encoding = ENCODING;
        }
        List<String> resultList = new ArrayList<String>();

        File inFile = new File(filePath);
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(inFile), encoding);
            BufferedReader bufferR = new BufferedReader(read);

            String inLine;
            if (inFile.isFile() && inFile.exists()) {
                while ((inLine = bufferR.readLine()) != null) {
                    resultList.add(inLine);
                }
                read.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 读取文件
     *
     * @param filePath
     * @param encoding
     * @return
     */
    public static String readFile(String filePath, String encoding) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            String cread;
            StringBuffer content = new StringBuffer();

            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader in = new BufferedReader(read);
            while ((cread = in.readLine()) != null) {
                content.append(cread);
            }
            read.close();
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }

    }

    /**
     * create File Parent
     *
     * @param filePath
     */
    public static void createputFileParent(String filePath) {
        filePath = filePath + "\\temp";
        File file = new File(filePath);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        file.delete();
    }

    /**
     * append File
     *
     * @param filePath
     * @param content
     */
    public static void appendFileA(String filePath, String content) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(file, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * append File
     *
     * @param filePath
     * @param content
     */
    public static void appendFileB(String filePath, String content) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(file, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            // 因为如果以MB为单位的话，要保留最后1位小数，
            // 因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }
    public static void inputStreamToFile(InputStream ins, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File fileParent = file.getParentFile();
                if (!fileParent.exists()) {
                    fileParent.mkdirs();
                }
                file.createNewFile();
            }
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];

            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
