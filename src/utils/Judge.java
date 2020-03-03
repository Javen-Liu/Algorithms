package utils;

/**
 * @author 刘建雯
 * 用于测试的判断类，如果符合条件则返回"pass"，若不符合条件则返回"fail"
 */
public class Judge {
    private static final String P = "pass";
    private static final String F = "fail";
    public static final String EQUALS = "equals";
    public static final String UNEQUALS = "unequals";
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    public static String judge(Object src, Object tar, String judgeType){
        if(EQUALS.equals(judgeType)){
            return src == tar ? P : F;
        }else if(UNEQUALS.equals(judgeType)){
            return src != tar ? P : F;
        }else{
            throw new UnsupportedOperationException("输入的操作字符有误");
        }
    }

    public static String judge(Object src, String judgeType){
        return String.valueOf(src).equals(judgeType) ? P : F;
    }
}
