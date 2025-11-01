package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    private TorpedoStore store;
    private TorpedoStore storeWithFailure;

    @BeforeEach
    void setUp() {
        store = new TorpedoStore(10, 0.0);
        storeWithFailure = new TorpedoStore(10, 1.0);
    }

    @Test
    void fire_Success() {
        // Arrange
        store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fire_SingleTorpedo_Success() {
        boolean result = store.fire(1);

        assertTrue(result);
        assertEquals(9, store.getTorpedoCount());
    }

    @Test
    void fire_AllTorpedoes_Success() {
        boolean result = store.fire(10);

        assertTrue(result);
        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void fire_SingleTorpedo_Failure() {
        boolean result = storeWithFailure.fire(1);

        assertFalse(result);
        assertEquals(10, storeWithFailure.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedoes_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_MoreThanAvailable_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> store.fire(11));
    }

    @Test
    void isEmpty_WithTorpedoes_ReturnsFalse() {
        assertFalse(store.isEmpty());
    }

    @Test
    void isEmpty_WithZeroTorpedoes_Test() {
        TorpedoStore emptyStore = new TorpedoStore(0);

        assertTrue(emptyStore.isEmpty());
    }

    @Test
    void getTorpedoCount_Test() {
        store.fire(3);

        assertEquals(7, store.getTorpedoCount());
    }

    @Test
    void fire_ConsecutiveFiring_ReducesCount() {
        store.fire(2);
        store.fire(3);

        assertEquals(5, store.getTorpedoCount());
    }
}
