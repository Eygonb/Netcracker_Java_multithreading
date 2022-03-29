package com.netcracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankTeller implements Runnable {
    private final Bank bank;
    private boolean isActive;
    private final int tellerNumber;
    private final List<Client> clientList;

    public BankTeller(Bank bank, int tellerNumber) {
        this.clientList = Collections.synchronizedList(new ArrayList<>());
        this.bank = bank;
        this.tellerNumber = tellerNumber;
        this.isActive = true;
    }

    public int getQueueSize() {
        synchronized (clientList) {
            return clientList.size();
        }
    }

    public int getTellerNumber() {
        return tellerNumber;
    }

    public void addClient(Client client) {
        synchronized (clientList) {
            clientList.add(client);
            clientList.notify();
        }
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void run() {
        while (isActive) {
            if (clientList.isEmpty()) {
                synchronized (clientList) {
                    try {
                        clientList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                continue;
            }

            Client client = clientList.get(0);

            bank.changeCash(client, tellerNumber);

            try {
                Thread.sleep(client.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clientList.remove(0);
        }
    }
}
