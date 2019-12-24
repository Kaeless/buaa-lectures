package org.feuyeux.async.hash;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestHash {
    @Test
    public void test() {
        int bucketSequence = HashCoon.hash("TEST.d", 100);
        log.info("bucketSequence={}", bucketSequence);
    }
}
