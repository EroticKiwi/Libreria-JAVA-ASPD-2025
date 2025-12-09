package zapsdtest.testframework.containers.collections;

import apsd.interfaces.containers.collections.OrderedChain;

public interface OrderedChainTest<Data extends Comparable<Data>, Con extends OrderedChain<Data>> extends ChainTest<Data, Con>, OrderedSetTest<Data, Con> {}
