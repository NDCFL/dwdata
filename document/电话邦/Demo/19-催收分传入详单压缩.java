/**
 * @str　待压缩字符
 * gzip 压缩方式
 */
public static byte[] compresszip(String str) throws IOException {
   if (str == null || str.length() == 0) {
    System.out.print("null----");
  }
   ByteArrayOutputStream out = new ByteArrayOutputStream();
  GZIPOutputStream gzip = new GZIPOutputStream(out);
   gzip.write(str.getBytes());
   gzip.close();
  return out.toByteArray();
}

// 另外注意：
urlConnection.setRequestProperty("Content-Encoding", "gzip");  //gzip压缩
urlConnection.setRequestProperty("Content-Encoding", "deflate"); //zlib压缩
urlConnection.setRequestProperty("Content-Encoding", "deflate-raw");
