public class Worker {

    private String name;
    private String occupation;
    private boolean hungry;
    private boolean alive;
    private int daysHungry;
    private Village.WorkerDelegate workerDelegate;

    public Worker(String name,
                  String occupation,
                  Village.WorkerDelegate workerDelegate) {
        this.name = name;
        this.occupation = occupation;
        this.hungry = false;
        this.alive = true;
        this.daysHungry = 0;
        this.workerDelegate = workerDelegate;
    }

    public void doWork() {
        if (!hungry) {
            workerDelegate.doWork();
        }
    }

    public void feed() {
        hungry = false;
        daysHungry = 0;
    }

    public void addHungryDay() {
        daysHungry++;
        hungry = true;
        if (daysHungry >= 40) {
            alive = false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public boolean isHungry() {
        return hungry;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getDaysHungry() {
        return daysHungry;
    }


}
