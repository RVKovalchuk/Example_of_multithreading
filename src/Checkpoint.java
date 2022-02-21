//создаем класс Checkpoint, наследуемся от Thread
public class Checkpoint extends Thread {
    //объявляем счетчик  максимального количества проверенного транспорта
    private final int countOfChecking;
    //создаем объект класса Queue
    Queue queue;
    //инициализируем счетчик проверенного транспорта
    private int countOfLeave = 0;

    //создаем конструктор для создания объекта класса Checkpoint
    public Checkpoint(Queue queue, int countOfChecking) {
        this.queue = queue;
        this.countOfChecking = countOfChecking;
    }

    //переопределяем метод run()
    @Override
    public void run() {
        //проверяем возможность проверки транспорта
        while (countOfLeave < countOfChecking) {
            //удаляем транспорт из очереди
            queue.removeTransportFromQueue(queue);
            //обновляем счетчик проверенного транспорта
            countOfLeave++;
        } 
        Thread.interrupted();
    }
}
