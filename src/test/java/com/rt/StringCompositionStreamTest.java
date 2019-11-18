package com.rt;

import org.junit.Before;

public class StringCompositionStreamTest extends StringCompositionTestBase {

    @Before
    public void setup() {
        sc = StringCompositionStream::findComposites;
    }
}
