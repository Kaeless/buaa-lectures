package org.feuyeux.async.cache;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CacheConfig {
    static final int UNSET_INT = -1;
    private int maxSize = UNSET_INT;
    private int expireAfterAccessSnd = UNSET_INT;
    private int expireAfterWriteSnd = UNSET_INT;
}
