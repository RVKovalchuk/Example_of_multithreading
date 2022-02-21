import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

//создаем класс-генератор транспорта, наследуемся от Thread
public class GeneratorOfTransport extends Thread {
    //объявляем объект класса очереди транспорта
    private final Queue queue;
    //инициализируем счетчик сгенерированного транспорта (ввиду многопоточности, создаем объект класса AtomicInteger)
    private final AtomicInteger countOfGeneratedTransport = new AtomicInteger();
    //объявляем максимальное количество генерируемых транспортов
    public int maxQuantityOfGenerate;
    //создаем объект класса Random
    Random random = new Random();

    //создаем конструктор для создания объекта класса-генератора
    public GeneratorOfTransport(Queue queue, int maxQuantityOfGenerate) {
        this.queue = queue;
        this.maxQuantityOfGenerate = maxQuantityOfGenerate;
        //устанавливаем стартовое значение для счетчика сгенерированного транспорта
        countOfGeneratedTransport.set(0);
    }

    //переопределяем метод run()
    @Override
    public void run() {
        //объявляем условие для ограничения количества генерируемого транспорта
        while (countOfGeneratedTransport.get() < maxQuantityOfGenerate) {
            //объявляем условие проверки заполненности очереди
            if (queue.checkOfFullnessQueue()) {
                //генерируем объект класса Transport  и добавляем его в очередь
                queue.addTransportToQueue(new Transport(generateQuantityOfPassengers(), generateQuantityOfBaggage(),
                        generateNumberOfTransport()));
                //обновляем счетчик сгенерированного транспорта
                countOfGeneratedTransport.getAndIncrement();
            }
        }
    }

    //создаем метод для генерации количества багажа
    public int generateQuantityOfBaggage() {
        //инциализируем максимальное количество багажа и минимальное количество багажа
        int maxQuantityOfBaggage = 15;
        int minQuantityOfBaggage = 1;
        //возвращаем рандомное значение в диапазоне мин-макс
        return (random.nextInt(maxQuantityOfBaggage - minQuantityOfBaggage + 1) + minQuantityOfBaggage);
    }

    //создаем метод для генерации количества пассажиров
    public int generateQuantityOfPassengers() {
        //инциализируем максимальное количество пассажиров и минимальное количество пассажиров
        int maxQuantityOfPassengers = 100;
        int minQuantityOfPassengers = 1;
        //возвращаем рандомное значение в диапазоне мин-макс
        return (random.nextInt(maxQuantityOfPassengers - minQuantityOfPassengers + 1) + minQuantityOfPassengers);
    }

    //создаем метод для получения рандомного номера транспорта в диапазоне от 10000-99999
    public int generateNumberOfTransport() {
        //возвращаем значение номера транспорта
        return (random.nextInt(99999 - 10000 + 1) + 10000);
    }
}
