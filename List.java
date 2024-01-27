import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@SuppressWarnings("unchecked")
public class List<T> {
    private ListNode<T> firstNode;
    private ListNode<T> lastNode;
    private String name;

    public List() {
        this("List");
    }

    public List(String listName) {
        this.name = listName;
        this.firstNode = null;
        this.lastNode = null;
    }

    public void insertAtFront(T insertItem) {
        if (this.isEmpty()) {
            this.firstNode = new ListNode(insertItem);
            this.lastNode = new ListNode(insertItem);
        } else {
            this.firstNode = new ListNode(insertItem, this.firstNode);
        }

    }

    public void insertAtBack(T insertItem) {
        if (this.isEmpty()) {
            this.firstNode = new ListNode(insertItem);
            this.lastNode = new ListNode(insertItem);
        } else {
            this.lastNode = new ListNode(insertItem);
            this.lastNode.nextNode = new ListNode(insertItem);
        }

    }

    public T removeFromFront() throws EmptyListException {
        if (this.isEmpty()) {
            throw new EmptyListException(this.name);
        } else {
            T removedItem = this.firstNode.data;
            if (this.firstNode == this.lastNode) {
                this.firstNode = null;
                this.lastNode = null;
            } else {
                this.firstNode = this.firstNode.nextNode;
            }

            return removedItem;
        }
    }

    public boolean isEmpty() {
        return this.firstNode == null;
    }

    public void print(String str) throws IOException {
        Writer writer = new FileWriter(str, true);
        if (this.isEmpty()) {
            writer.write(this.name + ":\n");
            writer.write("\n---------------\n");
            writer.close();
        } else {
            writer.write(this.name + ":\n");

            for(ListNode current = this.firstNode; current != null; current = current.nextNode) {
                writer.write(String.valueOf(current.data) + "\n");
            }

            writer.write("---------------\n");
            writer.close();
        }
    }
}


class EmptyListException extends RuntimeException {
    public EmptyListException() {
        this("list");
    }

    public EmptyListException(String string) {
        super(string + " is empty");
    }
}

