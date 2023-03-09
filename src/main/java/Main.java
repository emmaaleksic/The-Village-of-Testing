public class Main {
    public static void main(String[] args) {
        boolean castleIsBuilt = false;
        Village village = new Village(new DatabaseConnection());

        village.addWorker("name", "wood", () -> village.addWood());
        village.addWorker("name", "metal", () -> village.addMetal());
        village.addWorker("name", "food", () -> village.addFood());
        village.addWorker("name", "build", () -> village.build());

        Castle halmstadSlott = new Castle("Halmstad slott");

        while (!castleIsBuilt) {
            village.day();
            if (village.getWood() >= halmstadSlott.getWoodCost() && village.getMetal() >= halmstadSlott.getMetalCost() && village.getProjects().isEmpty()) {
                village.addProject(halmstadSlott);
            }
            castleIsBuilt = village.castleisBuilt();
        }
    }
}


