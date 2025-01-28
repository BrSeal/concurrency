package dressRoom;

record Person(
        String name,
        Gender gender,
        int dressMs,
        DressRoom dressRoom
) implements Runnable {

    @Override
    public void run() {
        try {
            dressRoom.enterRoom(this);
            System.out.println(name + " заходит");

            System.out.println(name + " переодевается");
            Thread.sleep(dressMs);

            System.out.println(name + " выходит");
            dressRoom.leaveRoom(this);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
