package feuyeux.pattern.structural.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CaseInputStream extends FilterInputStream {
    private CASE_FLAG caseFlag;

    public CaseInputStream(InputStream in, CASE_FLAG caseFlag) {
        super(in);
        this.caseFlag = caseFlag;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return filter(c);
    }

    @Override
    public int read(byte[] b) throws IOException {
        int r = super.read(b);
        for (int i = 0; i < r; i++) {
            b[i] = filter(b, i);
        }
        return r;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int r = super.read(b, off, len);
        for (int i = off; i < off + r; i++) {
            b[i] = filter(b, i);
        }
        return r;
    }

    private byte filter(byte[] b, int i) {
        return CASE_FLAG.UPPER.equals(caseFlag) ? (byte)Character.toUpperCase((char)b[i])
            : (byte)Character.toLowerCase((char)b[i]);
    }

    private int filter(int c) {
        return c == -1 ? c : caseFlag.equals(CASE_FLAG.LOWER) ? Character.toLowerCase(c) : Character.toUpperCase(c);
    }

    public enum CASE_FLAG {
        UPPER,
        LOWER
    }
}
