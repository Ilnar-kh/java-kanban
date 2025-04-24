package tracker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    void epicsWithSameIdShouldBeEqual() {
        Epic epic1 = new Epic(1, "Эпик", "Описание");
        Epic epic2 = new Epic(1, "Другой эпик", "Другое описание");

        assertEquals(epic1, epic2, "Эпики с одинаковым ID должны быть равны");
    }

    @Test
    void epicCannotContainItselfAsSubtask() {
        Epic epic = new Epic(1, "Эпик", "Описание");
        Subtask subtask = new Subtask(1, "Подзадача", "Описание", Status.NEW, epic.getId());

        assertNotEquals(epic.getId(), subtask.getId(), "Эпик не может быть своей же подзадачей");
    }
}

