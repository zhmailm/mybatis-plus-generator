package com.zhm.gen.common.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;

public class ShellUtil {

    /**
     * 执行系统命令, 返回执行结果
     *
     * @param cmdString 需要执行的命令
     */
    public static String exec(String type, String cmdString) throws Exception {
        // todo 设置的 path 环境变量需要重启才能生效
        String[] cmdShell = new String[]{};
        if ("powershell".equals(type)) {
            cmdShell = new String[]{"C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe", "/c", cmdString};
        } else if ("cmd".equals(type)) {
            cmdShell = new String[]{"cmd", "/c", cmdString};
        } else if ("shell".equals(type)) {
            cmdShell = new String[]{ "/usr/bin/"+cmdString};
        } else {
            return null;
        }
        LogUtil.info(cmdShell[0]+" "+cmdShell[1]+" "+cmdShell[2]);
        StringBuilder result = new StringBuilder();

        Process process = null;
        BufferedReader bufrIn = null;
        BufferedReader bufrError = null;
        Runtime runtime = Runtime.getRuntime();

        try {
            // 执行命令, 返回一个子进程对象（命令在子进程中执行）
            process = runtime.exec(cmdShell);

            // 获取命令执行结果, 有两个结果: 正常的输出 和 错误的输出（PS: 子进程的输出就是主进程的输入）
            bufrIn = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB2312"));
            bufrError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "GB2312"));

            // 读取输出
//            String line = null;
//            while ((line = bufrIn.readLine()) != null) {
//                result.append(line);
//            }
//            while ((line = bufrError.readLine()) != null) {
//                result.append(line);
//            }
            closeStream(bufrIn);
            closeStream(bufrError);
            // 方法阻塞, 等待命令执行完成（成功会返回0）
            int pid = process.waitFor();
            // 销毁子进程
            if (process != null) {
                process.destroy();
            }
            // 返回执行结果
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result.toString();
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                LogUtil.error(e);
            }
        }
    }

}
