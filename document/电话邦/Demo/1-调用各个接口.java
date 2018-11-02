    /**
     * 获取接口调用凭证
     * <p>
     * 示例返回：
     * {"status":0,"msg":"OK","data":{"token":"2cdce4b804a8152eeddce41065d2536d","expires":"2018-07-31 23:59:59"}}
     *
     * @param appid 系统签发的 appid
     * @param sign  数据签名
     * @param time  当前 unix 时间戳(秒级)
     * @return
     * @throws IOException
     */
    public String getToken(String appid, String sign, String time) throws IOException {
        if (isEmpty(appid) || isEmpty(sign) || isEmpty(time)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/token?appid=" + appid + "&sign=" + sign + "&time=" + time;
        String result = get(url);
        System.out.println("getToken: " + result);
        return result;
    }

    /**
     * 获取接口调用凭证 sign 签名生成算法
     *
     * @param input 签名字符串
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] output = messageDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        String temp;
        for (byte aB : output) {
            temp = (Integer.toHexString(aB & 0XFF));
            if (temp.length() == 1) {
                sb.append("0").append(temp);
            } else {
                sb.append(temp);
            }
        }
        return sb.toString();
    }

    /**
     * 获取登录方式
     * <p>
     * 示例返回：
     * {"status":0,"msg":"OK","data":{"sid":"SID40d1cb1d4ea54fcbab17ca4cef0e954f","need_full_name":0,"need_id_card":0,"need_pin_pwd":1,"sms_duration":120,"captcha_image":"","verify_notes":""},"action":"processing"}
     *
     * @param token                接口调用凭证
     * @param tel                  用户手机号码
     * @param uid                  用户唯一标识
     * @param user_info            个人信息
     * @param user_name            用户姓名
     * @param user_idcard          身份证号码
     * @param user_province        省份
     * @param user_city            城市
     * @param user_address         详细地址
     * @param user_contact         紧急联系人信息
     * @param contact_tel          紧急联系电话
     * @param contact_name         紧急联系人姓名
     * @param contact_relationship 客户与紧急联系人的关系
     * @param contact_priority     紧急联系人优先级
     * @return
     * @throws IOException
     */
    public String flow(String token, String tel, String uid, String user_info, String user_name, String user_idcard,
                       String user_province, String user_city, String user_address, String[] user_contact, String contact_tel,
                       String contact_name, String contact_relationship, int contact_priority) throws IOException {
        if (isEmpty(token) || isEmpty(tel)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/calls/flow?token=" + token;
        HashMap<String, String> postParams = new HashMap<>();
        postParams.put("tel", tel);
        postParams.put("uid", uid);
        postParams.put("user_info", user_info);
        postParams.put("user_name", user_name);
        postParams.put("user_idcard", user_idcard);
        postParams.put("user_province", user_province);
        postParams.put("user_city", user_city);
        postParams.put("user_address", user_address);
        postParams.put("user_contact", Arrays.toString(user_contact));
        postParams.put("contact_tel", contact_tel);
        postParams.put("contact_name", contact_name);
        postParams.put("contact_relationship", contact_relationship);
        postParams.put("contact_priority", String.valueOf(contact_priority));
        String result = post(url, postParams);
        System.out.println("flow: " + result);
        return result;
    }

    /**
     * 刷新图形验证码
     *
     * @param token         接口调用凭证
     * @param captcha_image base64 图片文件
     * @return
     * @throws IOException
     */
    public String captcha(String token, String captcha_image) throws IOException {
        if (isEmpty(token) || isEmpty(captcha_image)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/calls/verify/captcha?token=" + token + "&captcha_image=" + captcha_image;
        String result = get(url);
        System.out.println("captcha: " + result);
        return result;
    }

    /**
     * 登录
     * <p>
     * 示例返回：
     * {"status":0,"msg":"OK","data":{"sms_duration":"","captcha_image":"","verify_notes":""},"action":"done"}
     *
     * @param token        接口调用凭证
     * @param sid          会话标识
     * @param tel          查询手机号
     * @param pin_pwd      手机服务密码
     * @param full_name    姓名
     * @param id_card      身份证号码
     * @param sms_code     短信验证码
     * @param captcha_code 图形验证码
     * @return
     * @throws IOException
     */
    public String login(String token, String sid, String tel, String pin_pwd, String full_name, String id_card,
                        String sms_code, String captcha_code) throws IOException {
        if (isEmpty(token) || isEmpty(sid) || isEmpty(tel)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/calls/login?token=" + token;
        HashMap<String, String> postParams = new HashMap<>();
        postParams.put("sid", sid);
        postParams.put("tel", tel);
        postParams.put("pin_pwd", pin_pwd);
        postParams.put("full_name", full_name);
        postParams.put("id_card", id_card);
        postParams.put("sms_code", sms_code);
        postParams.put("captcha_code", captcha_code);
        String result = post(url, postParams);
        System.out.println("login: " + result);
        return result;
    }

    /**
     * 登录后二次验证
     * <p>
     * 示例返回：
     * {"status":0,"action":"processing","data":{"sms_duration":"","captcha_image":"","content":""}}
     *
     * @param token        接口调用凭证
     * @param sid          会话标识
     * @param sms_code     短信验证码
     * @param captcha_code 图形验证码
     * @return
     * @throws IOException
     */
    public String verify(String token, String sid, String sms_code, String captcha_code) throws IOException {
        if (isEmpty(token) || isEmpty(sid)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/calls/verify?token=" + token;
        HashMap<String, String> postParams = new HashMap<>();
        postParams.put("sid", sid);
        postParams.put("sms_code", sms_code);
        postParams.put("captcha_code", captcha_code);
        String result = post(url, postParams);
        System.out.println("verify: " + result);
        return result;
    }

    /**
     * 找回密码
     * <p>
     * 示例返回：
     * {"status":0,"msg":"OK","data":{"tid":"TID15118615280091125525","need_new_pwd":0,"need_contacts":0,"login_sms_duration":"","sms_duration":120,"content":""},"action":"processing"}
     *
     * @param token     接口调用凭证
     * @param tel       手机号
     * @param user_name 姓名
     * @param user_id   身份证
     * @return
     * @throws IOException
     */
    public String forgetPwd(String token, String tel, String user_name, String user_id) throws IOException {
        if (isEmpty(token) || isEmpty(tel) || isEmpty(user_name) || isEmpty(user_id)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/calls/verify?token=" + token;
        HashMap<String, String> postParams = new HashMap<>();
        postParams.put("tel", tel);
        postParams.put("user_name", user_name);
        postParams.put("user_id", user_id);
        String result = post(url, postParams);
        System.out.println("forgetPwd: " + result);
        return result;
    }

    /**
     * 找回密码短信校验
     * <p>
     * 示例返回：
     * {"status":0,"msg":"OK","data":{"tid":"TID15118615280091125525","need_new_pwd":1,"need_contacts":0,"login_sms_duration":"","sms_duration":"","content":""},"action":"processing"}
     *
     * @param token    接口调用凭证
     * @param tid      找回密码唯一标识
     * @param sms_code 短信验证码
     * @return
     * @throws IOException
     */
    public String forgetPwdVerify(String token, String tid, String sms_code) throws IOException {
        if (isEmpty(token) || isEmpty(tid) || isEmpty(sms_code)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/forget/pwd?token=" + token;
        HashMap<String, String> postParams = new HashMap<>();
        postParams.put("tid", tid);
        postParams.put("sms_code", sms_code);
        String result = post(url, postParams);
        System.out.println("forgetPwdVerify: " + result);
        return result;
    }

    /**
     * 找回密码登录校验
     * <p>
     * 示例返回：
     * {"status":0,"msg":"OK","data":{"tid":"TID15118615280091125525","need_new_pwd":0,"need_contacts":1,"login_sms_duration":"","sms_duration":"","content":""},"action":"processing"}
     *
     * @param token      接口调用凭证
     * @param tid        找回密码唯一标识
     * @param login_code 登录短信验证码
     * @return
     * @throws IOException
     */
    public String forgetPwdLogin(String token, String tid, String login_code) throws IOException {
        if (isEmpty(token) || isEmpty(tid) || isEmpty(login_code)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/forget/pwd?token=" + token;
        HashMap<String, String> postParams = new HashMap<>();
        postParams.put("tid", tid);
        postParams.put("login_code", login_code);
        String result = post(url, postParams);
        System.out.println("forgetPwdLogin: " + result);
        return result;
    }

    /**
     * 联系人通话记录校验
     * <p>
     * 示例返回：
     * {"status":0,"msg":"OK","data":{"tid":"TID15118615280091125525","need_new_pwd":1,"need_contacts":0,"login_sms_duration":"","sms_duration":"","content":""},"action":" processing"}
     *
     * @param token    接口调用凭证
     * @param tid      找回密码唯一标识
     * @param tel_list 联系人手机号
     * @return
     * @throws IOException
     */
    public String forgetPwdRecordVerify(String token, String tid, String[] tel_list) throws IOException {
        if (isEmpty(token) || isEmpty(tid)) {
            throw new IllegalArgumentException();
        }
        if (tel_list == null || tel_list.length == 0) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/forget/pwd?token=" + token;
        HashMap<String, String> postParams = new HashMap<>();
        postParams.put("tid", tid);
        postParams.put("tel_list", Arrays.toString(tel_list));
        String result = post(url, postParams);
        System.out.println("forgetPwdRecordVerify: " + result);
        return result;
    }

    /**
     * 设置新服务密码
     * <p>
     * 示例返回：
     * {"status":0,"msg":"OK","data":{"tid":"TID15118615280091125525","need_new_pwd":0,"need_contacts":0,"login_sms_duration":"","sms_duration":"","content":""},"action":"done"}
     *
     * @param token   接口调用凭证
     * @param tid     找回密码唯一标识
     * @param new_pwd 新服务密码
     * @return
     * @throws IOException
     */
    public String forgetPwdReset(String token, String tid, String new_pwd) throws IOException {
        if (isEmpty(token) || isEmpty(tid) || isEmpty(new_pwd)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/forget/pwd?token=" + token;
        HashMap<String, String> postParams = new HashMap<>();
        postParams.put("tid", tid);
        postParams.put("new_pwd", new_pwd);
        String result = post(url, postParams);
        System.out.println("forgetPwdReset: " + result);
        return result;
    }

    /**
     * 详单列表
     * <p>
     * 示例返回：
     * {"status":0,"msg":"OK","data":{"tel":"18511071359","uid":"","sid":"SID779c0beeb9eb46b2b5fc70a238acff69","call_log_missing_month_list":[],"call_log_possibly_missing_month_list":[],"phone_bill_missing_month_list":[],"call_log":[{"call_method":"被叫","call_from":"北京","call_cost":"0.00","call_duration":"7","call_time":"1511428078","call_tel":"18709257665","call_to":"陕西西安","call_type":"国内通话"}],"tel_info":{"id_card":"1407****003X","full_name":"青培","address":"中国大陆西大街18-3号","open_date":"1494388800"},"bill":[{"bill_amount":"8.55","bill_zengzhifei":"0.00","bill_qita":"0.00","bill_package":"6.00","bill_ext_sms":"0.00","bill_daishoufei":"0.00","bill_ext_data":"0.00","bill_month":"201711","bill_ext_calls":"2.55"},{"bill_amount":"6.00","bill_zengzhifei":"0.00","bill_qita":"0.00","bill_package":"6.00","bill_ext_sms":"0.00","bill_daishoufei":"0.00","bill_ext_data":"0.00","bill_month":"201710","bill_ext_calls":"0.00"}]}}
     *
     * @param token 接口调用凭证
     * @param sid   会话标识
     * @return
     * @throws IOException
     */
    public String getRecord(String token, String sid) throws IOException {
        if (isEmpty(token) || isEmpty(sid)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/calls/record?token=" + token + "&sid=" + sid;
        String result = get(url);
        System.out.println("getRecord: " + result);
        return result;
    }

    /**
     * 详单报告
     *
     * @param token 接口调用凭证
     * @param sid   会话标识
     * @return
     * @throws IOException
     */
    public String getReport(String token, String sid) throws IOException {
        if (isEmpty(token) || isEmpty(sid)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/calls/report?token=" + token + "&sid=" + sid;
        String result = get(url);
        System.out.println("getReport: " + result);
        return result;
    }

    /**
     * 拉取异步调用催收分结果集
     *
     * @param token 接口调用凭证
     * @param sid   催收分唯一标识
     * @return
     * @throws IOException
     */
    public String getCuiShou(String token, String sid) throws IOException {
        if (isEmpty(token) || isEmpty(sid)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/cuishou?token=" + token + "&sid=" + sid;
        String result = get(url);
        System.out.println("getCuiShou: " + result);
        return result;
    }

    /**
     * 拉取异步调用邦秒配结果集
     *
     * @param token 接口调用凭证
     * @param sid   邦秒爬唯一标识
     * @return
     * @throws IOException
     */
    public String getITag(String token, String sid) throws IOException {
        if (isEmpty(token) || isEmpty(sid)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/itag?token=" + token + "&sid=" + sid;
        String result = get(url);
        System.out.println("getITag: " + result);
        return result;
    }

    /**
     * 工具函数，判断字符串是否为空
     *
     * @param source 字符串
     * @return 空返回 true，非空为 false
     * @throws IOException
     */
    public boolean isEmpty(String source) {
        return source == null || source.isEmpty();
    }

    /**
     * 工具函数，get 请求
     *
     * @param link 请求链接
     * @return 请求结果
     * @throws IOException
     */
    public String get(String link) throws IOException {
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

    /**
     * 工具函数，post 请求
     *
     * @param link 请求链接
     * @param postDataParams 请求参数
     * @return 请求结果
     * @throws IOException
     */
    public String post(String link, HashMap<String, String> postDataParams) throws IOException {
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
            if (!(postDataParams == null || postDataParams.isEmpty())) {
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(getParamsString(postDataParams));
            }
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

    /**
     * 工具函数，map 转换 String
     *
     * @param params 要转换的 map
     * @return 转换结果
     * @throws IOException
     */
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

    /**
     * 工具函数，输入流转字节数组
     *
     * @param from 输入流
     * @return 转换结果
     * @throws IOException
     */
    private byte[] toBytes(InputStream from) throws IOException {
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
