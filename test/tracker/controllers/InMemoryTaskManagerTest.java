package tracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.history.InMemoryHistoryManager;
import tracker.model.Epic;
import tracker.model.Status;
import tracker.model.Subtask;
import tracker.model.Task;

import java.util.List;

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
        int taskId = manager.addNewTask(task);

        Task stored = manager.getTask(taskId);
        assertNotNull(stored, "Менеджер должен вернуть задачу");
        assertEquals(taskId, stored.getId());
        assertEquals("Тестовая задача", stored.getTitle());
    }

    @Test
    void shouldNotAllowDuplicateId() {
        Task t1 = new Task(1, "Первая", "Описание", Status.NEW);
        Task t2 = new Task(1, "Вторая", "Описание", Status.NEW);

        manager.addNewTask(t1);
        assertThrows(IllegalArgumentException.class,
                () -> manager.addNewTask(t2),
                "Задачи с одинаковым ID не должны допускаться");
    }

    @Test
    void shouldRemoveSubtaskAndItsIdFromEpicAndHistory() {
        Epic epic = new Epic(1, "Эпик", "Описание");
        int epicId = manager.addNewEpic(epic);

        Subtask sub = new Subtask(2, "Подзадача", "Описание", Status.NEW, epicId);
        int subtaskId = manager.addNewSubtask(sub);

        manager.getSubtask(subtaskId);
        assertTrue(manager.getHistory().stream()
                        .anyMatch(t -> t.getId() == subtaskId),
                "Subtask должна сначала появиться в истории");

        manager.removeSubtask(subtaskId);

        assertNull(manager.getSubtask(subtaskId),
                "Subtask должна быть удалена из хранилища");

        assertFalse(manager.getEpic(epicId).getSubtaskIds().contains(subtaskId),
                "ID подзадачи должен быть удалён из эпика");

        assertFalse(manager.getHistory().stream()
                        .anyMatch(t -> t.getId() == subtaskId),
                "Subtask должна быть удалена из истории");
    }

    @Test
    void shouldRemoveEpicAndAllItsSubtasksFromStorageAndHistory() {
        Epic epic = new Epic(1, "Эпик", "Описание");
        int epicId = manager.addNewEpic(epic);

        Subtask s1 = new Subtask(2, "Sub1", "D", Status.NEW, epicId);
        Subtask s2 = new Subtask(3, "Sub2", "D", Status.NEW, epicId);
        int id1 = manager.addNewSubtask(s1);
        int id2 = manager.addNewSubtask(s2);

        manager.getEpic(epicId);
        manager.getSubtask(id1);
        manager.getSubtask(id2);
        assertEquals(3, manager.getHistory().size(),
                "В истории должны быть 3 элемента перед удалением эпика");

        manager.removeEpic(epicId);

        assertNull(manager.getEpic(epicId), "Epic должен быть удалён");
        assertNull(manager.getSubtask(id1), "Subtask1 должна быть удалена");
        assertNull(manager.getSubtask(id2), "Subtask2 должна быть удалена");

        List<Task> history = manager.getHistory();
        assertTrue(history.isEmpty(),
                "История просмотров должна быть очищена после удаления эпика и его подзадач");
    }

    @Test
    void shouldPreserveInternalStateWhenReturnedTaskIsMutated() {
        // 1) создаём и добавляем задачу
        Task original = new Task(1, "Исходный", "Описание", Status.NEW);
        manager.addNewTask(original);

        // 2) получаем её из менеджера и мутируем через сеттеры
        Task fetched = manager.getTask(1);
        fetched.setTitle("Изменено");
        fetched.setDescription("Новое описание");

        // 3) снова получаем — внутри всё должно остаться без изменений
        Task fetchedAgain = manager.getTask(1);
        assertEquals("Исходный", fetchedAgain.getTitle(),
                "Менеджер должен сохранить оригинальный заголовок");
        assertEquals("Описание", fetchedAgain.getDescription(),
                "Менеджер должен сохранить оригинальное описание");
    }


}
