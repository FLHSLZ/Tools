package citylife.signtool.mode;

import java.util.Date;

import citylife.signtool.entity.SignResult;

/**
 * SHA256withRSA
 * @author zheng.liu
 *
 */
public class MySHA256withRSA {
  /**
   * 加签
   * @param data 原始数据
   * @return 签
   */
  public SignResult sign(String data) {
    SignResult rst = new SignResult();
    
    String timestamp = new Date().getTime() + "";
    String newStr = "timestamp=" + timestamp + ",data=" + data;
    byte[] sign = SignUtils.sign(newStr);
    
    rst.setSign(new String(sign));
    rst.setTimestamp(timestamp);
    
    return rst;
  }
}
