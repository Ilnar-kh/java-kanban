package tracker.model;

import tracker.model.Status;

public class Subtask extends Task {

    private int epicId;

    public Subtask(int id, String title, String description, Status status, int epicId) {
        super(id, title, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "tracker.model.Subtask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", epicId='" + epicId + '\'' +
                '}';
    }
}
