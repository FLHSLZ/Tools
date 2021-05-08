package citylife.signtool.entity;

import java.io.Serializable;

/**
 * 加签结果
 * @author zheng.liu
 *
 */
public class SignResult implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /** 签 */
  private String sign;
  
  /** 时间戳 */
  private String timestamp;

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

}
