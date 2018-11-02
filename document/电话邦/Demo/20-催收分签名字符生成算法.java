package com.yulore.bangmiaopa;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CuiShouFenSign {

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

    public static void main(String[] args) {
        String time = (System.currentTimeMillis() / 1000) + "";
        String url = "https://cuishou-api.dianhua.cn/busi/genCuiShou?apikey=" + apikey + "&time=" + time;
        Map<String, String> map = new TreeMap<String, String>();
        map.put("apikey", apikey);
        map.put("time", time);
        String sig = Signature(map, appsecret);
        System.out.println("sig: " + sig + " time: " + time);

        // 得到api请求url
        String sigUrl = url + "&sig=" + sig;
    }

    /**
     * 催收分签名字符串生成算法
     *
     * @param map
     * @param appsecret
     * @return
     */
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
}