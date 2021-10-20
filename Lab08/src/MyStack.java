import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack implements StackADT {

    Square[] stack;
    int size;

    public MyStack(int initCap){
        this.stack = new Square[initCap];
    }

    public MyStack(){
        this(0);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Square peek(){
        return this.stack[0];
    }

    public Square pop(){
        if(size < 1){
            System.out.println("bad");
           throw new EmptyStackException(); 
        }

        Square out = this.stack[0];
        Square[] temp = new Square[this.stack.length-1];
        System.arraycopy(this.stack, 1, temp, 0, temp.length); //omits topmost element 
        this.stack = temp;
        
        this.size--;
        return out;
    }

    public void push(Square in){
        Square[] temp = new Square[stack.length+1];
        System.arraycopy(this.stack, 0, temp, 1, this.stack.length);
        temp[0] = in;
        this.stack = temp;
        this.size++;
    }

    // private void doubleCapacity(){
    //     this.stack = Arrays.copyOf(this.stack, this.stack.length*2);
    // }

    @Override
    public String toString(){
        // String out = "";
        // for (int i = 0; i < this.stack.length; i++) {
        //     if(this.stack[i] != null){
        //         if(i == 0){
        //             out += this.stack[i].toString() + "\t<----- TOP\n";
        //             continue;
        //         }
        //         out += this.stack[i].toString() + "\n";
        //         if(i == this.stack.length-1){
        //             out += "-------";
        //         }
        //     }
        // }
        // return out;
        String temp = Arrays.toString(this.stack); // [6, 7, 8, null, null, null] worst case scenario
        while(temp.contains("null")){
            temp = temp.replace("null", "");
        }
        // while(temp.contains(", ]")){
        //     temp = temp.replace(", ]", "]");
        // }
        // while(temp.contains(", ,")){
        //     temp = temp.replace(", ,", "");
        // }
        // while(temp.contains(",]")){
        //     temp = temp.replace(",]", "]"); 
        // }
        // while(temp.contains(" ]")){
        //     temp = temp.replace(" ]", "]");
        // }
    return temp;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.stack = new Square[0];
        this.size = 0;
    }


}
