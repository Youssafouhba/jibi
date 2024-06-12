package com.ensa.jibi.cmi.mappers;

public interface Mapper<A,B> {
    B mapTo(A a); // Map from type A to type B
    A mapFrom(B b); // Map from type B to type A
}
