package citylife.signtool.test;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.esotericsoftware.minlog.Log;

import citylife.signtool.config.SecretProperties;
import citylife.signtool.mode.SignUtils;
import citylife.signtool.mode.VerifyUtils;

public class TestSign {

  public static void main(String[] args) throws Exception {
    sha256withrsa();
  }

  private static void sha256withrsa() throws Exception {
    String data = JSONObject.toJSONString(TestData.JSON_RAW);
    String timestamp = new Date().getTime() + "";
    String newStr = "timestamp=" + timestamp + ",data=" + data;
    byte[] signBytes = SignUtils.sign(newStr);
    String sign = new String(signBytes);
    System.out.println("Verify:" + VerifyUtils.verifySign(newStr, sign, VerifyUtils.readPublicKey(SecretProperties.PUBLIC_KEY)));
  }

}
