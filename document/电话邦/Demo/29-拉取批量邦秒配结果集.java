package com.yulore.bmp.demo;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 邦秒配实现
 */
public class BmpApiImpl {
    private static final String HOST_BATCH = "https://itag.dianhua.cn/batch/";
    private static final String HOST_BATCH_RESULT = "https://itag.dianhua.cn/batchResult/";

    public static void main(String[] args) {
        BmpApiImpl api = new BmpApiImpl();
        try {
            // 批量查询
            // TODO
            String batchTestData = "替换 JSON ARRAY 格式的批量号码数据"; // 例如 ["10086","100861"]

            String queryBatchResult = api.queryBatch("替换 apikey", "替换 country", "替换 uid", "替换 app", "替换 app_ver", "替换 version", "替换 callback，可以不传", batchTestData);

            System.out.println(queryBatchResult);

            String batchResult = api.batchResult("替换 apikey", "替换批量查询接口结果中得到的 tid 值");
            System.out.println(batchResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量查询任务接口
     * 输入批量电话号码(最多支持 500 个)，同步返回当前查询任务信息，异步返回识别结果
     *
     * @param apikey   接口调用凭证
     * @param country  号码的国家代码，中国:86
     * @param uid      用户唯一识别 id
     * @param app      调用用户的应用或项目标识(名称)
     * @param app_ver  调用用户的应用或项目的版本号
     * @param version  金融标签接口版本
     * @param callback 回调地址，由调用者提供，用来接收查询结果
     * @param data     批量号码，数据格式为 json array
     * @return 当前查询任务信息
     * @throws IOException
     */
    public String queryBatch(String apikey, String country, String uid, String app, String app_ver, String version,
                             String callback, String data) throws IOException {
        return post(createQueryBatchUrl(apikey, country, uid, app, app_ver, version, callback),
                data, true, false);
    }

    /**
     * 批量查询任务接口
     * 输入批量电话号码(最多支持 500 个)，同步返回当前查询任务信息，异步返回识别结果
     *
     * @param apikey  接口调用凭证
     * @param country 号码的国家代码，中国:86
     * @param uid     用户唯一识别 id
     * @param app     调用用户的应用或项目标识(名称)
     * @param app_ver 调用用户的应用或项目的版本号
     * @param version 金融标签接口版本
     * @param data    批量号码，数据格式为 json array
     * @return 当前查询任务信息
     * @throws IOException
     */
    public String queryBatch(String apikey, String country, String uid, String app, String app_ver, String version,
                             String data) throws IOException {
        return queryBatch(apikey, country, uid, app, app_ver, version, null, data);
    }

    /**
     * 批量任务结果查询接口
     * 输入成功请求批量接口后返回的 tid 标识，返回批量查询的结果
     *
     * @param apikey 接口调用凭证
     * @param tid    批量查询任务唯一标识
     * @return 批量查询的结果
     * @throws IOException
     */
    public String batchResult(String apikey, String tid) throws IOException {
        return get(createBatchResultUrl(apikey, tid), true);
    }

    private static String createQueryBatchUrl(String apikey, String country, String uid, String app, String app_ver, String version, String callback) {
        return HOST_BATCH + "?apikey=" + apikey + "&country=" + country + "&uid=" + uid + "&app=" + app + "&app_ver="
                + app_ver + "&version=" + version + ((callback != null && callback.length() != 0) ? "&callback=" + callback : "");
    }

    private static String createBatchResultUrl(String apikey, String tid) {
        return HOST_BATCH_RESULT + "?apikey=" + apikey + "&tid=" + tid;
    }

    public static String get(String link, boolean gzip) throws IOException {
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
                result = gzip ? new String(decompress(bytes), StandardCharsets.UTF_8) : new String(bytes, StandardCharsets.UTF_8);
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

    public static String post(String link, String data, boolean inGzip, boolean outGzip) throws IOException {
        URL url = new URL(link);
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if (!(data == null || data.isEmpty())) {
                connection.getOutputStream().write(inGzip ? compress(data.getBytes(StandardCharsets.UTF_8)) : data.getBytes(StandardCharsets.UTF_8));
            }
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            stream = connection.getInputStream();
            if (stream != null) {
                byte[] bytes = toBytes(stream);
                result = outGzip ? new String(decompress(bytes), StandardCharsets.UTF_8) : new String(bytes, StandardCharsets.UTF_8);
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

    public static byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        gzip = new GZIPOutputStream(out);
        gzip.write(data);
        gzip.close();
        return out.toByteArray();
    }

    public static byte[] decompress(byte[] bytes) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        GZIPInputStream unGzip = new GZIPInputStream(in);
        byte[] buffer = new byte[1024];
        int n;
        while ((n = unGzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }

        return out.toByteArray();
    }

}
