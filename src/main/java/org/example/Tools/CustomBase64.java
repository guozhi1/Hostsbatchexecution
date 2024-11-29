package org.example.Tools;

public class CustomBase64 {

    // 自定义的Base64字符集（魔改版）
    private static final String BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final int PAD = '=';

    // 编码
    public static String encode(byte[] data) {
        StringBuilder encoded = new StringBuilder();

        // 对输入字节数组进行编码
        int paddingCount = (3 - data.length % 3) % 3;
        int length = data.length + paddingCount;

        for (int i = 0; i < length; i += 3) {
            int b1 = (i < data.length) ? (data[i] & 0xFF) : 0;
            int b2 = (i + 1 < data.length) ? (data[i + 1] & 0xFF) : 0;
            int b3 = (i + 2 < data.length) ? (data[i + 2] & 0xFF) : 0;

            int combined = (b1 << 16) | (b2 << 8) | b3;

            // 将三个字节合并为四个Base64字符
            encoded.append(BASE64_ALPHABET.charAt((combined >> 18) & 0x3F));
            encoded.append(BASE64_ALPHABET.charAt((combined >> 12) & 0x3F));
            encoded.append(i + 1 < data.length ? BASE64_ALPHABET.charAt((combined >> 6) & 0x3F) : PAD);
            encoded.append(i + 2 < data.length ? BASE64_ALPHABET.charAt(combined & 0x3F) : PAD);
        }

        return encoded.toString();
    }

    // 解码
    public static String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();

        // 将Base64字符串转换为字节数组
        int length = encoded.length();
        int paddingCount = 0;
        if (encoded.charAt(length - 1) == PAD) paddingCount++;
        if (encoded.charAt(length - 2) == PAD) paddingCount++;

        int dataLength = (length * 6 / 8) - paddingCount;

        byte[] data = new byte[dataLength];
        int index = 0;

        for (int i = 0; i < length; i += 4) {
            int combined = (BASE64_ALPHABET.indexOf(encoded.charAt(i)) << 18)
                    | (BASE64_ALPHABET.indexOf(encoded.charAt(i + 1)) << 12)
                    | (BASE64_ALPHABET.indexOf(encoded.charAt(i + 2)) << 6)
                    | BASE64_ALPHABET.indexOf(encoded.charAt(i + 3));

            // 提取并填充解码后的字节数组
            if (index < data.length) data[index++] = (byte) ((combined >> 16) & 0xFF);
            if (index < data.length) data[index++] = (byte) ((combined >> 8) & 0xFF);
            if (index < data.length) data[index++] = (byte) (combined & 0xFF);
        }

        return data.toString();
    }

}