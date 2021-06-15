package Domain;

import java.io.Serializable;

public class Note implements Serializable {
    /*This attribute is to ensure when serializing this object, the program knows what kind of object it is reading
    when deserializing the project.
    */
    private static final long serialVersionUID = 2000;
    //List of attributes each of our Note objects possess.
    private int noteId;
    private String text;
    private String references;
    private double xPos, yPos;

    /*Default constructor for our Note object.
    text and reference is set upon creation of the object, and therefore stored.
    */
    public Note(String text, String references){
        this.text = text;
        this.references = references;
    }

    //Generic getters and setters for the different attributes. getText() is also used for exporting.
    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getReference() {
        return references;
    }

    public void setReference(String reference) {
        this.references = reference;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    //The toString of our Note object. This is formatted specifically for being readable in listviews
    @Override
    public String toString(){
        return text +" | Reference: "+ references;
    }

    //Getters and setters added later in the programming process. So users can save the position of notes on the timeline.
    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

}
