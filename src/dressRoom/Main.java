package dressRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static dressRoom.Gender.FEMALE;
import static dressRoom.Gender.MALE;

class Main {
    static final int DRESS_ROOM_SIZE = 3;
    private static final int PERSONS_COUNT = 100;

    private static final int TIME_TO_DRESS_FROM = 100;
    private static final int TIME_TO_DRESS_TO = 300;

    private static final DressRoom dressRoom = new DressRoom(DRESS_ROOM_SIZE);
    private static final Random rand = new Random();

    public static void main(String[] args) {
        for (Thread thread : generatePersonsThreads()) {
            thread.start();
        }
    }

    private static List<Thread> generatePersonsThreads(){
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < PERSONS_COUNT; i++) {
            threads.add(new Thread(generatePerson(i)));
        }

        return threads;
    }

    private static Person generatePerson(int num) {
        Gender gender = rand.nextBoolean() ? MALE : FEMALE;
        String name = gender + " " + num;
        int timeToDress = rand.nextInt(TIME_TO_DRESS_FROM, TIME_TO_DRESS_TO);
        return new Person(
                name,
                gender,
                timeToDress,
                dressRoom
        );

    }
}
