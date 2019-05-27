package ladder.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner input = new Scanner(System.in);
    private static final String SPLITTER = ",";

    public static List<String> inputNames() {
        System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
        return filterTypos(input.nextLine());
    }

    public static List<String> inputRewards() {
        System.out.println("실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)");
        return filterTypos(input.nextLine());
    }

    public static List<String> inputResultNames() {
        System.out.println("결과를 보고 싶은 사람은?");
        return filterTypos(input.nextLine());
    }

    private static List<String> filterTypos(String arg) {
        return new ArrayList<>(
                Arrays.stream(arg.split(SPLITTER))
                        .map(x -> x.trim())
                        .filter(x -> (!x.equals("")) && (!x.equals(" ")) && (!x.equals(SPLITTER)))
                        .collect(Collectors.toList())
        );
    }

    public static int inputLadderHeight() throws IllegalArgumentException {
        System.out.println("최대 사다리 높이는 몇 개인가요?");
        return Integer.parseInt(input.nextLine().trim());
    }
}

