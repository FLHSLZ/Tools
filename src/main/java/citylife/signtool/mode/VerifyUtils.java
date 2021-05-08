package citylife.signtool.mode;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.slf4j.impl.Log4jLoggerAdapter;

import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.minlog.Log.Logger;

public class VerifyUtils {

  private static JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter();

  /**
   * 
   * @param keyText
   * @return
   * @throws IOException
   */
  public static PublicKey readPublicKey(String keyText) throws IOException {
    return readPublicKey(new ByteArrayInputStream(keyText.getBytes()));
  }

  /**
   * 根据key字符串获取公钥
   * 
   * @param keyTextStream key pem字符串
   * @return {@link PublicKey}
   * @throws IOException 异常
   */
  public static PublicKey readPublicKey(InputStream keyTextStream) throws IOException {
    PEMParser pemParser = new PEMParser(new InputStreamReader(keyTextStream));

    try {
      PublicKey publicKey = null;
      Object keyObj = pemParser.readObject();
      if (keyObj instanceof PEMKeyPair) {
        PEMKeyPair kp = (PEMKeyPair) keyObj;

        publicKey = keyConverter.getPublicKey(kp.getPublicKeyInfo());
      } else if (keyObj instanceof SubjectPublicKeyInfo) {
        publicKey = keyConverter.getPublicKey((SubjectPublicKeyInfo) keyObj);
      } else {
        throw new IOException("unrecognised private key file"); // TODO: handle encrypted private keys
      }
      return publicKey;

    }finally {
      IOUtils.closeQuietly(pemParser);
    }
  }

  /**
   * 根据公钥验证签名和平文的正确性
   * 
   * @param plainText 平文
   * @param signature 签名
   * @param publicKey 公钥
   * @return 签名正确
   */
  public static boolean verifySign(String plainText, String signature, PublicKey publicKey) {

    try {
      System.out.println(plainText);
      System.out.println(signature);
      Signature publicSignature = Signature.getInstance("SHA256withRSA");
      publicSignature.initVerify(publicKey);
      publicSignature.update(plainText.getBytes(StandardCharsets.UTF_8.toString()));
      byte[] signatureBytes = Base64.getDecoder().decode(signature);
      return publicSignature.verify(signatureBytes);
    } catch (Exception e) {
      return false;
    }

  }

}