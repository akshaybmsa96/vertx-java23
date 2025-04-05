package com.akki;


import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class Test {
    private final Addition addition;

    public void test() {
        System.out.println(addition.add(2,3));
    }
}
