package tracker.model;

import tracker.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    private final List<Integer> subtaskIds = new ArrayList<>();

    public Epic(int id, String title, String description) {
        super(id, title, description, Status.NEW);
    }

    public List<Integer> getSubtaskIds() {
        return new ArrayList<>(subtaskIds);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addSubtaskId(int id) {
        if (id != getId()) {
            subtaskIds.add(id);
        }
    }

    @Override
    public String toString() {
        return "tracker.model.Epic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", subtaskIds=" + subtaskIds +
                '}';
    }

    public Epic(Epic other) {
        super(other);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Удаление подзадачи из эпика
    public void removeSubtaskId(int id) {
        subtaskIds.remove((Integer) id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Epic)) return false;
        Epic epic = (Epic) o;
        return getId() == epic.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
