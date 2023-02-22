package laddergame.view;

import laddergame.constant.ErrorCode;
import laddergame.dto.GameResult;
import laddergame.domain.Line;
import laddergame.domain.Point;

import java.util.List;

public class OutputView {

    private static final int LADDER_SPACING = 5;
    private static final int LABEL_SPACING = LADDER_SPACING + 1;
    private static final String KOREAN_LANGUAGE_CODE = "kor";

    private static final String PLAYER_NAMES_REQUEST_MSG = "참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요) ";
    private static final String LADDER_PRIZE_REQUEST_MSG = "실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)";
    private static final String LADDER_HEIGHT_MSG = "최대 사다리 높이는 몇 개인가요?";
    private static final String LADDER_GENERATED_INFO_MSG = "사다리 결과";
    private static final String RESULT_SHOW_PLAYER_REQUEST_MSG = "결과를 보고 싶은 사람은?";
    private static final String RESULT_INFO_MSG = "실행결과";

    private static final String LADDER_LABEL_FORMAT = "%" + LABEL_SPACING + "s";
    private static final String LADDER_FORMAT = "%s|";
    private static final String LADDER_CONNECTED_SIGN = "-";
    private static final String LADDER_DISCONNECTED_SIGN = " ";
    private static final String RESULT_FORMAT = "%s : %s";

    public static void printPlayerNamesRequestMsg() {
        System.out.println(PLAYER_NAMES_REQUEST_MSG);
    }

    public static void printLadderPrizeRequestMsg() {
        System.out.println(LADDER_PRIZE_REQUEST_MSG);
    }

    public static void printLadderHeightRequestMsg() {
        System.out.println(LADDER_HEIGHT_MSG);
    }

    public static void printGeneratedLadderInfoMsg() {
        System.out.println(LADDER_GENERATED_INFO_MSG);
    }

    public static void printLadderLabel(List<String> playerNames) {
        for (String name : playerNames) {
            System.out.printf(LADDER_LABEL_FORMAT, name);
        }
        System.out.println();
    }

    public static void printLadderMap(List<Line> ladder) {
        for (Line line : ladder) {
            printLine(line);
        }
    }

    private static void printLine(Line line) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeLadderConnectionOutput(Point.DISCONNECT));
        for (Point point : line.getLine()) {
            stringBuilder.append(makeLadderConnectionOutput(point));
        }
        System.out.println(stringBuilder);
    }

    private static String makeLadderConnectionOutput(Point point) {
        if (point.isConnected()) {
            return String.format(LADDER_FORMAT, LADDER_CONNECTED_SIGN.repeat(LADDER_SPACING));
        }
        return String.format(LADDER_FORMAT, LADDER_DISCONNECTED_SIGN.repeat(LADDER_SPACING));
    }

    public static void printResultShowPlayerRequestMsg() {
        System.out.println(RESULT_SHOW_PLAYER_REQUEST_MSG);
    }

    public static void printResults(List<GameResult> results) {
        printResultInfoMsg();
        for (GameResult result : results) {
            System.out.println(String.format(RESULT_FORMAT, result.getName(), result.getPrizeValue()));
        }
        System.out.println();
    }

    private static void printResultInfoMsg() {
        System.out.println(RESULT_INFO_MSG);
    }

    public static void printErrorMsg(RuntimeException exception) {
        ErrorCode errorCode = ErrorCode.findByCode(exception.getMessage());
        System.out.println(errorCode.getMessage(KOREAN_LANGUAGE_CODE));
    }
}
