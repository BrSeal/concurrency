package dressRoom;

import java.util.Random;

import static dressRoom.Gender.FEMALE;
import static dressRoom.Gender.MALE;

class Main {
    static final int DRESS_ROOM_SIZE = 3;
    private static final int PERSONS_COUNT = 200;

    private static final int PERSON_COME_INTERVAL_MIN = 50;
    private static final int PERSON_COME_INTERVAL_MAX = 100;

    private static final int TIME_TO_DRESS_FROM = 20;
    private static final int TIME_TO_DRESS_TO = 80;

    private static final DressRoom dressRoom = new DressRoom(DRESS_ROOM_SIZE);
    private static final Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < PERSONS_COUNT; i++) {
            Thread.sleep(rand.nextInt(PERSON_COME_INTERVAL_MIN, PERSON_COME_INTERVAL_MAX));
            new Thread(generatePerson(i)).start();
        }
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
