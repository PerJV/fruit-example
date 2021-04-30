package org.acme.cxf;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeFruitWebServiceIT extends FruitWebServiceTest {

    // Execute the same tests but in native mode.
}