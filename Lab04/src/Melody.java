import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Melody {
    Queue<Note> notes;

    public Melody(Queue<Note> song) {
        notes = new LinkedList<Note>();
        notes = song;
    }

    private Queue<Note> deepReference(Queue<Note> in){
        Queue<Note> out = new LinkedList<>();
        Object[] inArr = in.toArray();

        for (int i = 0; i < inArr.length; i++) {
            Note toAdd = (Note) inArr[i];
            out.add(toAdd);
        }
        if(!Arrays.equals(inArr, out.toArray())){
        }
        return out;
    }

    public double getTotalDuration(){//coped from this.play() with minot modification
        double out = 0.0;
        Queue<Note> temp = deepReference(this.notes);
        Queue<Note> repeat = new LinkedList<Note>(); //stores note from repeated section to be played back again
        boolean recording = false; //if true, record all notes into second queue while they are played
        while(!temp.isEmpty()){
            Note cur = temp.remove();
            out += cur.getDuration();
            if(repeat.isEmpty() && cur.isRepeat() && !recording){//start of recording section
                // System.out.println("simulation of recording");
                recording = true; 
                continue;
            }
            if(!repeat.isEmpty() && cur.isRepeat() && recording){//end of recording section
                while(!repeat.isEmpty()){
                    // System.out.println("repeated note");
                    out += repeat.remove().getDuration();
                }
                recording = false;
                continue;
            }
            if(recording && !cur.isRepeat()){//middle of recording section
                repeat.add(cur);
                continue;
            }
        }
        return out;
    }

    @Override
    public String toString(){
        Queue<Note> temp = deepReference(this.notes);
        String out = "";
        while(!temp.isEmpty()){
            out += temp.remove().toString();
        }
        return out;
    }

    public void changeTempo(double tempo){
        Queue<Note> temp = deepReference(this.notes);
        Queue<Note> out = new LinkedList<Note>();

        while(!temp.isEmpty()){
            Note toAdd = temp.remove();
            toAdd.setDuration(toAdd.getDuration()*(1.0/tempo)); 
            //i don't know why i have to do 1/tempo, the gui may be a little broken
            out.add(toAdd);
        }

        this.notes = out;
    }

    public void reverse(){
        Stack<Note> reverse = new Stack<Note>();

        while(!this.notes.isEmpty()){
            reverse.push(this.notes.remove());
        }//reverse is full, temp is empty

        while(!reverse.isEmpty()){
            notes.add(reverse.pop());
        }
    }

    public void append(Melody other){
        Queue<Note> notesIn = deepReference(other.getNotes());
        while(!notesIn.isEmpty()){
            this.notes.add(notesIn.remove());
        }
    }

    public void play(){
        Queue<Note> temp = deepReference(this.notes);
        Queue<Note> repeat = new LinkedList<Note>(); //stores note from repeated section to be played back again
        boolean recording = false; //if true, record all notes into second queue while they are played
        while(!temp.isEmpty()){
            Note cur = temp.remove();
            cur.play();
            if(repeat.isEmpty() && cur.isRepeat() && !recording){//start of recording section
                // System.out.println("start of recording");
                recording = true; 
                continue;
            }
            if(!repeat.isEmpty() && cur.isRepeat() && recording){//end of recording section
                while(!repeat.isEmpty()){
                    // System.out.println("playing repeated note");
                    repeat.remove().play();
                }
                recording = false;
                continue;
            }
            if(recording && !cur.isRepeat()){//middle of recording section
                repeat.add(cur);
                continue;
            }
        }
    }

    public Queue<Note> getNotes(){
        return notes;
    }
}
