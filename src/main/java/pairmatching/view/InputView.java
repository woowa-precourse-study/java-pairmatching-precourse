package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String _REQUEST = "";

    public static String read() {
        System.out.println(_REQUEST);
        return Console.readLine();
    }

    public static String readMenuSelection() {
        System.out.println("");
        return Console.readLine();
    }
}
