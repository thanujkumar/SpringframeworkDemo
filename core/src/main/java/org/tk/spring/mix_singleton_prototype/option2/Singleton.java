package org.tk.spring.mix_singleton_prototype.option2;

//When using method inject make class as abstract
public abstract class Singleton {

    private Prototype prototype;

    //create proxy for this method using method injection
    public abstract Prototype createPrototype();

    //Though bean is prototype, each call for this will return same object
    public Prototype getPrototype() {
        return prototype; // You can change createPrototype method as getPrototype, here these methods shows the difference
    }

    public void setPrototype(Prototype prototype) {
        this.prototype = prototype;  // this will return all the time same object
    }
}
