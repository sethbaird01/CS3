import java.util.Arrays;

public class NumberList {

	private Integer[] arr;
	private int size = 0;

	public NumberList() { //generic constructor
		this.arr = new Integer[2];
		//empty 2-element array created
	}//done

	public NumberList(int in) { //specify list size
		this.arr = new Integer[in];
	}//done

	public int size() {
		//basically getter function
		return this.size;
	}//done
	
	public boolean isEmpty() {
		return this.size() == 0;
	}//done
	
	public String toString() {
		String temp = Arrays.toString(this.arr); // [6, 7, 8, null, null, null] worst case scenario
			while(temp.contains("null")){
				temp = temp.replace("null", "");
			}
			while(temp.contains(", ]")){
				temp = temp.replace(", ]", "]");
			}
			while(temp.contains(", ,")){
				temp = temp.replace(", ,", "");
			}
			while(temp.contains(",]")){
				temp = temp.replace(",]", "]"); 
			}
			while(temp.contains(" ]")){
				temp = temp.replace(" ]", "]");
			}
		//please please please
		return temp;
	}//done 
	
	public void add(int index, int in) {

		if(index > this.arr.length) {//index is greater than
			//System.out.println("old arr length: "+this.arr.length + " old arr: "+Arrays.toString(this.arr));
			this.arr = Arrays.copyOf(this.arr, this.arr.length*2);
			//System.out.println("new arr length: "+this.arr.length + " new arr: " +Arrays.toString(this.arr));
			if(in > this.arr.length*2){
				System.out.println("bad");
			}
		}//arr length was less than target index, arr length now doubled
	
		Integer[] temp = new Integer[this.arr.length+1];

		//System.out.println("pre-step 1 this.arr: "+ Arrays.toString(this.arr)+" temp: "+Arrays.toString(temp)+" index: "+index);
        System.arraycopy(this.arr, 0, temp, 0, index);
		//System.out.println("step 1 done this.arr: "+ Arrays.toString(this.arr)+" temp: "+Arrays.toString(temp)+" index: "+index);

        temp[index] = in;
		//System.out.println("step 2 done this.arr: "+ Arrays.toString(this.arr)+" temp: "+Arrays.toString(temp)+" index: "+index);

        System.arraycopy(this.arr, index, temp, index+1, this.arr.length - index);
		//System.out.println("step 3 done this.arr: "+ Arrays.toString(this.arr)+" temp: "+Arrays.toString(temp)+" index: "+index);

		this.arr = temp;
		this.size++;
	}

	public boolean add(int in) {
		this.add(this.size, in);
		return true;
	}//done

	public Integer get(int index){
		if(index > this.size-1){
			//out of bounds
			throw new IndexOutOfBoundsException();
		}
			return this.arr[index];

	}

	public Integer set(int index, int in){
		int temp = this.arr[index]; //about to be replaced, stored temporarily
		this.arr[index] = in;
		return temp;
	}

	public Integer remove(int index){
		int numRemoved = this.arr[index];

		Integer[] temp = new Integer[this.arr.length];

		System.arraycopy(this.arr, 0, temp, 0, index); //first hald
		System.arraycopy(this.arr, index+1, temp, index, this.arr.length-index-1); //last half

		this.arr = temp;
		this.size--;
		return numRemoved;
	}

}