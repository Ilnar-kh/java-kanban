package tracker.controllers;

import tracker.model.Epic;
import tracker.model.Status;
import tracker.model.Subtask;
import tracker.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();

    private int idCounter = 1;

    public int generateId() {
        return idCounter++;
    }

    public int addNewTask(Task task) {
        final int id = generateId();
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public int addNewEpic(Epic epic) {
        final int id = generateId();
        epic.setId(id);
        epics.put(id, epic);
        return id;
    }

    public int addNewSubtask(Subtask subtask) {
        final int id = generateId();
        subtask.setId(id);
        subtasks.put(id, subtask);
        epics.get(subtask.getEpicId()).addSubtask(id);
        updateEpicStatus(subtask.getEpicId());
        return id;
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public List<Subtask> getSubtasksByEpic(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return new ArrayList<>();
        }

        List<Subtask> subtaskList = new ArrayList<>();
        for (int subtaskId : epic.getSubtaskIds()) {
            Subtask subtask = subtasks.get(subtaskId);
            if (subtask != null) {
                subtaskList.add(subtask);
            }
        }
        return subtaskList;
    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }


    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            updateEpicStatus(subtask.getEpicId());
        }
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            Epic oldEpic = epics.get(epic.getId());
            oldEpic.setTitle(epic.getTitle());
            oldEpic.setDescription(epic.getDescription());
        }
    }

    public void removeTasks(int id) {
        tasks.remove(id);
    }

    public void removeEpics(int id) {
       epics.clear();
       subtasks.clear();
    }

    public void removeSubtasks(int id) {
        for (Epic epic : epics.values()) {
            epic.getSubtaskIds().clear();
            updateEpicStatus(epic.getId());
        }
        subtasks.clear();
    }



    private void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null || epic.getSubtaskIds().isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }

        boolean allDone = true;
        boolean hasInProgress = false;
        boolean hasNew = false;

        for (int subtaskId : epic.getSubtaskIds()) {
            Subtask subtask = subtasks.get(subtaskId);
            if (subtask == null) continue;

            if (subtask.getStatus() == Status.IN_PROGRESS) {
                hasInProgress = true;
            }
            if (subtask.getStatus() == Status.NEW) {
                hasNew = true;
            }
            if (subtask.getStatus() != Status.DONE) {
                allDone = false;
            }
        }

        if (allDone) {
            epic.setStatus(Status.DONE);
        } else if (hasInProgress || (hasNew && !allDone)) {
            epic.setStatus(Status.IN_PROGRESS);
        } else {
            epic.setStatus(Status.NEW);
        }
    }

}
