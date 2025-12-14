package test.classes.containers.sequences;

import apsd.classes.containers.sequences.CircularVector;
import apsd.classes.utilities.Natural;

public class CircularVectorTest {

    public static void main(String[] args) {

        CircularVector<Long> circularVector = new CircularVector<>(Natural.Of(10));
        circularVector.SetAt(100L, Natural.Of(0));
        circularVector.SetAt(101L, Natural.Of(1));
        circularVector.SetAt(102L, Natural.Of(2));
        circularVector.SetAt(103L, Natural.Of(3));
        circularVector.SetAt(104L, Natural.Of(4));
        circularVector.SetAt(105L, Natural.Of(5));
        circularVector.SetAt(106L, Natural.Of(6));
        circularVector.SetAt(107L, Natural.Of(7));
        circularVector.SetAt(108L, Natural.Of(8));
        circularVector.SetAt(109L, Natural.Of(9));

        circularVector.ShiftRight(Natural.Of(2), Natural.Of(3));

        System.out.println(circularVector.GetAt(Natural.Of(0)));
        System.out.println(circularVector.GetAt(Natural.Of(1)));
        System.out.println(circularVector.GetAt(Natural.Of(2)));
        System.out.println(circularVector.GetAt(Natural.Of(3)));
        System.out.println(circularVector.GetAt(Natural.Of(4)));
        System.out.println(circularVector.GetAt(Natural.Of(5)));
        System.out.println(circularVector.GetAt(Natural.Of(6)));
        System.out.println(circularVector.GetAt(Natural.Of(7)));
        System.out.println(circularVector.GetAt(Natural.Of(8)));
        System.out.println(circularVector.GetAt(Natural.Of(9)));

        circularVector.Clear();
    }

}
