package com.rt;

import org.junit.Before;

public class StringCompositionNonStreamTest extends StringCompositionTestBase {

    @Before
    public void setup() {
        sc = StringCompositionNonStream::findComposites;
    }
}
