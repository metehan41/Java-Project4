import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class Main {
    public static ArrayList<Stack> parts = new ArrayList();
    public static ArrayList<Queue> queueArrayList = new ArrayList();


    public static void main(String[] args) {
        readParts(args[0]);
        readItem(args[1]);
        readToken(args[2]);
        sortByNumber(queueArrayList);
        readTask(args[3]);
        writeOutput(args[4]);
    }

    public static void readParts(String str) {
        try {
            Scanner scanPart = new Scanner(new File(str));
            while (scanPart.hasNext()) {
                String name = scanPart.nextLine();
                Stack<String> stack = new Stack<>(name);
                parts.add(stack);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void readItem(String str) {

        try {
            Scanner scanItem = new Scanner(new File(str));
            while (scanItem.hasNext()) {
                String[] items = scanItem.nextLine().trim().split(" ");
                for (Stack stack: parts) {
                    if (stack.name != null && stack.name.equals(items[1])) {
                        stack.push(items[0]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void readToken(String str) {
        try {
            Scanner scanner  = new Scanner(new File(str));
            while (scanner.hasNext()) {
                String[] token = scanner.nextLine().trim().split(" ");
                Queue<String> tokens = new Queue<>(token[0], token[1], Integer.parseInt(token[2]));
                queueArrayList.add(tokens);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void readTask(String str) {
        try {
            Scanner scanner = new Scanner(new File(str));
            while (scanner.hasNext()) {
                String[] task = scanner.nextLine().trim().split("\t");
                switch (task[0]) {
                    case "BUY":
                        for (int i = 1; i < task.length; i ++) {
                            String[] strings = task[i].split(",");
                            int numberOfBuy = Integer.parseInt(strings[1]);
                            int numberOfBuy2 = Integer.parseInt(strings[1]);
                            while (numberOfBuy > 0) {
                                for (Queue queue : queueArrayList) {
                                    if (queue.type.equals(strings[0]) && queue.number >= numberOfBuy) {
                                        queue.number -= numberOfBuy;
                                        Queue.counter++;
                                        queue.order = Queue.counter;
                                        numberOfBuy = 0;
                                        Queue tmp = queue;
                                        queueArrayList.remove(queue);
                                        if (tmp.number > 0) {
                                            queueArrayList.add(tmp);
                                        }
                                        break;
                                    } else if (queue.type.equals(strings[0]) && queue.number < numberOfBuy) {
                                        numberOfBuy -= queue.number;
                                        queueArrayList.remove(queue);
                                        break;
                                    }
                                }
                            }
                            for (int i1 = 0; i1 < numberOfBuy2; i1++) {
                                for (Stack stack: parts) {
                                    if (strings[0].equals(stack.name)) {
                                        stack.pop();
                                    }
                                }
                            }
                        }
                        sortByNumber(queueArrayList);
                        break;

                    case "PUT":
                        for (int i = 1; i < task.length; i ++) {
                            String[] strings = task[i].split(",");
                            for (Stack stack: parts) {
                                if (strings[0].equals(stack.name)) {
                                    for (int j = 1; j < strings.length; j++) {
                                        stack.push(strings[j]);
                                    }
                                }
                            }
                        }
                        break;

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void sortByNumber(ArrayList<Queue> queueArrayList) {
        boolean flag = false;
        while (!flag) {
            flag = true;
            for (int i = 0; i < queueArrayList.size() -1; i++) {
                if (queueArrayList.get(i) != null && queueArrayList.get(i).number < queueArrayList.get(i + 1).number) {
                    flag = false;
                    Queue tmp = queueArrayList.get(i + 1);
                    queueArrayList.remove(i + 1);
                    queueArrayList.add(i + 1, queueArrayList.get(i));
                    queueArrayList.remove(i);
                    queueArrayList.add(i, tmp);
                } else if (queueArrayList.get(i) != null && queueArrayList.get(i).number == queueArrayList.get(i + 1).number) {
                    if (queueArrayList.get(i).order > queueArrayList.get(i + 1).order) {
                        flag = false;
                        Queue tmp = queueArrayList.get(i + 1);
                        queueArrayList.remove(i + 1);
                        queueArrayList.add(i + 1, queueArrayList.get(i));
                        queueArrayList.remove(i);
                        queueArrayList.add(i, tmp);
                    }
                }
            }
        }
    }


    public static void writeOutput(String str) {
        try {
            Writer writer = new FileWriter(str);
            writer.close();
            Writer writer2 = new FileWriter(str, true);
            for (Stack stack: parts) {
                stack.print(str);
            }
            writer2.write("Token Box:\n");
            for (int i = queueArrayList.size() - 1; i >= 0; i --) {
                if (queueArrayList.get(i).number > 0) {
                    writer2.write(queueArrayList.get(i).key + "\t" + queueArrayList.get(i).type + "\t" + queueArrayList.get(i).number + "\n");
                }
            }
            writer2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

