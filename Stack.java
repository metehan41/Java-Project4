import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Stack<T> extends List {
    private List<T> stackList;
    public String name;
    public static ArrayList parts = new ArrayList();

    public Stack(String name) {
        this.stackList = new List(name);
        parts.add(this.stackList);
        this.name = name;
    }

    public void push(T object) {
        this.stackList.insertAtFront(object);
    }

    public T pop() throws EmptyListException {
        return this.stackList.removeFromFront();
    }

    public boolean isEmpty() {
        return this.stackList.isEmpty();
    }

    public void print(String str) throws IOException {
        this.stackList.print(str);
    }
}

