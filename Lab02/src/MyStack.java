import static java.lang.System.arraycopy;
import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack {

    Integer[] stack;
    int size;

    public MyStack(int initCap){
        this.stack = new Integer[initCap];
    }

    public MyStack(){
        this(0);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Integer peek(){
        return this.stack[0];
    }

    public Integer pop(){
        if(size < 1){
           throw new EmptyStackException(); 
        }

        Integer out = this.stack[0];
        Integer[] temp = new Integer[this.stack.length-1];
        System.arraycopy(this.stack, 1, temp, 0, temp.length-1); //omits topmost element 
        this.stack = temp;
        
        this.size--;
        return out;
        //TODO test
    }

    public void push(Integer in){
        Integer[] temp = new Integer[stack.length+1];
        System.arraycopy(this.stack, 0, temp, 1, this.stack.length);
        temp[0] = in;
        this.stack = temp;
        this.size++;
    //TODO test
    }

    private void doubleCapacity(){
        this.stack = Arrays.copyOf(this.stack, this.stack.length*2);
    }

    @Override
    public String toString(){
        String out = "";
        for (int i = 0; i < this.stack.length; i++) {
            if(this.stack[i] != null){
                if(i == 0){
                    out += this.stack[i] + "\t<----- TOP\n";
                    continue;
                }
                out += this.stack[i] + "\n";
                if(i == this.stack.length-1){
                    out += "-------";
                }
            }
        }
        return out;
    }


}
