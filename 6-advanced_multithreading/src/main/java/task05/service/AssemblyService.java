package task05.service;

import task05.model.Computer;
import task05.model.ComputerPart;

import java.util.concurrent.CompletableFuture;

public class AssemblyService {

    public static CompletableFuture<Computer> addMotherboard(Computer computer, ComputerPart part) {
        CompletableFuture<Computer> result = CompletableFuture.supplyAsync(() -> {
            computer.setMotherboard(part);
            System.out.println("Motherboard added");

            return computer;
        });

        return result;
    }

    public static CompletableFuture<Computer> addRam(Computer computer, ComputerPart part) {
        CompletableFuture<Computer> result = CompletableFuture.supplyAsync(() -> {
            computer.setRam(part);
            System.out.println("Ram added");

            return computer;
        });

        return result;
    }

    public static CompletableFuture<Computer> addHdd(Computer computer, ComputerPart part) {
        CompletableFuture<Computer> result = CompletableFuture.supplyAsync(() -> {
            computer.setHdd(part);
            System.out.println("Hdd added");

            return computer;
        });

        return result;
    }

    public static CompletableFuture<Computer> addCpu(Computer computer, ComputerPart part) {
        CompletableFuture<Computer> result = CompletableFuture.supplyAsync(() -> {
            computer.setCpu(part);
            System.out.println("Processor added");

            return computer;
        });

        return result;
    }
}
