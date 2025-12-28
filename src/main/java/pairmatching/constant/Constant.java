//package pairmatching.constant;
//
//import java.util.Arrays;
//import java.util.List;
//
//public enum Constant {
//
//    NAME(1, "", List.of()),
//    ;
//
//    private final int x1;
//    private final String x2;
//    private final List<String> x3;
//
//    Constant(int x1, String x2, List<String> x3) {
//        this.x1 = x1;
//        this.x2 = x2;
//        this.x3 = x3;
//    }
//
//    public static Constant fromX1(int x1) {
//        return Arrays.stream(values())
//                .filter(constant -> constant.x1 == x1)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage..getErrorMessage()));
//    }
//
//    public static Constant fromX2(String x2) {
//        return Arrays.stream(values())
//                .filter(constant -> constant.x2.equals(x2))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage..getErrorMessage()));
//    }
//
//    public int getX1() {
//        return x1;
//    }
//
//    public String getX2() {
//        return x2;
//    }
//
//    public List<String> getX3() {
//        return x3;
//    }
//}
