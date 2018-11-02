// Gzip压缩
public static byte[] compress(byte[] data) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    GZIPOutputStream gzip;
    gzip = new GZIPOutputStream(out);
    gzip.write(data);
    gzip.close();
    return out.toByteArray();
}