class ListNode<T> {
    T data;
    ListNode<T> nextNode;

    ListNode(T object) {
        this(object, null);
    }

    ListNode(T object, ListNode<T> node) {
        this.data = object;
        this.nextNode = node;
    }
}
