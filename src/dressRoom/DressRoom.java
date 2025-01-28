package dressRoom;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static dressRoom.Gender.MALE;
import static dressRoom.Gender.NONE;

class DressRoom {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition genderCondition = lock.newCondition();
    private final Condition fullyOccupiedCondition = lock.newCondition();

    private Gender occupiedBy = NONE;
    private int personsCount = 0;

    private int size;

    public DressRoom(int size) {
        this.size = size;
    }


    public void enterRoom(Person person) throws InterruptedException {
        lock.lock();
        try {

            while (personsCount == size) {
                fullyOccupiedCondition.await();
            }

            while (occupiedBy != NONE && occupiedBy != person.gender()) {
                genderCondition.await();
            }
            if (occupiedBy == NONE) {
                occupiedBy = person.gender();
                System.out.println("Раздевалка занята " + (person.gender() == MALE ? "мужчинами" : "женщинами"));
            }

            personsCount++;

            System.out.println(person.name() + " берет ключ");
        } finally {
            lock.unlock();
        }

    }

    public void leaveRoom(Person person) {
        lock.lock();
        try {
            System.out.println(person.name() + " сдает ключ");
            personsCount--;
            fullyOccupiedCondition.signal();
            if (personsCount == 0) {
                occupiedBy = NONE;
                System.out.println("Раздевалка свободна!");
                genderCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
