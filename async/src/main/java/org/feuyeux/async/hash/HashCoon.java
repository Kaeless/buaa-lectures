package org.feuyeux.async.hash;

import com.google.common.hash.Hashing;

public class HashCoon {
    public static int hash(String input, int buckets) {
        return Hashing.consistentHash(input.hashCode(), buckets);
    }
}
