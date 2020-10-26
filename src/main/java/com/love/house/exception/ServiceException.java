package com.love.house.exception;

/**
 * @Author: wy
 * @Date: 2020/10/26 16:21
 */
public class ServiceException extends RuntimeException {
    /**
     * spring事物只接受运行期异常回滚
     */
    private static final long serialVersionUID = 1L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
