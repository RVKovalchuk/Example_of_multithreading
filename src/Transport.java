//создаем класс транспорта
public class Transport {
    //объявляем переменные на количество пассажиров, количество багажа, номер транспорта
    public final int quantityOfPassengers;
    public final int quantityOfBaggage;
    public final int numberOfTransport;

    //создаем конструктор для создания объекта класса транспорт
    public Transport(int quantityOfPassengers, int quantityOfBaggage, int numberOfTransport) {
        this.quantityOfPassengers = quantityOfPassengers;
        this.quantityOfBaggage = quantityOfBaggage;
        this.numberOfTransport = numberOfTransport;
    }

    //геттеры на получение номера транспорта, получение количества пассажиров и количества багажа
    public int getNumberOfTransport() {
        return numberOfTransport;
    }

    public int getQuantityOfPassengers() {
        return quantityOfPassengers;
    }

    public int getQuantityOfBaggage() {
        return quantityOfBaggage;
    }
}
