package citylife.signtool.mode;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import citylife.signtool.config.SecretProperties;

public class SignUtils {

  private static JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter();

  /**
   * 
   * @param keyText
   * @return
   * @throws IOException
   */
  public static PrivateKey readPrivateKey(String keyText) throws IOException {
    return readPrivateKey(new ByteArrayInputStream(keyText.getBytes("UTF-8")));
  }

  /**
   * 根据key字符串获取公钥
   * 
   * @param keyTextStream key pem字符串
   * @return {@link PublicKey}
   * @throws IOException 异常
   */
  public static PrivateKey readPrivateKey(InputStream keyTextStream) throws IOException {
    PEMParser pemParser = new PEMParser(new InputStreamReader(keyTextStream));

    try {
      PrivateKey privateKey = null;
      Object keyObj = pemParser.readObject();
      if (keyObj instanceof PrivateKeyInfo) {
        PrivateKeyInfo kp = (PrivateKeyInfo) keyObj;

        privateKey = keyConverter.getPrivateKey(kp);
      } else {
        throw new IOException("unrecognised private key file"); // TODO: handle encrypted private keys
      }
      return privateKey;

    } finally {
      IOUtils.closeQuietly(pemParser);
    }
  }

  /**
   * 加签
   * 
   * @param plainText 平文
   * @param privateKey 公钥
   * @return 签名
   */
  public static byte[] sign(String plainText, PrivateKey privateKey) {

    try {
      Signature privateSignature = Signature.getInstance("SHA256withRSA");
      privateSignature.initSign(privateKey);
      privateSignature.update(plainText.getBytes(StandardCharsets.UTF_8.toString()));
      byte[] signatureBytes = Base64.getEncoder().encode(privateSignature.sign());
      return signatureBytes;
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  /**
   * 加签
   * 
   * @param plainText 平文
   * @return 签名
   */
  public static byte[] sign(String plainText) {

    try {
      privateSignature.update(plainText.getBytes(StandardCharsets.UTF_8.toString()));
      byte[] signatureBytes = Base64.getEncoder().encode(privateSignature.sign());
      return signatureBytes;
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  public static Signature privateSignature = getPrivateSignature();

  public static Signature getPrivateSignature() {
      PrivateKey privateKey;
      Signature privateSignature = null;
      try {
        privateKey = readPrivateKey(SecretProperties.PRIVATE_KEY);
        privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
      } catch (Exception e) {
        e.printStackTrace();
      }

      return privateSignature;
  }

}