package com.dwsj.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GsonUtils {
	   private static final Gson mGson;

	    static {
	    	//当属性值为null的时候不会生成json的key
	    	mGson = new Gson();
	    	//当属性值为null的时候json的key会存在，但是key对应的value为""
	    	/*mGson = new GsonBuilder()
	    	        .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
	    	        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
	    	        .create();*/
	    }

	    @SuppressWarnings("unused")
		private void GsonUtils() {
	        throw new UnsupportedOperationException("This class unsupport create instance");
	    }

	    /**
	     * 转化成Json字符串
	     * @param object
	     * @return
	     */
	    public static String translateToJson(Object object) {
	        return mGson.toJson(object);
	    }

	    /**
	     * 解析Json字符串成实体类
	     * @param json
	     * @param cla
	     * @param <T>
	     * @return
	     */
	    public static <T> T parserJson(String json, Class<T> cla) {
	        Gson gson = mGson;
	        T t = null;
	        try {
	            t = gson.fromJson(json, cla);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return t;
	    }
	    
	    /**
	     * 解析Json字符串成实体类,时间字符串会自动解析
	     * @param json json字符串
	     * @param clazz 要转换的实体类类型
	     * @param dateFormat 时间格式  eg:"yyyy-MM-dd HH:mm"
	     * @return
	     */
	    public static <T> T JsonStrToBeanIncludeDate(String json, Class<T> clazz, String dateFormat) {
			Gson gson = new GsonBuilder()
	        .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
	        .setDateFormat(dateFormat)
	        .create();
	        T t = null;
	        try {
	            t = gson.fromJson(json, clazz);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return t;
	    }

	    /**
	     * json转List
	     * @param json
	     * @param cla
	     * @return
	     */
	    public static <T> List<T> listJson(String json, Class<T> cla) {
	        List<T> list = new ArrayList<T>();
	        Gson gson = mGson;
	        try {
	            JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
	            T t = null;
	            for (JsonElement jsonElement : jsonArray) {
	                t = gson.fromJson(jsonElement, cla);
	                list.add(t);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	    public static <T> List<T> listParseAllJson(String json, Class<T> cla) {
	        List<T> list = null;
	        try {
	            list = GsonUtils.mGson.fromJson(json, new TypeToken<List<T>>() {
	            }.getType());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }


	    public static <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
	        Gson mGson = GsonUtils.mGson;
	        ArrayList<T> mList = new ArrayList<T>();
	        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
	        for (final JsonElement elem : array) {
	            mList.add(mGson.fromJson(elem, cls));
	        }
	        return mList;
	    }

	    public static Gson getGson() {
		return GsonUtils.mGson;
	    }
	}


