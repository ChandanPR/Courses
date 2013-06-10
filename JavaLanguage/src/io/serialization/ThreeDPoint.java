package io.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ThreeDPoint extends Point implements Serializable{
	
	private static final long serialVersionUID = 1856835860954L;

	public ThreeDPoint(int x, int y) {
		super(x, y);
	}
	
	private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        System.out.println("ThreeDPoint.readObject()");
        // Manually deserialize and initialize superclass state
        int x = s.readInt();
        int y = s.readInt();
//        init(x, y);
    }

    private void writeObject(ObjectOutputStream s)
            throws IOException {
    	System.out.println("ThreeDPoint.writeObject()");
        s.defaultWriteObject();

        // Manually serialize superclass state
        s.writeInt(getX());
        s.writeInt(getY());
    }

}
