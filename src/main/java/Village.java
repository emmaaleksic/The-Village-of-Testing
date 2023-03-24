import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

        House house1 = new House("House");
        House house2 = new House("House");
        House house3 = new House("House");

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

    public WorkerDelegate addRandomWorker(Random random){
        int randomNumber = random.nextInt(4);
        switch (randomNumber){
            case 0 -> {
                return ()->addWood();
            }
            case 1 -> {
                return  ()->addFood();
            }
            case 2 -> {
                return  ()->addMetal();
            }
            case 3 -> {
                return ()->addBuilder();
            }
        }
        return null;
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

    public void addBuilder() {
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
        boolean save = databaseConnection.save(food);
        System.out.println(save);
    }

    public void loadProgress() {
        int loadedFood = databaseConnection.load();
        System.out.println(loadedFood);
        food = loadedFood;
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
