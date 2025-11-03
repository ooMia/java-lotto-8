package lotto.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LottoManager {

    public static final int LOTTO_PRICE = 1_000;

    private List<Lotto> ticketsBought = List.of();
    private WinnerLotto winnerTicket;

    public Collection<Lotto> buyTickets(long money) {
        this.ticketsBought = Vendor.buyLotto(money);
        return Collections.unmodifiableCollection(ticketsBought);
    }

    public Lotto prepareWinnerTicket(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public WinnerLotto completeWinnerTicket(Lotto lotto, int number) {
        this.winnerTicket = new WinnerLotto(lotto, number);
        return winnerTicket;
    }

    public String prizeStats() {
        return new WinningStats(this.ticketsBought, this.winnerTicket).toString();
    }
}
