import java.util.*;

public class QueueUtils {
    public static <T> void printQueueReverse(Queue<T> queue) {
        if (queue.isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }

        Stack<T> stack = new Stack<>();
        Queue<T> tempQueue = new LinkedList<>(queue);

        while (!tempQueue.isEmpty()) {
            stack.push(tempQueue.poll());
        }

        System.out.print("Очередь в обратном порядке: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}