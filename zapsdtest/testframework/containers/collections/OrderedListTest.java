package zapsdtest.testframework.containers.collections;

import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.collections.OrderedChain;
import apsd.interfaces.containers.collections.OrderedList;

public interface OrderedListTest<Data extends Comparable<Data>, Con extends OrderedList<Data> & List<Data> & OrderedChain<Data>> extends ListTest<Data, Con>, OrderedChainTest<Data, Con> {}
