import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedList2Test {
    private LinkedList2 emptyList;
    private LinkedList2 singleList;
    private LinkedList2 list;

    @Before
    public void setUp() {
        emptyList = new LinkedList2();

        singleList = new LinkedList2();
        singleList.addInTail(new Node(1));

        list = new LinkedList2();
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(3));
        list.addInTail(new Node(2));
    }

    @Test
    public void testAddInTail() {
        emptyList.addInTail(new Node(1));
        assertEquals(1, emptyList.head.value);
        assertEquals(1, emptyList.tail.value);
        assertNull(emptyList.head.prev);
        assertNull(emptyList.tail.next);

        assertEquals(1, list.head.value);
        assertEquals(2, list.tail.value);
        assertEquals(3, list.tail.prev.value);
        assertNull(list.head.prev);
    }

    @Test
    public void testFind() {
        assertNull(emptyList.find(1));

        Node foundNode = list.find(2);
        assertNotNull(foundNode);
        assertEquals(2, foundNode.value);

        assertNull(list.find(4));
    }

    @Test
    public void testFindAll() {
        assertEquals(0, emptyList.findAll(1).size());

        assertEquals(1, list.findAll(1).size());
        assertEquals(2, list.findAll(2).size());
        assertEquals(3, list.findAll(3).get(0).value);

        assertTrue(list.findAll(4).isEmpty());
    }

    @Test
    public void testRemove() {
        assertFalse(emptyList.remove(1));

        assertTrue(singleList.remove(1));
        assertNull(singleList.head);

        assertTrue(list.remove(2));
        assertEquals(1, list.head.value);
        assertEquals(2, list.tail.value);
        assertEquals(1, list.head.next.prev.value);

        assertFalse(list.remove(4));
    }

    @Test
    public void testRemoveAll() {
        emptyList.removeAll(1);
        assertEquals(0, emptyList.count());

        singleList.remove(2);
        assertEquals(1, singleList.count());

        list.removeAll(2);
        assertEquals(2, list.count());
        assertEquals(1, list.head.value);
        assertEquals(3, list.tail.value);
        assertEquals(1, list.tail.prev.value);

        list.removeAll(1);
        assertEquals(1, list.count());
        assertEquals(3, list.head.value);
        assertEquals(3, list.tail.value);

        list.removeAll(3);
        assertEquals(0, list.count());
        assertNull(list.head);
        assertNull(list.tail);
    }

    @Test
    public void testClear() {
        emptyList.clear();
        assertNull(emptyList.head);
        assertNull(emptyList.tail);

        singleList.clear();
        assertNull(singleList.head);
        assertNull(singleList.tail);

        list.clear();
        assertNull(list.head);
        assertNull(list.tail);
    }

    @Test
    public void testCount() {
        assertEquals(0, emptyList.count());
        assertEquals(1, singleList.count());
        assertEquals(4, list.count());
    }

    @Test
    public void testInsertAfter() {
        emptyList.insertAfter(null, new Node(1));
        assertEquals(1, emptyList.head.value);
        assertEquals(1, emptyList.tail.value);

        singleList.insertAfter(singleList.tail, new Node(2));
        assertEquals(1, singleList.head.value);
        assertEquals(2, singleList.tail.value);
        assertEquals(1, list.head.next.prev.value);

        list.insertAfter(list.head, new Node(4));
        assertEquals(4, list.head.next.value);
        assertEquals(2, list.tail.value);
        assertEquals(1, list.head.next.prev.value);

        Node node = list.head.next.next;
        assertEquals(4, node.prev.value);
        assertNull(list.head.prev);

        list.insertAfter(list.head, null);
        assertEquals(4, list.head.next.value);

        list.insertAfter(list.tail, new Node(5));
        assertEquals(5, list.tail.value);
        assertEquals(2, list.tail.prev.value);
        assertNull(list.tail.next);
    }
}

