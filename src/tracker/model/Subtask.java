package tracker.model;

import tracker.model.Status;

public class Subtask extends Task {

    private int epicId;

    public Subtask(int id, String title, String description, Status status, int epicId) {
        super(id, title, description, status);
        this.epicId = epicId;
    }

    // Copy-конструктор
    public Subtask(Subtask other) {
        super(other);
        this.epicId = other.epicId;
    }

    // Оставляем только один геттер
    public int getEpicId() {
        return epicId;
    }

    // Если нужен сеттер для id
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
}
