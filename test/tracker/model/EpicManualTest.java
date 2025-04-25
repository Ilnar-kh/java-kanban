package tracker.model;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EpicManualTest {
    @Test
    void epicShouldIgnoreSelfId() {
        Epic epic = new Epic(1, "X", "Y");
        epic.addSubtaskId(1);
        List<Integer> ids = epic.getSubtaskIds();
        assertTrue(ids.isEmpty(), "Эпик не должен добавлять свой же ID");
    }
}
