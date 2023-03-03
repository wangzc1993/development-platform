package org.wangzc.development.platform.util;

import org.nutz.lang.util.NutMap;

public class Result extends NutMap {

    private Result() {

    }

    public static Result ok(String msg) {
        Result result = new Result();
        result.put("code", 1);
        result.put("msg", msg);
        return result;
    }

    public static Result ok() {
        return ok("成功");
    }

    public static Result fail() {
        return fail("失败");
    }

    public static Result okResult(Object data) {
        Result result = Result.ok();
        result.put("data", data);
        return result;
    }

    public static Result fail(String msg) {
        return fail(msg, "");
    }

    public static Result fail(String msg, Throwable error) {
        return fail(msg, ExceptionUtils.toString(error));
    }

    public static Result fail(String msg, String errorInfo) {
        Result result = new Result();
        result.put("code", 0);
        result.put("msg", msg);
        result.put("errorInfo", errorInfo);
        return result;
    }
}
