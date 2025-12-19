package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Box;

public interface OrderedSet<Data extends Comparable<? super Data>> extends Set<Data> { // Must extend Set

    // Min
    default Data Min() {

        Box<Data> minBox = new Box<Data>();

        TraverseForward(currentElement -> {
            if (minBox.Get() == null) {
                minBox.Set(currentElement);
            } else {
                int compareResult = currentElement.compareTo(minBox.Get());
                if (compareResult < 0) { /* currentElement < minBox */
                    minBox.Set(currentElement);
                }
            }

            return false;
        });

        return minBox.Get();
    }

    // RemoveMin
    default void RemoveMin() {
        Data min = Min();
        if (min != null) {
            Remove(min);
        }
    }

    // MinNRemove
    default Data MinNRemove() {
        Data min = Min();
        if (min != null) {
            Remove(min);
        }
        return min;
    }

    // Max
    default Data Max() {

        Box<Data> maxBox = new Box<Data>();

        TraverseForward(currentElement -> {
            if (maxBox.Get() == null) {
                maxBox.Set(currentElement);
            } else {
                int compareResult = currentElement.compareTo(maxBox.Get());
                if (compareResult > 0) { /* currentElement > maxBox */
                    maxBox.Set(currentElement);
                }
            }

            return false;
        });

        return maxBox.Get();
    }

    // RemoveMax
    default void RemoveMax() {
        Data max = Max();
        if (max != null) {
            Remove(max);
        }
    }

    // MaxNRemove
    default Data MaxNRemove() {
        Data max = Max();
        if (max != null) {
            Remove(max);
        }
        return max;
    }

    // Predecessor
    default Data Predecessor(Data data) {

        if (data == null) {
            throw new IllegalArgumentException("Data parameter can't be null!");
        }

        Box<Data> predecessorBox = new Box<Data>();

        TraverseForward(currentElement -> {

            if (currentElement.compareTo(data) >= 0) { /* Siccome i dati sono in ordine crescente */
                return true;
            }

            predecessorBox.Set(currentElement);
            return false;
        });

        return predecessorBox.Get();
    }

    // RemovePredecessor
    default void RemovePredecessor(Data data) {
        Data pred = Predecessor(data);
        if (pred != null) {
            Remove(pred);
        }
    }

    // PredecessorNRemove
    default Data PredecessorNRemove(Data data) {
        Data pred = Predecessor(data);
        if (pred != null) {
            Remove(pred);
        }
        return pred;
    }

    // Successor
    default Data Successor(Data data) {

        if (data == null) {
            throw new IllegalArgumentException("Data parameter can't be null!");
        }

        Box<Data> successorBox = new Box<Data>();

        TraverseBackward(currentElement -> {

            if (currentElement.compareTo(data) <= 0) {
                return true;
            }

            successorBox.Set(currentElement);
            return false;
        });

        return successorBox.Get();
    }

    // RemoveSuccessor
    default void RemoveSuccessor(Data data) {
        Data successor = Successor(data);
        if (successor != null) {
            Remove(successor);
        }
    }

    // SuccessorNRemove
    default Data SuccessorNRemove(Data data) {
        Data successor = Successor(data);
        if (successor != null) {
            Remove(successor);
        }
        return successor;
    }

}
