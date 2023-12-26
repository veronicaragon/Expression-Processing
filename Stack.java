
public class Stack{
    private Node top;

    public Stack() {
        top = null;
    }

    public boolean isEmpty(){
        return (top ==null);
    }

    public void push(Object newItem){
        top = new Node(newItem,  top);
    }

    public Object pop(){
        if (isEmpty()){
            System.out.println(
             "Trying to pop when stack is empty");
            return null;
        }else{
            Node temp = top;
            top = top.next;
            return temp.info;
        }
    }


    void popAll(){
        top = null;
    }

    public Object peek(){
        if (isEmpty()){
           System.out.println(
            "Trying to peek when stack is empty");
           return null;
        }else{	
           return top.info;
        }
    }
}// End of Stack using a linked list

