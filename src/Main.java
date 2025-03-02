public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task(manager.generateId(), "Купить яблоки", "Сезонный сорт", Status.NEW);
        Task task2 = new Task(manager.generateId(), "Пойти в кофейню", "Заполнить дневник", Status.NEW);
        manager.addTask(task1);
        manager.addTask(task2);

        Epic epic1 = new Epic(manager.generateId(), "Организовать переезд",
                "Спланировать отправку вещей");
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask(manager.generateId(), "Упаковать вещи", "Собрать коробки",
                Status.NEW, epic1.getId());
        Subtask subtask2 = new Subtask(manager.generateId(), "Найти пункт отправки", "Оформить отправку",
                Status.NEW, epic1.getId());
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        Epic epic2 = new Epic(manager.generateId(), "Подготовиться к отпуску",
                "Собрать документы и вещи");
        manager.addEpic(epic2);

        Subtask subtask3 = new Subtask(manager.generateId(), "Купить билеты", "Забронировать перелёт",
                Status.NEW, epic2.getId());
        manager.addSubtask(subtask3);

        System.out.println("\n Список всех задач");
        System.out.println(manager.getTask(task1.getId()));
        System.out.println(manager.getTask(task2.getId()));

        System.out.println("\n Список всех эпиков");
        System.out.println(manager.getEpic(epic1.getId()));
        System.out.println(manager.getEpic(epic2.getId()));

        System.out.println("\n Список всех подзадач");
        System.out.println(manager.getSubtask(subtask1.getId()));
        System.out.println(manager.getSubtask(subtask2.getId()));
        System.out.println(manager.getSubtask(subtask3.getId()));


        task1.setStatus(Status.DONE);
        subtask1.setStatus(Status.DONE);
        subtask2.setStatus(Status.DONE);
        subtask3.setStatus(Status.IN_PROGRESS);

        manager.updateTask(task1);
        manager.updateSubtask(subtask1);
        manager.updateSubtask(subtask2);
        manager.updateSubtask(subtask3);

        System.out.println("\nПосле изменения статусов");
        System.out.println(manager.getEpic(epic1.getId()));
        System.out.println(manager.getEpic(epic2.getId()));

        System.out.println("\nУдаление задач и эпика");
        manager.removeTask(task2.getId());
        manager.removeEpic(epic1.getId());

        System.out.println("\nСписок всех задач после удаления:");
        System.out.println(manager.getTask(task1.getId()));
        System.out.println(manager.getTask(task2.getId()));

        System.out.println("\nСписок всех эпиков после удаления: ");
        System.out.println(manager.getEpic(epic1.getId()));
        System.out.println(manager.getEpic(epic2.getId()));

        System.out.println(manager.getTask(task1.getId()));
        System.out.println(manager.getEpic(epic1.getId()));
        System.out.println(manager.getSubtask(subtask1.getId()));
    }
}


