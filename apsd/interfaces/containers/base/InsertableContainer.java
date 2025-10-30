package apsd.interfaces.containers.base;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<Data> extends Container { // Must extend Container

  // Insert
  public boolean Insert(Data dat);

  // InsertAll

  // InsertSome

}
