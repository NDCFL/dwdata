package com.yulore.bangmiaopa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CuiShouTag {

    /**
     * api key
     * 请换成电话邦提供的apikey
     */
    private static final String apikey = "";
    /**
     * secret
     * 请换成电话邦提供的appsecret
     */
    private static final String appsecret = "";

    /**
     * 请换成电话邦催收分接口返回的 sid
     */
    private static final String sid = "";
    /**
     * 超时时间
     */
    private static final int TIME_OUT = 10 * 1000;
    /**
     * content type
     */
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    /**
     * 编码格式
     */
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";

    public static void main(String[] args) {
        String time = (System.currentTimeMillis() / 1000) + "";
        String url = "https://cuishou-api.dianhua.cn/busi/itagInfo?apikey=" + apikey + "&time=" + time + "&sid=" + sid;
        Map<String, String> map = new TreeMap<String, String>();
        map.put("sid", sid);
        map.put("apikey", apikey);
        map.put("time", time);
        String sig = Signature(map, appsecret);
        // 得到api请求url
        String sigUrl = url + "&sig=" + sig;
        request(sigUrl);
    }

    /**
     * 编码
     *
     * @return
     */
    private static String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }

    /**
     * 获取ContentType
     *
     * @return
     */
    private static String getBodyContentType() {
        return "application/json; charset=" + getParamsEncoding();
    }

    private static String Signature(Map map, String appsecret) {
        String sig_str = "";

        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            sig_str = sig_str + s + map.get(s);
        }
        sig_str = appsecret + sig_str + appsecret;
        return SHA1(sig_str);
    }

    private static String SHA1(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes(Charset.forName("UTF-8")));
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void request(String url) {
        try {
            URL parsedUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) parsedUrl.openConnection();
            connection.setConnectTimeout(TIME_OUT);
            connection.setReadTimeout(TIME_OUT);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.addRequestProperty(HEADER_CONTENT_TYPE, getBodyContentType());
            connection.connect();
            int responseCode = connection.getResponseCode();
            System.out.println("response code: " + responseCode);
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("response code not ok");
                String responseData = getContent(connection.getErrorStream());
                System.out.println("response data: " + responseData);
            } else {
                System.out.println("response code ok");
                String responseData = getContent(connection.getInputStream());
                System.out.println("response data: " + responseData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getContent(InputStream in) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
