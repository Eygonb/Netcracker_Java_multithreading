package com.netcracker;

import java.util.List;
import java.util.stream.IntStream;

class Main {
    public static final int TELLERS_COUNT = 10;
    public static final int SERVICE_TIME_SEC = 10;
    public static final int CLIENTS_PER_MINUTE = 60;
    public static final long BANK_CASH = 10000;

    public static void runBank() {
        Bank bank = new Bank(BANK_CASH);

        List<BankTeller> tellers = IntStream.range(1, TELLERS_COUNT + 1).boxed()
                .map(t -> new BankTeller(bank, t)).toList();

        tellers.forEach(teller -> new Thread(teller).start());

        new Thread(new ClientGenerator(tellers, bank)).start();
    }

    public static void main(String[] args) {
        runBank();
    }
}
