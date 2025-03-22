package tracker.history;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.model.Task;
import tracker.model.Status;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    private InMemoryHistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void shouldStoreLastTenTasks() {
        for (int i = 1; i <= 12; i++) {
            historyManager.add(new Task(i, "Задача " + i, "Описание", Status.NEW));
        }

        List<Task> history = historyManager.getHistory();
        assertEquals(10, history.size(), "История должна хранить только 10 последних задач");
        assertEquals(3, history.get(0).getId(), "Самая старая задача должна быть первой в списке");
    }

    @Test
    void shouldNotFailOnNullTasks() {
        assertDoesNotThrow(() -> historyManager.add(null), "Добавление null не должно ломать систему");
    }
}
