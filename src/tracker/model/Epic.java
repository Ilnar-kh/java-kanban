package tracker.model;

import tracker.model.Status;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subtaskIds;

    public Epic(int id, String title, String description) {
        super(id, title, description, Status.NEW);
        this.subtaskIds = new ArrayList<>();
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Добавление подзадачи в эпик
    public void addSubtask(int subtaskId) {
        subtaskIds.add(subtaskId);
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


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Удаление подзадачи из эпика
    public void removeSubtask(int subtaskId) {
        subtaskIds.remove(Integer.valueOf(subtaskId));
    }
}
