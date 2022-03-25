package com.netcracker;

class Client {
    private int number;
    private Operation operation;
    private long money;
    private long time;

    public Client(int number, Operation operation, long money, long time) {
        this.number = number;
        this.operation = operation;
        this.money = money;
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}