package cn.uestc.pojo;

/**
 * 封装向前端返回转账的状态吗
 */
public class AccountResult {

    public static final String SUCCESS="0";
    public static final String ERROR="1";

    private String state;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
