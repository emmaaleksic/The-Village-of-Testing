import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MockingTest {

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

        when(databaseConnection.load()).thenReturn(village);

        village.saveProgress();
        village.loadProgress();

        assertEquals(15, village.getFood());
    }
}
