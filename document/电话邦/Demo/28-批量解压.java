// Gzip 解压
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