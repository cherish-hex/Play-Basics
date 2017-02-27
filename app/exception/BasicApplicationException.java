package exception;

/**
 * Created by cherish.sham on 22/2/2017.
 */
public class BasicApplicationException extends RuntimeException{
    private String message;
    private String code;

    public BasicApplicationException(String message, String code){
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
