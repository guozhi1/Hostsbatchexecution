package org.example.Tools;
import com.google.gson.Gson;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.example.ButtoMeno.IPlistMenu;
import org.example.ButtoMeno.base64Menu;
import org.example.shared.variableshared;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;


public class Instructionissuance implements TipsBox{
    private static String commade;
    private static String IpLIstdata;
    static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter("src/main/resources/confing.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static class SiteData {
        public Sites sites;

        public static class Sites {
            public List<Site> site;
        }

        public static class Site {
            public int id;
            public String host;  // 需要访问的参数
            public int port;
            public String name;
            public String passwd;
            public String url;
        }
    }
    private void resultReadFile(String host, String Data) throws IOException {
        try {
            // 定义统一的输出文件路径
            String fileName = "output.txt";  // 所有输出都保存到这个文件

            // 创建文件对象
            File file = new File(fileName);

            // 使用 BufferedWriter 写入文件
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) { // true 表示追加模式
                writer.write("执行结果: " + host);
                writer.newLine();
                writer.write(Data);
                writer.newLine();
                writer.write("=====================================");
                writer.newLine();
            }
            System.out.println("结果保存到文件: " + fileName);

        } catch (IOException e) {
            System.out.println("写入文件时发生错误");
            TipsBox.Tips("写入文件时发生错误");
            e.printStackTrace();
        }
    }



    private void  LinkSshCline(String Host, int Port, String User, String Passwd) throws IOException {
        JSch jSch = new JSch();
        try {
            Session session = jSch.getSession(User, Host, Port);
            session.setPassword(Passwd);
            // 关闭主机密钥检查（实际部署时要考虑安全性）
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(10000); //超时时间
            session.connect(); //链接
            System.out.println("当前链接主机 " + Host);
            //执行命令
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(commade);
            //获取命令的输出流
            channel.setInputStream(null);
            channel.setErrStream(System.err);
            // 获取命令执行结果
            channel.connect();
            // 获取命令的输出流
            InputStream inputStream = channel.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream  = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            // 转换为字符串
            String result = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
            System.out.println("Command output: \n" + result);
            resultReadFile(Host, result); //文件写入
            //断开链接
            channel.disconnect();
            session.disconnect();
            System.out.println("断开链接主机 " + Host);
        }catch (JSchException | IOException e){
            System.out.println("ssh模块报错");
            resultReadFile(Host, "链接失败");
            e.printStackTrace();
        }
    }
    public Instructionissuance(){
        this.IpLIstdata = variableshared.Iplistdata;
        this.commade = variableshared.Commandvar;
        Gson gson = new Gson();
        SiteData siteData = gson.fromJson(IpLIstdata, SiteData.class);
        System.out.println("开始创建ssh链接");
        for(SiteData.Site site : siteData.sites.site){
            //System.out.printf(site.passwd);
            System.out.println(site.host+"<--当前链接主机-->"+site.port+"用户名-->"+site.name);
            try {
                LinkSshCline(site.host, site.port, site.name, site.passwd); //链接ssh
            }catch (IOException e){
                TipsBox.Tips("LinkSshCline IOEx错误");
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader bufferedReader = new BufferedReader(  new FileReader("src/main/resources/confing.json"));
        String line;
        StringBuilder lines = new StringBuilder(); // 使用 StringBuilder 来拼接字符串

        // 逐行读取文件内容
        while ((line = bufferedReader.readLine()) != null) {
            lines.append(line);  // 将每一行拼接到 StringBuilder 中
        }
        // 打印读取的内容
        line = lines.toString();
        Gson gson = new Gson();
        SiteData siteData =  gson.fromJson(line ,SiteData.class);
        for (SiteData.Site site : siteData.sites.site){
            System.out.println(site.host);
        }
    }
}
