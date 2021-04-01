package com.zhm.gen.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FFmpegUtil {
    private static String FFMPEG_PATH = "ffmpeg";


    //格式:"00:00:10.68"
    private static float getTimeFloat(String timelen) {
        float min = 0;
        String[] strs = timelen.split(":");
        if (strs[0].compareTo("0") > 0) {
            min += Integer.valueOf(strs[0]) * 60 * 60;//秒
        }
        if (strs[1].compareTo("0") > 0) {
            min += Integer.valueOf(strs[1]) * 60;
        }
        if (strs[2].compareTo("0") > 0) {
            min += Float.valueOf(strs[2]);
        }
        return min;
    }

    /**
     * 获取 视频/音频 总时间（秒）
     *
     * @return
     */
    public static double getVideoTimeFloat(String video_path) {
        List<String> commands = new java.util.ArrayList<String>();
        commands.add(FFMPEG_PATH);
        commands.add("-i");
        commands.add(video_path);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            final Process p = builder.start();

            //从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            //从视频信息中解析时长
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
            Pattern pattern = Pattern.compile(regexDuration);
            Matcher m = pattern.matcher(sb.toString());
            if (m.find()) {
                double time = getTimeFloat(m.group(1));
//                LogUtil.info(video_path+",视频时长："+time+", 开始时间："+m.group(2)+",比特率："+m.group(3)+"kb/s");
                return time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Double转为Integer
     *
     * @param count
     * @return
     */
    public static Integer convertInt(Double count) {
        DecimalFormat df = new DecimalFormat("#.000");
        Double d = Double.valueOf(df.format(count)) * 1000;
        Integer i = d.intValue();
        return i;
    }

    /**
     * @author: zhm
     * @version: 1.0
     * @Description: 合并多个MP3
     * @create: 2020/8/18 22:31
     * @return:
     */
    public static String addMp3List(List<String> mp3List, String finallyPath) {
        StringBuffer sb = new StringBuffer();
        List<String> cmd = new java.util.ArrayList<String>();
        cmd.add(FFMPEG_PATH);
        for (String mp3Path : mp3List) {
            cmd.add("-i");
            cmd.add(mp3Path);
        }
        cmd.add("-filter_complex");
        cmd.add(String.format("concat=n=%d:v=0:a=1", mp3List.size()));
        cmd.add("-f");
        cmd.add("mp3");
        cmd.add("-vn");
        if (new File(finallyPath).exists()) {
            cmd.add("-y");
        }
        cmd.add(finallyPath);
        try {
            LogUtil.info(cmd);
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(cmd);
            final Process p = builder.start();
            //从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
