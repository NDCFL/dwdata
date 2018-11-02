package com.yulore.bangmiaopa;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CuiShouFen {

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
        String url = "https://cuishou-api.dianhua.cn/busi/genCuiShou?apikey=" + apikey + "&time=" + time;
        Map<String, String> map = new TreeMap<String, String>();
        map.put("apikey", apikey);
        map.put("time", time);
        String sig = Signature(map, appsecret);
        System.out.println("sig: " + sig + " time: " + time);
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
            connection.setRequestMethod("POST");
            connection.addRequestProperty(HEADER_CONTENT_TYPE, getBodyContentType());
            connection.setDoOutput(true);
            String bodyStr = "{\"tel\":\"15652297411\",\"time\":1516951470,\"call_log\":[{\"call_method\":\"2\",\"call_duration\":\"49\",\"call_time\":\"1516779825\",\"call_tel\":\"18810605884\"},{\"call_method\":\"2\",\"call_duration\":\"79\",\"call_time\":\"1516415863\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"10\",\"call_time\":\"1516333526\",\"call_tel\":\"13716457689\"},{\"call_method\":\"2\",\"call_duration\":\"17\",\"call_time\":\"1516241002\",\"call_tel\":\"15511632557\"},{\"call_method\":\"2\",\"call_duration\":\"31\",\"call_time\":\"1516170165\",\"call_tel\":\"2867507763\"},{\"call_method\":\"1\",\"call_duration\":\"368\",\"call_time\":\"1515925308\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"15\",\"call_time\":\"1515894179\",\"call_tel\":\"18610738908\"},{\"call_method\":\"1\",\"call_duration\":\"245\",\"call_time\":\"1515840316\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"73\",\"call_time\":\"1515805866\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"94\",\"call_time\":\"1515800728\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"7\",\"call_time\":\"1515638331\",\"call_tel\":\"13991964008\"},{\"call_method\":\"2\",\"call_duration\":\"35\",\"call_time\":\"1515580183\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"22\",\"call_time\":\"1515491891\",\"call_tel\":\"13121836378\"},{\"call_method\":\"2\",\"call_duration\":\"11\",\"call_time\":\"1515409088\",\"call_tel\":\"125909888520\"},{\"call_method\":\"1\",\"call_duration\":\"154\",\"call_time\":\"1515322084\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"13\",\"call_time\":\"1515216765\",\"call_tel\":\"13311598639\"},{\"call_method\":\"2\",\"call_duration\":\"11\",\"call_time\":\"1515207104\",\"call_tel\":\"17600127347\"},{\"call_method\":\"2\",\"call_duration\":\"12\",\"call_time\":\"1515203019\",\"call_tel\":\"17301319062\"},{\"call_method\":\"2\",\"call_duration\":\"7\",\"call_time\":\"1515027989\",\"call_tel\":\"18513567706\"},{\"call_method\":\"2\",\"call_duration\":\"16\",\"call_time\":\"1514971208\",\"call_tel\":\"17020303669\"},{\"call_method\":\"2\",\"call_duration\":\"38\",\"call_time\":\"1514854926\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"25\",\"call_time\":\"1514809084\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"214\",\"call_time\":\"1514808792\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"18\",\"call_time\":\"1514806466\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"38\",\"call_time\":\"1514799379\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"24\",\"call_time\":\"1514791585\",\"call_tel\":\"18335693754\"},{\"call_method\":\"1\",\"call_duration\":\"67\",\"call_time\":\"1514783638\",\"call_tel\":\"18334673661\"},{\"call_method\":\"2\",\"call_duration\":\"21\",\"call_time\":\"1514707996\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"29\",\"call_time\":\"1514707859\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"23\",\"call_time\":\"1514634553\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"23\",\"call_time\":\"1514631480\",\"call_tel\":\"18635684535\"},{\"call_method\":\"2\",\"call_duration\":\"19\",\"call_time\":\"1514630948\",\"call_tel\":\"18635684535\"},{\"call_method\":\"1\",\"call_duration\":\"75\",\"call_time\":\"1514628553\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"52\",\"call_time\":\"1514627244\",\"call_tel\":\"18635684535\"},{\"call_method\":\"2\",\"call_duration\":\"79\",\"call_time\":\"1514616702\",\"call_tel\":\"18334673661\"},{\"call_method\":\"2\",\"call_duration\":\"85\",\"call_time\":\"1514609765\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"54\",\"call_time\":\"1514609134\",\"call_tel\":\"13233565462\"},{\"call_method\":\"2\",\"call_duration\":\"7\",\"call_time\":\"1514601492\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"39\",\"call_time\":\"1514598047\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"103\",\"call_time\":\"1514596084\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"51\",\"call_time\":\"1514553574\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"65\",\"call_time\":\"1514544393\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"91\",\"call_time\":\"1514436901\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"7\",\"call_time\":\"1514364304\",\"call_tel\":\"17744498820\"},{\"call_method\":\"2\",\"call_duration\":\"29\",\"call_time\":\"1514253744\",\"call_tel\":\"57126880046\"},{\"call_method\":\"2\",\"call_duration\":\"155\",\"call_time\":\"1514247097\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"38\",\"call_time\":\"1514116610\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"61\",\"call_time\":\"1514115372\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"121\",\"call_time\":\"1514079295\",\"call_tel\":\"8657041195\"},{\"call_method\":\"1\",\"call_duration\":\"62\",\"call_time\":\"1514029055\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"55\",\"call_time\":\"1513956156\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"63\",\"call_time\":\"1513955256\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"35\",\"call_time\":\"1513942077\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"62\",\"call_time\":\"1513852271\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"8\",\"call_time\":\"1513826821\",\"call_tel\":\"15810184568\"},{\"call_method\":\"2\",\"call_duration\":\"109\",\"call_time\":\"1513744935\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"11\",\"call_time\":\"1513742115\",\"call_tel\":\"13161343562\"},{\"call_method\":\"2\",\"call_duration\":\"14\",\"call_time\":\"1513737756\",\"call_tel\":\"17806385516\"},{\"call_method\":\"2\",\"call_duration\":\"113\",\"call_time\":\"1513727296\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"36\",\"call_time\":\"1513686816\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"14\",\"call_time\":\"1513652216\",\"call_tel\":\"15097969871\"},{\"call_method\":\"1\",\"call_duration\":\"75\",\"call_time\":\"1513570838\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"102\",\"call_time\":\"1513570714\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"29\",\"call_time\":\"1513515345\",\"call_tel\":\"13910195645\"},{\"call_method\":\"2\",\"call_duration\":\"72\",\"call_time\":\"1513325140\",\"call_tel\":\"75588648320\"},{\"call_method\":\"1\",\"call_duration\":\"15\",\"call_time\":\"1513148932\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"35\",\"call_time\":\"1513076744\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"27\",\"call_time\":\"1513051582\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"18\",\"call_time\":\"1512993875\",\"call_tel\":\"18610738908\"},{\"call_method\":\"1\",\"call_duration\":\"153\",\"call_time\":\"1512987290\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"8\",\"call_time\":\"1512870598\",\"call_tel\":\"17301319062\"},{\"call_method\":\"1\",\"call_duration\":\"120\",\"call_time\":\"1512804540\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"80\",\"call_time\":\"1512727855\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"1\",\"call_time\":\"1512608983\",\"call_tel\":\"15364868048\"},{\"call_method\":\"2\",\"call_duration\":\"23\",\"call_time\":\"1512549212\",\"call_tel\":\"17147073350\"},{\"call_method\":\"2\",\"call_duration\":\"22\",\"call_time\":\"1512475157\",\"call_tel\":\"17147073350\"},{\"call_method\":\"2\",\"call_duration\":\"9\",\"call_time\":\"1512378947\",\"call_tel\":\"9521290372\"},{\"call_method\":\"2\",\"call_duration\":\"460\",\"call_time\":\"1512261247\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"162\",\"call_time\":\"1512172539\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"35\",\"call_time\":\"1511933971\",\"call_tel\":\"1010010\"},{\"call_method\":\"2\",\"call_duration\":\"214\",\"call_time\":\"1511779535\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"13\",\"call_time\":\"1511753454\",\"call_tel\":\"13233565462\"},{\"call_method\":\"2\",\"call_duration\":\"447\",\"call_time\":\"1511652389\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"36\",\"call_time\":\"1511649765\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"67\",\"call_time\":\"1511615758\",\"call_tel\":\"15210115617\"},{\"call_method\":\"2\",\"call_duration\":\"11\",\"call_time\":\"1511573501\",\"call_tel\":\"17301319062\"},{\"call_method\":\"2\",\"call_duration\":\"51\",\"call_time\":\"1511535059\",\"call_tel\":\"18610640441\"},{\"call_method\":\"2\",\"call_duration\":\"45\",\"call_time\":\"1511522449\",\"call_tel\":\"18801732318\"},{\"call_method\":\"2\",\"call_duration\":\"13\",\"call_time\":\"1511522222\",\"call_tel\":\"18801732318\"},{\"call_method\":\"1\",\"call_duration\":\"11\",\"call_time\":\"1511521915\",\"call_tel\":\"18801732318\"},{\"call_method\":\"1\",\"call_duration\":\"270\",\"call_time\":\"1511518827\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"20\",\"call_time\":\"1511518738\",\"call_tel\":\"18801732318\"},{\"call_method\":\"2\",\"call_duration\":\"51\",\"call_time\":\"1511518512\",\"call_tel\":\"18801732318\"},{\"call_method\":\"1\",\"call_duration\":\"81\",\"call_time\":\"1511512766\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"384\",\"call_time\":\"1511434209\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"144\",\"call_time\":\"1511431439\",\"call_tel\":\"18801732318\"},{\"call_method\":\"2\",\"call_duration\":\"10\",\"call_time\":\"1511420683\",\"call_tel\":\"1086390682\"},{\"call_method\":\"2\",\"call_duration\":\"165\",\"call_time\":\"1511394587\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"278\",\"call_time\":\"1511308018\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"400\",\"call_time\":\"1511273506\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"628\",\"call_time\":\"1511272850\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"14\",\"call_time\":\"1511272798\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"8\",\"call_time\":\"1511270711\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"42\",\"call_time\":\"1511170450\",\"call_tel\":\"8653654186\"},{\"call_method\":\"2\",\"call_duration\":\"172\",\"call_time\":\"1511133805\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"360\",\"call_time\":\"1511088642\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"453\",\"call_time\":\"1510999237\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"290\",\"call_time\":\"1510875397\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"101\",\"call_time\":\"1510838079\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"190\",\"call_time\":\"1510804922\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"217\",\"call_time\":\"1510742774\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"50\",\"call_time\":\"1510742546\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"1181\",\"call_time\":\"1510671529\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"218\",\"call_time\":\"1510572239\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"143\",\"call_time\":\"1510448844\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"12\",\"call_time\":\"1510405629\",\"call_tel\":\"18610738908\"},{\"call_method\":\"1\",\"call_duration\":\"33\",\"call_time\":\"1510403026\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"1\",\"call_time\":\"1510403011\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"420\",\"call_time\":\"1510397399\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"15\",\"call_time\":\"1510315073\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"33\",\"call_time\":\"1510314906\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"79\",\"call_time\":\"1510143463\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"87\",\"call_time\":\"1510034940\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"10\",\"call_time\":\"1509940041\",\"call_tel\":\"15711469056\"},{\"call_method\":\"1\",\"call_duration\":\"1112\",\"call_time\":\"1509884371\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"103\",\"call_time\":\"1509870465\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"62\",\"call_time\":\"1509865116\",\"call_tel\":\"18335693754\"},{\"call_method\":\"1\",\"call_duration\":\"69\",\"call_time\":\"1509792843\",\"call_tel\":\"13233565462\"},{\"call_method\":\"1\",\"call_duration\":\"155\",\"call_time\":\"1509792622\",\"call_tel\":\"13233565462\"},{\"call_method\":\"1\",\"call_duration\":\"28\",\"call_time\":\"1509792491\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"409\",\"call_time\":\"1509791969\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"7\",\"call_time\":\"1509789283\",\"call_tel\":\"17611213031\"},{\"call_method\":\"1\",\"call_duration\":\"88\",\"call_time\":\"1509752852\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"129\",\"call_time\":\"1509708604\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"40\",\"call_time\":\"1509550568\",\"call_tel\":\"15110161767\"},{\"call_method\":\"2\",\"call_duration\":\"14\",\"call_time\":\"1509550178\",\"call_tel\":\"18610412475\"},{\"call_method\":\"1\",\"call_duration\":\"120\",\"call_time\":\"1509532214\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"53\",\"call_time\":\"1509530225\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"49\",\"call_time\":\"1509503020\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"36\",\"call_time\":\"1509501639\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"14\",\"call_time\":\"1509490625\",\"call_tel\":\"18334673661\"},{\"call_method\":\"2\",\"call_duration\":\"44\",\"call_time\":\"1509441303\",\"call_tel\":\"75561803580\"},{\"call_method\":\"2\",\"call_duration\":\"17\",\"call_time\":\"1509441156\",\"call_tel\":\"17611213031\"},{\"call_method\":\"1\",\"call_duration\":\"25\",\"call_time\":\"1509432762\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"583\",\"call_time\":\"1509423120\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"96\",\"call_time\":\"1509416330\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"200\",\"call_time\":\"1509408304\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"18\",\"call_time\":\"1509335377\",\"call_tel\":\"13381183370\"},{\"call_method\":\"1\",\"call_duration\":\"455\",\"call_time\":\"1509274680\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"28\",\"call_time\":\"1509251011\",\"call_tel\":\"18801732318\"},{\"call_method\":\"1\",\"call_duration\":\"323\",\"call_time\":\"1509164743\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"29\",\"call_time\":\"1509153461\",\"call_tel\":\"18910639751\"},{\"call_method\":\"2\",\"call_duration\":\"54\",\"call_time\":\"1509088222\",\"call_tel\":\"13521038236\"},{\"call_method\":\"2\",\"call_duration\":\"120\",\"call_time\":\"1509063678\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"76\",\"call_time\":\"1509014906\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"240\",\"call_time\":\"1508934379\",\"call_tel\":\"18610763948\"},{\"call_method\":\"2\",\"call_duration\":\"32\",\"call_time\":\"1508762713\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"641\",\"call_time\":\"1508669631\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"978\",\"call_time\":\"1508668489\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"41\",\"call_time\":\"1508647310\",\"call_tel\":\"18801732318\"},{\"call_method\":\"1\",\"call_duration\":\"482\",\"call_time\":\"1508065309\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"79\",\"call_time\":\"1508032561\",\"call_tel\":\"18335693754\"},{\"call_method\":\"1\",\"call_duration\":\"76\",\"call_time\":\"1507986392\",\"call_tel\":\"18335693754\"},{\"call_method\":\"1\",\"call_duration\":\"85\",\"call_time\":\"1507974745\",\"call_tel\":\"18335693754\"},{\"call_method\":\"2\",\"call_duration\":\"76\",\"call_time\":\"1507962847\",\"call_tel\":\"15810458271\"},{\"call_method\":\"1\",\"call_duration\":\"200\",\"call_time\":\"1507955557\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"161\",\"call_time\":\"1507782034\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"146\",\"call_time\":\"1507729080\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"17\",\"call_time\":\"1507719503\",\"call_tel\":\"18610738908\"},{\"call_method\":\"1\",\"call_duration\":\"21\",\"call_time\":\"1507676958\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"66\",\"call_time\":\"1507638267\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"220\",\"call_time\":\"1507637349\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"29\",\"call_time\":\"1507635402\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"46\",\"call_time\":\"1507629055\",\"call_tel\":\"13513567640\"},{\"call_method\":\"1\",\"call_duration\":\"45\",\"call_time\":\"1507623241\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"25\",\"call_time\":\"1507623204\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"40\",\"call_time\":\"1507622070\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"54\",\"call_time\":\"1507615591\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"4\",\"call_time\":\"1507539664\",\"call_tel\":\"17639991677\"},{\"call_method\":\"1\",\"call_duration\":\"129\",\"call_time\":\"1507458742\",\"call_tel\":\"13209873997\"},{\"call_method\":\"2\",\"call_duration\":\"13\",\"call_time\":\"1507453628\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"37\",\"call_time\":\"1507453409\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"15\",\"call_time\":\"1507453299\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"145\",\"call_time\":\"1507452412\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"43\",\"call_time\":\"1507451732\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"20\",\"call_time\":\"1507435641\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"9\",\"call_time\":\"1507431376\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"83\",\"call_time\":\"1507369887\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"84\",\"call_time\":\"1507279240\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"24\",\"call_time\":\"1507263770\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"42\",\"call_time\":\"1507263539\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"80\",\"call_time\":\"1507179358\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"12\",\"call_time\":\"1507172726\",\"call_tel\":\"13513567640\"},{\"call_method\":\"1\",\"call_duration\":\"57\",\"call_time\":\"1507171097\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"45\",\"call_time\":\"1507170950\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"36\",\"call_time\":\"1507170462\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"27\",\"call_time\":\"1507169763\",\"call_tel\":\"15735668600\"},{\"call_method\":\"2\",\"call_duration\":\"23\",\"call_time\":\"1507168476\",\"call_tel\":\"15735668600\"},{\"call_method\":\"1\",\"call_duration\":\"34\",\"call_time\":\"1507168013\",\"call_tel\":\"15003562621\"},{\"call_method\":\"2\",\"call_duration\":\"45\",\"call_time\":\"1507167931\",\"call_tel\":\"15735668600\"},{\"call_method\":\"2\",\"call_duration\":\"13\",\"call_time\":\"1507163489\",\"call_tel\":\"15735668600\"},{\"call_method\":\"1\",\"call_duration\":\"11\",\"call_time\":\"1507163274\",\"call_tel\":\"13233565462\"},{\"call_method\":\"2\",\"call_duration\":\"51\",\"call_time\":\"1507162715\",\"call_tel\":\"13513567640\"},{\"call_method\":\"2\",\"call_duration\":\"23\",\"call_time\":\"1507160647\",\"call_tel\":\"13513567640\"},{\"call_method\":\"2\",\"call_duration\":\"25\",\"call_time\":\"1507160607\",\"call_tel\":\"13513567640\"},{\"call_method\":\"2\",\"call_duration\":\"10\",\"call_time\":\"1507160251\",\"call_tel\":\"15603565150\"},{\"call_method\":\"1\",\"call_duration\":\"39\",\"call_time\":\"1507159869\",\"call_tel\":\"13834313799\"},{\"call_method\":\"2\",\"call_duration\":\"54\",\"call_time\":\"1507158710\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"31\",\"call_time\":\"1507158658\",\"call_tel\":\"15603565150\"},{\"call_method\":\"2\",\"call_duration\":\"52\",\"call_time\":\"1507158231\",\"call_tel\":\"15603565150\"},{\"call_method\":\"2\",\"call_duration\":\"520\",\"call_time\":\"1507117503\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"175\",\"call_time\":\"1507114349\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"56\",\"call_time\":\"1507107362\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"60\",\"call_time\":\"1507096883\",\"call_tel\":\"18734616725\"},{\"call_method\":\"1\",\"call_duration\":\"74\",\"call_time\":\"1507091555\",\"call_tel\":\"15534634975\"},{\"call_method\":\"1\",\"call_duration\":\"40\",\"call_time\":\"1507091031\",\"call_tel\":\"13209873997\"},{\"call_method\":\"1\",\"call_duration\":\"27\",\"call_time\":\"1507090819\",\"call_tel\":\"13233565462\"},{\"call_method\":\"1\",\"call_duration\":\"36\",\"call_time\":\"1507029222\",\"call_tel\":\"15534634975\"},{\"call_method\":\"1\",\"call_duration\":\"139\",\"call_time\":\"1507028597\",\"call_tel\":\"13233565462\"},{\"call_method\":\"2\",\"call_duration\":\"1\",\"call_time\":\"1507026514\",\"call_tel\":\"15534634975\"},{\"call_method\":\"2\",\"call_duration\":\"17\",\"call_time\":\"1507024096\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"38\",\"call_time\":\"1507022771\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"46\",\"call_time\":\"1507022497\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"39\",\"call_time\":\"1507004978\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"22\",\"call_time\":\"1507004785\",\"call_tel\":\"13233565462\"},{\"call_method\":\"1\",\"call_duration\":\"11\",\"call_time\":\"1507004376\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"61\",\"call_time\":\"1507003751\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"54\",\"call_time\":\"1507000744\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"39\",\"call_time\":\"1506997392\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"43\",\"call_time\":\"1506994435\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"55\",\"call_time\":\"1506990086\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"53\",\"call_time\":\"1506939300\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"28\",\"call_time\":\"1506924019\",\"call_tel\":\"15010755752\"},{\"call_method\":\"2\",\"call_duration\":\"117\",\"call_time\":\"1506919227\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"13\",\"call_time\":\"1506916486\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"10\",\"call_time\":\"1506901119\",\"call_tel\":\"15010755752\"},{\"call_method\":\"1\",\"call_duration\":\"6\",\"call_time\":\"1506855803\",\"call_tel\":\"13233565462\"}]}";
            byte[] body = bodyStr.getBytes(DEFAULT_PARAMS_ENCODING);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(body);
            out.close();
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
