package citylife.signtool.test;

import java.io.Serializable;

public class TestData implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String field1 = "abc";

  private int field2 = 123;

  public String getField1() {
    return field1;
  }

  public void setField1(String field1) {
    this.field1 = field1;
  }

  public int getField2() {
    return field2;
  }

  public void setField2(int field2) {
    this.field2 = field2;
  }
  
  public static final String JSON_RAW = 
    "{\r\n" + 
    "    \"data\": {\r\n" + 
    "        \"password\": \"c1234567\",\r\n" + 
    "        \"mobile\": \"13520797752\",\r\n" + 
    "        \"authType\": \"username\",\r\n" + 
    "        \"platform\": \"\"\r\n" + 
    "    },\r\n" + 
    "    \"common\": {\r\n" + 
    "        \"systemVersion\": \"12.4\",\r\n" + 
    "        \"mobileEquip\":\"fbd435d881d3ab63\",\r\n" + 
    "        \"latitude\": \"39.915308\",\r\n" + 
    "        \"appVersion\": \"1.2.3\",\r\n" + 
    "        \"mobileSystem\": \"iOS\",\r\n" + 
    "        \"longitude\": \"116.436009\"\r\n" + 
    "    }\r\n" + 
    "}";

}
