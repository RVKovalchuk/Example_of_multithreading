import java.util.ArrayDeque;

//создаем класс очереди для транспорта
public class Queue {
    //объявляем максимальное количество машин в очереди
    private final int maxQuantityOfTransportAtQueue = 10;
    //объявляем double-ended лист для очереди (deque для работы с первым и последним элементами очереди).
    private final ArrayDeque<Transport> queueList;
    //объявляем минимально допустимое количество транспорта в очереди
    private int quantityOfTransportInQueue = 0;

    //создаем конструктор для создание объекта класса
    public Queue() {
        queueList = new ArrayDeque<>();
    }

    //создаем синхронизированный метод для добавления транспорта в очередь
    public synchronized void addTransportToQueue(Transport transport) {
        //ловим interrupted-исключение
        try {
            //проверяем очередь на возможность размещения в очереди
            if (quantityOfTransportInQueue < maxQuantityOfTransportAtQueue) {
                //снимаем ожидание с метода
                notifyAll();
                //добавляем транспорт в очередь последним
                queueList.addLast(transport);
                //выводим информацию
                System.out.printf("#%s arrived to queue. %s-passengers, %s-ton of baggage. \n",
                        transport.getNumberOfTransport(), transport.getQuantityOfPassengers(),
                        transport.getQuantityOfBaggage());
                //обновляем счетчик количества транспорта в очереди
                quantityOfTransportInQueue++;
                //ставим поток в сон на 100 мс
                Thread.sleep(100);
                //в противном случае:
            } else {
                //выводим сообщение о заполненности очереди
                System.out.println("Transport can't stay here. Queue is full.");
                //отправляем метод в ожидание
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //создаем синхронизированный метод на удаление транспорта из очереди
    public synchronized void removeTransportFromQueue(Queue queue) {
        //инициализируем минимальное возможное количество транспорта в очереди
        int minQuantityOfTransportAtQueue = 0;
        //ловим interrupt-исключения
        try {
            //проверяем очередь на наличие транспорта в ней
            if (quantityOfTransportInQueue > minQuantityOfTransportAtQueue) {
                //снимаем ожидание с метода
                notifyAll();
                //создаем объект класса транспорт, возвращая первый элемент из очереди
                Transport transport = queueList.peek();
                //выводим информацию о времени проверки транспорта
                System.out.printf("Time of checking #%s is: %s milliseconds. Here was %s-passengers, %s-ton of " +
                                "baggage\n", transport.getNumberOfTransport(),
                        timeForCheckingOfTransport(transport), transport.getQuantityOfPassengers(),
                        transport.getQuantityOfBaggage());
                //отправляем поток в ожидание на время проверки транспорта
                Thread.sleep(timeForCheckingOfTransport(transport));
                //выводим информацию о том, что транспорт покинул очередь
                System.out.printf("#%s, with %s-passengers, with %s-ton of baggage leave the queue.\n",
                        transport.getNumberOfTransport(), transport.getQuantityOfPassengers(),
                        transport.getQuantityOfBaggage());
                //удаляем первый элемент очереди
                queueList.removeFirst();
                //обновляем счетчик количества транспорта в очереди
                quantityOfTransportInQueue--;
                //в противном случае:
            } else {
                //выводим сообщение о возможности размещения транспорта в очереди
                System.out.println("Transport can stay here. Queue isn't full.");
                //отправляем метод в ожидание
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //создаем метод для получения времени, необходимого для проверки транспорта
    public long timeForCheckingOfTransport(Transport transport) {
        //инициализируем коэффициенты затрат на проверку одного пассажира и одной тонны багажа
        int timeForCheckingPassenger = 50;
        int timeForCheckingBaggage = 100;
        //возвращаем время
        return ((long) transport.getQuantityOfBaggage() * timeForCheckingBaggage +
                (long) transport.getQuantityOfPassengers() * timeForCheckingPassenger);
    }

    //создаем метод на проверку заполненности очереди
    public boolean checkOfFullnessQueue() {
        return quantityOfTransportInQueue < maxQuantityOfTransportAtQueue;
    }
}
