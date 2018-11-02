package com.yulore.bmp.demo;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 邦秒配实现
 */
public class BmpApiImpl {
    private static final String HOST = "https://itag.dianhua.cn/itag/";

    public static void main(String[] args) {
        BmpApiImpl api = new BmpApiImpl();
        try {
            // 单条查询
            // TODO
            String result = api.query("替换 apikey", " 替换 tel", "替换 country", "替换 uid", "替换 app", "替换 app_ver", "替换 version");
            System.out.println(result);

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 金融标签识别接口
     * 输入电话号码，返回金融标签
     *
     * @param apikey  接口调用凭证
     * @param tel     要进行识别的完整电话号码
     * @param country 号码的国家代码，中国：86
     * @param uid     用户唯一识别id
     * @param app     调用用户的应用或项目标识（名称）
     * @param app_ver 调用用户的应用或项目的版本号
     * @param version 金融标签接口版本
     * @return 金融标签
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String query(String apikey, String tel, String country, String uid, String app, String app_ver,
                        String version) throws IOException, NoSuchAlgorithmException {
        return get(createQueryUrl(tel, uid, app, app_ver, apikey, country, version));
    }

    private static String createQueryUrl(String tel, String uid, String app, String app_ver, String apikey, String country,
                                         String version) throws NoSuchAlgorithmException, MalformedURLException {
        String sig = computeSign(tel, uid, app, app_ver, apikey, country, version);
        String url = HOST + "?apikey=" + apikey + "&tel=" + tel + "&country=" + country + "&uid=" + uid + "&app=" + app + "&app_ver=" + app_ver + "&sig=" + sig + "&version=" + version;
        System.out.println(url);
        return url;
    }

    /**
     * 签名生成方法
     */
    private static String computeSign(String tel, String uid, String app, String app_ver, String apikey, String country,
                                      String version) throws NoSuchAlgorithmException {
        // TODO
        String sign = "替换你的签名字符串".replace("{tel}", tel).replace("{uid}", uid)
                .replace("{app}", app).replace("{app_ver}", app_ver).replace("{apikey}", apikey).replace("{country}", country)
                .replace("{version}", version);
        return sha1(sign).substring(4, 36);
    }

    /**
     * sha1 算法
     */
    private static String sha1(String input) throws NoSuchAlgorithmException {
        String result;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] output = messageDigest.digest(input.getBytes());
        result = byte2String(output);
        return result;
    }

    /**
     * bytes 转 String
     */
    private static String byte2String(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String temp;
        for (byte aB : bytes) {
            temp = (Integer.toHexString(aB & 0XFF));
            if (temp.length() == 1) {
                sb.append("0").append(temp);
            } else {
                sb.append(temp);
            }
        }
        return sb.toString();
    }

    public static String get(String link) throws IOException {
        URL url = new URL(link);
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            stream = connection.getInputStream();
            if (stream != null) {
                byte[] bytes = toBytes(stream);
                result = new String(bytes, StandardCharsets.UTF_8);
            }
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    private static byte[] toBytes(InputStream from) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[8096];
        while (true) {
            int r = from.read(buf);
            if (r == -1) {
                break;
            }
            out.write(buf, 0, r);
        }
        return out.toByteArray();
    }
}
