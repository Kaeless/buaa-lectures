package org.feuyeux.pattern.structural.adapter;

/**
 * Adapter
 */
public class UdsCore {
    private final QasCore qasCore;

    public UdsCore(QasCore qasCore) {
        this.qasCore = qasCore;
    }

    public String dialog(String query) {
        return "action for " + query;
    }

    public String qa(String question) {
        return qasCore.qa(question);
    }
}
