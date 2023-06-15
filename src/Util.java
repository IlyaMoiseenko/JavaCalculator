public class Util {
    public static String[] CreateActions() {
        return new String[] {"+", "-", "/", "*"};
    }

    public static String[] CreateRegexActions() {
        return new String[] {"\\+", "-", "/", "\\*"};
    }

    public static int ArithmeticAction(String[] actions, String exp) {
        int actionIndex = -1;

        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])){
                actionIndex = i;
                break;
            }
        }

        return actionIndex;
    }

    public static boolean CheckNumberOfOperands(String[] data) {
        return data.length != 2;
    }

    public static boolean CheckNumbers(int a, int b) {
        return a < 1 || b > 10;
    }
}
