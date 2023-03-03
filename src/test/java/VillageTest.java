import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VillageTest {

    private Village village;

    @BeforeEach
    void setUp() {
        village = new Village();
    }

    @Test
    void testBuryDead() {
        village.buryDead();
        assertEquals(0, village.getWorkers().size());
    }

    @Test
    void testAddWorker() {
        for (int i = 0; i < 10; i++) {
            village.addWorker("name", "job", () -> village.addFood());
        }
        assertEquals(6, village.getWorkers().size());
    }

    @Test
    void testAddWorkerDay() {
        village.addWorker("name", "job", () -> village.addFood());
        village.day();

        assertEquals(14, village.getFood());
    }

    @Test
    void testDayOne() {
        village.day();

        assertResources(0, 0, 10);
        assertEquals(0, village.getWorkers().size());
    }

    @Test
    void testDayTwo() {
        village.addWorker("name", "job", () -> village.addMetal());
        village.addWorker("name", "job", () -> village.addWood());
        village.addWorker("name", "job", () -> village.addFood());
        village.day();
        village.day();

        assertResources(2, 2, 14);
    }

    @Test
    void testDayThree() {
        village.setFood(0);
        village.addWorker("name", "job", () -> village.addMetal());
        village.day();

        Worker worker = village.getWorkers().get(0);

        assertEquals(0, village.getMetal());
        assertTrue(worker.isHungry());
        assertTrue(worker.isAlive());
        assertEquals(1, worker.getDaysHungry());
    }

    @Test
    void testAddProjectOne() {
        village.addWorker("name", "job", () -> village.addWood());
        village.addWorker("name", "job", () -> village.addFood());
        village.addWorker("name", "job", () -> village.build());

        for (int i = 0; i < 5; i++) {
            village.day();
        }

        House house = new House("Osby");
        village.addProject(house);

        assertEquals(1, village.getProjects().size());

        for (int i = 0; i < 4; i++) {
            village.day();
        }

        assertTrue(village.getProjects().isEmpty());
        assertEquals(4, village.getBuildings().size());
    }

    @Test
    void testAddProjectTwo() {
        House house = new House("Osby");
        village.addProject(house);

        for (int i = 0; i < 4; i++) {
            village.day();
        }

        assertEquals(0, village.getProjects().size());
    }

    @Test
    void testWoodMill() {
        village.addWorker("name", "job", () -> village.addWood());
        village.addWorker("name", "job", () -> village.addMetal());
        village.addWorker("name", "job", () -> village.addFood());
        village.addWorker("name", "job", () -> village.build());

        for (int i = 0; i < 5; i++) {
            village.day();
        }

        assertResources(5, 5, 15);

        Woodmill woodmill = new Woodmill("Osby");
        village.addProject(woodmill);

        assertEquals(0, village.getWood());
        assertEquals(4, village.getMetal());
        assertEquals(1, village.getProjects().size());

        for (int i = 0; i < 6; i++) {
            village.day();
        }
        assertResources(6, 10, 21);
        assertEquals(0, village.getProjects().size());
        assertEquals(4, village.getBuildings().size());
    }

    @Test
    void testHungryOne() {
        village.setFood(0);
        village.addWorker("name", "job", () -> village.addWood());

        for (int i = 0; i < 4; i++) {
            village.day();
        }

        assertEquals(0, village.getWood());
    }

    @Test
    void testHungryTwo() {
        village.setFood(0);
        village.addWorker("name", "job", () -> village.addWood());

        for (int i = 0; i < 42; i++) {
            village.day();
        }
        assertEquals(0, village.getWorkers().size());
    }

    @Test
    void testHungryThree() {
        village.setFood(0);
        village.addWorker("name", "job", () -> village.addWood());

        Worker worker = village.getWorkers().get(0);

        assertEquals(0, worker.getDaysHungry());
        assertFalse(worker.isHungry());
        assertTrue(worker.isAlive());

        village.day();

        assertEquals(1, worker.getDaysHungry());
        assertTrue(worker.isHungry());
        assertTrue(worker.isAlive());
    }

    @Test
    void testHungryFour() {
        village.setFood(0);
        village.addWorker("name", "job", () -> village.addWood());
        Worker worker = village.getWorkers().get(0);
        worker.setAlive(false);

        assertEquals(1, village.getWorkers().size());

        village.buryDead();

        assertEquals(0, village.getWorkers().size());
    }

    @Test
    void testCastle() {
        village.addWorker("name", "job", () -> village.addWood());
        village.addWorker("name", "job", () -> village.addMetal());
        village.addWorker("name", "job", () -> village.addFood());
        village.addWorker("name", "job", () -> village.build());

        for (int i = 0; i < 50; i++) {
            village.day();
        }

        assertResources(50, 50, 60);

        Castle castle = new Castle("Slott");
        village.addProject(castle);

        assertEquals(1, village.getProjects().size());

        for (int i = 0; i < 51; i++) {
            village.day();
        }

        assertResources(51, 51, 111);
        assertEquals(0, village.getProjects().size());
        assertEquals(4, village.getBuildings().size());

    }

    private void assertResources(int wood, int metal, int food) {
        assertEquals(wood, village.getWood());
        assertEquals(metal, village.getMetal());
        assertEquals(food, village.getFood());
    }

}
