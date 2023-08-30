package io.github.viacheslav.bondarchuk.kafkamirror.util;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.text.MessageFormat;
import java.util.Base64.Decoder;

public final class JKSUtil {
    private JKSUtil() {}

    public static String load(String name, String password, String path, String base64Content, Decoder decoder) {
        String location = MessageFormat.format("{0}/{1}", path, name);
        try (FileOutputStream fileOutputStream = new FileOutputStream(location)) {
            byte[] bytes = decoder.decode(base64Content);
            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(new ByteArrayInputStream(bytes), password.toCharArray());
            keystore.store(fileOutputStream, password.toCharArray());
        } catch (Exception ex) {
            throw new RuntimeException("Can not load to JKS: ", ex);
        }
        return location;
    }
}
