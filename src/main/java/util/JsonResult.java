package util;

/**
 * Create by czq
 * time on 2019/8/21  15:20
 */
public class JsonResult<T> {

    private int code;

    private String message;
    private T data;

    public JsonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> JsonResult<T> success(String msg, T data) {
        return new JsonResult<>(200, msg, data);
    }


    public static void main(String[] args) {
        JsonResult.success("666", "123");
    }
}
