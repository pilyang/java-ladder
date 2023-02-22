package laddergame.controller;

import laddergame.domain.Ladder;
import laddergame.domain.LadderGame;
import laddergame.domain.LadderHeight;
import laddergame.domain.LadderMaker;
import laddergame.domain.Players;
import laddergame.domain.Prizes;
import laddergame.util.RandomBooleanGenerator;
import laddergame.util.RepeatValidator;
import laddergame.view.InputView;
import laddergame.view.OutputView;

import java.util.List;

public class LadderGameController {

    public void run() {
        LadderGame ladderGame = initLadderGame();
        printGeneratedLadderResult(ladderGame);
    }

    private LadderGame initLadderGame() {
        Players players = RepeatValidator.readUntilValidate(this::readPlayers);
        return RepeatValidator.readUntilValidate(() -> {
            Prizes prizes = RepeatValidator.readUntilValidate(this::readPrizes);
            int height = RepeatValidator.readUntilValidate(this::readLadderHeight);
            LadderMaker ladderMaker = new LadderMaker(new RandomBooleanGenerator());
            Ladder ladder = ladderMaker.make(players.size(), new LadderHeight(height));
            return new LadderGame(players, ladder, prizes);
        });
    }

    private Players readPlayers() {
        OutputView.printPlayerNamesRequestMsg();
        List<String> playerNames = InputView.inputPlayerNames();
        return new Players(playerNames);
    }

    private Prizes readPrizes() {
        OutputView.printLadderPrizeRequestMsg();
        List<String> prizeValues = InputView.inputLadderPrize();
        return new Prizes(prizeValues);
    }

    private int readLadderHeight() {
        OutputView.printLadderHeightRequestMsg();
        int height = InputView.inputLadderHeight();
        return height;
    }

    private void printGeneratedLadderResult(LadderGame ladderGame) {
        OutputView.printResultInfoMsg();
        OutputView.printLadderLabel(ladderGame.getPlayerNames());
        OutputView.printLadderMap(ladderGame.getLadderMap());
        OutputView.printLadderLabel(ladderGame.getPrizeValues());
    }
}
