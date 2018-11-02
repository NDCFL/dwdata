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
