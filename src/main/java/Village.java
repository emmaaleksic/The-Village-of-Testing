import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Village {

    private int food;
    private int wood;
    private int metal;
    private int foodPerDay;
    private int woodPerDay;
    private int metalPerDay;
    private int daysGone;
    private List<Worker> workers;
    private List<Building> buildings;
    private List<Building> projects;

    private DatabaseConnection databaseConnection;

    public Village(DatabaseConnection databaseConnection) {
        this.food = 10;
        this.wood = 0;
        this.metal = 0;
        this.foodPerDay = 0;
        this.woodPerDay = 0;
        this.metalPerDay = 0;
        this.daysGone = 0;
        this.workers = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.databaseConnection = databaseConnection;

        House house1 = new House("Hus 1");
        House house2 = new House("Hus 2");
        House house3 = new House("Hus 3");

        house1.setComplete(daysGone);
        house2.setComplete(daysGone);
        house3.setComplete(daysGone);

        buildings.add(house1);
        buildings.add(house2);
        buildings.add(house3);

    }

    @FunctionalInterface
    public interface WorkerDelegate {
        void doWork();
    }

    public void addWorker(String name,
                          String occupation,
                          Village.WorkerDelegate workerDelegate) {
        if (workers.size() < (buildings.size() * 2)) {
            workers.add(new Worker(name, occupation, workerDelegate));
        }
    }

    public void addProject(Building newBuilding) {

        if (newBuilding.enoughResources(wood, metal)) {
            projects.add(newBuilding);
            wood = wood - newBuilding.getWoodCost();
            metal = metal - newBuilding.getMetalCost();
        }
    }

    public void day() {
        workers.forEach(worker -> {
            feedWorkers(worker);
            worker.doWork();
        });
        buryDead();
        daysGone++;
    }

    public void addFood() {
        long farmBuildings = buildings.stream()
                .filter(building -> building instanceof Farm)
                .count();
        food += 5 + farmBuildings * 10;

    }

    public void addMetal() {
        long quarryBuildings = buildings.stream()
                .filter(building -> building instanceof Quarry)
                .count();
        metal += 2 + quarryBuildings * 2;

    }

    public void addWood() {
        long woodMillBuildings = buildings.stream()
                .filter(building -> building instanceof Woodmill)
                .count();
        wood += 2 + woodMillBuildings * 2;

    }

    public void build() {
        if (!projects.isEmpty()) {
            Building building = projects.get(0);

            if (building.getDaysToComplete() > 0) {
                building.removeOneDayToComplete();
            } else {
                buildings.add(building);
                projects.remove(building);
                building.setComplete(daysGone);
            }
        }
    }

    public void feedWorkers(Worker worker) {
        if (food > 0) {
            worker.feed();
            food--;
        } else {
            worker.addHungryDay();
        }

    }

    public void buryDead() {
        List<Worker> deadWorkers = workers.stream()
                .filter(worker -> !worker.isAlive())
                .toList();

        deadWorkers.forEach(worker -> this.workers.remove(worker));

        if (this.workers.isEmpty()) {
            System.out.println("game over");
        }

    }

    public boolean castleisBuilt() {
        Optional<Building> any = buildings.stream()
                .filter(e -> e instanceof Castle)
                .findAny();

        return any.isPresent();
    }

    public void saveProgress() {
        databaseConnection.save(this);
    }

    public Village loadProgress() {
        Village loadedVillage = databaseConnection.load();
        return loadedVillage;
        /*food = databaseConnection.getFood();*/
    }


    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getMetal() {
        return metal;
    }

    public void setMetal(int metal) {
        this.metal = metal;
    }

    public int getFoodPerDay() {
        return foodPerDay;
    }

    public void setFoodPerDay(int foodPerDay) {
        this.foodPerDay = foodPerDay;
    }

    public int getWoodPerDay() {
        return woodPerDay;
    }

    public void setWoodPerDay(int woodPerDay) {
        this.woodPerDay = woodPerDay;
    }

    public int getMetalPerDay() {
        return metalPerDay;
    }

    public void setMetalPerDay(int metalPerDay) {
        this.metalPerDay = metalPerDay;
    }

    public int getDaysGone() {
        return daysGone;
    }

    public void setDaysGone(int daysGone) {
        this.daysGone = daysGone;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Building> getProjects() {
        return projects;
    }

    public void setProjects(List<Building> projects) {
        this.projects = projects;
    }

}
