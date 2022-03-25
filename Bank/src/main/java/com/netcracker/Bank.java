package com.netcracker;

public class Bank {
    private volatile long cash;

    public Bank(long cash) {
        this.cash = cash;
    }

    public long getCash() {
        return cash;
    }

    public synchronized void changeCash(Client client, int tellerNumber) {
        switch (client.getOperation()) {
            case DEPOSIT -> {
                cash += client.getMoney();
                System.out.printf("Клиент №%d положил на счёт банка %d$ через %d кассу.\n",
                        client.getNumber(), client.getMoney(), tellerNumber);
            }
            case WITHDRAW -> {
                if (cash - client.getMoney() > 0) {
                    cash -= client.getMoney();
                    System.out.printf("Клиент №%d снял со счёта банка %d$ через %d кассу.\n",
                            client.getNumber(), client.getMoney(), tellerNumber);
                } else {
                    System.out.printf("Клиент №%d не смог снять со счёта банка %d$ через %d кассу. " +
                            "В банке недостаточно средств.\n", client.getNumber(), client.getMoney(), tellerNumber);
                }
            }
        }
        System.out.printf("В банке осталось %d$.\n", cash);
    }
}

