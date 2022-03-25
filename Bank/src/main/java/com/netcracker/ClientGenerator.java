package com.netcracker;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ClientGenerator implements Runnable {
    private final List<BankTeller> tellers;
    private final Bank bank;
    private boolean isActive;
    private final Random random;
    private int clientNumber = 1;

    public ClientGenerator(List<BankTeller> tellers, Bank bank) {
        this.tellers = tellers;
        this.isActive = true;
        this.random = new Random();
        this.bank = bank;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void run() {
        while (isActive) {
            BankTeller bankTeller = tellers
                    .stream()
                    .min(Comparator.comparing(BankTeller::getQueueSize))
                    .orElse(tellers.get(0));

            System.out.printf("В очередь №%d пришел новый клиент. Текущее расположение клиентов в очередях у касс: ",
                    bankTeller.getTellerNumber());
            tellers.forEach(t -> System.out.print(t.getQueueSize() + " "));
            System.out.println();

            Operation operation = random.nextBoolean() ? Operation.DEPOSIT : Operation.WITHDRAW;
            long money = random.nextLong(bank.getCash() * 3 / 2);

            tellers.get(bankTeller.getTellerNumber() - 1)
                    .addClient(new Client(clientNumber++, operation, money,
                            Main.SERVICE_TIME_SEC * 1000 + random.nextLong(1000)));

            try {
                Thread.sleep(60 / Main.CLIENTS_PER_MINUTE * 1000 + random.nextLong(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
