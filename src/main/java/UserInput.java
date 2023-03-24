public class UserInput {

    public static void menuChoice(Village village, int userInput) {

        switch (userInput) {
            case 1 -> village.addWorker("name", "wood", village::addWood);
            case 2 -> village.addWorker("name", "food", village::addFood);
            case 3 -> village.addWorker("name", "metal", village::addMetal);
            case 4 -> village.addWorker("name", "builder", village::addBuilder);
            case 5 -> village.addProject(new House("House"));
            case 6 -> village.addProject(new Woodmill("Woodmill"));
            case 7 -> village.addProject(new Quarry("Quarry"));
            case 8 -> village.addProject(new Farm("Farm"));
            case 9 -> village.addProject(new Castle("Castle"));
            case 10 -> village.day();
        }
    }

    public static void printVillageInfo(Village village) {
        System.out.println("Wood: " + village.getWood());
        System.out.println("Metal: " + village.getMetal());
        System.out.println("Food: " + village.getFood());
        village.getProjects().forEach(project -> System.out.println("Project: " + project.getName() + " " + project.getDaysToComplete()));
        village.getBuildings().forEach(buildings -> System.out.println("Building: " + buildings.getName()));
        village.getWorkers().forEach(worker -> System.out.println("Worker: " + worker.getOccupation()));
        System.out.println();
        System.out.println();
    }

    public static void printMenu(int daysGone) {
        System.out.println("DAY " + daysGone);
        System.out.println("1. Add wood worker");
        System.out.println("2. Add food worker");
        System.out.println("3. Add metal worker");
        System.out.println("4. Add builder");
        System.out.println("5. Add House");
        System.out.println("6. Add Woodmill");
        System.out.println("7. Add Quarry");
        System.out.println("8. Add Farm");
        System.out.println("9. Add Castle");
        System.out.println("10. Day");
    }
}
