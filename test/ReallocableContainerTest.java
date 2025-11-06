
package test;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReallocableContainerTest {

    // Mock implementation for testing the interface's default methods
    // This allows us to test ReallocableContainer even without a full implementation
    private static class MockReallocableContainer implements ReallocableContainer {
        private Natural capacity;

        public MockReallocableContainer(Natural initialCapacity) {
            this.capacity = initialCapacity;
        }

        @Override
        public Natural Capacity() {
            return capacity;
        }

        @Override
        public void Realloc(Natural newsize) {
            this.capacity = newsize;
        }
    }

    private MockReallocableContainer container;

    @BeforeEach
    void setUp() {
        container = new MockReallocableContainer(Natural.Of(10));
    }

    @Test
    void capacity() {
        // Test that Capacity returns the correct value
        assertEquals(10, container.Capacity().ToLong(),
                "Capacity should return the initial capacity");

        // Test after reallocation
        container.Realloc(Natural.Of(20));
        assertEquals(20, container.Capacity().ToLong(),
                "Capacity should return the updated capacity after Realloc");
    }

    @Test
    void grow() {
        // Test Grow from non-zero capacity
        long initialCapacity = container.Capacity().ToLong();
        container.Grow();
        long expectedCapacity = (long) Math.ceil(initialCapacity * ReallocableContainer.GROW_FACTOR);
        assertEquals(expectedCapacity, container.Capacity().ToLong(),
                "Capacity should be multiplied by GROW_FACTOR");

        // Test Grow from zero capacity
        container = new MockReallocableContainer(Natural.ZERO);
        container.Grow();
        assertEquals(1, container.Capacity().ToLong(),
                "Capacity should be 1 after Grow from 0");
    }

    @Test
    void testGrow() {
        // Test Grow with specific amount
        long initialCapacity = container.Capacity().ToLong();
        Natural toAdd = Natural.Of(5);
        container.Grow(toAdd);
        assertEquals(initialCapacity + 5, container.Capacity().ToLong(),
                "Capacity should increase by the specified amount");

        // Test Grow with zero
        initialCapacity = container.Capacity().ToLong();
        container.Grow(Natural.ZERO);
        assertEquals(initialCapacity, container.Capacity().ToLong(),
                "Capacity should not change when growing by 0");

        // Test Grow with large amount
        container = new MockReallocableContainer(Natural.Of(5));
        container.Grow(Natural.Of(100));
        assertEquals(105, container.Capacity().ToLong(),
                "Capacity should increase correctly with large amount");
    }

    @Test
    void shrink() {
        // Test Shrink from non-zero capacity
        long initialCapacity = container.Capacity().ToLong();
        container.Shrink();
        long expectedCapacity = (long) Math.floor(initialCapacity / ReallocableContainer.SHRINK_FACTOR);
        assertEquals(expectedCapacity, container.Capacity().ToLong(),
                "Capacity should be divided by SHRINK_FACTOR");

        // Test Shrink from zero capacity (should not fail)
        container = new MockReallocableContainer(Natural.ZERO);
        container.Shrink();
        assertEquals(0, container.Capacity().ToLong(),
                "Capacity should remain 0 after Shrink from 0");

        // Test Shrink from capacity of 1
        container = new MockReallocableContainer(Natural.ONE);
        container.Shrink();
        assertEquals(0, container.Capacity().ToLong(),
                "Capacity should be 0 after Shrink from 1");
    }

    @Test
    void size() {
        // Test that Size equals Capacity
        assertEquals(container.Capacity().ToLong(), container.Size().ToLong(),
                "Size should equal Capacity");

        // Test after Grow
        container.Grow();
        assertEquals(container.Capacity().ToLong(), container.Size().ToLong(),
                "Size should equal Capacity after Grow");

        // Test with zero capacity
        container = new MockReallocableContainer(Natural.ZERO);
        assertEquals(0, container.Size().ToLong(),
                "Size should be 0 for empty container");
        assertTrue(container.IsEmpty(),
                "IsEmpty should return true for zero capacity");
    }

    @Test
    void clear() {
        // Test Clear resets capacity to zero
        assertFalse(container.IsEmpty(),
                "Container should not be empty initially");
        container.Clear();
        assertEquals(0, container.Capacity().ToLong(),
                "Capacity should be 0 after Clear");
        assertEquals(0, container.Size().ToLong(),
                "Size should be 0 after Clear");
        assertTrue(container.IsEmpty(),
                "IsEmpty should return true after Clear");

        // Test Clear on already empty container
        container.Clear();
        assertEquals(0, container.Capacity().ToLong(),
                "Capacity should remain 0 after Clear on empty container");
    }

    @Test
    void growShrinkSequence() {
        // Test a sequence of Grow and Shrink operations
        long initialCapacity = container.Capacity().ToLong();

        container.Grow();
        long afterGrow = container.Capacity().ToLong();
        assertTrue(afterGrow > initialCapacity,
                "Capacity should increase after Grow");

        container.Shrink();
        long afterShrink = container.Capacity().ToLong();
        assertTrue(afterShrink < afterGrow,
                "Capacity should decrease after Shrink");
    }

    @Test
    void multipleGrows() {
        // Test multiple consecutive Grow operations
        container = new MockReallocableContainer(Natural.ONE);
        long capacity = 1;

        for (int i = 0; i < 5; i++) {
            container.Grow();
            long newCapacity = container.Capacity().ToLong();
            assertTrue(newCapacity > capacity,
                    "Capacity should increase after each Grow");
            capacity = newCapacity;
        }
    }

    @Test
    void growWithNaturalEdgeCases() {
        // Test Grow with Natural.ONE
        long initialCapacity = container.Capacity().ToLong();
        container.Grow(Natural.ONE);
        assertEquals(initialCapacity + 1, container.Capacity().ToLong(),
                "Capacity should increase by 1");

        // Test Grow with large value
        container.Grow(Natural.Of(1000));
        assertTrue(container.Capacity().ToLong() > 1000,
                "Capacity should be greater than 1000 after adding 1000");
    }
}