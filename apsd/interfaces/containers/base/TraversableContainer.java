package apsd.interfaces.containers.base;

// import apsd.classes.utilities.Box;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Accumulator;
import apsd.interfaces.traits.Predicate;

import java.util.Objects;

/**
 * Interface: MembershipContainer con supporto all'attraversamento.
 */

public interface TraversableContainer<Data> extends MembershipContainer<Data> { // Must extend MembershipContainer

    // TraverseForward
    public boolean TraverseForward(Predicate<Data> predicate);

    // TraverseBackward
    public boolean TraverseBackward(Predicate<Data> predicate);

    default <Acc> Acc FoldForward(Accumulator<Data, Acc> fun, Acc ini) { // fun = funzione da eseguire, ini = valore iniziale
        final Box<Acc> acc = new Box<>(ini);
        if (fun != null) {
            TraverseForward(dat -> {
                acc.Set(fun.Apply(dat, acc.Get()));
                return false;
            });
        }
        return acc.Get();
    }

    // FoldBackward
    default <Acc> Acc FoldBackward(Accumulator<Data, Acc> fun, Acc ini) {
        final Box<Acc> acc = new Box<>(ini);
        if (fun != null) {
            TraverseBackward(dat -> {
                if(dat != null){
                    acc.Set(fun.Apply(dat, acc.Get()));
                }
                return false;
            });
        }
        return acc.Get();
    }

    /* ************************************************************************ */
 /* Override specific member functions from Container                        */
 /* ************************************************************************ */
    // ...
    @Override
    default Natural Size(){
        long size = FoldForward((dat, count) -> Long.valueOf(count + 1L), 0L);
        return Natural.Of(size);
    }

    /* ************************************************************************ */
 /* Override specific member functions from MembershipContainer              */
 /* ************************************************************************ */
    @Override
    default boolean Exists(Data dat){
        if(IsEmpty()){
            return false;
        }
        return TraverseForward(currentElement -> Objects.equals(dat, currentElement)); // Se non sono uguali continui sennò ti fermi
    }
}
