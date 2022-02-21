public class App_kpp {
    public static void main(String[] args) {
        //создаем объект класса Queue
        Queue queue = new Queue();
        //создаем два объекта класса GeneratorOfTransport
        GeneratorOfTransport generatorOfTransport1 = new GeneratorOfTransport(queue, 20);
        GeneratorOfTransport generatorOfTransport2 = new GeneratorOfTransport(queue, 30);
        //создаем объект класса Checkpoint
        Checkpoint checkpoint = new Checkpoint(queue, 40);
        //запускаем потоки
        generatorOfTransport1.start();
        generatorOfTransport2.start();
        checkpoint.start();
        //Остановка потоков !!!
        //Необходимо доделать остановку потоков и завершения программы !!!
        if (checkpoint.isInterrupted()) {
            try {
                checkpoint.join();
                generatorOfTransport1.join();
                generatorOfTransport2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkpoint.interrupt();
            generatorOfTransport1.interrupt();
            generatorOfTransport2.interrupt();
        }
    }
}
