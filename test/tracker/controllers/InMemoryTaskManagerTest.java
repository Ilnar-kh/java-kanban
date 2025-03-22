package tracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.history.InMemoryHistoryManager;
import tracker.model.Epic;
import tracker.model.Status;
import tracker.model.Subtask;
import tracker.model.Task;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private InMemoryTaskManager manager;

    @BeforeEach
    void setUp() {
        manager = new InMemoryTaskManager(new InMemoryHistoryManager());
    }

    @Test
    void shouldAddTaskAndRetrieveItById() {
        Task task = new Task(1, "Тестовая задача", "Описание", Status.NEW);
        int id = manager.addNewTask(task);
        assertEquals(task, manager.getTask(id), "Задача должна быть найдена по ID");
    }

    @Test
    void shouldNotAllowDuplicateId() {
        Task task1 = new Task(1, "Первая", "Описание", Status.NEW);
        Task task2 = new Task(1, "Вторая", "Описание", Status.NEW);

        manager.addNewTask(task1);
        assertThrows(IllegalArgumentException.class, () -> manager.addNewTask(task2),
                "Задачи с одинаковым ID не должны допускаться");
    }
}
