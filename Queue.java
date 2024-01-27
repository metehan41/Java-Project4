import java.io.IOException;

@SuppressWarnings("unchecked")
public class Queue<T> {
    public List<T> queueList = new List("queue");
    public String key;
    public String type;
    public int number;
    public static int counter = 0;
    public int order;

    public Queue(String key, String tür, int number) {
        this.key = key;
        this.type = tür;
        this.number = number;
        ++counter;
        this.order = counter;
    }

    public void enqueue(T object) {
        this.queueList.insertAtBack(object);
    }

    public T dequeue() throws EmptyListException {
        return this.queueList.removeFromFront();
    }

    public boolean isEmpty() {
        return this.queueList.isEmpty();
    }

    public void print(String str) throws IOException {
        this.queueList.print(str);
    }

    public int getNumber() {
        return this.number;
    }
}
