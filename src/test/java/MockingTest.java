import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MockingTest {
    // Mina mockingtester blev inte som enligt uppgiften, jag valde dock att behålla dessa för att visa att jag förstått principen.

    @Test
    void testDatabase() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Village village = new Village(databaseConnection);
        village.saveProgress();
        village.loadProgress();
    }

    @Test
    void testDatabaseMock() {
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);

        Village village = new Village(databaseConnection);

        when(databaseConnection.save(village.getFood())).thenReturn(true);
        village.saveProgress();
        assertEquals(10, village.getFood());

        when(databaseConnection.load()).thenReturn(15);
        village.loadProgress();
        assertEquals(15, village.getFood());

    }

    @Test
    void addRandomWorker() {
        Village village = new Village(new DatabaseConnection());
        village.setFood(100);
        Random random = mock(Random.class);
        when(random.nextInt(4)).thenReturn(2);

        for (int i = 0; i < 4; i++) {
            Village.WorkerDelegate workerDelegate = village.addRandomWorker(random);
            Worker worker = new Worker("name", "metal", workerDelegate);
            village.addWorker(worker.getName(), worker.getOccupation(), workerDelegate);
            System.out.println(worker.getOccupation());
        }
        village.day();
        assertEquals(8, village.getMetal());
    }
}
