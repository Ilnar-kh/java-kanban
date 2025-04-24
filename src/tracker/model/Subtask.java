package tracker.model;

import tracker.model.Status;

import java.util.Objects;

public class Subtask extends Task {

    private int epicId;

    public Subtask(int id, String title, String description, Status status, int epicId) {
        super(id, title, description, status);
        // если передали свой же ID как epicId — игнорируем
        this.epicId = (epicId == id) ? -1 : epicId;
    }

    public Subtask(Subtask other) {
        super(other);
        this.epicId = other.epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "tracker.model.Subtask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", epicId=" + epicId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subtask)) return false;
        Subtask that = (Subtask) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
